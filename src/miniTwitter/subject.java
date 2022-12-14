package miniTwitter;

public interface subject {
	//methods to register and unregister observers
	public void register(Observer obj);
	public void unregister(Observer obj);
	
	//methods to notify observers of change
	public void notifyObservers();
	
	//method to get updates from subject
	public Object getUpdate(Observer obj); 
}
