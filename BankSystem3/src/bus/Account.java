package bus;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import data.AccountDB;
 
public abstract class Account implements ITransaction{
	protected Integer accountNumber;
	protected EnumTypeAccount type;
	protected Integer customer;
	protected Double balance;
	protected LocalDate openingDate;
	protected ArrayList<Transaction> transactions;
	
	public Account() {
		super();
		this.accountNumber = null;
		this.type = EnumTypeAccount.Undefined;
		this.customer = null;
		this.balance = 0.00;
		this.openingDate = null;
		this.transactions = new ArrayList<Transaction>();
	}
	
	public Account(Integer accountNumber, EnumTypeAccount type, Integer customer, Double balance, LocalDate openingDate) throws ExceptionIsNull, ExceptionIsNotANumber {
		super();
		this.accountNumber = accountNumber;
		this.type = type;
		this.customer = customer;
		setBalance(balance);
		this.openingDate = openingDate;
		setTransactions();
	}
	
	public void setAccountNumber(Integer accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Integer getAccountNumber() {
		return this.accountNumber;
	}

	public void setType(EnumTypeAccount type) {
		this.type = type;
	}

	public EnumTypeAccount getType() {
		return this.type;
	}
	
	public void setCustomer(Integer customer) {
		this.customer = customer;
	}
	
	public Integer getCustomer() {
		return this.customer;
	}

	public void setBalance(Double balance) throws ExceptionIsNull, ExceptionIsNotANumber {
		if(Validator.isNull(balance)) {
			throw new ExceptionIsNull();
		}
		if(!Validator.isDouble(balance)) {
			throw new ExceptionIsNotANumber();
		}
		this.balance = balance;
	}

	public Double getBalance() {
		return this.balance;
	}

	public void setOpeningDate(LocalDate openingDate) {
		this.openingDate = openingDate;
	}
	
	public LocalDate getOpeningDate() {
		return this.openingDate;
	}

	public ArrayList<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions() {
		try {
			ArrayList<Transaction> listTransactions = Transaction.searchByAccount(this.accountNumber);
			this.transactions = listTransactions;
		}
		catch (Exception exc) {
			this.transactions = new ArrayList<Transaction>();
		}
	}
	
	@Override
	public String toString() {
		return "Account Number: " + this.accountNumber +
				"\n\tType: " + this.type + 
				"\n\tCustomer: " + this.customer + 
				"\n\tDate of Opening : " + this.openingDate +
				"\n\tBalance : " + this.balance;
	}
		
	//ABSTRACT METHODS - NOT IMPLEMENTED IN PARENT CLASS	
	public abstract void deposit (Double amount) throws ExceptionNegativeAmount, ExceptionWrongAmount, ExceptionLatePayment, ExceptionIsPassedDate, ExceptionIsNotANumber, ExceptionIsNull, SQLException;
	public abstract void withdraw (Double amount) throws ExceptionNotEnoughBalance, ExceptionNegativeAmount, ExceptionIsNull, ExceptionIsNotANumber, SQLException, ExceptionNotEnoughLimit;
	
	
	//////////////////////////////
	//   public static services //
	//////////////////////////////
	public static void add(Account element) throws SQLException {
		AccountDB.insert(element);
	}
	
	public static void update(Account element) throws SQLException {
		AccountDB.update(element);
	}
	
	public static void remove(Integer id) throws SQLException {
		AccountDB.delete(id);
	}
	
	public static Double getBalance(Integer accountId, Integer customerId) throws SQLException {
		return AccountDB.getBalanceById(accountId, customerId);
	}
	
	public static String getType(Integer accountId, Integer customerId) throws SQLException {
		return AccountDB.getTypeById(accountId, customerId);
	}
	
	public static ArrayList<Integer> searchByCustomerId(Integer customerId) throws SQLException {
		return AccountDB.searchByCustomerId(customerId);
	}
	
	public static Customer searchCustomerByAccountId(Integer accountId) throws SQLException, ExceptionIsNotANumber, ExceptionIsNull {
		Integer customerId = AccountDB.searchCustomerByAccountId(accountId);
		return Customer.search(customerId);
	}
}
