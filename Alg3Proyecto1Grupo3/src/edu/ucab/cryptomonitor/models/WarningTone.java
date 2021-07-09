package edu.ucab.cryptomonitor.models;

import com.paaksing.jjango.Manager;
import com.paaksing.jjango.Model;

public class WarningTone extends Model {
    
    private String name;
    private String filename;

    public static transient Manager<WarningTone> objects = new Manager<WarningTone>("db/warning_tones", WarningTone.class);

    @SuppressWarnings("unchecked")
    public Manager<WarningTone> getObjects() {
        return objects;
    }

    public WarningTone() { }
    
    public WarningTone(String name, String filename) {
        super();
        this.setName(name);
        this.setFilename(filename);
    }
    
    public WarningTone(long id, String name, String filename) {
        this(name, filename);
        this.setId(id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

}
