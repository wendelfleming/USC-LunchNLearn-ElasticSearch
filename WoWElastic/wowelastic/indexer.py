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

import os, glob
from elasticsearch import Elasticsearch
from elasticsearch.client import IndicesClient
import json

from wowelastic.configurator import WoWAPIConfigurator

class WoWIndexer:
    def __init__(self, dir, map_dir):
        self.base_directory = dir
        self.map_directory = map_dir
        if(not self.map_directory.endswith("/")):
            self.map_directory += "/"


    def runIndexer(self):
        self.__createIndex()
        self.__indexDirectory(self.base_directory)


    def __createIndex(self):
        es = Elasticsearch([{'host': 'localhost', 'port': 9200}])
        ic = IndicesClient(es)
        if(ic.exists(index='wow')):
            self.deleteIndex()
        ic.create(index='wow')
        blah = glob.glob(os.path.join(self.map_directory, '*'))
        for currentFile in glob.glob(os.path.join(self.map_directory, '*')):
            print("MAP FILE: " + currentFile)
            self.__mapFile(currentFile)


    def __mapFile(self, json_map_file):
        es = Elasticsearch([{'host': 'localhost', 'port': 9200}])
        ic = IndicesClient(es)
        with open(json_map_file) as json_data:
            d = json.load(json_data)
            doc_type = list(d.keys())[0]
            ic.put_mapping(index='wow', doc_type=doc_type, body=d)


    def __indexDirectory(self, path):
        for currentFile in glob.glob( os.path.join(path, '*') ):
            if os.path.isdir(currentFile):
                self.__indexDirectory(currentFile)
            else:
                print("FILE: " + currentFile)
                self.__indexFile(currentFile)


    def __indexFile(self, json_file):
        es = Elasticsearch([{'host': 'localhost', 'port': 9200}])
        with open(json_file) as json_data:
            d = json.load(json_data)
            docType = d.get('itemClass')
            objectId = d.get('indexPath')
            es.index(index='wow', doc_type=docType, id=objectId, body=d)



    def deleteIndex(self):
        es = Elasticsearch([{'host': 'localhost', 'port': 9200}])
        es.indices.delete(index='wow', ignore=[400, 404])


    def searchTest(self):
        es = Elasticsearch([{'host': 'localhost', 'port': 9200}])
        d = es.search(index="wow", body={"query": {"prefix" : { "name" : "a" }}})
        x = 1
        print(x)



wowApiConfig = WoWAPIConfigurator()
indexer = WoWIndexer(wowApiConfig.applicationItemDir, wowApiConfig.mappingDir)
indexer.runIndexer()
# indexer.searchTest()
# indexer.deleteIndex()

