package miniTwitter;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class User {
    String id;
    String creatTime;
    List<User> followList;
    List<User> fanList;
    List<msg>messages;

    public User(String id, String creatTime) {
        this.id = id;
        this.creatTime = creatTime;
        followList = new ArrayList<>();
        fanList = new ArrayList<>();
        messages = new ArrayList<>();
    }
    public void sendMessage(String data){
        msg message = new msg(id,data);
        messages.add(message);
    }
    public void follow(User user){
        followList.add(user);
        user.fanList.add(this);
    }

    public String getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(String creatTime) {
        this.creatTime = creatTime;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public String toString() {
    	 return  id + " (" +
                 "CreationTime: " + creatTime+")";
    }
}

