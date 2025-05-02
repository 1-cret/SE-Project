import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author omarh
 */
public class Librarian {
    private int userID;
    private String name;
    private String email;
    private String password;
    private String role;
    private LibrarianController.Status status;
    
    // Constructor
    public Librarian(int userID, String name, String email, String password, String role, LibrarianController.Status status) {
        this.userID = userID;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.status = status;
    }
    
    // Getters and setters
    public int getUserID() {
        return userID;
    }
    
    public void setUserID(int userID) {
        this.userID = userID;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
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
    
    public LibrarianController.Status getStatus() {
        return status;
    }
    
    public void setStatus(LibrarianController.Status status) {
        this.status = status;
    }
    
    public boolean login(){
        Connection conn = DBManager.openCon();
        if (conn == null) {
            return false;
        }

        String query = "SELECT * FROM LIBRARIAN WHERE EMAIL = '" + this.getEmail() + "' AND PASSWORD = '" + this.getPassword() + "'";
        System.out.println("q: " + query);
        try {
            ResultSet res = DBManager.query(conn, query);
            if (res != null && res.next()) {
                this.setUserID(res.getInt("ID"));
                this.setName(res.getString("NAME"));
                this.setEmail(res.getString("EMAIL"));
                this.setPassword(res.getString("PASSWORD"));
                Boolean f = res.getBoolean("STATUS");
                this.setStatus(f==true?LibrarianController.Status.ACTIVE:LibrarianController.Status.DISABLED);
                return true;
            }
        } catch (SQLException ex) {
            System.out.println("Login Error: " + ex.getMessage());
        } finally {
            DBManager.closeCon(conn);
        }

        return false;
//        try {
//            // Create connection using DBManager
//            Connection connection = DBManager.openCon();
//            
//            // Prepare SQL query to check librarian credentials
//            String query = "SELECT * FROM LIBRARIAN WHERE EMAIL = ? AND PASSWORD = ?";
//            PreparedStatement stmt = connection.prepareStatement(query);
//            stmt.setString(1, email);
//            stmt.setString(2, password);
//            
//            // Execute query and check results
//            ResultSet rs = stmt.executeQuery();
//            
//            if (rs.next()) {
//                // Login successful, update librarian info from database
//                userID = rs.getInt("ID");
//                name = rs.getString("NAME");
//                status = LibrarianController.Status.valueOf(rs.getString("STATUS"));
//                
//                // Close resources
//                rs.close();
//                stmt.close();
//                DBManager.closeCon(connection);
//                return true;
//            }
//            
//            // Close resources
//            rs.close();
//            stmt.close();
//            DBManager.closeCon(connection);
//            return false;
//            
//        } catch (SQLException e) {
//            System.out.println("Database error during librarian login: " + e.getMessage());
//            return false;
//        }
    }
}
