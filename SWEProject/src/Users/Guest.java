package Users;
import CatalogueManagement.MusicCatalogue;
import CatalogueManagement.ProtectionProxyCatalogue;

public class Guest implements User {
	
	public Guest(ProtectionProxyCatalogue catalogue) {
		this.catalogue = catalogue;
	}
	
	public Subscriber subscribe(String nickName, String password, Boolean payed) {
		return catalogue.subscribe(this, nickName, password, payed);
	}
	
	public Boolean searchCatalogue (String songName) {
		return catalogue.search(songName);
	}
	
	public Boolean searchCategoryCatalogue (String category) {
		return catalogue.getCategoryInformation(category);
	}
	
	public Boolean searchCatalogueByAuthor (String authorName, String songName) {
		return catalogue.searchByAuthor(authorName, songName);
	}
	
	public Boolean searchCatalogueByCategory (String category, String songName) {
		return catalogue.searchByCategory(category, songName);
	}
	
	public Boolean playCatalogue (String songName) {
		return catalogue.play(songName);
	}
	
	public Boolean playCatalogueByAuthor (String authorName, String songName) {
		return catalogue.playByAuthor(authorName, songName);
	}
	
	public Boolean playCatalogueByCategory (String category, String songName) {
		return catalogue.playByCategory(category, songName);
	}
	
	public void addToLiked (String songName) {
		catalogue.likeSong(this, songName);
	}
	
	public void addToDownloaded (String songName, String title) {
		catalogue.download(this, songName, title);
	}
	
	public void playLiked (Boolean shuffle) {
		System.out.println("Error, you are not subscribed");
	}
	
	public void playDownloaded (Boolean shuffle) {
		System.out.println("Error, you are not subscribed");
	}
	
	public String getNickName () {
		return nickName;
	}
	
	public String getPassword () {
		return password;
	}
	
	public void changeNickName (String newNick) {
		System.out.println("Error, you are not subscribed");
	}
	
	public void changePassword (String newPassword) {
		System.out.println("Error, you are not subscribed");
	}
	
	private String nickName = "000000";
	private String password = "000000";
	private MusicCatalogue catalogue;
}
