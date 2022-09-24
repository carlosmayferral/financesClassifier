package financesClassifier;

import java.util.Arrays;

/**
 * Reads transaction descriptions and extracts the unique identifier for the merchant.
 * @author Carlos May Ferral
 *
 */
public class DescriptionParser {

	/**
	 * Takes a full description for a transaction and returns only the Merchant ID components.
	 * @param description - From the transaction log file.
	 * @return - Only the components that uniquely identify this merchant. Or null if there is no
	 * valid merchant ID in this description.
	 */
	public String parseMerchant(String description) {
		
		//Split string by non-breaking space
		String delimitedDescription = description.replace('\u00A0', ',');
		System.out.println(Arrays.toString(delimitedDescription.split(",")));
		
		String potentialId = delimitedDescription.split(",")[0];
		
		if(potentialId.equals("TRANSFER")) {
			return description;
		}
		
		if(potentialId.contains("PENDING")) {
			return null;
		}
		
		if(potentialId.equals("2% CASHBACK - ENJOY")) {
			return potentialId;
		}
		
		String location = delimitedDescription.split(",")[4];
		location = location.substring(location.indexOf(' '));
		
		return (potentialId + " - " + location);
	}
	
	
	

}
