package bus;

import java.util.Date;

public abstract class Account implements ITransaction {
	protected Integer accountNumber;
	protected EnumTypeAccount type;
	protected Double balance;
	protected Date openingDate;
	protected TransactionCollection transactions;
	
	public Account() {
		super();
		this.accountNumber = 0;
		this.type = EnumTypeAccount.Undefined;
		this.balance = (double) 0;
		this.openingDate = null;
		this.transactions = new TransactionCollection();
	}
	
	public Account(Integer accountNumber, EnumTypeAccount type, Double balance, Date openingDate,
			TransactionCollection transactions) {
		super();
		this.accountNumber = accountNumber;
		this.type = type;
		this.balance = balance;
		this.openingDate = openingDate;
		this.transactions = transactions;
	}

	public Integer getAccountNumber() {
		return accountNumber;
	}

	public EnumTypeAccount getType() {
		return type;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Date getOpeningDate() {
		return openingDate;
	}

	@Override
	public String toString() {
		return "Account Number: " + this.accountNumber +
				"\n\tType: " + this.type + 
				"\n\tDate of Opening : " + this.openingDate +
				"\n\tBalance : " + this.balance;
	}
	
	//ABSTRACT METHODS - NOT IMPLEMENTED IN PARENT CLASS	
	public abstract void deposit (Integer transactionNumber, String description, Date transactionDate, Double amount,
			EnumTypeTransaction type);
	public abstract void withdraw (Integer transactionNumber, String description, Date transactionDate, Double amount,
			EnumTypeTransaction type);
	
}
