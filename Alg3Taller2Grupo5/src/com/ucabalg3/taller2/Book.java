package com.ucabalg3.taller2;

import java.util.Scanner;

public class Book extends Print {

	private String isbn;
    private String author;

    public Book() {
    	super();
    }

	public Book(String isbn, String title, String author, long pageNum) {
		super(title, pageNum);
		this.isbn = isbn;
		this.author = author;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@Override
    public String toString() {
        return String.format(
        	"El libro %s creado por el autor %s tiene %d paginas",
        	this.getIsbn(), this.getAuthor(), this.getPageNum()
        );
    }

	public static Book fromStdin(Scanner scan) {
		System.out.print(">> ISBN: ");
		String isbn = scan.nextLine();
		System.out.print(">> Titulo: ");
		String title = scan.nextLine();
		System.out.print(">> Autor: ");
		String author = scan.nextLine();
		System.out.print(">> Paginas: ");
		long pageNum = scan.nextLong();
		scan.nextLine();
		return new Book(isbn, title, author, pageNum);
	}

	public static String tableHead() {
		return String.format(
			"%16s|%30s|%30s|%12s\n%16s|%30s|%30s|%12s",
			"ISBN", "Titulo del Libro", "Autor del Libro", "Paginas",
			"-".repeat(16), "-".repeat(30), "-".repeat(30), "-".repeat(12)
		);
	}

	@Override
	public String tableRow() {
		return String.format(
			"%16s|%30s|%30s|%12d",
			this.getIsbn(), this.getTitle(), this.getAuthor(), this.getPageNum()
		);
	}

}
