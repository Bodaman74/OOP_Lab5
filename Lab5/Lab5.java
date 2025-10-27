
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Lab5 {

    public static void main(String[] args) {

        StudentDataManager sManager = new StudentDataManager();

        // Only add if the file is empty
        if (sManager.getAllStudents().isEmpty()) {
            sManager.insertRecord(new Student(1, "Mohamed Ahmed", "Male", 20, "CCE", 3.5));
            sManager.insertRecord(new Student(6, "Omar shaaban", "Male", 23, "CCE", 3.5));
            sManager.insertRecord(new Student(8, "Salma ali", "Female", 18, "MRE", 3.2));
            sManager.insertRecord(new Student(3, "Menna yasser", "Female", 22, "CEE", 3.9));
            
            sManager.saveToFile();
        }
        MainFrame frame = new MainFrame();
        frame.setVisible(true);
    }

}
