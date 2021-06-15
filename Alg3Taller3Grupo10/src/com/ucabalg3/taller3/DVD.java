package com.ucabalg3.taller3;

import java.text.ParseException;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Scanner;

public class DVD implements Media {

//	título, director, tiempo de reproducción, comentario, genero
	
	private String title;
	private String director;
	private Timedelta duration;
	private String comment;
	private String genre;

	public DVD(String title, String director, Timedelta duration, String comment, String genre) {
		super();
		this.title = title;
		this.director = director;
		this.duration = duration;
		this.comment = comment;
		this.genre = genre;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public Timedelta getDuration() {
		return duration;
	}

	public void setDuration(Timedelta duration) {
		this.duration= duration;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	@Override
	public String toString() {
		return String.format(
			"Creado %s <titulo: %s | duracion: %s | genero: %s>",
			this.getClass().getName(),
			this.getTitle(),
			this.getDuration().toString(),
			this.getGenre()
		);
	}

	public static String tableHead() {
		return String.format(
			"%-30s|%-30s|%-12s|%-16s|%-30s\n%30s|%30s|%12s|%16s|%30s",
			"Titulo", "Director", "Duracion", "Genero", "Comentario",
			"-".repeat(30), "-".repeat(30), "-".repeat(12), "-".repeat(16), "-".repeat(30)
		);
	}

	@Override
	public String tableRow() {
		return String.format(
			"%-30s|%-30s|%-12s|%-16s|%-30s",
			this.getTitle(),
			this.getDirector(),
			this.getDuration().toString(),
			this.getGenre(),
			this.getComment()
		);
	}

	public static DVD fromStdin(Scanner scan) throws ParseException {
		System.out.print(">> Titulo: ");
		String title = scan.nextLine();

		System.out.print(">> Director: ");
		String director = scan.nextLine();

		System.out.print(">> Duracion (HH:MM:SS): ");
		String durationStr = scan.nextLine();
		Timedelta duration = new Timedelta(Duration.between(LocalTime.MIN, LocalTime.parse(durationStr)));
		
		System.out.print(">> Comentario: ");
		String comment = scan.nextLine();
		
		System.out.print(">> Genero: ");
		String genre = scan.nextLine();

		return new DVD(title, director, duration, comment, genre);
	}

}
