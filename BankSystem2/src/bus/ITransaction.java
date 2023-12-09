package bus;

import java.sql.SQLException;
import java.time.LocalDate;

public interface ITransaction {
	public void deposit (LocalDate transactionDate, Double amount) throws ExceptionNegativeAmount, ExceptionWrongAmount, ExceptionLatePayment, ExceptionIsPassedDate, ExceptionIsNotANumber, ExceptionIsNull, SQLException;
	public void withdraw (LocalDate transactionDate, Double amount) throws ExceptionNotEnoughBalance, ExceptionNegativeAmount, ExceptionIsNull, ExceptionIsNotANumber, SQLException;
	public Double getBalance ();
}
