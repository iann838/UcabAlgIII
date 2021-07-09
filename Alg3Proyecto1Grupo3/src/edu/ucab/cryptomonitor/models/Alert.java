package edu.ucab.cryptomonitor.models;

import com.paaksing.jjango.Manager;
import com.paaksing.jjango.Model;

public class Alert extends Model {

    private int user;
    private float price;
    private String filename;

    public static transient Manager<Alert> objects = new Manager<Alert>("db/alerts", Alert.class);

    @SuppressWarnings("unchecked")
    public Manager<Alert> getObjects() {
        return objects;
    }

}
