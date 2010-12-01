package beans;

public class User {

	protected String userID;
	protected String password;
	protected String firstName;
	protected String lastName;
	
	//while registration there should a userID checker that tells you if userID is available or not, done via the servlet ajax.
	public User(String userID, String password, String fName, String lName)
	{
		this.userID = userID;
		this.password = password;
		this.firstName = fName;
		this.lastName = lName;
		
	}
	
	public boolean addBooking()
	{
		return false;
	}
	
	
	
	
	
	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	
	
	
	
	
	
	
	
}
