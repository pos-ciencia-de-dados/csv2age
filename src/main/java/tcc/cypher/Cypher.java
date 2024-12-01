package tcc.cypher;

import java.util.Iterator;
import java.util.List;

import de.siegmar.fastcsv.reader.NamedCsvRecord;

public abstract class Cypher {

	public abstract String run(NamedCsvRecord rec);
	
	protected String toList(List<String> list) {

		StringBuilder resultado = new StringBuilder();

		for (Iterator<String> iter = list.iterator(); iter.hasNext();) {
			resultado.append("'" + iter.next() + "'");
			if (iter.hasNext())
				resultado.append(", ");
		}

		return resultado.toString();
	}
	
	protected String upperCaseFirstLetter(String input) {
		return input.substring(0, 1).toUpperCase() + input.substring(1);
	}

}
