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
		
		transaction.setMerchantId(merchantDescriptor);
		
		
		return transaction;
	}



	private Double parseAmount(String string) {
		return Double.parseDouble(string.replaceAll("\"", ""));
	}

	private LocalDate parseDate(String date) {
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
	 * Returns the equivalent transactions from parsing a group of strings.
	 * @param readFile
	 * @return
	 */
	public ArrayList<Transaction> parseTransactions(List<String> transactionTextLines) {
		
		ArrayList<Transaction> result = new ArrayList<>();
		
		//STUB
		Transaction transaction1 = new Transaction(LocalDate.of(1991, 10, 17),"",0.0);
		transaction1.setMerchantId("UBER *TRIP");
		result.add(transaction1);
		Transaction transaction2 = new Transaction(LocalDate.of(1991, 10, 17),"",0.0);
		transaction2.setMerchantId("CARTE CREPES");
		result.add(transaction2);
		Transaction transaction3 = new Transaction(LocalDate.of(1991, 10, 17),"",0.0);
		transaction3.setMerchantId("COMMERCIAL COIN LAUNDRET");
		result.add(transaction3);
		Transaction transaction4 = new Transaction(LocalDate.of(1991, 10, 17),"",0.0);
		transaction4.setMerchantId("TRANSFER - Samma Real Estat");
		result.add(transaction4);
		Transaction transaction5 = new Transaction(LocalDate.of(1991, 10, 17),"",0.0);
		transaction5.setMerchantId("GREEN REFECTORY");
		result.add(transaction5);
		
		
		return result;
		
	}

}
