package tcc.cypher.impl.dynamic;

import de.siegmar.fastcsv.reader.NamedCsvRecord;
import tcc.cypher.Cypher;

public class CypherPostIsLocatedInPlace extends Cypher {

	@Override
	public String run(NamedCsvRecord rec) {
		
		// Post.id|Place.id
		
		String postId = rec.getField("Post.id");
		String placeId = rec.getField("Place.id");
		
		String cypher = """
				SELECT * FROM
				cypher('agload_ldbc_graph',
				$$
				MATCH (a: Post), (b)
				WHERE (label(b) = 'City' or label(b) = 'Continent' or label(b) = 'Country') 
				  AND a.id = %s 
				  AND b.id = %s
				CREATE (a)-[e:IS_LOCATED_IN]->(b)
				RETURN e
				$$) as (e agtype);
				""";

		return String.format(cypher, postId, placeId);
	}

}
