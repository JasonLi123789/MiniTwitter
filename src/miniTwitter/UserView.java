package miniTwitter;
import javax.swing.*;
import javax.swing.text.html.ListView;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserView extends JFrame {
    User user;
    UpdateUser updateUser;
    JFrame jFrame;

    TextArea textAreaUserId;
    JButton buttonFollowUser;
    JList listViewCurrentFollow;

    TextArea TweetMessage;
    JButton buttonPostTweet;

    JList listViewNewsFeed;
    JButton closeButton;
    controlPanel panelBoard;

    public UserView(User user,boolean isVisiable){
        jFrame = this;
        this.user = user;
        this.setTitle(user.id+" (CreationTime:"+user.creatTime+")");
        getContentPane().setLayout(null);
        initUserView();
        this.setSize(420,500);
        this.setVisible(isVisiable);
    }

    void initUserView(){

        textAreaUserId = new TextArea();
        textAreaUserId.setBounds(10,10,200,50);

        jFrame.getContentPane().add(textAreaUserId);
        buttonFollowUser = new JButton("Follow User");
        buttonFollowUser.setBackground(new Color(100, 149, 237));
        buttonFollowUser.setBounds(200,10,200,50);
        buttonFollowUser.addActionListener(e -> followUser());
        jFrame.getContentPane().add(buttonFollowUser);

        DefaultListModel followListModel = new DefaultListModel();
        followListModel.addElement("Current Following");
        for(User s : user.followList){
            followListModel.addElement(String.format(" - %s", s.id));
        }
        listViewCurrentFollow = new JList(followListModel);
        listViewCurrentFollow.setBounds(10,70,390,120);
        jFrame.getContentPane().add(listViewCurrentFollow);

        TweetMessage = new TextArea();
        TweetMessage.setBounds(10,200,200,50);
        jFrame.getContentPane().add(TweetMessage);

        buttonPostTweet = new JButton("Post Tweet");
        buttonPostTweet.setBackground(new Color(100, 149, 237));
        buttonPostTweet.setBounds(200,200,200,50);
        buttonPostTweet.addActionListener(e-> sendMessage());
        jFrame.getContentPane().add(buttonPostTweet);

        DefaultListModel feedListModel = new DefaultListModel();

        listViewNewsFeed = new JList(followListModel);
        updateNewsFeed(false);
        listViewNewsFeed.setBounds(10,260,390,120);
        jFrame.getContentPane().add(listViewNewsFeed);

        closeButton = new JButton("Close");
        closeButton.setBackground(new Color(100, 149, 237));
        closeButton.setBounds(0,400,400,30);
        closeButton.addActionListener(e -> jFrame.setVisible(false));
        jFrame.getContentPane().add(closeButton);
    }
    
    void updateNewsFeed(boolean  active){
        System.out.println("updateNewsFeed: "+user.id+" active: "+active );
        DefaultListModel feedListModel = new DefaultListModel();
        feedListModel.addElement("News Feed");
        List<msg>newsfeed = new ArrayList<>();
        for(User s : user.followList){
            newsfeed.addAll(s.messages);
        }
        newsfeed.addAll(user.messages);
        newsfeed.sort((a,b)-> (int) (b.time-a.time));
        for(msg m : newsfeed){
            feedListModel.addElement(String.format(" - %s:%s", m.getSenderId(), m.getData()));
        }
        listViewNewsFeed.setModel(feedListModel);
        listViewNewsFeed.updateUI();
        if(active){
        	panelBoard.updateAllFollowers(user);
        }
    }
    
    void sendMessage(){

        String data = TweetMessage.getText();
        String dateTime = dataTime();

        if(data.length() > 0){
            user.sendMessage(data + " " + dateTime);
            TweetMessage.setText("");
        }
        updateNewsFeed(true);

    }
    
    
    void setMmanagementBoard(controlPanel managementBoard) {
        this.panelBoard = managementBoard;
    }
    
    void followUser(){
        String id = textAreaUserId.getText();
        int index = panelBoard.getIndexByUserId(id);
        if(index == -1 || id.equals(user.id)){
            JOptionPane.showMessageDialog(jFrame,"User not found");
        }else{
            User searchUser = panelBoard.userList.get(index);
            this.user.follow(searchUser);
            textAreaUserId.setText("");
            DefaultListModel followListModel = new DefaultListModel();
            followListModel.addElement("Current Following");
            for(User s : user.followList){
                followListModel.addElement(String.format(" - %s", s.id));
            }
            listViewCurrentFollow.setModel(followListModel);
            listViewCurrentFollow.updateUI();
            updateNewsFeed(true);
        }
    }

    public String dataTime(){
        try {
            SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();
            String dateStr = form.format(date);
            System.out.println(dateStr);
            return  dateStr;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }



}
