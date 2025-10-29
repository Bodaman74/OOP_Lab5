/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab5;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteStudentPanel extends JPanel {
    private StudentDataManager studentManager;
    private JTextField searchIdField, idField, nameField, ageField, genderField, departmentField, gpaField;
    private JButton searchButton, deleteButton, clearButton;
    private Student currentStudent;

    public DeleteStudentPanel(StudentDataManager manager) {
        this.studentManager = manager;
        setupPanel();
    }

    private void setupPanel() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Title
        JLabel titleLabel = new JLabel("Delete Student", JLabel.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(new Color(0, 70, 130));
        add(titleLabel, BorderLayout.NORTH);

        // Main panel
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        // Search panel
        JPanel searchPanel = new JPanel(new FlowLayout());
        searchPanel.setBorder(BorderFactory.createTitledBorder("Find Student to Delete"));
        searchPanel.add(new JLabel("Search by ID:"));
        searchIdField = new JTextField(10);
        searchPanel.add(searchIdField);
        
        searchButton = createStyledButton("Search", new Color(70, 130, 180));
        searchPanel.add(searchButton);
        
        mainPanel.add(searchPanel, BorderLayout.NORTH);

        // Display panel (read-only)
        JPanel displayPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        displayPanel.setBorder(BorderFactory.createTitledBorder("Student Information (Read-only)"));

        displayPanel.add(new JLabel("Student ID:"));
        idField = new JTextField();
        idField.setEditable(false);
        displayPanel.add(idField);

        displayPanel.add(new JLabel("Full Name:"));
        nameField = new JTextField();
        nameField.setEditable(false);
        displayPanel.add(nameField);

        displayPanel.add(new JLabel("Age:"));
        ageField = new JTextField();
        ageField.setEditable(false);
        displayPanel.add(ageField);

        displayPanel.add(new JLabel("Gender:"));
        genderField = new JTextField();
        genderField.setEditable(false);
        displayPanel.add(genderField);

        displayPanel.add(new JLabel("Department:"));
        departmentField = new JTextField();
        departmentField.setEditable(false);
        displayPanel.add(departmentField);

        displayPanel.add(new JLabel("GPA:"));
        gpaField = new JTextField();
        gpaField.setEditable(false);
        displayPanel.add(gpaField);

        mainPanel.add(displayPanel, BorderLayout.CENTER);

        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout());
        deleteButton = createStyledButton("Delete Student", new Color(220, 80, 70));
        clearButton = createStyledButton("Clear", new Color(220, 120, 70));
        
        buttonPanel.add(deleteButton);
        buttonPanel.add(clearButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel, BorderLayout.CENTER);

        // Event listeners
        searchButton.addActionListener(e -> searchStudent());
        deleteButton.addActionListener(e -> deleteStudent());
        clearButton.addActionListener(e -> clearForm());
    }

    private JButton createStyledButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("Segoe UI", Font.BOLD, 12));
        return button;
    }

    private void searchStudent() {
        try {
            int id = Integer.parseInt(searchIdField.getText().trim());
            currentStudent = studentManager.getStudentById(id);
            
            if (currentStudent != null) {
                idField.setText(String.valueOf(currentStudent.getId()));
                nameField.setText(currentStudent.getName());
                ageField.setText(String.valueOf(currentStudent.getAge()));
                genderField.setText(currentStudent.getGender());
                departmentField.setText(currentStudent.getDepartment());
                gpaField.setText(String.format("%.2f", currentStudent.getGpa()));
                JOptionPane.showMessageDialog(this, "Student found! You can now delete this record.", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Student not found with ID: " + id, "Error", JOptionPane.ERROR_MESSAGE);
                clearForm();
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid student ID", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteStudent() {
        if (currentStudent == null) {
            JOptionPane.showMessageDialog(this, "Please search for a student first", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int confirmation = JOptionPane.showConfirmDialog(this, 
            "Are you sure you want to delete student:\n" + 
            "ID: " + currentStudent.getId() + "\n" +
            "Name: " + currentStudent.getName() + "\n" +
            "Department: " + currentStudent.getDepartment(),
            "Confirm Deletion", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        
        if (confirmation == JOptionPane.YES_OPTION) {
            boolean deleted = studentManager.deleteStudent(currentStudent.getId());
            if (deleted) {
                JOptionPane.showMessageDialog(this, 
                    "Student deleted successfully!\nID: " + currentStudent.getId() + "\nName: " + currentStudent.getName(), 
                    "Success", JOptionPane.INFORMATION_MESSAGE);
                currentStudent = null;
                clearForm();
            } else {
                JOptionPane.showMessageDialog(this, "Error deleting student", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void clearForm() {
        searchIdField.setText("");
        idField.setText("");
        nameField.setText("");
        ageField.setText("");
        genderField.setText("");
        departmentField.setText("");
        gpaField.setText("");
        currentStudent = null;
    }
}