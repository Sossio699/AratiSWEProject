package Tests;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Vector;
import org.junit.Before;
import org.junit.Test;
import CatalogueManagement.*;


public class TestManager {
	
	static MusicCatalogue catalogue;
	static Manager manager;
	
	@Before
	public void setUp() {
		catalogue = ConcreteMusicCatalogue.getInstance();
		((ConcreteMusicCatalogue) catalogue).reset();
		manager = Manager.getInstance();
		Author a1 = new Author("Author1");
		manager.addAuthor(a1);
		Vector<Song> songs = new Vector<Song>();
		songs.add(new Song("Song1", 300, "Category1", new Author("Author2")));
		songs.add(new Song("Song1", 320, "Category1", a1));
		songs.add(new Song("Song2", 340, "Category2", a1));
		manager.addPlaylist("Playlist1", songs);
	}
	
	@Test
	public void testAuthor() {
		assertTrue(catalogue.search("Author2"));
		assertTrue(catalogue.searchByAuthor("Author2", "Song1"));
		assertTrue(catalogue.searchByAuthor("Author1", "Song1"));
		assertTrue(catalogue.search("Song1"));
		catalogue.play("Song1");
	}

}
