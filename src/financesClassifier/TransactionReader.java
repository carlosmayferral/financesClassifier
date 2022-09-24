package financesClassifier;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Reads transaction logs as csv files into strings.
 * @author Carlos May Ferral
 *
 */
public class TransactionReader {
	
	private BufferedReader br;

	/**
	 * Read the each line of the transaction log file found at the specified path into a 
	 * list of strings that excludes the header.
	 * @param path - The path of the transaction log to be processed.
	 * @return - A list of the lines of the file minus the header.
	 */
	public List<String> readFile(String path) {
		List<String> lines = new ArrayList<>();
		
		try {
			br = new BufferedReader(new FileReader(new File(path)));
			//Skip header.
			br.readLine();
			
			//Read lines into List while file is not empty
			String currentLine = null;
			while((currentLine = br.readLine())!= null) {
				lines.add(currentLine);
			}
			br.close();
			
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		
		return lines;
	}

}
