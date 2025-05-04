
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

    public void setBorrowList(ArrayList<Borrow> borrowList) {
        this.borrowList = borrowList;
    }

    public ArrayList<Borrow> getBorrowList() {
        return borrowList;
    }

    public boolean signUp(String name, String email, String password) {
        Connection conn = DBManager.openCon();
            if (conn == null) {
                return false;
            }
        try {
            
            String currID = "SELECT ID FROM STUDENT ORDER BY DESC FETCH FIRST 1 ROWS ONLY";
            ResultSet rs = st.executeQuery(currID);
            int nextID = Integer.parseInt(currID);
            String sql = String.format("INSERT INTO STUDENT (ID, NAME, EMAIL, PASSWORD, STATUS)"
                    + " VALUES ('%d', '%s', '%s', '%s', TRUE)",
                    nextID, name, email, password);
            st.executeUpdate(sql);
            st.close();
            conn.close();
            return true;
        } catch (SQLException ex) {
            System.out.println("Connect failed ! " + ex.getMessage());
            return false;
        }
    }
}
