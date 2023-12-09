package bus;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import data.TransactionDB;

public class Transaction {
	private Integer transactionId;
	private String description;
	private LocalDate transactionDate;
	private Double amount;
	private Integer accountId;
	private EnumTypeTransaction type;
	
	public Transaction() {
		super();
		this.transactionId = null;
		this.description = "";
		this.transactionDate = null;
		this.amount = (double) 0;
		this.accountId = null;
		this.type = EnumTypeTransaction.Undefined;
	}
	
	public Transaction(Integer transactionId, String description, LocalDate transactionDate, Double amount, Integer account,
			EnumTypeTransaction type) throws ExceptionNegativeAmount, ExceptionIsNotANumber, ExceptionIsNull {
		super();
		this.transactionId = transactionId;
		this.description = description;
		this.transactionDate = transactionDate;
		setAmount(amount);
		this.accountId = account;
		this.type = type;
	}
	
	public void setTransactionId(Integer transactionId) {
		this.transactionId = transactionId;
	}
	public Integer getTransactionId() {
		return transactionId;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDescription() {
		return description;
	}
	
	public void setTransactionDate(LocalDate transactionDate) {
		this.transactionDate = transactionDate;
	}
	public LocalDate getTransactionDate() {
		return transactionDate;
	}
	
	public void setAmount(Double amount) throws ExceptionNegativeAmount, ExceptionIsNotANumber, ExceptionIsNull {
		boolean notValidAmount = false;
		boolean isDouble = false;
		boolean isNull = false;
		
		notValidAmount = Validator.isNegativeOrZero(amount);
		isDouble = Validator.isDouble(amount);
		isNull = Validator.isNull(amount);
		
		if (notValidAmount)
		{
			throw new ExceptionNegativeAmount();
		}
		
		if (!isDouble) {
			throw new ExceptionIsNotANumber();
		}
		if (isNull) {
			throw new ExceptionIsNull();
		}
		
		this.amount = amount;
	}
	public Double getAmount() {
		return amount;
	}
	
	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}
	public Integer getAccountId() {
		return accountId;
	}

	public void setType(EnumTypeTransaction type) {
		this.type = type;
	}
	public EnumTypeTransaction getType() {
		return type;
	}
		
	//Overridden Methods
	
	@Override
	public String toString() {
		return "Transaction ID: " + this.transactionId + 
				"\n\tDescription: " + this.description + ", on " + this.transactionDate +
				"\n\tAmount: " + this.amount + ", Account: " + this.accountId + 
				"\n\t, Type: " + this.type;
	}
	
	//////////////////////////////
	//   public static services //
	//////////////////////////////
	public static void add(Transaction element) throws SQLException {
		TransactionDB.insert(element);
	}
	
	public static void update(Transaction element) throws SQLException {
		TransactionDB.update(element);
	}
	
	public static void remove(Integer id) throws SQLException {
		TransactionDB.delete(id);
	}
	
	public static Transaction search(Integer id) throws SQLException, ExceptionNegativeAmount, ExceptionIsNotANumber, ExceptionIsNull {
		return TransactionDB.search(id);
	}
	
	public static ArrayList<Transaction> getData() throws SQLException, ExceptionNegativeAmount, ExceptionIsNotANumber, ExceptionIsNull {
		return TransactionDB.select();
	}
}
