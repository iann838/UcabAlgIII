package com.ucabalg3.taller2;

import java.util.ArrayList;

public class Record {

	private ArrayList<Book> books;
	private ArrayList<Magazine> magazines;

	public Record() {
		super();
	};
	
	public Record(ArrayList<Book> books, ArrayList<Magazine> magazines) {
		super();
		this.books = books;
		this.magazines = magazines;
	}

	public ArrayList<Book> getBooks() {
		return books;
	}

	public void setBooks(ArrayList<Book> books) {
		this.books = books;
	}

	public ArrayList<Magazine> getMagazines() {
		return magazines;
	}

	public void setMagazines(ArrayList<Magazine> magazines) {
		this.magazines = magazines;
	}

}
