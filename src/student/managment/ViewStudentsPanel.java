/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package student.managment;

/**
 *
 * @author PCV
 */
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ViewStudentsPanel extends JPanel {
    private StudentDataManager dataManager;
    private JTable studentTable;
    private DefaultTableModel tableModel;
    private JTextField searchField;
    private JLabel statusLabel;
    
    public ViewStudentsPanel() {
        dataManager = new StudentDataManager();
        setupPanel();
        showAllStudents();
    }
    
    private void setupPanel() {
        setLayout(new BorderLayout());
        
        // Title
        JLabel titleLabel = new JLabel("View and Search Students", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(titleLabel, BorderLayout.NORTH);
        
        // Center panel with search and table
        JPanel centerPanel = new JPanel(new BorderLayout());
        
        // Search panel
        JPanel searchPanel = new JPanel(new FlowLayout());
        searchPanel.add(new JLabel("Search by Name or ID:"));
        
        searchField = new JTextField(20);
        searchPanel.add(searchField);
        
        JButton searchBtn = new JButton("Search");
        JButton viewAllBtn = new JButton("View All Students");
        JButton sortIdBtn = new JButton("Sort by ID");
        JButton sortNameBtn = new JButton("Sort by Name");
        
        searchPanel.add(searchBtn);
        searchPanel.add(viewAllBtn);
        searchPanel.add(sortIdBtn);
        searchPanel.add(sortNameBtn);
        
        centerPanel.add(searchPanel, BorderLayout.NORTH);
        
        // Table panel
        String[] columns = {"Student ID", "Full Name", "Age", "Gender", "Department", "GPA"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Makes the table read-only
            }
        };
        
        studentTable = new JTable(tableModel);
        studentTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        studentTable.getTableHeader().setReorderingAllowed(false);
        
        JScrollPane scrollPane = new JScrollPane(studentTable);
        centerPanel.add(scrollPane, BorderLayout.CENTER);
        
        add(centerPanel, BorderLayout.CENTER);
        
        // Status panel
        JPanel statusPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        statusLabel = new JLabel("Ready to display students");
        statusPanel.add(statusLabel);
        add(statusPanel, BorderLayout.SOUTH);
        
        // button actions
        searchBtn.addActionListener(e -> searchStudents());
        viewAllBtn.addActionListener(e -> showAllStudents());
        sortIdBtn.addActionListener(e -> sortById());
        sortNameBtn.addActionListener(e -> sortByName());
        
        // Enter key for search
        searchField.addActionListener(e -> searchStudents());
    }
    
    private void showAllStudents() {
        clearTable();
        ArrayList<Student> students = dataManager.getAllStudents();
        
        if (students.isEmpty()) {
            statusLabel.setText("No students found in the system");
            JOptionPane.showMessageDialog(this, "No students available", "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        for (Student s : students) {
            Object[] row = {
                s.getId(),
                s.getName(), 
                s.getAge(),
                s.getGender(),
                s.getDepartment(),
                String.format("%.2f", s.getGpa())
            };
            tableModel.addRow(row);
        }
        
        statusLabel.setText("Displaying all " + students.size() + " students");
    }
    
    private void searchStudents() {
        String searchText = searchField.getText().trim();
        
        if (searchText.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Please enter a student name or ID to search", 
                "Search Error", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        clearTable();
        ArrayList<Student> results = dataManager.searchStudents(searchText);
        
        for (Student s : results) {
            Object[] row = {
                s.getId(),
                s.getName(),
                s.getAge(), 
                s.getGender(),
                s.getDepartment(),
                String.format("%.2f", s.getGpa())
            };
            tableModel.addRow(row);
        }
        
        if (results.isEmpty()) {
            statusLabel.setText("No students found matching: '" + searchText + "'");
            JOptionPane.showMessageDialog(this, 
                "No students found for: " + searchText, 
                "Search Results", 
                JOptionPane.INFORMATION_MESSAGE);
        } else {
            statusLabel.setText("Found " + results.size() + " student(s) for: '" + searchText + "'");
        }
    }
    
    private void sortById() {
        dataManager.sortById();
        showAllStudents();
        JOptionPane.showMessageDialog(this, "Students sorted by ID", "Sort Complete", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void sortByName() {
        dataManager.sortByName();
        showAllStudents();
        JOptionPane.showMessageDialog(this, "Students sorted by Name", "Sort Complete", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void clearTable() {
        tableModel.setRowCount(0);
    }
    
    // Method to refresh when data gets edited
    public void refreshData() {
        showAllStudents();
    }
}