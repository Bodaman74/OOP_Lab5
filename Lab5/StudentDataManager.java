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

public class StudentDataManager {
    private ArrayList<Student> students;
    
    public StudentDataManager() {
        students = new ArrayList<>();
        addSampleStudents(); // Add some test data
    }
    
    // Add sample students for testing
    private void addSampleStudents() {
        students.add(new Student(101, "Ahmed Mohamed", 20, "Male", "Computer Engineering", 3.4));
        students.add(new Student(102, "Mariam Ali", 21, "Female", "Electrical Engineering", 3.8));
        students.add(new Student(103, "Omar Hassan", 22, "Male", "Mechanical Engineering", 3.2));
        students.add(new Student(104, "Fatma Mahmoud", 19, "Female", "Computer Engineering", 3.9));
        students.add(new Student(105, "Khaled Ibrahim", 20, "Male", "Communication Engineering", 3.1));
    }
    
    // Get all students
    public ArrayList<Student> getAllStudents() {
        return students;
    }
    
    // Search by name or ID
    public ArrayList<Student> searchStudents(String searchText) {
        ArrayList<Student> results = new ArrayList<>();
        
        for (Student s : students) {
            // Search by name 
            if (s.getName().toLowerCase().contains(searchText.toLowerCase())) {
                results.add(s);
            }
            // Search by ID
            else if (String.valueOf(s.getId()).contains(searchText)) {
                results.add(s);
            }
        }
        
        return results;
    }
    
    // Sort by ID
    public void sortById() {
        //  bubble sort 
        for (int i = 0; i < students.size() - 1; i++) {
            for (int j = 0; j < students.size() - i - 1; j++) {
                if (students.get(j).getId() > students.get(j + 1).getId()) {
                    // Swap students
                    Student temp = students.get(j);
                    students.set(j, students.get(j + 1));
                    students.set(j + 1, temp);
                }
            }
        }
    }
    
    // Sort by name
    public void sortByName() {
        // Simple bubble sort by name
        for (int i = 0; i < students.size() - 1; i++) {
            for (int j = 0; j < students.size() - i - 1; j++) {
                if (students.get(j).getName().compareTo(students.get(j + 1).getName()) > 0) {
                    // Swap students
                    Student temp = students.get(j);
                    students.set(j, students.get(j + 1));
                    students.set(j + 1, temp);
                }
            }
        }
    }
}
