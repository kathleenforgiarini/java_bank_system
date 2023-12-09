package bus;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import data.AccountDB;
import data.CheckingAccountDB;

public class CheckingAccount extends Account {
	private Integer accountNumber;
	private Integer monthlyTransactionLimit;
	private Double transactionFees;
	
	public CheckingAccount() {
		super();
		this.accountNumber = null;
		this.monthlyTransactionLimit = 3;
		this.transactionFees = 0.00;
	}
	
	public CheckingAccount(Integer accountNumber, EnumTypeAccount type, Integer customer, Double balance, LocalDate openingDate,
			Integer monthlyTransactionLimit, Double transactionFees) throws ExceptionIsNull, ExceptionIsNotANumber {
		super(accountNumber, type, customer, balance, openingDate);
		setMonthlyTransactionLimit(monthlyTransactionLimit);
		setTransactionFees(transactionFees);
	}
	
	public void setAccountNumber(Integer accountNumber) {
		this.accountNumber = accountNumber;
	}
	public Integer getAccountNumber() {
		return accountNumber;
	}

	public void setMonthlyTransactionLimit(Integer monthlyTransactionLimit) throws ExceptionIsNotANumber, ExceptionIsNull {
		if (!Validator.isInteger(monthlyTransactionLimit)) {
			throw new ExceptionIsNotANumber();
		}
		if (Validator.isNull(monthlyTransactionLimit)) {
			throw new ExceptionIsNull();
		}
		if (monthlyTransactionLimit < 3) {
			this.monthlyTransactionLimit = 3;
		} else {
			this.monthlyTransactionLimit = monthlyTransactionLimit;
		}
	}
	public int getMonthlyTransactionLimit() {
		return monthlyTransactionLimit;
	}

	public void setTransactionFees(Double transactionFees) throws ExceptionIsNull, ExceptionIsNotANumber {
		if (Validator.isNull(transactionFees)) {
			throw new ExceptionIsNull();
		}
		
		if (!Validator.isDouble(transactionFees)) {
			throw new ExceptionIsNotANumber();
		}
		this.transactionFees = transactionFees;
	}
	public double getTransactionFees() {
		return transactionFees;
	}

	@Override
	public void deposit(LocalDate transactionDate, Double amount) throws ExceptionNegativeAmount, ExceptionIsNotANumber, ExceptionIsNull {
//		if (amount > 0) {
//			
//			if (transactions.getCountThisMonth(transactionDate) < this.monthlyTransactionLimit) {
//				this.balance += amount;
//
//	            Transaction transaction = new Transaction("Deposit", transactionDate,
//	            		amount, this, EnumTypeTransaction.Credit);
//	            
//	            this.transactions.add(transaction);
//			} 
//			else {
//				this.balance += amount;
//				
//				Transaction transactionDep = new Transaction("Deposit", transactionDate,
//	            		amount, this, EnumTypeTransaction.Credit);
//	            
//	            this.transactions.add(transactionDep);
//	            
//	            
//				this.balance -= this.transactionFees;
//
//				Transaction transactionFee = new Transaction("Fee for transaction limit", transactionDate,
//	            		amount, this, EnumTypeTransaction.Debit);
//	            
//	            this.transactions.add(transactionFee);
//			}
//        }
		
	}

	@Override
	public void withdraw(LocalDate transactionDate, Double amount) throws ExceptionNotEnoughBalance, ExceptionNegativeAmount, ExceptionIsNotANumber, ExceptionIsNull {

//			if (amount <= this.balance) {
//				if (transactions.getCountThisMonth(transactionDate) < monthlyTransactionLimit) {
//					this.balance -= amount;
//
//		            Transaction transaction = new Transaction("Withdraw", transactionDate, amount, this,
//		            		EnumTypeTransaction.Debit);
//		            this.transactions.add(transaction);
//				
//				}
//				else {
//					this.balance -= amount;
//
//		            Transaction transactionWith = new Transaction("Withdraw", transactionDate, amount, this,
//		            		EnumTypeTransaction.Debit);
//		            this.transactions.add(transactionWith);
//		            
//		            this.balance -= this.transactionFees;
//		            
//		            Transaction transactionFees = new Transaction("Fee for transaction limit", transactionDate, amount, this,
//		            		EnumTypeTransaction.Debit);
//		            this.transactions.add(transactionFees);
//				
//				}
//			} else {
//				throw new ExceptionNotEnoughBalance();
//			}				
	}

	@Override
	public String toString() {
		return super.toString() + "\n\tMonthly Transaction Limit: " + monthlyTransactionLimit + 
								  "\n\tTransaction Fees: " + transactionFees;
	}
	
	//////////////////////////////
	//   public static services //
	//////////////////////////////
	public static void add(CheckingAccount element) throws SQLException {
		CheckingAccountDB.insert(element);
	}
	
	public static void update(CheckingAccount element) throws SQLException {
		CheckingAccountDB.update(element);
	}
	
	public static void remove(Integer id) throws SQLException {
		AccountDB.delete(id);
	}
	
	public static CheckingAccount search(Integer id) throws SQLException, ExceptionIsNull, ExceptionIsNotANumber, ExceptionIsPassedDate {
		return CheckingAccountDB.search(id);
	}
	
	public static ArrayList<CheckingAccount> getData() throws SQLException, ExceptionIsNull, ExceptionIsNotANumber {
		return CheckingAccountDB.select();
	}
}
