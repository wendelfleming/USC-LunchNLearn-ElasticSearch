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

class WoWAPIConfigurator:

    def __init__(self):
        # Blizzard API Key - Freely available after logging in to dev.battle.net with a Mashery account
        # see https://dev.battle.net/ and http://www.mashery.com/ - used by downloader and scrubber
        self.blizzardAPIKey = ""

        # Location to save scrubbed JSON files - used by scrubber and indexer
        # self.applicationItemDir = "/java/projects/learn/lunchnlearn/USC-LunchNLearn-ElasticSearch/data/wow/items"
        # self.applicationItemDir = "/projects/lunchnlearn/data/wow/items"
        self.applicationItemDir = "/data/items"

        # Name of tar gzipped file contained all raw JSON files downloaded - used by scrubber
        # self.tarballFile = "/projects/lunchnlearn/data/wow/raw_items.tar.gz"
        self.tarballFile = "/data/raw_items.tar.gz"

        # Location of raw JSON files that is downloaded (or exploded from tarballFileName) - used by downloader and scrubber
        # NOTE: Do NOT make the same as applicationItemDir
        # self.rawJsonDir = "/java/projects/learn/lunchnlearn/USC-LunchNLearn-ElasticSearch/data/wow/raw_items"
        # self.rawJsonDir = "/projects/lunchnlearn/data/wow/raw_items"
        self.rawJsonDir = "/data/raw_items"

        # Should you read the tarball or the rawJsonDir?  True = tarballFileName value, False = rawJsonDir
        # My suggestion is to untar the tarball that comes from github and set this to fall after setting rawJsonDir to
        # the location you unpacked the file at - used by scrubber
        self.runTarball = False

        # Location of the mapping files.  This is used to set up the index. - used by indexer
        # self.mappingDir = "/projects/lunchnlearn/data/wow/mappings"
        self.mappingDir = "/data/mappings" 

        # ElasticSearch host - using Docker VMBox Host IP
        # self.esHost = "192.168.59.103"
        # self.esHost = "localhost"
        self.esHost = "es_server"

        # ElasticSearch ports
        self.esPort = "9200"
