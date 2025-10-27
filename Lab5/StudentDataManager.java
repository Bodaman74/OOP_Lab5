import java.util.*;

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
        return results;
    }

    // Sort by ID
    public void sortById() {
        //  bubble sort
        for (int i = 0; i < records.size() - 1; i++) {
            for (int j = 0; j < records.size() - i - 1; j++) {
                if (records.get(j).getId() > records.get(j + 1).getId()) {
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

