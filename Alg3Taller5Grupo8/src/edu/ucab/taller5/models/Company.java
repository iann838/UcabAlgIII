package edu.ucab.taller5.models;

import java.util.List;

public class Company {

    private String name;
    private List<Drink> drinks;
    private double total;
    private double totalVES;

    public void calculateTotal(double exchangeRateVES) {
        double total = 0;
        double totalVES = 0;
        for (Drink drink: drinks) {
            drink.calculateSubTotal(exchangeRateVES);
            total = total + drink.getSubTotal();
            totalVES = totalVES + drink.getSubTotalVES();
        }
        this.setTotal(total);
        this.setTotalVES(totalVES);
    }

    public List<Drink> getDrinks() {
        return drinks;
    }

    public void setDrinks(List<Drink> drinks) {
        this.drinks = drinks;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getTotalVES() {
        return totalVES;
    }

    public void setTotalVES(double totalVES) {
        this.totalVES = totalVES;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    } 

}
