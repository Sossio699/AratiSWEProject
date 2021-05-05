package Tests;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Vector;
import org.junit.Before;
import org.junit.Test;
import CatalogueManagement.*;

public class TestSearching {
	
	static MusicCatalogue catalogue;
	static Manager manager;
	static Category c3;
	
	@Before
	public void setUp() {
		manager = Manager.getInstance();
		catalogue = ConcreteMusicCatalogue.getInstance();
		((ConcreteMusicCatalogue) catalogue).reset();
		Author a1 = new Author("Author1");
		Author a2 = new Author("Author2");
		manager.addAuthor(a1);
		manager.addAuthor(a2);
		a1.newSong("Song1", 135, "Category1");
		a2.newSong("Song2", 125, "Category2");
		a1.newSong("Song3", 100, "Category1");
		Vector<Song> songs = new Vector<Song>(); 
		songs.add(new Song("Song4", 300, "Category1", a1));
		songs.add(new Song("Song5", 300, "Category3", a1));
		a1.newDisc("Disc1", songs);
		a2.newSong("Song6", 320, "Category3");
		Vector<Song> songs2 = new Vector<Song>();
		songs2.add(new Song("Song6", 200, "Category3", a1));
		songs2.add(new Song("Song7", 340, "Category3", a2));
		manager.addPlaylist("Playlist1", songs2);
	}
	
	@Test
	public void search() {
		assertTrue(catalogue.search("Song1"));
		assertTrue(catalogue.search("Author2"));
		assertTrue(catalogue.search("Song5"));
		assertTrue(catalogue.search("Disc1"));
		assertFalse(catalogue.search("Song"));
	}
	
	@Test
	public void searchByAuthor() {
		assertTrue(catalogue.searchByAuthor("Author1", "Song3"));
		assertTrue(catalogue.searchByAuthor("Author1", "Disc1"));
		assertTrue(catalogue.searchByAuthor("Author1", "Song4"));
		assertTrue(catalogue.searchByAuthor("Author1", "Song6"));
		assertFalse(catalogue.searchByAuthor("Author2", "Song3"));
	}
	
	@Test
	public void searchCategory() {
		assertTrue(catalogue.getCategoryInformation("Category1"));
		assertTrue(catalogue.getCategoryInformation("Category2"));
		assertFalse(catalogue.getCategoryInformation("Category"));
	}
	
	@Test
	public void searchByCategory() {
		assertTrue(catalogue.searchByCategory("Category1", "Song1"));
		assertTrue(catalogue.searchByCategory("Category3", "Song5"));
		assertFalse(catalogue.searchByCategory("Category2", "Song3"));
	}
	
}
