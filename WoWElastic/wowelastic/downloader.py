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
import requests
import json
import logging
import os

from wowelastic.configurator import WoWAPIConfigurator


class WoWDownloader:
    def __init__(self, blizzKey, saveDir):
        self.blizzardAPIKey = blizzKey
        self.saveDirectory = saveDir
        if(not self.saveDirectory.endswith("/")):
            self.saveDirectory += "/"
        self.item_id_start = 9800
        self.item_id_end = 130000


    def download(self):
        api_url = "https://us.api.battle.net/wow/item/%d?locale=en_US&apikey=%s"

        logging.basicConfig(filename='wow_item_crawl.log',level=logging.DEBUG)

        try:
            for x in range(self.item_id_start, self.item_id_end):
                hash_code = hash(x)
                mask = 255
                first_dir = hash_code & mask
                second_dir = (hash_code >> 8) & mask
                # third_dir = (hash_code >> 16) & mask

                # file_name = "%s%d/%d/%d/%d.json" % (saveDirectory, first_dir, second_dir, third_dir, x)

                file_name = "%s%d/%d/%d.json" % (self.saveDirectory, first_dir, second_dir, x)

                item_url = api_url % (x, self.blizzardAPIKey)
                response = requests.get(item_url)

                if(response.status_code == 200):
                    if not os.path.exists(os.path.dirname(file_name)):
                        os.makedirs(os.path.dirname(file_name))
                    with open(file_name, 'w') as f:
                        json.dump(response.json(), f)
                        f.closed
                    logging.info('http_status = %d item = %d ', response.status_code, x)
                elif(response.status_code == 404):
                    logging.warning('http_status = %d item = %d ', response.status_code, x)
                else:
                    logging.error('http_status = %d item = %d ', response.status_code, x)
                    raise ValueError("HTTP Status Code is %d" % (response.status_code))
                print("We're on item %d - filename %s" % (x, file_name))
                # sleep(0.0005)
        except requests.ConnectionError:
            print("failed to connect")


wowApiConfig = WoWAPIConfigurator()
downloader = WoWDownloader(wowApiConfig.blizzardAPIKey, wowApiConfig.rawJsonDir)
downloader.download()
