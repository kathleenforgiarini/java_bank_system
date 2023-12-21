package bus;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import data.AccountDB;
import data.CheckingAccountDB;
import data.CreditAccountDB;
import data.CurrencyAccountDB;
import data.CustomerDB;
import data.ManagerDB;
import data.SavingAccountDB;
import data.UserDB;

public class Manager extends User {
	private Integer id;

	public Manager() {
		super();
		this.id = null;
	}
	
	public Manager(Integer id, String userName, Integer password) {
		super(id, userName, password);
		this.id = id;
		
	}
	
	public static void openSavingAccount(Integer customer, Double balance, Double interestRate, LocalDate dueDate) throws ExceptionIsNull, ExceptionIsNotANumber, ExceptionIsPassedDate, ClassNotFoundException, IOException, SQLException {
		SavingAccount savingAccount = new SavingAccount(null, EnumTypeAccount.SavingAccount, customer, balance, LocalDate.now(), 
				interestRate, dueDate);
		
		SavingAccount.add(savingAccount);
	}
	
	public static void openCheckingAccount(Integer customer, Double balance, Integer monthlyTransactionLimit, Double transactionFees) throws ExceptionIsNull, ExceptionIsNotANumber, ExceptionIsPassedDate, ClassNotFoundException, IOException, SQLException {
		CheckingAccount checkingAccount = new CheckingAccount(null, EnumTypeAccount.CheckingAccount, customer, balance, LocalDate.now(), 
				monthlyTransactionLimit, transactionFees);
		
		CheckingAccount.add(checkingAccount);
	}
	
	public static void openCurrencyAccount(Integer customer, Double balance, EnumTypeCurrency currency, Double currencyRate, Double conversionFees) throws ExceptionIsNull, ExceptionIsNotANumber, ExceptionIsPassedDate, ClassNotFoundException, IOException, SQLException {
		CurrencyAccount currencyAccount = new CurrencyAccount(null, EnumTypeAccount.CurrencyAccount, customer, balance, LocalDate.now(), 
				currency, currencyRate, conversionFees);
		
		CurrencyAccount.add(currencyAccount);
	}
	
	public static void openCreditAccount(Integer customer, Double balance, LocalDate dueDate, Double limit) throws ExceptionIsNull, ExceptionIsNotANumber, ExceptionIsPassedDate, ClassNotFoundException, IOException, SQLException {
		CreditAccount creditAccount = new CreditAccount(null, EnumTypeAccount.CreditAccount, customer, balance, LocalDate.now(), 
				dueDate, limit);
		
		CreditAccount.add(creditAccount);
	}
	
	public static void openLineOfCreditAccount(Integer customer, LocalDate dueDate, Double limit, Double interestRate) throws ExceptionIsNull, ExceptionIsNotANumber, ExceptionIsPassedDate, ExceptionNegativeAmount, ClassNotFoundException, IOException, ExceptionNotEnoughBalance, SQLException {
		LineOfCreditAccount newAccount = new LineOfCreditAccount(null, EnumTypeAccount.LineOfCreditAccount, customer, 0.00, LocalDate.now(), 
				dueDate, limit, interestRate);
		
		LineOfCreditAccount.add(newAccount);
	}

	public static void closeAccount(Integer accountNumber) throws SQLException {
		if (accountNumber != null) {
			AccountDB.delete(accountNumber);
		}
	}
	
	public static void createCustomer(String username, Integer password, Double salary, Integer mgr) throws SQLException, ExceptionIsNotANumber, ExceptionIsNull {
		Customer newCustomer = new Customer(null, username, password, salary, mgr);
		Integer addedId = Customer.add(newCustomer);
		CheckingAccount newCheckingAccount = new CheckingAccount((Integer)null, EnumTypeAccount.CheckingAccount, addedId, 0.00, LocalDate.now(), 3, 5.00);
		CheckingAccount.add(newCheckingAccount);
	}
	
	public static void removeCustomer(Integer customer) throws SQLException {
        if (customer != null) {
            UserDB.delete(customer);
        }
	}

	@Override
	public String toString() {
		return super.toString() + "\n\tManager id: " + this.id;
	}
	
	//////////////////////////////
	//   public static services //
	//////////////////////////////
	public static void add(Manager element) throws SQLException {
		ManagerDB.insert(element);
	}
	
	public static void update(Manager element) throws SQLException {
		UserDB.update(element);
	}
	
	public static void remove(Integer id) throws SQLException {
		UserDB.delete(id);
	}
	
	public static Manager search(Integer id) throws SQLException, ExceptionIsNotANumber, ExceptionIsNull {
		return ManagerDB.search(id);
	}
	
	public static ArrayList<Manager> getData() throws SQLException, ExceptionIsNotANumber, ExceptionIsNull {
		return ManagerDB.select();
	}
}
