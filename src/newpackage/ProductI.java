/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newpackage;

/**
 *
 * @author Verbal
 */
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JTable;
import javax.swing.JTextArea;

public interface ProductI {
    void Edit();
    void Display(JTextArea src);
    void LoadToTable(JTable j1);
    void CalculateVatPrice();
    double CalculateTotalAmount();
    void ReadFromFile();
    void SaveToFile();
    
}