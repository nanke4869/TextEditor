/*  
 * ����JTextPane��  
 * ���ܣ�getText��������ҹ��ܵĲ�ƥ�����⣬ʵ�ֿ����ı��Զ����й���  
 * */     

package soft;
import java.awt.Dimension;   
import javax.swing.JTextPane;   
public class TextPane extends JTextPane {    
    private static final long serialVersionUID = 1L;   
    boolean flag=true;   
       
        //���Ǹ��๹�캯��   
        public TextPane() {   
                super();   
        }   
        //���ĳ���ӿ�ʼ��Ӧ��ǿ�ȴ� Scrollable �Ŀ�ȷ��ϸ��ӿڵĿ�ȣ��򷵻� true   
            public boolean getScrollableTracksViewportWidth() {     
                return false;     
            }     
            void setFlag(boolean flag){//��ȡ�Ƿ��б��   
                this.flag=flag;   
            }   
        //��text�ı����һ�еĳ��ȱ仯ʱִ��setSize(),Dimension d�õ�����text�ı������ַ��ĳ���   
             public void setSize(Dimension d) {     
                // System.out.println(d.width+" "+getParent().getSize().width);   
                 //getParent().getSize()��ȡtextPane�ĸ�������textPane_panel�Ĵ�С   
                 //ʹtext�ı���Ŀ��ʼ��>=���������Ŀ��   
                 if(flag){   
                    
                    if (d.width < getParent().getSize().width) {     
                           d.width = getParent().getSize().width;     
                       }     
                    super.setSize(d);   
                   // System.out.println("������");   
                 }   
                 else{//����   
                        d.width = getParent().getSize().width;     
                        super.setSize(d);     
                       // System.out.println("����");   
                  }   
                    
                  }     
             //��дJTextPane��getText����������\rӰ����ҹ���   
             public String getText(){   
                 String s=null;   
                 s=super.getText();   
                 s=s.replaceAll("\r","");   
                 return s;   
             }   
        }     
