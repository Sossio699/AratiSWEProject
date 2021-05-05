package ObserverPattern;
import java.util.Vector;
import CatalogueManagement.Playable;

public abstract class Observable {
	
	abstract protected void attach (Observer o);

	abstract protected void notifyManager (Playable s);
	
	private Observer observer;
	
}