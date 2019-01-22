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
        this.setTitle("����");   
        this.setResizable(false);   
           
        p1=new JPanel();   
           
        JLabel lab1=new JLabel("�������ݣ�",JLabel.CENTER);   
        t1=new JTextField(10);   
        bt_ok1=new JButton("����");   
           
        rbt1=new JRadioButton("���ϲ���");   
        rbt2=new JRadioButton("���²���");   
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
        //�ı���������ʱ�����»�ù��λ��   
        if(arg0.getActionCommand()=="���ϲ���"||arg0.getActionCommand()=="���²���")   
        {   
            mouse_position=textPane.getCaretPosition();   
            //System.out.println(mouse_position);   
        }   
           
        if(arg0.getSource()==bt_ok1){   
               
               
            //System.out.print(textPane.getText().length());   
               
               
            //��������   
            if(rbt2.isSelected()){   
                int star=textPane.getText().indexOf(t1.getText(),mouse_position);   
                if(star!=-1)   
                {   
                textPane.setSelectionStart(star);   
                textPane.setSelectionEnd(star+t1.getText().length());   
                mouse_position=star+1;   
                }else JOptionPane.showMessageDialog(this, "����ʧ��");   
            }   
            //��������   
            else if(rbt1.isSelected()){   
                   
                int star=textPane.getText().lastIndexOf(t1.getText(),mouse_position);   
                   
                //star==-1ʱ��ʾ����ʧ��   
                if(star!=-1){   
                textPane.setSelectionStart(star);   
                textPane.setSelectionEnd(star+t1.getText().length());   
                mouse_position=star-1;   
                }else {   
                    JOptionPane.showMessageDialog(this, "����ʧ��");   
                  }   
                }   
            }   
        }   
           
    public void keyPressed(KeyEvent arg0) {   
           
        if(arg0.getKeyCode()==KeyEvent.VK_UP||arg0.getKeyCode()==KeyEvent.VK_DOWN||   
        arg0.getKeyCode()==KeyEvent.VK_LEFT||arg0.getKeyCode()==KeyEvent.VK_RIGHT)   
//          �������ʱ��ȡ����λ��   
        mouse_position=textPane.getCaretPosition();   
    }   
    public void mousePressed(MouseEvent arg0) {   
        //���·����ʱ��ȡ����λ��   
        mouse_position=textPane.getCaretPosition();   
    }   
   
    @Override   
    //�������¼�   
    public void mouseClicked(MouseEvent arg0) {}   
    public void mouseEntered(MouseEvent arg0) {}   
    public void mouseExited(MouseEvent arg0) {}   
    public void mouseReleased(MouseEvent arg0) {}   
       
    //���������¼�   
    public void keyReleased(KeyEvent arg0) {}   
    public void keyTyped(KeyEvent arg0) {}   
}   
