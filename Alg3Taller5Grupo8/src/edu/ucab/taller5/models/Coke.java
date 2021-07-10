package edu.ucab.taller5.models;

public class Coke extends Drink {

    private double discountPercent = 10;
    private double discountApplicableThreshold = 1000;
    private double colorantPercent;

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

    public double getColorantPercent() {
        return colorantPercent;
    }

    public void setColorantPercent(double colorantPercent) {
        this.colorantPercent = colorantPercent;
    }

}
