package soft;

import java.awt.FileDialog;   
import java.io.*;   
import javax.swing.*;   
   
   
public class OpenSave {   
    String txt="";   
    TextPane textPane;   
    FileDialog jfc1;   
    FileDialog jfc2;   
       
    BufferedReader br;   
    FileReader fr;   
       
    OpenSave(TextPane textPane){   
        this.textPane=textPane;   
    }   
  public void open(){   
      String filename="";   
        jfc1=new FileDialog(new JFrame(),"���ļ�",FileDialog.LOAD);      
        jfc1.setVisible(true);    
           
        if(jfc1.getFile()!=null)   
        try{   
            //����ļ�·��   
            filename=jfc1.getDirectory()+jfc1.getFile();   
            //System.out.println(filename);   
            fr=new FileReader(filename);   
            br=new BufferedReader(fr);   
            textPane.read(br, fr);   
               
        }catch(Exception e){   
            e.printStackTrace();   
        }finally{   
            try{   
            fr.close();   
            br.close();   
            }catch(Exception e){   
                   
            }   
        }   
           
        //����껹ԭ����ʼλ��   
        textPane.setCaretPosition(0);   
  }   
 public void save(){   
         String filename="";   
        jfc2=new FileDialog(new JFrame(),"�����ļ�",FileDialog.SAVE);   
        jfc2.setVisible(true);   
        try{   
            filename=jfc2.getDirectory()+jfc2.getFile();   
            FileWriter fw=new FileWriter(filename);   
            //BufferedWriter bw=new BufferedWriter(fw);   
            fw.write(textPane.getText());   
            fw.close();   
            //bw.close();   
        }catch(Exception e){   
        }   
 }   
}   