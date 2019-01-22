package soft;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.util.Date;
import java.text.SimpleDateFormat;

import javax.swing.*;
import javax.swing.text.*;
import javax.swing.undo.UndoManager;

public  class Texteditor extends JFrame implements ActionListener{
	
	 
	private JMenuBar menuBar;
	//�˵���
	private JMenu menu_File,menu_Edit,menu_Format,menu_View,menu_Help;
	//�˵����ڵĲ˵�
	private JMenuItem item_new,item_open,item_save,item_exit,item_find;
	//����file�˵�������
	private JMenuItem item_replace,item_undo,item_redo,item_cut,item_copy,item_paste,item_delete;
	//����edit�˵�������
	private JMenuItem item_status_bar,item_date;
	//����find�˵�������
	private JMenuItem item_about;
	//����help�˵�������
	private JMenuItem item_word_format,item_select_all;
	//����format�˵�������
	
	//UndoManager �ṩ�����ķ���
	UndoManager myundo = new UndoManager(); 
	
	//����ؼ�   
    JScrollPane textPane_panel;   
    JPanel p1;
	
	//�ı�����   
    TextPane textPane;   
    int point1,point0;//ѡ�в��ֵ�������յ�   
	
	public Texteditor() {
		initMenuBar();
		initListener();
		
		//****�ı�����󴴽���JTextPane��******************   
        textPane=new TextPane();   
        textPane.getDocument().addUndoableEditListener(myundo);   
        textPane.setFont(new Font("����",Font.PLAIN,20));   
        textPane_panel = new JScrollPane(textPane);   
        p1 = new JPanel(new BorderLayout()); 
        this.add(textPane_panel,"Center");
		
		this.setJMenuBar(menuBar);
		this.setSize(800,600);
		this.setTitle("�ı��༭��");
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	public void initMenuBar() {
		menuBar = new JMenuBar();
		menu_File = new JMenu("�ļ�(F)");
		menu_File.setMnemonic('f');//f+alt��
		item_new = new JMenuItem("�½�(N)");
		item_new.setAccelerator(KeyStroke.getKeyStroke('N',java.awt.Event.CTRL_MASK,false));
		item_open = new JMenuItem("��(O)");
		item_open.setAccelerator(KeyStroke.getKeyStroke('O',java.awt.Event.CTRL_MASK,false));
		item_save = new JMenuItem("����(S)");
		item_save.setAccelerator(KeyStroke.getKeyStroke('S',java.awt.Event.CTRL_MASK,false));
		item_exit = new JMenuItem("�˳�(E)");
		item_exit.setAccelerator(KeyStroke.getKeyStroke('E',java.awt.Event.CTRL_MASK,false));
		menu_File.add(item_new);
		menu_File.add(item_open);
		menu_File.add(item_save);
		menu_File.add(item_exit);
		//File �˵�
		
		menu_Edit = new JMenu("�༭(E)");
		menu_Edit.setMnemonic('e');
		item_replace = new JMenuItem("�滻(P)");
		item_replace.setAccelerator(KeyStroke.getKeyStroke('P',java.awt.Event.CTRL_MASK,false));
		item_undo = new JMenuItem("����(U)");
		item_undo.setAccelerator(KeyStroke.getKeyStroke('U',java.awt.Event.CTRL_MASK,false));
		item_redo = new JMenuItem("�ָ�(R)");
		item_redo.setAccelerator(KeyStroke.getKeyStroke('R',java.awt.Event.CTRL_MASK,false));
		item_cut = new JMenuItem("����(T)");
		item_cut.setAccelerator(KeyStroke.getKeyStroke('X',java.awt.Event.CTRL_MASK,false));
		item_copy = new JMenuItem("����(C)");
		item_copy.setAccelerator(KeyStroke.getKeyStroke('C',java.awt.Event.CTRL_MASK,false));
		item_paste = new JMenuItem("ճ��(V)");
		item_paste.setAccelerator(KeyStroke.getKeyStroke('V',java.awt.Event.CTRL_MASK,false));
		item_delete = new JMenuItem("ɾ��(L)");
		item_delete.setAccelerator(KeyStroke.getKeyStroke('L',java.awt.Event.CTRL_MASK,false));
		item_find = new JMenuItem("����(F)");
		item_find.setAccelerator(KeyStroke.getKeyStroke('F',java.awt.Event.CTRL_MASK,false));
		menu_Edit.add(item_replace);
		menu_Edit.add(item_undo);
		menu_Edit.add(item_redo);
		menu_Edit.add(item_cut);
		menu_Edit.add(item_copy);
		menu_Edit.add(item_paste);
		menu_Edit.add(item_delete);
		menu_Edit.add(item_find);
		//Edit �˵�
		
		menu_Format = new JMenu("��ʽ(O)");
		menu_Format.setMnemonic('o');
		item_word_format = new JMenuItem("����(Z)");
		item_word_format.setAccelerator(KeyStroke.getKeyStroke('Z',java.awt.Event.CTRL_MASK,false));//��item��ӿ�ݼ�
		item_select_all = new JMenuItem("ȫѡ(A)");
		item_select_all.setAccelerator(KeyStroke.getKeyStroke('A',java.awt.Event.CTRL_MASK,false));
		menu_Format.add(item_word_format);
		menu_Format.add(item_select_all);
		//Format �˵�
		
		menu_View = new JMenu("�鿴(V)");
		menu_View.setMnemonic('v');
		item_status_bar = new JMenuItem("״̬��(S)");
		item_date = new JMenuItem("ʱ��(T)");
		item_date.setAccelerator(KeyStroke.getKeyStroke('T',java.awt.Event.CTRL_MASK,false));
		menu_View.add(item_status_bar);
		menu_View.add(item_date);
		
		menu_Help = new JMenu("����(H)");
		menu_Help.setMnemonic('h');
		item_about = new JMenuItem("����(G)");
		item_about.setAccelerator(KeyStroke.getKeyStroke('G',java.awt.Event.CTRL_MASK,false));
		menu_Help.add(item_about);
		//Help �˵�
		
		menuBar.add(menu_File);
		menuBar.add(menu_Edit);
		menuBar.add(menu_Format);
		menuBar.add(menu_View);
		menuBar.add(menu_Help);
	}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource() == item_word_format) {//����
			    Format format = new Format(textPane);
			}else if (e.getSource() == item_select_all) {//ȫѡ
				textPane.selectAll();
			}else if (e.getSource() == item_about) {//����
				new About_Window();
			}else if (e.getSource() == item_new) {//�½�
				new Texteditor();
			}else if (e.getSource() == item_exit) {//�ر�
				this.dispose();
			}else if (e.getSource() == item_open) {//��
				OpenSave open=new OpenSave(textPane);   
	            open.open(); 
			}else if (e.getSource() == item_save) {//����
				OpenSave save=new OpenSave(textPane);   
	            save.save(); 
			}else if (e.getSource() == item_undo) {//����
				if(myundo.canUndo())   
		            myundo.undo();   
		            else JOptionPane.showMessageDialog(this, "�޷�����");
			}else if (e.getSource() == item_redo) {//�ָ�
				if(myundo.canRedo())   
		            myundo.redo();   
		            else JOptionPane.showMessageDialog(this, "�޷��ָ�");
			}else if (e.getSource() == item_cut) {//����
				textPane.cut();
			}else if (e.getSource() == item_copy) {//����
				textPane.copy();
			}else if (e.getSource() == item_paste) {//ճ��
				textPane.paste();
			}else if (e.getSource() == item_replace) {//�滻
			    Replace replace = new Replace(textPane);
			}else if (e.getSource() == item_find) {//����
				Find find = new Find(textPane); 
				find.setVisible(true);
			}else if (e.getSource() == item_date) {//ʱ��
				Date nowTime = new Date();
				SimpleDateFormat h=new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");   
		        String str1=textPane.getText().substring(0, textPane.getCaretPosition());   
		        String str2=textPane.getText().substring(textPane.getCaretPosition());   
		        //�ڹ������λ�ò���ʱ��   
		        textPane.setText(str1+h.format(nowTime)+str2);
			}
		}
		
		/**
		 * ������btn��itemͳһ���ü�����
		 */
		public void initListener() {
			item_new.addActionListener(this);
			item_open.addActionListener(this);
			item_save.addActionListener(this);
			item_exit.addActionListener(this);
			item_find.addActionListener(this);
			item_replace.addActionListener(this);
			item_undo.addActionListener(this);
			item_redo.addActionListener(this);
			item_cut.addActionListener(this);
			item_copy.addActionListener(this);
			item_paste.addActionListener(this);
			item_delete.addActionListener(this);
			item_word_format.addActionListener(this);
			item_select_all.addActionListener(this);
			item_status_bar.addActionListener(this);
			item_date.addActionListener(this);
			item_about.addActionListener(this);
		}
				
		public static void main(String[] args) {
			Texteditor texteditor = new Texteditor();
		}

}
 

