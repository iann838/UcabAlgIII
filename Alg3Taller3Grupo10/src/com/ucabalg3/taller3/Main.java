package com.ucabalg3.taller3;

import java.io.IOException;
import java.text.ParseException;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Main {

	public static void main(String[] args) throws IOException {
//		Se desea desarrollar una aplicaci�n que permita guardar informaci�n sobre discos de m�sica (CD) y pel�culas (DVD).
//		- CD: t�tulo, artista, cantidad de canciones, tiempo de reproducci�n total, tiempo de reproducci�n por cada canci�n, titulo de cada canci�n, comentario, genero.
//		- DVD: t�tulo, director, tiempo de reproducci�n, comentario, genero.
//		- Se desea poder buscar informaci�n de todos los CD y de todos los DVD
//		- Se desea buscar toda la informaci�n de todos los CD dado un g�nero as� como tambi�n de los DVD.
//		- Debe haber una clase padre llamada: Medio. 
//		- No se puede instanciar la clase padre y no se puede verificar que instancia es para mostrar la informaci�n.

		Scanner scan = new Scanner(System.in);

		MediaList<CD> cds = new MediaList<CD>();
		MediaList<DVD> dvds = new MediaList<DVD>();

		while (true) {
			System.out.println();
			System.out.println("1. Agregar un CD.");
			System.out.println("2. Agregar un DVD.");
			System.out.println("3. Ver todos los CD's.");
			System.out.println("4. Ver todas los DVD's.");
			System.out.println("5. Buscar CD's por genero.");
			System.out.println("6. Buscar DVD's por genero.");
			System.out.println("0. Salir.");
			
			System.out.println();
			System.out.print(">> Opcion: ");
			
			String option = scan.nextLine();
			System.out.println();
			
			try {
				if (option.equals("1")) {
					CD cd = CD.fromStdin(scan);
					cds.add(cd);
				} else if (option.equals("2")) {
					DVD dvd = DVD.fromStdin(scan);
					dvds.add(dvd);
				} else if (option.equals("3")) {
					System.out.println(CD.tableHead());
					for (CD cd: cds)
						System.out.println(cd.tableRow());
				} else if (option.equals("4")) {
					System.out.println(DVD.tableHead());
					for (DVD dvd: dvds)
						System.out.println(dvd.tableRow());
				} else if (option.equals("5")) {
					System.out.print(">> Genero: ");
					String genre = scan.nextLine();
					System.out.println();
					System.out.println(CD.tableHead());
					for (CD cd: cds.filterByGenre(genre))
						System.out.println(cd.tableRow());
				} else if (option.equals("6")) {
					System.out.print(">> Genero: ");
					String genre = scan.nextLine();
					System.out.println();
					System.out.println(DVD.tableHead());
					for (DVD dvd: dvds.filterByGenre(genre))
						System.out.println(dvd.tableRow());
				} else if (option.equals("0")) {
					System.out.println("Has salido.");
					scan.close();
					break;
				} else {
					System.out.println("Opcion no existe.");
					System.in.read();
					scan.nextLine();
					continue;
				}
			} catch (InputMismatchException | ParseException | DateTimeParseException e) {
				if (e instanceof InputMismatchException)
					scan.nextLine();
				System.out.println();
				System.out.println("Entrada invalida.");
				System.in.read();
				scan.nextLine();
				continue;
			}

			System.in.read();
			scan.nextLine();
		}

	}

}
