package financesClassifier;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;


public class MappingsWriter {
	
	private static final String DELIMITED_HEADER = "Merchant ID, Category";
	private PrintWriter pw;
	
	

	/**
	 * 
	 * @param transactionMapping
	 * @param mappingsPath
	 */
	public void updateFile(HashMap<String, Category> transactionMapping, String mappingsPath) {
		File mappingsFile = new File(mappingsPath);
		mappingsFile.delete();
		
		try {
			pw = new PrintWriter(new File(mappingsPath));
			
			pw.println(DELIMITED_HEADER);
			
			for(String merchantID: transactionMapping.keySet()) {
				pw.println(merchantID +","+ transactionMapping.get(merchantID));
			}
			pw.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(-1);
		}
		
	}

}
