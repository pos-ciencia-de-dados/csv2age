package tcc.cypher.impl.dynamic;

import de.siegmar.fastcsv.reader.NamedCsvRecord;
import tcc.cypher.Cypher;

public class CypherCommentReplyOfComment extends Cypher {

	@Override
	public String run(NamedCsvRecord rec) {
		
		// Comment.id|Comment.id
		
		String startCommentId = rec.getField(0);
		String endCommentId = rec.getField(1);
		
		String cypher = """
				SELECT * FROM
				cypher('agload_ldbc_graph',
				$$
				MATCH (a: Comment), (b: Comment)
				WHERE a.id = %s 
				  AND b.id = %s
				CREATE (a)-[e: REPLY_OF]->(b)
				RETURN e
				$$) as (e agtype);
				""";

		return String.format(cypher, startCommentId, endCommentId);
	}

}
