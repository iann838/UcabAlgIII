package com.ucab.taller1;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
		/* Implemente las clases: circulo, cuadrado, triangulo que represente la información de las
		respectivas figuras geométricas, solicite al usuario mediante 3 opciones el cálculo del
		área para cualquier figura indicada y mostrar el resultado. Realice las instancias
		respectivas para realizar el cálculo. */
		
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

			try {
				if (option.equals("1")) {				
					shape = Square.fromStdin(scan);
				} else if (option.equals("2")) {
					shape = Triangle.fromStdin(scan);
				} else if (option.equals("3")) {
					shape = Circle.fromStdin(scan);
				} else if (option.equals("0")) {
					System.out.println("Has salido.");
					break;
				} else {
					System.out.println("Opcion no existe.");
					System.in.read();
					scan.nextLine();
					continue;
				}
			} catch (InputMismatchException e) {
				System.out.println("No es una medida.");
				System.in.read();
				scan.nextLine();
				continue;
			}

			System.out.println();
			System.out.println("Area: " + Double.toString(shape.getArea()) + " unid\u00b2");
			System.in.read();
			scan.nextLine();
		}

	}

}
