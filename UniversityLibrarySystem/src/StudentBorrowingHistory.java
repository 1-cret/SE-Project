import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

public class StudentBorrowingHistory extends javax.swing.JFrame {

    private int studentID;
    private DefaultTableModel tableModel;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public StudentBorrowingHistory() {
        initComponents();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public StudentBorrowingHistory(int studentID) {
        initComponents();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.studentID = studentID;
        setupTable();
        loadBorrowingHistory();
    }

    public StudentBorrowingHistory(Student student) {
        initComponents();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.studentID = student.getUserID();
        setupTable();
        loadBorrowingHistory();
    }

    private void setupTable() {
        String[] columnNames = {"Borrow ID", "Book Title", "Borrow Date", "Due Date", "Return Date", "Status", "Fine Amount"};
        tableModel = new DefaultTableModel(columnNames, 0);
        jTable1.setModel(tableModel);
    }

    private void loadBorrowingHistory() {
        Connection conn = DBManager.openCon();
        if (conn == null) {
            System.out.println("Database connection failed.");
            return;
        }

        try {
            String query = "SELECT b.BORROW_ID, bk.BOOK_TITLE, b.BORROW_DATE, b.DUE_DATE, " +
                           "b.RETURN_DATE, b.FINE_AMOUNT " +
                           "FROM BORROW b JOIN BOOK bk ON b.BOOK_ID = bk.ISBN " +
                           "WHERE b.STUDENT_ID = " + studentID + " " +
                           "ORDER BY b.BORROW_DATE DESC";

            ResultSet rs = DBManager.query(conn, query);
            tableModel.setRowCount(0);

            while (rs != null && rs.next()) {
                int borrowId = rs.getInt("BORROW_ID");
                String bookTitle = rs.getString("BOOK_TITLE");
                String borrowDate = formatDate(rs.getDate("BORROW_DATE"));
                String dueDate = formatDate(rs.getDate("DUE_DATE"));
                String returnDate = formatDate(rs.getDate("RETURN_DATE"));
                String status = determineStatus(rs.getDate("RETURN_DATE"), rs.getDate("DUE_DATE"));
                double fineAmount = rs.getDouble("FINE_AMOUNT");

                tableModel.addRow(new Object[]{
                    borrowId, bookTitle, borrowDate, dueDate, returnDate, status, fineAmount
                });
            }
        } catch (SQLException ex) {
            System.out.println("Error loading borrowing history: " + ex.getMessage());
        } finally {
            DBManager.closeCon(conn);
        }
    }

    private String formatDate(Date date) {
        return (date == null) ? "" : dateFormat.format(date);
    }

    private String determineStatus(Date returnDate, Date dueDate) {
        if (returnDate != null) return "Returned";
        Date currentDate = new Date();
        if (currentDate.after(dueDate)) return "Overdue";
        return "Active";
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(51, 153, 255));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36));
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Your Borrowing History");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup().addGap(30, 30, 30).addComponent(jLabel1).addContainerGap(400, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup().addGap(30, 30, 30).addComponent(jLabel1).addContainerGap(30, Short.MAX_VALUE))
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object[][] {}, new String[] {}
        ));
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup().addGap(20, 20, 20).addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 900, javax.swing.GroupLayout.PREFERRED_SIZE).addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup().addGap(20, 20, 20).addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE).addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)).addContainerGap()));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addContainerGap()));

        pack();
    }

    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
}
