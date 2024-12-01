package tcc.cypher.impl.statik;

import de.siegmar.fastcsv.reader.NamedCsvRecord;
import tcc.cypher.Cypher;

public class CypherTagClass extends Cypher {

	@Override
	public String run(NamedCsvRecord rec) {

		// id|name|url
		
		String id = rec.getField("id");
		String name = rec.getField("name");
		String url = rec.getField("url");
		
		String cypher = """
				SELECT * FROM
				cypher('agload_ldbc_graph',
				$$
				CREATE (:TagClass {
					id: %s,
					name: "%s",
					url: "%s"})
				$$) AS (n agtype);
				""";

		return String.format(cypher, id, name, url);
	}

}
