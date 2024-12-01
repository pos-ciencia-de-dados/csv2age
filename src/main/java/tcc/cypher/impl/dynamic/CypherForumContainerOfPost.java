package tcc.cypher.impl.dynamic;

import de.siegmar.fastcsv.reader.NamedCsvRecord;
import tcc.cypher.Cypher;

public class CypherForumContainerOfPost extends Cypher {

	@Override
	public String run(NamedCsvRecord rec) {
		
		// Forum.id|Post.id
		
		String forumId = rec.getField("Forum.id");
		String postId = rec.getField("Post.id");
		
		String cypher = """
				SELECT * FROM
				cypher('agload_ldbc_graph',
				$$
				MATCH (a: Forum), (b: Post)
				WHERE a.id = %s 
				  AND b.id = %s
				CREATE (a)-[e: CONTAINER_OF]->(b)
				RETURN e
				$$) as (e agtype);
				""";

		return String.format(cypher, forumId, postId);
	}

}
