package CatalogueManagement;
import java.util.Collections;
import java.util.Vector;

public class SongsManager <T>{
	
	public SongsManager() {
		songs = new Vector<T>();
	}
	
	private void shuffle () {
		Collections.shuffle(songs);
	}
	
	public void addSong (T s) {
		if (s instanceof Playable)
			songs.add(s);
	}	
	
	public Boolean searchSong (String songName) {
		for (T song : songs)
			if (((Playable) song).getTitle().compareTo(songName) == 0)
				return true;
		return false;
	}
	
	public void play (Boolean shuffle) {
		if (shuffle)
			shuffle();
		for (T song : songs) 
			((Playable) song).play();
	}
	
	public Boolean playSong (String songName) {
		Boolean found = false;
		for (T song : songs) { 
			if (((Playable) song).getTitle().compareTo(songName) == 0) {
				((Playable) song).play();
				found = true;
			}
		}
		return found;
	}
	
	Vector<T> getSongs() {
		return songs;
	}
	
	public Boolean isEmpty() {
		return songs.isEmpty();
	}
	
	private Vector<T> songs;
}
