package financesClassifier;

import java.util.HashMap;
import java.util.List;

public class TransactionClassifier {
	
	private HashMap<String,Category> transactionMapping;

	public TransactionClassifier(HashMap<String, Category> transactionMapping) {
		this.transactionMapping = transactionMapping;
	}

	public void classifyTransactions(List<Transaction> transactions, ConsoleInterface consoleInterface) {
		for(Transaction transaction : transactions) {
			classifyTransaction(transaction, consoleInterface);
		}
	}

	public void classifyTransaction(Transaction transaction, ConsoleInterface consoleInterface) {
		
		
		
		System.out.println("Classifying transaction: " + transaction.getMerchantId());
		if(transactionMapping.containsKey(transaction.getMerchantId())) {
			transaction.setCategory(transactionMapping.get(transaction.getMerchantId()));
		}
		else {
			transaction.setCategory(consoleInterface.promptForCategory(transaction.getMerchantId()));
			//Only remember category if not a transfer
			if(!transaction.getMerchantId().contains("TRANSFER")) {
				transactionMapping.put(transaction.getMerchantId(),transaction.getCategory());
			}
		}
		System.out.println("Assigned to: " + transaction.getCategory().toString());
		
	}

}
