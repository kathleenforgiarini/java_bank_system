package bus;

import java.time.LocalDate;

public interface ITransaction {
	public void deposit (LocalDate transactionDate, Double amount) throws ExceptionNegativeAmount, ExceptionWrongAmount, ExceptionLatePayment, ExceptionIsPassedDate, ExceptionIsNotANumber, ExceptionIsNull;
	public void withdraw (LocalDate transactionDate, Double amount) throws ExceptionNotEnoughBalance, ExceptionNegativeAmount, ExceptionIsNull, ExceptionIsNotANumber;
	public Double getBalance ();
}
