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
		
		System.out.println("Classifying transaction: " + transaction.getIdText());
		if(transactionMapping.containsKey(transaction.getIdText())) {
			transaction.setCategory(transactionMapping.get(transaction.getIdText()));
		}
		else {
			transaction.setCategory(consoleInterface.promptForCategory(transaction.getIdText()));
			transactionMapping.put(transaction.getIdText(),transaction.getCategory());
		}
		System.out.println("Assigned to: " + transaction.getCategory().toString());
		
	}

}
