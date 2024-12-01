package tcc.cypher.impl.statik;

import de.siegmar.fastcsv.reader.NamedCsvRecord;
import tcc.cypher.Cypher;

public class CypherPlaceIsPartOfPlace extends Cypher {

	@Override
	public String run(NamedCsvRecord rec) {

		// Place.id|Place.id
		
		String startPlaceId = rec.getField(0);
		String endPlaceId = rec.getField(1);
		
		String cypher = """
				SELECT * FROM
				cypher('agload_ldbc_graph',
				$$
				MATCH (a), (b)
				WHERE (label(a) = 'City' or label(a) = 'Continent' or label(a) = 'Country') 
				  AND (label(b) = 'City' or label(b) = 'Continent' or label(b) = 'Country')
				  AND a.id = %s 
				  AND b.id = %s
				CREATE (a)-[e:IS_PART_OF]->(b)
				RETURN e
				$$) as (e agtype);
				""";

		return String.format(cypher, startPlaceId, endPlaceId);
	}

}
