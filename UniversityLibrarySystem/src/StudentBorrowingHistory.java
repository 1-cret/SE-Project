import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author Omayr
 */
public class StudentBorrowingHistory extends javax.swing.JFrame {

    private int studentID;
    private DefaultTableModel tableModel;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * Creates new form BorrowingHistory
     */
    public StudentBorrowingHistory() {
        initComponents();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setupTable();
    }
    
    /**
     * Creates new form BorrowingHistory for a specific student
     * @param studentID the ID of the student to show borrowing history for
     */
    public StudentBorrowingHistory(int studentID) {
        initComponents();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.studentID = studentID;
        setupTable();
        loadBorrowingHistory();
    }

    /**
     * Creates new form BorrowingHistory for a specific student
     * @param student the Student object to show borrowing history for
     */
    public StudentBorrowingHistory(Student student) {
        initComponents();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.studentID = student.getUserID();
        setupTable();
        loadBorrowingHistory();
    }
    
    /**
     * Setup table columns and formatting
     */
    private void setupTable() {
        tableModel = (DefaultTableModel) jTable1.getModel();
        // Clear existing data
        tableModel.setRowCount(0);
        
        // Set column headers
        String[] columnNames = {"Borrow ID", "Book Title", "Borrow Date", "Due Date", "Return Date", "Status", "Fine Amount"};
        tableModel.setColumnIdentifiers(columnNames);
        
        // Configure table appearance
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(70);  // Borrow ID
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(200); // Book Title
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(100); // Borrow Date
        jTable1.getColumnModel().getColumn(3).setPreferredWidth(100); // Due Date
        jTable1.getColumnModel().getColumn(4).setPreferredWidth(100); // Return Date
        jTable1.getColumnModel().getColumn(5).setPreferredWidth(80);  // Status
        jTable1.getColumnModel().getColumn(6).setPreferredWidth(80);  // Fine Amount
    }
    
    /**
     * Load borrowing history for the current student from the database
     */
    private void loadBorrowingHistory() {
        Connection conn = DBManager.openCon();
        if (conn == null) {
            System.out.println("Failed to connect to database");
            // Show an error message in the UI
            tableModel.setRowCount(0);
            tableModel.addRow(new Object[]{"Error connecting to database", "", "", "", "", "", ""});
            return;
        }
        
        Statement stmt = null;
        ResultSet rs = null;
        try {
            String query = "SELECT b.BORROW_ID, bk.BOOK_TITLE, b.BORROW_DATE, b.DUE_DATE, " +
                           "b.RETURN_DATE, b.RENEWAL_COUNT, b.FINE_AMOUNT " +
                           "FROM BORROW b JOIN BOOK bk ON b.BOOK_ID = bk.ISBN " +
                           "WHERE b.STUDENT_ID = " + studentID + " " +
                           "ORDER BY b.BORROW_DATE DESC";
            
            System.out.println("Executing query: " + query);
            
            // Get statement directly instead of using DBManager.query
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            
            tableModel.setRowCount(0);
            
            int rowCount = 0;
            while (rs != null && rs.next()) {
                rowCount++;
                int borrowId = rs.getInt("BORROW_ID");
                String bookTitle = rs.getString("BOOK_TITLE");
                
                // Format dates for display
                String borrowDate = formatDate(rs.getDate("BORROW_DATE"));
                String dueDate = formatDate(rs.getDate("DUE_DATE"));
                String returnDate = formatDate(rs.getDate("RETURN_DATE"));
                
                // Determine status based on return date and due date
                String status = determineStatus(rs.getDate("RETURN_DATE"), rs.getDate("DUE_DATE"));
                
                double fineAmount = rs.getDouble("FINE_AMOUNT");
                
                System.out.println("Adding row: ID=" + borrowId + ", Title=" + bookTitle);
                
                // Add row to table
                tableModel.addRow(new Object[]{
                    borrowId, bookTitle, borrowDate, dueDate, returnDate, status, fineAmount
                });
            }
            
            System.out.println("Loaded " + rowCount + " records for student ID: " + studentID);
            
            // If no records were found, add a message
            if (rowCount == 0) {
                tableModel.addRow(new Object[]{"No borrowing records found", "", "", "", "", "", ""});
            }
            
        } catch (SQLException ex) {
            System.out.println("Error loading borrowing history: " + ex.getMessage());
            // Show the error in the table
            tableModel.setRowCount(0);
            tableModel.addRow(new Object[]{"Database error: " + ex.getMessage(), "", "", "", "", "", ""});
        } finally {
            // Close resources properly
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                System.out.println("Error closing resources: " + e.getMessage());
            }
            DBManager.closeCon(conn);
        }
    }
    
    /**
     * Format a date for display, handling null dates
     * @param date the date to format
     * @return formatted date string or empty string if date is null
     */
    private String formatDate(Date date) {
        if (date == null) {
            return "";
        }
        return dateFormat.format(date);
    }
    
    /**
     * Determine the status of a borrowed book
     * @param returnDate the date the book was returned
     * @param dueDate the date the book was due
     * @return status string (Returned, Overdue, Active)
     */
    private String determineStatus(Date returnDate, Date dueDate) {
        if (returnDate != null) {
            return "Returned";
        }
        
        Date currentDate = new Date();
        if (currentDate.after(dueDate)) {
            return "Overdue";
        }
        
        return "Active";
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(51, 153, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Your Borrowing History");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addComponent(jLabel1)
                .addContainerGap(33, Short.MAX_VALUE))
        );

       
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 902, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 439, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(StudentBorrowingHistory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StudentBorrowingHistory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StudentBorrowingHistory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StudentBorrowingHistory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StudentBorrowingHistory().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
