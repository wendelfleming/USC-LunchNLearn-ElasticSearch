FROM python:3.4
RUN mkdir /wowelastic
ENV PYTHONPATH=$PYTHONPATH:.:/wowelastic
ADD https://github.com/wendelfleming/USC-LunchNLearn-ElasticSearch/raw/master/Docker-dir/PythonContainer/requirements.txt /wowelastic/requirements.txt
ADD https://github.com/wendelfleming/USC-LunchNLearn-ElasticSearch/raw/master/WoWElastic/wowelastic/__init__.py /wowelastic/__init__.py
ADD https://github.com/wendelfleming/USC-LunchNLearn-ElasticSearch/raw/master/WoWElastic/wowelastic/configurator.py /wowelastic/configurator.py
ADD https://github.com/wendelfleming/USC-LunchNLearn-ElasticSearch/raw/master/WoWElastic/wowelastic/indexer.py /wowelastic/indexer.py

# VOLUME /data
# ADD https://github.com/wendelfleming/USC-LunchNLearn-ElasticSearch/raw/master/data/items.tar.gz /data/items.tar.gz
# ADD https://github.com/wendelfleming/USC-LunchNLearn-ElasticSearch/raw/master/data/mappings.tar.gz /data/mappings.tar.gz
# RUN tar xvfz /data/mappings.tar.gz -C /data/
# RUN tar xvfz /data/items.tar.gz -C /data/

# WORKDIR /wowelastic
RUN pip install -r /wowelastic/requirements.txt
CMD ["python", "/wowelastic/indexer.py"]
