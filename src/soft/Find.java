package soft;

import java.awt.event.*;   
import javax.swing.*;   
   
public class Find extends JFrame implements ActionListener,MouseListener,KeyListener{   
    /**  
     *   
     */   
    private static final long serialVersionUID = 1L;   
       
    JDialog dialog1;   
    JRadioButton rbt1,rbt2;   
    ButtonGroup group;   
    JTextField t1;   
    JButton bt_ok2,bt_ok1;   
    TextPane textPane;   
    JPanel p1;   
    int mouse_position;   
       
    Find(TextPane textPane){   
        this.textPane=textPane;   
           
        //this.setVisible(true);   
        this.setSize(200,180);   
        this.setLocation(400,200);   
        this.setTitle("查找");   
        this.setResizable(false);   
           
        p1=new JPanel();   
           
        JLabel lab1=new JLabel("查找内容：",JLabel.CENTER);   
        t1=new JTextField(10);   
        bt_ok1=new JButton("查找");   
           
        rbt1=new JRadioButton("向上查找");   
        rbt2=new JRadioButton("向下查找");   
        rbt2.setSelected(true);   
        group=new ButtonGroup();   
        group.add(rbt1);   
        group.add(rbt2);   
           
           
        p1.add(lab1);   
        p1.add(t1);   
        p1.add(rbt1);   
        p1.add(rbt2);   
        p1.add(bt_ok1);   
        bt_ok1.addActionListener(this);   
        rbt1.addActionListener(this);   
        rbt2.addActionListener(this);   
        textPane.addMouseListener(this);   
        textPane.addKeyListener(this);   
        this.add(p1);   
    }   
   
    @Override   
    public void actionPerformed(ActionEvent arg0) {   
        //改变搜索方向时，重新获得光标位置   
        if(arg0.getActionCommand()=="向上查找"||arg0.getActionCommand()=="向下查找")   
        {   
            mouse_position=textPane.getCaretPosition();   
            //System.out.println(mouse_position);   
        }   
           
        if(arg0.getSource()==bt_ok1){   
               
               
            //System.out.print(textPane.getText().length());   
               
               
            //向下搜索   
            if(rbt2.isSelected()){   
                int star=textPane.getText().indexOf(t1.getText(),mouse_position);   
                if(star!=-1)   
                {   
                textPane.setSelectionStart(star);   
                textPane.setSelectionEnd(star+t1.getText().length());   
                mouse_position=star+1;   
                }else JOptionPane.showMessageDialog(this, "查找失败");   
            }   
            //向上搜索   
            else if(rbt1.isSelected()){   
                   
                int star=textPane.getText().lastIndexOf(t1.getText(),mouse_position);   
                   
                //star==-1时表示查找失败   
                if(star!=-1){   
                textPane.setSelectionStart(star);   
                textPane.setSelectionEnd(star+t1.getText().length());   
                mouse_position=star-1;   
                }else {   
                    JOptionPane.showMessageDialog(this, "查找失败");   
                  }   
                }   
            }   
        }   
           
    public void keyPressed(KeyEvent arg0) {   
           
        if(arg0.getKeyCode()==KeyEvent.VK_UP||arg0.getKeyCode()==KeyEvent.VK_DOWN||   
        arg0.getKeyCode()==KeyEvent.VK_LEFT||arg0.getKeyCode()==KeyEvent.VK_RIGHT)   
//          单击鼠标时获取光标的位置   
        mouse_position=textPane.getCaretPosition();   
    }   
    public void mousePressed(MouseEvent arg0) {   
        //按下方向键时获取光标的位置   
        mouse_position=textPane.getCaretPosition();   
    }   
   
    @Override   
    //鼠标监听事件   
    public void mouseClicked(MouseEvent arg0) {}   
    public void mouseEntered(MouseEvent arg0) {}   
    public void mouseExited(MouseEvent arg0) {}   
    public void mouseReleased(MouseEvent arg0) {}   
       
    //按键监听事件   
    public void keyReleased(KeyEvent arg0) {}   
    public void keyTyped(KeyEvent arg0) {}   
}   
