FROM python:3.4
RUN mkdir /wowelastic
ENV PYTHONPATH=$PYTHONPATH:.:/wowelastic
ADD https://github.com/wendelfleming/USC-LunchNLearn-ElasticSearch/raw/master/Docker-dir/PythonContainer/requirements.txt /wowelastic/requirements.txt
ADD https://github.com/wendelfleming/USC-LunchNLearn-ElasticSearch/raw/master/WoWElastic/wowelastic/__init__.py /wowelastic/__init__.py
ADD https://github.com/wendelfleming/USC-LunchNLearn-ElasticSearch/raw/master/WoWElastic/wowelastic/configurator.py /wowelastic/configurator.py
ADD https://github.com/wendelfleming/USC-LunchNLearn-ElasticSearch/raw/master/WoWElastic/wowelastic/indexer.py /wowelastic/indexer.py
# WORKDIR /wowelastic
RUN pip install -r /wowelastic/requirements.txt
CMD python /wowelastic/indexer.py
