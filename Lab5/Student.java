public class Student {
    private int id;
    private String name;
    private String gender;
    private int age;
    private String department;
    private double gpa;
    // Constructor
    public Student(int id, String name, String gender, int age, String department, double gpa) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.department = department;
        this.gpa = gpa;
    }

   // Setters    
    public void setId(int id) {this.id = id;}
    public void setName(String name) {this.name = name;}
    public void setGender(String gender) { this.gender = gender;}
    public void setAge(int age) {this.age = age;}
    public void setDepartment(String department) {this.department = department;}
    public void setGpa(double gpa) {this.gpa = gpa;}
    
    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public String getGender() { return gender; }
    public int getAge() { return age; }
    public String getDepartment() { return department; }
    public double getGpa() { return gpa; }
    
    public String lineRepresentation() {
        return id + "," + name + "," + gender + "," + age + "," + department + "," + gpa;
    }
    
    //Key
    public String getSearchKey() {
         return String.valueOf(id);
   //      return id + "," + name+ "," + gender + "," + age +","+  department+ "," + gpa;
    }
    
    @Override
    public String toString(){
        return lineRepresentation();
    }
}
