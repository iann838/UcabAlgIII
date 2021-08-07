package edu.ucab.cryptomonitor.models;

import com.paaksing.jjango.Manager;
import com.paaksing.jjango.Model;

/**
     * Clase que representa la billetera del usuario
     * version 1.0
     */

public class WalletBalance extends Model {
    
    private long user;
    private long currency;
    private double balance;
    private double usdtInversion;

    public static transient Manager<WalletBalance> objects = new Manager<WalletBalance>("db/wallet-balances", WalletBalance.class);
    
    /**
     * Metodo que regresa la lista de billeteras registradas
     * @return Regresa la lista de billeteras
     */
    
    @SuppressWarnings("unchecked")
    @Override
    public Manager<WalletBalance> getObjects() {
        return objects;
    }
    
    /**
     * Metodo constructor por defecto
     */
    
    public WalletBalance() {}
    
    /**
     * Metodo constructor parametrizado
     * @param user Usuario al que le pertenece la billetera
     * @param currency 
     * @param balance
     */
    
    public WalletBalance(long user, long currency, double balance, double usdtInversion) {
        this.setUser(user);
        this.setCurrency(currency);
        this.setBalance(balance);
        this.setUsdtInversion(usdtInversion);
    }
    
    public WalletBalance(long user, long id, long currency, double balance, double usdtInversion) {
        this(user, currency, balance, usdtInversion);
        this.setId(id);
    }
    
    public double getUsdtInversion() {
        return usdtInversion;
    }
    
    public void setUsdtInversion(double usdtInversion) {
        this.usdtInversion = usdtInversion;
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
