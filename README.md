# USC-LunchNLearn-ElasticSearch
USC Lunch-n-Learn for ElasticSearch

Initial commit of code.  It is currently scattered.

- WoWElastic - Python code that downloads, scrubs, and creates the ElasticSearch index.
       This uses the Blizzard API to download the World of Warcraft items.

- JavaWebApp - Java web application that uses the ElasticSearch index created by WoWElastic.

- data - holds the raw download and scrubbed json files (raw_items.tar.gz and items.tar.gz).
       Also holds the ElasticSearch mapping for all of the item types.  This is slightly modified
       mappings from the default as I have added suggest functionality based on Item Name.


TODO:
- Update this readme with step-by-step instructions on how to set all this up to run locally.
- Add faceted search capabilities to the Java web app.
- Add item detail.  Might try pulling from the ElasticSearch source field to get the highlight functionality from ES.
- Include the elasticsearch.yml configuration.  Currently the only change I have made from the
    default is to change the cluster.name to "wowelastic".  This will come into play on next TODO.
- Add 4 Docker containers to handle ElasticSearch, Java WebApp, Python, and a common data container.
