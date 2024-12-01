package tcc;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import de.siegmar.fastcsv.reader.CsvReader;
import de.siegmar.fastcsv.reader.NamedCsvRecord;
import tcc.cypher.Cypher;
import tcc.cypher.CypherFactory;

public class Main {

	public static void main(String[] args) throws IOException, URISyntaxException {

		String vanillaDir = args[0];
		String convertedDir = args[1];

		Set<String> ls = listFiles(vanillaDir);

		// Criar diretorio de output
		String outputDir = createOutputDiretory(convertedDir);

		
		for (String f : ls) {
			
			String dir = new java.io.File(f).getParentFile().getName();
			String csv = new java.io.File(f).getName();
			
			Path file = Paths.get(vanillaDir + File.separator + dir + File.separator + csv);
			try (CsvReader<NamedCsvRecord> csvReader = CsvReader.builder().fieldSeparator('|').ofNamedCsvRecord(file)) {
				
				String subDir = createOutputDiretory(outputDir + File.separator + dir);
				
				String sqlFile = subDir + File.separator + csv + ".sql";
				
				FileWriter escritor = new FileWriter(sqlFile);

				escritor.write("LOAD 'age';");
				escritor.write("SET search_path TO ag_catalog;");
				
				escritor.write("BEGIN TRANSACTION;");

				csvReader.forEach(rec -> {
					try {
						Cypher cypher = CypherFactory.create(csv);
						if (cypher != null) escritor.write(cypher.run(rec));
					} catch (IOException e) {
						System.out.println("Erro ao escrever no arquivo: " + e.getMessage());
					}
				});

				escritor.write("COMMIT;");
				
				escritor.close();
				System.out.println("Texto escrito com sucesso no arquivo " + sqlFile);
			}
		}
	}
	
	private static Set<String> listFiles(String dir) throws IOException {
		try (Stream<Path> stream = Files.walk(Paths.get(dir), 2)) {
			return stream
					.filter(file -> !Files.isDirectory(file))
					.map(path -> {return path.getParent().toString() + "/" + path.getFileName().toString();})
					.collect(Collectors.toSet());
		}
	}
	
	private static String createOutputDiretory(String convertedDir) throws IOException, URISyntaxException {

		Path convertedPath = Path.of(convertedDir);

		if (!Files.exists(convertedPath)) {
			Files.createDirectories(convertedPath);
		}

		System.out.println("Directory created successfully: " + convertedDir);
		return convertedDir.toString();
	}
}
