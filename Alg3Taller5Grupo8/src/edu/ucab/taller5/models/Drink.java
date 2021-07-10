package edu.ucab.taller5.models;

import java.util.Date;

public class Drink {
    
    public enum Type {

        COKE("coke"),
        ORANGE("orange"),
        LEMON("lemon");
        
        public final String label;

        private Type(String label) {
            this.label = label;
        }
        
        public static Type nameOf(String label){
            for(Type e : Type.values()){
                if(e.label.equals(label)) return e;
            }
            return null;
        }

    }
    
    private Type type;
    private String name;
    private Date creation;
    private Date expiration;
    private double price;
    private long amountSold;
    private transient double discountPercent;
    private transient double discountApplicableThreshold;
    private double subTotal;
    private double subTotalVES;

    public double getDiscountMultiplier() {
        return (100 - this.getDiscountPercent()) / 100;
    }
    
    public void calculateSubTotal(double exchangeRateVES) {
        if (this.getAmountSold() > this.getDiscountApplicableThreshold()) {
            this.setSubTotal(this.getAmountSold() * this.getPrice() * this.getDiscountMultiplier());
        } else {
            this.setSubTotal(this.getAmountSold() * this.getPrice());
        }
        this.setSubTotalVES(this.getSubTotal() * exchangeRateVES);
    }
    
    public Type getType() {
        return type;
    }
    public void setType(Type type) {
        this.type = type;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Date getCreation() {
        return creation;
    }
    public void setCreation(Date creation) {
        this.creation = creation;
    }
    public Date getExpiration() {
        return expiration;
    }
    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public long getAmountSold() {
        return amountSold;
    }
    public void setAmountSold(long amountSold) {
        this.amountSold = amountSold;
    }

    public double getSubTotalVES() {
        return subTotalVES;
    }

    public void setSubTotalVES(double subTotalVES) {
        this.subTotalVES = subTotalVES;
    }

    public double getDiscountApplicableThreshold() {
        return discountApplicableThreshold;
    }

    public void setDiscountApplicableThreshold(double discountApplicableThreshold) {
        this.discountApplicableThreshold = discountApplicableThreshold;
    }

    public double getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(double discountPercent) {
        this.discountPercent = discountPercent;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

}
