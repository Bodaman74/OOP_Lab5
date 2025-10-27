
public class UpdateDelete {

    private StudentDataManager dataManager;

    public UpdateDelete(StudentDataManager dataManager) {
        this.dataManager = dataManager;
    }

    public boolean updateStudent(int id, Student updatedStudent) {
        boolean updated = dataManager.updateRecord(String.valueOf(id), updatedStudent);
        if (updated) {
            dataManager.saveToFile();
        }
        return updated;
    }

    public boolean deleteStudent(int id) {
        boolean deleted = dataManager.deleteRecord(String.valueOf(id));
        if (deleted) {
            dataManager.saveToFile();
        }
        return deleted;
    }
}
