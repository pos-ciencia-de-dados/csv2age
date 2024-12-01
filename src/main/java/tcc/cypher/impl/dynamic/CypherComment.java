package tcc.cypher.impl.dynamic;

import de.siegmar.fastcsv.reader.NamedCsvRecord;
import tcc.cypher.Cypher;

public class CypherComment extends Cypher {

	@Override
	public String run(NamedCsvRecord rec) {

		// id|creationDate|locationIP|browserUsed|content|length
		
		String id = rec.getField("id");
		String creationDate = rec.getField("creationDate");
		String locationIP = rec.getField("locationIP");
		String browserUsed = rec.getField("browserUsed");
		String content = removerCaracteresEspeciais(rec.getField("content"));
		String length = rec.getField("length");

		String cypher = """
				SELECT * FROM
				cypher('agload_ldbc_graph',
				$$
				CREATE (:Comment {
					id: %s,
					creationDate: %s,
					locationIP: "%s",
					browserUsed: "%s",
					content: "%s",
					length: %s})
				$$) AS (n agtype);
				""";

		return String.format(cypher, id, creationDate, locationIP, browserUsed, content, length);
	}

	private String removerCaracteresEspeciais(String texto) {
		
		String resultado = texto.replace(";", ",");
		
		resultado = resultado.replace("$$", "SS");
		
		return resultado;
	}

}
