package data;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import bus.*;

import bus.SavingAccount;

public class AccountDB {
	static private Connection myConnection;
	static private String mySQLStatement = null;	
	static private String mySQLQuery = null;
	static private Statement myStatemnt = null;
	static private ResultSet myResultSet = null;
	
	public static Integer insertAccount(Account aNewAccount) throws SQLException {
		PreparedStatement myPreparedStatement = null ;
		ResultSet generatedKeys = null;
		String sqlStatement;
		
		sqlStatement = "insert into AccountBank values( ? , ? , ? , ? , ?)  ";
				
		myPreparedStatement = myConnection.prepareStatement(sqlStatement);		
		
		myPreparedStatement.setNull(1, Types.INTEGER);
		myPreparedStatement.setString(2, aNewAccount.getType().toString());
		myPreparedStatement.setInt(3, aNewAccount.getCustomer().getIdentificationNumber());	
		myPreparedStatement.setDouble(4, aNewAccount.getBalance());
		myPreparedStatement.setDate(5, Date.valueOf(aNewAccount.getOpeningDate()));
		
		myPreparedStatement.executeUpdate();	
		
		myConnection.commit();	
		
		generatedKeys = myPreparedStatement.getGeneratedKeys();

	    if (generatedKeys.next()) {
	        int generatedId = generatedKeys.getInt(1);
	        return generatedId;
	    }
		return null;
	}
	
    public static void insert(SavingAccount aNewSavingAccount) throws SQLException 
	{	
    	
    	Integer id = insertAccount(aNewSavingAccount);

	    if (id != null) {
	        PreparedStatement myPreparedStatement = null ;
			String sqlStatement;
			
			sqlStatement = "insert into SavingAccount values( ? , ? , ? , ? , ? , ? , ?, ?)  ";
					
			myPreparedStatement = myConnection.prepareStatement(sqlStatement);		
			
			myPreparedStatement.setInt(1, id);
			myPreparedStatement.setString(2, aNewSavingAccount.getType().toString());
			myPreparedStatement.setInt(3, aNewSavingAccount.getCustomer().getIdentificationNumber());	
			myPreparedStatement.setDouble(4, aNewSavingAccount.getBalance());
			myPreparedStatement.setDate(5, Date.valueOf(aNewSavingAccount.getOpeningDate()));
			myPreparedStatement.setDouble(6, aNewSavingAccount.getInterestRate());
			myPreparedStatement.setDouble(7, aNewSavingAccount.getGain());
			myPreparedStatement.setDate(8, Date.valueOf(aNewSavingAccount.getDueDate()));
			
			myPreparedStatement.executeUpdate();	
			
			myConnection.commit();	
	    }
	}
    
    public static void insert(CheckingAccount aNewCheckingAccount) throws SQLException 
	{	 	
    	Integer id = insertAccount(aNewCheckingAccount);
   
	    if (id != null) {
	        PreparedStatement myPreparedStatement = null ;
			String sqlStatement;
			
			sqlStatement = "insert into CheckingAccount values( ? , ? , ? , ? , ? , ? , ?)  ";
					
			myPreparedStatement = myConnection.prepareStatement(sqlStatement);		
			
			myPreparedStatement.setInt(1, id);
			myPreparedStatement.setString(2, aNewCheckingAccount.getType().toString());
			myPreparedStatement.setInt(3, aNewCheckingAccount.getCustomer().getIdentificationNumber());	
			myPreparedStatement.setDouble(4, aNewCheckingAccount.getBalance());
			myPreparedStatement.setDate(5, Date.valueOf(aNewCheckingAccount.getOpeningDate()));
			myPreparedStatement.setInt(6, aNewCheckingAccount.getMonthlyTransactionLimit());
			myPreparedStatement.setDouble(7, aNewCheckingAccount.getTransactionFees());
			
			myPreparedStatement.executeUpdate();	
			
			myConnection.commit();	
	    }
	}
    
    public static void insert(CurrencyAccount aNewCurrencyAccount) throws SQLException 
	{	 	
    	Integer id = insertAccount(aNewCurrencyAccount);
    	   
	    if (id != null) {
	        
	        PreparedStatement myPreparedStatement = null ;
			String sqlStatement;
			
			sqlStatement = "insert into CurrencyAccount values( ? , ? , ? , ? , ? , ? , ?)  ";
					
			myPreparedStatement = myConnection.prepareStatement(sqlStatement);		
			
			myPreparedStatement.setInt(1, id);
			myPreparedStatement.setString(2, aNewCurrencyAccount.getType().toString());
			myPreparedStatement.setInt(3, aNewCurrencyAccount.getCustomer().getIdentificationNumber());	
			myPreparedStatement.setDouble(4, aNewCurrencyAccount.getBalance());
			myPreparedStatement.setDate(5, Date.valueOf(aNewCurrencyAccount.getOpeningDate()));
			myPreparedStatement.setString(6, aNewCurrencyAccount.getCurrency().toString());
			myPreparedStatement.setDouble(7, aNewCurrencyAccount.getCurrencyRate());
			myPreparedStatement.setDouble(8, aNewCurrencyAccount.getConversionFees());
			
			myPreparedStatement.executeUpdate();	
			
			myConnection.commit();	
	    }
	}
    
