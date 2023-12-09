package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	public static Connection getConnection() throws SQLException
	{
         Connection myConnection = null;		
	     String userName, password, service, url;
		
		 userName = "fortisbank" ;
		 password = "1234" ;
		 service = "localhost" ;
		
		 url = "jdbc:oracle:thin:";
		       
    	myConnection = DriverManager.getConnection(url + userName + "/" + password + "@" + service);
  	  
		return myConnection;		
	}
}
