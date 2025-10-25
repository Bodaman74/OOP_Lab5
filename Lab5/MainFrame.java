/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab5;

/**
 *
 * @author PCV
 */
import javax.swing.*;

public class MainFrame extends JFrame {
    private JTabbedPane tabbedPane;
    
    public MainFrame() {
        setTitle("Student Management System");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        createTabs();
    }
    
    private void createTabs() {
        tabbedPane = new JTabbedPane();
        
        
        ViewStudentsPanel viewPanel = new ViewStudentsPanel();
        tabbedPane.addTab("View & Search Students", viewPanel);
        
      
        
        add(tabbedPane);
    }
    
    
}