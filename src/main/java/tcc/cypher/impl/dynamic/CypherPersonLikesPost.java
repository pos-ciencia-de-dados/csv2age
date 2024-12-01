package tcc.cypher.impl.dynamic;

import de.siegmar.fastcsv.reader.NamedCsvRecord;
import tcc.cypher.Cypher;

public class CypherPersonLikesPost extends Cypher {

	@Override
	public String run(NamedCsvRecord rec) {
		
		// Person.id|Post.id|creationDate
		
		String personId = rec.getField("Person.id");
		String postId = rec.getField("Post.id");
		String createDate = rec.getField("creationDate");
		
		String cypher = """
				SELECT * FROM
				cypher('agload_ldbc_graph',
				$$
				MATCH (a: Person), (b: Post)
				WHERE a.id = %s 
				  AND b.id = %s
				CREATE (a)-[e:LIKES {createDate: %s}]->(b)
				RETURN e
				$$) as (e agtype);
				""";

		return String.format(cypher, personId, postId, createDate);
	}

}
