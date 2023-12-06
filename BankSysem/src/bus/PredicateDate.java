package bus;

import java.util.Comparator;
import java.time.LocalDate;

public class PredicateDate implements Comparator<Transaction>{
	
	@Override
	public int compare(Transaction transaction1, Transaction transaction2) {
		
		LocalDate date1 = transaction1.getTransactionDate();
		LocalDate date2 = transaction2.getTransactionDate();
		
		return date1.compareTo(date2);
	}
}
