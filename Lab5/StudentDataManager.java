import java.util.*;
import java.io.*;

public class StudentDataManager extends FilesOperations<Student> {

    public StudentDataManager() {
        super("students.txt");
    }

    @Override
    protected Student createRecordFrom(String line) {

        try {
            String[] parts = line.split(",");
            if (parts.length != 6) {
                return null;
            }
            int id = Integer.parseInt(parts[0].trim());
            String name = parts[1].trim();
            String gender = parts[2].trim();
            int age = Integer.parseInt(parts[3].trim());
            String department = parts[4].trim();
            double gpa = Double.parseDouble(parts[5].trim());

            return new Student(id, name, gender, age, department, gpa);
        } catch (Exception e) {
            System.out.println("Error creating file: " + e.getMessage());
            return null;
        }
    }

    @Override
    protected String lineRepresentation(Student record) {
        return record.lineRepresentation();
    }

    @Override
    protected String getSearchKey(Student record) {
        return record.getSearchKey();
    }

    public ArrayList<Student> search(String searchText) {
        ArrayList<Student> results = new ArrayList<>();
        for (Student s : records) {
            if (String.valueOf(s.getId()).equalsIgnoreCase(searchText) || s.getName().toLowerCase().contains(searchText.toLowerCase())) {
                results.add(s);
            }
        }
public class StudentDataManager {

    private ArrayList<Student> students;
    private String filename = "students.txt";

    public StudentDataManager() {
        students = new ArrayList<>();
        loadFromFile();
    }

    // Load students from text file
    private void loadFromFile() {
        try {
            File file = new File(filename);
            if (!file.exists()) {
                createFile();
            }

            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");

                if (parts.length == 6) {
                    int id = Integer.parseInt(parts[0].trim());
                    String name = parts[1].trim();
                    int age = Integer.parseInt(parts[2].trim());
                    String gender = parts[3].trim();
                    String department = parts[4].trim();
                    double gpa = Double.parseDouble(parts[5].trim());

                    students.add(new Student(id, name, age, gender, department, gpa));
                }
            }
            scanner.close();
            System.out.println("Loaded " + students.size() + " students from file.");

        } catch (Exception e) {
            System.out.println("Error loading file: " + e.getMessage());
        }
    }

    // Create file if it doesn't exist
    private void createFile() {
        try {
            PrintWriter writer = new PrintWriter(filename);
            writer.println("101, Ahmed Mohamed, 20, Male, Computer Engineering, 3.4");
            writer.println("102, Mariam Ali, 21, Female, Electrical Engineering, 3.8");
            writer.println("103, Omar Hassan, 22, Male, Mechanical Engineering, 3.2");
            writer.println("104, Fatma Mahmoud, 19, Female, Computer Engineering, 3.9");
            writer.println("105, Khaled Ibrahim, 20, Male, Communication Engineering, 3.1");
            writer.close();
            System.out.println("Created sample students.txt file");
        } catch (Exception e) {
            System.out.println("Error creating file: " + e.getMessage());
        }
    }

    // Save students to file
    private void saveToFile() {
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

    // Get all students
    public ArrayList<Student> getAllStudents() {
        return students;
    }

    // Search by name or ID
    public ArrayList<Student> searchStudents(String searchText) {
        ArrayList<Student> results = new ArrayList<>();

        for (Student s : students) {
            // Search by name (case insensitive)
            if (s.getName().toLowerCase().contains(searchText.toLowerCase())) {
                results.add(s);
            } // Search by ID
            else if (String.valueOf(s.getId()).contains(searchText)) {
                results.add(s);
            }
        }

        return results;
    }

    // Sort by ID
    public void sortById() {
        //  bubble sort
        for (int i = 0; i < records.size() - 1; i++) {
            for (int j = 0; j < records.size() - i - 1; j++) {
                if (records.get(j).getId() > records.get(j + 1).getId()) {
        for (int i = 0; i < students.size() - 1; i++) {
            for (int j = 0; j < students.size() - i - 1; j++) {
                if (students.get(j).getId() > students.get(j + 1).getId()) {
                    // Swap students
                    Student temp = records.get(j);
                    records.set(j, records.get(j + 1));
                    records.set(j + 1, temp);
                }
            }
        }
        saveToFile(); // Save sorted data back to file
    }

    // Sort by name
    public void sortByName() {
        //  bubble sort by name
        for (int i = 0; i < records.size() - 1; i++) {
            for (int j = 0; j < records.size() - i - 1; j++) {
                if (records.get(j).getName().compareTo(records.get(j + 1).getName()) > 0) {
        for (int i = 0; i < students.size() - 1; i++) {
            for (int j = 0; j < students.size() - i - 1; j++) {
                if (students.get(j).getName().compareTo(students.get(j + 1).getName()) > 0) {
                    // Swap students
                    Student temp = records.get(j);
                    records.set(j, records.get(j + 1));
                    records.set(j + 1, temp);
                }
            }
        }
        saveToFile(); // Save sorted data back to file
    }

    public ArrayList<Student> getAllStudents() {
        return new ArrayList<>(records);
    }
    }

