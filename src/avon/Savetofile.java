/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package avon;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import javax.swing.JOptionPane;

/**
 *
 * @author KOURSOS-PC
 */
public class Savetofile {
private String value= MainFrame.getLeftList().getSelectedValue();
 private String parts[]=value.split(" ");
 private String name=parts[0];
 private  String surname= parts[1];
 
   public Savetofile(String cambanetextfield,String codetextfield,String quantitytextfield,String pricetextfield,String producttextfield,String pagetextfield){
        try{
            FileOutputStream fos = new FileOutputStream(name+" "+surname+".txt", true);
            PrintWriter pw=new PrintWriter(fos);
            
            pw.print(cambanetextfield+" ");
             pw.print(codetextfield+" ");
               pw.print(quantitytextfield+" ");
                pw.print(pricetextfield+" ");
                pw.print(pricetextfield+" ");
                  pw.println(pagetextfield+" ");
                     pw.close();                     
        }
        catch (FileNotFoundException e){
             JOptionPane.showMessageDialog(MainFrame.getTakewindow(),"Πρόβλημα κατά την αποθήκευση-ενημέρωση δεδομένων του αρχείου "+name+" "+surname+".\n Το αρχείο δεν βρέθηκε.");
        }
        
     
      
    }//end constr
    
    
    
    
    
}
