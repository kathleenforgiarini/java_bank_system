package bus;

import java.util.Date;
import java.time.LocalDate;

public class CheckingAccount extends Account {

	private int monthlyTransactionLimit;
	private double transactionFees;
	
	public CheckingAccount() {
		super();
		this.monthlyTransactionLimit = 3;
		this.transactionFees = 0.00;
	}
	
	public CheckingAccount(Integer accountNumber, EnumTypeAccount type, Integer customerNumber, Double balance, LocalDate openingDate,
			TransactionCollection transactions, int monthlyTransactionLimit, double transactionFees) {
		super(accountNumber, type, customerNumber, balance, openingDate, transactions);
		setMonthlyTransactionLimit(monthlyTransactionLimit);
		this.transactionFees = transactionFees;
	}

	public int getMonthlyTransactionLimit() {
		return monthlyTransactionLimit;
	}

	public void setMonthlyTransactionLimit(int monthlyTransactionLimit) {
		if (monthlyTransactionLimit < 3) {
			this.monthlyTransactionLimit = 3;
		} else {
			this.monthlyTransactionLimit = monthlyTransactionLimit;
		}
	}

	public double getTransactionFees() {
		return transactionFees;
	}

	public void setTransactionFees(double transactionFees) {
		this.transactionFees = transactionFees;
	}

	@Override
	public void deposit(LocalDate transactionDate, Double amount) throws ExceptionNegativeAmount {
		if (amount > 0) {
			
			if (transactions.getCountThisMonth(transactionDate) < this.monthlyTransactionLimit) {
				this.balance += amount;

	            Transaction transaction = new Transaction(null, "Deposit", transactionDate,
	            		amount, EnumTypeTransaction.Credit);
	            
	            this.transactions.add(transaction);
			} 
			else {
				this.balance += amount;
				
				Transaction transactionDep = new Transaction(null, "Deposit", transactionDate,
	            		amount, EnumTypeTransaction.Credit);
	            
	            this.transactions.add(transactionDep);
	            
	            
				this.balance -= this.transactionFees;

				Transaction transactionFee = new Transaction(null, "Fee for transaction limit", transactionDate,
	            		amount, EnumTypeTransaction.Debit);
	            
	            this.transactions.add(transactionFee);
			}
        }
		
	}

	@Override
	public void withdraw(LocalDate transactionDate, Double amount) throws ExceptionNotEnoughBalance, ExceptionNegativeAmount {

		//if (amount > 0) {
			if (amount <= this.balance) {
				if (transactions.getCountThisMonth(transactionDate) < monthlyTransactionLimit) {
					this.balance -= amount;

		            Transaction transaction = new Transaction(null, "Withdraw", transactionDate, amount,
		            		EnumTypeTransaction.Debit);
		            this.transactions.add(transaction);
				
				}
				else {
					this.balance -= amount;

		            Transaction transactionWith = new Transaction(null, "Withdraw", transactionDate, amount,
		            		EnumTypeTransaction.Debit);
		            this.transactions.add(transactionWith);
		            
		            this.balance -= this.transactionFees;
		            
		            Transaction transactionFees = new Transaction(null, "Fee for transaction limit", transactionDate, amount,
		            		EnumTypeTransaction.Debit);
		            this.transactions.add(transactionFees);
				
				}
			} else {
				throw new ExceptionNotEnoughBalance();
			}
		//} else {
			//
		//}
				
	}

	@Override
	public String toString() {
		return super.toString() + "\n\tMonthly Transaction Limit: " + monthlyTransactionLimit + 
								  "\n\tTransaction Fees: " + transactionFees;
	}

	

}
