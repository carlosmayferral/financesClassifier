package financesClassifier;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DescriptionParserTest {
	
	private DescriptionParser descriptionParser;

	@BeforeEach
	void setUp() throws Exception {
		descriptionParser = new DescriptionParser();
	}

	@Test
	void parseMerchandIdNormal() {
		String result = descriptionParser.parseMerchant("BRUNSWICK SUPA IGA 15SEP22 ATMA896 07:49:21 1346     VISA        AUD BRUNSWICK SUPA IGA 686405 BRUNSWICK    AU A88827052 ATM ");
		assertEquals("BRUNSWICK SUPA IGA -  BRUNSWICK    AU",result);
	}
	
	@Test
	void parseMerchandIdCashBack() {
		String result = descriptionParser.parseMerchant("2% CASHBACK - ENJOY Carte crepes 13SEP22  274979 14:32:20 2% Cashback - Enjoy 274979 Parkvill 36 1346 Z@MF46333 SYSTEM GENERATED ");
		assertEquals("2% CASHBACK - ENJOY",result);
	}
	
	@Test
	void parseMerchandIdTransfer() {
		String result = descriptionParser.parseMerchant("TRANSFER LP SDB21J0PP ayuda semanal mama Ana Ferral 10673486 ayuda semanal YIB159233 INTERNET BANKING ");
		assertEquals("TRANSFER LP SDB21J0PP ayuda semanal mama Ana Ferral 10673486 ayuda semanal YIB159233 INTERNET BANKING ",result);
	}

}
