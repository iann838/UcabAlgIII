package com.ucabalg3.taller3;

import java.io.IOException;
import java.text.ParseException;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Main {

	public static void main(String[] args) throws IOException {
//		Se desea desarrollar una aplicación que permita guardar información sobre discos de música (CD) y películas (DVD).
//		- CD: título, artista, cantidad de canciones, tiempo de reproducción total, tiempo de reproducción por cada canción, titulo de cada canción, comentario, genero.
//		- DVD: título, director, tiempo de reproducción, comentario, genero.
//		- Se desea poder buscar información de todos los CD y de todos los DVD
//		- Se desea buscar toda la información de todos los CD dado un género así como también de los DVD.
//		- Debe haber una clase padre llamada: Medio. 
//		- No se puede instanciar la clase padre y no se puede verificar que instancia es para mostrar la información.

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
