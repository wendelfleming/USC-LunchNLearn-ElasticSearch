FROM python:3.4
RUN mkdir /wowelastic
ADD https://github.com/wendelfleming/USC-LunchNLearn-ElasticSearch/raw/master/WoWElastic/wowelastic/__init__.py /wowelastic/__init__.py
ADD https://github.com/wendelfleming/USC-LunchNLearn-ElasticSearch/raw/master/WoWElastic/wowelastic/configurator.py /wowelastic/configurator.py
ADD https://github.com/wendelfleming/USC-LunchNLearn-ElasticSearch/raw/master/WoWElastic/wowelastic/indexer.py /wowelastic/indexer.py
ADD wowelastic /code
WORKDIR /code
RUN pip install -r requirements.txt
CMD python indexer.py
