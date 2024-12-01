package tcc.cypher.impl.dynamic;

import de.siegmar.fastcsv.reader.NamedCsvRecord;
import tcc.cypher.Cypher;

public class CypherPostHasCreatorPerson extends Cypher {

	@Override
	public String run(NamedCsvRecord rec) {
		
		// Post.id|Person.id
		
		String postId = rec.getField("Post.id");
		String personId = rec.getField("Person.id");
		
		String cypher = """
				SELECT * FROM
				cypher('agload_ldbc_graph',
				$$
				MATCH (a: Post), (b: Person)
				WHERE a.id = %s 
				  AND b.id = %s
				CREATE (a)-[e:HAS_CREATOR]->(b)
				RETURN e
				$$) as (e agtype);
				""";

		return String.format(cypher, postId, personId);
	}

}
