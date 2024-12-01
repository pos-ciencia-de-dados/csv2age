FONTE: https://age.apache.org/age-manual/master/intro/agload.html

-----------------------------------------------------------------------

docker run --rm \
    --name age  \
    --publish=5455:5432 \
    --volume=/home/s826169733/dev/git/github/age/regress/age_load/data:/data:z \
    -e POSTGRES_USER=postgresUser \
    -e POSTGRES_PASSWORD=postgresPW \
    -e POSTGRES_DB=postgresDB \
    -d \
    apache/age

docker exec -it age bash

psql -d postgresDB -U postgresUser

LOAD 'age';

SET search_path TO ag_catalog;
SELECT create_graph('agload_test_graph');


SELECT create_vlabel('agload_test_graph','Country');
SELECT load_labels_from_file('agload_test_graph', 'Country', '/age/regress/age_load/data/countries.csv');

SELECT create_vlabel('agload_test_graph','City');
SELECT load_labels_from_file('agload_test_graph','City', '/age/regress/age_load/data/cities.csv');


SELECT create_elabel('agload_test_graph','has_city');
SELECT load_edges_from_file('agload_test_graph', 'has_city','/age/regress/age_load/data/edges.csv');

SELECT table_catalog, table_schema, table_name, table_type
FROM information_schema.tables
WHERE table_schema = 'agload_test_graph';

SELECT COUNT(*) FROM agload_test_graph."Country";
SELECT COUNT(*) FROM agload_test_graph."City";
SELECT COUNT(*) FROM agload_test_graph."has_city";

SELECT COUNT(*) FROM cypher('agload_test_graph', $$MATCH(n) RETURN n$$) as (n agtype);
SELECT COUNT(*) FROM cypher('agload_test_graph', $$MATCH (a)-[e]->(b) RETURN e$$) as (n agtype);
