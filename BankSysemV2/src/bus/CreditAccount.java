package bus;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import data.AccountDB;
import data.CreditAccountDB;

public class CreditAccount extends Account{

	private Integer accountNumber;
	protected LocalDate dueDate;
	protected Double limit;
	
	
	public CreditAccount() {
		super();
		this.accountNumber = null;
		this.dueDate = null;
		this.limit = 0.00;
	}

	public CreditAccount(Integer accountNumber, EnumTypeAccount type, Integer customer, Double balance, LocalDate openingDate,
			LocalDate dueDate, Double limit) throws ExceptionIsNull, ExceptionIsNotANumber, ExceptionIsPassedDate {
		
		super(accountNumber, type, customer, balance, openingDate);
		setDueDate(dueDate);
		setLimit(limit);
	}
	
	public void setAccountNumber(Integer accountNumber) {
		this.accountNumber = accountNumber;
	}
	public Integer getAccountNumber() {
		return accountNumber;
	}
	
	public void setDueDate(LocalDate dueDate) throws ExceptionIsPassedDate {
		LocalDate now = LocalDate.now();
		if (dueDate.isBefore(now)) {
			throw new ExceptionIsPassedDate();
		}
		this.dueDate = dueDate;
	}
	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setLimit(Double limit) throws ExceptionIsNull, ExceptionIsNotANumber {
		if (Validator.isNull(limit)) {
			throw new ExceptionIsNull();
		}
		
		if (!Validator.isDouble(limit)) {
			throw new ExceptionIsNotANumber();
		}
		this.limit = limit;
	}
	public Double getLimit() {
		return limit;
	}

	@Override
	public void deposit(LocalDate transactionDate, Double amount) throws ExceptionNegativeAmount, ExceptionWrongAmount, ExceptionLatePayment, ExceptionIsPassedDate, ExceptionIsNotANumber, ExceptionIsNull {
				
//		Double debtValue = getLimit() - getBalance();
//		
//		if (transactionDate.isBefore(getDueDate())) {
//				
//			Transaction transaction = new Transaction("Deposit", transactionDate, amount, this, EnumTypeTransaction.Credit);	
//			
//			this.balance += amount;
//			setDueDate(getDueDate().plusMonths(1));
//			this.transactions.add(transaction);
//		}
//		else 
//		{
//			Double taxLate = 0.05;
//			Double lateFee = taxLate*debtValue;			
//		 		
//			Transaction transactionDep = new Transaction("Deposit", transactionDate, amount, this, EnumTypeTransaction.Credit);
//			Transaction transactionFees = new Transaction("Fee for late payment", transactionDate, lateFee, this, EnumTypeTransaction.Debit);
//			
//		 	this.balance += amount;
//	        this.transactions.add(transactionDep);
//			
//	        this.balance -= lateFee;
//	        this.transactions.add(transactionFees);
//		}
	}

	@Override
	public void withdraw(LocalDate transactionDate, Double amount) throws ExceptionNegativeAmount, ExceptionNotEnoughBalance, ExceptionIsNull, ExceptionIsNotANumber {
		
//		if (amount <= getBalance()) {
//			
//			Transaction transaction = new Transaction("Withdraw", transactionDate, amount, this, EnumTypeTransaction.Debit);
//			
//			this.balance -= amount;
//            this.transactions.add(transaction);
//		}
//		else {
//			throw new ExceptionNotEnoughBalance();
//		}
	}
	
	@Override
	public String toString() {
		return "\n\tDue Date: " + this.dueDate + 
			   "\n\tLimit: " + this.limit + 
			   "\n\tBalance: " + this.balance;
	}
	
	//////////////////////////////
	//   public static services //
	//////////////////////////////
	public static void add(CreditAccount element) throws SQLException {
		CreditAccountDB.insert(element);
	}
	
	public static void update(CreditAccount element) throws SQLException {
		CreditAccountDB.update(element);
	}
	
	public static void remove(Integer id) throws SQLException {
		AccountDB.delete(id);
	}
	
	public static CreditAccount search(Integer id) throws SQLException, ExceptionIsNull, ExceptionIsNotANumber, ExceptionIsPassedDate {
		return CreditAccountDB.search(id);
	}
	
	public static ArrayList<CreditAccount> getData() throws SQLException, ExceptionIsNull, ExceptionIsNotANumber, ExceptionIsPassedDate {
		return CreditAccountDB.select();
	}
}
