package bus;

import java.sql.SQLException;
import java.time.LocalDate;

public interface ITransaction {
	public void deposit (Double amount) throws ExceptionNegativeAmount, ExceptionWrongAmount, ExceptionLatePayment, ExceptionIsPassedDate, ExceptionIsNotANumber, ExceptionIsNull, SQLException;
	public void withdraw (Double amount) throws ExceptionNotEnoughBalance, ExceptionNegativeAmount, ExceptionIsNull, ExceptionIsNotANumber, SQLException, ExceptionNotEnoughLimit;
	public Double getBalance ();
}
