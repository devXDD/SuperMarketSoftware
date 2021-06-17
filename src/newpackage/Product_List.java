package newpackage;

import newpackage.Product;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

abstract class Product_List implements ProductI {

    ArrayList<Product> products;
    private final String filename = "Product_List.txt";

    public Product_List() {
        this.products = new ArrayList<>();
    }

    public void editProduct(Product oldSrc, Product newSrc) {
        products.set(products.indexOf(oldSrc), newSrc);
    }

    public void addProduct(Product src) {
        products.add(src);
    }

    public void deleteProduct(Product src) {
        products.remove(src);
    }
    
    @Override
    public void ReadFromFile() {
      
    }
    
    private Product createProduct(String[] metadata) { 
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
        String ProductID = metadata[0];
        if(find(ProductID) != null) {
            return null;
        }
        String ProductName = metadata[1];
        String Weight = metadata[2];
        double Price;
        try {
            Price = Double.parseDouble(metadata[3]);
        } catch (Exception e) {
            return null;
        }
        Date ExpiryDate;
        try {
            ExpiryDate = sdf.parse(metadata[4]);
        } catch (Exception e) {
            return null;
        }
        Date EstimatedOrderDate;
        try {
            EstimatedOrderDate = sdf.parse(metadata[5]);
        } catch (Exception e) {
            return null;
        }
        ArrayList<String> Images = new ArrayList<>();
        try {
            String[] imgs = metadata[6].split("#");
            for (String img : imgs) {
                Images.add(img);
            }
        } catch (Exception e) {
            return null;
        }
        String Category = metadata[7];
        int StockQuantity = Integer.parseInt(metadata[8]);
        int MinimumQuantity = Integer.parseInt(metadata[9]);
        int MaximumQuantity = Integer.parseInt(metadata[10]);
        Date LastOrderDate;
        try {
            LastOrderDate = sdf.parse(metadata[11]);
        } catch (Exception e) {
            return null;
        }
        String ManufacturedBy = metadata[12];
        Date ManufacturedDate;
        try {
            ManufacturedDate = sdf.parse(metadata[13]);
        } catch (Exception e) {
            return null;
        }
        // create and return product of this metadata 
        Product p = new Product(ProductID, ProductName, Weight, Price, ExpiryDate, EstimatedOrderDate, Images, Category, StockQuantity, MinimumQuantity, MaximumQuantity, ManufacturedBy, ManufacturedDate);
        p.setOrderDate(LastOrderDate);
        p.CalculateVatPrice();
        return p;
    }
    

    @Override
    public void SaveToFile() {
        FileWriter awriter;
        try {
            awriter = new FileWriter(filename);
            for (Product product : products) {
                awriter.write(product.toString());
                awriter.write("X-x-x-x-x-x-x-x-x-x--xx-x-x-x-x-x-X" + System.getProperty("line.separator"));
            }
            awriter.flush();
            awriter.close();
        } catch (IOException ioe) {
        }
    }

