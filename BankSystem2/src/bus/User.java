package bus;

import java.sql.SQLException;
import data.UserDB;

public class User{
	protected Integer id;
	protected String username;
	protected Integer password;
	
	public User() {
		super();
		this.id = null;
		this.username = "";
		this.password = 0;
	}
	
	public User(Integer id, String username, Integer password) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
	}

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String userName) {
		this.username = userName;
	}

	public Integer getPassword() {
		return password;
	}

	public void setPassword(Integer password) {
		this.password = password;
	}

	//Overridden Methods
	@Override
	public String toString() {
		return "User ID: " + this.id + " - Name: " + this.username;
	}
	
	//////////////////////////////
	//   public static services //
	//////////////////////////////
	public static void add(User element) throws SQLException {
		UserDB.insert(element);
	}
	
	public static void update(User element) throws SQLException {
		UserDB.update(element);
	}
	
	public static void remove(Integer id) throws SQLException {
		UserDB.delete(id);
	}
	
	public static User search(String username, Integer password) throws SQLException {
		User userFound = UserDB.searchUser(username,password);
		return userFound;
	}
}
