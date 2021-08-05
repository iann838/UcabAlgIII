package edu.ucab.question1;

import java.util.ArrayList;

public class MatrizAdyacencia {
    // odio programar spanish

    private ArrayList<Nodo> nodos;
    
    public MatrizAdyacencia() {
        this.nodos = new ArrayList<Nodo>();
    }

    public MatrizAdyacencia(ArrayList<Nodo> nodos) {
        this.nodos = nodos;
    }

    public ArrayList<Nodo> getNodos() {
        return nodos;
    }

    public void setNodos(ArrayList<Nodo> nodos) {
        this.nodos = nodos;
    }

    public void agregarNodo(Nodo nodo) {
        this.nodos.add(nodo);
    }

    public boolean esCompleto() {
        boolean esCompletoBool = true;
        ArrayList<String> nodoNombres = new ArrayList<String>();
        for (Nodo nodo: this.getNodos()) {
            nodoNombres.add(nodo.getNombre());
        }
        for (Nodo nodo: this.getNodos()) {
            ArrayList<String> visitados = new ArrayList<String>();
            for (Adyacente adyacente: nodo.getAdyacentes()) {
                if (
                        adyacente.getNombreVertice1() == adyacente.getNombreVertice2() || // HAY BUCLE
                        !nodoNombres.contains(adyacente.getNombreVertice1()) || // VERTICE 1 NO EXISTE
                        !nodoNombres.contains(adyacente.getNombreVertice2()) || // VERTICE 2 NO EXISTE
                        visitados.contains(adyacente.getNombreVertice1() + "__" + adyacente.getNombreVertice2()) // MULTIPLE ARISTAS DE MISMO VERTICES
                ) {
                    esCompletoBool = false;
                    break;
                }
                visitados.add(adyacente.getNombreVertice1() + "__" + adyacente.getNombreVertice2());
            }
            if (visitados.size() != this.getNodos().size() - 1)
                esCompletoBool = false;
            if (!esCompletoBool)
                break;
        }
        return esCompletoBool;
    }

}
