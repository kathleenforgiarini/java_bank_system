package bus;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

public class LineOfCreditAccount extends CreditAccount{

	private static final long serialVersionUID = 46034430825741637L;
	protected Double interestRate;
	protected Integer numberOfInstallments;
	protected Double installment;
	
	public LineOfCreditAccount() {
		super();
		this.interestRate = 0.00;
		this.numberOfInstallments = null;
		this.installment = 0.00;
	}
	
	public LineOfCreditAccount(EnumTypeAccount type, Customer customer, LocalDate openingDate,
			ArrayList<Transaction> transactions, LocalDate dueDate, Double limit, Double interestRate) throws ExceptionNegativeAmount, ExceptionIsNull, ExceptionIsNotANumber, ExceptionIsPassedDate {
		
		//limit = requested value
		
		super(type, customer, (double)0, openingDate, transactions, dueDate, limit);
		setInterestRate(interestRate);
				
		Period numberOfMonths = Period.between(openingDate.withDayOfMonth(1), dueDate.withDayOfMonth(1));	//NUMBER OF INSTALLMENTS IS THE QUANTITY OF MONTHS FROM THE OPENING DATE OF THE ACCOUNT AND THE DUE DATE
		this.numberOfInstallments = numberOfMonths.getMonths();

		
		Double finalDebt = limit * (1 + getInterestRate());													//CALCULATING THE FINAL PRICE THAT THE CUSTOMER HAS TO PAY: THE AMOUNT ASKED PLUS THE INTEREST RATE
		this.installment = finalDebt/this.numberOfInstallments;
		
		withdraw(openingDate, finalDebt);
		
	}

	public Double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(Double interestRate) throws ExceptionIsNotANumber, ExceptionIsNull {
		if (!Validator.isDouble(interestRate)) {
			throw new ExceptionIsNotANumber();
		}
		if (Validator.isNull(interestRate)) {
			throw new ExceptionIsNull();
		}
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
	public void withdraw(LocalDate transactionDate, Double amount) throws ExceptionNegativeAmount, ExceptionIsNull, ExceptionIsNotANumber {
		
		Transaction transaction = new Transaction("Withdraw", transactionDate, amount, this, EnumTypeTransaction.Debit);
		
		this.setBalance(amount*-1);
		this.transactions.add(transaction);
	}
	
	
	@Override
	public void deposit(LocalDate transactionDate, Double amount) throws ExceptionNegativeAmount, ExceptionWrongAmount, ExceptionLatePayment, ExceptionIsNotANumber, ExceptionIsNull {
		
		if (amount >= getInstallment()) {
			if (transactionDate.isBefore(this.dueDate))
			{
				Transaction transaction = new Transaction("Deposit", transactionDate, amount, this, EnumTypeTransaction.Credit);
				
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
