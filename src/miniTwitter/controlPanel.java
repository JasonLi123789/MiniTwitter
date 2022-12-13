package miniTwitter;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.UIManager;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.TextArea;
import java.util.*;
import javax.swing.*;

import javax.swing.border.LineBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.JToolBar;

public class controlPanel extends JFrame {
	
		//create frame 
	    JFrame jFrame;
	    
	    //Input text area 
	    TextArea textUser;
	    TextArea textGroup;
	    
	    //Button
	    JButton buttonAddUser; 
	    JButton buttonAddGroup;
	    JButton buttonOpenUserView;
	    JButton buttonLastUpdate;
	    JButton buttonVerification;
	    JButton btnUserTotal;
	    JButton btnGroupTotal;
	    JButton btnMessageTotal;
	    JButton btnPositive;
	    
	    //Create a Tree view
	    JTree tree;

	    Set<String> groupIdSet;
	    List<User>userList;
	    List<UserView>userViewList;
	    List<msg>messageList;
	    List<String>positiveMessageList;
		List<UpdateUser>updateList;
	    
	    public controlPanel(){
	        jFrame=this;
	        setTitle("Mini Twitter");
	        setBounds(0,0,1200, 750);
	        getContentPane().setLayout(null);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setResizable(false);
	        initPanel();
	        setVisible(true);
	        
	    }
	    
	    void initPanel(){

	        groupIdSet = new HashSet<>();
	        userList = new ArrayList<>();
	        messageList = new ArrayList<>();
	        userViewList = new ArrayList<>();
	        updateList = new ArrayList<>();

	        createTreeView();
	       
	        //Create text field for adding user
	        textUser = new TextArea();
	        textUser.setBounds(500,35,252,75);
	        jFrame.getContentPane().add(textUser);

	        //Create text field for adding group
	        textGroup = new TextArea();
	        textGroup.setBounds(500,110,252,84);
	        jFrame.getContentPane().add(textGroup);
	        
	        //Create button for add user
	        buttonAddUser = new JButton("Button - Add User");
	        buttonAddUser.setBackground(new Color(100, 149, 237));
	        buttonAddUser.setBounds(800,35,262,75);
	        buttonAddUser.addActionListener(e -> addUser());
	        jFrame.getContentPane().add(buttonAddUser);
	       
	        //Create button for add group
	        buttonAddGroup = new JButton("Button - Add Group");
	        buttonAddGroup.setBackground(new Color(100, 149, 237));
	        buttonAddGroup.setBounds(800,110,262,92);
	        buttonAddGroup.addActionListener(e -> addGroup());
	        jFrame.getContentPane().add(buttonAddGroup);

	        //Create button for open user view
	        buttonOpenUserView = new JButton("Button - Open User View");
	        buttonOpenUserView.setBackground(new Color(100, 149, 237));
	        buttonOpenUserView.setBounds(500,240,561,75);
	        buttonOpenUserView.addActionListener(e -> openUserView());
	        jFrame.getContentPane().add(buttonOpenUserView);
	        
	        //Create button for show last update User
	        buttonLastUpdate = new JButton("Button - Show Last Update User");
	        buttonLastUpdate.setBackground(new Color(100, 149, 237));
	        buttonLastUpdate.setBounds(500,384,262,68);
	        buttonLastUpdate.addActionListener(e -> lastUpdateUser());
	        jFrame.getContentPane().add(buttonLastUpdate);
	        
	        //Create button for verification user and group ID
	        buttonVerification = new JButton("Button - User/Group ID Verification");
	        buttonVerification.setBackground(new Color(100, 149, 237));
	        buttonVerification.setBounds(811,384,262,68);
	        buttonVerification.addActionListener(e -> verification());
	        jFrame.getContentPane().add(buttonVerification);

	        //Create button for show total number of user
	        btnUserTotal = new JButton("Button - Show User Total");
	        btnUserTotal.setBackground(new Color(100, 149, 237));
	        btnUserTotal.setBounds(500,473,262,68);
	        btnUserTotal.addActionListener(e -> showUserTotal());
	        jFrame.getContentPane().add(btnUserTotal);

	        //Create button for show total number of group
	        btnGroupTotal = new JButton("Button - Show Group Total");
	        btnGroupTotal.setBackground(new Color(100, 149, 237));
	        btnGroupTotal.setBounds(811,473,262,68);
	        btnGroupTotal.addActionListener(e -> showGroupTotal());
	        jFrame.getContentPane().add(btnGroupTotal);
	        
	        //Create button for show total number of message
	        btnMessageTotal = new JButton("Button - Show Message Total");
	        btnMessageTotal.setBackground(new Color(100, 149, 237));
	        btnMessageTotal.setBounds(500,563,262,68);
	        btnMessageTotal.addActionListener(e -> showMessagesTotal());
	        jFrame.getContentPane().add(btnMessageTotal);

	        //Create button for show positive Percentage
	        btnPositive = new JButton("Button - Show Positive Percentage");
	        btnPositive.setBackground(new Color(100, 149, 237));
	        btnPositive.setBounds(811,563,262,68);
	        btnPositive.addActionListener(e -> showPositivePercentage());
	        jFrame.getContentPane().add(btnPositive);
	        
	        //Positive words
	        positiveMessageList=List.of("good","great","bright","joyful","confident","happy","expectant");
	    }
	    
