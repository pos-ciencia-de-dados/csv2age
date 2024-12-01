package tcc.cypher.impl.dynamic;

import de.siegmar.fastcsv.reader.NamedCsvRecord;
import tcc.cypher.Cypher;

public class CypherPost extends Cypher {

	@Override
	public String run(NamedCsvRecord rec) {
		
		// id|imageFile|creationDate|locationIP|browserUsed|language|content|length
		
		String id = rec.getField("id");
		String imageFile = rec.getField("imageFile");
		String creationDate = rec.getField("creationDate");
		String locationIP = rec.getField("locationIP");
		String browserUsed = rec.getField("browserUsed");
		String language = rec.getField("language");
		String content = rec.getField("content");
		String length = rec.getField("length");
		
		String cypher = """
				SELECT * FROM
				cypher('agload_ldbc_graph',
				$$
				CREATE (:Post {
					id: %s,
					imageFile: "%s",
					creationDate: %s,
					locationIP: "%s",
					browserUsed: "%s",
					language: "%s",
					content: "%s",
					length: %s})
				$$) AS (n agtype);
				""";

		return String.format(cypher, id, imageFile, creationDate, locationIP, browserUsed, language, content, length);
	}

}
