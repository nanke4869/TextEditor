package soft;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class About_Window  extends JFrame{ 
	
		
		private JButton btn_ok;
		private JLabel about_label1,about_label2,about_label3;
	 
		private JPanel panel ;
		private BoxLayout boxlayout;
		
		/**
		 * ���ڵĹ��캯��
		 */
		public About_Window() {
			panel = new JPanel();
			boxlayout = new BoxLayout(panel,BoxLayout.Y_AXIS);
			panel.setLayout(boxlayout);
			
			btn_ok = new JButton("OK");
			btn_ok.setAlignmentX(CENTER_ALIGNMENT);
			about_label1 = new JLabel("~~~~~~~~~~�ı��༭��~~~~~~~~~~");
			about_label1.setAlignmentX(CENTER_ALIGNMENT);
	        about_label2 = new JLabel("����ʱ�䣺2018/12/23");
	        about_label2.setAlignmentX(CENTER_ALIGNMENT);
	        about_label3 = new JLabel("�����ˣ�B16112112-��Ӱ");
	        about_label3.setAlignmentX(CENTER_ALIGNMENT);
			panel.add(about_label1);
			panel.add(about_label2);
			panel.add(about_label3);
			panel.add(btn_ok);
			
			
			this.add(panel);
			this.setSize(300,200);
			this.setTitle("�����ı��༭��");
			this.setVisible(true);
			this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			
			btn_ok.addActionListener(e->{//���OK���˳�
				this.dispose();
			});
		}
}


