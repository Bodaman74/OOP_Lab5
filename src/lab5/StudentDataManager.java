/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab5;

/**
 *
 * @author PCV
 */
import java.util.*;
import java.io.*;


public class StudentDataManager {
    private ArrayList<Student> students;
    private String filename = "students.txt";

    public StudentDataManager() {
        students = new ArrayList<>();
        loadFromFile();
    }

    private void loadFromFile() {
        try {
            File file = new File(filename);
            if (!file.exists()) {
                createFile();
                return;
            }

            Set<Integer> uniqueIds = new HashSet<>();
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty()) continue;
                
                String[] parts = line.split(",");
                if (parts.length == 6) {
                    try {
                        int id = Integer.parseInt(parts[0].trim());
                        
                        // Prevent duplicates
                        if (!uniqueIds.contains(id)) {
                            String name = parts[1].trim();
                            int age = Integer.parseInt(parts[2].trim());
                            String gender = parts[3].trim();
                            String department = parts[4].trim();
                            double gpa = Double.parseDouble(parts[5].trim());

                            students.add(new Student(id, name, age, gender, department, gpa));
                            uniqueIds.add(id);
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Skipping invalid line: " + line);
                    }
                }
            }
            scanner.close();
            System.out.println("Loaded " + students.size() + " students from file.");

        } catch (Exception e) {
            System.out.println("Error loading file: " + e.getMessage());
        }
    }

    private void createFile() {
        try {
            PrintWriter writer = new PrintWriter(filename);
            writer.println("101,Ahmed Mohamed,20,Male,Computer Engineering,3.4");
            writer.println("102,Mariam Ali,21,Female,Electrical Engineering,3.8");
            writer.println("103,Omar Hassan,22,Male,Mechanical Engineering,3.2");
            writer.println("104,Fatma Mahmoud,19,Female,Computer Engineering,3.9");
            writer.println("105,Khaled Ibrahim,20,Male,Communication Engineering,3.1");
            writer.close();
            System.out.println("Created sample students.txt file");
        } catch (Exception e) {
            System.out.println("Error creating file: " + e.getMessage());
        }
    }

    public void saveToFile() {
        try {
            PrintWriter writer = new PrintWriter(filename);
            for (Student s : students) {
                writer.println(s.toString());
            }
            writer.close();
        } catch (Exception e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }

    public ArrayList<Student> getAllStudents() {
        return new ArrayList<>(students);
    }

    public ArrayList<Student> searchStudents(String searchText) {
        ArrayList<Student> results = new ArrayList<>();
        for (Student s : students) {
            if (s.getName().toLowerCase().contains(searchText.toLowerCase()) || 
                String.valueOf(s.getId()).contains(searchText)) {
                results.add(s);
            }
        }
        return results;
    }

    public void sortById() {
        students.sort((s1, s2) -> Integer.compare(s1.getId(), s2.getId()));
        saveToFile();
    }

    public void sortByName() {
        students.sort((s1, s2) -> s1.getName().compareToIgnoreCase(s2.getName()));
        saveToFile();
    }
    
    // Complete CRUD operations
    public boolean addStudent(int id, String name, int age, String gender, String department, double gpa) {
        // Validation
        if (name == null || name.trim().isEmpty()) {
            return false;
        }
        if (age < 16 || age > 60) {
            return false;
        }
        if (gpa < 0.0 || gpa > 4.0) {
            return false;
        }
        if (department == null || department.trim().isEmpty()) {
            return false;
        }

        // Check for duplicate ID
        for (Student s : students) {
            if (s.getId() == id) {
                return false;
            }
        }

        Student newStudent = new Student(id, name.trim(), age, gender, department.trim(), gpa);
        students.add(newStudent);
        saveToFile();
        return true;
    }

    public boolean updateStudent(int id, String name, int age, String gender, String department, double gpa) {
        for (Student s : students) {
            if (s.getId() == id) {
                // Validation
                if (name == null || name.trim().isEmpty()) return false;
                if (age < 16 || age > 60) return false;
                if (gpa < 0.0 || gpa > 4.0) return false;
                if (department == null || department.trim().isEmpty()) return false;

                s.setName(name.trim());
                s.setAge(age);
                s.setGender(gender);
                s.setDepartment(department.trim());
                s.setGpa(gpa);
                saveToFile();
                return true;
            }
        }
        return false;
    }

    public boolean deleteStudent(int id) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId() == id) {
                students.remove(i);
                saveToFile();
                return true;
            }
        }
        return false;
    }

    public Student getStudentById(int id) {
        for (Student s : students) {
            if (s.getId() == id) {
                return s;
            }
        }
        return null;
    }
}