    public static void insert(CreditAccount aNewCreditAccount) throws SQLException 
	{	 	
    	Integer id = insertAccount(aNewCreditAccount);
    	   
	    if (id != null) {
	        
	        PreparedStatement myPreparedStatement = null ;
			String sqlStatement;
			
			sqlStatement = "insert into CurrencyAccount values( ? , ? , ? , ? , ? , ? , ?)  ";
					
			myPreparedStatement = myConnection.prepareStatement(sqlStatement);		
			
			myPreparedStatement.setInt(1, id);
			myPreparedStatement.setString(2, aNewCreditAccount.getType().toString());
			myPreparedStatement.setInt(3, aNewCreditAccount.getCustomer().getIdentificationNumber());	
			myPreparedStatement.setDouble(4, aNewCreditAccount.getBalance());
			myPreparedStatement.setDate(5, Date.valueOf(aNewCreditAccount.getOpeningDate()));
			myPreparedStatement.setDate(6, Date.valueOf(aNewCreditAccount.getDueDate()));
			myPreparedStatement.setDouble(7, aNewCreditAccount.getLimit());
			
			myPreparedStatement.executeUpdate();	
			
			myConnection.commit();	
	    }
	}
    
    
    
    
    
    
	public static void updateAccount(Account aChangedAccount) throws SQLException {
		
		myConnection = DBConnection.getConnection();
		
		mySQLStatement = "update AccountBank set balance =  \'"    
				
			              +   aChangedAccount.getBalance() +    "\' WHERE  accountid = "
			                  
			              +  aChangedAccount.getAccountNumber();
	
		myStatemnt = myConnection.createStatement();
		myStatemnt.executeUpdate(mySQLStatement);
		myConnection.commit();								
	}
    
	public static void updateSavingAccount(SavingAccount aChangedAccount) throws SQLException {
		
		myConnection = DBConnection.getConnection();
		
		mySQLStatement = "update SavingAccount set interest_rate =  \'"    
			              +   aChangedAccount.getInterestRate() + ", due_date = \'" 
			              +   aChangedAccount.getDueDate() + "\' WHERE  savingsaccountid = "
			              +  aChangedAccount.getAccountNumber();
	
		myStatemnt = myConnection.createStatement();
		myStatemnt.executeUpdate(mySQLStatement);
		myConnection.commit();								
	}
	
	public static void updateCheckingAccount(CheckingAccount aChangedAccount) throws SQLException {
		
		myConnection = DBConnection.getConnection();
		
		mySQLStatement = "update CheckingAccount set month_trans_limit =  \'"    
			              +   aChangedAccount.getMonthlyTransactionLimit() + ", trans_fee = \'" 
			              +   aChangedAccount.getTransactionFees() + "\' WHERE  checkingaccountid = "
			              +  aChangedAccount.getAccountNumber();
	
		myStatemnt = myConnection.createStatement();
		myStatemnt.executeUpdate(mySQLStatement);
		myConnection.commit();								
	}
    
    
    
    
	
	public static void update(Employee newEmployee) throws SQLException {
		
		myConnection = DBConnection.getConnection();
		
		mySQLStatement = "update Employee set Emp_First_Name =  \'"    
				
			              +   newEmployee.getFirstName()     +    "\' WHERE  Emp_Id = "
			                  
			              +  newEmployee.getId() ;
	
		myStatemnt = myConnection.createStatement();
			
		    myStatemnt.executeUpdate(mySQLStatement);
			
		 myConnection.commit();		
									
	}
	
	/**
	 * 
	 * @param id primary key of a department
	 * @throws SQLException
	 */
	public static void delete(int id ) throws SQLException {
		
		myConnection = DBConnection.getConnection();
		
		mySQLStatement = "Delete FROM Employee WHERE Emp_Id = "  + id  ;
	
			
			myStatemnt = myConnection.createStatement();
			
		   myStatemnt.executeUpdate(mySQLStatement);
			
			myConnection.commit();	

	}
	
	public static Employee search(int id) throws SQLException, SQLException{
		
		myConnection = DBConnection.getConnection();
		
		mySQLQuery = "SELECT * FROM Employee WHERE  Emp_Id = " + id ;
		
		myStatemnt = myConnection.createStatement();
		
		myResultSet = myStatemnt.executeQuery(mySQLQuery);
		
		if(myResultSet.next()) {
			aEmployee = new Employee(Integer.parseInt(myResultSet.getString(1)), myResultSet.getString(2), myResultSet.getString(3), Integer.parseInt(myResultSet.getString(4)));
		}	
		
		return aEmployee;
	}
	
	public static ArrayList<Employee> select() throws SQLException, NumberFormatException, SQLException{
		
		myConnection = DBConnection.getConnection();
		
		mySQLQuery = "SELECT * FROM Employee";
		
		myStatemnt = myConnection.createStatement();
		
		myResultSet = myStatemnt.executeQuery(mySQLQuery);
		
		ArrayList<Employee> myList = new ArrayList<Employee>();
		
		while(myResultSet.next()) {
			
			aEmployee = new Employee(Integer.parseInt(myResultSet.getString(1)), myResultSet.getString(2), myResultSet.getString(3), Integer.parseInt(myResultSet.getString(4)));
			
			myList.add(aEmployee);
		}
		
		return myList;
	}
}
