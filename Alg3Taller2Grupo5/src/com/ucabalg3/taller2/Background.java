package com.ucabalg3.taller2;

import java.util.ArrayList;


public class Background {
	
	private String filename;
	private Record record;

	public Background() {
		this.record = new Record(new ArrayList<Book>(), new ArrayList<Magazine>());
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public Record getRecord() {
		return this.record;
	}

	public void setRecord(Record record) {
		this.record = record;
	}

	public ArrayList<Book> getBooks() {
		return this.record.getBooks();
	}

	public ArrayList<Magazine> getMagazines() {
		return this.record.getMagazines();
	}

}
