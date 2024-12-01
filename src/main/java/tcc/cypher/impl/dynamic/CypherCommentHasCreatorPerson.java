package tcc.cypher.impl.dynamic;

import de.siegmar.fastcsv.reader.NamedCsvRecord;
import tcc.cypher.Cypher;

public class CypherCommentHasCreatorPerson extends Cypher {

	@Override
	public String run(NamedCsvRecord rec) {
		
		// Comment.id|Person.id
		
		String commentId = rec.getField("Comment.id");
		String personId = rec.getField("Person.id");
		
		String cypher = """
				SELECT * FROM
				cypher('agload_ldbc_graph',
				$$
				MATCH (a: Comment), (b: Person)
				WHERE a.id = %s 
				  AND b.id = %s
				CREATE (a)-[e:HAS_CREATOR]->(b)
				RETURN e
				$$) as (e agtype);
				""";

		return String.format(cypher, commentId, personId);
	}

}
