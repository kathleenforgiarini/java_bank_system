package bus;

import java.util.Date;
import java.time.LocalDate;
import java.time.Period;

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
	
	public LineOfCreditAccount(Integer accountNumber, EnumTypeAccount type, Integer customerNumber, LocalDate openingDate,
			TransactionCollection transactions, LocalDate dueDate, Double limit, Double interestRate) throws ExceptionNegativeAmount {
		
		//limit = requested value
		
		super(accountNumber, type, customerNumber, (double)0, openingDate, transactions, dueDate, limit);
		this.interestRate = interestRate;
				
		Double finalDebt = limit * (1 + getInterestRate());
		this.installment = finalDebt/numberOfInstallments;
		
		withdraw(openingDate, finalDebt);
		
		Period numberOfMonths = Period.between(openingDate.withDayOfMonth(1), dueDate.withDayOfMonth(1));
		this.numberOfInstallments = numberOfMonths.getMonths();
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
	public void withdraw(LocalDate transactionDate, Double amount) throws ExceptionNegativeAmount {
		
		Transaction transaction = new Transaction(null, "Withdraw", transactionDate, amount, EnumTypeTransaction.Debit);
		
		this.setBalance(amount*-1);
		this.transactions.add(transaction);
	}
	
	
	@Override
	public void deposit(LocalDate transactionDate, Double amount) throws ExceptionNegativeAmount, ExceptionWrongAmount, ExceptionLatePayment {
		
		if (amount >= getInstallment()) {
			if (transactionDate.isBefore(this.dueDate))
			{
				Transaction transaction = new Transaction(null, "Deposit", transactionDate, amount, EnumTypeTransaction.Credit);
				
				this.balance += amount;
				this.numberOfInstallments--;
		        this.transactions.add(transaction);
			}
			else
			{
				throw new ExceptionLatePayment();
			}
		}
		else {
			throw new ExceptionWrongAmount();
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
