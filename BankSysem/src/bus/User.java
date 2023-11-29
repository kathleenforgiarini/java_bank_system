package bus;

public class User {
	protected Integer identificationNumber;
	protected String userName;
	protected Integer password;
	
	public User() {
		super();
		this.identificationNumber = 0;
		this.userName = "";
		this.password = 0;
	}
	
	public User(Integer identificationNumber, String userName, Integer password) {
		super();
		this.identificationNumber = identificationNumber;
		this.userName = userName;
		this.password = password;
	}

	public Integer getIdentificationNumber() {
		return identificationNumber;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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
		return "User ID: " + this.identificationNumber + " - Name: " + this.userName;
	}
}
