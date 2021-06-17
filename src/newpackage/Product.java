package newpackage;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextArea;

public class Product implements ProductI{
    
  protected  String ProductID;
    protected  Date Manufacture;
    protected  String Weight;
    protected  double Price;
    protected  Date OrderDate; Date ExpDate; Date ETAOrderDate;
    protected  ArrayList<String> Images;
    protected  String Category;
    protected  String ProductName;
    protected  int StockQuantity;
    protected  int MaximumQuantity;
     protected String ManufacturedBy;
    protected  double VatPrice;
     protected int MinimumQuantity;
   

    public Product(String ProductID, String ProductName, String Weight, double Price, Date ExpiryDate, 
            Date EstimatedOrderDate, ArrayList<String> Images, String Category, int StockQuantity,
            int MinimumQuantity, int MaximumQuantity, String ManufacturedBy, Date Manufact) {
        this.ProductID = ProductID;
        this.ProductName = ProductName;
        this.Weight = Weight;
        this.Price = Price;
        this.ExpDate = ExpiryDate;
        this.ETAOrderDate = EstimatedOrderDate;
       this.Images = Images;
        this.Category = Category;
        this.StockQuantity = StockQuantity;
        this.MinimumQuantity = MinimumQuantity;
        this.MaximumQuantity = MaximumQuantity;
        this.OrderDate = new Date();
        this.ManufacturedBy = ManufacturedBy;
        this.Manufacture = Manufact;
    }
    
    public Product() {
        this.ProductID = "000";
        this.ProductName = "";
        this.Weight = "";
        this.Price = 0.0;
        this.VatPrice = 0.0;
        this.ExpDate = new Date();
        this.ETAOrderDate = new Date();
       this.Images = new ArrayList<>();
        this.Category = "";
        this.StockQuantity = 0;
        this.MinimumQuantity = 0;
        this.MaximumQuantity = 0;
        this.OrderDate = new Date();
        this.ManufacturedBy = "";
        this.Manufacture = new Date();
    }
    
    public void Edit(String ProductName, String Weight, double Price, Date ExpiryDate, 
            Date EstimatedOrderDate, ArrayList<String> Images, String Category, int StockQuantity,
            int MinimumQuantity, Date LastOrderDate, String ManufacturedBy, Date ManufacturedDate) {
        this.ProductName = ProductName;
        this.Weight = Weight;
        this.Price = Price;
        this.ExpDate = ExpiryDate;
        this.ETAOrderDate = EstimatedOrderDate;
        this.Images = Images;
        this.Category = Category;
        this.StockQuantity = StockQuantity;
        this.MinimumQuantity = MinimumQuantity;
        this.OrderDate = LastOrderDate;
        this.ManufacturedBy = ManufacturedBy;
        this.Manufacture = ManufacturedDate;
    }
    
    public void Display(JTextArea src) {
        src.append(toString());
        src.append("X-O-X-O-X-O-X-O-X-O-X-O" + System.lineSeparator());
    }
    
    public void CalculateVatPrice() {
        double vatPrsnt = 1.15;
        double roundVal = vatPrsnt * Price;
        VatPrice = Price + roundVal;
    }
    
    public double CalculateTotalAmount() {
        double TotalAmount = 0.0;
        int OrderQuantity = getOrderQuantity();
        if(StockQuantity <= MinimumQuantity) {
            TotalAmount = OrderQuantity * Price;
        } 
        return TotalAmount;
    }
    
    public int getOrderQuantity() {
        return MaximumQuantity - StockQuantity;
    }
    
    public void setProductImage(File file, JLabel label) {
      
    }
    
    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        StringBuilder builder = new StringBuilder();
        builder.append("PID=").append(ProductID).append(System.lineSeparator());
        builder.append("PName=").append(ProductName).append(System.lineSeparator());
        builder.append("Weight=").append(Weight).append(System.lineSeparator());
        builder.append("Price=").append(Price).append(System.lineSeparator());
        builder.append("VAT=").append(VatPrice).append(System.lineSeparator());
        builder.append("Exp Date=").append(sdf.format(ExpDate)).append(System.lineSeparator());
        builder.append("Estimated Order Date=").append(sdf.format(ETAOrderDate)).append(System.lineSeparator());
        builder.append("Images=");
        String imgs = "";
        for (String Image : Images) {
            imgs += Image + "#";
        }
        if(!Images.isEmpty())
            builder.append(imgs.replaceFirst(".$",""));
        builder.append(System.lineSeparator());
        builder.append("Category=").append(Category).append(System.lineSeparator());
        builder.append("Stock Quantity=").append(StockQuantity).append(System.lineSeparator());
        builder.append("Minimum Quantity=").append(MinimumQuantity).append(System.lineSeparator());
        builder.append("Maximum Quantity=").append(MaximumQuantity).append(System.lineSeparator());
        builder.append("Last Order Date=").append(sdf.format(OrderDate)).append(System.lineSeparator());
        builder.append("Manufactured By=").append(ManufacturedBy).append(System.lineSeparator());
        builder.append("Manufactured Date=").append(sdf.format(Manufacture)).append(System.lineSeparator());
        return builder.toString();
    }

    // GETTERS AND SETTERS METHODS DEFINED HERE: 
    
    public String getPID() {
        return ProductID;
    }

    public void setProductID(String ProductID) {
        this.ProductID = ProductID;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String ProductName) {
        this.ProductName = ProductName;
    }

    public String getWeight() {
        return Weight;
    }

    public void setWeight(String Weight) {
        this.Weight = Weight;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double Price) {
        this.Price = Price;
    }

    public double getVatPrice() {
        return VatPrice;
    }

    public void setVatPrice(double VatPrice) {
        this.VatPrice = VatPrice;
    }

    public Date getExpiryDate() {
        return ExpDate;
    }

    public void setExpiryDate(Date ExpiryDate) {
        this.ExpDate = ExpiryDate;
    }

    public Date getEstimatedOrderDate() {
        return ETAOrderDate;
    }

    public void setEstimatedOrderDate(Date EstimatedOrderDate) {
        this.ETAOrderDate = EstimatedOrderDate;
    }

    public ArrayList<String> getImages() {
        return Images;
    }

    public void setImages(ArrayList<String> Images) {
        this.Images = Images;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String Category) {
        this.Category = Category;
    }

    public int getStockQuantity() {
        return StockQuantity;
    }

    public void setStockQuantity(int StockQuantity) {
        this.StockQuantity = StockQuantity;
    }

    public int getMinimumQuantity() {
        return MinimumQuantity;
    }

    public void setMinimumQuantity(int MinimumQuantity) {
        this.MinimumQuantity = MinimumQuantity;
    }

    public int getMaximumQuantity() {
        return MaximumQuantity;
    }

    public void setMaximumQuantity(int MaximumQuantity) {
        this.MaximumQuantity = MaximumQuantity;
    }

    public Date getOrderDate() {
        return OrderDate;
    }

    public void setOrderDate(Date LastOrderDate) {
        this.OrderDate = LastOrderDate;
    }

    public String getManufacturedBy() {
        return ManufacturedBy;
    }

    public void setManufacturedBy(String ManufacturedBy) {
        this.ManufacturedBy = ManufacturedBy;
    }

    public Date getManufacturedDate() {
        return Manufacture;
    }

    public void setManufacturedDate(Date ManufacturedDate) {
        this.Manufacture = ManufacturedDate;
    }

    @Override
    public void Edit() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void LoadToTable(JTable j1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void ReadFromFile() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void SaveToFile() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
