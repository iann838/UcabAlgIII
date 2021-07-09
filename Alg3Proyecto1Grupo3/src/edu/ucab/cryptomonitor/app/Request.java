package edu.ucab.cryptomonitor.app;

import java.util.Scanner;

import edu.ucab.cryptomonitor.models.User;

public class Request {

    private User user;
    private Scanner scanner;

    public Request(User user, Scanner scanner) {
        super();
        this.user = user;
        this.scanner = scanner;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }
    

}
