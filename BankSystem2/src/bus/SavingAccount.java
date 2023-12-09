package bus;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import data.AccountDB;
import data.SavingAccountDB;

public class SavingAccount extends Account {
	private Integer accountNumber;
	private Double interestRate;	// in percentage
	private Double gain;
	private LocalDate dueDate;
	
	public SavingAccount() {
		super();
		this.accountNumber = null;
		this.interestRate = 0.00;
		this.gain = 0.00;
		this.dueDate = null;
	}

	public SavingAccount(Integer accountNumber, EnumTypeAccount type, Integer customer, Double balance, LocalDate openingDate, Double interestRate, LocalDate dueDate) throws ExceptionIsNull, ExceptionIsNotANumber, ExceptionIsPassedDate {
		super(accountNumber, type, customer, balance, openingDate);
		setInterestRate(interestRate);
		setDueDate(dueDate);		
		setGain();
	}

	public void setAccountNumber(Integer accountNumber) {
		this.accountNumber = accountNumber;
	}
	public Integer getAccountNumber() {
		return accountNumber;
	}
	
	public void setInterestRate(Double interestRate) throws ExceptionIsNotANumber, ExceptionIsNull {
		if (!Validator.isDouble(interestRate)) {
			throw new ExceptionIsNotANumber();
		}
		if (Validator.isNull(interestRate)) {
			throw new ExceptionIsNull();
		}
		this.interestRate = interestRate/100;
	}
	public double getInterestRate() {
		return interestRate;
	}

	public void setGain() {
		this.gain = this.balance * this.interestRate;
	}
	public Double getGain() {
		return gain;
	}
	public void calcGain() {
		this.balance += getGain();
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

	@Override
	public void deposit(LocalDate transactionDate, Double amount) throws ExceptionNegativeAmount, ExceptionIsNotANumber, ExceptionIsNull {
		
//		Transaction transaction = new Transaction("Deposit", transactionDate, amount, this,
//        		EnumTypeTransaction.Credit);    
//        
//		this.balance += amount;
//		setGain();
//		this.transactions.add(transaction);
		
	}

	@Override
	public void withdraw(LocalDate transactionDate, Double amount) throws ExceptionNegativeAmount, ExceptionNotEnoughBalance, ExceptionIsNotANumber, ExceptionIsNull {

//		if (transactionDate.isBefore(this.dueDate)) {
//			
//			if (amount <= this.balance) {
//				
//			Transaction transaction = new Transaction("Withdraw", transactionDate, amount, this,
//            		EnumTypeTransaction.Debit);
//			
//			this.balance -= amount;
//			setGain();
//            this.transactions.add(transaction);
//          
//			}
//			else {
//				throw new ExceptionNotEnoughBalance();
//			}
//		}
//		else {
//			
//			calcGain();
//			
//			if (amount == this.balance) {
//				Transaction transactionInterest = new Transaction("Withdraw", transactionDate, amount, this,
//	            		EnumTypeTransaction.Debit);
//				
//				this.balance -= amount;
//				setGain();
//				this.transactions.add(transactionInterest);
//			}
//		}
	}

	@Override
	public String toString() {
		return super.toString() + "\n\tAnnual Interest Rate: " + interestRate + 
								  "\n\tAnnual Gain: " + gain;
	}
	
	//////////////////////////////
	//   public static services //
	//////////////////////////////
	public static void add(SavingAccount element) throws SQLException {
		SavingAccountDB.insert(element);
	}
	
	public static void update(SavingAccount element) throws SQLException {
		SavingAccountDB.update(element);
	}
	
	public static void remove(Integer id) throws SQLException {
		AccountDB.delete(id);
	}
	
	public static SavingAccount search(Integer id) throws SQLException, ExceptionIsNull, ExceptionIsNotANumber, ExceptionIsPassedDate {
		return SavingAccountDB.search(id);
	}
	
	public static ArrayList<SavingAccount> getData() throws SQLException, ExceptionIsNull, ExceptionIsNotANumber, ExceptionIsPassedDate {
		return SavingAccountDB.select();
	}
}