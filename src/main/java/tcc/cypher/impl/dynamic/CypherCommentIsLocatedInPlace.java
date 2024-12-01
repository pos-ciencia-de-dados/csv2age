package tcc.cypher.impl.dynamic;

import de.siegmar.fastcsv.reader.NamedCsvRecord;
import tcc.cypher.Cypher;

public class CypherCommentIsLocatedInPlace extends Cypher {

	@Override
	public String run(NamedCsvRecord rec) {
		
		// Comment.id|Place.id
		
		String commentId = rec.getField("Comment.id");
		String placeId = rec.getField("Place.id");
		
		String cypher = """
				SELECT * FROM
				cypher('agload_ldbc_graph',
				$$
				MATCH (a: Comment), (b)
				WHERE (label(b) = 'City' or label(b) = 'Continent' or label(b) = 'Country') 
				  AND a.id = %s 
				  AND b.id = %s
				CREATE (a)-[e:IS_LOCATED_IN]->(b)
				RETURN e
				$$) as (e agtype);
				""";

		return String.format(cypher, commentId, placeId);
	}

}
