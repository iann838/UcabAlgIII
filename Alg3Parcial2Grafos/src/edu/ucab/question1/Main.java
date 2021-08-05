package edu.ucab.question1;

public class Main {

    public static void main(String[] args) {
        MatrizAdyacencia matrix;
        Nodo nodo1;
        Nodo nodo2;
        Nodo nodo3;

        matrix = new MatrizAdyacencia();
        nodo1 = new Nodo("1", 2.0);
        nodo2 = new Nodo("2", 1.0);
        nodo1.agregarAdyacente(new Adyacente(nodo1.getNombre(), nodo2.getNombre(), 1));
        nodo2.agregarAdyacente(new Adyacente(nodo2.getNombre(), nodo1.getNombre(), 1));
        matrix.agregarNodo(nodo1);
        matrix.agregarNodo(nodo2);
        System.out.println(matrix.esCompleto()); // True

        matrix = new MatrizAdyacencia();
        nodo1 = new Nodo("1", 2.0);
        nodo2 = new Nodo("2", 1.0);
        nodo3 = new Nodo("3", 1.0);
        nodo1.agregarAdyacente(new Adyacente(nodo1.getNombre(), nodo2.getNombre(), 1));
        nodo1.agregarAdyacente(new Adyacente(nodo1.getNombre(), nodo3.getNombre(), 1));
        nodo2.agregarAdyacente(new Adyacente(nodo2.getNombre(), nodo1.getNombre(), 1));
        nodo2.agregarAdyacente(new Adyacente(nodo2.getNombre(), nodo3.getNombre(), 1));
        nodo3.agregarAdyacente(new Adyacente(nodo3.getNombre(), nodo1.getNombre(), 1));
        nodo3.agregarAdyacente(new Adyacente(nodo3.getNombre(), nodo2.getNombre(), 1));
        matrix.agregarNodo(nodo1);
        matrix.agregarNodo(nodo2);
        matrix.agregarNodo(nodo3);
        System.out.println(matrix.esCompleto()); // True
        
        matrix = new MatrizAdyacencia();
        nodo1 = new Nodo("1", 2.0);
        nodo2 = new Nodo("2", 1.0);
        nodo3 = new Nodo("3", 1.0);
        nodo1.agregarAdyacente(new Adyacente(nodo1.getNombre(), nodo2.getNombre(), 1));
        nodo1.agregarAdyacente(new Adyacente(nodo1.getNombre(), nodo3.getNombre(), 1));
//        nodo2.agregarAdyacente(new Adyacente(nodo2.getNombre(), nodo1.getNombre(), 1));
        nodo2.agregarAdyacente(new Adyacente(nodo2.getNombre(), nodo3.getNombre(), 1));
        nodo3.agregarAdyacente(new Adyacente(nodo3.getNombre(), nodo1.getNombre(), 1));
//        nodo3.agregarAdyacente(new Adyacente(nodo3.getNombre(), nodo2.getNombre(), 1)); FALTA ADYACENCIA
        matrix.agregarNodo(nodo1);
        matrix.agregarNodo(nodo2);
        matrix.agregarNodo(nodo3);
        System.out.println(matrix.esCompleto()); // False
        
        matrix = new MatrizAdyacencia();
        nodo1 = new Nodo("1", 2.0);
        nodo2 = new Nodo("2", 1.0);
        nodo3 = new Nodo("3", 1.0);
        nodo1.agregarAdyacente(new Adyacente("5", nodo2.getNombre(), 1)); // NODO NO EXISTE
        nodo1.agregarAdyacente(new Adyacente(nodo1.getNombre(), nodo3.getNombre(), 1));
        nodo2.agregarAdyacente(new Adyacente(nodo2.getNombre(), nodo1.getNombre(), 1));
        nodo2.agregarAdyacente(new Adyacente(nodo2.getNombre(), nodo3.getNombre(), 1));
        nodo3.agregarAdyacente(new Adyacente(nodo3.getNombre(), nodo1.getNombre(), 1));
        nodo3.agregarAdyacente(new Adyacente(nodo3.getNombre(), nodo2.getNombre(), 1));
        matrix.agregarNodo(nodo1);
        matrix.agregarNodo(nodo2);
        matrix.agregarNodo(nodo3);
        System.out.println(matrix.esCompleto()); // False

        matrix = new MatrizAdyacencia();
        nodo1 = new Nodo("1", 2.0);
        nodo2 = new Nodo("2", 1.0);
        nodo1.agregarAdyacente(new Adyacente(nodo1.getNombre(), nodo1.getNombre(), 1)); // bucle
        nodo2.agregarAdyacente(new Adyacente(nodo2.getNombre(), nodo1.getNombre(), 1));
        matrix.agregarNodo(nodo1);
        matrix.agregarNodo(nodo2);
        System.out.println(matrix.esCompleto()); // False
        
        matrix = new MatrizAdyacencia();
        nodo1 = new Nodo("1", 2.0);
        nodo2 = new Nodo("2", 1.0);
        nodo1.agregarAdyacente(new Adyacente(nodo1.getNombre(), nodo2.getNombre(), 1)); // multigrafo
        nodo1.agregarAdyacente(new Adyacente(nodo1.getNombre(), nodo2.getNombre(), 1)); // multigrafo
        nodo2.agregarAdyacente(new Adyacente(nodo2.getNombre(), nodo1.getNombre(), 1));
        matrix.agregarNodo(nodo1);
        matrix.agregarNodo(nodo2);
        System.out.println(matrix.esCompleto()); // False
        
        matrix = new MatrizAdyacencia();
        nodo1 = new Nodo("1", 2.0);
        nodo2 = new Nodo("2", 1.0);
        nodo2.agregarAdyacente(new Adyacente(nodo2.getNombre(), nodo1.getNombre(), 1)); // Falta una adyacente
        matrix.agregarNodo(nodo1);
        matrix.agregarNodo(nodo2);
        System.out.println(matrix.esCompleto()); // False
    }

}
