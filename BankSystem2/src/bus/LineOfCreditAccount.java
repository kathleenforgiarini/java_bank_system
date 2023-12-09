package bus;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;

import data.AccountDB;
import data.LineOfCreditAccountDB;

public class LineOfCreditAccount extends CreditAccount{

	private Integer lineOfCreditAccountId;
	private Double interestRate; //	TROCAR NO BANCO DE DADOS
	private Integer nbOfInstallments;
	private Double installment;
	
	public LineOfCreditAccount() {
		super();
		this.lineOfCreditAccountId = null;
		this.interestRate = 0.00;
		this.nbOfInstallments = null;
		this.installment = 0.00;
	}

	public LineOfCreditAccount(Integer accountNumber, EnumTypeAccount type, Integer customer, LocalDate openingDate,
			LocalDate dueDate, Double limit, Double interestRate, Integer nbOfInstallments,
			Double installment) throws ExceptionIsNull, ExceptionIsNotANumber, ExceptionIsPassedDate, ExceptionNegativeAmount, ExceptionNotEnoughBalance {
		super(accountNumber, type, customer, 0.00, openingDate, dueDate, limit);
		this.lineOfCreditAccountId = accountNumber;
		this.nbOfInstallments = nbOfInstallments;
		this.installment = installment;
		
		setInterestRate(interestRate);
				
		Period numberOfMonths = Period.between(openingDate.withDayOfMonth(1), dueDate.withDayOfMonth(1));	//NUMBER OF INSTALLMENTS IS THE QUANTITY OF MONTHS FROM THE OPENING DATE OF THE ACCOUNT AND THE DUE DATE
		this.nbOfInstallments = numberOfMonths.getMonths();
		
		
		Double finalDebt = limit * (1 + getInterestRate());													//CALCULATING THE FINAL PRICE THAT THE CUSTOMER HAS TO PAY: THE AMOUNT ASKED PLUS THE INTEREST RATE
		this.installment = finalDebt/this.nbOfInstallments;
		
		//withdraw(openingDate, finalDebt);
	}

	public Integer getLineOfCreditAccountId() {
		return lineOfCreditAccountId;
	}

	public void setLineOfCreditAccountId(Integer lineOfCreditAccountId) {
		this.lineOfCreditAccountId = lineOfCreditAccountId;
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

	public Integer getNbOfInstallments() {
		return nbOfInstallments;
	}

	public void setNbOfInstallments(Integer nbOfInstallments) {
		this.nbOfInstallments = nbOfInstallments;
	}

	public Double getInstallment() {
		return installment;
	}

	public void setInstallment(Double installment) {
		this.installment = installment;
	}
	
	@Override
	public void withdraw(LocalDate transactionDate, Double amount) throws ExceptionNegativeAmount, ExceptionIsNull, ExceptionIsNotANumber, SQLException {
		
		Transaction transaction = new Transaction(null, "Withdraw", transactionDate, amount, this.lineOfCreditAccountId, EnumTypeTransaction.Debit);
		
		this.setBalance(amount*-1);
		Transaction.add(transaction);
	}
	
	
	@Override
	public void deposit(LocalDate transactionDate, Double amount) throws ExceptionNegativeAmount, ExceptionWrongAmount, ExceptionLatePayment, ExceptionIsNotANumber, ExceptionIsNull, SQLException {
		
		if (amount >= this.getInstallment()) {
			if (transactionDate.isBefore(this.dueDate))
			{
				Transaction transaction = new Transaction(null, "Deposit", transactionDate, amount, this.lineOfCreditAccountId, EnumTypeTransaction.Credit);
				
				this.balance += amount;
				Account.update(this);
				this.setNbOfInstallments(this.nbOfInstallments--);
				LineOfCreditAccount.updateNbOfInstallments(this);
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
		return "LineOfCreditAccount Id " + this.lineOfCreditAccountId +
			   "\n\tInterest Rate: " + this.interestRate + 
			   "\n\tInstallments: " + this.nbOfInstallments + 
			   "\n\tLimit = " + this.limit + 
			   "\n\tBalance = " + this.balance;
	}
	
	//////////////////////////////
	//   public static services //
	//////////////////////////////
	public static void add(LineOfCreditAccount element) throws SQLException {
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
	
//	public static LineOfCreditAccount search(Integer id) throws SQLException, ExceptionIsNull, ExceptionIsNotANumber, ExceptionIsPassedDate, ExceptionNegativeAmount, ExceptionNotEnoughBalance {
//		return LineOfCreditAccountDB.search(id);
//	}
//	
//	public static ArrayList<LineOfCreditAccount> getData() throws SQLException, ExceptionIsNull, ExceptionIsNotANumber, ExceptionIsPassedDate {
//		return LineOfCreditAccountDB.select();
//	}
}
