package bus;

import java.util.ArrayList;

public class TransactionCollection {
	private ArrayList<Transaction> listOfTransactions = new ArrayList<Transaction>();
	
	public void add(Transaction transaction)
	{
		listOfTransactions.add(transaction);
	}
}
