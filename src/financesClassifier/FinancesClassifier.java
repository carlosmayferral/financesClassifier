package financesClassifier;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class FinancesClassifier {
	
	private static final String MAPPINGS_PATH = null;
	private ConsoleInterface consoleInterface;
	private TransactionReader transactionReader;
	private TransactionParser transactionParser;
	private MappingsReader mappingsReader;
	private TransactionWriter transactionWriter;
	private MappingsWriter mappingsWriter;
	
	
	public FinancesClassifier() {
		consoleInterface = new ConsoleInterface();
		transactionReader = new TransactionReader();
		transactionParser = new TransactionParser();
		mappingsReader = new MappingsReader();
		transactionWriter = new TransactionWriter();
		mappingsWriter = new MappingsWriter();
	}

	/**
	 * Contains the controller-level functionality of this program.
	 */
	public void run() {
		
		//Get path of transaction file from console interface
		String path = consoleInterface.promptForPath();
		
		//Read transactions from file as strings
		List<String> transactionText = transactionReader.readFile(path);
		
		//Parse transactions into transaction objects
		List<Transaction> transactions = transactionParser.parseTransactions(transactionText);
		
		//Read in mappings
		HashMap<String,Category> transactionMapping = mappingsReader.readMappings(MAPPINGS_PATH);
		
		//Start transaction classifier with mappings
		TransactionClassifier transactionClassifier = new TransactionClassifier(transactionMapping);
		
		//Start classification process
		transactionClassifier.classifyTransactions(transactions, consoleInterface);
		
		//Write out classified transactions
		transactionWriter.writeModifiedFile(transactions,path);
		
		//Write out mappings
		mappingsWriter.updateFile(transactionMapping, MAPPINGS_PATH);
		
		
		consoleInterface.close();
		
		
	}

	/**
	 * The main method for this program, simply calls run on the main class.
	 * @param args - Console arguments, not in use.
	 */
	public static void main(String[] args) {
		FinancesClassifier financesClassifier = new FinancesClassifier();
		financesClassifier.run();
	}

}
