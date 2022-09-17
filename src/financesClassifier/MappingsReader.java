package financesClassifier;

import java.util.HashMap;

public class MappingsReader {

	public HashMap<String, Category> readMappings(String mappingsPath) {
		
		HashMap<String, Category> result = new HashMap<>();
		
		result.put("UBER *TRIP", Category.Transportation);
		result.put("CARTE CREPES", Category.Transportation);
		result.put("COMMERCIAL COIN LAUNDRET", Category.Lining_Expenses);
		
		
		return result;
		
		
		
	}

}
