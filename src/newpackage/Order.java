package newpackage;



import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JTable;
import javax.swing.JTextArea;

public class Order implements ProductI {
    
    String OrderID;
    String ProductID;
    Date DeliveryDate;
    double TotalAmount;
    String SuplierName;
    String SuplierAddress;

    public Order(String OrderID, String ProductID, Date DeliveryDate, double TotalAmount, String SuplierName, String SuplierAddress) {
        this.OrderID = OrderID;
        this.ProductID = ProductID;
        this.DeliveryDate = DeliveryDate;
        this.TotalAmount = TotalAmount;
        this.SuplierName = SuplierName;
        this.SuplierAddress = SuplierAddress;
    }

    public Order() {
        this.OrderID = "";
        this.ProductID = "";
        this.DeliveryDate = new Date();
        this.TotalAmount = 0.0;
        this.SuplierName = "";
        this.SuplierAddress = "";
    }

    public void Display(JTextArea src) {
        src.append(toString());
        src.append("#######################" + System.lineSeparator());
    }
    
    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Product_List pl = new Product_List() {};
        pl.LoadFromFile();
        Product p = pl.find(ProductID);
        StringBuilder builder = new StringBuilder();
        builder.append("Order ID=").append(OrderID).append(System.lineSeparator());
        builder.append("PID=").append(ProductID).append(System.lineSeparator());
        builder.append("Total Amount=").append(TotalAmount).append(System.lineSeparator());
        builder.append("Suplier Name=").append(SuplierName).append(System.lineSeparator());
        builder.append("Suplier Address=").append(SuplierAddress).append(System.lineSeparator());
        builder.append("Delivery Date=").append(sdf.format(DeliveryDate)).append(System.lineSeparator());
        return builder.toString();
    }
    
    public String getOrderID() {
        return OrderID;
    }

    public void setOrderID(String OrderID) {
        this.OrderID = OrderID;
    }

    public String getProductID() {
        return ProductID;
    }

    public void setProductID(String ProductID) {
        this.ProductID = ProductID;
    }

    public Date getDeliveryDate() {
        return DeliveryDate;
    }

    public void setDeliveryDate(Date DeliveryDate) {
        this.DeliveryDate = DeliveryDate;
    }

    public double getTotalAmount() {
        return TotalAmount;
    }

    public void setTotalAmount(double TotalAmount) {
        this.TotalAmount = TotalAmount;
    }

    public String getSuplierName() {
        return SuplierName;
    }

    public void setSuplierName(String SuplierName) {
        this.SuplierName = SuplierName;
    }

    public String getSuplierAddress() {
        return SuplierAddress;
    }

    public void setSuplierAddress(String SuplierAddress) {
        this.SuplierAddress = SuplierAddress;
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
    public void CalculateVatPrice() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double CalculateTotalAmount() {
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
