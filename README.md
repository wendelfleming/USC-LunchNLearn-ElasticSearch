# USC-LunchNLearn-ElasticSearch
USC Lunch-n-Learn for ElasticSearch

Quick two week project to demonstrate ElasticSearch and Docker.  (NOTE: I am still learning both of these technologies, so this
  was a first attempt.  I would not consider these as best-practices.)

- WoWElastic - Python code that downloads, scrubs, and creates the ElasticSearch index.
       This uses the Blizzard API to download the World of Warcraft items.

- JavaWebApp - Java web application that uses the ElasticSearch index created by WoWElastic.

- Docker-dir - docker configuration for a data volume, Python3 indexer, and Java web application server (WildFly)

UPDATE:  Added AngularJS front end client

Another quick project to redo the Spring MVC front end client with AngularJS.  Again, this is a learning experience with
AngularJS so there is room for improvement.

Presentation
ElasticSearch
https://docs.google.com/presentation/d/1pwcXhqYBwzrpMfD1YT6zlVmyoYkUeTnPy_5Wx4DiMyM/edit?usp=sharing
AngularJS
https://docs.google.com/presentation/d/1NmyMcl1b_Mhcm74feqAskhu0d1JBUwvdVZ-85hxdFwQ/edit?usp=sharing


REQUIREMENTS
- Java 8
- Docker (unless you want to install and configure a whole lot of dependencies)


TODO:
- Add faceted search capabilities to the Java web app.
- Add item detail.  Might try pulling from the ElasticSearch source field to get the highlight functionality from ES.
- Make the Java project compile in a container so that you only need Docker.
- Get the official ElasticSearch container working.



Step-by-step instructions
- Clone this repository from github or download and unzip it
- Start up boot2docker (NOTE: pay attention to DOCKER_HOST IP setting that is displayed here)
- cd &lt; REPO &gt;/Docker-dir/DataContainer
- docker build -t docker_data .
- docker run --name docker_data docker_data
- docker run -d -p 9200:9200 -p 9300:9300 --name docker_elasticsearch -e CLUSTER=wowelastic itzg/elasticsearch
- cd ../PythonIndexerContainer/
- docker build -t docker_indexer .
- docker run --volumes-from=docker_data --link docker_elasticsearch:es_server docker_indexer
- cd ../../JavaWebApp/
- ./gradlew build -x test
- ./gradlew copyWarAndProp
- cd ../Docker-dir/JavaContainer/
- docker build --tag=wildfly-app .
- docker run -it -p 8080:8080 --volumes-from=docker_data --link docker_elasticsearch:es_server wildfly-app
- http://192.168.59.103:8080/elasticwow/  (NOTE: my DOCKER_HOST IP was 192.168.59.103, yours may be different)
- http://192.168.59.103:8080/elasticwow/angular/index.html  (NOTE: The AngularJS client)

Explanation of the above
- The DataContainer directory sets up the container that is the data volume for the other containers.  This
holds the tarballs and the Dockerfile configuration.  The tarballs get untarred into the /data directory when
the container is built.  The data directory then becomes available once the container is run.  We give the name
"docker_data" to this container so that we can mount it to other containers using --volumes-from.

- The ElasticSearch container:  I am using an unofficial ES container because it allows for easily setting the
ElasticSearch cluster name.  This setting is needed for the Java ElasticSearch configuration and it would be set
to some value automatically if not set here.  To control that, I am using itzg/elasticsearch.  This starts up
the container and then does port forwarding for ports 9200 and 9300 to the outside docker host.  It runs as
a daemon process with -d flag.  NOTE:  You can use "docker stop docker_elasticsearch" and "docker start docker_elasticsearch"
to not lose data between starts/stops of elasticsearch.  As long as the docker_elasticsearch container image shows in
the "docker ps -a" list.  If you delete the image (docker rm &lt;CONTAINERID&rt;), then the data is also deleted.  If
this was production, it would be better to put the ElasticSearch index data directory into a volume container.

- The PythonIndexerContainer - this is one of the 3 python programs that is needed to handle downloading, scrubbing,
and indexing.  I have provided the post scrubbed files in the DataContainer, so this is the only process I provided.  It
should be the case that all 3 processes have docker containers that run and do their thing.  The downloader and scrubber
need an environment variable, BLIZZARDAPIKEY, set.  This can be done in the Dockerfile with "ENV BLIZZARDAPIKEY=XXXX".
Connecting the downloader and scrubber with docker is left up to you.  This task is what indexes all of the scrubbed
World of Warcraft items into our ElasticSearch container.  We alias the ElasticSearch container within this one as
"es_server".  This allows us to refer to the ElasticSearch server as "es_server" in our connection settings.  This also
installed the Python modules that are required to run this script from the requirements.txt file.  Currently they are the
requests and elasticsearch modules.

- gradlew build - this builds the war file.  Java 8 is required.  The unit test will fail because it is configured to run
within the Docker container.  Currently I do not have a way of running the unit test easily because ElasticSearch has been
pushed into containers.

- gradlew copyWarAndProp - this copies the war file and the JavaWebApp/ext/wowelastic.properties files into
Docker-dir/JavaContainer.  This is because docker needs files at or below the directory that contains the Dockerfile
configuration and can not reference parent directories.  So these files must be copied into here before building the
Java WildFly container.

- WildFly - this is a light weight servlet container that can run your Java web application.  Again, you have to mount the
DataContainer.  You need to link the ElasticSearch container as well as provide the "es_server" alias that goes into the
wowelastic.properties file.  You also have to port map 8080 to the docker host.  To exit this container, hit CTRL-c.
