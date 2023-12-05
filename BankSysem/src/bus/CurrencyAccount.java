package bus;

import java.util.Date;

public class CurrencyAccount extends Account {

	protected EnumTypeCurrency currency;
	
	protected Double currencyRate;
	
	protected Double conversionFees;
	
	

	public CurrencyAccount() {
		
		super();
		
		this.currency = EnumTypeCurrency.Undefined;
		
		this.currencyRate = (double) 0;
		
		this.conversionFees = (double) 0;
		
	}
	
	

	public CurrencyAccount(Integer accountNumber, EnumTypeAccount type, Double balance, Date openingDate, TransactionCollection transactions, EnumTypeCurrency currencyType, Double currencyRate, Double conversionFees) {
		super(accountNumber, type, balance, openingDate, transactions);
		
		this.currency = currencyType;
		this.currencyRate = currencyRate;
		this.conversionFees = conversionFees;
	}
	
	
	public EnumTypeCurrency getCurrency() {
		return currency;
	}


	public Double getCurrencyRate() {
		return currencyRate;
	}



	public void setCurrencyRate(Double currencyRate) {
		this.currencyRate = currencyRate;
	}



	public Double getConversionFees() {
		return conversionFees;
	}



	public void setConversionFees(Double conversionFees) {
		this.conversionFees = conversionFees;
	}


	@Override
	public void deposit(Double amount) { //amount in CAN$
		
		double convertedAmount = amount*this.getCurrencyRate();
		double bankFee =  convertedAmount*this.getConversionFees();
		double finalAmount = convertedAmount - bankFee;
		
		this.balance += finalAmount; //balance in another currency
		
	}

	@Override
	public void withdraw(Double amount) { //amount in CAN$???
		
		double convertedAmount = amount*this.getCurrencyRate();
		double bankFee =  convertedAmount*this.getConversionFees();
		double finalAmount = convertedAmount + bankFee;
		
		this.balance -= finalAmount; //balance in another currency
		
	}


	@Override
	public String toString() {
		return "Account Number: " + this.accountNumber +
				"\n\tType: " + this.type + 
				"\n\tCurrency : " + this.currency +
				"\n\tCurrency Rate : " + this.currencyRate +
				"\n\tConversion Fee : " + this.conversionFees +
				"\n\tDate of Opening : " + this.openingDate +
				"\n\tBalance : " + this.balance;
	}
	
	

}
