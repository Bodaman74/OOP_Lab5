import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

public abstract class FilesOperations<T> {
    protected ArrayList<T> records;
    protected String filename;

    public FilesOperations(String filename) {
        this.filename = filename;
        this.records = new ArrayList<>();
        readFromFile();
    }

    protected abstract T createRecordFrom(String line);
    protected abstract String getSearchKey(T record);
    protected abstract String lineRepresentation(T record);

    public ArrayList<T> getAllRecords() {
        return records;
    }

    public void readFromFile() {
        records.clear();//clear old data before reading again
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line = reader.readLine();

            while (line != null) {
                T record = createRecordFrom(line);
                if (record != null) {
                    records.add(record);
                }
                line = reader.readLine();
            }
            reader.close();
        } catch (Exception e) {
            // File might not exist yet
        }
    }

    public void saveToFile() {//append or overwrite?
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(filename));
            for (int i = 0; i < records.size(); i++) {
                T record = records.get(i);
                writer.println(lineRepresentation(record));
            }
            writer.close();
        } catch (Exception e) {
            System.out.println("Error saving to " + filename);
        }
    }

    public boolean contains(String key) {
        for (int i = 0; i < records.size(); i++) {
            T record = records.get(i);
            if (getSearchKey(record).equals(key)) {
                return true;
            }
        }
        return false;
    }

    public T getRecord(String key) {
        for (int i = 0; i < records.size(); i++) {
            T record = records.get(i);
            if (getSearchKey(record).equals(key)) {
                return record;
            }
        }
        return null;
    }

    public void insertRecord(T record) {
        if (!contains(getSearchKey(record))) {
            records.add(record);
        }
    }
    
    public boolean deleteRecord(String key) {
        for (int i = 0; i < records.size(); i++) {
            if (getSearchKey(records.get(i)).equals(key)) {
                records.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean updateRecord(String key, T newRecord) {
        for (int i = 0; i < records.size(); i++) {
            if (getSearchKey(records.get(i)).equals(key)) {
                records.set(i, newRecord);
                return true;
            }
        }
        return false;
    }
}