package edu.ucab.cryptomonitor.models;

import com.paaksing.jjango.Manager;
import com.paaksing.jjango.Model;

public class WalletBalance extends Model {
    
    private long user;
    private long currency;
    private double balance;

    public static transient Manager<WalletBalance> objects = new Manager<WalletBalance>("db/wallet-balances", WalletBalance.class);
    
    @SuppressWarnings("unchecked")
    @Override
    public Manager<WalletBalance> getObjects() {
        return objects;
    }
    
    public WalletBalance() {}
    
    public WalletBalance(long user, long currency, double balance) {
        this.setUser(user);
        this.setCurrency(currency);
        this.setBalance(balance);
    }
    
    public WalletBalance(long user, long id, long currency, double balance) {
        this(user, currency, balance);
        this.setId(id);
    }

    public long getCurrency() {
        return currency;
    }

    public void setCurrency(long currency) {
        this.currency = currency;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public long getUser() {
        return user;
    }

    public void setUser(long user) {
        this.user = user;
    }

}
