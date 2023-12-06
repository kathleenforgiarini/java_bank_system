package bus;
import java.util.Date;
import java.time.LocalDate;

public class CreditAccount extends Account{

	protected LocalDate dueDate;
	protected Double limit;
	
	
	public CreditAccount() {
		
		super();
		this.dueDate = null;
		this.limit = 0.00;
	}

	public CreditAccount(Integer accountNumber, EnumTypeAccount type, Integer customerNumber, Double balance, LocalDate openingDate,
			TransactionCollection transactions, LocalDate dueDate, Double limit) {
		
		super(accountNumber, type, customerNumber, balance, openingDate, transactions);
		
		this.dueDate = dueDate;
		this.limit = limit;
	}
	
	
	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) { //day of the month
		this.dueDate = dueDate;
	}

	public Double getLimit() {
		return limit;
	}

	public void setLimit(Double limit) {
		this.limit = limit;
	}

	@Override
	public void deposit(LocalDate transactionDate, Double amount) throws ExceptionNegativeAmount, ExceptionWrongAmount, ExceptionLatePayment {
				
		Double debtValue = getLimit() - getBalance();
		
		if (transactionDate.isBefore(getDueDate())) {
				
			Transaction transaction = new Transaction(null, "Deposit", transactionDate, amount, EnumTypeTransaction.Credit);	
			
			this.balance += amount;
			setDueDate(getDueDate().plusMonths(1));
			this.transactions.add(transaction);
		}
		else 
		{
			Double taxLate = 0.05;
			Double lateFee = taxLate*debtValue;			
		 		
			Transaction transactionDep = new Transaction(null, "Deposit", transactionDate, amount, EnumTypeTransaction.Credit);
			Transaction transactionFees = new Transaction(null, "Fee for late payment", transactionDate, lateFee, EnumTypeTransaction.Debit);
			
		 	this.balance += amount;
	        this.transactions.add(transactionDep);
			
	        this.balance -= lateFee;
	        this.transactions.add(transactionFees);
		}
	}

	@Override
	public void withdraw(LocalDate transactionDate, Double amount) throws ExceptionNegativeAmount, ExceptionNotEnoughBalance {
		
		if (amount <= getBalance()) {
			
			Transaction transaction = new Transaction(null, "Withdraw", transactionDate, amount, EnumTypeTransaction.Debit);
			
			this.balance -= amount;
            this.transactions.add(transaction);
		}
		else {
			throw new ExceptionNotEnoughBalance();
		}
	}
	
	@Override
	public String toString() {
		return "CreditAccount " +
			   "\n\tDue Date =" + this.dueDate + 
			   "\n\tLimit =" + this.limit + 
			   "\n\tBalance=" + this.balance;
	}
}
