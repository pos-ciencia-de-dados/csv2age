package tcc.cypher.impl.dynamic;

import de.siegmar.fastcsv.reader.NamedCsvRecord;
import tcc.cypher.Cypher;

public class CypherPersonLikesComment extends Cypher {

	@Override
	public String run(NamedCsvRecord rec) {
		
		// Person.id|Comment.id|creationDate
		
		String personId = rec.getField("Person.id");
		String commentId = rec.getField("Comment.id");
		String createDate = rec.getField("creationDate");
		
		String cypher = """
				SELECT * FROM
				cypher('agload_ldbc_graph',
				$$
				MATCH (a: Person), (b: Comment)
				WHERE a.id = %s 
				  AND b.id = %s
				CREATE (a)-[e:LIKES {createDate: %s}]->(b)
				RETURN e
				$$) as (e agtype);
				""";

		return String.format(cypher, personId, commentId, createDate);
	}

}
