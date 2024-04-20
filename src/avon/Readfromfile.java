/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package avon;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author KOURSOS-PC
 */
public  class Readfromfile {
 private String value= MainFrame.getLeftList().getSelectedValue();
 private String parts[]=value.split(" ");
 private String name=parts[0];
 private  String surname= parts[1];
    public Readfromfile() {
        
    } 
     
    public void read(){
         try{
        File file=new File(name+" "+surname+".txt");
        Scanner scanner=new Scanner(file);
  
           if (MainFrame.getTableModel().getRowCount() > 0) {
    for (int i = MainFrame.getTableModel().getRowCount() - 1; i > -1; i--) {
        MainFrame.getTableModel().removeRow(i);
    }
}         
        while(scanner.hasNext()){
             MainFrame.getTableModel().addRow(new Object[]{scanner.next(),scanner.next(),scanner.next(),scanner.next(),scanner.next(),scanner.next()});
        }
             
     }
     catch (FileNotFoundException e){
      
         JOptionPane.showMessageDialog(MainFrame.getTakewindow(),"Πρόβλημα κατά την ανάκτηση δεδομένων απο το αρχείο "+name+" "+surname+".\n Το Αρχείο δεν βρέθηκε.");
     }
    
       catch (NoSuchElementException e){
            JOptionPane.showMessageDialog(MainFrame.getTakewindow(),"Άδειο αρχείο "+name+" "+surname+".\nΑδυναμία ανάκτησης.","Μύνημα Λάθους ",JOptionPane.ERROR_MESSAGE);
             
       }
       
    }

   
    
}
