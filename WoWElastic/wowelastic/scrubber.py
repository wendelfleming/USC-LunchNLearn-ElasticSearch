#
# Copyright 2015 wendel fleming
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#    http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#
__author__ = 'wendel fleming'

from time import sleep
import os, glob
import re
import json
import requests
import tarfile

from wowelastic.configurator import WoWAPIConfigurator

class WoWScrubber:
    def __init__(self, blizzKey, rawDir, scrubDir, useTar, tar_filename):
        self.blizzardAPIKey = blizzKey
        self.raw_directory = rawDir
        self.scrubbed_directory = scrubDir
        if(not self.scrubbed_directory.endswith("/")):
            self.scrubbed_directory += "/"
        self.use_tarball = useTar
        self.tarball_name = tar_filename
        self.character_classes = self.__initPlayerClasses()
        self.character_races = self.__initRaces()
        self.item_class = dict()
        self.item_subclasses = dict()
        self.__initItemClasses()
        self.quality = self.__initQuality()
        self.bind_list = self.__initBind()
        self.inventory_type = self.__initInventoryType()
        self.bonus_stats = self.__initBonusStat()



    def __initPlayerClasses(self):
        # LOAD CLASSES
        # https://us.api.battle.net/wow/data/character/classes?locale=en_US&apikey=
        api_class_url = "https://us.api.battle.net/wow/data/character/classes?locale=en_US&apikey=%s"
        class_url = api_class_url % (self.blizzardAPIKey)
        response = requests.get(class_url)
        char_classes = dict()
        player_class_json = dict()
        if(response.status_code == 200):
            player_class_json = response.json()
            # Cache the successful call
            with open("wow_classes.json", 'w') as f:
                json.dump(response.json(), f)
                f.closed
        else:
            # Load from cached json file if call wasn't successful
            with open("wow_classes.json") as json_classes:
                player_class_json = json.load(json_classes)
                json_classes.close()
        for pl_cl in player_class_json.get('classes'):
            char_classes[pl_cl.get('id')] = pl_cl.get('name')
        return char_classes



    def __initRaces(self):
        # LOAD RACES
        # https://us.api.battle.net/wow/data/character/races?locale=en_US&apikey=
        # Races API has duplicates
        api_race_url = "https://us.api.battle.net/wow/data/character/races?locale=en_US&apikey=%s"
        race_url = api_race_url % (self.blizzardAPIKey)
        response = requests.get(race_url)
        char_races = dict()
        player_race_json = dict()
        if(response.status_code == 200):
            player_race_json = response.json()
            # Cache the successful call
            with open("wow_races.json", 'w') as f:
                json.dump(response.json(), f)
                f.closed
        else:
            # Load from cached json file if call wasn't successful
            with open("wow_races.json") as json_races:
                player_race_json = json.load(json_races)
                json_races.close()
        for pl_ra in player_race_json.get('races'):
            char_races[pl_ra.get('id')] = dict()
            char_races[pl_ra.get('id')]['name'] = pl_ra.get('name')
            char_races[pl_ra.get('id')]['side'] = pl_ra.get('side')
        return char_races



    def __initItemClasses(self):
        # LOAD ITEM CLASSES AND SUBCLASSES
        # https://us.api.battle.net/wow/data/item/classes?locale=en_US&apikey=
        api_itemclass_url = "https://us.api.battle.net/wow/data/item/classes?locale=en_US&apikey=%s"
        itemclass_url = api_itemclass_url % (self.blizzardAPIKey)
        response = requests.get(itemclass_url)
        item_class_json = dict()
        if(response.status_code == 200):
            item_class_json = response.json()
            # Cache the successful call
            with open("wow_itemclasses.json", 'w') as f:
                json.dump(response.json(), f)
                f.closed
        else:
            # Load from cached json file if call wasn't successful
            with open("wow_itemclasses.json") as json_items:
                item_class_json = json.load(json_items)
                json_items.close()
        for it_cl in item_class_json.get('classes'):
            class_id = it_cl.get('class')
            self.item_class[class_id] = it_cl.get('name')
            self.item_subclasses[class_id] = dict()
            for it_scl in it_cl.get('subclasses'):
                self.item_subclasses[class_id][it_scl.get('subclass')] = it_scl.get('name')

        # some values are not in the API call but in the data, add them here
        if 10 not in self.item_class:
            self.item_class[10] = 'No longer available'
        if 10 not in self.item_subclasses:
            self.item_subclasses[10] = dict()
            if 0 not in self.item_subclasses[10]:
                self.item_subclasses[10][0] = "No longer available"
        if 8 not in self.item_subclasses[12]:
            self.item_subclasses[12][8] = "Quest no longer available"
        if 3 not in self.item_subclasses[12]:
            self.item_subclasses[12][3] = "Quest no longer available"
        if 12 not in self.item_subclasses[15]:
            self.item_subclasses[15][12] = "No longer available"



    def __initQuality(self):
        # QUALITY LIST
        # http://wowwiki.wikia.com/API_ITEM_QUALITY_COLORS
        qlty = {
            0: {'name': 'Poor', 'color': '9d9d9d' },
            1: {'name': 'Common', 'color': 'ffffff' },
            2: {'name': 'Uncommon', 'color': '1eff00' },
            3: {'name': 'Rare', 'color': '0081ff' },
            4: {'name': 'Epic', 'color': 'c600ff' },
            5: {'name': 'Legendary', 'color': 'ff8000' },
            6: {'name': 'Artifact', 'color': 'e5cc80' },
            7: {'name': 'Heirloom', 'color': 'e5cc80' }
        }
        return qlty



    def __initBind(self):
        # ITEM BIND LIST
        # pieced together
        binds = {
            0: 'No Bind',
            1: 'Binds when picked up',
            2: 'Binds when equipped',
            3: 'Binds when used',
            4: 'Quest item'
        }
        return binds



    def __initInventoryType(self):
        # INVENTORY TYPE
        # http://wowwiki.wikia.com/InventorySlotId
        inv_type = {
            0: 'Ammo',
            1: 'Head',
            2: 'Neck',
            3: 'Shoulder',
            4: 'Shirt',
            5: 'Chest',
            6: 'Waist',
            7: 'Legs',
            8: 'Feet',
            9: 'Wrist',
            10: 'Hands',
            11: 'Finger 1',
            12: 'Finger 2',
            13: 'Trinket 1',
            14: 'Trinket 2',
            15: 'Back',
            16: 'Main Hand',
            17: 'Off Hand',
            18: 'Ranged',
            19: 'Tabard',
            # 20: 'First Bag',
            20: 'Chest',               # http://us.battle.net/wow/en/item/99584
            # 21: 'Second Bag',
            21: 'Main Hand',           # http://us.battle.net/wow/en/item/18610
            # 22: 'Third Bag',
            22: 'Off Hand',            # http://us.battle.net/wow/en/item/35072
            # 23: 'Fourth Bag',
            23: 'Off Hand',            # http://us.battle.net/wow/en/item/69632
            24: 'Removed',             # http://us.battle.net/wow/en/item/49255
            25: 'Removed',             # http://us.battle.net/wow/en/item/68608
            26: 'No longer available'  # http://www.wowhead.com/item=42496/furious-gladiators-heavy-crossbow#comments
        }
        return inv_type



    def __initBonusStat(self):
        # BONUS STATS LIST
        # http://us.battle.net/en/forum/topic/15700610010
        bonuses = {
            "-1": "None",
            -1: "None",
            0: "Mana",
            1: "Health",
            3: "Agility",
            4: "Strength",
            5: "Intellect",
            6: "Spirit",
            7: "Stamina",
            # //-------------------------------
            12: "Defense Skill",
            13: "Dodge",
            14: "Parry",
            15: "Block",
            16: "Melee Hit",
            17: "Ranged Hit",
            18: "Spell Hit",
            19: "Melee Crit",
            20: "Ranged Crit",
            21: "Spell Crit",
            22: "Melee Hit Taken",
            23: "Ranged Hit Taken",
            24: "Spell Hit Taken",
            25: "Melee Crit Taken",
            26: "Ranged Crit Taken",
            27: "Spell Crit Taken",
            28: "Melee Haste",
            29: "Ranged Haste",
            30: "Spell Haste",
            31: "Hit",
            32: "Crit",
            33: "Hit Taken",
            34: "Crit Taken",
            35: "Resilience",
            36: "Haste",
            37: "Expertise",
            38: "Attack Power",
            39: "Ranged Attack Power",
            40: "Versatility",
            41: "Spell Healing Done", #// deprecated
            42: "Spell Damage Done", #// deprecated
            43: "Mana Regeneration",
            44: "Armor Penetration",
            45: "Spell Power",
            46: "Health Regen",
            47: "Spell Penetration",
            48: "Block Value",
            49: "Mastery",
            50: "Bonus Armor",
            51: "Fire Resistance",
            52: "Frost Resistance",
            53: "Holy Resistance",
            54: "Shadow Resistance",
            55: "Nature Resistance",
            56: "Arcane Resistance",
            57: "PVP Power",
            58: "Amplify",
            59: "Multistrike",
            60: "Readiness",
            61: "Speed",
            62: "Leech",
            63: "Avoidence",
            64: "Indestructible",
            65: "WOD_5",
            66: "Cleave",
            # //-------------------------------
            71: "Strength, Agility, Intellect",
            72: "Strength, Agility",
            73: "Agility, Intellect",
            74: "Strength, Intellect"
        }
        return bonuses



    def __scandirs(self, path):
        for currentFile in glob.glob( os.path.join(path, '*') ):
            if os.path.isdir(currentFile):
                self.__scandirs(currentFile)
            else:
                hashed_path = re.sub(self.raw_directory, '', currentFile)
                self.__runFile(currentFile, hashed_path)



    def __runFile(self, file, index_filepath):
        with open(file) as json_data:
            d = json.load(json_data)
            json_data.close()
            self.__handleAvailableContexts(d, index_filepath)



    def __apiRequest(self, itemCode, index_filepath, error_count): #, filename):
        api_url = "https://us.api.battle.net/wow/item/%s?locale=en_US&apikey=%s"
        item_url = api_url % (itemCode, self.blizzardAPIKey)
        response = requests.get(item_url)

        if(response.status_code == 200):
            self.__scrubData(response.json(), index_filepath)
        elif(response.status_code == 404):
            print("404\n")
        else:
            if error_count < 5:
                error_count += 1
                sleep(0.05)
                print("ERROR: " + response.status_code + " - " + itemCode)
                self.__apiRequest(itemCode, index_filepath, error_count)
            else:
                raise ValueError("HTTP Status Code is %d" % (response.status_code))



    def __scrubData(self, data, index_filepath): #, character_classes, character_races, item_class, item_subclasses, quality, bind_list, inventory_type, bonus_stats, blizzardAPIKey):
        data['indexPath'] = index_filepath

        if 'icon' not in data:
            data['icon'] = "inv_misc_questionmark"

        if data.get('itemClass') in self.item_subclasses:
            if data.get('itemSubClass') in self.item_subclasses[data.get('itemClass')]:
                item_subclass_id = data.get('itemSubClass')
                data['itemSubClass'] = self.item_subclasses[data.get('itemClass')][item_subclass_id]
            else:
                raise ValueError("UNKNOWN CLASS/SUBCLASS: " + str(data.get('id')))

        if data.get('itemClass') in self.item_class:
            item_class_id = data.get('itemClass')
            data['itemClass'] = self.item_class[item_class_id]
        else:
            raise ValueError("UNKNOWN CLASS: " + str(data.get('id')))

        quality_id = data.get('quality')
        data['quality'] = self.quality.get(quality_id).get('name')

        if data.get('itemBind') not in self.bind_list:
            raise ValueError("ITEM NOT HERE: " + str(data.get('id')))

        bind_id = data.get('itemBind')
        data['itemBind'] = self.bind_list.get(bind_id)

        inventory_type_id = data.get('inventoryType')

        data['inventoryType'] = self.inventory_type.get(inventory_type_id)

        for it_stat in data.get('bonusStats'):
            stat_id = it_stat.get('stat')
            it_stat['stat'] = self.bonus_stats[stat_id]

        if 'allowableClasses' in data:
            a_class = []
            for allow_class in data.get('allowableClasses'):
                a_class.append(self.character_classes.get(allow_class))

            data['allowableClasses'] = a_class

        if 'allowableRaces' in data:
            al_ra = dict()
            allowable_races = []
            for allow_race in data.get('allowableRaces'):
                al_ra[allow_race] = allow_race
            for races in al_ra:
                allowable_races.append(self.character_races.get(races))

            data['allowableRaces'] = allowable_races

        file_name = "%s%s" % (self.scrubbed_directory, index_filepath)
        print("FILE: " + file_name)
        if not os.path.exists(os.path.dirname(file_name)):
            os.makedirs(os.path.dirname(file_name))
        with open(file_name, 'w') as f:
            json.dump(data, f)
            f.closed


    def __handleAvailableContexts(self, json_data, name):
        # availableContexts
        if 'name' not in json_data:
            if 'availableContexts' in json_data:
                for av_con in json_data.get('availableContexts'):
                    mod_index_filepath = re.sub(".json", "_" + av_con + ".json", name)
                    combined = str(json_data.get('id')) + "/" + av_con
                    self.__apiRequest(combined, mod_index_filepath, 0)
            else:
                raise ValueError("NO NAME AND NO AVAILABLECONTEXT: " + name)
        else:
            self.__scrubData(json_data, name)


    def __scantar(self):
        tar = tarfile.open(self.tarball_name)
        for member in tar.getmembers():
            print("MEMBER: " + member.name)
            if "json" in member.name:
                f=tar.extractfile(member)
                d = json.loads(f.read().decode("ascii"))

                self.__handleAvailableContexts(d, member.name)

        tar.close()



    def startScrub(self):
        if(self.use_tarball):
            self.__scantar()
        else:
            self.__scandirs(self.raw_directory)


wowApiConfig = WoWAPIConfigurator()

cleaner = WoWScrubber(wowApiConfig.blizzardAPIKey, wowApiConfig.rawJsonDir, wowApiConfig.applicationItemDir, wowApiConfig.runTarball, wowApiConfig.tarballFile)
cleaner.startScrub()
