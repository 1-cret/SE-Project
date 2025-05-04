import java.util.ArrayList;
import java.sql.Connection; 
import java.sql.DriverManager; 
import java.sql.ResultSet; 
import java.sql.SQLException; 
import java.sql.Statement;
import java.util.List;

/**
 *
 * @author omarh
 */
public class LibrarianController {
    public enum Status {
        ACTIVE, DISABLED
    }
    
    Librarian librarian;
    private int userID;
    private String name;
    private String email;
    private String password;
    private String role;
    private Status status;
    
    // Default constructor
    public LibrarianController() {
        this.userID = 0;
        this.name = "";
        this.email = "";
        this.password = "";
        this.role = "librarian";
        this.status = Status.ACTIVE;
        this.librarian = new Librarian(this.userID, this.name, this.email, this.password, this.role, this.status);
    }
    
    public LibrarianController(String email, String password) {
        this.email = email;
        this.password = password;
        this.role = "librarian";
        this.librarian = new Librarian(0, null, email, password, null, null);
    }
    
    public LibrarianController(int userID, String name, String email, String password, Status status) {
        this.userID = userID;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = "librarian";
        this.status = status;
        this.librarian = new Librarian(this.userID, this.name, this.email, this.password, this.role, this.status);
    }
    
    public LibrarianController login() {
        this.librarian = librarian.login();
        if(librarian != null) {
            return this;
        } else {
            return null;
        }
    }
    
    // Get all librarians
    public List<Librarian> getAllLibrarians() {
        return Librarian.getAllLibrarians();
    }
    
    // Add a new librarian
    public boolean addLibrarian(String name, String email, String password, Status status) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.status = status;
        this.librarian = new Librarian(0, name, email, password, "librarian", status);
        return this.librarian.addLibrarian();
    }
    
    // Update an existing librarian
    public boolean updateLibrarian(int id, String name, String email, String password, Status status) {
        this.userID = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.status = status;
        this.librarian = new Librarian(id, name, email, password, "librarian", status);
        return this.librarian.updateLibrarian();
    }
    
    // Delete a librarian
    public boolean deleteLibrarian(int id) {
        this.librarian = new Librarian(id, "", "", "", "librarian", null);
        return this.librarian.deleteLibrarian();
    }
    
    // Get librarian by ID
    public Librarian getLibrarianById(int id) {
        return Librarian.getLibrarianById(id);
    }
    
    public Librarian getLibrarian() {
        return librarian;
    }
    public void setLibrarian(Librarian librarian) {
        this.librarian = librarian;
    }
}
