package edu.ucab.taller5.models;

import java.util.List;

public class Data {
    
    private List<Company> companies;
    private double exchangeRateVES;
    
    public void calculateCompanyTotals() {
        for (Company company: this.getCompanies()) {
            company.calculateTotal(this.getExchangeRateVES());
        }
    }

    public List<Company> getCompanies() {
        return companies;
    }
    public void setCompanies(List<Company> companies) {
        this.companies = companies;
    }
    public double getExchangeRateVES() {
        return exchangeRateVES;
    }
    public void setExchangeRateVES(double uSDtoVESExchangeRate) {
        this.exchangeRateVES = uSDtoVESExchangeRate;
    }

}
