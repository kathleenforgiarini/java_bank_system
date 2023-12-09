package data;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.sql.PreparedStatement;
import bus.*;

public class AccountDB {
	static private Connection myConnection;
	static private String mySQLStatement = null;
	
	public static Integer insert(Account aNewAccount) throws SQLException {
		PreparedStatement myPreparedStatement = null ;
		ResultSet generatedKeys = null;
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
		
		generatedKeys = myPreparedStatement.getGeneratedKeys();

	    if (generatedKeys.next()) {
	        int generatedId = generatedKeys.getInt(1);
	        return generatedId;
	    }
		return null;
	}
	
	public static void update(Account aChangedAccount) throws SQLException {
		
		myConnection = DBConnection.getConnection();
		
		mySQLStatement = "update AccountBank set balance = "    
			              +  aChangedAccount.getBalance() + " WHERE accountid = "
			              +  aChangedAccount.getAccountNumber();
	
		Statement myStatemnt = myConnection.createStatement();
		myStatemnt.executeUpdate(mySQLStatement);
		myConnection.commit();								
	}
	
	public static void delete(Integer id) throws SQLException {
		
		myConnection = DBConnection.getConnection();
		
		mySQLStatement = "Delete FROM accountbank WHERE accountid = "  + id;
	
		Statement myStatemnt = myConnection.createStatement();
		myStatemnt.executeUpdate(mySQLStatement);
		myConnection.commit();	
	}
}
