package edu.ucab.cryptomonitor.models;

import com.paaksing.jjango.Manager;
import com.paaksing.jjango.Model;

public class Alert extends Model {

    public enum Direction {
        GTE("gte"), LTE("lte");
        
        private final String directionCode;

        private Direction(String directionCode) {
            this.directionCode = directionCode;
        }

        public String getDirectionCode() {
            return directionCode;
        }
    }

    private long user;
    private long currency;
    private double price;
    private long warningTone;
    private Direction direction;

    public static transient Manager<Alert> objects = new Manager<Alert>("db/alerts", Alert.class);

    @SuppressWarnings("unchecked")
    public Manager<Alert> getObjects() {
        return objects;
    }

    public Alert() { }

    public Alert(long user, long currency, double price, long warningTone, Direction direction) {
        super();
        this.setUser(user);
        this.setCurrency(currency);
        this.setPrice(price);
        this.setDirection(direction);
        this.setWarningTone(warningTone);
    }

    public Alert(long id, long user, long currency, double price, long warningTone, Direction direction) {
        this(user, currency, price, warningTone, direction);
        this.setId(id);
    }

    public long getUser() {
        return user;
    }

    public void setUser(long user) {
        this.user = user;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public long getWarningTone() {
        return warningTone;
    }

    public void setWarningTone(long warningTone) {
        this.warningTone = warningTone;
    }

    public long getCurrency() {
        return currency;
    }

    public void setCurrency(long currency) {
        this.currency = currency;
    }

}
