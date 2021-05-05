package Users;
import CatalogueManagement.MusicCatalogue;
import CatalogueManagement.PrototypeDownloadable;
import CatalogueManagement.ProtectionProxyCatalogue;
import CatalogueManagement.Playable;
import CatalogueManagement.SongsManager;

public class Subscriber extends Guest {
	
	public Subscriber(String nickName, String password, Boolean payed, ProtectionProxyCatalogue catalogue, Boolean guest) {
		super(catalogue);
		this.catalogue = catalogue;
		this.password = password;
		this.payed = payed;
		this.likedSongs = new SongsManager<Playable>();
		this.downloadedSongs = new SongsManager<PrototypeDownloadable>();
		if(guest)
			this.nickName = nickName;
		else
			this.nickName = catalogue.addSubscriber(nickName, password);
	}
	
	public Boolean searchCatalogue (String songName) {
		Boolean result = catalogue.search(songName);
		if (result)
			System.out.println(songName + " is present");
		else
			System.out.println(songName + " is not present");
		return result;
	}
	
	public Boolean searchCategoryCatalogue (String category) {
		return catalogue.getCategoryInformation(category);
	}
	
	public Boolean searchCatalogueByAuthor (String authorName, String songName) {
		Boolean result = catalogue.searchByAuthor(authorName, songName);
		if (result)
			System.out.println(songName + " of " + authorName + " is present");
		else
			System.out.println(songName + " of " + authorName + " is not present");
		return result;
	}
	
	public Boolean searchCatalogueByCategory (String category, String songName) {
		Boolean result = catalogue.searchByCategory(category, songName);
		if (result)
			System.out.println(songName + " of category " + category + " is present");
		else
			System.out.println(songName + " of category " + category + " is not present");
		return result;
	}
	
	public Boolean playCatalogue (String songName) {
		return catalogue.play(songName);
	}
	
	public Boolean playCatalogueByAuthor (String authorName, String songname) {
		return catalogue.playByAuthor(authorName, songname);
	}
	
	public Boolean playCatalogueByCategory (String category, String songName) {
		return catalogue.playByCategory(category, songName);
	}
	
	public String getNickName () {
		return nickName;
	}
	
	public String getPassword () {
		return password;
	}
	
	public void changeNickName (String newNick) {
		nickName = ((ProtectionProxyCatalogue) catalogue).changeNickName(this, nickName, newNick);
	}
	
	public void changePassword (String newPassword) {
		((ProtectionProxyCatalogue) catalogue).changePassword(this, password, newPassword);
		password = newPassword;
	}
	
	public Boolean getPayed () {
		return payed;
	}
	
	public void setPayed (Boolean p) {
		this.payed = p;
	}
	
	public void addToLiked (String songName) {
		Playable s = ((ProtectionProxyCatalogue)catalogue).likeSong(this, songName);
		if (s != null) 
			likedSongs.addSong(s);
	}
	
	public void addToDownloaded (String songName, String title) {
		PrototypeDownloadable d = ((ProtectionProxyCatalogue)catalogue).download(this, songName, title);
		if (d != null)
			downloadedSongs.addSong(d);
	}
	
	public void playLiked (Boolean shuffle) {
		likedSongs.play(shuffle);
	}
	
	public void playDownloaded (Boolean shuffle) {
		downloadedSongs.play(shuffle);
	}
	
	public Boolean isEmpty (Boolean liked) {
		if (liked)
			return likedSongs.isEmpty();
		return downloadedSongs.isEmpty();
	}
	
	public Subscriber subscribe(String nickName, String password, Boolean payed) {
		System.out.println("Sei già iscritto");
		return this;
	}
	
	private String nickName;
	private String password;
	private Boolean payed;
	private SongsManager<Playable> likedSongs;
	private SongsManager<PrototypeDownloadable> downloadedSongs;
	private MusicCatalogue catalogue;

}
