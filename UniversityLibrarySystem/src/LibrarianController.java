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
    
    public boolean login() {
        return librarian.login();
    }
}
