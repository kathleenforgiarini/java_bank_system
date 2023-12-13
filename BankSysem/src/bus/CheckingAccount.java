package bus;

import java.time.LocalDate;
import java.util.ArrayList;

public class CheckingAccount extends Account {
	private static final long serialVersionUID = -536443424779652112L;
	private int monthlyTransactionLimit;
	private double transactionFees;
	
	public CheckingAccount() {
		super();
		this.monthlyTransactionLimit = 3;
		this.transactionFees = 0.00;
	}
	
	public CheckingAccount(EnumTypeAccount type, Customer customer, Double balance, LocalDate openingDate,
			ArrayList<Transaction> transactions, int monthlyTransactionLimit, double transactionFees) throws ExceptionIsNull, ExceptionIsNotANumber {
		super(type, customer, balance, openingDate, transactions);
		setMonthlyTransactionLimit(monthlyTransactionLimit);
		setTransactionFees(transactionFees);
	}

	public int getMonthlyTransactionLimit() {
		return monthlyTransactionLimit;
	}

	public void setMonthlyTransactionLimit(int monthlyTransactionLimit) throws ExceptionIsNotANumber, ExceptionIsNull {
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

	public double getTransactionFees() {
		return transactionFees;
	}

	public void setTransactionFees(double transactionFees) throws ExceptionIsNull, ExceptionIsNotANumber {
		if (Validator.isNull(transactionFees)) {
			throw new ExceptionIsNull();
		}
		
		if (!Validator.isDouble(transactionFees)) {
			throw new ExceptionIsNotANumber();
		}
		this.transactionFees = transactionFees;
	}

	@Override
	public void deposit(LocalDate transactionDate, Double amount) throws ExceptionNegativeAmount, ExceptionIsNotANumber, ExceptionIsNull {
		if (amount > 0) {
			
			Integer getCountThisMonth = TransactionCollection.getCountThisMonth(LocalDate.now(), this.getTransactions());

			if (getCountThisMonth < this.monthlyTransactionLimit) {
				this.balance += amount;

	            Transaction transaction = new Transaction("Deposit", transactionDate,
	            		amount, this, EnumTypeTransaction.Credit);
	            
	            this.transactions.add(transaction);
			} 
			else {
				this.balance += amount;
				
				Transaction transactionDep = new Transaction("Deposit", transactionDate,
	            		amount, this, EnumTypeTransaction.Credit);
	            
	            this.transactions.add(transactionDep);
	            
	            
				this.balance -= this.transactionFees;

				Transaction transactionFee = new Transaction("Fee for transaction limit", transactionDate,
						this.transactionFees, this, EnumTypeTransaction.Debit);
	            
	            this.transactions.add(transactionFee);
			}
        }
		
	}

	@Override
	public void withdraw(LocalDate transactionDate, Double amount) throws ExceptionNotEnoughBalance, ExceptionNegativeAmount, ExceptionIsNotANumber, ExceptionIsNull {
		
			if (amount <= this.balance) {
				
				Integer getCountThisMonth = TransactionCollection.getCountThisMonth(LocalDate.now(), this.getTransactions());
				
				if (getCountThisMonth < this.monthlyTransactionLimit) {
					this.balance -= amount;

		            Transaction transaction = new Transaction("Withdraw", transactionDate, amount, this,
		            		EnumTypeTransaction.Debit);
		            this.transactions.add(transaction);
				}
				else {
					this.balance -= amount;

		            Transaction transactionWith = new Transaction("Withdraw", transactionDate, amount, this,
		            		EnumTypeTransaction.Debit);
		            this.transactions.add(transactionWith);
		            
		            this.balance -= this.transactionFees;
		            
		            Transaction transactionFees = new Transaction("Fee for transaction limit", transactionDate, this.transactionFees, this,
		            		EnumTypeTransaction.Debit);
		            this.transactions.add(transactionFees);
				
				}
			} else {
				throw new ExceptionNotEnoughBalance();
			}				
	}

	@Override
	public String toString() {
		return super.toString() + "\n\tMonthly Transaction Limit: " + monthlyTransactionLimit + 
								  "\n\tTransaction Fees: " + transactionFees;
	}

	

}
