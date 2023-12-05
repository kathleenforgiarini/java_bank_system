package bus;

import java.util.Date;

public interface ITransaction {
	public void deposit (Integer transactionNumber, String description, Date transactionDate, Double amount,
			EnumTypeTransaction type);
	public void withdraw (Integer transactionNumber, String description, Date transactionDate, Double amount,
			EnumTypeTransaction type);
	public Double getBalance ();
}
