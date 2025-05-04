/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.util.ArrayList;
import java.sql.Connection; 
import java.sql.DriverManager; 
import java.sql.ResultSet; 
import java.sql.SQLException; 
import java.sql.Statement; 

/**
 *
 * @author omarh
 */
public class StudentController{
   
    Student student;
    private ArrayList<Borrow> borrowList;
    private int userID;
    private String name;
    private String email;
    private String password;
    private String role;
    private UserStatus.Status status;
    public StudentController(String name, String email, String password, String role, UserStatus.Status status, ArrayList<Borrow> borrowList) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.status = status;
        this.borrowList = borrowList;
        this.student = new Student(name, email, password, role, status, borrowList);
        student = new Student(name, email, password, role, status, borrowList);
    }
    public StudentController(String email, String password) {
        this.email = email;
        this.password = password;
        this.student = new Student(null, email, password, null, null, null);
    }
    public boolean login() {
        return student.login();
    }
    
//    public boolean signup(){
//        return student.signUp();
//    }
}
