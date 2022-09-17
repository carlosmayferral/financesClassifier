package financesClassifier;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TransactionParserTest {
	
	private TransactionParser tp;

	@BeforeEach
	void setUp() throws Exception {
		tp = new TransactionParser();
	}
	
	@Test
	void parsingDateWorks() {
		Transaction transaction = tp.parseTransaction("14 Sep 2022,2% CASHBACK - ENJOY BRUNSWICK SUPA IGA 12SEP22  572308 17:05:40 2% Cashback - Enjoy 572308 BRUNSWIC 36 1346 Z@MF14556 SYSTEM GENERATED ,\"0.38\",\" 1,124.15\"");
		LocalDate expected = LocalDate.of(2022, 9, 14);
		assertTrue(expected.equals(transaction.getDate()));
	}
	
	@Test
	void parsingAmountWorks() {
		Transaction transaction = tp.parseTransaction("14 Sep 2022,2% CASHBACK - ENJOY BRUNSWICK SUPA IGA 12SEP22  572308 17:05:40 2% Cashback - Enjoy 572308 BRUNSWIC 36 1346 Z@MF14556 SYSTEM GENERATED ,\"0.38\",\" 1,124.15\"");
		Double expected = 0.38;
		assertEquals(expected,transaction.getAmount());
	}

}
