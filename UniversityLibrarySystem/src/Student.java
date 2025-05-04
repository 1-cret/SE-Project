import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Student extends User {

    private ArrayList<Borrow> borrowList;

    public Student(int userID) {
        super(userID);
        this.borrowList = new ArrayList<Borrow>();
    }

    public Student(String name, String email, String password, String role, UserStatus.Status status, ArrayList<Borrow> borrowList) {
        super(name, email, password, role, status);
        this.borrowList = borrowList;
    }

    public void setBorrowList(ArrayList<Borrow> borrowList) {
        this.borrowList = borrowList;
    }

    public ArrayList<Borrow> getBorrowList() {
        return borrowList;
    }

    public boolean login() {
        Connection conn = DBManager.openCon();
        if (conn == null) {
            return false;
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
                return true;
            }
        } catch (SQLException ex) {
            System.out.println("Login Error: " + ex.getMessage());
        } finally {
            DBManager.closeCon(conn);
        }

        return false;
    }

    public boolean signUp(String name, String email, String password) {
        // Check if all required fields are provided
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
            String insertQuery = "INSERT INTO STUDENT (ID, NAME, EMAIL, PASSWORD, ROLE, STATUS) VALUES (" + (lastId + 1) + ", '" + name + "', '" + email + "', '" + password + "', 'Student', TRUE)";
            ResultSet res = DBManager.query(conn, insertQuery);
            // Set the user ID
            this.setUserID(lastId + 1);
            if (res != null) {
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
