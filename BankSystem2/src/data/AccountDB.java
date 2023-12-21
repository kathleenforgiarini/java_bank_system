package data;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import bus.*;

public class AccountDB {
	static private Connection myConnection;
	static private String mySQLStatement = null;
	static private String mySQLQuery = null;
	
	public static Integer insert(Account aNewAccount) throws SQLException {
		myConnection = DBConnection.getConnection();
		
		PreparedStatement myPreparedStatement = null ;
		String sqlStatement;
		
		sqlStatement = "insert into AccountBank values(? , ? , ? , ? , ?)";
				
		myPreparedStatement = myConnection.prepareStatement(sqlStatement);		
		
		myPreparedStatement.setNull(1, Types.INTEGER);
		myPreparedStatement.setString(2, aNewAccount.getType().toString());
		myPreparedStatement.setInt(3, aNewAccount.getCustomer());	
		myPreparedStatement.setDouble(4, aNewAccount.getBalance());
		myPreparedStatement.setDate(5, Date.valueOf(aNewAccount.getOpeningDate()));
		
		myPreparedStatement.executeUpdate();	
		
		myConnection.commit();	
		
		Integer maxId = searchMaxId();
		myConnection.close();
		
		return maxId;
	}
	
	public static void update(Account aChangedAccount) throws SQLException {
		
		myConnection = DBConnection.getConnection();
		
		mySQLStatement = "update AccountBank set balance = "    
			              +  aChangedAccount.getBalance() + " WHERE accountid = "
			              +  aChangedAccount.getAccountNumber();
	
		Statement myStatemnt = myConnection.createStatement();
		myStatemnt.executeUpdate(mySQLStatement);
		myConnection.commit();		
		myConnection.close();
	}
	
	public static void delete(Integer id) throws SQLException {
		
		myConnection = DBConnection.getConnection();
		
		mySQLStatement = "Delete FROM accountbank WHERE accountid = "  + id;
	
		Statement myStatemnt = myConnection.createStatement();
		myStatemnt.executeUpdate(mySQLStatement);
		myConnection.commit();	
		myConnection.close();
	}
	
	public static Integer searchMaxId () throws SQLException
	{
		myConnection = DBConnection.getConnection();
		Integer idFound = null;
		
		mySQLQuery = "SELECT MAX(accountid) as max "
					+ "FROM accountbank";
		
		Statement myStatemnt = myConnection.createStatement();
		
		ResultSet myResultSet = myStatemnt.executeQuery(mySQLQuery);
		
		if(myResultSet.next()) {
			idFound = myResultSet.getInt("max");
		}	
		
		myConnection.close();
		return idFound;
	}
	
	public static Double getBalanceById(Integer accountId, Integer customerId) throws SQLException {
		myConnection = DBConnection.getConnection();
		Double balanceFound = null;
		
		mySQLQuery = "SELECT balance "
					+ "FROM accountbank WHERE accountid = " + accountId
					+ " AND customerid = " + customerId;
		
		Statement myStatemnt = myConnection.createStatement();
		
		ResultSet myResultSet = myStatemnt.executeQuery(mySQLQuery);
		
		if(myResultSet.next()) {
			balanceFound = myResultSet.getDouble("balance");
		}	
		
		myConnection.close();
		return balanceFound;
	}
	
	public static String getTypeById(Integer accountId, Integer customerId) throws SQLException {
		myConnection = DBConnection.getConnection();
		String typeFound = null;
		
		mySQLQuery = "SELECT typeaccount "
					+ "FROM accountbank WHERE accountid = " + accountId
					+ " AND customerid = " + customerId;
		
		Statement myStatemnt = myConnection.createStatement();
		
		ResultSet myResultSet = myStatemnt.executeQuery(mySQLQuery);
		
		if(myResultSet.next()) {
			typeFound = myResultSet.getString("typeaccount");
		}	
		
		myConnection.close();
		return typeFound;
	}

	public static ArrayList<Integer> searchByCustomerId(Integer customerId) throws SQLException {
		myConnection = DBConnection.getConnection();
		
		mySQLQuery = "SELECT accountid "
				+ "FROM accountbank "
				+ "WHERE customerid = " + customerId
				+ " ORDER BY accountid";
		
		Statement myStatemnt = myConnection.createStatement();
		ResultSet myResultSet = myStatemnt.executeQuery(mySQLQuery);
		
		ArrayList<Integer> myList = new ArrayList<Integer>();
		
		while(myResultSet.next()) {
			Integer accountid = myResultSet.getInt("accountid");	
			
			myList.add(accountid);
		}
		
		return myList;
	}
}
