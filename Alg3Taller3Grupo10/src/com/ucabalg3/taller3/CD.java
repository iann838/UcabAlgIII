package com.ucabalg3.taller3;

import java.text.ParseException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Scanner;


public class CD implements Media {

//	 título, artista, cantidad de canciones, tiempo de reproducción total, tiempo
//	 de reproducción por cada canción, titulo de cada canción, comentario, genero.

	private String title;
	private String artist;
	private long songNum;
	private ArrayList<Song> songs;
	private String comment;
	private String genre;
	
	public CD(String title, String artist, long songNum, ArrayList<Song> songs, String comment,
			String genre) {
		super();
		this.title = title;
		this.artist = artist;
		this.songNum = songNum;
		this.songs = songs;
		this.comment = comment;
		this.genre = genre;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getArtist() {
		return artist;
	}
	
	public void setArtist(String artist) {
		this.artist = artist;
	}
	
	public long getSongNum() {
		return songNum;
	}
	
	public void setSongNum(long songNum) {
		this.songNum = songNum;
	}

	public Timedelta getDuration() {
		Duration duration = Duration.ZERO;
		for (Song song: this.getSongs()) {
			duration = duration.plus(song.getDuration().self());
		}
		return new Timedelta(duration);
	}

	public ArrayList<Song> getSongs() {
		return songs;
	}
	
	public void setSongs(ArrayList<Song> songs) {
		this.songs = songs;
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
			"Titulo", "Artista", "Duracion", "Genero", "Comentario",
			"-".repeat(30), "-".repeat(30), "-".repeat(12), "-".repeat(16), "-".repeat(30)
		);
	}

	@Override
	public String tableRow() {
		String row = String.format(
			"%-30s|%-30s|%-12s|%-16s|%-30s",
			this.getTitle(),
			this.getArtist(),
			this.getDuration().toString(),
			this.getGenre(),
			this.getComment()
		);
		row = row + "\n" + "~".repeat(122);
		int i = 0;
		for (Song song: this.getSongs()) {
			row = row + "\n" + " ".repeat(5) + String.format("%5d) ", ++i) + song.tableRow();
		}
		row = row + "\n" + "~".repeat(122);
		return row;
	}

	public static CD fromStdin(Scanner scan) throws ParseException {
		System.out.print(">> Titulo: ");
		String title = scan.nextLine();

		System.out.print(">> Artista: ");
		String artist = scan.nextLine();

		System.out.print(">> Numero de canciones: ");
		long songNum = scan.nextLong();
		scan.nextLine();
		
		System.out.println();
		ArrayList<Song> songs = new ArrayList<Song>(); 
		for (int i = 1; i <= songNum; i++) {
			System.out.println(String.format("Cancion #%d", i));
			songs.add(Song.fromStdin(scan));
		}
		System.out.println();
		
		System.out.print(">> Comentario: ");
		String comment = scan.nextLine();
		
		System.out.print(">> Genero: ");
		String genre = scan.nextLine();

		return new CD(title, artist, songNum, songs, comment, genre);
	}

}
