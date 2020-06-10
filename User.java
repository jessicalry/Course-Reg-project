
import java.io.*;
public class User implements Serializable{
	protected String username;
	protected String password;
	protected String first;
	protected String last;
	
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the first
	 */
	public String getFirst() {
		return first;
	}

	/**
	 * @param first the first to set
	 */
	public void setFirst(String first) {
		this.first = first;
	}

	/**
	 * @return the last
	 */
	public String getLast() {
		return last;
	}

	/**
	 * @param last the last to set
	 */
	public void setLast(String last) {
		this.last = last;
	}
	
	public User() {
		
	}
	
	public User(String first, String last) {
		this.first = first;
		this.last = last;

	}
	
	public User(String username, String password, String first, String last) {
		this.first = first;
		this.last = last;
		this.username = username;
		this.password = password;
	}
	
	//Methods 
	public String userToString() {
		return(this.getUsername());
	}

}
