package data;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import bus.*;

public class ManagerDB {
	static private Connection myConnection;
	static private String mySQLQuery = null;
	
	public static void insert(Manager aNewManager) throws SQLException 
	{	 	
    	Integer id = UserDB.insert(aNewManager);
   
	    if (id != null) {
	        PreparedStatement myPreparedStatement = null ;
			String sqlStatement;
			
			sqlStatement = "insert into managerbank values(?)";
					
			myPreparedStatement = myConnection.prepareStatement(sqlStatement);		
			
			myPreparedStatement.setInt(1, id);
			myPreparedStatement.executeUpdate();	
			
			myConnection.commit();	
	    }
	}
	
//	public static void update(Manager aChangedManager) throws SQLException {								
//	}
	
	public static Manager search(Integer id) throws SQLException{
		
		Manager aManager = null;
		
		myConnection = DBConnection.getConnection();
		
		mySQLQuery = "SELECT u.userid, u.username, u.password "
				+ "FROM userbank u "
				+ "JOIN managerbank m ON u.userid = m.mgrid "
				+ "WHERE u.userid = " + id;
		
		Statement myStatemnt = myConnection.createStatement();
		
		ResultSet myResultSet = myStatemnt.executeQuery(mySQLQuery);
		
		if(myResultSet.next()) {
            Integer userid = myResultSet.getInt("userid");	
            String username = myResultSet.getString("username");
            Integer password = myResultSet.getInt("password");

            aManager = new Manager(userid, username, password);
		}	
		
		return aManager;
	}
	
	public static ArrayList<Manager> select() throws SQLException, ExceptionIsNotANumber, ExceptionIsNull{
		
		Manager aManager = null;
		myConnection = DBConnection.getConnection();
		
		mySQLQuery = "SELECT u.userid, u.username, u.password"
				+ "FROM userbank u "
				+ "JOIN managerbank m ON u.userid = m.mgrid";
		
		Statement myStatemnt = myConnection.createStatement();
		ResultSet myResultSet = myStatemnt.executeQuery(mySQLQuery);
		
		ArrayList<Manager> myList = new ArrayList<Manager>();
		
		while(myResultSet.next()) {
			Integer userid = myResultSet.getInt("userid");	
            String username = myResultSet.getString("username");
            Integer password = myResultSet.getInt("password");

            aManager = new Manager(userid, username, password);
			myList.add(aManager);
		}
		
		return myList;
	}

}
