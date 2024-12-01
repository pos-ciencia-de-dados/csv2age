package tcc.cypher.impl.dynamic;

import de.siegmar.fastcsv.reader.NamedCsvRecord;
import tcc.cypher.Cypher;

public class CypherPersonHasInterestTag extends Cypher {

	@Override
	public String run(NamedCsvRecord rec) {
		
		// Person.id|Tag.id
		
		String personId = rec.getField("Person.id");
		String tagId = rec.getField("Tag.id");
		
		String cypher = """
				SELECT * FROM
				cypher('agload_ldbc_graph',
				$$
				MATCH (a: Person), (b: Tag)
				WHERE a.id = %s 
				  AND b.id = %s
				CREATE (a)-[e:HAS_INTEREST]->(b)
				RETURN e
				$$) as (e agtype);
				""";

		return String.format(cypher, personId, tagId);
	}

}