	    //Show the total user
	    void showUserTotal(){
	        List<String>list=new ArrayList<>();
	        for(User user:userList){
	            String id=user.id;
	            list.add(id);
	        }
	        totalView showTotalView = new totalView("Show User Total",list);

	    }
	    void showGroupTotal(){
	        List<String>list=new ArrayList<>();
	        for(String s:groupIdSet){
	            list.add(s);
	        }
	        totalView showTotalView = new totalView("Show Group Total",list);
	    }
	    void showMessagesTotal(){
	        List<String>list=new ArrayList<>();
	        for(msg m:messageList){
	            list.add(m.toString());
	        }
	        totalView showTotalView = new totalView("Show Messages Total",list);
	    }
	    void showPositivePercentage() {
	        List<String> list = new ArrayList<>();
	        for(msg message:messageList){
	            String data=message.getData();

	            for(String s:positiveMessageList){
	                if(data.contains(s)){
	                    list.add(message.toString());
	                    break;
	                }
	            }

	        }
	        int totalsize=messageList.size();
	        if(totalsize==0){
	            totalsize=1;
	        }
	        totalView showTotalView = new  totalView(String.format("Positive Percentage %.2f%%",list.size()*100.0/totalsize), list);
	    }
	    
	    public UserView createUserView(User user){
	        UserView userView = new UserView(user, false);
	        userView.setMmanagementBoard(this);
	        userViewList.add(userView);

	        return userView;
	    }
	    public void createTreeView(){
	        User john = new User("John",dataTime());
			UpdateUser updateUser = new UpdateUser("John", dataTime());
	        DefaultMutableTreeNode top = new DefaultMutableTreeNode("Root");
	        top.add(new DefaultMutableTreeNode(john));
	        userList.add(john);
			updateList.add(updateUser);
	        createUserView(john);
	        groupIdSet.add("Root");
	        tree = new JTree(top);
	        tree.setBorder(new LineBorder(new Color(100, 149, 237)));
	        tree.setBounds(84,35,372,596);
	        jFrame.getContentPane().add(tree);
	      
	        tree.addTreeSelectionListener(new TreeSelectionListener() {

	            @Override
	            public void valueChanged(TreeSelectionEvent e) {
	                DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree
	                        .getLastSelectedPathComponent();

	                if (node == null)
	                    return;

	                Object object = node.getUserObject();
	                if (node.isLeaf()) {
	                    User user = (User) object;
	                    System.out.println("choose user: " + user.toString());
	                }
	                else {
	                    System.out.println("choose group: " + object.toString());
	                }

	            }
	        });
	    }
	    
	    //get the user ID
	    int getIndexByUserId(String id){
	        for(int i = 0; i < userList.size(); i++){
	            if(userList.get(i).id.equals(id)){
	                return i;
	            }
	        }
	        return -1;
	    }
	    
