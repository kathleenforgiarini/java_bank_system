package data;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import bus.*;

public class CreditAccountDB {
	static private Connection myConnection;
	static private String mySQLStatement = null;	
	static private String mySQLQuery = null;
	
	public static Integer insert(CreditAccount aNewCreditAccount) throws SQLException 
	{	 	
    	Integer id = AccountDB.insert(aNewCreditAccount);
    	myConnection = DBConnection.getConnection();
    	   
	    if (id != null) {
	        
	        PreparedStatement myPreparedStatement = null ;
			String sqlStatement;
			
			sqlStatement = "insert into CreditAccount values(? , ? , ?)";
					
			myPreparedStatement = myConnection.prepareStatement(sqlStatement);		
			
			myPreparedStatement.setInt(1, id);
			myPreparedStatement.setDate(2, Date.valueOf(aNewCreditAccount.getDueDate()));
			myPreparedStatement.setDouble(3, aNewCreditAccount.getLimit());
			
			myPreparedStatement.executeUpdate();	
			
			myConnection.commit();	
	    }
	    myConnection.close();
	    return id;
	}
	
	public static void updateLimit(CreditAccount aChangedAccount) throws SQLException {
		
		myConnection = DBConnection.getConnection();
		
		mySQLStatement = "update CreditAccount set limit = " 
			              +   aChangedAccount.getLimit() + " WHERE creditaccountid = "
			              +   aChangedAccount.getAccountNumber();
	
		Statement myStatemnt = myConnection.createStatement();
		myStatemnt.executeUpdate(mySQLStatement);
		myConnection.commit();	
		myConnection.close();
	}
	
	public static void updateDueDate(CreditAccount aChangedAccount) throws SQLException {
		
		myConnection = DBConnection.getConnection();
		
		mySQLStatement = "update CreditAccount set due_date = "    
			              +   Date.valueOf(aChangedAccount.getDueDate()) + " WHERE creditaccountid = "
			              +   aChangedAccount.getAccountNumber();
	
		Statement myStatemnt = myConnection.createStatement();
		myStatemnt.executeUpdate(mySQLStatement);
		myConnection.commit();		
		myConnection.close();
	}
	
	public static CreditAccount search(Integer id) throws SQLException, ExceptionIsNull, ExceptionIsNotANumber, ExceptionIsPassedDate{
		
		CreditAccount aCreditAccount = null;
		
		myConnection = DBConnection.getConnection();
		
		mySQLQuery = "SELECT a.accountid, a.customerid, a.balance, a.openingdate, a.typeaccount, c.duedate, c.limit"
					+ "FROM accountbank a "
					+ "JOIN creditaccount c ON a.accountid = c.creditaccountid "
					+ "WHERE a.accountid = " + id ;
		
		Statement myStatemnt = myConnection.createStatement();
		
		ResultSet myResultSet = myStatemnt.executeQuery(mySQLQuery);
		
		if(myResultSet.next()) {
			Integer accountid = myResultSet.getInt("accountid");
			EnumTypeAccount type = EnumTypeAccount.valueOf(myResultSet.getString("typeaccount"));
			Integer customerid = myResultSet.getInt("customerid");		
            Double balance = myResultSet.getDouble("balance");
            LocalDate openingDate = myResultSet.getDate("openingdate").toLocalDate();
            LocalDate dueDate = myResultSet.getDate("duedate").toLocalDate();
            Double limit = myResultSet.getDouble("limit");

            aCreditAccount = new CreditAccount(accountid, type, customerid, balance, openingDate, dueDate, limit);
		}	
		myConnection.close();
		return aCreditAccount;
	}
	
