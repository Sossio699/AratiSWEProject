package Tests;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Vector;
import org.junit.Before;
import org.junit.Test;
import CatalogueManagement.*;
import Users.*;

public class TestUsers {
	
	static MusicCatalogue catalogue;
	static ProtectionProxyCatalogue protection;
	static Manager manager;
	static User u1;
	static User u2;
	static User u3;
	static User u4;
	
	@Before
	public void setUp() {
		catalogue = ConcreteMusicCatalogue.getInstance();
		((ConcreteMusicCatalogue) catalogue).reset();
		manager = Manager.getInstance();
		protection = new ProtectionProxyCatalogue();
		u1 = new Guest(protection);
		u2 = new Subscriber("n1", "p1", false, protection, false);
		u3 = new Subscriber("n2", "p2", true, protection, false);
		u4 = new Subscriber("n3", "p3", false, protection, false);
		Author a1 = new Author("Author1");
		Author a2 = new Author("Author2");
		manager.addAuthor(a1);
		manager.addAuthor(a2);
		a1.newSong("Song1", 200, "Category1");
		a1.newSong("Song2", 250, "Category1");
		Vector<Song> songs = new Vector<Song>();
		songs.add(new Song("Song1", 300, "Category2", a2));
		songs.add(new Song("Song3", 320, "Category2", a2));
		songs.add(new Song("Song4", 400, "Category1", a2));
		a2.newDisc("Disc1", songs);
		Vector<Song> songs2 = new Vector<Song>();
		songs2.add(new Song("Song4", 200, "Category3", a1));
		songs2.add(new Song("Song5", 340, "Category3", a2));
		manager.addPlaylist("Playlist1", songs2);
	}
	
	@Test
	public void search() {
		assertTrue(u1.searchCatalogue("Song4"));
		assertTrue(u2.searchCatalogue("Disc1"));
		assertTrue(u1.searchCatalogueByAuthor("Author1", "Song1"));
		assertTrue(u2.searchCatalogueByAuthor("Author2", "Song1"));
		assertTrue(u1.searchCatalogueByCategory("Category2", "Song3"));
		assertFalse(u2.searchCatalogueByCategory("Category1", "Song5"));
		assertTrue(u2.searchCategoryCatalogue("Category3"));
		assertFalse(u1.searchCategoryCatalogue("Category"));
	}
	
	@Test
	public void play() {
		assertTrue(u1.playCatalogue("Song5"));
		assertTrue(u2.playCatalogue("Song3"));
		assertTrue(u1.playCatalogueByAuthor("Author1", "Song1"));
		assertTrue(u2.playCatalogueByAuthor("Author2", "Song5"));
		assertTrue(u1.playCatalogueByCategory("Category3", "Song4"));
		assertFalse(u2.playCatalogueByCategory("Category2", "Song2"));
	}
	
	@Test
	public void changeCredentials() {
		u3.changeNickName("n3");
		assertTrue(u3.getNickName().compareTo("n3") != 0);
		u3.changePassword("p3");
		assertTrue(u3.getPassword().compareTo("p2") != 0);
		u4.changeNickName("n32");
		assertTrue(u4.getNickName().compareTo("n32") != 0);
		u4.changePassword("p4");
		assertTrue(u4.getPassword().compareTo("p4") == 0);
	}
}