       public void LoadFromFile() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String record;
        try {
            try (BufferedReader bin = new BufferedReader(new FileReader(filename))) {
                Product p = new Product();
                while ((record = bin.readLine()) != null) {
                    String[] arr = record.split("=");
                    switch (arr[0]) {
                        case "Product ID":
                            p.setProductID(arr[1]);
                            break;
                        case "Product Name":
                            p.setProductName(arr[1]);
                            break;
                        case "Weight":
                            p.setWeight(arr[1]);
                            break;
                        case "Price":
                            p.setPrice(Double.parseDouble(arr[1]));
                            break;
                        case "VAT Price":
                            p.setVatPrice(Double.parseDouble(arr[1]));
                            break;
                        case "Expiry Date":
                            p.setExpiryDate(sdf.parse(arr[1]));
                            break;
                        case "Estimated Order Date":
                            p.setEstimatedOrderDate(sdf.parse(arr[1]));
                            break;
                        case "Images":
                            ArrayList<String> Images = new ArrayList<>();
                            if (arr.length > 1) {
                                String[] imgs = arr[1].split("#");
                                for (String img : imgs) {
                                    Images.add(img);
                                }
                            }
                            p.setImages(Images);
                            break;
                        case "Category":
                            p.setCategory(arr[1]);
                            break;
                        case "Stock Quantity":
                            p.setStockQuantity(Integer.parseInt(arr[1]));
                            break;
                        case "Minimum Quantity":
                            p.setMinimumQuantity(Integer.parseInt(arr[1]));
                            break;
                        case "Maximum Quantity":
                            p.setMaximumQuantity(Integer.parseInt(arr[1]));
                            break;
                        case "Last Order Date":
                            p.setOrderDate(sdf.parse(arr[1]));
                            break;
                        case "Manufactured By":
                            p.setManufacturedBy(arr[1]);
                            break;
                        case "Manufactured Date":
                            p.setManufacturedDate(sdf.parse(arr[1]));
                            products.add(p);
                            break;
                        default:
                            p = new Product();
                            break;
                    }
                }
            }
        } catch (IOException | ParseException ioe) {
        }
    
    }

    public Product find(String ProductID) {
        for (Product product : products) {
            if (product.getPID().equals(ProductID)) {
                return product;
            }
        }
        return null;
    }
    
    public void LoadToTable(JTable tbl) {
        DefaultTableModel dtm = (DefaultTableModel) tbl.getModel();
        dtm.setRowCount(0);
        for (Product product : products) {
            String ProductID = product.getPID();
            String ProductName = product.getProductName();
            String category = product.getCategory();
            double Price = product.getPrice();
            double VatPrice = product.getVatPrice();
            int StockQuantity = product.getStockQuantity();
            int MinimumQuantity = product.getMinimumQuantity();
            int MaximumQuantity = product.getMaximumQuantity();
            String Status = "Enough Stock";
            if(StockQuantity <= MinimumQuantity) {
                Status = "Re-Order";
            }
            dtm.addRow(new Object[]{ProductID,ProductName,Price,VatPrice,StockQuantity,MinimumQuantity,MaximumQuantity,Status, category});
        }
        tbl.setModel(dtm);
    }
     public void filter(JTable tbl, String query) {
         
        DefaultTableModel dtm = (DefaultTableModel) tbl.getModel();
       TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(dtm);
       tbl.setRowSorter(tr);
       
       if(query != "None")
       {
           tr.setRowFilter(RowFilter.regexFilter(query));
       }
       else
       {
            tbl.setRowSorter(tr);
       }
    }
     
     public void tableToFile(JTable tab) throws IOException
     {
        
         File file = new File("TableData.txt");
               FileWriter fw = new FileWriter(file.getAbsoluteFile());
               BufferedWriter bw = new BufferedWriter(fw);
               String[] value = new String[50];
               int [] row = new int [50];
               int column = 0;
for(int i=0; i<9 ; i++)
{
    row[i] = tab.getSelectedRow();
    value[i] = tab.getModel().getValueAt(row[i], i).toString();
     bw.write(value[i]+" ");
  bw.write("\n_________\n");
               }
                bw.close();
               fw.close();
}
     
     public void CopyJTable(JTable t1, JTable t2) throws IOException
     {
        TableModel model1 = t1.getModel();
        int indexs[] = t1.getSelectedRows();
        
        Object[] row = new Object[50];

        DefaultTableModel model2 = (DefaultTableModel)t2.getModel();
        
        for(int i = 0; i < indexs.length; i++)
        {
            row[0] = model1.getValueAt(indexs[i], 0);
            row[1] = model1.getValueAt(indexs[i], 1);
            row[2] = model1.getValueAt(indexs[i], 2);
            row[3] = model1.getValueAt(indexs[i], 3);
            row[4] = model1.getValueAt(indexs[i], 4);
            row[5] = model1.getValueAt(indexs[i], 5);
            row[6] = model1.getValueAt(indexs[i], 6);
            row[7] = model1.getValueAt(indexs[i], 7);
            row[8] = model1.getValueAt(indexs[i], 8);
            
            model2.addRow(row);
        }
        
     
    }                                           

    @Override
    public void Edit() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Display(JTextArea src) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void CalculateVatPrice() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double CalculateTotalAmount() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
         
         
         
     }
               


             
/*
    for(int i = 0; i < tab.getSelectedRow(); i++){
                   //loop for jtable column
                   for(int x = 0; x < tab.getColumnCount(); x++){
                       bw.write(tab.getModel().getValueAt(i, x)+" ");
                   }
                   bw.write("\n_________\n");
               }
               bw.close();
               fw.close();
               }
}
         */
     

   
     

     
    


