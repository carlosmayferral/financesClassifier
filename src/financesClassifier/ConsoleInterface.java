package financesClassifier;

import java.io.PrintStream;
import java.util.Scanner;

public class ConsoleInterface {
	
	private Scanner scanner;
	PrintStream printStream;
	
	public ConsoleInterface() {
		scanner = new Scanner(System.in);
		printStream = System.out;
	}

	public String promptForPath() {
		return null;
		
	}

	public Category promptForCategory(String idText) {
		printStream.println("Category for: " + idText);
		Category[] options = Category.values();
		for(int i = 0; i < options.length ; i++) {
			printStream.println("Press " + i + " for: " + options[i]);
		}
		Integer response = null;
		while(true) {
			try {
				response = Integer.parseInt(scanner.nextLine());
				break;
			}catch (Exception e) {
				printStream.println("Error reading response, try again.");
			}
		}
		return options[response];
	}
	
	public void close() {
		scanner.close();
	}

}