	    //Adding User to the Tree root
	    void addUser(){
	        String userId = textUser.getText();
		
	        if(userId.equals("")){
	            return;
	        }
	        if(getIndexByUserId(userId) != -1){
	            JOptionPane.showMessageDialog(null, "User id already exists" );
	            return;
	        }

	        User user = new User(userId,dataTime());
			UpdateUser updateUser = new UpdateUser(userId, dataTime());

	        DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
	        if (node == null)
	            return;
	        if (node.isLeaf()) {
	            node = (DefaultMutableTreeNode) node.getParent();

	        }
	        node.add(new DefaultMutableTreeNode(user));
	        createUserView(user);
	        for(int i=0;i<node.getChildCount();i++){
	            if(node.getChildAt(i).toString().equals("Empty")){
	                node.remove(i);
	            }
	        }
	        userList.add(user);
			updateList.add(updateUser);
	        textUser.setText("");
	        tree.updateUI();


	    }
	    
	    //Adding group to the Tree root
	    void addGroup(){
	        String groupId = textGroup.getText();
			//groupId = groupId+"(CreationTime: "+dataTime()+")";
	        if(groupId.equals("")){
	            return;
	        }
	        if(groupIdSet.contains(groupId)){
	        	JOptionPane.showMessageDialog(null, "Group id already exists" );
	            return;
	        }

	        DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
	        if (node == null)
	            return;
	        if (node.isLeaf()) {
	            node = (DefaultMutableTreeNode) node.getParent();
	        }

	        DefaultMutableTreeNode group = new DefaultMutableTreeNode(groupId);
	        node.add(group);
	        group.add(new DefaultMutableTreeNode("Empty"));
	        groupIdSet.add(groupId);
	        textGroup.setText("");
	        tree.updateUI();

	    }
	    
	    //Update followers 
	    void updateAllFollowers(User user){
	        for(int i = 0; i < user.fanList.size(); i++){
	            User fan = user.fanList.get(i);
	            int index = getIndexByUserId(fan.id);
	            if(index != -1){

	                UserView userView = userViewList.get(index);
	                System.out.println("updateAllFollowers: "+fan.id);
	                userView.updateNewsFeed(false);
	            }
	        }
	        messageList.clear();
	        for(User u:userList){
	            for(msg m:u.messages){
	                messageList.add(m);
	            }
	        }
	        messageList.sort((a,b)-> (int) (b.time-a.time));
	    }
	    
	    //Open User view and display another window
	    void openUserView(){
	        DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
	        if (node == null)
	            return;
	        if (node.isLeaf()) {
	            User user = (User) node.getUserObject();
	            int index = getIndexByUserId(user.id);
	            if(index != -1){
	                UserView userView = userViewList.get(index);
	                userView.setVisible(true);
	            }
	        }
	    }
	    
	    void lastUpdateUser() {
	    	List<String>list=new ArrayList<>();


	        for(UpdateUser updateuser: updateList){
	            String id= updateuser.id;
				String update = updateuser.getUpdateTime();
				list.add(id+"(update time: "+update+")");
	        }
	        UpdateView showUpdateView = new UpdateView("Show Last Update User", list);
	    }
	    	
		void verification(){
			String userId = textUser.getText();
			String groupId = textGroup.getText();

			if(getIndexByUserId(userId) != -1){
				JOptionPane.showMessageDialog(null, "ID already exists" );
				return;
			}
			else if(userId.contains(" ")) {
				JOptionPane.showMessageDialog(null, "ID cannot have space");
				return;
			}
			else if(groupId.contains(" ")) {
				JOptionPane.showMessageDialog(null, "ID cannot have space");
				return;
			}
			else if(groupIdSet.contains(groupId)){
				JOptionPane.showMessageDialog(null, "ID already exists" );
				return;
			}
			else
				JOptionPane.showMessageDialog(null, "Valid User ID" );
				return;
		}
	    
	    public static <T> List<T> listOf(T... elements) {
	        List<T> list = new ArrayList<>();
	        for (T e : elements)
	            list.add(e);
	        return Collections.unmodifiableList(list);
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
	    //Show the last update User
	       
	   
	}

