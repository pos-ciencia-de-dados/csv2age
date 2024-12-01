package tcc.cypher.impl.statik;

import de.siegmar.fastcsv.reader.NamedCsvRecord;
import tcc.cypher.Cypher;

public class CypherTagclassIsSubclassOfTagclass extends Cypher {

	@Override
	public String run(NamedCsvRecord rec) {
		
		// TagClass.id|TagClass.id
		
		String startTagClassId = rec.getField(0);
		String endTagClassId = rec.getField(1);
		
		String cypher = """
				SELECT * FROM
				cypher('agload_ldbc_graph',
				$$
				MATCH (a: TagClass), (b: TagClass)
				WHERE a.id = %s 
				  AND b.id = %s
				CREATE (a)-[e:IS_SUBCLASS_OF]->(b)
				RETURN e
				$$) as (e agtype);
				""";

		return String.format(cypher, startTagClassId, endTagClassId);
	}

}
