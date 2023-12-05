package bus;

import java.util.Date;
import java.time.LocalDate;

public class LineOfCreditAccount extends CreditAccount{

	protected Double interestRate;
	protected Integer numberOfInstallments;
	protected Double installment;
	
	public LineOfCreditAccount() {
		super();
		this.interestRate = 0.00;
		this.numberOfInstallments = null;
		this.installment = 0.00;
	}
	
	public LineOfCreditAccount(Integer accountNumber, EnumTypeAccount type, Integer customerNumber, Double balance, Date openingDate,
			TransactionCollection transactions, LocalDate dueDate, Double limit, Double interestRate, int installments) {
		
		super(accountNumber, type, customerNumber, balance, openingDate, transactions, dueDate, limit);
		this.interestRate = interestRate;
		this.numberOfInstallments = installments;
		
	}

	public Double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(Double interestRate) {
		this.interestRate = interestRate;
	}

	public int getNumberOfInstallments() {
		return numberOfInstallments;
	}
	
	public void setInstallment(Double installment) {
		this.installment = installment;
	}
	
	public Double getInstallment() {
		return this.installment;
	}

	
	
	@Override
	public void withdraw(LocalDate transactionDate, Double amount) {
		
		if (amount <= getLimit()) {
			
			Double finalDebt = amount * (1 + getInterestRate());
			
			this.setBalance(finalDebt*-1);
			
			setInstallment(getBalance() / getNumberOfInstallments()); 
			
			
			Transaction transaction = new Transaction(null, "Withdraw", transactionDate, amount, EnumTypeTransaction.Debit);
	        this.transactions.add(transaction);
		}
		else {
			
			Double finalDebt = getLimit()*(1+ getInterestRate());
			
			this.setBalance(finalDebt*-1);
			
			Transaction transaction = new Transaction(null, "Withdraw", transactionDate, amount, EnumTypeTransaction.Debit);
	        this.transactions.add(transaction);

		}
	}
	
	
	@Override
	public void deposit(LocalDate transactionDate, Double amount) {
		
		if (amount >= getInstallment()) {
			this.balance += amount;
			
			this.numberOfInstallments--;
			
			Transaction transaction = new Transaction(null, "Deposit", transactionDate, amount, EnumTypeTransaction.Credit);
	        this.transactions.add(transaction);
		}
		else {
			// EXCEPTION IF AMOUNT IS LESS THAN THE PRICE OF THE INSTALLMENT
		}
	}

	@Override
	public String toString() {
		return "LineOfCreditAccount "+
			   "InterestRate = " + interestRate + 
			   "Installments = " + numberOfInstallments + 
			   "Limit = " + limit + 
			   "Balance = " + balance;
	}
}