# csv2age
A CSV to Apache AGE openCypher converter.


# Importar dataset LDBC

Cria banco no docker

```
docker run --rm \
    --name snb-interactive-apache-age-postgres  \
    --publish=5455:5432 \
    --volume=/home/s826169733/dev/workspace/tcc/tcc/src/main/resources/tcc_data:/data:z \
    -e POSTGRES_USER=postgresUser \
    -e POSTGRES_PASSWORD=postgresPW \
    -e POSTGRES_DB=postgresDB \
    -d \
    apache/age
```

Acessar container docker do banco

```
docker exec -it --user "$(id -u):$(id -g)" snb-interactive-apache-age-postgres bash
```

Acessar banco via psql

```
psql -d ldbcsnb -U postgres
```

Comandos do Apache AGE

```
LOAD 'age';

SET search_path TO ag_catalog;
SELECT create_graph('agload_ldbc_graph');

*** Não funcionou ****
SELECT create_vlabel('agload_ldbc_graph','Person');
SELECT load_labels_from_file('agload_test_graph', 'Person', '/data/person.txt');

SELECT * FROM 
cypher('agload_ldbc_graph', 
$$
CREATE (:Person {
	id: 933,
	firstName: 'Mahinda',
	lastName: 'Perera',
	gender: 'male',
	birthday: 628646400000,
	creationDate: 1266161530447,
	locationIP: '119.235.7.103',
	browserUsed: 'Firefox',
	language: ['si', 'en'],
	email: ['Mahinda933@boarderzone.com', 'Mahinda933@hotmail.com', 'Mahinda933@yahoo.com', 'Mahinda933@zoho.com']})
$$) AS (n agtype);
```

mkdir -p age-converted/{static,dynamic}/

###### STATIC

```bash
psql -U postgres -d ldbcsnb -f /data/static/organisation_0_0.csv.sql
psql -U postgres -d ldbcsnb -f /data/static/place_0_0.csv.sql
psql -U postgres -d ldbcsnb -f /data/static/organisation_isLocatedIn_place_0_0.csv
```

 
docker exec -it snb-interactive-apache-age-postgres psql -U postgres -d ldbcsnb -f /data/static/organisation_0_0.csv.sql

docker exec -it snb-interactive-apache-age-postgres psql -U postgres -d ldbcsnb -f /data/static/place_0_0.csv.sql

docker exec -it snb-interactive-apache-age-postgres psql -U postgres -d ldbcsnb -f /data/static/organisation_isLocatedIn_place_0_0.csv.sql

docker exec -it snb-interactive-apache-age-postgres psql -U postgres -d ldbcsnb -f /data/static/place_isPartOf_place_0_0.csv.sql

docker exec -it snb-interactive-apache-age-postgres psql -U postgres -d ldbcsnb -f /data/static/tag_0_0.csv.sql

docker exec -it snb-interactive-apache-age-postgres psql -U postgres -d ldbcsnb -f /data/static/tagclass_0_0.csv.sql

docker exec -it snb-interactive-apache-age-postgres psql -U postgres -d ldbcsnb -f /data/static/tag_hasType_tagclass_0_0.csv.sql

docker exec -it snb-interactive-apache-age-postgres psql -U postgres -d ldbcsnb -f /data/static/tagclass_isSubclassOf_tagclass_0_0.csv.sql

docker exec -it snb-interactive-apache-age-postgres psql -U postgres -d ldbcsnb -f /data/dynamic/person_0_0.csv.sql

docker exec -it snb-interactive-apache-age-postgres psql -U postgres -d ldbcsnb -f /data/dynamic/comment_0_0.csv.sql

docker exec -it snb-interactive-apache-age-postgres psql -U postgres -d ldbcsnb -f /data/dynamic/forum_0_0.csv.sql

docker exec -it snb-interactive-apache-age-postgres psql -U postgres -d ldbcsnb -f /data/dynamic/post_0_0.csv.sql

docker exec -it snb-interactive-apache-age-postgres psql -U postgres -d ldbcsnb -f /data/dynamic/person_hasInterest_tag_0_0.csv.sql

docker exec -it snb-interactive-apache-age-postgres psql -U postgres -d ldbcsnb -f /data/dynamic/person_isLocatedIn_place_0_0.csv.sql

docker exec -it snb-interactive-apache-age-postgres psql -U postgres -d ldbcsnb -f /data/dynamic/person_knows_person_0_0.csv.sql

docker exec -it snb-interactive-apache-age-postgres psql -U postgres -d ldbcsnb -f /data/dynamic/person_likes_comment_0_0.csv.sql

docker exec -it snb-interactive-apache-age-postgres psql -U postgres -d ldbcsnb -f /data/dynamic/person_likes_post_0_0.csv.sql

docker exec -it snb-interactive-apache-age-postgres psql -U postgres -d ldbcsnb -f /data/dynamic/person_studyAt_organisation_0_0.csv.sql

docker exec -it snb-interactive-apache-age-postgres psql -U postgres -d ldbcsnb -f /data/dynamic/person_workAt_organisation_0_0.csv.sql

docker exec -it snb-interactive-apache-age-postgres psql -U postgres -d ldbcsnb -f /data/dynamic/comment_hasCreator_person_0_0.csv.sql

docker exec -it snb-interactive-apache-age-postgres psql -U postgres -d ldbcsnb -f /data/dynamic/comment_hasTag_tag_0_0.csv.sql

docker exec -it snb-interactive-apache-age-postgres psql -U postgres -d ldbcsnb -f /data/dynamic/comment_isLocatedIn_place_0_0.csv.sql

docker exec -it snb-interactive-apache-age-postgres psql -U postgres -d ldbcsnb -f /data/dynamic/comment_replyOf_comment_0_0.csv.sql

docker exec -it snb-interactive-apache-age-postgres psql -U postgres -d ldbcsnb -f /data/dynamic/comment_replyOf_post_0_0.csv.sql

docker exec -it snb-interactive-apache-age-postgres psql -U postgres -d ldbcsnb -f /data/dynamic/forum_containerOf_post_0_0.csv.sql

docker exec -it snb-interactive-apache-age-postgres psql -U postgres -d ldbcsnb -f /data/dynamic/forum_hasMember_person_0_0.csv.sql

docker exec -it snb-interactive-apache-age-postgres psql -U postgres -d ldbcsnb -f /data/dynamic/forum_hasModerator_person_0_0.csv.sql

docker exec -it snb-interactive-apache-age-postgres psql -U postgres -d ldbcsnb -f /data/dynamic/forum_hasTag_tag_0_0.csv.sql

docker exec -it snb-interactive-apache-age-postgres psql -U postgres -d ldbcsnb -f /data/dynamic/post_hasCreator_person_0_0.csv.sql

docker exec -it snb-interactive-apache-age-postgres psql -U postgres -d ldbcsnb -f /data/dynamic/post_hasTag_tag_0_0.csv.sql

docker exec -it snb-interactive-apache-age-postgres psql -U postgres -d ldbcsnb -f /data/dynamic/post_isLocatedIn_place_0_0.csv.sql











