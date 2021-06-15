package com.ucabalg3.taller3;

import java.util.ArrayList;


public class MediaList<T extends Media> extends ArrayList<T> {

	private static final long serialVersionUID = 1L;

	public MediaList<T> filterByGenre(String genre) {
		MediaList<T> filtered = new MediaList<T>();
		for (T el: this) {
			if (el.getGenre().equals(genre))
				filtered.add(el);
		}
		return filtered;
	}

}
