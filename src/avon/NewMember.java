/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package avon;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.text.Collator;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author KOURSOS-PC
 */
public class NewMember extends JDialog{
    private  JTextField nametField;
    private JTextField surnameTextField;
    private static  JTextField phonelField;
    private JButton addbButton;
    private JButton clear;
    private  static  List<Person> myList = new LinkedList<>();
  public static Person person;
  private boolean namebool=false;
  boolean suramebool=false;
  boolean phonebool=false;
  private RandomAccessFile randfile;
    public NewMember(Frame parent) {
        super(parent,"Νέο Μέλος",true);
         setSize(450,200);
        this.setMaximumSize(new Dimension(500,220));
        setMinimumSize(new Dimension(300,150));
        setLayout(new BorderLayout());
        JPanel centermainpanel=new JPanel();
       JPanel mainpanel=new JPanel(new GridLayout(3, 2, 5, 5));
        JLabel namelabel=new JLabel("Όνομα:");
         nametField=new JTextField(10);
        JLabel surnamelabel=new JLabel("Επώμυνο:");
         surnameTextField=new JTextField(10);
        JLabel phonelJLabel=new JLabel("Τηλέφωνο:");
        phonelField=new JTextField(10);
       
        mainpanel.add(namelabel);
        mainpanel.add(nametField);
        mainpanel.add(surnamelabel);
        mainpanel.add(surnameTextField);
        mainpanel.add(phonelJLabel);
        mainpanel.add(phonelField);
        add(mainpanel);
   
        JPanel butonJPanel=new JPanel(new FlowLayout());
          addbButton=new JButton("Προσθήκη");
          clear=new JButton("Καθαρισμός");
         butonJPanel.add(addbButton);
         butonJPanel.add(clear);
         add(butonJPanel);
        centermainpanel.add(mainpanel);
        add(centermainpanel,BorderLayout.CENTER);
        add(butonJPanel,BorderLayout.SOUTH);
clear.addActionListener(new ActionListener() {
           @Override
            public void actionPerformed(ActionEvent ae) {
              nametField.setText(" ");
              surnameTextField.setText(" ");
              phonelField.setText(" ");
            }
        });

addbButton.setEnabled(false);
try{
nametField.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent de) {
                if(nametField.getText().isEmpty()){
                    namebool=false;
                    change();
                }
                else{
                    namebool=true;
                    change();
                }
            }

            @Override
            public void removeUpdate(DocumentEvent de) {
               if(nametField.getText().isEmpty()){
                    namebool=false;
                    change();
                }
                else{
                    namebool=true;
                    change();
                }
            }

            @Override
            public void changedUpdate(DocumentEvent de) {
            }
        });


surnameTextField.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent de) {
                 if(surnameTextField.getText().isEmpty()){
               
                    suramebool=false;
                    change();
                }
                else{
                    
                    suramebool=true;
                    change();
                }
            }

            @Override
            public void removeUpdate(DocumentEvent de) {
                 if(surnameTextField.getText().isEmpty()){
                    suramebool=false;
                    change();
                }
                else{
                    suramebool=true;
                    change();
                }
            }

            @Override
            public void changedUpdate(DocumentEvent de) {
                
            }
        });



phonelField.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent de) {
               if(phonelField.getText().isEmpty()){
                    phonebool=false;
                    change();
                }
                else{
                    phonebool=true;
                    change();
                }
            }

            @Override
            public void removeUpdate(DocumentEvent de) {
                if(phonelField.getText().isEmpty()){
                    phonebool=false;
                    change();
                }
                else{
                    phonebool=true;
                    change();
                }
            }

            @Override
            public void changedUpdate(DocumentEvent de) {
                
            }
        });
}
catch(UnsupportedOperationException e){
    
}

  addbButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { 
              NewMember.this.setVisible(false);
       String name=nametField.getText();
      String surname=surnameTextField.getText();
             String phone=phonelField.getText();
               myList.add(person=new Person(name, surname, phone));
             //   MainFrame.leftListModel.addElement(name+" "+surname+" - "+phone);
               Collections.sort(myList,new Comparator<Person>() {
                    @Override
                    public int compare(Person t, Person t1) {
                        return Collator.getInstance().compare(t.getSurname(), t1.getSurname());
                    }
                });
           
               if(!MainFrame.leftListModel.isEmpty()){        
               MainFrame.leftListModel.removeAllElements();
               }         
               
               for(int i=0;i<myList.size();i++){
                   MainFrame.leftListModel.addElement(myList.get(i).getName()+" "+myList.get(i).getSurname()+" - "+myList.get(i).getPhone());
                   
               }
               
             try{
                 randfile=new RandomAccessFile("namesfile.txt","rw");
                    try {
                        randfile.setLength(0);
                    } catch (IOException ex) {
                       JOptionPane.showMessageDialog(MainFrame.getTakewindow(),"Αδυναμία ενημέρωσης του αρχείου ονομάτων\n Προσπαθήστε ξανά","Μύνημα Λάθους ",JOptionPane.ERROR_MESSAGE);
                    }
             FileOutputStream fos = new FileOutputStream("namesfile.txt", true);
            PrintWriter pw=new PrintWriter(fos);
            for(int i=0;i<myList.size();i++){
            pw.println(myList.get(i).getName() +" "+myList.get(i).getSurname() +" "+myList.get(i).getPhone());
            }
            pw.close();
              FileOutputStream fos1 = new FileOutputStream(name+" "+surname+".txt",true);
              MainFrame.getLeftList().setSelectedValue(name+" "+surname+" - "+phone, true);
          
             }
             catch(FileNotFoundException ea){
                 System.err.println(ea);
             }
             MainFrame.getOrder().setVisible(true);
            }
        });
  addbButton.getRootPane().setDefaultButton(addbButton);
  
 
    
    }   //end cosnt
    
    private void change(){
        if(namebool==true&&suramebool==true&&phonebool==true){
            addbButton.setEnabled(true);
        }
        else{
            addbButton.setEnabled(false);
        }
    }

    public  static  String getPhonelField() {
        return phonelField.getText();
    }

    public static Person getPerson() {
        return person;
    }

    public JTextField getNametField() {
        return nametField;
    }

    public JTextField getSurnameTextField() {
        return surnameTextField;
    }

   
     public  JTextField   getPhonelFieldempty() {
        return phonelField;
    }

    public static List<Person> getMyList() {
        return myList;
    }
}
