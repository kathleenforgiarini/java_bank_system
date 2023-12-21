package bus;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import data.AccountDB;
import data.CurrencyAccountDB;

public class CurrencyAccount extends Account {
	private Integer accountNumber;
	private EnumTypeCurrency currency;
	private Double currencyRate;
	private Double conversionFees; //percentage fee for each
	
	
	public CurrencyAccount() {
		super();
		this.accountNumber = null;
		this.currency = EnumTypeCurrency.UND;
		this.currencyRate = 0.00;
		this.conversionFees = 0.00;
	}
	
	public CurrencyAccount(Integer accountNumber, EnumTypeAccount type, Integer customer, Double balance, LocalDate openingDate,
			EnumTypeCurrency currency, double currencyRate, double conversionFees) throws ExceptionIsNull, ExceptionIsNotANumber {
		super(accountNumber, type, customer, balance, openingDate);
		setAccountNumber(accountNumber);
		this.currency = currency;
		this.currencyRate = currencyRate;
		this.conversionFees = conversionFees / 100;
	}

	public void setAccountNumber(Integer accountNumber) {
		this.accountNumber = accountNumber;
	}
	public Integer getAccountNumber() {
		return accountNumber;
	}
	
	public void setCurrency(EnumTypeCurrency currency) {
		this.currency = currency;
	}
	public EnumTypeCurrency getCurrency() {
		return currency;
	}
	
	public void setCurrencyRate(Double currencyRate) throws ExceptionIsNotANumber, ExceptionIsNull {
		if (!Validator.isDouble(currencyRate)) {
			throw new ExceptionIsNotANumber();
		}
		
		if (Validator.isNull(currencyRate)) {
			throw new ExceptionIsNull();
		}
		this.currencyRate = currencyRate;
	}
	public Double getCurrencyRate() {
		return currencyRate;
	}
	
	public void setConversionFees(Double conversionFees) throws ExceptionIsNotANumber, ExceptionIsNull {
		if (!Validator.isDouble(conversionFees)) {
			throw new ExceptionIsNotANumber();
		}
		
		if (Validator.isNull(conversionFees)) {
			throw new ExceptionIsNull();
		}
		this.conversionFees = conversionFees / 100;
	}
	public double getConversionFees() {
		return conversionFees;
	}

	@Override
	public void deposit(Double amount) throws ExceptionNegativeAmount, ExceptionIsNotANumber, ExceptionIsNull, SQLException {
		
        double convertedAmount = amount * this.currencyRate;
        double conversionFee = convertedAmount * this.conversionFees;
       
        Transaction transactionDep = new Transaction(null, "Deposit", LocalDate.now(), convertedAmount, this.accountNumber, EnumTypeTransaction.Credit);
        Transaction transactionFee = new Transaction(null, "Fee for transaction", LocalDate.now(), conversionFee, this.accountNumber, EnumTypeTransaction.Debit);
            
        this.balance += convertedAmount;
        Account.update(this);
        Transaction.add(transactionDep);
          
        this.balance -= conversionFee;
        Account.update(this);
        Transaction.add(transactionFee);
	}

	@Override
	public void withdraw(Double amount) throws ExceptionNegativeAmount, ExceptionNotEnoughBalance, ExceptionIsNotANumber, ExceptionIsNull, SQLException {

		if (amount <= this.balance) {
			Transaction transaction = new Transaction(null, "Withdraw", LocalDate.now(), amount, this.accountNumber, EnumTypeTransaction.Debit);
			
			this.balance -= amount;
			Account.update(this);
			Transaction.add(transaction);
        }
		else {
			throw new ExceptionNotEnoughBalance();
		}
	}

	@Override
	public String toString() {
		return super.toString() + "\n\tCurrency: " + currency + "\n\tCurrency Rate: " + currencyRate + 
								  "\n\tConversion Fees: " + conversionFees;
	}
	
	//////////////////////////////
	//   public static services //
	//////////////////////////////
	public static void add(CurrencyAccount element) throws SQLException {
		CurrencyAccountDB.insert(element);
	}
	
	public static void updateCurrencyRate(CurrencyAccount element) throws SQLException {
		CurrencyAccountDB.updateCurrencyRate(element);
	}
	
	public static void updateConversionFees(CurrencyAccount element) throws SQLException {
		CurrencyAccountDB.updateConversionFees(element);
	}
	
	public static void remove(Integer id) throws SQLException {
		AccountDB.delete(id);
	}
	
	public static CurrencyAccount search(Integer id) throws SQLException, ExceptionIsNull, ExceptionIsNotANumber, ExceptionIsPassedDate {
		return CurrencyAccountDB.search(id);
	}
	
	public static ArrayList<CurrencyAccount> searchByCustomer(Integer customer) throws SQLException, ExceptionIsNull, ExceptionIsNotANumber, ExceptionIsPassedDate {
		return CurrencyAccountDB.searchByCustomer(customer);
	}
	
	public static CurrencyAccount searchByIdAndCustomer(Integer id, Integer customer) throws SQLException, ExceptionIsNull, ExceptionIsNotANumber, ExceptionIsPassedDate {
		return CurrencyAccountDB.searchByIdAndCustomer(id, customer);
	}
	
	public static ArrayList<CurrencyAccount> getData() throws SQLException, ExceptionIsNull, ExceptionIsNotANumber, ExceptionIsPassedDate {
		return CurrencyAccountDB.select();
	}

}