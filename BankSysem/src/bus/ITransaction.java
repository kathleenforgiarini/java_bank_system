package bus;

import java.time.LocalDate;

public interface ITransaction {
	public void deposit (LocalDate transactionDate, Double amount);
	public void withdraw (LocalDate transactionDate, Double amount);
	public Double getBalance ();
}
