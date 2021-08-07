package edu.ucab.cryptomonitor.models;

import java.util.Date;
import com.paaksing.jjango.Manager;
import com.paaksing.jjango.Model;

/**
 * Esta clase contiene la informacion de las criptomonedas
 * @version 1.0
 */

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

    /**
     * Metodo que regresa la lista de criptomonedas almacenadas
     * @return Regresa la lista de monedas
     */
    
    @SuppressWarnings("unchecked")
    public Manager<Currency> getObjects() {
        return objects;
    }
    
    /**
     * Metodo constructor por defecto
     */

    public Currency() { }
    
    /**
     * Metodo constructor parametrizado
     * @param rank Rango de la moneda en el mercado
     * @param tag Abreviatura de la moneda
     * @param name Nombre de la moneda
     * @param price Precio actual de la moneda
     * @param price1hpc Variacion del precio en la ultima hora
     * @param price24hpc Variacion del precio las ultimas 24 horas
     * @param price7dpc Variacion del precio los ultimos 7 dias
     * @param price30dpc Variacion del precio el ultimo mes
     * @param volume Volumen circulante de la moneda en el mercado
     * @param volume24hpc
     * @param totalSupply
     * @param marketCap
     * @param marketCap24hpc
     * @param circulatingSupply
     * @param fullyDilutedMarketCap
     * @param fullyDilutedMarketCap24hpc
     * @param lastUpdated
     */

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
    
    /**
     * Metodo constructor parametrizado
     * @param id Identificador de la moneda
     * @param rank Rango de la moneda en el mercado
     * @param tag Abreviatura de la moneda
     * @param name Nombre de la moneda
     * @param price Precio actual de la moneda
     ** @param price1hpc Variacion del precio en la ultima hora
     * @param price24hpc Variacion del precio las ultimas 24 horas
     * @param price7dpc Variacion del precio los ultimos 7 dias
     * @param price30dpc Variacion del precio el ultimo mes
     * @param volume Volumen circulante de la moneda en el mercado
     * @param volume24hpc 
     * @param totalSupply
     * @param marketCap
     * @param marketCap24hpc
     * @param circulatingSupply
     * @param fullyDilutedMarketCap
     * @param fullyDilutedMarketCap24hpc
     * @param lastUpdated
     */
    
    public Currency(long id, int rank, String tag, String name, double price, double price1hpc, double price24hpc,
            double price7dpc, double price30dpc, double volume, double volume24hpc, double totalSupply,
            double marketCap, double marketCap24hpc, double circulatingSupply, double fullyDilutedMarketCap,
            double fullyDilutedMarketCap24hpc, Date lastUpdated) {
        this(rank, tag, name, price, price1hpc, price24hpc, price7dpc, price30dpc, volume, volume24hpc,
                totalSupply, marketCap, marketCap24hpc, circulatingSupply, fullyDilutedMarketCap,
                fullyDilutedMarketCap24hpc, lastUpdated);
        this.setId(id);
    }
    
    /**
     * Metodo para obtener el rango
     * @return Regresa el rango 
     */

    public int getRank() {
        return rank;
    }
    
   /**
    * Metodo para colocar el rango
    * @param rank Valor que se asigna
    */

    public void setRank(int rank) {
        this.rank = rank;
    }
    
    /**
     * Metodo para obtener la etiqueta
     * @return Regresa la etiqueta
     */

    public String getTag() {
        return tag;
    }
    
    /**
     * Metodo para colocar la etiqueta
     * @param tag Etiqueta que se asigna
     */

    public void setTag(String tag) {
        this.tag = tag;
    }
    
    /**
     * Metodo para obtener el nombre
     * @return Regresa el nombre
     */

    public String getName() {
        return name;
    }
    
    /**
     * Metodo para colocar el nombre
     * @param tag Nombre que se asigna
     */

    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Metodo para obtener el precio
     * @return Regresa el precio 
     */

    public double getPrice() {
        return price;
    }
    
    /**
     * Metodo para colocar el precio
     * @param price Valor que se asigna
     */

    public void setPrice(double price) {
        this.price = price;
    }
    
    /**
     * Metodo para obtener la variacion de precio la ultima hora
     * @return Regresa la variacion
     */

    public double getPrice1hpc() {
        return price1hpc;
    }
    
    /**
     * Metodo para colocar la variacion de precio la ultima hora
     * @param price1hpc Valor que se asigna
     */

    public void setPrice1hpc(double price1hpc) {
        this.price1hpc = price1hpc;
    }
    
    /**
     * Metodo para obtener la variacion de precio las ultimas 24 horas
     * @return Regresa la variacion
     */

    public double getPrice24hpc() {
        return price24hpc;
    }

    /**
     * Metodo para colocar la variacion de precio las ultimas 24 horas
     * @param price24hpc Valor que se asigna
     */
    
    public void setPrice24hpc(double price24hpc) {
        this.price24hpc = price24hpc;
    }
    
    /**
     * Metodo para obtener la variacion de precio la ultima semana
     * @return Regresa la variacion
     */

    public double getPrice7dpc() {
        return price7dpc;
    }
    
    /**
     * Metodo para colocar la variacion de precio la ultima semana
     * @param price7dpc Valor que se asigna
     */

    public void setPrice7dpc(double price7dpc) {
        this.price7dpc = price7dpc;
    }
    
    /**
     * Metodo para obtener la variacion de precio el ultimo mes
     * @return Regresa la variacion
     */

    public double getPrice30dpc() {
        return price30dpc;
    }
    
    /**
     * Metodo para colocar la variacion de precio el ultimo mes
     * @param price30dpc Valor que se asigna
     */

    public void setPrice30dpc(double price30dpc) {
        this.price30dpc = price30dpc;
    }
    
    /**
     * Metodo para obtener el volumen de moneda circulante
     * @return Regresa el volumen
     */

    public double getVolume() {
        return volume;
    }
    
    /**
     * Metodo para colocar el volumen de moneda circulante
     * @param volume Valor que se asigna 
     */

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
