FONTE: https://github.com/apache/age

-----------------------------------------------------------------

docker run \
    --name age  \
    -p 5455:5432 \
    -e POSTGRES_USER=postgresUser \
    -e POSTGRES_PASSWORD=postgresPW \
    -e POSTGRES_DB=postgresDB \
    -d \
    apache/age


docker exec -it age psql -d postgresDB -U postgresUser


LOAD 'age';


SET search_path = ag_catalog, "$user", public;


SELECT create_graph('graph_name');


SELECT * 
FROM cypher('graph_name', $$
    CREATE (:label {property:"Node A"})
$$) as (v agtype);



SELECT * 
FROM cypher('graph_name', $$
    CREATE (:label {property:"Node B"})
$$) as (v agtype);



SELECT * 
FROM cypher('graph_name', $$
    MATCH (a:label), (b:label)
    WHERE a.property = 'Node A' AND b.property = 'Node B'
    CREATE (a)-[e:RELTYPE {property:a.property + '<->' + b.property}]->(b)
    RETURN e
$$) as (e agtype);



SELECT * from cypher('graph_name', $$
        MATCH (V)-[R]-(V2)
        RETURN V,R,V2
$$) as (V agtype, R agtype, V2 agtype);



















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


