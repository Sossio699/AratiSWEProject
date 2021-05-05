package CatalogueManagement;

public class Category {
	
	public Category(String name) {
		this.name = name;
		nSongs = 0;
		categorySongs = new SongsManager<Playable>();
		}
	
	public String getName () {
		return name;
	}
	
	public int getNSongs () {
		return nSongs;
	}
	
	public void getInformation () {
		System.out.println("Category " + name + " has " + nSongs + " songs");
	}
	
	void addSong (Playable s) {
		categorySongs.addSong(s);
		nSongs ++;
	}
	
	public Boolean searchSong (String songName) {
		return categorySongs.searchSong(songName);
	}
	
	public Boolean playSong (String songName) {
		return categorySongs.playSong(songName);
	}
	
	private String name;
	private int nSongs;
	private SongsManager<Playable> categorySongs;
	
}
