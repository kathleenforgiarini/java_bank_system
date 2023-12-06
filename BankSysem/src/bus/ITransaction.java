package bus;

import java.time.LocalDate;

public interface ITransaction {
	public void deposit (LocalDate transactionDate, Double amount) throws ExceptionNegativeAmount, ExceptionWrongAmount, ExceptionLatePayment;
	public void withdraw (LocalDate transactionDate, Double amount) throws ExceptionNotEnoughBalance, ExceptionNegativeAmount;
	public Double getBalance ();
}
