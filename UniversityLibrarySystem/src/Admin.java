import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author omarh
 */
public class Admin {
    private int userID;
    private String name;
    private String email;
    private String password;
    private String role;
    private AdminController.Status status;
    
    public Admin(int userID, String name, String email, String password, String role, AdminController.Status status) {
        this.userID = userID;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.status = status;
    }
    
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
    
    public AdminController.Status getStatus() {
        return status;
    }
    
    public void setStatus(AdminController.Status status) {
        this.status = status;
    }
    
    public Admin login(){
        Connection conn = DBManager.openCon();
        if (conn == null) {
            return null;
        }

        String query = "SELECT * FROM ADMIN WHERE EMAIL = '" + this.getEmail() + "' AND PASSWORD = '" + this.getPassword() + "'";
        System.out.println("q: " + query);
        try {
            ResultSet res = DBManager.query(conn, query);
            if (res != null && res.next()) {
                this.setUserID(res.getInt("ID"));
                this.setName(res.getString("NAME"));
                this.setEmail(res.getString("EMAIL"));
                this.setPassword(res.getString("PASSWORD"));
                Boolean f = res.getBoolean("STATUS");
                this.setStatus(f==true?AdminController.Status.ACTIVE:AdminController.Status.DISABLED);
                return this;
            }
        } catch (SQLException ex) {
            System.out.println("Login Error: " + ex.getMessage());
        } finally {
            DBManager.closeCon(conn);
        }

        return null;
    }
}