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
    
    // Enum for tracking signup status
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
    
    public boolean login() {
        return student.login();
    }
    
    /**
     * Validates and processes student signup
     * @param name Student's name
     * @param email Student's email address
     * @param password Student's password
     * @return Status enum indicating the result of the signup operation
     */
    public Status signUp(String name, String email, String password) {
        // Check for empty fields
        if (name == null || name.trim().isEmpty() ||
            email == null || email.trim().isEmpty() ||
            password == null || password.trim().isEmpty()) {
            return Status.EMPTY_FIELDS;
        }
        
        // Validate email format with regex
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        if (!pattern.matcher(email).matches()) {
            return Status.INVALID_EMAIL;
        }
        
        // Initialize student object
        this.student = new Student(0); // Initialize with temporary ID
        
        // Attempt signup
        boolean success = student.signUp(name, email, password);
        
        if (success) {
            // Set the controller's properties
            this.name = student.getName();
            this.email = student.getEmail();
            this.password = student.getPassword();
            this.role = student.getRole();
            this.status = student.getStatus();
            this.userID = student.getUserID();
            
            return Status.SUCCESS;
        } else {
            // Check if email exists query could be executed in the Student class
            // but this is a simpler approach without modifying Student class
            Connection conn = DBManager.openCon();
            if (conn == null) {
                return Status.DATABASE_ERROR;
            }
            
            try {
                String checkQuery = "SELECT * FROM STUDENT WHERE EMAIL = '" + email + "'";
                ResultSet checkResult = DBManager.query(conn, checkQuery);
                if (checkResult != null && checkResult.next()) {
                    return Status.EMAIL_EXISTS;
                }
            } catch (SQLException ex) {
                System.out.println("Email Check Error: " + ex.getMessage());
            } finally {
                DBManager.closeCon(conn);
            }
            
            return Status.DATABASE_ERROR;
        }
    }
}
