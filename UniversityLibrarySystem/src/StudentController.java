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
import java.util.regex.Pattern;

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
    
    public enum Status {
        SUCCESS,
        EMPTY_FIELDS,
        INVALID_EMAIL,
        EMAIL_EXISTS,
        DATABASE_ERROR
    }
    
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
    
    public StudentController login() {
        this.student = student.login();
        if(student != null) {
            return this;
        } else {
            return null;
        }
    }
    public Student getStudent() {
        return student;
    }
    public void setStudent(Student student) {
        this.student = student;
    }
    
    public Status signUp(String name, String email, String password) {
       boolean s = student.signUp(name, email, password);
        if (s) {
            return Status.SUCCESS;
        } else {
            return Status.DATABASE_ERROR;
        }
    }
}
