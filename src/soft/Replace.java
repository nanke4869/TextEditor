package soft;

import java.awt.event.ActionEvent;   
import java.awt.event.ActionListener;   
import java.util.regex.Matcher;   
import java.util.regex.Pattern;   
   
import javax.swing.*;   
   
public class Replace extends JDialog implements ActionListener{   
       
 
    private static final long serialVersionUID = 8363613934667881447L;   
    JTextField t1,t2;   
    JButton bt_ok2,bt_3;   
    JPanel p1,p2;   
    TextPane textPane;   
    int flag=0;   
       
    Replace(TextPane textPane){   
        this.textPane=textPane;   
           
        this.setSize(200,180);   
        this.setLocation(400,200);   
        this.setTitle("�滻");   
        this.setVisible(true);   
        //���ò��ɵ������ڴ�С   
        this.setResizable(false);   
           
        p1=new JPanel();   
        p2=new JPanel();   
           
        JLabel lab1=new JLabel("�滻���ݣ�",JLabel.CENTER);   
        JLabel lab2=new JLabel("�滻��    ��",JLabel.CENTER);   
        t1=new JTextField(10);   
        t2=new JTextField(10);   
        bt_ok2=new JButton("�滻");   
        bt_3=new JButton("�滻ȫ��");   
           
        p1.add(lab1);   
        p1.add(t1);   
        p1.add(lab2);   
        p1.add(t2);   
        p1.add(bt_ok2);   
        p1.add(bt_3);   
        this.add(p1,"Center");   
        bt_ok2.addActionListener(this);   
        bt_3.addActionListener(this);   
    }   
   
    @Override   
    public void actionPerformed(ActionEvent arg0) {   
        if(arg0.getActionCommand()=="�滻ȫ��"){   
            Pattern p=Pattern.compile(t1.getText());   
            Matcher m=p.matcher(textPane.getText());   
            textPane.setText(m.replaceAll(t2.getText()));    
            this.setVisible(false);   
        }   
        if(arg0.getActionCommand()=="�滻"){   
               
            int mouse_position = 0;   
               
//          flag���ڱ�ǣ����һ�ΰ�ť�Ȳ��ң���ڶ������滻   
            if(flag%2!=0){   
                //��ѡ�в����滻��   
            textPane.replaceSelection(t2.getText());   
            }   
            else{   
            //��Ҫ�滻�Ĳ���ѡ��   
            int star=textPane.getText().indexOf(t1.getText(),mouse_position);   
             if(star!=-1)   
             {   
             textPane.setSelectionStart(star);   
             textPane.setSelectionEnd(star+t1.getText().length());   
             mouse_position=star+1;   
             }else {   
                 JOptionPane.showMessageDialog(this, "����ʧ��");   
                 //����ʧ��ʱ�ٵ㰴ť����ִ���滻���ֵĴ��룬��flag����   
                   flag--;   
             }   
            }   
            flag++;   
        }   
    }   
}   