package edu.ucab.cryptomonitor.models;

import java.io.IOException;

import com.paaksing.jjango.Auth;
import com.paaksing.jjango.Manager;
import com.paaksing.jjango.Model;
import com.paaksing.jjango.Database.ObjectDoesNotExist;
import com.paaksing.jjango.Validator.ValidationError;

public class User extends Model {

	private String username;
	private String email;
	private String password;
	private String lastLoggedIP;

	public static transient Manager<User> objects = new Manager<User>("db/users", User.class);

	@SuppressWarnings("unchecked")
	public Manager<User> getObjects() {
		return objects;
	}

	public User() {
	    this.setUsername(null);
	}

	public User(String username, String email, String password) {
		this.setUsername(username);
		this.setEmail(email);
		this.setPassword(password);
		this.setLastLoggedIP(null);
	}

	public User(String username, String email, String password, String lastLoggedIP) {
	    this(username, email, password);
	    this.setLastLoggedIP(lastLoggedIP);
	}

	public User(long id, String username, String email, String password, String lastLoggedIP) {
		this(username, email, password, lastLoggedIP);
		this.setId(id);
	}

	public boolean isAuthenticated() {
	    return this.getUsername() != null;
	}

	public static boolean authenticate(String email, String password) throws IOException {
		try {
			User user = objects.first("email", email);
			return Auth.checkPassword(password, user.getPassword());
		} catch (ObjectDoesNotExist e) {
			return false;
		}
	}

	public void clean() throws ValidationError {
		getObjects().validator.email(this.getEmail());
		getObjects().validator.securePassword(this.getPassword());
		try {
			getObjects().validator.unique(this, "email", this.getEmail());
		} catch (IOException e) {
			e.printStackTrace();
			throw new ValidationError("Could not connect to db");
		}
		this.setPassword(Auth.makePassword(this.getPassword()));
	}

	/* Getters and Setters */

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEmail(String email) {
		this.email = email;
	}

    public String getLastLoggedIP() {
        return lastLoggedIP;
    }

    public void setLastLoggedIP(String lastLoggedIP) {
        this.lastLoggedIP = lastLoggedIP;
    }

}
