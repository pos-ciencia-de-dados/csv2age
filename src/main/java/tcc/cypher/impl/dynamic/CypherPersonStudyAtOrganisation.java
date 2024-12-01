package tcc.cypher.impl.dynamic;

import de.siegmar.fastcsv.reader.NamedCsvRecord;
import tcc.cypher.Cypher;

public class CypherPersonStudyAtOrganisation extends Cypher {

	@Override
	public String run(NamedCsvRecord rec) {
		
		// Person.id|Organisation.id|classYear
		
		String personId = rec.getField("Person.id");
		String organisationId = rec.getField("Organisation.id");
		String classYear = rec.getField("classYear");
		
		String cypher = """
				SELECT * FROM
				cypher('agload_ldbc_graph',
				$$
				MATCH (a: Person), (b: University)
				WHERE a.id = %s 
				  AND b.id = %s
				CREATE (a)-[e:STUDY_AT {classYear: %s}]->(b)
				RETURN e
				$$) as (e agtype);
				""";

		return String.format(cypher, personId, organisationId, classYear);
	}

}
