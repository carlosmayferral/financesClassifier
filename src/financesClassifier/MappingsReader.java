package financesClassifier;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/**
 * Reads a csv file containing mappings from merchant ID to categories.
 * @see Category
 * @author Carlos May Ferral
 *
 */
public class MappingsReader {
	
	private static final String DELIMITER = ",";
	
	private BufferedReader br;

	/**
	 * Reads a file from the specified path as a csv. Assumes csv will have two columns, the first
	 * containing merchant IDs and the second their mapped categories. The header is skipped.
	 * @param mappingsPath - The path where the mappings file is expected to be found. If it 
	 * doesn't exist, this method returns an empty hashmap.
	 * @return - A hashmap mapping merchant Ids to their known categories.
	 */
	public HashMap<String, Category> readMappings(String mappingsPath) {
		
		//Init hashmap
		HashMap<String, Category> merchantMappings = new HashMap<>();
		
		//Try to read, if available
		try {
			br = new BufferedReader(new FileReader(new File(mappingsPath)));
			
			//skip header
			br.readLine();
			
			//Read lines into List while file is not empty
			String currentLine = null;
			while((currentLine = br.readLine())!= null) {
				String[] columns = currentLine.split(DELIMITER);
				merchantMappings.put(columns[0], Category.valueOf(columns[1]));
			}
			br.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("Category mappings file not found. Starting from scratch");
		} catch (IOException e) {
			System.out.println("Error reading from mappings file");
			e.printStackTrace();
			System.exit(-1);
		}

		
		return merchantMappings;
	}

}
