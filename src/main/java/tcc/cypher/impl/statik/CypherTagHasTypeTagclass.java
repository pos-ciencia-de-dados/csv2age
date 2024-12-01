package tcc.cypher.impl.statik;

import de.siegmar.fastcsv.reader.NamedCsvRecord;
import tcc.cypher.Cypher;

public class CypherTagHasTypeTagclass extends Cypher {

	@Override
	public String run(NamedCsvRecord rec) {

		// Tag.id|TagClass.id
		
		String tagId = rec.getField("Tag.id");
		String tagClassId = rec.getField("TagClass.id");
		
		String cypher = """
				SELECT * FROM
				cypher('agload_ldbc_graph',
				$$
				MATCH (a: Tag), (b: TagClass)
				WHERE a.id = %s 
				  AND b.id = %s
				CREATE (a)-[e:HAS_TYPE]->(b)
				RETURN e
				$$) as (e agtype);
				""";

		return String.format(cypher, tagId, tagClassId);
	}

}
