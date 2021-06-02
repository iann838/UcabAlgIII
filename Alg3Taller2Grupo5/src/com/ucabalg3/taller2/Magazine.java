package com.ucabalg3.taller2;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Magazine extends Print {

	private long id;
	private Date date;

    public Magazine() {
    	super();
    }

	public Magazine(long id, String title, long pageNum, Date date) {
		super(title, pageNum);
		this.id = id;
		this.date = date;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	@Override
	public String toString() {
		return String.format(
			"La revista %s tiene %d paginas",
			this.getTitle(), this.getPageNum()
		);
	}

	public static Magazine fromStdin(Scanner scan) throws ParseException {
		System.out.print(">> ID: ");
		long id = scan.nextLong();
		scan.nextLine();
		System.out.print(">> Titulo: ");
		String title = scan.nextLine();
		System.out.print(">> Paginas: ");
		long pageNum = scan.nextLong();
		scan.nextLine();
		System.out.print(">> Fecha (dd/MM/yyyy): ");
		Date date = new SimpleDateFormat("dd/MM/yyyy").parse(scan.nextLine());
		return new Magazine(id, title, pageNum, date);
	}

	public static String tableHead() {
		return String.format(
			"%16s|%30s|%12s|%16s\n%16s|%30s|%12s|%16s",
			"ID", "Titulo de la Revista", "Paginas", "Fecha",
			"-".repeat(16), "-".repeat(30), "-".repeat(12), "-".repeat(16)
		);
	}

	@Override
	public String tableRow() {
		return String.format(
			"%16s|%30s|%12d|%16s",
			this.getId(), this.getTitle(), this.getPageNum(), new SimpleDateFormat("dd/MM/yyyy").format(this.getDate())
		);
	}

}
