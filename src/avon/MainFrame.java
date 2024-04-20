/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package avon;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author KOURSOS-PC
 */
 
public class MainFrame extends JFrame{
  
   private static JTable table;
   private static DefaultTableModel tableModel = new DefaultTableModel();
    private JSplitPane splitpane;
    public static DefaultListModel<String> leftListModel;
    private static JList<String> leftList;
    private NewMember addmember=new NewMember(this);
    public static  Order order;
     private JMenuItem orderitem;
     private JMenuItem clearsum;
    private Readfromfile readfromfile;
    private static MainFrame takewindow;
    JLabel statusLabel = new JLabel("  0.0");
    JLabel sumlabel=new JLabel(" Σύνολο ");
    private  Namefile namefile=new Namefile();
    private double sum=0.0;
    private RandomAccessFile file;
    private String name;
    private String surname;
    
            public MainFrame() {
        setSize(900, 500);
       setDefaultCloseOperation(EXIT_ON_CLOSE);
       setLayout(new BorderLayout());
       setTitle("Avon");
  
      order=new Order(this);
        leftListModel=new DefaultListModel<String>();
        leftList=new JList<String>(leftListModel);
        leftList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
      
        
         JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Επιλογές");
        JMenuItem menuItem1 = new JMenuItem("Προσθήκη μέλους");
         orderitem=new JMenuItem("Παραγγελία");
        orderitem.setEnabled(false);
        JMenuItem exit=new JMenuItem("Έξοδος");
        clearsum=new JMenuItem("Μηδενισμός Συνόλου");
      
        menu.add(menuItem1);
        menu.add(orderitem);
        menu.add(clearsum);
        menu.addSeparator();
        menu.add(exit);
        setJMenuBar(menuBar);
        menuBar.add(menu);
        
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
               System.exit(0);
            }
        });
        menuItem1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                
                if (tableModel.getRowCount() > 0) {
    for (int i = tableModel.getRowCount() - 1; i > -1; i--) {
        tableModel.removeRow(i);
    }
}
               if(ae.getActionCommand().equals("Προσθήκη μέλους")){
                    addmember.getNametField().setText("");
                    addmember.getSurnameTextField().setText("");
                    addmember.getPhonelFieldempty().setText("");
                   addmember.setVisible(true);
               }
            }
        });
      
        
        clearsum.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                    if(ae.getActionCommand().equals("Μηδενισμός Συνόλου")){
                     statusLabel.setText("  0.0");
                     sum=0.0;
                      }
                    }
                });
        
        try{
      menu.addMenuListener(new MenuListener() {

                    @Override
                    public void menuSelected(MenuEvent me) {
                     if(statusLabel.getText().equals("  0.0")){
                         clearsum.setEnabled(false);
                         
                     }
                     else{
                         clearsum.setEnabled(true);
                     }
                    }
                      
                    @Override
                    public void menuDeselected(MenuEvent me) {                             
      }
                      

                    @Override
                    public void menuCanceled(MenuEvent me) {
                    }
                }  );
        }
        catch(UnsupportedOperationException e){
        
        }     
     leftList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent lse) { 
                sum=0;
                 statusLabel.setText("  "+Double.toString(sum));
                if(lse.getValueIsAdjusting()==false){
                    if(leftList.getSelectedIndex()!=-1){
                        orderitem.setEnabled(true);
                    orderitem.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent ae) {
                                order.setVisible(true);
                            }
                        });
                    } 
                }
            }
        });
     
  leftList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent lse) {
                try{
                readfromfile=new Readfromfile(); 
                           readfromfile.read();
               }
                  catch(NullPointerException e){
        
     }
  
            }
          });
     
   
  
       JPanel mainJPanel=new JPanel(new BorderLayout());
       table=new JTable(tableModel);
       tableModel.setColumnIdentifiers(new Object[]{"Καμπάνια","Κωδικό","Ποσότητα","Ποσό-EURO","Προιόν","Σελίδα"});
       
       JScrollPane rightScrollPane = new JScrollPane(table);
       rightScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    
       JScrollPane leftsJScrollPane=new JScrollPane(leftList,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
       leftsJScrollPane.setPreferredSize(new Dimension(150, 100));
       leftsJScrollPane.setMinimumSize(new Dimension(110, 150));
       JPanel rightmainpJPanel=new JPanel(new BorderLayout());
  
       splitpane=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,leftsJScrollPane,rightmainpJPanel);
       splitpane.setOneTouchExpandable(true);
      splitpane.setContinuousLayout(true); 
   
      mainJPanel.add(splitpane,BorderLayout.WEST);
      mainJPanel.add(rightScrollPane,BorderLayout.CENTER);
      rightmainpJPanel.add(sumlabel,BorderLayout.NORTH);
      rightmainpJPanel.add(statusLabel,BorderLayout.CENTER);
      
       table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                    @Override
                    public void valueChanged(ListSelectionEvent lse) {
                        if(lse.getValueIsAdjusting()){
                            try{
                              
                            sum+=Double.parseDouble(table.getValueAt(table.getSelectedRow(),3).toString())*Integer.parseInt(table.getValueAt(table.getSelectedRow(), 2).toString());
                            statusLabel.setText("  "+Double.toString(sum)); 
                                //System.out.println("sum is "+sum);
                            }
                            catch (NumberFormatException e){
                                  JOptionPane.showMessageDialog(MainFrame.getTakewindow(),"Λάθος δεδομένα.Έχετε τοποθετήσει συμβολοσειρά. ή ',' αντί για '.'\n Βάλτε αριθμό στο πεδιο<<Ποσό-Ευρώ ή στην Ποσότητα >>","Μύνημα Λάθους ",JOptionPane.ERROR_MESSAGE);
                                   //System.err.println("error");                   
                            }
                            catch(ArrayIndexOutOfBoundsException ar){
                                System.err.println("out of bounds");
                            }
       }
                    }
                });
      
    
