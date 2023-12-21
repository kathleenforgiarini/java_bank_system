package data;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.time.LocalDate;

import javax.swing.JOptionPane;

import java.sql.PreparedStatement;
import bus.*;

public class UserDB {
	static private Connection myConnection;
	static private String mySQLStatement = null;
	static private String mySQLQuery = null;
	
	public static Integer insert(User aNewUser) throws SQLException {
		myConnection = DBConnection.getConnection();
		
		PreparedStatement myPreparedStatement = null ;
		//ResultSet generatedKeys = null;
		String sqlStatement;
		
		sqlStatement = "insert into userbank (username,password) values (? , ?)";
				
		myPreparedStatement = myConnection.prepareStatement(sqlStatement);
		
		//myPreparedStatement.setNull(1, Types.INTEGER);
		myPreparedStatement.setString(1, aNewUser.getUsername());
		myPreparedStatement.setInt(2, aNewUser.getPassword());	
		
		myPreparedStatement.executeUpdate();	
		
		myConnection.commit();	
		
		Integer id = UserDB.searchUser(aNewUser.getUsername(),aNewUser.getPassword()).getId();
		myConnection.close();
		return id;
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
	
	public static User searchUser (String username, Integer password) throws SQLException
	{
		User userfound = null;
		
		myConnection = DBConnection.getConnection();
		
		mySQLQuery = "SELECT userid, username, password "
					+ "FROM userbank "
					+ "WHERE username = '" + username + "' AND password = " + password;
		
		Statement myStatemnt = myConnection.createStatement();
		
		ResultSet myResultSet = myStatemnt.executeQuery(mySQLQuery);
		
		if(myResultSet.next()) {
			Integer idFound = myResultSet.getInt("userid");
			String usernameFound = myResultSet.getString("username");
			Integer passwordFound = myResultSet.getInt("password");

			userfound = new User(idFound, usernameFound, passwordFound);
		}	
		
		return userfound;
	}
}
