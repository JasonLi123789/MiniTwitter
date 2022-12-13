package miniTwitter;

import javax.security.auth.Subject;

public class myTwitterSubscriber implements Observer {
	private String name;
	private subject topic;
	
	public myTwitterSubscriber(String n) {
		this.name = n;
	}
	@Override
	public void update() {
		String msg = (String) topic.getUpdate(this);
		if(msg == null)
			System.out.println("- "+name+" No new messgae");
		else {
			System.out.println("- "+name+": "+ msg);
		}
		
	}

	@Override
	public void setSubject(subject sub) {
		this.topic = sub;
		
	}

}
