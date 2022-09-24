package financesClassifier;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

public class TransactionWriter {
	
	private static final String DELIMITED_HEADER = "Transaction Date,Description,Amount,Category";
	
	PrintWriter pw;

	public void writeModifiedFile(List<Transaction> transactions, String path) {
		
		String newFileName = path.substring(0, path.indexOf(".csv")) + "categorized.csv";
		
		try {
			pw = new PrintWriter(new File(newFileName));
			
			pw.println(DELIMITED_HEADER);
			
			for(Transaction transaction: transactions) {
				pw.println(toCsv(transaction));
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(-1);
		}
		pw.close();
		
	}

	private String toCsv(Transaction transaction) {
		String date = transaction.getDate().toString();
		String description = transaction.getFullText();
		String amount = "" + transaction.getAmount();
		String category = transaction.getCategory().name();
		
		String[] tokens = new String[]{
			date,description,amount,category
		};
		
		return String.join(",", tokens);
	}

}
