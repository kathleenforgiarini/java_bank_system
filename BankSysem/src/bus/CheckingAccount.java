package bus;

import java.util.Date;

public class CheckingAccount extends Account {

	private int monthlyTransactionLimit;
	private double transactionFees;
	
	public CheckingAccount() {
		super();
		this.monthlyTransactionLimit = 0;
		this.transactionFees = 0.00;
	}
	
	public CheckingAccount(Integer accountNumber, EnumTypeAccount type, Integer customerNumber, Double balance, Date openingDate,
			TransactionCollection transactions, int monthlyTransactionLimit, double transactionFees) {
		super(accountNumber, type, customerNumber, balance, openingDate, transactions);
		this.monthlyTransactionLimit = monthlyTransactionLimit;
		this.transactionFees = transactionFees;
	}

	public int getMonthlyTransactionLimit() {
		return monthlyTransactionLimit;
	}

	public void setMonthlyTransactionLimit(int monthlyTransactionLimit) {
		this.monthlyTransactionLimit = monthlyTransactionLimit;
	}

	public double getTransactionFees() {
		return transactionFees;
	}

	public void setTransactionFees(double transactionFees) {
		this.transactionFees = transactionFees;
	}

	@Override
	public void deposit(Integer transactionNumber, String description, Date transactionDate, Double amount,
			EnumTypeTransaction type) {
		if (amount > 0) {
            this.balance += amount;

            Transaction transaction = new Transaction(transactionNumber, description, transactionDate,
            		amount, type);
            
            this.transactions.add(transaction);
        }
		
	}

	@Override
	public void withdraw(Integer transactionNumber, String description, Date transactionDate, Double amount,
			EnumTypeTransaction type) {

		if (amount > 0 && amount <= this.balance && transactions.getCountThisMonth() < monthlyTransactionLimit) {
            this.balance -= amount;

            this.balance -= transactionFees;

            Transaction transaction = new Transaction(transactionNumber, description, transactionDate, amount,
            		type);
            this.transactions.add(transaction);
        } else {
        	// valor incorreto ou excedeu limite de transações
        }
		
	}

	@Override
	public String toString() {
		return super.toString() + "\n\tMonthly Transaction Limit: " + monthlyTransactionLimit + 
								  "\n\tTransaction Fees: " + transactionFees;
	}

	

}
