package CatalogueManagement;
import java.util.Vector;
import Users.Guest;
import Users.Subscriber;
import Users.User;

public class ConcreteMusicCatalogue implements MusicCatalogue{
	
	private ConcreteMusicCatalogue () {
		this.musics = new java.util.HashMap<String, Playable>();
		this.categories = new Vector<Category>();
	}
	
	public static ConcreteMusicCatalogue getInstance () {
		if (instance == null) 
			instance = new ConcreteMusicCatalogue ();
		return instance;
	}
	
	public Subscriber subscribe(Guest u, String nickName, String password, Boolean payed) {
		return null;
	}
	
	public Boolean search (String songName) {
		return musics.containsKey(songName);
	}
	
	public Boolean getCategoryInformation (String category) {
		Boolean found = false;
		for (Category c : categories) {
			if(c.getName().compareTo(category) == 0) {
				c.getInformation();
				found = true;
			}
		}
		if (!found)
			System.out.println("No information of " + category + " category");
		return found;
	}
	
	public Boolean searchByAuthor (String authorName, String songName) {
		if(musics.containsKey(authorName)) {
			Author a = (Author) musics.get(authorName);
			return a.searchSong(songName);
		}
		return false;
	}
	
	public Boolean searchByCategory (String category, String songName) {
		for (Category c : categories) {
			if(c.getName().compareTo(category) == 0)
				return c.searchSong(songName);
		}
		return false;
	}
	
	public Boolean play (String songName) {
		if (musics.containsKey(songName)) {
			musics.get(songName).play(); 
			return true;
		}
		return false;
	}
	
	public Boolean playByAuthor (String authorName, String songName) {
		if(musics.containsKey(authorName)) {
			Author a = (Author) musics.get(authorName);
			if(a.searchSong(songName)) {
				a.playSong(songName);
				return true;
			}
			return false;
		}
		return false;
	}
	
	public Boolean playByCategory (String category, String songName) {
		Boolean found = false;
		for (Category c : categories) {
			if(c.getName().compareTo(category) == 0) {
				found = c.playSong(songName);
			}
		}
		if (!found)
			System.out.println("No songs called " + songName + " found in category " + category);
		return found;
	}
	
	public Playable likeSong(User u, String songName) {
		if(musics.get(songName) instanceof Author) 
			return ((Author) musics.get(songName)).getSongs();
		return musics.get(songName);
	}
	
	public PrototypeDownloadable download (User u, String songName, String title) {
		return (((PrototypeDownloadable)musics.get(songName)).download(title));
	}
	
	void addSong (Song s) {
		Boolean added = false;
		if (musics.containsKey(s.getTitle())) {
			if(musics.get(s.getTitle()) instanceof Song) {
				if(((Song) musics.get(s.getTitle())).getAuthor().getTitle().compareTo(s.getAuthor().getTitle()) != 0) {
					Vector<Song> songs = new Vector<Song>();
					songs.add(s);
					songs.add((Song) musics.get(s.getTitle()));
					Playlist p = new Playlist(s.getTitle(), null, songs);
					musics.put(p.getTitle(), p);
					added = true;
				}
			}
			else {
				Vector<Song> songs = new Vector<Song>();
				songs.add(s);
				Vector<Song> oldsongs = ((Playlist) musics.get(s.getTitle())).getSongs();
				for (Song song : oldsongs)
					songs.add(song);
				Playlist p = new Playlist(s.getTitle(), null, songs);
				musics.put(p.getTitle(), p);
				added = true; 
				}
			}
		else {
			musics.put(s.getTitle(), s);
			added = true;
		}
		if (added) {
			Boolean found = false;
			for (Category category : categories) {
				if (category.getName().equals(s.categoryName())) {
					category.addSong(s);
					found = true;
				}
			}
			if (!found) {
				Category c = new Category(s.categoryName());
				c.addSong(s);
				addCategory(c);		
			}
			if (! s.getAuthor().searchSong(s.getTitle()))
				s.getAuthor().addSong(s);
		}
	}
	
	void addPlaylist (Playlist p) {
		musics.put(p.getTitle(), p);
		Vector<Song> temp = p.getSongs();
		for (Song song : temp) {
			addSong(song);
			if(! search(song.getAuthor().getTitle()))
				addAuthor(song.getAuthor());
			if(! song.getAuthor().searchSong(song.getTitle()))
				song.getAuthor().addSong(song);
		}
	}
	
	void addAuthor (Author a) {
		musics.put(a.getTitle(), a);
	}
	
	void addCategory (Category c) {
		if(! categories.contains(c))
			categories.add(c);
	} 

	public void reset() {
		musics = new java.util.HashMap<String, Playable>();
		categories = new Vector<Category>();
	}
	
	private static ConcreteMusicCatalogue instance = null;
	private java.util.HashMap<String, Playable> musics;
	private Vector<Category> categories;
	
}