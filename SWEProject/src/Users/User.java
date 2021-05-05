package Users;
public interface User {
	
	public Subscriber subscribe(String nickName, String password, Boolean payed);
	
	public Boolean searchCatalogue (String songName);
	
	public Boolean searchCategoryCatalogue (String category);
	
	public Boolean searchCatalogueByAuthor (String authorName, String songName);
	
	public Boolean searchCatalogueByCategory (String category, String songName);
	
	public Boolean playCatalogue (String songName);
	
	public Boolean playCatalogueByAuthor (String authorName, String songName);
	
	public Boolean playCatalogueByCategory (String category, String songName);
	
	public void addToLiked (String songName);
	
	public void addToDownloaded(String songName, String title);
	
	public void playLiked(Boolean shuffle);
	
	public void playDownloaded(Boolean shuffle);
	
	public String getNickName();
	
	public String getPassword();
	
	public void changeNickName (String newNick);
	
	public void changePassword (String newPassword);
	
}