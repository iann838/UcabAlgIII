package com.ucab.taller1;

import java.io.IOException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
		/* Implemente las clases: circulo, cuadrado, triangulo que represente la informaci�n de las
		respectivas figuras geom�tricas, solicite al usuario mediante 3 opciones el c�lculo del
		�rea para cualquier figura indicada y mostrar el resultado. Realice las instancias
		respectivas para realizar el c�lculo. */
		
		Scanner scan = new Scanner(System.in);

		while (true) {			
			System.out.println();
			System.out.println("1. Calcular el area de un cuadrado.");
			System.out.println("2. Calcular el area de un triangulo.");
			System.out.println("3. Calcular el area de un circulo.");
			System.out.println("0. Salir.");
			
			System.out.println();
			System.out.print(">> Opcion: ");
			
			String option = scan.nextLine();
			System.out.println();
			
			Shape shape = null;

			if (option.equals("1")) {				
				shape = Square.fromStdin(scan);
			} else if (option.equals("2")) {
				shape = Triangle.fromStdin(scan);
			} else if (option.equals("3")) {
				shape = Circle.fromStdin(scan);
			} else if (option.equals("0")) {
				System.out.println("Has salido.");
				break;
			} else
				continue;
			
			System.out.println();
			System.out.println("Area: " + Double.toString(shape.getArea()) + " unidades\u00b2");
			System.in.read();
		}

	}

}
