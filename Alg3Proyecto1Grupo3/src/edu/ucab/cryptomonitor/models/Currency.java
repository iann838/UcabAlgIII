package edu.ucab.cryptomonitor.models;

import java.util.Date;

import com.paaksing.jjango.Manager;
import com.paaksing.jjango.Model;

public class Currency extends Model {

    private int rank;
    private String tag;
    private String name;
    private double price;
    private double marketCap;
    private double volume24h;
    private double percentChange1h;
    private double percentChange24h;
    private double percentChange7d;
    private Date lastUpdated;

    public static transient Manager<Currency> objects = new Manager<Currency>("db/currencies", Currency.class);

    @SuppressWarnings("unchecked")
    public Manager<Currency> getObjects() {
        return objects;
    }

    public Currency() { }

    public Currency(int rank, String tag, String name, double price, double marketCap, double volume24h, double percentChange1h,
            double percentChange24h, double percentChange7d, Date lastUpdated) {
        super();
        this.setRank(rank);
        this.setTag(tag);
        this.setName(name);
        this.setPrice(price);
        this.setMarketCap(marketCap);
        this.setVolume24h(volume24h);
        this.setPercentChange1h(percentChange1h);
        this.setPercentChange24h(percentChange24h);
        this.setPercentChange7d(percentChange7d);
        this.setLastUpdated(lastUpdated);
    }

    public Currency(long id, int rank, String tag, String name, double price, double marketCap, double volume24h, double percentChange1h,
            double percentChange24h, double percentChange7d, Date lastUpdated) {
        this(rank, tag, name, price, marketCap, volume24h, percentChange1h, percentChange24h, percentChange7d, lastUpdated);
        this.setId(id);
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getMarketCap() {
        return marketCap;
    }

    public void setMarketCap(double marketCap) {
        this.marketCap = marketCap;
    }

    public double getVolume24h() {
        return volume24h;
    }

    public void setVolume24h(double volume24h) {
        this.volume24h = volume24h;
    }

    public double getPercentChange1h() {
        return percentChange1h;
    }

    public void setPercentChange1h(double percentChange1h) {
        this.percentChange1h = percentChange1h;
    }

    public double getPercentChange24h() {
        return percentChange24h;
    }

    public void setPercentChange24h(double percentChange24h) {
        this.percentChange24h = percentChange24h;
    }

    public double getPercentChange7d() {
        return percentChange7d;
    }

    public void setPercentChange7d(double percentChange7d) {
        this.percentChange7d = percentChange7d;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

}
