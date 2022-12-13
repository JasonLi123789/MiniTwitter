package miniTwitter;

import java.awt.Font;
import java.awt.TextArea;
import java.util.List;

import javax.swing.JFrame;

public class UpdateView extends JFrame {
    JFrame jFrame;
    TextArea textArea;
    List<String> list;
    void init(){
        textArea = new TextArea();
        textArea.setBounds(0,0,400,400);
        jFrame.add(textArea);

        textArea.setFont(new Font("Calibri", Font.PLAIN, 20));
        textArea.append("All Update User: "+ list.size()+"\n");
        for (String s : list) {
            textArea.append(s + "\n");
        }

    }
    
    public UpdateView(String title, List<String> list){
        jFrame = this;
        this.list = list;
        this.setTitle(title);
        setLayout(null);
        this.setBounds(300,200,300,300);
        init();
        this.setVisible(true);
    }

}