	public static ArrayList<CreditAccount> searchByCustomer(Integer customerId) throws SQLException, ExceptionIsNull, ExceptionIsNotANumber, ExceptionIsPassedDate{
		
		CreditAccount aCreditAccount = null;
		
		myConnection = DBConnection.getConnection();
		
		mySQLQuery = "SELECT a.accountid, a.customerid, a.balance, a.openingdate, a.typeaccount, c.duedate, c.limit"
					+ "FROM accountbank a "
					+ "JOIN creditaccount c ON a.accountid = c.creditaccountid "
					+ "WHERE a.customerid = " + customerId ;
		
		Statement myStatemnt = myConnection.createStatement();
		
		ResultSet myResultSet = myStatemnt.executeQuery(mySQLQuery);
		
		ArrayList<CreditAccount> myList = new ArrayList<CreditAccount>();
		
		while(myResultSet.next()) {
			Integer accountid = myResultSet.getInt("accountid");
			EnumTypeAccount type = EnumTypeAccount.valueOf(myResultSet.getString("typeaccount"));
			Integer customerid = myResultSet.getInt("customerid");		
            Double balance = myResultSet.getDouble("balance");
            LocalDate openingDate = myResultSet.getDate("openingdate").toLocalDate();
            LocalDate dueDate = myResultSet.getDate("duedate").toLocalDate();
            Double limit = myResultSet.getDouble("limit");

            aCreditAccount = new CreditAccount(accountid, type, customerid, balance, openingDate, dueDate, limit);
            
            myList.add(aCreditAccount);
		}	
		myConnection.close();
		return myList;
	}
	

	public static CreditAccount searchByIdAndCustomer(Integer id, Integer customer) throws SQLException, ExceptionIsNull, ExceptionIsNotANumber, ExceptionIsPassedDate {
		CreditAccount aCreditAccount = null;
		
		myConnection = DBConnection.getConnection();
		
		mySQLQuery = "SELECT a.accountid, a.customerid, a.balance, a.openingdate, a.typeaccount, c.duedate, c.limit"
					+ "FROM accountbank a "
					+ "JOIN creditaccount c ON a.accountid = c.creditaccountid "
					+ "WHERE a.accountid = " + id + " AND a.customerid = " + customer;
		
		Statement myStatemnt = myConnection.createStatement();
		
		ResultSet myResultSet = myStatemnt.executeQuery(mySQLQuery);
		
		if(myResultSet.next()) {
			Integer accountid = myResultSet.getInt("accountid");
			EnumTypeAccount type = EnumTypeAccount.valueOf(myResultSet.getString("typeaccount"));
			Integer customerid = myResultSet.getInt("customerid");		
            Double balance = myResultSet.getDouble("balance");
            LocalDate openingDate = myResultSet.getDate("openingdate").toLocalDate();
            LocalDate dueDate = myResultSet.getDate("duedate").toLocalDate();
            Double limit = myResultSet.getDouble("limit");

            aCreditAccount = new CreditAccount(accountid, type, customerid, balance, openingDate, dueDate, limit);
		}	
		myConnection.close();
		return aCreditAccount;
	}
	
	public static ArrayList<CreditAccount> select() throws SQLException, ExceptionIsNull, ExceptionIsNotANumber, ExceptionIsPassedDate{
		
		CreditAccount aCreditAccount = null;
		myConnection = DBConnection.getConnection();
		
		mySQLQuery = "SELECT a.accountid, a.customerid, a.balance, a.openingdate, a.typeaccount, c.duedate, c.limit"
				+ "FROM accountbank a "
				+ "JOIN creditaccount c ON a.accountid = c.creditaccountid ";
		
		Statement myStatemnt = myConnection.createStatement();
		
		ResultSet myResultSet = myStatemnt.executeQuery(mySQLQuery);
		
		ArrayList<CreditAccount> myList = new ArrayList<CreditAccount>();
		
		while(myResultSet.next()) {
			Integer accountid = myResultSet.getInt("accountid");
			EnumTypeAccount type = EnumTypeAccount.valueOf(myResultSet.getString("typeaccount"));
			Integer customerid = myResultSet.getInt("customerid");		
            Double balance = myResultSet.getDouble("balance");
            LocalDate openingDate = myResultSet.getDate("openingdate").toLocalDate();
            LocalDate dueDate = myResultSet.getDate("duedate").toLocalDate();
            Double limit = myResultSet.getDouble("limit");

            aCreditAccount = new CreditAccount(accountid, type, customerid, balance, openingDate, dueDate, limit);
	
			myList.add(aCreditAccount);
		}
		myConnection.close();
		return myList;
	}

}
