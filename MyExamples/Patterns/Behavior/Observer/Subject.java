
import java.util.ArrayList;
import java.util.List;

interface ISubject {

	// methods to register and unregister observers
	public void register(IObserver obj);

	public void unregister(IObserver obj);

	// method to notify observers of change
	public void notifyObservers();

	// method to get updates from subject
	public Object getUpdate(IObserver obj);
}
public class Subject implements ISubject {

	private List<IObserver> observers;
	private String message;
	private boolean changed;
	private final Object MUTEX = new Object();

	public Subject() {
		this.observers = new ArrayList<IObserver>();
	}

	@Override
	public void register(IObserver obj) {
		if (obj == null) throw new NullPointerException("Null Observer");
		synchronized (MUTEX) {
			if (!observers.contains(obj)) observers.add(obj);
		}
	}

	@Override
	public void unregister(IObserver obj) {
		synchronized (MUTEX) {
			observers.remove(obj);
		}
	}

	@Override
	public void notifyObservers() {
		List<IObserver> observersLocal = null;
		// synchronization is used to make sure any observer registered after message is received is not notified
		synchronized (MUTEX) {
			if (!changed) return;
			observersLocal = new ArrayList<IObserver>(this.observers);
			this.changed = false;
		}
		for (IObserver obj : observersLocal) {
			obj.update();
		}

	}

	@Override
	public Object getUpdate(IObserver obj) {
		return this.message;
	}

	// method to post message to the topic
	public void postMessage(String msg) {
		System.out.println("Message Posted to Topic:" + msg);
		this.message = msg;
		this.changed = true;
		notifyObservers();
	}

}