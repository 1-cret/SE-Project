
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
public class AdminController {

    public enum Status {
        ACTIVE, DISABLED
    }

    Admin admin;
    private int userID;
    private String name;
    private String email;
    private String password;
    private String role;
    private Status status;

    public AdminController() {
        this.userID = 0;
        this.name = "";
        this.email = "";
        this.password = "";
        this.role = "admin";
        this.status = Status.ACTIVE;
        this.admin = new Admin(this.userID, this.name, this.email, this.password, this.role, this.status);
    }

    public AdminController(String email, String password) {
        this.email = email;
        this.password = password;
        this.role = "admin";
        this.admin = new Admin(0, null, email, password, null, null);
    }

    public AdminController(int userID, String name, String email, String password, Status status) {
        this.userID = userID;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = "admin";
        this.status = status;
        this.admin = new Admin(this.userID, this.name, this.email, this.password, this.role, this.status);
    }

    public AdminController login() {
        if (admin.login() != null) {
            return this;
        }
        return null;
    }

    public ArrayList<Admin> getAllAdmins() {
        ArrayList<Admin> admins = new ArrayList<>();
        Connection conn = DBManager.openCon();

        if (conn == null) {
            return admins;
        }

        String query = "SELECT * FROM ADMIN";
        try {
            ResultSet rs = DBManager.query(conn, query);
            while (rs != null && rs.next()) {
                int id = rs.getInt("ID");
                String adminName = rs.getString("NAME");
                String adminEmail = rs.getString("EMAIL");
                String adminPassword = rs.getString("PASSWORD");
                String adminRole = "admin";
                boolean isActive = rs.getBoolean("STATUS");
                Status adminStatus = isActive ? Status.ACTIVE : Status.DISABLED;

                Admin adminObj = new Admin(id, adminName, adminEmail, adminPassword, adminRole, adminStatus);
                admins.add(adminObj);
            }
        } catch (SQLException ex) {
            System.out.println("Error fetching admins: " + ex.getMessage());
        } finally {
            DBManager.closeCon(conn);
        }

        return admins;
    }

    public boolean addAdmin(String name, String email, String password, Status status) throws SQLException {
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
        int nextID = lastId + 1;
        boolean isActive = (status == Status.ACTIVE);
        String query = "INSERT INTO ADMIN (ID,NAME, EMAIL, PASSWORD, STATUS) VALUES ("
                + nextID + ", '" + name + "', '" + email + "', '" + password + "', " + true + ")";
        System.out.println(query);
        try {
            int result = DBManager.updateQuery(conn, query);
            return result > 0;
        } finally {
            DBManager.closeCon(conn);
        }
    }

    public boolean updateAdmin(int id, String name, String email, String password, Status status) {
        Connection conn = DBManager.openCon();

        if (conn == null) {
            return false;
        }

        boolean isActive = (status == Status.ACTIVE);
        String query = "UPDATE ADMIN SET NAME = '" + name + "', EMAIL = '" + email
                + "', PASSWORD = '" + password + "', STATUS = " + isActive
                + " WHERE ID = " + id;

        try {
            int result = DBManager.updateQuery(conn, query);
            return result > 0;
        } finally {
            DBManager.closeCon(conn);
        }
    }

    public boolean deleteAdmin(int id) {
        Connection conn = DBManager.openCon();

        if (conn == null) {
            return false;
        }

        String query = "DELETE FROM ADMIN WHERE ID = " + id;

        try {
            int result = DBManager.updateQuery(conn, query);
            return result > 0;
        } finally {
            DBManager.closeCon(conn);
        }
    }

    public int[] getSystemStats() {
        int[] stats = new int[3];
        Connection conn = DBManager.openCon();

        if (conn == null) {
            return stats;
        }

        try {

            String returnsQuery = "SELECT COUNT(*) AS count FROM BORROW WHERE RETURN_DATE IS NOT NULL";
            ResultSet returnsRs = DBManager.query(conn, returnsQuery);
            if (returnsRs != null && returnsRs.next()) {
                stats[0] = returnsRs.getInt("count");
            }

            String studentsQuery = "SELECT COUNT(*) AS total_students FROM STUDENT";
            String librariansQuery = "SELECT COUNT(*) AS total_librarians FROM LIBRARIAN";
            String adminsQuery = "SELECT COUNT(*) AS total_admins FROM ADMIN";
            ResultSet studentsRs = DBManager.query(conn, studentsQuery);
            ResultSet librariansRs = DBManager.query(conn, librariansQuery);
            ResultSet adminsRs = DBManager.query(conn, adminsQuery);
            if (studentsRs != null && studentsRs.next()) {
                stats[1] = studentsRs.getInt("total_students");
            }
            if (librariansRs != null && librariansRs.next()) {
                stats[1] += librariansRs.getInt("total_librarians");
            }
            if (adminsRs != null && adminsRs.next()) {
                stats[1] += adminsRs.getInt("total_admins");
            }

            String booksQuery = "SELECT COUNT(*) AS count FROM BOOK";
            ResultSet booksRs = DBManager.query(conn, booksQuery);
            if (booksRs != null && booksRs.next()) {
                stats[2] = booksRs.getInt("count");
            }
        } catch (SQLException ex) {
            System.out.println("Error fetching system stats: " + ex.getMessage());
        } finally {
            DBManager.closeCon(conn);
        }

        return stats;
    }
}
