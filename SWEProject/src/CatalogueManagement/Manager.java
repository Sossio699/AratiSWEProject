package CatalogueManagement;
import java.util.Vector;
import ObserverPattern.Observer;

public class Manager implements Observer {
	
	private Manager () {
		catalogue = ConcreteMusicCatalogue.getInstance();
	}
	
	public static Manager getInstance () {
		if (instance == null)
			instance = new Manager ();
		return instance;
	}
	
	public void update (Playable s) {
		if (s instanceof Song)
			catalogue.addSong((Song) s);
		else {
			if (s instanceof Playlist)
				catalogue.addPlaylist((Playlist) s);
		}
	}
	
	public void addAuthor (Author a) {
		a.attach(this);
		catalogue.addAuthor(a);
	}
	
	public void addPlaylist (String title, Vector<Song> songs) {
		catalogue.addPlaylist(new Playlist(title, null, songs));
	}
	
	private static Manager instance = null;
	private ConcreteMusicCatalogue catalogue;
	
}
