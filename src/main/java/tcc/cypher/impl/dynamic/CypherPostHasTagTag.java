package tcc.cypher.impl.dynamic;

import de.siegmar.fastcsv.reader.NamedCsvRecord;
import tcc.cypher.Cypher;

public class CypherPostHasTagTag extends Cypher {

	@Override
	public String run(NamedCsvRecord rec) {
		
		// Post.id|Tag.id
		
		String postId = rec.getField("Post.id");
		String tagId = rec.getField("Tag.id");
		
		String cypher = """
				SELECT * FROM
				cypher('agload_ldbc_graph',
				$$
				MATCH (a: Post), (b: Tag)
				WHERE a.id = %s 
				  AND b.id = %s
				CREATE (a)-[e:HAS_TAG]->(b)
				RETURN e
				$$) as (e agtype);
				""";

		return String.format(cypher, postId, tagId);
	}

}
