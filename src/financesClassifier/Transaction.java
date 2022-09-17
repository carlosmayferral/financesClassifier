package financesClassifier;

import java.time.LocalDate;

/**
 * Represents a transaction read from the hsbc payment summary.
 * @author Carlos May Ferral
 *
 */
public class Transaction {
	/**
	 * The date this payment was finalized.
	 */
	private LocalDate date; //basic
	
	/**
	 * The full text of this transaction description.
	 */
	private String fullText; //basic
	
	/**
	 * The part of the description used for ID purposes.
	 */
	private String idText; 
	
	/**
	 * The assigned category.
	 */
	private Category category;
	
	/**
	 * The amount of money (in AUD) the payment represents.
	 */
	private Double amount; //basic
	
	/**
	 * Builds a transaction based on the payment log cells.
	 * @param date - The date this payment was finalized.
	 * @param description - The full text of this transaction description.
	 * @param amount - The amount of money (in AUD) the payment represents.
	 */
	public Transaction(LocalDate date, String description, Double amount) {
		this.date = date;
		this.fullText = description;
		this.amount = amount;
		
		this.idText = null;
		this.category = Category.UNCLASSIFIED;
	}

	/**
	 * @return the date
	 */
	public LocalDate getDate() {
		return date;
	}

	/**
	 * @return the fullText
	 */
	public String getFullText() {
		return fullText;
	}

	/**
	 * @return the idText
	 */
	public String getIdText() {
		return idText;
	}

	/**
	 * @return the category
	 */
	public Category getCategory() {
		return category;
	}

	/**
	 * @return the amount
	 */
	public Double getAmount() {
		return amount;
	}

	/**
	 * @param idText the idText to set
	 */
	public void setIdText(String idText) {
		this.idText = idText;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(Category category) {
		this.category = category;
	}
	
	

}

