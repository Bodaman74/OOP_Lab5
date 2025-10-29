/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab5;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ViewStudentsPanel extends JPanel {
    private StudentDataManager studentManager;
    private JTable studentTable;
    private DefaultTableModel tableModel;
    private JTextField searchField;
    private JLabel statusLabel;

    public ViewStudentsPanel(StudentDataManager manager) {
        this.studentManager = manager;
        setupPanel();
        showAllStudents();
    }

    private void setupPanel() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Title
        JLabel titleLabel = new JLabel("View and Search Students", JLabel.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(new Color(0, 70, 130));
        add(titleLabel, BorderLayout.NORTH);

        // Search panel
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        searchPanel.setBorder(BorderFactory.createTitledBorder("Search Students"));
        
        searchPanel.add(new JLabel("Search by Name or ID:"));
        searchField = new JTextField(20);
        searchPanel.add(searchField);
        
        JButton searchBtn = createStyledButton("Search", new Color(70, 130, 180));
        JButton viewAllBtn = createStyledButton("View All", new Color(60, 179, 113));
        JButton sortIdBtn = createStyledButton("Sort by ID", new Color(255, 165, 0));
        JButton sortNameBtn = createStyledButton("Sort by Name", new Color(255, 165, 0));
        
        searchPanel.add(searchBtn);
        searchPanel.add(viewAllBtn);
        searchPanel.add(sortIdBtn);
        searchPanel.add(sortNameBtn);
        
        add(searchPanel, BorderLayout.NORTH);

        // Table
        String[] columns = {"ID", "Full Name", "Age", "Gender", "Department", "GPA"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        studentTable = new JTable(tableModel);
        studentTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        studentTable.getTableHeader().setReorderingAllowed(false);
        studentTable.setRowHeight(25);
        studentTable.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        
        JScrollPane scrollPane = new JScrollPane(studentTable);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Student Records"));
        add(scrollPane, BorderLayout.CENTER);

        // Status panel
        JPanel statusPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        statusLabel = new JLabel("Ready to display students");
        statusPanel.add(statusLabel);
        add(statusPanel, BorderLayout.SOUTH);

        // Event listeners
        searchBtn.addActionListener(e -> searchStudents());
        viewAllBtn.addActionListener(e -> showAllStudents());
        sortIdBtn.addActionListener(e -> sortById());
        sortNameBtn.addActionListener(e -> sortByName());
        searchField.addActionListener(e -> searchStudents());
    }

    private JButton createStyledButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("Segoe UI", Font.BOLD, 12));
        return button;
    }

    private void showAllStudents() {
        clearTable();
        ArrayList<Student> students = studentManager.getAllStudents();
        
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
        ArrayList<Student> results = studentManager.searchStudents(searchText);
        
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
        studentManager.sortById();
        showAllStudents();
        JOptionPane.showMessageDialog(this, "Students sorted by ID", "Sort Complete", JOptionPane.INFORMATION_MESSAGE);
    }

    private void sortByName() {
        studentManager.sortByName();
        showAllStudents();
        JOptionPane.showMessageDialog(this, "Students sorted by Name", "Sort Complete", JOptionPane.INFORMATION_MESSAGE);
    }

    private void clearTable() {
        tableModel.setRowCount(0);
    }
}