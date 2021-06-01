package com.ucabalg3.taller2;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
		/* Crear una clase Libro que maneje los siguientes atributos: ISBN,Titulo, Autor, N�mero de p�ginas
		Crear sus respectivos m�todos get y set correspondientes para cada atributo. Crear
		el m�todo toString() para mostrar la informaci�n relativa al libro con el siguiente formato:
		�El libro con ISBN creado por el autor tiene p�ginas�

		Crear una clase Revista que maneja: nombre, n�mero de revista, n�mero de p�ginas,
		fecha de la revista.
		�La revista TITULO tiene NUMERO DE PAGINAS�

		En el programa principal crear dos arreglos uno para libros y otro para revistas,
		donde la informaci�n ser� introducida por teclado, y luego mostrar el m�todo
		to_string por cada libro. La informaci�n debe almacenarse en un archivo. El men�
		debe contar con opciones para mostrar la informaci�n de todos los libros y de todas las revistas.
		La informaci�n se debe mostrar en formato de tabla como el ejercicio 4. */
		
		Scanner scan = new Scanner(System.in);
		
		Background background = new Background("data.json");
		ArrayList<Book> books = background.getBooks();
		ArrayList<Magazine> magazines = background.getMagazines();

		while (true) {
			System.out.println();
			System.out.println("1. Agregar un libro.");
			System.out.println("2. Agregar una revista.");
			System.out.println("3. Ver todos los libros.");
			System.out.println("4. Ver todas las revistas.");
			System.out.println("0. Salir.");
			
			System.out.println();
			System.out.print(">> Opcion: ");
			
			String option = scan.nextLine();
			System.out.println();

			try {
				if (option.equals("1")) {
					Book book = Book.fromStdin(scan);
					books.add(book);
					background.update();
					System.out.println();
					System.out.println(book.toString());
				} else if (option.equals("2")) {
					Magazine magazine = Magazine.fromStdin(scan);
					magazines.add(magazine);
					background.update();
					System.out.println();
					System.out.println(magazine.toString());
				} else if (option.equals("3")) {
					System.out.println(Book.tableHead());
					for (Book book : books) {
						System.out.println(book.tableRow());
					}
				} else if (option.equals("4")) {
					System.out.println(Magazine.tableHead());
					for (Magazine magazine : magazines) {
						System.out.println(magazine.tableRow());
					}
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
			} catch (InputMismatchException | ParseException e) {
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
