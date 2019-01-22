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
	//菜单栏
	private JMenu menu_File,menu_Edit,menu_Format,menu_View,menu_Help;
	//菜单栏内的菜单
	private JMenuItem item_new,item_open,item_save,item_exit,item_find;
	//对于file菜单的子项
	private JMenuItem item_replace,item_undo,item_redo,item_cut,item_copy,item_paste,item_delete;
	//对于edit菜单的子项
	private JMenuItem item_status_bar,item_date;
	//对于find菜单的子项
	private JMenuItem item_about;
	//对于help菜单的子项
	private JMenuItem item_word_format,item_select_all;
	//对于format菜单的子项
	
	//UndoManager 提供撤消的方法
	UndoManager myundo = new UndoManager(); 
	
	//定义控件   
    JScrollPane textPane_panel;   
    JPanel p1;
	
	//文本区域   
    TextPane textPane;   
    int point1,point0;//选中部分的起点与终点   
	
	public Texteditor() {
		initMenuBar();
		initListener();
		
		//****文本框对象创建（JTextPane）******************   
        textPane=new TextPane();   
        textPane.getDocument().addUndoableEditListener(myundo);   
        textPane.setFont(new Font("宋体",Font.PLAIN,20));   
        textPane_panel = new JScrollPane(textPane);   
        p1 = new JPanel(new BorderLayout()); 
        this.add(textPane_panel,"Center");
		
		this.setJMenuBar(menuBar);
		this.setSize(800,600);
		this.setTitle("文本编辑器");
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	public void initMenuBar() {
		menuBar = new JMenuBar();
		menu_File = new JMenu("文件(F)");
		menu_File.setMnemonic('f');//f+alt打开
		item_new = new JMenuItem("新建(N)");
		item_new.setAccelerator(KeyStroke.getKeyStroke('N',java.awt.Event.CTRL_MASK,false));
		item_open = new JMenuItem("打开(O)");
		item_open.setAccelerator(KeyStroke.getKeyStroke('O',java.awt.Event.CTRL_MASK,false));
		item_save = new JMenuItem("保存(S)");
		item_save.setAccelerator(KeyStroke.getKeyStroke('S',java.awt.Event.CTRL_MASK,false));
		item_exit = new JMenuItem("退出(E)");
		item_exit.setAccelerator(KeyStroke.getKeyStroke('E',java.awt.Event.CTRL_MASK,false));
		menu_File.add(item_new);
		menu_File.add(item_open);
		menu_File.add(item_save);
		menu_File.add(item_exit);
		//File 菜单
		
		menu_Edit = new JMenu("编辑(E)");
		menu_Edit.setMnemonic('e');
		item_replace = new JMenuItem("替换(P)");
		item_replace.setAccelerator(KeyStroke.getKeyStroke('P',java.awt.Event.CTRL_MASK,false));
		item_undo = new JMenuItem("撤消(U)");
		item_undo.setAccelerator(KeyStroke.getKeyStroke('U',java.awt.Event.CTRL_MASK,false));
		item_redo = new JMenuItem("恢复(R)");
		item_redo.setAccelerator(KeyStroke.getKeyStroke('R',java.awt.Event.CTRL_MASK,false));
		item_cut = new JMenuItem("剪切(T)");
		item_cut.setAccelerator(KeyStroke.getKeyStroke('X',java.awt.Event.CTRL_MASK,false));
		item_copy = new JMenuItem("复制(C)");
		item_copy.setAccelerator(KeyStroke.getKeyStroke('C',java.awt.Event.CTRL_MASK,false));
		item_paste = new JMenuItem("粘贴(V)");
		item_paste.setAccelerator(KeyStroke.getKeyStroke('V',java.awt.Event.CTRL_MASK,false));
		item_delete = new JMenuItem("删除(L)");
		item_delete.setAccelerator(KeyStroke.getKeyStroke('L',java.awt.Event.CTRL_MASK,false));
		item_find = new JMenuItem("查找(F)");
		item_find.setAccelerator(KeyStroke.getKeyStroke('F',java.awt.Event.CTRL_MASK,false));
		menu_Edit.add(item_replace);
		menu_Edit.add(item_undo);
		menu_Edit.add(item_redo);
		menu_Edit.add(item_cut);
		menu_Edit.add(item_copy);
		menu_Edit.add(item_paste);
		menu_Edit.add(item_delete);
		menu_Edit.add(item_find);
		//Edit 菜单
		
		menu_Format = new JMenu("格式(O)");
		menu_Format.setMnemonic('o');
		item_word_format = new JMenuItem("字体(Z)");
		item_word_format.setAccelerator(KeyStroke.getKeyStroke('Z',java.awt.Event.CTRL_MASK,false));//给item添加快捷键
		item_select_all = new JMenuItem("全选(A)");
		item_select_all.setAccelerator(KeyStroke.getKeyStroke('A',java.awt.Event.CTRL_MASK,false));
		menu_Format.add(item_word_format);
		menu_Format.add(item_select_all);
		//Format 菜单
		
		menu_View = new JMenu("查看(V)");
		menu_View.setMnemonic('v');
		item_status_bar = new JMenuItem("状态栏(S)");
		item_date = new JMenuItem("时间(T)");
		item_date.setAccelerator(KeyStroke.getKeyStroke('T',java.awt.Event.CTRL_MASK,false));
		menu_View.add(item_status_bar);
		menu_View.add(item_date);
		
		menu_Help = new JMenu("帮助(H)");
		menu_Help.setMnemonic('h');
		item_about = new JMenuItem("关于(G)");
		item_about.setAccelerator(KeyStroke.getKeyStroke('G',java.awt.Event.CTRL_MASK,false));
		menu_Help.add(item_about);
		//Help 菜单
		
		menuBar.add(menu_File);
		menuBar.add(menu_Edit);
		menuBar.add(menu_Format);
		menuBar.add(menu_View);
		menuBar.add(menu_Help);
	}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource() == item_word_format) {//字体
			    Format format = new Format(textPane);
			}else if (e.getSource() == item_select_all) {//全选
				textPane.selectAll();
			}else if (e.getSource() == item_about) {//关于
				new About_Window();
			}else if (e.getSource() == item_new) {//新建
				new Texteditor();
			}else if (e.getSource() == item_exit) {//关闭
				this.dispose();
			}else if (e.getSource() == item_open) {//打开
				OpenSave open=new OpenSave(textPane);   
	            open.open(); 
			}else if (e.getSource() == item_save) {//保存
				OpenSave save=new OpenSave(textPane);   
	            save.save(); 
			}else if (e.getSource() == item_undo) {//撤销
				if(myundo.canUndo())   
		            myundo.undo();   
		            else JOptionPane.showMessageDialog(this, "无法撤消");
			}else if (e.getSource() == item_redo) {//恢复
				if(myundo.canRedo())   
		            myundo.redo();   
		            else JOptionPane.showMessageDialog(this, "无法恢复");
			}else if (e.getSource() == item_cut) {//剪切
				textPane.cut();
			}else if (e.getSource() == item_copy) {//复制
				textPane.copy();
			}else if (e.getSource() == item_paste) {//粘贴
				textPane.paste();
			}else if (e.getSource() == item_replace) {//替换
			    Replace replace = new Replace(textPane);
			}else if (e.getSource() == item_find) {//查找
				Find find = new Find(textPane); 
				find.setVisible(true);
			}else if (e.getSource() == item_date) {//时间
				Date nowTime = new Date();
				SimpleDateFormat h=new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");   
		        String str1=textPane.getText().substring(0, textPane.getCaretPosition());   
		        String str2=textPane.getText().substring(textPane.getCaretPosition());   
		        //在光标所在位置插入时间   
		        textPane.setText(str1+h.format(nowTime)+str2);
			}
		}
		
		/**
		 * 对所有btn跟item统一设置监听器
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
 

