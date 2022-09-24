package financesClassifier;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * Acts as a user inteface for the program. Simply gets input from the console.
 * @author Carlos May Ferral
 *
 */
public class ConsoleInterface {
	
	private Scanner scanner;
	PrintStream printStream;
	

	/**
	 * Builds a console interface using the default console input and output.
	 */
	public ConsoleInterface() {
		scanner = new Scanner(System.in);
		printStream = System.out;
	}

	/**
	 * Gets a path from a user.
	 * @return - The path provided by the user.
	 */
	public String promptForPath() {
		printStream.println("Enter path of file to process");
		String path = scanner.nextLine();
		return path;
	}

	/**
	 * Prompts the user for a category to be assigned to the given merchant id.
	 * @param idText - A merchant ID that has not been categorized.
	 * @return - A {@link Category} for the merchant in question.
	 */
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
	
	/**
	 * Closes the scanner resource. To be used on program termination.
	 */
	public void close() {
		scanner.close();
	}

}
