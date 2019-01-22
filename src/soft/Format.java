package soft;

import java.awt.*;   
import java.awt.event.*;   
   
import javax.swing.*;   
import javax.swing.text.*;   
   
public class Format extends JFrame implements ActionListener{   
    /**  
     *   
     */   
    private static final long serialVersionUID = 1L;   
    MutableAttributeSet attr=new SimpleAttributeSet();   
    TextPane textPane;   
       
    JPanel p1,p2,p3;   
    JLabel lab1,lab2,lab3,lab4,lab5,lab6,lab7;   
    JComboBox cbox1,cbox2,cbox3;   
    JButton bt_color,bt_backcolor;   
    JButton bt_ok;   
   
    String name="����";//������   
    int style=Font.BOLD;//������ʽ   
    int size=20;//�����С   
    Color color=Color.black;//������ɫ   
    Color color_bk=Color.white;   
       
    String[] fontlist =    
       GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();    
   
   
    Format(TextPane textPane){   
        this.textPane=textPane;   
           
        this.setTitle("��������");   
        this.setLocation(400,200);   
        //���ò��ɵ������ڴ�С   
        this.setResizable(false);   
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   
        this.setSize(400,200);   
        this.setVisible(true);   
           
        p1=new JPanel(new GridLayout(2,5));   
        p2=new JPanel();   
        p3=new JPanel();   
           
        lab1=new JLabel("����:");   
        lab2=new JLabel("����:");   
        lab3=new JLabel("��С:");   
        lab5=new JLabel("����ʾ����ABCabc",JLabel.CENTER);   
        lab6=new JLabel("ǰ����ɫ:");   
        lab7=new JLabel("������ɫ:");   
        lab5.setForeground(color);   
           
        cbox1=new JComboBox();   
        cbox2=new JComboBox();   
        cbox3=new JComboBox();   
        bt_color=new JButton("ǰ��ɫ");   
        bt_backcolor=new JButton("����ɫ");   
           
        bt_ok=new JButton("ȷ��");   
           
        //����ϵͳ����   
        for(int i=0;i<fontlist.length;i++){   
            cbox1.addItem(fontlist[i]);   
        }   
           
        cbox2.addItem("����");   
        cbox2.addItem("б��");   
        cbox2.addItem("����");   
           
        for(int i=20;i<80;i++){   
            cbox3.addItem(""+i);   
        }   
           
        p1.add(lab1);   
        p1.add(lab2);   
        p1.add(lab3);   
        p1.add(lab6);   
        p1.add(lab7);   
           
        p1.add(cbox1);   
        p1.add(cbox2);   
        p1.add(cbox3);   
        p1.add(bt_color);   
        p1.add(bt_backcolor);   
           
        p2.add(bt_ok);   
           
        p3.add(lab5);   
        p3.setBackground(Color.white);   
           
        this.add(p1,"North");   
        this.add(p3,"Center");   
        this.add(p2,"South");   
           
        cbox1.addActionListener(this);   
        cbox2.addActionListener(this);   
        cbox3.addActionListener(this);   
        bt_ok.addActionListener(this);   
        bt_color.addActionListener(this);   
        bt_backcolor.addActionListener(this);   
    }   
       
    public static void main(String[] args) {   
    }   
       
    public int getFont(String str_style){   
        int f=0;   
        if(str_style.compareTo("����")==0)f=Font.BOLD;   
        if(str_style.compareTo("б��")==0)f=Font.ITALIC;   
        if(str_style.compareTo("����")==0)f=Font.PLAIN;   
        return f;   
    }   
       
    public void actionPerformed(ActionEvent e) {   
        //������������   
        if(cbox1==e.getSource()||cbox2==e.getSource()||cbox3==e.getSource()){   
            name=(String)cbox1.getSelectedItem();   
            style=this.getFont((String)cbox2.getSelectedItem());   
            size=Integer.valueOf((String)cbox3.getSelectedItem());   
           lab5.setFont(new Font(name,style,size));   
              
              
        }   
        if(e.getSource()==bt_color){   
            color=JColorChooser.showDialog(this, "��ɫ����", Color.red);   
            lab5.setForeground(color);   
        }   
        if(e.getSource()==bt_backcolor){   
            color_bk=JColorChooser.showDialog(this, "��ɫ����", Color.red);   
            p3.setBackground(color_bk);   
        }   
        if(e.getSource()==bt_ok){   
            //����ִ�С���Ե����Լ�attr��   
            attr.addAttribute(StyleConstants.FontSize, size);    
              //�����������   
            attr.addAttribute(StyleConstants.FontFamily,name);   
            //���������ʽ����   
            switch(style){   
            case Font.BOLD:StyleConstants.setBold(attr, false);break;   
            case Font.ITALIC:StyleConstants.setItalic(attr, false);break;   
            }   
            //�������ǰ����ɫ����   
            attr.addAttribute(StyleConstants.Foreground,color);   
            //������屳������ɫ����   
            attr.addAttribute(StyleConstants.Background,color_bk);   
               
            //����������attrӦ����ѡ�񲿷��ַ���false��ʾ�������ԣ�true�滻   
            textPane.setCharacterAttributes(attr,false);    
               
            this.setVisible(false);//���ش���   
        }   
    }   
   
}   