package tcc.cypher.impl.dynamic;

import de.siegmar.fastcsv.reader.NamedCsvRecord;
import tcc.cypher.Cypher;

public class CypherForumHasModeratorPerson extends Cypher {

	@Override
	public String run(NamedCsvRecord rec) {
		
		// Forum.id|Person.id
		
		String forumId = rec.getField("Forum.id");
		String personId = rec.getField("Person.id");
		
		String cypher = """
				SELECT * FROM
				cypher('agload_ldbc_graph',
				$$
				MATCH (a: Forum), (b: Person)
				WHERE a.id = %s 
				  AND b.id = %s
				CREATE (a)-[e: HAS_MODERATOR]->(b)
				RETURN e
				$$) as (e agtype);
				""";

		return String.format(cypher, forumId, personId);
	}

}
