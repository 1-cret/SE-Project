import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
    
    public Librarian(int userID, String name, String email, String password, String role, LibrarianController.Status status) {
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
    
    public LibrarianController.Status getStatus() {
        return status;
    }
    
    public void setStatus(LibrarianController.Status status) {
        this.status = status;
    }
    
    public Librarian login(){
        Connection conn = DBManager.openCon();
        if (conn == null) {
            return null;
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
                return this;
            }
        } catch (SQLException ex) {
            System.out.println("Login Error: " + ex.getMessage());
        } finally {
            DBManager.closeCon(conn);
        }

        return null;
    }
    
    public static List<Librarian> getAllLibrarians() {
        Connection conn = DBManager.openCon();
        List<Librarian> librarians = new ArrayList<>();
        
        if (conn == null) {
            return librarians;
        }
        
        String query = "SELECT * FROM LIBRARIAN";
        try {
            ResultSet rs = DBManager.query(conn, query);
            while (rs != null && rs.next()) {
                int id = rs.getInt("ID");
                String name = rs.getString("NAME");
                String email = rs.getString("EMAIL");
                String password = rs.getString("PASSWORD");
                boolean isActive = rs.getBoolean("STATUS");
                LibrarianController.Status status = isActive ? 
                    LibrarianController.Status.ACTIVE : 
                    LibrarianController.Status.DISABLED;
                
                Librarian librarian = new Librarian(id, name, email, password, "librarian", status);
                librarians.add(librarian);
            }
        } catch (SQLException ex) {
            System.out.println("Error fetching librarians: " + ex.getMessage());
        } finally {
            DBManager.closeCon(conn);
        }
        
        return librarians;
    }
    
    public boolean addLibrarian() throws SQLException {
        Connection conn = DBManager.openCon();
        if (conn == null) {
            return false;
        }
        String lastIdQuery = "SELECT MAX(ID) AS LAST_ID FROM ADMIN";
        ResultSet lastIdResult = DBManager.query(conn, lastIdQuery);
        int lastId = 0;
        if (lastIdResult != null && lastIdResult.next()) {
            lastId = lastIdResult.getInt("LAST_ID");
        }
        int nextID=lastId+1;
        String query = "INSERT INTO LIBRARIAN (ID,NAME, EMAIL, PASSWORD, STATUS) VALUES (" 
                +nextID+", '" + this.name + "', '" 
                + this.email + "', '" 
                + this.password + "', " 
                + (this.status == LibrarianController.Status.ACTIVE ? "TRUE" : "FALSE") + ")";
        
        try {
            int result = DBManager.updateQuery(conn, query);
            return result > 0;
        } catch (Exception ex) {
            System.out.println("Error adding librarian: " + ex.getMessage());
            return false;
        } finally {
            DBManager.closeCon(conn);
        }
    }
    
    public boolean updateLibrarian() {
        Connection conn = DBManager.openCon();
        if (conn == null) {
            return false;
        }
        
        String query = "UPDATE LIBRARIAN SET NAME = '" + this.name 
                + "', EMAIL = '" + this.email 
                + "', PASSWORD = '" + this.password 
                + "', STATUS = " + (this.status == LibrarianController.Status.ACTIVE ? "TRUE" : "FALSE") 
                + " WHERE ID = " + this.userID;
        
        try {
            int result = DBManager.updateQuery(conn, query);
            return result > 0;
        } catch (Exception ex) {
            System.out.println("Error updating librarian: " + ex.getMessage());
            return false;
        } finally {
            DBManager.closeCon(conn);
        }
    }
    
    public boolean deleteLibrarian() {
        Connection conn = DBManager.openCon();
        if (conn == null) {
            return false;
        }
        
        String query = "DELETE FROM LIBRARIAN WHERE ID = " + this.userID;
        
        try {
            int result = DBManager.updateQuery(conn, query);
            return result > 0;
        } catch (Exception ex) {
            System.out.println("Error deleting librarian: " + ex.getMessage());
            return false;
        } finally {
            DBManager.closeCon(conn);
        }
    }
    
    public static Librarian getLibrarianById(int id) {
        Connection conn = DBManager.openCon();
        if (conn == null) {
            return null;
        }
        
        String query = "SELECT * FROM LIBRARIAN WHERE ID = " + id;
        try {
            ResultSet rs = DBManager.query(conn, query);
            if (rs != null && rs.next()) {
                String name = rs.getString("NAME");
                String email = rs.getString("EMAIL");
                String password = rs.getString("PASSWORD");
                boolean isActive = rs.getBoolean("STATUS");
                LibrarianController.Status status = isActive ? 
                    LibrarianController.Status.ACTIVE : 
                    LibrarianController.Status.DISABLED;
                
                return new Librarian(id, name, email, password, "librarian", status);
            }
        } catch (SQLException ex) {
            System.out.println("Error fetching librarian: " + ex.getMessage());
        } finally {
            DBManager.closeCon(conn);
        }
        
        return null;
    }
}
