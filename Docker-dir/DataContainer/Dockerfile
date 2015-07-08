FROM ubuntu
RUN apt-get install -y wget
RUN mkdir /data

VOLUME /data

WORKDIR /data

#ADD https://github.com/wendelfleming/USC-LunchNLearn-ElasticSearch/raw/master/data/items.tar.gz /data/items.tar.gz
#ADD https://github.com/wendelfleming/USC-LunchNLearn-ElasticSearch/raw/master/data/mappings.tar.gz /data/mappings.tar.gz
# RUN curl -L --retry 20 --retry-delay 2 https://github.com/wendelfleming/USC-LunchNLearn-ElasticSearch/raw/master/data/items.tar.gz | tar zx -C /data/
# RUN curl -L --retry 20 --retry-delay 2 https://github.com/wendelfleming/USC-LunchNLearn-ElasticSearch/raw/master/data/mappings.tar.gz | tar zx -C /data/
# RUN tar xvfz /data/items.tar.gz -C /data/
# RUN tar xvfz /data/mappings.tar.gz -C /data/

#RUN wget https://github.com/wendelfleming/USC-LunchNLearn-ElasticSearch/raw/master/data/items.tar.gz -O /data/items.tar.gz
#RUN wget https://github.com/wendelfleming/USC-LunchNLearn-ElasticSearch/raw/master/data/mappings.tar.gz -O /data/mappings.tar.gz
#RUN ls /data/

#RUN tar xfz /data/mappings.tar.gz -C /data/
#RUN tar xfz /data/items.tar.gz -C /data/

#ADD ../../data/items.tar.gz /data/
#ADD ../../data/mappings.tar.gz /data/

ADD items.tar.gz /data/
ADD mappings.tar.gz /data/

