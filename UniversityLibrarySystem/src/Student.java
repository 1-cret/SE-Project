import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Student {
    private int userID;
    private String name;
    private String email;
    private String password;
    private String role;
    private UserStatus.Status status;
    private ArrayList<Borrow> borrowList;

    public Student(int userID, ArrayList<Borrow> borrowList) {
        this.userID = userID;
        this.borrowList = borrowList;
    }

    public Student(String name, String email, String password, String role, UserStatus.Status status, ArrayList<Borrow> borrowList) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.status = status;
        this.borrowList = borrowList;
    }
    
    public void setUserID(int userID) {
        this.userID = userID;
    }
    
    public int getUserID() {
        return userID;
    }    

    public void setName(String name) {
        this.name = name;
    }

     public String getName() {
        return name;
    }
   
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public UserStatus.Status getStatus() {
        return status;
    }

    public void setStatus(UserStatus.Status status) {
        this.status = status;
    }

    public void setBorrowList(ArrayList<Borrow> borrowList) {
        this.borrowList = borrowList;
    }

    public ArrayList<Borrow> getBorrowList() {
        return borrowList;
    }

    public Student login() {
        Connection conn = DBManager.openCon();
        if (conn == null) {
            return null;
        }

        String query = "SELECT * FROM STUDENT WHERE EMAIL = '" + this.getEmail() + "' AND PASSWORD = '" + this.getPassword() + "'";
        System.out.println("q: " + query);
        try {
            ResultSet res = DBManager.query(conn, query);
            if (res != null && res.next()) {
                this.setUserID(res.getInt("ID"));
                this.setName(res.getString("NAME"));
                this.setEmail(res.getString("EMAIL"));
                this.setPassword(res.getString("PASSWORD"));
                Boolean f = res.getBoolean("STATUS");
                this.setStatus(f == true ? UserStatus.Status.ACTIVE : UserStatus.Status.DISABLED);
                return this;
            }
        } catch (SQLException ex) {
            System.out.println("Login Error: " + ex.getMessage());
        } finally {
            DBManager.closeCon(conn);
        }

        return null;
    }

    public boolean signUp(String name, String email, String password) {
        // Check if all required fields are providedsword) {
        // Check if all required 
        if (name == null || name.trim().isEmpty() ||
            email == null || email.trim().isEmpty() ||
            password == null || password.trim().isEmpty()) {
            return false;
        }
        
        // Set the user properties
        this.setName(name);
        this.setEmail(email);
        this.setPassword(password);
        this.setRole("Student");
        this.setStatus(UserStatus.Status.ACTIVE);
        
        Connection conn = DBManager.openCon();
        if (conn == null) {
            return false;
        }
        
        try {
            // Check if email already exists
            String checkQuery = "SELECT * FROM STUDENT WHERE EMAIL = '" + email + "'";
            ResultSet checkResult = DBManager.query(conn, checkQuery);
            if (checkResult != null && checkResult.next()) {
                // Email already exists
                return false;
            }
            // Get last user ID
            String lastIdQuery = "SELECT MAX(ID) AS LAST_ID FROM STUDENT";
            ResultSet lastIdResult = DBManager.query(conn, lastIdQuery);
            int lastId = 0;
            if (lastIdResult != null && lastIdResult.next()) {
                lastId = lastIdResult.getInt("LAST_ID");
            }
            
            // Insert new student
            String insertQuery = "INSERT INTO STUDENT (ID, NAME, EMAIL, PASSWORD, STATUS) VALUES (" + (lastId + 1) + ", '" + name + "', '" + email + "', '" + password + "', TRUE)";
            int stat = DBManager.updateQuery(conn, insertQuery);
            // Set the user ID
            this.setUserID(lastId + 1);
            if (stat == 1) {
                System.out.println("Signed up.");
                return true;
            }
            
        } catch (SQLException ex) {
            System.out.println("Sign Up Error: " + ex.getMessage());
        } finally {
            DBManager.closeCon(conn);
        }
        return false;

    }

}