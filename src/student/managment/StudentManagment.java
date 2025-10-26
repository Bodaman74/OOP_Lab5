/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package student.managment;

/**
 *
 * @author DELL
 */
public class StudentManagment {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
       User U1 =new User("admin","1234abc");
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
 
                new LoginScreen(U1).setVisible(true);
            }
        });
        
    }
    
}
