package tcc.cypher.impl.dynamic;

import java.util.List;

import de.siegmar.fastcsv.reader.NamedCsvRecord;
import tcc.cypher.Cypher;

public class CypherPerson extends Cypher {

	/*
	 * create_cypher_person
	 */
	@Override
	public String run(NamedCsvRecord rec) {

		String id = rec.getField("id");
		String firstName = rec.getField("firstName");
		String lastName = rec.getField("lastName");
		String gender = rec.getField("gender");
		String birthday = rec.getField("birthday");
		String creationDate = rec.getField("creationDate");
		String locationIP = rec.getField("locationIP");
		String browserUsed = rec.getField("browserUsed");
		String language = toList(List.of(rec.getField("language").split(";")));
		String email = toList(List.of(rec.getField("email").split(";")));

		String cypher = """
				SELECT * FROM
				cypher('agload_ldbc_graph',
				$$
				CREATE (:Person {
					id: %s,
					firstName: "%s",
					lastName: "%s",
					gender: "%s",
					birthday: %s,
					creationDate: %s,
					locationIP: "%s",
					browserUsed: "%s",
					language: [%s],
					email: [%s]})
				$$) AS (n agtype);
				""";

		return String.format(cypher, id, firstName, lastName, gender, birthday, creationDate, locationIP, browserUsed, language, email);
	}
	
	
}
