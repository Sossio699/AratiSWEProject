package CatalogueManagement;
import Users.User;
import Users.Guest;
import Users.Subscriber;

public class ProtectionProxyCatalogue implements MusicCatalogue{
	
	public ProtectionProxyCatalogue () {
		this.catalogue = ConcreteMusicCatalogue.getInstance();
		this.subscribersCredentials = new java.util.HashMap<String, String>();
	}
	
	public Boolean search (String SongName) {
		return catalogue.search(SongName);
	}
	
	public Boolean getCategoryInformation (String category) {
		return catalogue.getCategoryInformation(category);
	}
	
	public Boolean searchByAuthor (String authorName, String songName) {
		return catalogue.searchByAuthor(authorName, songName);
	}
	
	public Boolean searchByCategory (String category, String songName) {
		return catalogue.searchByCategory(category, songName);
	}
	
	public Boolean play (String SongName) {
		return catalogue.play(SongName);
	}
	
	public Boolean playByAuthor (String authorName, String songName) {
		return catalogue.playByAuthor(authorName, songName);
	}
	
	public Boolean playByCategory (String category, String songName) {
		return catalogue.playByCategory(category, songName);
	}
	
	public Playable likeSong (User u, String songName) {
		if (u instanceof Guest && !(u instanceof Subscriber)) {
			System.out.println("Error, you are not subscribed");
			return null;
			}
		else {
			if (u.getPassword().compareTo(subscribersCredentials.get(u.getNickName())) != 0) {
				System.out.println("Wrong password");
				return null;
			}
			else {
				return catalogue.likeSong(u, songName);
			}
		}
	}
	
	public PrototypeDownloadable download(User u, String songName, String title) {
		if (u instanceof Guest && !(u instanceof Subscriber)) {
			System.out.println("Error, you are not subscribed");
			return null;
		}
		else {
			if (! u.getPassword().equals(subscribersCredentials.get(u.getNickName()))) {
				System.out.println("Wrong password");
				return null;
			}
			else {
				if(! ((Subscriber)u).getPayed()) {
					System.out.println("Error, you have not a payed subscription");
					return null;
				}
				else
					 return catalogue.download(u, songName, title);
			}
		}
	}
	
	public Subscriber subscribe(Guest u, String nickName, String password, Boolean payed) {
		int i = 0;
		while (subscribersCredentials.containsKey(nickName)) {
			nickName = nickName + "2";
			i ++;
		}
		if (i != 0)
			System.out.println("NickName already registered, we assign you " + nickName);
		subscribersCredentials.put(nickName, password);
		return new Subscriber(nickName, password, payed, this, true);
	}
	
	public String addSubscriber (String nickName, String password) {
		int i = 0;
		while (subscribersCredentials.containsKey(nickName)) {
			nickName = nickName + "2";
			i ++;
		}
		if (i != 0)
			System.out.println("NickName already registered, we assign you " + nickName);
		subscribersCredentials.put(nickName, password);
		return nickName;
	}
	
	public String changeNickName (Subscriber s, String old, String newNick) {
		if(s.getNickName().compareTo(old) == 0) {
			int i = 0;
			while (subscribersCredentials.containsKey(newNick)) {
				newNick = newNick + "2";
				i ++;
			}
			if (i != 0)
				System.out.println("NickName already registeed, we assign you " + newNick);
			subscribersCredentials.put(newNick, subscribersCredentials.get(old));
			subscribersCredentials.remove(old);
			return newNick;
		}
		return "";
	}
	
	public void changePassword (Subscriber s, String old, String password) {
		if(subscribersCredentials.get(s.getNickName()).compareTo(old) == 0) 
			subscribersCredentials.put(s.getNickName(), password);
	}
	
	private ConcreteMusicCatalogue catalogue;
	private java.util.HashMap<String, String> subscribersCredentials;
	
}