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
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddStudentPanel extends JPanel {
    private StudentDataManager studentManager;
    private JTextField idField, nameField, ageField, gpaField, departmentField;
    private JComboBox<String> genderBox;
    private JButton addButton, clearButton;

    public AddStudentPanel(StudentDataManager manager) {
        this.studentManager = manager;
        setupPanel();
    }

    private void setupPanel() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Title
        JLabel titleLabel = new JLabel("Add New Student", JLabel.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(new Color(0, 70, 130));
        add(titleLabel, BorderLayout.NORTH);

        // Main form panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createTitledBorder("Student Information"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        // Student ID
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("Student ID:*"), gbc);
        gbc.gridx = 1;
        idField = new JTextField(20);
        formPanel.add(idField, gbc);

        // Full Name
        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(new JLabel("Full Name:*"), gbc);
        gbc.gridx = 1;
        nameField = new JTextField(20);
        formPanel.add(nameField, gbc);

        // Age
        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(new JLabel("Age:*"), gbc);
        gbc.gridx = 1;
        ageField = new JTextField(20);
        formPanel.add(ageField, gbc);

        // Gender
        gbc.gridx = 0; gbc.gridy = 3;
        formPanel.add(new JLabel("Gender:*"), gbc);
        gbc.gridx = 1;
        genderBox = new JComboBox<>(new String[]{"Male", "Female"});
        genderBox.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        formPanel.add(genderBox, gbc);

        // Department
        gbc.gridx = 0; gbc.gridy = 4;
        formPanel.add(new JLabel("Department:*"), gbc);
        gbc.gridx = 1;
        departmentField = new JTextField(20);
        formPanel.add(departmentField, gbc);

        // GPA
        gbc.gridx = 0; gbc.gridy = 5;
        formPanel.add(new JLabel("GPA:*"), gbc);
        gbc.gridx = 1;
        gpaField = new JTextField(20);
        formPanel.add(gpaField, gbc);

        add(formPanel, BorderLayout.CENTER);

        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout());
        addButton = createStyledButton("Add Student", new Color(60, 179, 113));
        clearButton = createStyledButton("Clear Form", new Color(220, 120, 70));

        buttonPanel.add(addButton);
        buttonPanel.add(clearButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Event listeners
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addStudent();
            }
        });

        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clearForm();
            }
        });
    }

    private JButton createStyledButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setPreferredSize(new Dimension(120, 35));
        return button;
    }

    private void addStudent() {
        try {
            int id = Integer.parseInt(idField.getText().trim());
            String name = nameField.getText().trim();
            int age = Integer.parseInt(ageField.getText().trim());
            String gender = (String) genderBox.getSelectedItem();
            String department = departmentField.getText().trim();
            double gpa = Double.parseDouble(gpaField.getText().trim());

            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter student name", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (department.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter department", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
             //Validation name and department must not contain numbers 
             if (name.matches(".*\\d.*")) {
              JOptionPane.showMessageDialog(this, "Name cannot contain numbers!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
                 }

            if (department.matches(".*\\d.*")) {
             JOptionPane.showMessageDialog(this, "Department cannot contain numbers!", "Error", JOptionPane.ERROR_MESSAGE);
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

            boolean added = studentManager.addStudent(id, name, age, gender, department, gpa);
            if (added) {
                JOptionPane.showMessageDialog(this, 
                    "Student added successfully!\nID: " + id + "\nName: " + name, 
                    "Success", JOptionPane.INFORMATION_MESSAGE);
                clearForm();
            } else {
                JOptionPane.showMessageDialog(this, 
                    "Failed to add student. Student ID might already exist.", 
                    "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, 
                "Please check your input:\n- ID, Age, and GPA must be valid numbers", 
                "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearForm() {
        idField.setText("");
        nameField.setText("");
        ageField.setText("");
        gpaField.setText("");
        departmentField.setText("");
        genderBox.setSelectedIndex(0);
    }
    
}