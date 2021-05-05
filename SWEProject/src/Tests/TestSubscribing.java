package Tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.Before;
import CatalogueManagement.*;
import Users.*;
import org.junit.Test;

public class TestSubscribing {
	
	static MusicCatalogue catalogue;
	static Manager manager;
	static ProtectionProxyCatalogue protection;
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
		u2 = new Guest(protection);
		u3 = new Guest(protection);
		Author a1 = new Author("Author1");
		Author a2 = new Author("Author2");
		manager.addAuthor(a1);
		manager.addAuthor(a2);
		a1.newSong("Song1", 300, "Category1");
		a2.newSong("Song2", 250, "Category1");
		a1.newSong("Song3", 200, "Category2");
		a2.newSong("Song3", 280, "Category2");
	}
	
	@Test
	public void subscribe() {
		assertFalse(u1 instanceof Subscriber);
		u1 = ((Guest)u1).subscribe("n1", "p1", false);
		assertTrue(u1 instanceof Subscriber);
		((Subscriber) u1).addToLiked("Song1");
		assertFalse(((Subscriber) u1).isEmpty(true));
		((Subscriber) u1).playLiked(false);
	}
	
	@Test
	public void doubleSubscribe() {
		u2 = ((Guest) u1).subscribe("n2", "p2", false);
		assertTrue(u2 instanceof Subscriber);
		u3 = ((Guest) u3).subscribe("n2", "p3", false);
		assertTrue(u3 instanceof Subscriber);
		assertTrue((u2.getNickName() + "2").equals(u3.getNickName()));
	}
	
}
