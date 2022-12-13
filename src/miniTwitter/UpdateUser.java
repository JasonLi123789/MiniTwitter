package miniTwitter;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class UpdateUser {
    String id;
    String updateTime;
    List<User> followList;
    List<User> fanList;
    List<msg>messages;


    public UpdateUser(String id, String updateTime){
        this.id = id;
        this.updateTime = updateTime;
        followList = new ArrayList<>();
        fanList = new ArrayList<>();
        messages = new ArrayList<>();
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String toString() {
        return  "id='" + id + '\'' +
                ", UpdateTime='" + updateTime + '\'' +
                '}';
   }

}


