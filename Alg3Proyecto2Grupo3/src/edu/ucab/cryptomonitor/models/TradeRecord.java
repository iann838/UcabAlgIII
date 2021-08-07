package edu.ucab.cryptomonitor.models;

import com.paaksing.jjango.Manager;
import com.paaksing.jjango.Model;

public class TradeRecord extends Model {
    
    private long user;
    private long currency;
    private double amount;

    public static transient Manager<TradeRecord> objects = new Manager<TradeRecord>("db/trade-records", TradeRecord.class);
    
    @SuppressWarnings("unchecked")
    @Override
    public Manager<TradeRecord> getObjects() {
        return objects;
    }
    
    public TradeRecord() {}
    
    public TradeRecord(long user, long currency, double amount) {
        this.setUser(user);
        this.setCurrency(currency);
        this.setAmount(amount);
    }
    
    public TradeRecord(long user, long id, long currency, double amount) {
        this(user, currency, amount);
        this.setId(id);
    }
    
    public long getUser() {
        return user;
    }

    public void setUser(long user) {
        this.user = user;
    }

    public long getCurrency() {
        return currency;
    }

    public void setCurrency(long currency) {
        this.currency = currency;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

}
