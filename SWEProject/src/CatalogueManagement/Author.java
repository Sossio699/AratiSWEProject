package CatalogueManagement;
import java.util.Vector;
import ObserverPattern.Observable;
import ObserverPattern.Observer;

public class Author  extends Observable implements Playable {
	
	public Author(String name) {
		this.name = name;
		this.songs = new SongsManager<Playable>();
	}
	
	public void play () {
		System.out.println("Playing songs of " + name);
		songs.play(false);
	}
	
	public void playSong (String songName) {
		songs.playSong(songName);
	}
	
	public String getTitle () {
		return this.name;
	}
	
	public void newSong (String title, int length, String category) {
		songs.addSong(new Song(title, length, category, this));
		notifyManager(new Song(title, length, category, this));
	}
	
	public void newDisc (String name, Vector<Song> s) {
		songs.addSong(new Playlist(name, this, s));
		notifyManager((Playable) new Playlist(name, this, s));
		for (Song song : s)
			songs.addSong(song);
	}
	
	void addSong (Song s) {
		songs.addSong(s);
	}
	
	public Boolean searchSong (String songName) {
		return songs.searchSong(songName);
	}
	
	Playlist getSongs() {
		Vector<Playable> authorSongs = songs.getSongs();
		Vector<Song> song = new Vector<Song>();
		for (Playable p : authorSongs) {
			if (p instanceof Playlist) {
				Vector<Song> temp = ((Playlist) p).getSongs();
				for (Song s : temp)
					song.add(s);
			}
			else
				song.add((Song) p);
		}
		return new Playlist(name + "songs", null, song);
	}
	
	protected void attach (Observer o) {
		observer = o;
	}
	
	protected void notifyManager (Playable s) {
			observer.update(s);
	}
	
	private String name;
	private SongsManager<Playable> songs;
	private Observer observer;

}
