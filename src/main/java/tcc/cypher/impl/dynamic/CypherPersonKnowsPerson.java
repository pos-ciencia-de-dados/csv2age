package tcc.cypher.impl.dynamic;

import de.siegmar.fastcsv.reader.NamedCsvRecord;
import tcc.cypher.Cypher;

public class CypherPersonKnowsPerson extends Cypher {

	@Override
	public String run(NamedCsvRecord rec) {
		
		// Person.id|Person.id|creationDate
		
		String startPersonId = rec.getField(0);
		String endPersonId = rec.getField(1);
		String createDate = rec.getField(2);
		
		String cypher = """
				SELECT * FROM
				cypher('agload_ldbc_graph',
				$$
				MATCH (a: Person), (b: Person)
				WHERE a.id = %s 
				  AND b.id = %s
				CREATE (a)-[e:KNOWS {createDate: %s}]->(b)
				RETURN e
				$$) as (e agtype);
				""";

		return String.format(cypher, startPersonId, endPersonId, createDate);
	}

}
