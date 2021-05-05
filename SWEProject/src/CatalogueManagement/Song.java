package CatalogueManagement;

public class Song implements PrototypeDownloadable, Playable {
	
	public Song (String title, int length, String category, Author author) {
		this.title = title;
		this.length = length;
		this.category = category;
		this.author = author;
	}
	
	public void play () {
		System.out.println("Playing " + title + ", length: " + length + ", author: " + author.getTitle());
	}
	
	public PrototypeDownloadable download (String title) {
		return new Song(title, this.length, this.category, this.author);
	}
	
	public String getTitle () {
		return title;
	}
	
	public int getLength () {
		return length;
	}
	
	public String categoryName () {
		return category;
	}
	
	Author getAuthor() {
		return author;
	}
	
	private String title;
	private int length;
	private String category;
	private Author author;
	
}
