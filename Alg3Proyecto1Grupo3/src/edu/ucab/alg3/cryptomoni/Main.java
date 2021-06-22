package edu.ucab.alg3.cryptomoni;

import java.io.IOException;

import com.paaksing.java.django.Validator.ValidationError;

import edu.ucab.alg3.cryptomoni.models.User;

public class Main {

	public static void main(String[] args) throws IOException, ValidationError {
		User user = new User("testuser", "a@b.c", "test123");
		user.save();

		System.out.println(User.authenticate("testuser", "test123"));
	}

}
