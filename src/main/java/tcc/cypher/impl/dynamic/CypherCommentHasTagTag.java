package tcc.cypher.impl.dynamic;

import de.siegmar.fastcsv.reader.NamedCsvRecord;
import tcc.cypher.Cypher;

public class CypherCommentHasTagTag extends Cypher {

	@Override
	public String run(NamedCsvRecord rec) {
		
		// Comment.id|Tag.id
		
		String commentId = rec.getField("Comment.id");
		String tagId = rec.getField("Tag.id");
		
		String cypher = """
				SELECT * FROM
				cypher('agload_ldbc_graph',
				$$
				MATCH (a: Comment), (b: Tag)
				WHERE a.id = %s 
				  AND b.id = %s
				CREATE (a)-[e:HAS_TAG]->(b)
				RETURN e
				$$) as (e agtype);
				""";

		return String.format(cypher, commentId, tagId);
	}

}
