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
