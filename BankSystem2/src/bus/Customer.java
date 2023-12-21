package bus;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import data.CustomerDB;
import data.UserDB;

public class Customer extends User{
	private Integer id;
	private Double salary;
	private Integer mgr;
	ArrayList<Account> listOfAccounts;
	
	public Customer() {
		super();
		this.id = null;
		this.salary = 0.00;
		this.mgr = null;
	}

	public Customer(Integer id, String username, Integer password, Double salary, Integer mgr) throws ExceptionIsNotANumber, ExceptionIsNull {
		super(id, username, password);
		this.id = id;
		setSalary(salary);
		this.mgr = mgr;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) throws ExceptionIsNotANumber, ExceptionIsNull {
		if (!Validator.isDouble(salary)) {
			throw new ExceptionIsNotANumber();
		}
		
		if (Validator.isNull(salary)) {
			throw new ExceptionIsNull();
		}
		this.salary = salary;
	}

	public Integer getMgr() {
		return mgr;
	}

	public void setMgr(Integer mgr) {
		this.mgr = mgr;
	}

	@Override
	public String toString() {
		return super.toString() + "\n\tCustomer salary: " + salary + 
								  "\n\tManager ID: " + mgr;
	}
	
	//////////////////////////////
	//   public static services //
	//////////////////////////////
	public static Integer add(Customer element) throws SQLException {
		//JOptionPane.showMessageDialog(null, "Entrei no Customer Add");
		Integer id = CustomerDB.insert(element);
		return id;
	}
	
	public static void update(Customer element) throws SQLException {
		CustomerDB.update(element);
	}
	
	public static void remove(Integer id) throws SQLException {
		UserDB.delete(id);
	}
	
	public static Customer search(Integer id) throws SQLException, ExceptionIsNotANumber, ExceptionIsNull {
		return CustomerDB.search(id);
	}
	
	public static ArrayList<Customer> getData() throws SQLException, ExceptionIsNotANumber, ExceptionIsNull {
		return CustomerDB.select();
	}
	
	public static ArrayList<Customer> searchByManager(Integer mgr) throws SQLException, ExceptionIsNotANumber, ExceptionIsNull {
		return CustomerDB.searchByManager(mgr);
	}
}
