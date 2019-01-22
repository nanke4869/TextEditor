/*  
 * 重载JTextPane类  
 * 功能：getText函数与查找功能的不匹配问题，实现控制文本自动换行功能  
 * */     

package soft;
import java.awt.Dimension;   
import javax.swing.JTextPane;   
public class TextPane extends JTextPane {    
    private static final long serialVersionUID = 1L;   
    boolean flag=true;   
       
        //覆盖父类构造函数   
        public TextPane() {   
                super();   
        }   
        //如果某个视口始终应该强迫此 Scrollable 的宽度符合该视口的宽度，则返回 true   
            public boolean getScrollableTracksViewportWidth() {     
                return false;     
            }     
            void setFlag(boolean flag){//获取是否换行标记   
                this.flag=flag;   
            }   
        //当text文本框最长一行的长度变化时执行setSize(),Dimension d得到的是text文本框中字符的长度   
             public void setSize(Dimension d) {     
                // System.out.println(d.width+" "+getParent().getSize().width);   
                 //getParent().getSize()获取textPane的父级容器textPane_panel的大小   
                 //使text文本框的宽度始终>=父级容器的宽度   
                 if(flag){   
                    
                    if (d.width < getParent().getSize().width) {     
                           d.width = getParent().getSize().width;     
                       }     
                    super.setSize(d);   
                   // System.out.println("不换行");   
                 }   
                 else{//换行   
                        d.width = getParent().getSize().width;     
                        super.setSize(d);     
                       // System.out.println("换行");   
                  }   
                    
                  }     
             //重写JTextPane的getText函数，避免\r影响查找功能   
             public String getText(){   
                 String s=null;   
                 s=super.getText();   
                 s=s.replaceAll("\r","");   
                 return s;   
             }   
        }     
