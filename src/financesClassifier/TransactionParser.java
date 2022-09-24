package financesClassifier;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Takes a string representation of a transaction and parses it into a {@link Transaction} object. Delegates the responsibility of 
 * generating the transaction ID to {@link DescriptionParser}.
 * @author Carlos May Ferral
 *
 */
public class TransactionParser {

	private static final String DELIMITER = ",";
	private static final int DATE_POS = 0;
	private static final int DESCRIPTION_POS = 1;
	private static final int AMOUNT_POS = 2;
	
	private static final int YEAR_INDEX = 2;
	private static final int DAY_INDEX = 0;
	private static final int MONTH_INDEX = 1;
	
	private String[] monthAbbrevs;
	
	private DescriptionParser descriptionParser;
	
	public TransactionParser() {
		monthAbbrevs = new String[] {
				"Jan",
				"Feb",
				"Mar",
				"Apr",
				"May",
				"Jun",
				"Jul",
				"Aug",
				"Sep",
				"Oct",
				"Nov",
				"Dec"
		};
		descriptionParser = new DescriptionParser();
	}
	

	/**
	 * Takes a line representing a transaction (assumed comma delimited) and returns a 
	 * {@link Transaction}.
	 * @param line - The string representation of a transaction from a log file, comma delimited.
	 * @return - The {@link Transaction} corresponding to the string. Or null if the transaction is pending.
	 */
	public Transaction parseTransaction(String line) {
		
		//Parse into Delimited
		String[] delimitedLine = line.split(DELIMITER);
		
		//Basic parsing
		LocalDate date = parseDate(delimitedLine[DATE_POS]);
		String description = delimitedLine[DESCRIPTION_POS];
		Double amount = parseAmount(delimitedLine[AMOUNT_POS]);
		
		Transaction transaction = new Transaction(date,description,amount);
		
		//Parse ID from description
		String merchantDescriptor = descriptionParser.parseMerchant(description);
		
		//Return null if invalid.
		if(merchantDescriptor == null) {
			return null;
		}
		
		transaction.setMerchantId(merchantDescriptor);
		
		
		return transaction;
	}



	private Double parseAmount(String string) {
		return Double.parseDouble(string.replaceAll("\"", "").replaceAll(",", ""));
	}

	private LocalDate parseDate(String date) {
		date = date.strip();
		//divide by spaces
		String[] tokens = date.split(" ");
		int year = Integer.parseInt(tokens[YEAR_INDEX]);
		int day = Integer.parseInt(tokens[DAY_INDEX]);
		int month = 0;
		String monthAbbreviation = tokens[MONTH_INDEX];
		for(int i = 0; i < monthAbbrevs.length; i++) {
			if(monthAbbreviation.equals(monthAbbrevs[i])) {
				month = i+1;
				break;
			}
		}
		return LocalDate.of(year, month, day);
	}

	/**
	 * Returns the equivalent transactions from parsing a group of strings. Ignores pending transactions.
	 * @param readFile
	 * @return
	 */
	public ArrayList<Transaction> parseTransactions(List<String> transactionTextLines) {
		
		ArrayList<Transaction> result = new ArrayList<>();
		
		for(String line : transactionTextLines) {
			//only add to results if not null
			Transaction transaction = parseTransaction(line);
			if(transaction != null) {
				result.add(parseTransaction(line));
			}
		}
		return result;
	}

}
