package tcc.cypher.impl.statik;

import de.siegmar.fastcsv.reader.NamedCsvRecord;
import tcc.cypher.Cypher;

public class CypherOrganisation extends Cypher {

	@Override
	public String run(NamedCsvRecord rec) {

		String type = upperCaseFirstLetter(rec.getField("type"));
		String id = rec.getField("id");
		String name = rec.getField("name");
		String url = rec.getField("url");

		/*
		 * A implementacao atual do Apache AGE não permite q um node (ou relacao) tenha mais do que um label :(
		 * 
		 * fonte:
		 * https://stackoverflow.com/questions/76350458/is-it-possible-for-a-node-to-have-multiple-labels-in-apache-age
		 */
		
		String cypher = """
				SELECT * FROM
				cypher('agload_ldbc_graph',
				$$
				CREATE (:%s {
					id: %s,
					name: "%s",
					url: "%s"})
				$$) AS (n agtype);
				""";

		return String.format(cypher, type, id, name, url);
	}
}
