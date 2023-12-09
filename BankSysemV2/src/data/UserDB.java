package data;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.sql.PreparedStatement;
import bus.*;

public class UserDB {
	static private Connection myConnection;
	static private String mySQLStatement = null;
	
	public static Integer insert(User aNewUser) throws SQLException {
		PreparedStatement myPreparedStatement = null ;
		ResultSet generatedKeys = null;
		String sqlStatement;
		
		sqlStatement = "insert into userbank values(? , ? , ?)";
				
		myPreparedStatement = myConnection.prepareStatement(sqlStatement);		
		
		myPreparedStatement.setNull(1, Types.INTEGER);
		myPreparedStatement.setString(2, aNewUser.getUsername());
		myPreparedStatement.setInt(3, aNewUser.getPassword());	
		
		myPreparedStatement.executeUpdate();	
		
		myConnection.commit();	
		
		generatedKeys = myPreparedStatement.getGeneratedKeys();

	    if (generatedKeys.next()) {
	        int generatedId = generatedKeys.getInt(1);
	        return generatedId;
	    }
		return null;
	}
	
	public static void update(User aChangedUser) throws SQLException {
		
		myConnection = DBConnection.getConnection();
		
		mySQLStatement = "update userbank set username = "    
			              +  aChangedUser.getUsername() + ", password = "
			              +  aChangedUser.getPassword() + " WHERE userid = " 
			              +  aChangedUser.getId();
	
		Statement myStatemnt = myConnection.createStatement();
		myStatemnt.executeUpdate(mySQLStatement);
		myConnection.commit();								
	}
	
	public static void delete(Integer id) throws SQLException {
		
		myConnection = DBConnection.getConnection();
		
		mySQLStatement = "Delete FROM userbank WHERE userid = "  + id;
	
		Statement myStatemnt = myConnection.createStatement();
		myStatemnt.executeUpdate(mySQLStatement);
		myConnection.commit();	

	}
}
