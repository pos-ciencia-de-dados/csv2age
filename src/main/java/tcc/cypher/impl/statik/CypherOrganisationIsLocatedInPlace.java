package tcc.cypher.impl.statik;

import de.siegmar.fastcsv.reader.NamedCsvRecord;
import tcc.cypher.Cypher;

public class CypherOrganisationIsLocatedInPlace extends Cypher {

	@Override
	public String run(NamedCsvRecord rec) {

		// Organisation.id|Place.id
		
		String organisationId = rec.getField("Organisation.id");
		String placeId = rec.getField("Place.id");
		
		String cypher = """
				SELECT * FROM
				cypher('agload_ldbc_graph',
				$$
				MATCH (a), (b)
				WHERE (label(a) = 'Company' or label(a) = 'University') 
				  AND (label(b) = 'City' or label(b) = 'Continent' or label(b) = 'Country') 
				  AND a.id = %s 
				  AND b.id = %s
				CREATE (a)-[e:IS_LOCATED_IN]->(b)
				RETURN e
				$$) as (e agtype);
				""";

		return String.format(cypher, organisationId, placeId);
	}

}
