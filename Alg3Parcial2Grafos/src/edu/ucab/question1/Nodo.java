package edu.ucab.question1;

import java.util.ArrayList;

public class Nodo {

    private String nombre;
    private Double peso;
    private ArrayList<Adyacente> adyacentes;

    public Nodo(String nombre, Double peso) {
        super();
        this.nombre = nombre;
        this.peso = peso;
        this.adyacentes = new ArrayList<Adyacente>();
    }

    public Nodo(String nombre, Double peso, ArrayList<Adyacente> adyacentes) {
        super();
        this.nombre = nombre;
        this.peso = peso;
        this.adyacentes = adyacentes;
    }

    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public Double getPeso() {
        return peso;
    }
    
    public void setPeso(Double peso) {
        this.peso = peso;
    }
    
    public ArrayList<Adyacente> getAdyacentes() {
        return adyacentes;
    }
    
    public void setAdyacentes(ArrayList<Adyacente> adyacentes) {
        this.adyacentes = adyacentes;
    }

    public void agregarAdyacente(Adyacente adyacente) {
        this.adyacentes.add(adyacente);
    }

}
