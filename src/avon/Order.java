/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package avon;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
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
public  class Order extends JDialog {
      private static JComboBox<String> cambaneComboBox = new JComboBox<>();
      private  JTextField cambanetextfield;
      private   JTextField codetextfield;
      private JTextField quantitytextfield;
      private  JTextField pricetextfield;
      private  JTextField producttextfield;
      private  JTextField pagetextfield;
      private JTextField numberorder;
      private  int count;
      private  JButton okbButton;
      private Savetofile save;
      private boolean cambanebool=false;
      private boolean codebool=false;
      private boolean quantitybool=false;
      private boolean  pricebool=false;
      private boolean productbool=false;
      private boolean pagebool=false;
      private boolean  numberorderbool=false;
      private boolean error=false;
   private JCheckBox  newcampane;
   private JCheckBox colorme;
   
      public Order(Frame parent){
      super(parent,"Παραγγελία",true);
      setSize(350, 350);
     setMinimumSize(new Dimension(350,350));
      setLayout(new BorderLayout());
      
      JPanel mainpanel=new JPanel();
      JPanel centerpanel=new JPanel(new GridLayout(8, 2, 8, 8));
       cambanetextfield=new JTextField(10);
      cambanetextfield.setEnabled(false);
      cambaneComboBox.addItem("K1");
      cambaneComboBox.addItem("K2");
       cambaneComboBox.addItem("K3");
       cambaneComboBox.addItem("K4");
        cambaneComboBox.addItem("K5");
         cambaneComboBox.addItem("K6");
          cambaneComboBox.addItem("K7");
           cambaneComboBox.addItem("K8");
            cambaneComboBox.addItem("K9");
             cambaneComboBox.addItem("K10");
              cambaneComboBox.addItem("K11");
               cambaneComboBox.addItem("K12");
                cambaneComboBox.addItem("K13");
                 cambaneComboBox.addItem("K14");
                  cambaneComboBox.addItem("K15");
                   cambaneComboBox.addItem("K16");
                    cambaneComboBox.addItem("K17");
                     cambaneComboBox.addItem("K18");
           cambaneComboBox.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                   cambanetextfield.setText((String) cambaneComboBox.getSelectedItem());
                 cambanetextfield.setBackground(Color.WHITE);
                  cambanetextfield.setDisabledTextColor(Color.RED);
            }
        });      
           
           JLabel numberorderlabel=new JLabel("Ποσότητα Παραγγελιών");
           numberorder=new JTextField(10);
                     
      JLabel codelabel=new JLabel("Κωδικός");
       codetextfield=new JTextField(10);
      
      JLabel quantitylabel=new JLabel("Ποσότητα");
       quantitytextfield=new JTextField(10);
      
      JLabel pricelabel=new JLabel("Τιμή");
       pricetextfield=new JTextField(10);
      
      JLabel productlabel=new JLabel("Προϊόν");
       producttextfield=new JTextField(10);
      
      JLabel pagelabel=new JLabel("Σελίδα");
       pagetextfield=new JTextField(10);
       
    newcampane=new JCheckBox("Νέα Καμπάνια");
     colorme=new JCheckBox("Χρωμάτισε Με");
   
       
       centerpanel.add(numberorderlabel);
       centerpanel.add(numberorder);
       centerpanel.add(cambaneComboBox);
      centerpanel.add(cambanetextfield);
     centerpanel.add(codelabel);
     centerpanel.add(codetextfield);
      centerpanel.add(quantitylabel);
     centerpanel.add(quantitytextfield);
     centerpanel.add(pricelabel);
     centerpanel.add(pricetextfield);
      centerpanel.add(productlabel);
     centerpanel.add(producttextfield);
     centerpanel.add(pagelabel);
     centerpanel.add(pagetextfield);
     centerpanel.add(newcampane);
     centerpanel.add(colorme);
      add(centerpanel);
      
        JPanel buttonpJPanel=new JPanel(new FlowLayout());
         okbButton=new JButton("Ok");
         okbButton.setEnabled(false);
      JButton clearbButton=new JButton("Καθαρισμός");
      buttonpJPanel.add(okbButton);
      buttonpJPanel.add(clearbButton);
      add(buttonpJPanel);
      
      mainpanel.add(centerpanel);
      add(mainpanel,BorderLayout.CENTER);
     add(buttonpJPanel,BorderLayout.SOUTH);  
     
     clearbButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
              cambaneComboBox.setSelectedIndex(0);
              cambanetextfield.setText("");
              codetextfield.setText("");
              pricetextfield.setText("");
              quantitytextfield.setText("");
              producttextfield.setText("");
              pagetextfield.setText("");
              numberorder.setText(" ");
            }
        });    
  count=0;
     okbButton.addActionListener(new ActionListener() {
              @Override 
             public void actionPerformed(ActionEvent ae) {
                  try{
           count=Integer.parseInt(numberorder.getText());
                  }
                   catch(NumberFormatException e){
           JOptionPane.showMessageDialog(MainFrame.getTakewindow(),"Λάθος δεδομένα.Έχετε τοποθετήσει συμβολοσειρά.\n Βάλτε αριθμό στο πεδιο<<Ποσότητα παραγελλιών>>","Μύνημα Λάθους ",JOptionPane.ERROR_MESSAGE);
          error=true;
      }//*****************************
                  if(!error){
                     
                      if(newcampane.isSelected()){
                            MainFrame.getTableModel().addRow(new Object[]{"-","-" ,"-","-","-","-"});
                             save=new Savetofile("-","-","-","-","-","-"); 
                             newcampane.setSelected(false);
                      //  MainFrame.getTable().setGridColor(Color.yellow);
                                  }
                      
                      
                      
                  Order.this.setVisible(false);
                MainFrame.getTableModel().addRow(new Object[]{cambanetextfield.getText(), codetextfield.getText(),quantitytextfield.getText(),pricetextfield.getText(),producttextfield.getText(),pagetextfield.getText()});               
                save=new Savetofile(cambanetextfield.getText(),codetextfield.getText(),quantitytextfield.getText(),pricetextfield.getText(),producttextfield.getText(),pagetextfield.getText());
                cambaneComboBox.setSelectedIndex(0);
              cambanetextfield.setText("");
              codetextfield.setText("");
              pricetextfield.setText("");
              quantitytextfield.setText("");
              producttextfield.setText("");
              pagetextfield.setText(""); 
              error=false;
              if(count!=1){
                  count--;
                  numberorder.setText(Integer.toString(count));
                  Order.this.setVisible(true);
              }
              else if(count==1){
                  numberorder.setText("");
                  Order.this.setVisible(false);
              }
               }
               else{
                   numberorder.setText("");
                   error=false;
                  }  
                  
             }
              
         
     
          });
     okbButton.getRootPane().setDefaultButton(okbButton);
      
     try{
     numberorder.getDocument().addDocumentListener(new DocumentListener() {

              @Override
              public void insertUpdate(DocumentEvent de) {
                  if(numberorder.getText().isEmpty()){
                      numberorderbool=false;
                      change();
                  }
                  else{
                      numberorderbool=true;
                      change();
                  }
              }

              @Override
              public void removeUpdate(DocumentEvent de) {
                 if(numberorder.getText().isEmpty()){
                      numberorderbool=false;
                      change();
                  }
                  else{
                      numberorderbool=true;
                      change();
                  }
              }

              @Override
              public void changedUpdate(DocumentEvent de) {
                
              }
          });
     
     cambanetextfield.getDocument().addDocumentListener(new DocumentListener() {

              @Override
              public void insertUpdate(DocumentEvent de) {
                 if(cambanetextfield.getText().isEmpty()){
                     cambanebool=false;
                     change();
                 }
                 else{
                     cambanebool=true;
                     change();
                 }
              }

              @Override
              public void removeUpdate(DocumentEvent de) {
                  if(cambanetextfield.getText().isEmpty()){
                     cambanebool=false;
                     change();
                 }
                 else{
                     cambanebool=true;
                     change();
                 }
              }

              @Override
              public void changedUpdate(DocumentEvent de) {
                 
              }
          });
     
     
     codetextfield.getDocument().addDocumentListener(new DocumentListener() {

              @Override
              public void insertUpdate(DocumentEvent de) {
                  if(codetextfield.getText().isEmpty()){
                     codebool=false;
                     change();
                 }
                 else{
                     codebool=true;
                     change();
                 }
              }

              @Override
              public void removeUpdate(DocumentEvent de) {
                   if(codetextfield.getText().isEmpty()){
                     codebool=false;
                     change();
                 }
                 else{
                     codebool=true;
                     change();
                 }
              }

              @Override
              public void changedUpdate(DocumentEvent de) {
                 
              }
          });
     
     quantitytextfield.getDocument().addDocumentListener(new DocumentListener() {

              @Override
              public void insertUpdate(DocumentEvent de) {
                 if(quantitytextfield.getText().isEmpty()){
                     quantitybool=false;
                     change();
                 }
                 else{
                     quantitybool=true;
                     change();
                 }
              }

              @Override
              public void removeUpdate(DocumentEvent de) {
if(quantitytextfield.getText().isEmpty()){
                     quantitybool=false;
                     change();
                 }
                 else{
                     quantitybool=true;
                     change();
                 }              }

              @Override
              public void changedUpdate(DocumentEvent de) {
              
              }
          });
     
     pricetextfield.getDocument().addDocumentListener(new DocumentListener() {

              @Override
              public void insertUpdate(DocumentEvent de) {
                  if(pricetextfield.getText().isEmpty()){
                     pricebool=false;
                     change();
                 }
                 else{
                     pricebool=true;
                     change();
                 }
              }

              @Override
              public void removeUpdate(DocumentEvent de) {
                  if(pricetextfield.getText().isEmpty()){
                     pricebool=false;
                     change();
                 }
                 else{
                     pricebool=true;
                     change();
                 }
              }

              @Override
              public void changedUpdate(DocumentEvent de) {
            
              }
          });
     
     
     producttextfield.getDocument().addDocumentListener(new DocumentListener() {

              @Override
              public void insertUpdate(DocumentEvent de) {
                 if(producttextfield.getText().isEmpty()){
                     productbool=false;
                     change();
                 }
                 else{
                     productbool=true;
                     change();
                 }
              }

              @Override
              public void removeUpdate(DocumentEvent de) {
                  if(producttextfield.getText().isEmpty()){
                     productbool=false;
                     change();
                 }
                 else{
                     productbool=true;
                     change();
                 }
              }

              @Override
              public void changedUpdate(DocumentEvent de) {
                 
              }
          });
     
     
     pagetextfield.getDocument().addDocumentListener(new DocumentListener() {

              @Override
              public void insertUpdate(DocumentEvent de) {
                  if(pagetextfield.getText().isEmpty()){
                     pagebool=false;
                     change();
                 }
                 else{
                     pagebool=true;
                     change();
                 }
              }

              @Override
              public void removeUpdate(DocumentEvent de) {
                   if(pagetextfield.getText().isEmpty()){
                     pagebool=false;
                     change();
                 }
                 else{
                     pagebool=true;
                     change();
                 }
              }

              @Override
              public void changedUpdate(DocumentEvent de) {
               
              }
          });
     }
     catch(UnsupportedOperationException e){
          JOptionPane.showMessageDialog(MainFrame.getTakewindow(),e+"Update changed ","Μύνημα Λάθους ",JOptionPane.ERROR_MESSAGE);
     }
     
       }//end constr

      
      
     
      
      
      private void change(){
          if(cambanebool==true&&codebool==true&& quantitybool==true&&pricebool==true&&productbool==true&&pagebool==true&& numberorderbool==true){
           okbButton.setEnabled(true);   
          }
          else{
              okbButton.setEnabled(false);
          }
      }
      
    public JTextField getCambanetextfield() {
        return cambanetextfield;
    }

    public JTextField getCodetextfield() {
        return codetextfield;
    }

    public JTextField getQuantitytextfield() {
        return quantitytextfield;
    }

    public JTextField getPricetextfield() {
        return pricetextfield;
    }

    public JTextField getProducttextfield() {
        return producttextfield;
    }

    public JTextField getPagetextfield() {
        return pagetextfield;
    }
    

}
