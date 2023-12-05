package bus;

import java.time.LocalDate;

public class Transaction {
	private Integer counter = 1;
	private Integer transactionNumber;
	private String description;
	private LocalDate transactionDate;
	private Double amount;
	private EnumTypeTransaction type;
	
	public Transaction() {
		super();
		this.transactionNumber = counter++;
		this.description = "";
		this.transactionDate = null;
		this.amount = (double) 0;
		this.type = EnumTypeTransaction.Undefined;
	}
	
	public Transaction(Integer transactionNumber, String description, LocalDate transactionDate, Double amount,
			EnumTypeTransaction type) {
		super();
		setTransactionNumber(transactionNumber);
		this.description = description;
		this.transactionDate = transactionDate;
		this.amount = amount;
		this.type = type;
	}

	public Integer getTransactionNumber() {
		return transactionNumber;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public LocalDate getTransactionDate() {
		return transactionDate;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	public void setTransactionNumber(Integer transactionNumber) {
		if (transactionNumber == null) {
			this.transactionNumber = counter++;
		} else {
			this.transactionNumber = transactionNumber;
		}
	}

	public EnumTypeTransaction getType() {
		return type;
	}
		
	//Overridden Methods
	
	@Override
	public String toString() {
		return "Transaction ID: " + this.transactionNumber + 
				"\n\tDescription: " + this.description + ", on " + this.transactionDate +
				"\n\tAmount: CAD$ " + this.amount + " , type " + this.type;
	}
}
