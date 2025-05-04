
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBManager {

    private static final String URL = "jdbc:derby://localhost:1527/group51";
    private static final String USER = "bue";
    private static final String PASSWORD = "bue";

    public static Connection openCon() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Connection Error: " + e.getMessage());
            return null;
        }
    }

    public static void closeCon(Connection conn) {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println("Close Error: " + e.getMessage());
        }
    }

    public static ResultSet query(Connection conn, String query) {
        try {
            Statement stmt = conn.createStatement();
            return stmt.executeQuery(query);
        } catch (SQLException e) {
            System.out.println("Query Error: " + e.getMessage());
            return null;
        }
    }

    public static int updateQuery(Connection conn, String query) {
        try {
            Statement stmt = conn.createStatement();
            return stmt.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println("Query Error: " + e.getMessage());
            return 0;
        }
    }
}
