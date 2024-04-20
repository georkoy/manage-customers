/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package avon;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author KOURSOS-PC
 */
public class Namefile {

    public Namefile() {
        
            
        }
        public void read(){
 try{
        File file=new File("namesfile.txt");
        Scanner scanner=new Scanner(file);
        while(scanner.hasNext()){
            String name=scanner.next();
            String surname=scanner.next();
            String phone=scanner.next();
        NewMember.getMyList().add(new Person(name, surname, phone));
         MainFrame.leftListModel.addElement(name+" "+surname+" "+phone);
        }
 }   
    catch (IOException e){
    JOptionPane.showMessageDialog(MainFrame.getTakewindow(),"Δέν υπάρχει αρχείο με ονόματα.\nΑδυναμία ανάκτησης.","Μύνημα Λάθους ",JOptionPane.ERROR_MESSAGE);
    }
   
    }//end constr
    
}
