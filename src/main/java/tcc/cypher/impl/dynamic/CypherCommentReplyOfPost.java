package tcc.cypher.impl.dynamic;

import de.siegmar.fastcsv.reader.NamedCsvRecord;
import tcc.cypher.Cypher;

public class CypherCommentReplyOfPost extends Cypher {

	@Override
	public String run(NamedCsvRecord rec) {
		
		// Comment.id|Post.id
		
		String commentId = rec.getField("Comment.id");
		String postId = rec.getField("Post.id");
		
		String cypher = """
				SELECT * FROM
				cypher('agload_ldbc_graph',
				$$
				MATCH (a: Comment), (b: Post)
				WHERE a.id = %s 
				  AND b.id = %s
				CREATE (a)-[e: REPLY_OF]->(b)
				RETURN e
				$$) as (e agtype);
				""";

		return String.format(cypher, commentId, postId);
	}

}
