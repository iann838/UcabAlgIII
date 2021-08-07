package edu.ucab.cryptomonitor.models;

import java.io.IOException;

import com.paaksing.jjango.Auth;
import com.paaksing.jjango.Manager;
import com.paaksing.jjango.Model;
import com.paaksing.jjango.Database.ObjectDoesNotExist;
import com.paaksing.jjango.Validator.ValidationError;

/**
 * Clase que representa al usuario
 * @version 1.0
 */

public class User extends Model {

	private String username;
	private String email;
	private String password;
	private String lastLoggedIP;

	public static transient Manager<User> objects = new Manager<User>("db/users", User.class);

	/**
	 * Metodo que regresa la lista de usuarios registrados
	 * @return Regresa la lista de usuarios
	 */
	
	@SuppressWarnings("unchecked")
	public Manager<User> getObjects() {
		return objects;
	}

	/**
	 * Metodo constructor por defecto
	 */
	
	public User() {
	    this.setUsername(null);
	}
	
	/**
	 * Metodo constructor parametrizado
	 * @param username Nombre de usuario
	 * @param email Email del usuario
	 * @param password Contraseña del usuario
	 */

	public User(String username, String email, String password) {
		this.setUsername(username);
		this.setEmail(email);
		this.setPassword(password);
		this.setLastLoggedIP(null);
	}
	
	/**
	 * Metodo constructor parametrizado
	 * @param username Nombre de usuario
	 * @param email Email del usuario
	 * @param password Contraseña del usuario
	 * @param lastLoggedIP IP de la ultima conexion del usuario
	 */

	public User(String username, String email, String password, String lastLoggedIP) {
	    this(username, email, password);
	    this.setLastLoggedIP(lastLoggedIP);
	}
	
	/**
	 * Metodo constructor parametrizado
	 * @param id Identificador del usuario
	 * @param username Nombre de usuario
	 * @param email Email del usuario
	 * @param password Contraseña del usuario
	 * @param lastLoggedIP IP de la ultima conexion del usuario
	 */

	public User(long id, String username, String email, String password, String lastLoggedIP) {
		this(username, email, password, lastLoggedIP);
		this.setId(id);
	}
	
	/**
	 * Metodo que verifica si el usuario esta autenticado
	 * @return Regresa el valor de autenticacion
	 */

	public boolean isAuthenticated() {
	    return this.getUsername() != null;
	}
	
	/**
	 * Metodo que autentica al usuario
	 * @param email Email del usuario
	 * @param password Contraseña del usuario
	 * @return Regresa la confirmacion de la contraseña
	 * @throws IOException
	 */

	public static boolean authenticate(String email, String password) throws IOException {
		try {
			User user = objects.first("email", email);
			return Auth.checkPassword(password, user.getPassword());
		} catch (ObjectDoesNotExist e) {
			return false;
		}
	}
	
	/**
	 * 
	 */

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
	
	/**
	 * Metodo que regresa el nombre de usuario
	 * @return Regresa el nombre de usuario
	 */

	public String getUsername() {
		return username;
	}
	
	/**
	 * Metodo que regresa la contraseña del usuario
	 * @return Regresa la contraseña
	 */

	public String getPassword() {
		return password;
	}
	
	/**
	 * Metodo que regresa el email del usuario
	 * @return Regresa el email del usuario
	 */

	public String getEmail() {
		return email;
	}
	
	/**
	 * Metodo para colocar el nombre de usuario
	 * @param username Nombre que se asigna
	 */

	public void setUsername(String username) {
		this.username = username;
	}
	
	/**
	 * Metodo para colocar la contraseña del usuario
	 * @param password Valor que se asigna
	 */

	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * Metodo para colocar el email del usuario
	 * @param email Email que se asigna
	 */

	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * Metodo que regresa la IP de la ultima conexion del usuario
	 * @return Regresa la IP
	 */

    public String getLastLoggedIP() {
        return lastLoggedIP;
    }
    
    /**
     * Metodo para colocar la IP de la ultima conexion del usuario
     * @param lastLoggedIP IP que se asigna
     */

    public void setLastLoggedIP(String lastLoggedIP) {
        this.lastLoggedIP = lastLoggedIP;
    }

}
