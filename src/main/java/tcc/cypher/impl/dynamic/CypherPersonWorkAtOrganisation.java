package tcc.cypher.impl.dynamic;

import de.siegmar.fastcsv.reader.NamedCsvRecord;
import tcc.cypher.Cypher;

public class CypherPersonWorkAtOrganisation extends Cypher {

	@Override
	public String run(NamedCsvRecord rec) {
		
		// Person.id|Organisation.id|workFrom
		
		String personId = rec.getField("Person.id");
		String organisationId = rec.getField("Organisation.id");
		String workFrom = rec.getField("workFrom");
		
		String cypher = """
				SELECT * FROM
				cypher('agload_ldbc_graph',
				$$
				MATCH (a: Person), (b: Company)
				WHERE a.id = %s 
				  AND b.id = %s
				CREATE (a)-[e:WORK_AT {workFrom: %s}]->(b)
				RETURN e
				$$) as (e agtype);
				""";

		return String.format(cypher, personId, organisationId, workFrom);
	}

}
