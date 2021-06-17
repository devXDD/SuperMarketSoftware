package newpackage;


import java.awt.TextArea;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


public class Basket extends Product {
    
    protected int quantity;
    File filename = new File("Basket.txt");
    
     ArrayList<Basket> bas;
     
     Basket()
     {
          this.quantity = 0;
          this.bas = new ArrayList<>();
     }
     public Basket(String ProductID, String ProductName, String Weight, double Price, int quantity)
     {
           
        this.ProductID = ProductID;
        this.ProductName = ProductName;
        this.Weight = Weight;
        this.Price = Price;
       this.quantity = quantity;
    }
      public void Edit(String ProductName, String Weight, double Price, int quantity) {
        this.ProductName = ProductName;
        this.Weight = Weight;
        this.Price = Price;
        this.quantity = quantity;
        
    }
    
    
     
     
     public void Edit(String ProductID, String ProductName, String Weight, double Price, int quantity)
     {
          this.ProductID = ProductID;
        this.ProductName = ProductName;
        this.Weight = Weight;
        this.Price = Price;
       this.quantity = quantity;
     }
     
     public void Display(JTextArea src) {
         
        src.append(toString());
        src.append("X-O-X-O-X-O-X-O-X-O-X-O" + System.lineSeparator());
    }
     public String toString()
     {
          StringBuilder builder = new StringBuilder();
        builder.append("Product ID=").append(ProductID).append(System.lineSeparator());
        builder.append("Product Name=").append(ProductName).append(System.lineSeparator());
        builder.append("Weight=").append(Weight).append(System.lineSeparator());
        builder.append("Price=").append(Price).append(System.lineSeparator());
        return builder.toString();
     }
       public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity ;
    }
     public Basket find(String ProductID) {
        for (Basket product : bas) {
            if (product.getPID().equals(ProductID)) {
                return product;
            }
        }
        return null;
    }
     public void editProduct(Basket oldSrc, Basket newSrc) {
        bas.set(bas.indexOf(oldSrc), newSrc);
    }
    
   
             public void SaveToFile(JTable jt) {
      try{
                //the file path
               File file = new File("Basket.txt");
               //if the file not exist create one
               if(!file.exists()){
                   file.createNewFile();
               }
               
               FileWriter fw = new FileWriter(file.getAbsoluteFile());
               BufferedWriter bw = new BufferedWriter(fw);
               
               //loop for jtable rows
               for(int i = 0; i < jt.getRowCount(); i++){
                   //loop for jtable column
                   for(int j = 0; j < jt.getColumnCount(); j++){
                       bw.write(jt.getModel().getValueAt(i, j)+" ");
                   }
                   //break line at the begin 
                   //break line at the end 
                   bw.write("\n_________\n");
               }
               //close BufferedWriter
               bw.close();
               //close FileWriter 
               fw.close();
               
               
               }catch(Exception ex){
                   ex.printStackTrace();
               }
           
    }
            public void removeFromTable(JTable j1)
            {
                DefaultTableModel model = new    DefaultTableModel();
              
               System.out.println("Table gitting removed");
               model.removeRow(j1.getSelectedRow());
               
                
            }
     public void getSum(JTable s, JTextField t)
    {
        double sum = 0;
        for(int i = 0; i < s.getRowCount(); i++)
        {
            sum = sum + Double.parseDouble(s.getValueAt(i, 2).toString());
        }
        
        t.setText(Double.toString(sum));
    }
   public void Display_TA(JTextArea src) throws IOException {
      try {
          String filestr="";
           System.out.println("LoadFrom FIle entered ");
          FileReader areader = new FileReader ("Basket.txt");
         Scanner sc = new Scanner(areader);
         while(sc.hasNext()== true)
         {
            filestr = filestr + sc.nextLine() + "\n";
         }
         src.setText(filestr);
         sc.close();
         areader.close();
         
      }
      catch (Exception e)
      {
          System.out.println("Error while loading Branchlist file");
      }
     
    }
    
      public void LoadToTable(JTable tbl) {
      
            
}
 
}

