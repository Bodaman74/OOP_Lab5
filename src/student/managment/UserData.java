/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package student.managment;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author DELL
 */
public class UserData {
    private ArrayList<User> users;
    private String filename = "users.txt";

    public UserData() {
        users = new ArrayList<>();
        loadFromFile(); //read the file
    }

    
    private void loadFromFile() {
        users.clear();
        File file = new File(filename);
        

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty()) continue;

                String[] parts = line.split(",", 2); 
                if (parts.length == 2) {
                    String username = parts[0].trim();
                    String password = parts[1].trim();
                    users.add(new User(username, password));
                } 
            }
        } catch (FileNotFoundException e) {
            
            System.err.println("Error: users file not accessible: " + e.getMessage());
        }
    }

    
//    public ArrayList<User> getAllUsers() {
//        return users; // return records
//        
//    }
    public boolean validation(String username, String password) {
        for (User u : users) {
            if (u.getUserName().equals(username)) {
                return u.getPassword().equals(password); // if username exists ,check if the password matchs
            }
        }
        return false; // the username not found or password not match
    }
    
}
