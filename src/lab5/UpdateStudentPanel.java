/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab5;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateStudentPanel extends JPanel {
    private StudentDataManager studentManager;
    private JTextField searchIdField, idField, nameField, ageField, gpaField, departmentField;
    private JComboBox<String> genderBox;
    private JButton searchButton, updateButton, clearButton;
    private Student currentStudent;

    public UpdateStudentPanel(StudentDataManager manager) {
        this.studentManager = manager;
        setupPanel();
    }

    private void setupPanel() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Title
        JLabel titleLabel = new JLabel("Update Student Information", JLabel.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(new Color(0, 70, 130));
        add(titleLabel, BorderLayout.NORTH);

        // Main panel
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        // Search panel
        JPanel searchPanel = new JPanel(new FlowLayout());
        searchPanel.setBorder(BorderFactory.createTitledBorder("Find Student to Update"));
        searchPanel.add(new JLabel("Search by ID:"));
        searchIdField = new JTextField(10);
        searchPanel.add(searchIdField);
        
        searchButton = createStyledButton("Search", new Color(70, 130, 180));
        searchPanel.add(searchButton);
        
        mainPanel.add(searchPanel, BorderLayout.NORTH);

        // Form panel
        JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createTitledBorder("Student Information (Editable)"));

        formPanel.add(new JLabel("Student ID:"));
        idField = new JTextField();
        idField.setEditable(false);
        formPanel.add(idField);

        formPanel.add(new JLabel("Full Name:*"));
        nameField = new JTextField();
        formPanel.add(nameField);

        formPanel.add(new JLabel("Age:*"));
        ageField = new JTextField();
        formPanel.add(ageField);

        formPanel.add(new JLabel("Gender:*"));
        genderBox = new JComboBox<>(new String[]{"Male", "Female"});
        formPanel.add(genderBox);

        formPanel.add(new JLabel("Department:*"));
        departmentField = new JTextField();
        formPanel.add(departmentField);

        formPanel.add(new JLabel("GPA:*"));
        gpaField = new JTextField();
        formPanel.add(gpaField);

        mainPanel.add(formPanel, BorderLayout.CENTER);

        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout());
        updateButton = createStyledButton("Update Student", new Color(60, 179, 113));
        clearButton = createStyledButton("Clear", new Color(220, 120, 70));
        
        buttonPanel.add(updateButton);
        buttonPanel.add(clearButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel, BorderLayout.CENTER);

        // Event listeners
        searchButton.addActionListener(e -> searchStudent());
        updateButton.addActionListener(e -> updateStudent());
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
                genderBox.setSelectedItem(currentStudent.getGender());
                departmentField.setText(currentStudent.getDepartment());
                gpaField.setText(String.valueOf(currentStudent.getGpa()));
                JOptionPane.showMessageDialog(this, "Student found! You can now update the information.", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Student not found with ID: " + id, "Error", JOptionPane.ERROR_MESSAGE);
                clearForm();
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid student ID", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateStudent() {
        if (currentStudent == null) {
            JOptionPane.showMessageDialog(this, "Please search for a student first", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
            String name = nameField.getText().trim();
            int age = Integer.parseInt(ageField.getText().trim());
            String gender = (String) genderBox.getSelectedItem();
            String department = departmentField.getText().trim();
            double gpa = Double.parseDouble(gpaField.getText().trim());

            // Validation
            // Validation name and department must not contain numbers 
             if (name.matches(".*\\d.*")) {
              JOptionPane.showMessageDialog(this, "Name cannot contain numbers!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
                 }

            if (department.matches(".*\\d.*")) {
             JOptionPane.showMessageDialog(this, "Department cannot contain numbers!", "Error", JOptionPane.ERROR_MESSAGE);
               return;
                }
            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter student name", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (department.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter department", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (age < 16 || age > 60) {
                JOptionPane.showMessageDialog(this, "Age must be between 16 and 60", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (gpa < 0.0 || gpa > 4.0) {
                JOptionPane.showMessageDialog(this, "GPA must be between 0.0 and 4.0", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            boolean updated = studentManager.updateStudent(currentStudent.getId(), name, age, gender, department, gpa);
            if (updated) {
                JOptionPane.showMessageDialog(this, 
                    "Student updated successfully!\nID: " + currentStudent.getId() + "\nName: " + name, 
                    "Success", JOptionPane.INFORMATION_MESSAGE);
                currentStudent = null;
                clearForm();
            } else {
                JOptionPane.showMessageDialog(this, "Error updating student", "Error", JOptionPane.ERROR_MESSAGE);
            }
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, 
                "Please check your input:\n- Age and GPA must be valid numbers", 
                "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearForm() {
        searchIdField.setText("");
        idField.setText("");
        nameField.setText("");
        ageField.setText("");
        gpaField.setText("");
        departmentField.setText("");
        genderBox.setSelectedIndex(0);
        currentStudent = null;
    }
}