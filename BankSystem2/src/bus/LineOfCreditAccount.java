package bus;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

import data.AccountDB;
import data.CurrencyAccountDB;
import data.LineOfCreditAccountDB;

public class LineOfCreditAccount extends CreditAccount{

	private Integer accountNumber;
	private Double interestRate;
	private Integer nbOfInstallments;
	private Double installment;
	
	public LineOfCreditAccount() {
		super();
		this.accountNumber = null;
		this.interestRate = 0.00;
		this.nbOfInstallments = null;
		this.installment = 0.00;
	}

	public LineOfCreditAccount(Integer accountNumber, EnumTypeAccount type, Integer customer, Double balance, LocalDate openingDate,
			LocalDate dueDate, Double limit, Double interestRate) throws ExceptionIsNull, ExceptionIsNotANumber, ExceptionIsPassedDate, ExceptionNegativeAmount, ExceptionNotEnoughBalance, SQLException {
		
		super(accountNumber, type, customer, balance, openingDate, dueDate, limit);
		setAccountNumber(accountNumber);
		setInterestRate(interestRate);
		
	}

	public Integer getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Integer lineOfCreditAccountId) {
		this.accountNumber = lineOfCreditAccountId;
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
		this.interestRate = interestRate/100;
	}

	public Integer getNbOfInstallments() {
		return nbOfInstallments;
	}

	public void setNbOfInstallments(Integer nbOfInstallments) throws SQLException {
		this.nbOfInstallments = nbOfInstallments;
		updateNbOfInstallments(this);
	}

	public Double getInstallment() {
		return installment;
	}

	public void setInstallment(Double installment) throws SQLException {
		this.installment = installment;
		updateInstallment(this);
	}
	
	@Override
	public void withdraw(Double amount) throws ExceptionNegativeAmount, ExceptionIsNull, ExceptionIsNotANumber, SQLException {
		
		Transaction transaction = new Transaction(null, "Withdraw", LocalDate.now(), amount, this.accountNumber, EnumTypeTransaction.Debit);
		
		this.setBalance(amount*-1);
		Account.update(this);
		Transaction.add(transaction);
	}
	
	
	@Override
	public void deposit(Double amount) throws ExceptionNegativeAmount, ExceptionWrongAmount, ExceptionLatePayment, ExceptionIsNotANumber, ExceptionIsNull, SQLException {
		
		if (amount >= this.getInstallment()) {
			if (LocalDate.now().isBefore(this.dueDate))
			{
				Transaction transaction = new Transaction(null, "Deposit", LocalDate.now(), amount, this.accountNumber, EnumTypeTransaction.Credit);
				
				this.balance = this.getBalance() + amount;
				Account.update(this);
				this.setNbOfInstallments(this.nbOfInstallments - 1);
				updateNbOfInstallments(this);
				
				this.setInstallment(this.getBalance()/this.getNbOfInstallments());
				
				Transaction.add(transaction);
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
		return "LineOfCreditAccount Id " + this.accountNumber +
			   "\n\tInterest Rate: " + this.interestRate + 
			   "\n\tInstallments: " + this.nbOfInstallments + 
			   "\n\tLimit = " + this.limit + 
			   "\n\tBalance = " + this.balance;
	}
	
	//////////////////////////////
	//   public static services //
	//////////////////////////////
	public static void add(LineOfCreditAccount element) throws SQLException, ExceptionIsNull, ExceptionIsNotANumber, ExceptionIsPassedDate, ExceptionNegativeAmount, ExceptionNotEnoughBalance {
		LineOfCreditAccountDB.insert(element);
	}
	
	public static void updateInterestRate(LineOfCreditAccount element) throws SQLException {
		LineOfCreditAccountDB.updateInterestRate(element);
	}
	
	public static void updateNbOfInstallments(LineOfCreditAccount element) throws SQLException {
		LineOfCreditAccountDB.updateNbOfInstallments(element);
	}
	
	public static void updateInstallment(LineOfCreditAccount element) throws SQLException {
		LineOfCreditAccountDB.updateInstallment(element);
	}
	
	public static void remove(Integer id) throws SQLException {
		AccountDB.delete(id);
	}
	
	public static LineOfCreditAccount searchByIdAndCustomer(Integer id, Integer customer) throws SQLException, ExceptionIsNull, ExceptionIsNotANumber, ExceptionIsPassedDate, ExceptionNegativeAmount, ExceptionNotEnoughBalance {
		return LineOfCreditAccountDB.searchByIdAndCustomer(id, customer);
	}
	
	public static LineOfCreditAccount search(Integer id) throws SQLException, ExceptionIsNull, ExceptionIsNotANumber, ExceptionIsPassedDate, ExceptionNegativeAmount, ExceptionNotEnoughBalance {
		return LineOfCreditAccountDB.search(id);
	}
}
