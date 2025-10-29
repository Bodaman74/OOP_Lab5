package lab5;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePanel extends JPanel {
    private MainFrame mainFrame;
    
    public HomePanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setupSimplePanel();
    }

    private void setupSimplePanel() {
        setLayout(new BorderLayout());
        setBackground(new Color(240, 245, 255));
        
        // Welcome message
        JLabel welcomeLabel = new JLabel("<html><div style='text-align: center;'>"
                + "<h1>Welcome to Student Management System</h1>"
                + "<p style='color: #666; font-size: 16px; margin-top: 20px;'>"
                + "Please select an operation from the options below"
                + "</p></div></html>", JLabel.CENTER);
        
        welcomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        welcomeLabel.setForeground(new Color(0, 70, 130));
        welcomeLabel.setBorder(BorderFactory.createEmptyBorder(80, 20, 60, 20));
        add(welcomeLabel, BorderLayout.NORTH);
        
        // Simple navigation buttons
        JPanel buttonPanel = new JPanel(new GridLayout(1, 4, 20, 0));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 40, 100, 40));
        buttonPanel.setBackground(new Color(240, 245, 255));
        
        buttonPanel.add(createSimpleButton("Add Student", "➕", MainFrame.TabIndices.ADD, new Color(60, 179, 113)));
        buttonPanel.add(createSimpleButton("View Students", "📊", MainFrame.TabIndices.VIEW, new Color(70, 130, 180)));
        buttonPanel.add(createSimpleButton("Update Student", "✏️", MainFrame.TabIndices.UPDATE, new Color(255, 165, 0)));
        buttonPanel.add(createSimpleButton("Delete Student", "🗑️", MainFrame.TabIndices.DELETE, new Color(220, 80, 70)));
        
        add(buttonPanel, BorderLayout.CENTER);
    }
    
    private JButton createSimpleButton(String text, String icon, int tabIndex, Color color) {
        JButton button = new JButton("<html><center><font size='+1'><b>" + icon + "<br>" + text + "</b></font></center></html>");
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainFrame.switchToTab(tabIndex);
            }
        });
        
        // Hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(color.darker());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(color);
            }
        });
        
        return button;
    }
}