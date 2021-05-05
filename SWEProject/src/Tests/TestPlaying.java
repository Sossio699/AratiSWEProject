package Tests;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Vector;
import org.junit.Before;
import org.junit.Test;
import CatalogueManagement.*;

public class TestPlaying {
	
	static MusicCatalogue catalogue;
	static Manager manager;
	
	@Before
	public void setUp() {
		catalogue = ConcreteMusicCatalogue.getInstance();
		((ConcreteMusicCatalogue) catalogue).reset();
		manager = Manager.getInstance();
		Author a1 = new Author("Author1");
		Author a2 = new Author("Author2");
		manager.addAuthor(a1);
		manager.addAuthor(a2);
		a1.newSong("Song1", 230, "Category1");
		a1.newSong("Song2", 300, "Category2");
		a1.newSong("Song3", 340, "Category1");
		Vector<Song> songs = new Vector<Song>();
		songs.add(new Song("Song4", 400, "Category3", a2));
		songs.add(new Song("Song5", 350, "Category3", a2));
		a2.newDisc("Disc1", songs);
		a1.newSong("Song6", 320, "Category1");
		a2.newSong("Song6", 300, "Category2");
		Vector<Song> songs2 = new Vector<Song>();
		songs2.add(new Song("Song1", 230, "Category1", a1));
		songs2.add(new Song("Song2", 300, "Category2", a1));
		manager.addPlaylist("Playlist1", songs2);
	}
	
	@Test
	public void play() {
		assertTrue(catalogue.play("Song1"));
		assertTrue(catalogue.play("Disc1"));
		assertTrue(catalogue.play("Song5"));
		assertTrue(catalogue.play("Song6"));
		assertTrue(catalogue.play("Playlist1"));
		assertFalse(catalogue.play("Song"));
	}
	
	@Test
	public void playByAuthor() {
		assertTrue(catalogue.playByAuthor("Author1", "Song2"));
		assertTrue(catalogue.playByAuthor("Author2", "Disc1"));
		assertTrue(catalogue.playByAuthor("Author2", "Song4"));
		assertFalse(catalogue.playByAuthor("Author2", "Song2"));
	}
	
	@Test
	public void playByCategory() {
		assertTrue(catalogue.playByCategory("Category1", "Song1"));
		assertTrue(catalogue.playByCategory("Category3", "Song4"));
		assertTrue(catalogue.playByCategory("Category2", "Song2"));
		assertFalse(catalogue.playByCategory("Category", "Song5"));
	}
	
}
