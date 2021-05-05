package CatalogueManagement;
import Users.Guest;
import Users.Subscriber;
import Users.User;

public interface MusicCatalogue {
	
	public Subscriber subscribe(Guest u, String nickName, String password, Boolean payed);
	
	public Boolean search (String songName);
	
	public Boolean getCategoryInformation (String category);
	
	public Boolean searchByAuthor(String authorName, String songName);
	
	public Boolean searchByCategory (String category, String songName);
	
	public Boolean play (String songName);
	
	public Boolean playByAuthor (String authorName, String songName);
	
	public Boolean playByCategory (String category, String songName);
	
	public Playable likeSong(User u, String songName);
	
	public PrototypeDownloadable download(User u, String songName, String Title);
	
}
