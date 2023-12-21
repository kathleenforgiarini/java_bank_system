package data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

import bus.*;

public class LineOfCreditAccountDB {
	static private Connection myConnection;
	static private String mySQLStatement = null;	
	static private String mySQLQuery = null;
	
	public static void insert(LineOfCreditAccount aNewLineOfCreditAccount) throws SQLException, ExceptionIsNull, ExceptionIsNotANumber, ExceptionIsPassedDate, ExceptionNegativeAmount, ExceptionNotEnoughBalance 
	{	 	
		Integer id = CreditAccountDB.insert(aNewLineOfCreditAccount);

    	myConnection = DBConnection.getConnection();
    	   
	    if (id != null) {
	        
	        PreparedStatement myPreparedStatement = null ;
			String sqlStatement;
			
			sqlStatement = "insert into LineOfCreditAccount (lineofcreditaccountid, interest_rate) values(? , ?)";
					
			myPreparedStatement = myConnection.prepareStatement(sqlStatement);		
			
			myPreparedStatement.setInt(1, id);
			myPreparedStatement.setDouble(2, aNewLineOfCreditAccount.getInterestRate());
			
			myPreparedStatement.executeUpdate();	
			
			myConnection.commit();
			
			Period period = Period.between(aNewLineOfCreditAccount.getOpeningDate(), aNewLineOfCreditAccount.getDueDate());

			long monthsLong = period.toTotalMonths();
			Integer monthsInt = (int) monthsLong;
			
			Double finalDebt = aNewLineOfCreditAccount.getLimit() * (1 + aNewLineOfCreditAccount.getInterestRate());	

			aNewLineOfCreditAccount.setAccountNumber(id);
			aNewLineOfCreditAccount.setNbOfInstallments(monthsInt);
			aNewLineOfCreditAccount.setInstallment(finalDebt/aNewLineOfCreditAccount.getNbOfInstallments());
			
			aNewLineOfCreditAccount.withdraw(finalDebt);
			
	    }
	    myConnection.close();
	}
	
	public static void updateInterestRate(LineOfCreditAccount aChangedLineOfCreditAccount) throws SQLException {
		
		myConnection = DBConnection.getConnection();
		
		mySQLStatement = "update LineOfCreditAccount set interest_rate = "    
			              +   aChangedLineOfCreditAccount.getInterestRate()+ " WHERE LINEOFCREDITACCOUNTID = "
			              +   aChangedLineOfCreditAccount.getAccountNumber();
	
		Statement myStatemnt = myConnection.createStatement();
		myStatemnt.executeUpdate(mySQLStatement);
		myConnection.commit();	
		myConnection.close();
	}
	
	public static void updateNbOfInstallments(LineOfCreditAccount aChangedLineOfCreditAccount) throws SQLException {
		
		myConnection = DBConnection.getConnection();
		
		mySQLStatement = "update LineOfCreditAccount set NBOFINSTALL = " 
			              +   aChangedLineOfCreditAccount.getNbOfInstallments() + " WHERE LINEOFCREDITACCOUNTID = "
			              +   aChangedLineOfCreditAccount.getAccountNumber();
	
		Statement myStatemnt = myConnection.createStatement();
		myStatemnt.executeUpdate(mySQLStatement);
		myConnection.commit();	
		myConnection.close();
	}
	
	public static void updateInstallment(LineOfCreditAccount aChangedLineOfCreditAccount) throws SQLException {
		
		myConnection = DBConnection.getConnection();
		
		mySQLStatement = "update LineOfCreditAccount set installment = " 
			              +   aChangedLineOfCreditAccount.getInstallment() + " WHERE LINEOFCREDITACCOUNTID = "
			              +   aChangedLineOfCreditAccount.getAccountNumber();
	
		Statement myStatemnt = myConnection.createStatement();
		myStatemnt.executeUpdate(mySQLStatement);
		myConnection.commit();	
		myConnection.close();
	}
	
