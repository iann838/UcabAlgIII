package com.ucabalg3.taller2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Background {
	
	private String filename;
	private Record record;
	private Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").setPrettyPrinting().create();

	public Background(String filename) {
		this.filename = filename;
		try {
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			this.record = gson.fromJson(reader, Record.class);
			if (this.record == null) {
				this.record = new Record(new ArrayList<Book>(), new ArrayList<Magazine>());
			}
			reader.close();
		} catch (IOException e) {
			this.record = new Record(new ArrayList<Book>(), new ArrayList<Magazine>());
		}
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

	public void update() throws IOException {
		FileWriter writer = new FileWriter(this.getFilename());
		this.gson.toJson(this.getRecord(), writer);
        writer.close();
	}
}
