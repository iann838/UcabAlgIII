package edu.ucab.cryptomonitor.models;

import java.util.Date;

import com.paaksing.jjango.Manager;
import com.paaksing.jjango.Model;

public class Currency extends Model {

    private int rank;
    private String tag;
    private String name;
    private double price;
    private double price1hpc;
    private double price24hpc;
    private double price7dpc;
    private double price30dpc;
    private double volume;
    private double volume24hpc;
    private double totalSupply;
    private double marketCap;
    private double marketCap24hpc;
    private double circulatingSupply;
    private double fullyDilutedMarketCap;
    private double fullyDilutedMarketCap24hpc;
    private Date lastUpdated;

    public static transient Manager<Currency> objects = new Manager<Currency>("db/currencies", Currency.class);

    @SuppressWarnings("unchecked")
    public Manager<Currency> getObjects() {
        return objects;
    }

    public Currency() { }

    public Currency(int rank, String tag, String name, double price, double price1hpc, double price24hpc,
            double price7dpc, double price30dpc, double volume, double volume24hpc, double totalSupply,
            double marketCap, double marketCap24hpc, double circulatingSupply, double fullyDilutedMarketCap,
            double fullyDilutedMarketCap24hpc, Date lastUpdated) {
        super();
        this.setRank(rank);
        this.setTag(tag);
        this.setName(name);
        this.setPrice(price);
        this.setPrice1hpc(price1hpc);
        this.setPrice24hpc(price24hpc);
        this.setPrice7dpc(price7dpc);
        this.setPrice30dpc(price30dpc);
        this.setVolume(volume);
        this.setVolume24hpc(volume24hpc);
        this.setTotalSupply(totalSupply);
        this.setMarketCap(marketCap);
        this.setMarketCap24hpc(marketCap24hpc);
        this.setCirculatingSupply(circulatingSupply);
        this.setFullyDilutedMarketCap(fullyDilutedMarketCap);
        this.setFullyDilutedMarketCap24hpc(fullyDilutedMarketCap24hpc);
        this.setLastUpdated(lastUpdated);
    }
    
    public Currency(long id, int rank, String tag, String name, double price, double price1hpc, double price24hpc,
            double price7dpc, double price30dpc, double volume, double volume24hpc, double totalSupply,
            double marketCap, double marketCap24hpc, double circulatingSupply, double fullyDilutedMarketCap,
            double fullyDilutedMarketCap24hpc, Date lastUpdated) {
        this(rank, tag, name, price, price1hpc, price24hpc, price7dpc, price30dpc, volume, volume24hpc,
                totalSupply, marketCap, marketCap24hpc, circulatingSupply, fullyDilutedMarketCap,
                fullyDilutedMarketCap24hpc, lastUpdated);
        this.setId(id);
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
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

    public double getPrice1hpc() {
        return price1hpc;
    }

    public void setPrice1hpc(double price1hpc) {
        this.price1hpc = price1hpc;
    }

    public double getPrice24hpc() {
        return price24hpc;
    }

    public void setPrice24hpc(double price24hpc) {
        this.price24hpc = price24hpc;
    }

    public double getPrice7dpc() {
        return price7dpc;
    }

    public void setPrice7dpc(double price7dpc) {
        this.price7dpc = price7dpc;
    }

    public double getPrice30dpc() {
        return price30dpc;
    }

    public void setPrice30dpc(double price30dpc) {
        this.price30dpc = price30dpc;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public double getVolume24hpc() {
        return volume24hpc;
    }

    public void setVolume24hpc(double volume24hpc) {
        this.volume24hpc = volume24hpc;
    }

    public double getTotalSupply() {
        return totalSupply;
    }

    public void setTotalSupply(double totalSupply) {
        this.totalSupply = totalSupply;
    }

    public double getMarketCap() {
        return marketCap;
    }

    public void setMarketCap(double marketCap) {
        this.marketCap = marketCap;
    }

    public double getMarketCap24hpc() {
        return marketCap24hpc;
    }

    public void setMarketCap24hpc(double marketCap24hpc) {
        this.marketCap24hpc = marketCap24hpc;
    }

    public double getCirculatingSupply() {
        return circulatingSupply;
    }

    public void setCirculatingSupply(double circulatingSupply) {
        this.circulatingSupply = circulatingSupply;
    }

    public double getFullyDilutedMarketCap() {
        return fullyDilutedMarketCap;
    }

    public void setFullyDilutedMarketCap(double fullyDilutedMarketCap) {
        this.fullyDilutedMarketCap = fullyDilutedMarketCap;
    }

    public double getFullyDilutedMarketCap24hpc() {
        return fullyDilutedMarketCap24hpc;
    }

    public void setFullyDilutedMarketCap24hpc(double fullyDilutedMarketCap24hpc) {
        this.fullyDilutedMarketCap24hpc = fullyDilutedMarketCap24hpc;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

}
