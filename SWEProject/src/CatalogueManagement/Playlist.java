package CatalogueManagement;
import java.util.Vector;

public class Playlist implements PrototypeDownloadable, Playable {
	
	public Playlist (String tt, Author a, Vector<Song> s) {
		this.title = tt;
		this.nSongs = s.size();
		this.songs = new SongsManager<Song>();
		this.length = 0;
		for (Song song : s) {
			this.length += song.getLength();
			songs.addSong(song);
		}
		this.author = a;
	}
	
	public void play () {
		if(author == null)
			System.out.println("Playing playlist " + title + ", " + nSongs + " songs, total length: " + length);
		else
			System.out.println("Playing disc" + title + " of " + author.getTitle() + ", " + nSongs + " songs, total length: " + length);
		songs.play(false);
	}
	
	public PrototypeDownloadable download (String title) {
		return new Playlist(title, this.author, this.songs.getSongs());
	}
	
	public String getTitle () {
		return title;
	}
	
	Author getAuthor() {
		return author;
	}
	
	Vector<Song> getSongs() {
		return songs.getSongs();
	}

	private String title;
	private int nSongs;
	private int length;
	private SongsManager<Song> songs;
	private Author author;
	
}
