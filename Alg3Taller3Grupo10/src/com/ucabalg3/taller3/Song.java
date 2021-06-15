package com.ucabalg3.taller3;

import java.text.ParseException;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Scanner;


public class Song {

	private String title;
	private Timedelta duration;

	public Song(String title, Timedelta duration) {
		super();
		this.title = title;
		this.duration = duration;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Timedelta getDuration() {
		return duration;
	}

	public void setDuration(Timedelta duration) {
		this.duration = duration;
	}

	public static Song fromStdin(Scanner scan) throws ParseException {
		System.out.print(">> Titulo: ");
		String title = scan.nextLine();

		System.out.print(">> Duracion (HH:MM:SS): ");
		String durationStr = scan.nextLine();
		Timedelta duration = new Timedelta(Duration.between(LocalTime.MIN, LocalTime.parse(durationStr)));

		return new Song(title, duration);
	}

	public String tableRow() {
		return String.format(
			"%-60s ------- %10s",
			this.getTitle(),
			this.getDuration().toString()
		);
	}

}