statusLabel.setForeground(Color.RED);
sumlabel.setForeground(Color.RED);
add(mainJPanel,BorderLayout.CENTER);




table.addMouseListener(new MouseAdapter() {@Override
 public void mouseClicked(MouseEvent e){
    if(SwingUtilities.isRightMouseButton(e)) {
        Point p = e.getPoint();
// get the row index that contains that coordinate
int rowNumber = table.rowAtPoint( p );
       tableModel.removeRow(rowNumber);
         String[] split = leftList.getSelectedValue().split(" ");
         name=split[0];
         surname=split[1];
         try {
             file=new RandomAccessFile(name+" "+surname+".txt","rw");
         } catch (FileNotFoundException ex) {
              JOptionPane.showMessageDialog(takewindow,"Αδυναμία ενημέρωσης του Αρχείου."+name+" "+surname+"\n Προσπαθήστε ξανά","Μύνημα Λάθους ",JOptionPane.ERROR_MESSAGE);
         }
         try {
             file.setLength(0);
         } catch (IOException ex) {
              JOptionPane.showMessageDialog(takewindow,"Αδυναμία ενημέρωσης του Αρχείου."+name+" "+surname+"\n Προσπαθήστε ξανά","Μύνημα Λάθους ",JOptionPane.ERROR_MESSAGE);
         }
        
          try{
            FileOutputStream fos = new FileOutputStream(name+" "+surname+".txt", true);
            PrintWriter pw=new PrintWriter(fos);
            
                 for(int i=0;i<table.getRowCount();i++){
                     for(int j=0;j<6;j++){
                         if(j<5){
                         pw.print(table.getValueAt(i, j)+" ");
                     }
                     
                     else{
                       pw.println(table.getValueAt(i, j)+" ");
                        }
                 }
                 }
            
                     pw.close();                     
        }
        catch (FileNotFoundException ea){
            JOptionPane.showMessageDialog(takewindow,"Αδυναμία ενημέρωσης του Αρχείου."+name+" "+surname+"\n Προσπαθήστε ξανά","Μύνημα Λάθους ",JOptionPane.ERROR_MESSAGE);
        }
            
    }
    
    }});
tableModel.addTableModelListener(new TableModelListener() {

                    @Override
                    public void tableChanged(TableModelEvent tme) {
                       if(tme.getType()==TableModelEvent.UPDATE){
                        table.clearSelection();
                   
                            String[] split = leftList.getSelectedValue().split(" ");
         name=split[0];
         surname=split[1];
         try {
             file=new RandomAccessFile(name+" "+surname+".txt","rw");
         } catch (FileNotFoundException ex) {
              JOptionPane.showMessageDialog(takewindow,"Αδυναμία ενημέρωσης του Αρχείου."+name+" "+surname+"\n Προσπαθήστε ξανά","Μύνημα Λάθους ",JOptionPane.ERROR_MESSAGE);
         }
         try {
             file.setLength(0);
         } catch (IOException ex) {
              JOptionPane.showMessageDialog(takewindow,"Αδυναμία ενημέρωσης του Αρχείου."+name+" "+surname+"\n Προσπαθήστε ξανά","Μύνημα Λάθους ",JOptionPane.ERROR_MESSAGE);
         }
        
          try{
            FileOutputStream fos = new FileOutputStream(name+" "+surname+".txt", true);
            PrintWriter pw=new PrintWriter(fos);
            
                 for(int i=0;i<table.getRowCount();i++){
                     for(int j=0;j<6;j++){
                         if(j<5){
                         pw.print(table.getValueAt(i, j)+" ");
                     }
                     
                     else{
                       pw.println(table.getValueAt(i, j)+" ");
                        }
                 }
                 }           
                     pw.close();                     
        }
        catch (FileNotFoundException ea){
            JOptionPane.showMessageDialog(takewindow,"Αδυναμία ενημέρωσης του Αρχείου."+name+" "+surname+"\n Προσπαθήστε ξανά","Μύνημα Λάθους ",JOptionPane.ERROR_MESSAGE);
        }
                       }
                    }
                });

      namefile.read();
 
    }//end const

    public static JList<String> getLeftList() {
        return leftList;
    }
   
      public static DefaultTableModel getTableModel() {
        return tableModel;
    }

    public static JTable getTable() {
        return table;
    }

    public static Order getOrder() {
        return order;
    }

    public static MainFrame getTakewindow() {
        return takewindow;
    }

  
}
