package Tests;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Vector;
import org.junit.Before;
import org.junit.Test;
import CatalogueManagement.*;
import Users.*;


public class TestLikeDownload {
	
	static MusicCatalogue catalogue;
	static ProtectionProxyCatalogue protection;
	static Manager manager;
	static User u1;
	static User u2;
	static User u3;
	
	@Before
	public void setUp() {
		catalogue = ConcreteMusicCatalogue.getInstance();
		((ConcreteMusicCatalogue) catalogue).reset();
		manager = Manager.getInstance();
		protection = new ProtectionProxyCatalogue();
		u1 = new Guest(protection);
		u2 = new Guest(protection);
		u3 = new Subscriber("n3", "p3", true, protection, false);
		Author a1 = new Author("Author1");
		Author a2 = new Author("Author2");
		manager.addAuthor(a1);
		manager.addAuthor(a2);
		a1.newSong("Song1", 230, "Category1");
		a1.newSong("Song2", 300, "Category1");
		Vector<Song> songs = new Vector<Song>();
		songs.add(new Song("Song3", 320, "Category1", a2));
		songs.add(new Song("Song4", 300, "Category2", a2));
		songs.add(new Song("Song5", 370, "Category2", a2));
		a2.newDisc("Disc1", songs);
	}	
	
	@Test
	public void like() {
		assertTrue(u3 instanceof Subscriber);
		assertTrue(((Subscriber) u3).isEmpty(true));
		((Subscriber) u3).addToLiked("Song1");
		((Subscriber) u3).addToLiked("Song3");
		assertFalse(((Subscriber) u3).isEmpty(true));
		((Subscriber) u3).playLiked(false);
		u1 = u1.subscribe("n1", "p1", false);
		assertTrue(u1 instanceof Subscriber);
		assertTrue(((Subscriber) u1).isEmpty(true));
		((Subscriber) u1).addToLiked("Song2");
		((Subscriber) u1).addToLiked("Disc1");
		assertFalse(((Subscriber) u1).isEmpty(true));
		((Subscriber) u1).playLiked(true);
	}
	
	@Test
	public void download() {
		assertTrue(u3 instanceof Subscriber);
		assertTrue(((Subscriber) u3).isEmpty(false));
		((Subscriber) u3).addToDownloaded("Song1", "song1");
		((Subscriber) u3).addToDownloaded("Song4", "song4");
		assertFalse(((Subscriber) u3).isEmpty(false));
		((Subscriber) u3).playDownloaded(false);
		u2 = u2.subscribe("n2", "p2", true);
		assertTrue(u2 instanceof Subscriber);
		assertTrue(((Subscriber) u2).isEmpty(false));
		((Subscriber) u2).addToDownloaded("Song2", "song2");
		((Subscriber) u2).addToDownloaded("Disc1", "disc");
		assertFalse(((Subscriber) u2).isEmpty(false));
		((Subscriber) u2).playDownloaded(false);
	}
	
}