	public static LineOfCreditAccount search(Integer id) throws SQLException, ExceptionIsNull, ExceptionIsNotANumber, ExceptionIsPassedDate, ExceptionNegativeAmount, ExceptionNotEnoughBalance{
		
		LineOfCreditAccount aLineOfCreditAccount = null;
		
		myConnection = DBConnection.getConnection();
		
		mySQLQuery = "SELECT a.accountid, a.customerid, a.balance, a.openingdate, a.typeaccount, c.duedate, c.limit, l.interest_rate, l.nbofinstall, l.installment "
					+ "FROM accountbank a "
					+ "JOIN lineofcreditaccount l ON a.accountid = l.lineofcreditaccountid "
					+ "JOIN creditaccount c ON a.accountid = c.creditaccountid "
					+ "WHERE a.accountid = " + id ;
		
		Statement myStatemnt = myConnection.createStatement();
		
		ResultSet myResultSet = myStatemnt.executeQuery(mySQLQuery);
		
		if(myResultSet.next()) {
			Integer accountid = myResultSet.getInt("accountid");
			EnumTypeAccount type = EnumTypeAccount.valueOf(myResultSet.getString("typeaccount"));
			Integer customerid = myResultSet.getInt("customerid");
            LocalDate openingDate = myResultSet.getDate("openingdate").toLocalDate();
            LocalDate dueDate = myResultSet.getDate("duedate").toLocalDate();
            Double limit = myResultSet.getDouble("limit");
            Double interestRate = myResultSet.getDouble("interest_rate");
            Double balance = myResultSet.getDouble("balance");

            aLineOfCreditAccount = new LineOfCreditAccount(accountid, type, customerid, balance, openingDate, dueDate, limit, interestRate);
		}	
		myConnection.close();
		return aLineOfCreditAccount;
	}
	
	public static ArrayList<LineOfCreditAccount> searchByCustomer(Integer customerId) throws SQLException, ExceptionIsNull, ExceptionIsNotANumber, ExceptionIsPassedDate, ExceptionNegativeAmount, ExceptionNotEnoughBalance{
		
		LineOfCreditAccount aLineOfCreditAccount = null;
		
		myConnection = DBConnection.getConnection();
		
		mySQLQuery = "SELECT a.accountid, a.customerid, a.balance, a.openingdate, a.typeaccount, c.duedate, c.limit, l.interest_rate, l.nbofinstall, l.installment "
				+ "FROM accountbank a "
				+ "JOIN lineofcreditaccount l ON a.accountid = l.lineofcreditaccountid "
				+ "JOIN creditaccount c ON a.accountid = c.creditaccountid "
				+ "WHERE a.customerid = " + customerId ;
		
		Statement myStatemnt = myConnection.createStatement();
		
		ResultSet myResultSet = myStatemnt.executeQuery(mySQLQuery);
		
		ArrayList<LineOfCreditAccount> myList = new ArrayList<LineOfCreditAccount>();
		
		while(myResultSet.next()) {
			Integer accountid = myResultSet.getInt("accountid");
			EnumTypeAccount type = EnumTypeAccount.valueOf(myResultSet.getString("typeaccount"));
			Integer customerid = myResultSet.getInt("customerid");
            LocalDate openingDate = myResultSet.getDate("openingdate").toLocalDate();
            LocalDate dueDate = myResultSet.getDate("duedate").toLocalDate();
            Double limit = myResultSet.getDouble("limit");
            Double interestRate = myResultSet.getDouble("interest_rate");
            Double balance = myResultSet.getDouble("balance");

            aLineOfCreditAccount = new LineOfCreditAccount(accountid, type, customerid, balance, openingDate, dueDate, limit, interestRate);
            
            myList.add(aLineOfCreditAccount);
		}	
		myConnection.close();
		return myList;
	}
	
