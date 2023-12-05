package bus;

import java.util.Comparator;
import java.util.Date;

public class PredicateDate implements Comparator<Transaction>{
	
	@Override
	public int compare(Transaction transaction1, Transaction transaction2) {
		
		Date date1 = transaction1.getTransactionDate();
		Date date2 = transaction2.getTransactionDate();
		
		return date1.compareTo(date2);
	}
}
