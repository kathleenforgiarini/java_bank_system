package bus;

import java.util.Date;
import java.time.LocalDate;

public class SavingAccount extends Account {
	
	private double annualInterestRate;
	private double annualGain;
	
	public SavingAccount() {
		super();
		this.annualInterestRate = 0.00;
		this.annualGain = 0.00;
	}

	public SavingAccount(Integer accountNumber, EnumTypeAccount type, Integer customerNumber, Double balance, LocalDate openingDate,
			TransactionCollection transactions, double annualInterestRate, double annualGain) {
		super(accountNumber, type, customerNumber, balance, openingDate, transactions);
		this.annualInterestRate = annualInterestRate;
		this.annualGain = annualGain;
	}

	
	public double getAnnualInterestRate() {
		return annualInterestRate;
	}

	public void setAnnualInterestRate(double annualInterestRate) {
		this.annualInterestRate = annualInterestRate;
	}

	public double getAnnualGain() {
		return annualGain;
	}

	public void calcAnnualGain(double annualGain) {
		this.annualGain = this.balance * this.annualInterestRate;
		this.balance += annualGain;
	}

	@Override
	public void deposit(LocalDate transactionDate, Double amount) throws ExceptionNegativeAmount {
		
		//if (amount > 0) {
		Transaction transaction = new Transaction(null, "Deposit", transactionDate, amount, 
        		EnumTypeTransaction.Credit);    
        
		this.balance += amount;
		this.transactions.add(transaction);
        //}
		
	}

	@Override
	public void withdraw(LocalDate transactionDate, Double amount) throws ExceptionNegativeAmount, ExceptionNotEnoughBalance {

		if (amount <= this.balance) {
			Transaction transaction = new Transaction(null, "Withdraw", transactionDate, amount,
            		EnumTypeTransaction.Debit);
			
			this.balance -= amount;
            this.transactions.add(transaction);
        }
		else {
			throw new ExceptionNotEnoughBalance();
		}
		
	}

	@Override
	public String toString() {
		return super.toString() + "\n\tAnnual Interest Rate: " + annualInterestRate + 
								  "\n\tAnnual Gain: " + annualGain;
	}

}
