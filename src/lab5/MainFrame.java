package lab5;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainFrame extends JFrame {
    private JTabbedPane tabbedPane;
    private StudentDataManager studentManager;
    private ViewStudentsPanel viewPanel;
    private AddStudentPanel addPanel;
    private UpdateStudentPanel updatePanel;
    private DeleteStudentPanel deletePanel;

    public MainFrame() {
        studentManager = new StudentDataManager();
        initializeFrame();
        createTabs();
        setupWindowListener();
    }

    private void initializeFrame() {
        setTitle("Student Management System");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        
        
    }

    private void setupWindowListener() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int option = JOptionPane.showConfirmDialog(
                    MainFrame.this,
                    "Are you sure you want to exit?",
                    "Confirm Exit",
                    JOptionPane.YES_NO_OPTION
                );
                if (option == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
    }

    private void createTabs() {
        tabbedPane = new JTabbedPane();
        
        // Create panels - pass this MainFrame reference to HomePanel
        viewPanel = new ViewStudentsPanel(studentManager);
        addPanel = new AddStudentPanel(studentManager);
        updatePanel = new UpdateStudentPanel(studentManager);
        deletePanel = new DeleteStudentPanel(studentManager);
        
        // Add tabs - HomePanel now gets MainFrame reference for navigation
        tabbedPane.addTab("🏠 Home", new HomePanel(this));
        tabbedPane.addTab("➕ Add Student", addPanel);
        tabbedPane.addTab("📊 View Students", viewPanel);
        tabbedPane.addTab("✏️ Update Student", updatePanel);
        tabbedPane.addTab("🗑️ Delete Student", deletePanel);
        
        add(tabbedPane);
    }

    // Method to switch tabs programmatically - used by HomePanel buttons
    public void switchToTab(int tabIndex) {
        if (tabIndex >= 0 && tabIndex < tabbedPane.getTabCount()) {
            tabbedPane.setSelectedIndex(tabIndex);
        }
    }

    // Get tab indices for reference (optional)
    public static class TabIndices {
        public static final int HOME = 0;
        public static final int ADD = 1;
        public static final int VIEW = 2;
        public static final int UPDATE = 3;
        public static final int DELETE = 4;
    }
}