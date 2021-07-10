package edu.ucab.taller5.models;

public class Lemon extends Drink {

    private double discountPercent = 2.5;
    private double discountApplicableThreshold = 500;
    private long lemonMl;

    public void setDiscountPercent(double discountPercent) {
        this.discountPercent = discountPercent;
    }

    public void setDiscountApplicableThreshold(double discountApplicableThreshold) {
        this.discountApplicableThreshold = discountApplicableThreshold;
    }

    public double getDiscountPercent() {
        return discountPercent;
    }

    public double getDiscountApplicableThreshold() {
        return discountApplicableThreshold;
    }

    public double getLemonMl() {
        return lemonMl;
    }

    public void setLemonMl(long lemonMl) {
        this.lemonMl = lemonMl;
    }

}
