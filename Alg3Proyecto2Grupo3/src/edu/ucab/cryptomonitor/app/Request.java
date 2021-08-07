package edu.ucab.cryptomonitor.app;

import edu.ucab.cryptomonitor.models.User;

public class Request {

    private User user;

    public Request(User user) {
        super();
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
