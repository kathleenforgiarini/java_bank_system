package bus;

public class User{
	protected Integer counter = 1;
	protected Integer identificationNumber;
	protected String userName;
	protected Integer password;
	
	public User() {
		super();
		this.identificationNumber = counter++;
		this.userName = "";
		this.password = 0;
	}
	
	public User(String userName, Integer password) {
		super();
		this.identificationNumber = counter++;
		this.userName = userName;
		this.password = password;
	}

	public Integer getIdentificationNumber() {
		return identificationNumber;
	}
	
	public void setIdentificationNumber(Integer identificationNumber) {
		this.identificationNumber = counter++;
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
