package tcc.cypher.impl.dynamic;

import de.siegmar.fastcsv.reader.NamedCsvRecord;
import tcc.cypher.Cypher;

public class CypherForum extends Cypher {

	@Override
	public String run(NamedCsvRecord rec) {
		
		// id|title|creationDate
		
		String id = rec.getField("id");
		String title = rec.getField("title");
		String creationDate = rec.getField("creationDate");
		
		String cypher = """
				SELECT * FROM
				cypher('agload_ldbc_graph',
				$$
				CREATE (:Forum {
					id: %s,
					title: "%s",
					creationDate: %s})
				$$) AS (n agtype);
				""";

		return String.format(cypher, id, title, creationDate);
	}

}
