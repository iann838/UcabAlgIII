package edu.ucab.alg3.cryptomoni.models;

import java.io.IOException;

import com.paaksing.java.django.Manager;
import com.paaksing.java.django.Model;
import com.paaksing.java.django.Validator.ValidationError;
import com.paaksing.java.django.Auth;
import com.paaksing.java.django.Database.ObjectDoesNotExist;

public class User extends Model {

    public String username;
    public String email;
    public String password;

    public static transient Manager<User> objects = new Manager<User>("db/users", User.class);
    @SuppressWarnings("unchecked") public Manager<User> getObjects() { return objects; }

    public User() {}
    
    public User(String username, String email, String password) {
    	this.username = username;
    	this.email = email;
    	this.password = password;
    }

    public User(int id, String username, String email, String password) {
    	this(username, email, password);
    	this.id = id;
    }

    public static boolean authenticate(String username, String password) throws IOException {
    	try {
			User user = objects.first("username", username);
			return Auth.checkPassword(password, user.password);
		} catch (ObjectDoesNotExist e) {
			return false;
		}
    }

    public void clean() throws ValidationError {
        getObjects().validator.email(this.email);
        try {
            getObjects().validator.unique(this, "email", this.email);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ValidationError("Could not connect to db");
        }
    }
    
    public int save() throws ValidationError {
    	this.password = Auth.makePassword(this.password);
    	return super.save();
    }

}
