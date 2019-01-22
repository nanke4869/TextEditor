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
   
    String name="楷体";//字体名   
    int style=Font.BOLD;//字体样式   
    int size=20;//字体大小   
    Color color=Color.black;//字体颜色   
    Color color_bk=Color.white;   
       
    String[] fontlist =    
       GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();    
   
   
    Format(TextPane textPane){   
        this.textPane=textPane;   
           
        this.setTitle("字体设置");   
        this.setLocation(400,200);   
        //设置不可调整窗口大小   
        this.setResizable(false);   
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   
        this.setSize(400,200);   
        this.setVisible(true);   
           
        p1=new JPanel(new GridLayout(2,5));   
        p2=new JPanel();   
        p3=new JPanel();   
           
        lab1=new JLabel("字体:");   
        lab2=new JLabel("字形:");   
        lab3=new JLabel("大小:");   
        lab5=new JLabel("字体示例：ABCabc",JLabel.CENTER);   
        lab6=new JLabel("前景颜色:");   
        lab7=new JLabel("背景颜色:");   
        lab5.setForeground(color);   
           
        cbox1=new JComboBox();   
        cbox2=new JComboBox();   
        cbox3=new JComboBox();   
        bt_color=new JButton("前景色");   
        bt_backcolor=new JButton("背景色");   
           
        bt_ok=new JButton("确定");   
           
        //加载系统字体   
        for(int i=0;i<fontlist.length;i++){   
            cbox1.addItem(fontlist[i]);   
        }   
           
        cbox2.addItem("粗体");   
        cbox2.addItem("斜体");   
        cbox2.addItem("常规");   
           
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
        if(str_style.compareTo("粗体")==0)f=Font.BOLD;   
        if(str_style.compareTo("斜体")==0)f=Font.ITALIC;   
        if(str_style.compareTo("常规")==0)f=Font.PLAIN;   
        return f;   
    }   
       
    public void actionPerformed(ActionEvent e) {   
        //字体样例设置   
        if(cbox1==e.getSource()||cbox2==e.getSource()||cbox3==e.getSource()){   
            name=(String)cbox1.getSelectedItem();   
            style=this.getFont((String)cbox2.getSelectedItem());   
            size=Integer.valueOf((String)cbox3.getSelectedItem());   
           lab5.setFont(new Font(name,style,size));   
              
              
        }   
        if(e.getSource()==bt_color){   
            color=JColorChooser.showDialog(this, "颜色设置", Color.red);   
            lab5.setForeground(color);   
        }   
        if(e.getSource()==bt_backcolor){   
            color_bk=JColorChooser.showDialog(this, "颜色设置", Color.red);   
            p3.setBackground(color_bk);   
        }   
        if(e.getSource()==bt_ok){   
            //添加字大小属性到属性集attr中   
            attr.addAttribute(StyleConstants.FontSize, size);    
              //添加字体属性   
            attr.addAttribute(StyleConstants.FontFamily,name);   
            //添加字体样式属性   
            switch(style){   
            case Font.BOLD:StyleConstants.setBold(attr, false);break;   
            case Font.ITALIC:StyleConstants.setItalic(attr, false);break;   
            }   
            //添加字体前景颜色属性   
            attr.addAttribute(StyleConstants.Foreground,color);   
            //添加字体背景景颜色属性   
            attr.addAttribute(StyleConstants.Background,color_bk);   
               
            //将给定属性attr应用于选择部分字符，false表示叠加属性，true替换   
            textPane.setCharacterAttributes(attr,false);    
               
            this.setVisible(false);//隐藏窗口   
        }   
    }   
   
}   