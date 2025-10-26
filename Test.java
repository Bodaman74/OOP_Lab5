package lab5;


import lab5.StudentManager;

public class Test {
    public static void main(String[] args) {
        StudentManager manager = new StudentManager();

        // تجربة إدخال طالب جديد
        boolean added = manager.addStudent(1, "Marawan Hamada", 19, "Male", "Computer Engineering", 3.8);

        if (added) {
            System.out.println("Student Added Successfully and saved to file!");
        } else {
            System.out.println("Failed to add student.");
        }
    }
}
