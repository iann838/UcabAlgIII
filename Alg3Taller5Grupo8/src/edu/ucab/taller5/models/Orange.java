package edu.ucab.taller5.models;

public class Orange extends Drink {

    private double discountPercent = 5;
    private double discountApplicableThreshold = 1000;
    private long calories;

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

    public double getCalories() {
        return calories;
    }

    public void setCalories(long calories) {
        this.calories = calories;
    }

}