	public static LineOfCreditAccount searchByIdAndCustomer(Integer id, Integer customer) throws SQLException, ExceptionIsNull, ExceptionIsNotANumber, ExceptionIsPassedDate, ExceptionNegativeAmount, ExceptionNotEnoughBalance {
		LineOfCreditAccount aLineOfCreditAccount = null;
		
		myConnection = DBConnection.getConnection();
		
		mySQLQuery = "SELECT a.accountid, a.customerid, a.balance, a.openingdate, a.typeaccount, c.duedate, c.limit, l.interest_rate, l.nbofinstall, l.installment "
					+ "FROM accountbank a "
					+ "JOIN lineofcreditaccount l ON a.accountid = l.lineofcreditaccountid "
					+ "JOIN creditaccount c ON a.accountid = c.creditaccountid "
					+ "WHERE a.accountid = " + id + " AND a.customerid = " + customer;
		
		Statement myStatemnt = myConnection.createStatement();
		
		ResultSet myResultSet = myStatemnt.executeQuery(mySQLQuery);
		
		if(myResultSet.next()) {
			Integer accountid = myResultSet.getInt("accountid");
			EnumTypeAccount type = EnumTypeAccount.valueOf(myResultSet.getString("typeaccount"));
			Integer customerid = myResultSet.getInt("customerid");
            LocalDate openingDate = myResultSet.getDate("openingdate").toLocalDate();
            LocalDate dueDate = myResultSet.getDate("duedate").toLocalDate();
            Double limit = myResultSet.getDouble("limit");
            Double interestRate = myResultSet.getDouble("interest_rate");
            Integer nbOfInstall = myResultSet.getInt("nbofinstall");
            Double installment = myResultSet.getDouble("installment");
            Double balance = myResultSet.getDouble("balance");
            

            aLineOfCreditAccount = new LineOfCreditAccount(accountid, type, customerid, balance, openingDate, dueDate, limit, interestRate);
            aLineOfCreditAccount.setNbOfInstallments(nbOfInstall);
            aLineOfCreditAccount.setInstallment(installment);
		}	
		myConnection.close();
		return aLineOfCreditAccount;
	}
	
	public static ArrayList<LineOfCreditAccount> select() throws SQLException, ExceptionIsNull, ExceptionIsNotANumber, ExceptionIsPassedDate, ExceptionNegativeAmount, ExceptionNotEnoughBalance{
		
		LineOfCreditAccount aLineOfCreditAccount = null;
		myConnection = DBConnection.getConnection();
		
		mySQLQuery = "SELECT a.accountid, a.customerid, a.balance, a.openingdate, a.typeaccount, c.duedate, c.limit, l.interest_rate, l.nbofinstall, l.installment "
				+ "FROM accountbank a "
				+ "JOIN lineofcreditaccount l ON a.accountid = l.lineofcreditaccountid "
				+ "JOIN creditaccount c ON a.accountid = c.creditaccountid";
		
		Statement myStatemnt = myConnection.createStatement();
		
		ResultSet myResultSet = myStatemnt.executeQuery(mySQLQuery);
		
		ArrayList<LineOfCreditAccount> myList = new ArrayList<LineOfCreditAccount>();
		
		while(myResultSet.next()) {
			Integer accountid = myResultSet.getInt("accountid");
			EnumTypeAccount type = EnumTypeAccount.valueOf(myResultSet.getString("typeaccount"));
			Integer customerid = myResultSet.getInt("customerid");
            LocalDate openingDate = myResultSet.getDate("openingdate").toLocalDate();
            LocalDate dueDate = myResultSet.getDate("duedate").toLocalDate();
            Double limit = myResultSet.getDouble("limit");
            Double interestRate = myResultSet.getDouble("interest_rate");
            Double balance = myResultSet.getDouble("balance");

            aLineOfCreditAccount = new LineOfCreditAccount(accountid, type, customerid, balance, openingDate, dueDate, limit, interestRate);
	
			myList.add(aLineOfCreditAccount);
		}
		myConnection.close();
		return myList;
	}


		
}
