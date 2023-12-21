package data;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import java.sql.PreparedStatement;
import bus.*;

public class CustomerDB {
	static private Connection myConnection;
	static private String mySQLStatement = null;	
	static private String mySQLQuery = null;
	
	public static Integer insert(Customer aNewCustomer) throws SQLException 
	{	 	
		myConnection = DBConnection.getConnection();
		//JOptionPane.showMessageDialog(null, "Entrei no CustomerDB Insert");
		Integer id = UserDB.insert(aNewCustomer);
		//JOptionPane.showMessageDialog(null, id);

   
	    if (id != null) {
	        PreparedStatement myPreparedStatement2 = null ;
			String sqlStatement2;
			
			sqlStatement2 = "insert into customerbank values(? , ? , ?)";
					
			myPreparedStatement2 = myConnection.prepareStatement(sqlStatement2);		
			
			myPreparedStatement2.setInt(1, id);
			myPreparedStatement2.setDouble(2, aNewCustomer.getSalary());
			myPreparedStatement2.setInt(3, aNewCustomer.getMgr());	// ALTERADO CLASSE PARA MANAGER SER INTEGER AO INVES DE OBJ
			
			//JOptionPane.showMessageDialog(null, myPreparedStatement2);
			
			myPreparedStatement2.executeUpdate();	
			
			myConnection.commit();	
	    }
	    myConnection.close();
	    return id;
	}
	
	public static void update(Customer aChangedCustomer) throws SQLException {
		
		myConnection = DBConnection.getConnection();
		
		mySQLStatement = "update customerbank set salary = "    
			              +   aChangedCustomer.getSalary() + ", mgrid = " 
			              +   aChangedCustomer.getMgr() + " WHERE customerid = "
			              +   aChangedCustomer.getId();
	
		Statement myStatemnt = myConnection.createStatement();
		myStatemnt.executeUpdate(mySQLStatement);
		myConnection.commit();								
	}
	
	public static Customer search(Integer id) throws SQLException, ExceptionIsNotANumber, ExceptionIsNull{
		
		Customer aCustomer = null;
		
		myConnection = DBConnection.getConnection();
		
		mySQLQuery = "SELECT u.userid, u.username, u.password, c.salary, c.mgrid "
					+ "FROM userbank u "
					+ "JOIN customerbank c ON u.userid = c.customerid "
					+ "WHERE u.userid = " + id ;
		
		Statement myStatemnt = myConnection.createStatement();
		
		ResultSet myResultSet = myStatemnt.executeQuery(mySQLQuery);
		
		if(myResultSet.next()) {
            Integer customerid = myResultSet.getInt("userid");	
            String username = myResultSet.getString("username");
            Integer password = myResultSet.getInt("password");
            Double salary = myResultSet.getDouble("salary");
            Integer mgr = myResultSet.getInt("mgrid");

            aCustomer = new Customer(customerid, username, password, salary, mgr);
		}	
		
		return aCustomer;
	}
	
	public static ArrayList<Customer> select() throws SQLException, ExceptionIsNotANumber, ExceptionIsNull{
		
		Customer aCustomer = null;
		myConnection = DBConnection.getConnection();
		
		mySQLQuery = "SELECT u.userid, u.username, u.password, c.salary, c.mgrid "
				+ "FROM userbank u "
				+ "JOIN customerbank c ON u.userid = c.customerid";
		
		Statement myStatemnt = myConnection.createStatement();
		ResultSet myResultSet = myStatemnt.executeQuery(mySQLQuery);
		
		ArrayList<Customer> myList = new ArrayList<Customer>();
		
		while(myResultSet.next()) {
			Integer customerid = myResultSet.getInt("userid");	
            String username = myResultSet.getString("username");
            Integer password = myResultSet.getInt("password");
            Double salary = myResultSet.getDouble("salary");
            Integer mgr = myResultSet.getInt("mgrid");

            aCustomer = new Customer(customerid, username, password, salary, mgr);
			myList.add(aCustomer);
		}
		
		return myList;
	}

	public static ArrayList<Customer> searchByManager(Integer mgr) throws SQLException, ExceptionIsNotANumber, ExceptionIsNull {
		Customer aCustomer = null;
		myConnection = DBConnection.getConnection();
		
		mySQLQuery = "SELECT u.userid, u.username, u.password, c.salary, c.mgrid "
				+ "FROM userbank u "
				+ "JOIN customerbank c ON u.userid = c.customerid "
				+ "WHERE c.mgrid = " + mgr;
		
		Statement myStatemnt = myConnection.createStatement();
		ResultSet myResultSet = myStatemnt.executeQuery(mySQLQuery);
		
		ArrayList<Customer> myList = new ArrayList<Customer>();
		
		while(myResultSet.next()) {
			Integer customerid = myResultSet.getInt("userid");	
            String username = myResultSet.getString("username");
            Integer password = myResultSet.getInt("password");
            Double salary = myResultSet.getDouble("salary");
            Integer mgrid = myResultSet.getInt("mgrid");

            aCustomer = new Customer(customerid, username, password, salary, mgrid);
			myList.add(aCustomer);
		}
		
		return myList;
	}
}
