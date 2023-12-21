package bus;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import data.AccountDB;
import data.SavingAccountDB;

public class SavingAccount extends Account {
	private Integer accountNumber;
	private Double interestRate;	// in percentage
	private Double gain;
	private LocalDate dueDate;
	
	public SavingAccount() {
		super();
		this.accountNumber = null;
		this.interestRate = 0.00;
		this.gain = 0.00;
		this.dueDate = null;
	}

	public SavingAccount(Integer accountNumber, EnumTypeAccount type, Integer customer, Double balance, LocalDate openingDate, Double interestRate, LocalDate dueDate) throws ExceptionIsNull, ExceptionIsNotANumber, ExceptionIsPassedDate, SQLException {
		super(accountNumber, type, customer, balance, openingDate);
		setAccountNumber(accountNumber);
		setInterestRate(interestRate);
		setDueDate(dueDate);		
		setGain();
	}

	public void setAccountNumber(Integer accountNumber) {
		this.accountNumber = accountNumber;
	}
	public Integer getAccountNumber() {
		return accountNumber;
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
	public double getInterestRate() {
		return interestRate;
	}

	public void setGain() throws SQLException {
		this.gain = this.balance * this.interestRate;
		//Account.update(this);
		updateGain(this);
		
	}
	public Double getGain() {
		return gain;
	}
	public void calcGain() throws SQLException {
		this.balance += getGain();
		Account.update(this);
		updateGain(this);
	}
	
	public void setDueDate(LocalDate dueDate) throws ExceptionIsPassedDate {
//		LocalDate now = LocalDate.now();
//		if (dueDate.isBefore(now)) {
//			throw new ExceptionIsPassedDate();
//		}
		this.dueDate = dueDate;
	}
	public LocalDate getDueDate() {
		return dueDate;
	}

	@Override
	public void deposit(Double amount) throws ExceptionNegativeAmount, ExceptionIsNotANumber, ExceptionIsNull, SQLException {
		
		Transaction transaction = new Transaction(null, "Deposit", LocalDate.now(), amount, this.accountNumber,
        		EnumTypeTransaction.Credit);    
        
		this.balance += amount;
		Account.update(this);
		Transaction.add(transaction);
		setGain();
	}

	@Override
	public void withdraw(Double amount) throws ExceptionNegativeAmount, ExceptionNotEnoughBalance, ExceptionIsNotANumber, ExceptionIsNull, SQLException {

		if (LocalDate.now().isBefore(this.dueDate)) {
			
			if (amount <= this.balance) {
				
			Transaction transaction = new Transaction(null, "Withdraw", LocalDate.now(), amount, this.accountNumber,
            		EnumTypeTransaction.Debit);
			
			this.balance -= amount;
			Account.update(this);
			Transaction.add(transaction);
			setGain();
			}
			else {
				throw new ExceptionNotEnoughBalance();
			}
		}
		else {
			if (amount <= this.balance + this.gain) {
				if (amount == this.balance + this.gain) {
					calcGain();
					
					Transaction transactionInterest = new Transaction(null, "Withdraw", LocalDate.now(), amount, this.accountNumber,
		            		EnumTypeTransaction.Debit);
					
					this.balance -= amount;
					Account.update(this);
					Transaction.add(transactionInterest);
					setGain();
				}
				else
				{
					Double funds = this.balance + this.gain;
					throw new ExceptionNotEnoughBalance("You must withdraw all your funds : " + funds);
				}
			}
			else {
				throw new ExceptionNotEnoughBalance();
			}
		}
	}

	@Override
	public String toString() {
		return super.toString() + "\n\tAnnual Interest Rate: " + interestRate + 
								  "\n\tAnnual Gain: " + gain;
	}
	
	//////////////////////////////
	//   public static services //
	//////////////////////////////
	public static void add(SavingAccount element) throws SQLException {
		SavingAccountDB.insert(element);
	}
	
	public static void updateInterestRate(SavingAccount element) throws SQLException {
		SavingAccountDB.updateInterestRate(element);
	}
	
	public static void updateGain(SavingAccount element) throws SQLException {
		SavingAccountDB.updateGain(element);
	}
	
	public static void updateDueDate(SavingAccount element) throws SQLException {
		SavingAccountDB.updateDueDate(element);
	}
	
	public static void remove(Integer id) throws SQLException {
		AccountDB.delete(id);
	}
	
	public static SavingAccount search(Integer id) throws SQLException, ExceptionIsNull, ExceptionIsNotANumber, ExceptionIsPassedDate {
		return SavingAccountDB.search(id);
	}
	
	public static ArrayList<SavingAccount> searchByCustomer(Integer customer) throws SQLException, ExceptionIsNull, ExceptionIsNotANumber, ExceptionIsPassedDate {
		return SavingAccountDB.searchByCustomer(customer);
	}
	
	public static SavingAccount searchByIdAndCustomer(Integer id, Integer customer) throws SQLException, ExceptionIsNull, ExceptionIsNotANumber, ExceptionIsPassedDate {
		return SavingAccountDB.searchByIdAndCustomer(id, customer);
	}

	public static ArrayList<SavingAccount> getData() throws SQLException, ExceptionIsNull, ExceptionIsNotANumber, ExceptionIsPassedDate {
		return SavingAccountDB.select();
	}
}
