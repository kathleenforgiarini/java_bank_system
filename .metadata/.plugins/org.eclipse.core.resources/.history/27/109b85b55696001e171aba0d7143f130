package bus;

import java.util.Comparator;

public class PredicateAmount implements Comparator<Transaction>{
	
	@Override
	public int compare(Transaction transaction1, Transaction transaction2) {
		
		Double amount1 = transaction1.getAmount();
		Double amount2 = transaction2.getAmount();
		
		return amount1.compareTo(amount2);
	}
}
