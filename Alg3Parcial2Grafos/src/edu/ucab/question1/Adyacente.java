package edu.ucab.question1;

public class Adyacente {

    private String nombreVertice1;
    private String nombreVertice2;
    private double peso;

    public Adyacente(String nombreVertice1, String nombreVertice2, double peso) {
        super();
        this.nombreVertice1 = nombreVertice1;
        this.nombreVertice2 = nombreVertice2;
        this.peso = peso;
    }

    public String getNombreVertice1() {
        return nombreVertice1;
    }

    public void setNombreVertice1(String nombreVertice1) {
        this.nombreVertice1 = nombreVertice1;
    }

    public String getNombreVertice2() {
        return nombreVertice2;
    }

    public void setNombreVertice2(String nombreVertice2) {
        this.nombreVertice2 = nombreVertice2;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

}
