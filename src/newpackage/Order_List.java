package newpackage;


import newpackage.Order;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.JTextArea;

public class Order_List implements ProductI{
    
    ArrayList<Order> orders;
    private final String filename = "Order_List.txt";
    
    public Order_List() {
        this.orders = new ArrayList<>();
    }
    
    public void newOrder(Order src) {
        orders.add(src);
    }
    
    public void Display(JTextArea jClientsTextArea) {
        jClientsTextArea.setText("");
        for (int i = 0; i < orders.size(); i++) {
            orders.get(i).Display(jClientsTextArea);
        }
    }
    
    public void SaveToFile() {
        FileWriter awriter;
        try {
            awriter = new FileWriter(filename);
            for (Order order : orders) {
                awriter.write(order.toString());
                awriter.write("X-X-X-X-X-X-X-X-X-X-X-X-X-X" + System.getProperty("line.separator"));
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
                Order o = new Order();
                while ((record = bin.readLine()) != null) {
                    String[] arr = record.split("=");
                    switch (arr[0]) {
                        case "Order ID":
                            o.setOrderID(arr[1]);
                            break;
                        case "Product ID":
                            o.setProductID(arr[1]);
                            break;
                        case "Total Amount":
                            o.setTotalAmount(Double.parseDouble(arr[1]));
                            break;
                        case "Suplier Name":
                            o.setSuplierName(arr[1]);
                            break;
                        case "Suplier Address":
                            o.setSuplierAddress(arr[1]);
                            break;
                        case "Delivery Date":
                            o.setDeliveryDate(sdf.parse(arr[1]));
                            orders.add(o);
                            break;
                        default:
                            o = new Order();
                            break;
                    }
                }
            }
        } catch (IOException | ParseException ioe) {
        }
    }
    
    public Order find(String OrderID) {
        for (Order order : orders) {
            if(order.getOrderID().equals(OrderID)) {
                return order;
            }
        }
        return null;
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
    
}
