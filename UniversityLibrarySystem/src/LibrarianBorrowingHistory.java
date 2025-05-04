import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JFrame;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
/**
 *
 * @author Omayr
 */
public class LibrarianBorrowingHistory extends javax.swing.JFrame {

    private DefaultTableModel tableModel;
    
    /**
     * Creates new form LibrarianBorrowingHistory
     */
    public LibrarianBorrowingHistory() {
        initComponents();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setupTable();
        loadBorrowingRecords();
    }
    
    /**
     * Set up the table model
     */
    private void setupTable() {
        tableModel = (DefaultTableModel) jTable1.getModel();
        // Clear existing rows
        tableModel.setRowCount(0);
    }
    
    /**
     * Load all borrowing records from the database
     */
    private void loadBorrowingRecords() {
        // Clear existing rows
        tableModel.setRowCount(0);
        
        Connection conn = null;
        try {
            conn = DBManager.openCon();
            if (conn != null) {
                // Query to get all borrowing records with book and student information
                String query = "SELECT b.BORROW_ID, b.BORROW_DATE, b.DUE_DATE, b.RETURN_DATE, " +
                        "b.RENEWAL_COUNT, b.FINE_AMOUNT, b.BORROW_STATUS, " +
                        "s.ID AS STUDENT_ID, s.NAME AS STUDENT_NAME, " +
                        "bk.ISBN, bk.BOOK_TITLE " +
                        "FROM BORROW b " +
                        "JOIN STUDENT s ON b.STUDENT_ID = s.ID " +
                        "JOIN BOOK bk ON b.BOOK_ID = bk.ISBN " +
                        "ORDER BY b.BORROW_DATE DESC";
                
                ResultSet rs = DBManager.query(conn, query);
                
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                
                while (rs != null && rs.next()) {
                    // Get data from the result set
                    int borrowId = rs.getInt("BORROW_ID");
                    Date borrowDate = rs.getDate("BORROW_DATE");
                    Date dueDate = rs.getDate("DUE_DATE");
                    Date returnDate = rs.getDate("RETURN_DATE");
                    int renewalCount = rs.getInt("RENEWAL_COUNT");
                    double fineAmount = rs.getDouble("FINE_AMOUNT");
                    String status = rs.getString("BORROW_STATUS");
                    
                    String studentId = rs.getString("STUDENT_ID");
                    String studentName = rs.getString("STUDENT_NAME");
                    
                    String isbn = rs.getString("ISBN");
                    String bookTitle = rs.getString("BOOK_TITLE");
                    
                    // Format dates as strings
                    String borrowDateStr = borrowDate != null ? dateFormat.format(borrowDate) : "";
                    String dueDateStr = dueDate != null ? dateFormat.format(dueDate) : "";
                    String returnDateStr = returnDate != null ? dateFormat.format(returnDate) : "";
                    
                    // Add row to the table
                    tableModel.addRow(new Object[]{
                        borrowId,
                        isbn,
                        bookTitle,
                        studentId,
                        studentName,
                        borrowDateStr,
                        dueDateStr,
                        returnDateStr,
                        renewalCount,
                        fineAmount,
                        status
                    });
                }
            }
        } catch (SQLException e) {
            System.out.println("Error loading borrowing records: " + e.getMessage());
            JOptionPane.showMessageDialog(this, "Error loading borrowing records: " + e.getMessage(), 
                    "Database Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            DBManager.closeCon(conn);
        }
    }
    
    /**
     * Search for borrowing records based on search criteria
     */
    private void searchBorrowings() {
        String searchText = searchTextField.getText().trim();
        if (searchText.isEmpty()) {
            loadBorrowingRecords(); // If search box is empty, load all records
            return;
        }
        
        // Clear existing rows
        tableModel.setRowCount(0);
        
        Connection conn = null;
        try {
            conn = DBManager.openCon();
            if (conn != null) {
                // Query to search borrowing records
                String query = "SELECT b.BORROW_ID, b.BORROW_DATE, b.DUE_DATE, b.RETURN_DATE, " +
                        "b.RENEWAL_COUNT, b.FINE_AMOUNT, b.BORROW_STATUS, " +
                        "s.ID AS STUDENT_ID, s.NAME AS STUDENT_NAME, " +
                        "bk.ISBN, bk.BOOK_TITLE " +
                        "FROM BORROW b " +
                        "JOIN STUDENT s ON b.STUDENT_ID = s.ID " +
                        "JOIN BOOK bk ON b.BOOK_ID = bk.ISBN " +
                        "WHERE bk.BOOK_TITLE LIKE '%" + searchText + "%' " +
                        "OR bk.ISBN LIKE '%" + searchText + "%' " +
                        "OR s.NAME LIKE '%" + searchText + "%' " +
                        "OR s.ID LIKE '%" + searchText + "%' " +
                        "OR b.BORROW_STATUS LIKE '%" + searchText + "%' " +
                        "ORDER BY b.BORROW_DATE DESC";
                
                ResultSet rs = DBManager.query(conn, query);
                
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                
                while (rs != null && rs.next()) {
                    // Get data from the result set
                    int borrowId = rs.getInt("BORROW_ID");
                    Date borrowDate = rs.getDate("BORROW_DATE");
                    Date dueDate = rs.getDate("DUE_DATE");
                    Date returnDate = rs.getDate("RETURN_DATE");
                    int renewalCount = rs.getInt("RENEWAL_COUNT");
                    double fineAmount = rs.getDouble("FINE_AMOUNT");
                    String status = rs.getString("BORROW_STATUS");
                    
                    String studentId = rs.getString("STUDENT_ID");
                    String studentName = rs.getString("STUDENT_NAME");
                    
                    String isbn = rs.getString("ISBN");
                    String bookTitle = rs.getString("BOOK_TITLE");
                    
                    // Format dates as strings
                    String borrowDateStr = borrowDate != null ? dateFormat.format(borrowDate) : "";
                    String dueDateStr = dueDate != null ? dateFormat.format(dueDate) : "";
                    String returnDateStr = returnDate != null ? dateFormat.format(returnDate) : "";
                    
                    // Add row to the table
                    tableModel.addRow(new Object[]{
                        borrowId,
                        isbn,
                        bookTitle,
                        studentId,
                        studentName,
                        borrowDateStr,
                        dueDateStr,
                        returnDateStr,
                        renewalCount,
                        fineAmount,
                        status
                    });
                }
            }
        } catch (SQLException e) {
            System.out.println("Error searching borrowing records: " + e.getMessage());
            JOptionPane.showMessageDialog(this, "Error searching borrowing records: " + e.getMessage(), 
                    "Database Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            DBManager.closeCon(conn);
        }
    }
    
    /**
     * Handle return book operation
     */
    private void returnBook() {
        int selectedRow = jTable1.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Please select a borrowing record to update.", 
                    "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Get the borrow ID
        int borrowId = (int) jTable1.getValueAt(selectedRow, 0);
        String status = (String) jTable1.getValueAt(selectedRow, 10);
        
        // Check if the book is already returned
        if (status.equalsIgnoreCase("Returned")) {
            JOptionPane.showMessageDialog(this, "This book has already been returned.", 
                    "Already Returned", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        // Ask for confirmation
        int confirm = JOptionPane.showConfirmDialog(this, 
                "Are you sure you want to mark this book as returned?", 
                "Confirm Return", 
                JOptionPane.YES_NO_OPTION);
        
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }
        
        // Get book ISBN to update book status
        String isbn = (String) jTable1.getValueAt(selectedRow, 1);
        
        Connection conn = null;
        try {
            conn = DBManager.openCon();
            if (conn != null) {
                // Start a transaction
                conn.setAutoCommit(false);
                
                // Update the borrow record to mark as returned
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String currentDate = dateFormat.format(new Date());
                
                String updateBorrowQuery = "UPDATE BORROW SET RETURN_DATE = '" + currentDate + "', " +
                        "BORROW_STATUS = 'Returned' WHERE BORROW_ID = " + borrowId;
                
                int borrowUpdateResult = DBManager.updateQuery(conn, updateBorrowQuery);
                
                // Update the book status to Available
                String updateBookQuery = "UPDATE BOOK SET STATUS = 'Available' WHERE ISBN = '" + isbn + "'";
                int bookUpdateResult = DBManager.updateQuery(conn, updateBookQuery);
                
                if (borrowUpdateResult > 0 && bookUpdateResult > 0) {
                    conn.commit();
                    JOptionPane.showMessageDialog(this, "Book has been returned successfully!");
                    loadBorrowingRecords(); // Refresh the table
                } else {
                    conn.rollback();
                    JOptionPane.showMessageDialog(this, "Failed to return the book.", 
                            "Update Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (SQLException e) {
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException ex) {
                System.out.println("Rollback error: " + ex.getMessage());
            }
            System.out.println("Error returning book: " + e.getMessage());
            JOptionPane.showMessageDialog(this, "Error returning book: " + e.getMessage(), 
                    "Database Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (conn != null) {
                    conn.setAutoCommit(true);
                    DBManager.closeCon(conn);
                }
            } catch (SQLException e) {
                System.out.println("Error resetting auto-commit: " + e.getMessage());
            }
        }
    }

    private void renewBorrowBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_renewBorrowBtnActionPerformed
        renewBorrowing();
    }//GEN-LAST:event_renewBorrowBtnActionPerformed
    
    /**
     * Renew a borrowing period for a selected book
     */
    private void renewBorrowing() {
        int selectedRow = jTable1.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Please select a borrowing record to renew.", 
                    "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Get the borrow ID and other details
        int borrowId = (int) jTable1.getValueAt(selectedRow, 0);
        String status = (String) jTable1.getValueAt(selectedRow, 10);
        int renewalCount = (int) jTable1.getValueAt(selectedRow, 8);
        String bookTitle = (String) jTable1.getValueAt(selectedRow, 2);
        
        // Check if the book is already returned
        if (status.equalsIgnoreCase("Returned")) {
            JOptionPane.showMessageDialog(this, "Cannot renew a book that has already been returned.", 
                    "Already Returned", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Check if renewal limit is reached (assuming max is 3)
        if (renewalCount >= 3) {
            JOptionPane.showMessageDialog(this, "This book has already been renewed the maximum number of times (3).", 
                    "Renewal Limit Reached", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Ask for confirmation
        int confirm = JOptionPane.showConfirmDialog(this, 
                "Renew borrowing period for book '" + bookTitle + "'?", 
                "Confirm Renewal", 
                JOptionPane.YES_NO_OPTION);
        
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }
        
        // Calculate new due date (14 days from now)
        java.util.Date currentDate = new java.util.Date();
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.add(java.util.Calendar.DAY_OF_MONTH, 14);
        java.util.Date newDueDate = calendar.getTime();
        
        // Format for SQL
        java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd");
        String formattedNewDueDate = dateFormat.format(newDueDate);
        
        // Update the borrow record
        Connection conn = null;
        try {
            conn = DBManager.openCon();
            if (conn != null) {
                // Update due date and increment renewal count
                String updateQuery = "UPDATE BORROW SET DUE_DATE = '" + formattedNewDueDate + "', " +
                        "RENEWAL_COUNT = " + (renewalCount + 1) + ", " +
                        "BORROW_STATUS = 'Borrowed' " + // Reset to 'Borrowed' in case it was 'Overdue'
                        "WHERE BORROW_ID = " + borrowId;
                
                int result = DBManager.updateQuery(conn, updateQuery);
                
                if (result > 0) {
                    JOptionPane.showMessageDialog(this, "Borrowing period renewed successfully!\nNew due date: " + formattedNewDueDate, 
                            "Renewal Successful", JOptionPane.INFORMATION_MESSAGE);
                    loadBorrowingRecords(); // Refresh the data
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to renew borrowing period.", 
                            "Renewal Failed", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error renewing borrowing: " + e.getMessage());
            JOptionPane.showMessageDialog(this, "Error renewing borrowing: " + e.getMessage(), 
                    "Database Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            DBManager.closeCon(conn);
        }
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
        jPanel3 = new javax.swing.JPanel();
        renewBorrowBtn = new javax.swing.JButton();
        searchTextField = new javax.swing.JTextField();
        searchButton = new javax.swing.JButton();
        returnBookBtn = new javax.swing.JButton();
        refreshBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(51, 153, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Borrowing History");

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

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "BorrowID", "Book Name", "Borrow Date", "Due Date", "Return Date", "Status", "Renewal Count", "Fine Amount", "Student ID"
            }
        ));
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 953, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 367, Short.MAX_VALUE))
        );

        renewBorrowBtn.setBackground(new java.awt.Color(51, 153, 255));
        renewBorrowBtn.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        renewBorrowBtn.setForeground(new java.awt.Color(255, 255, 255));
        renewBorrowBtn.setText("Renew Borrowing");
        renewBorrowBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                renewBorrowBtnActionPerformed(evt);
            }
        });

        searchButton.setBackground(new java.awt.Color(51, 153, 255));
        searchButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        searchButton.setForeground(new java.awt.Color(255, 255, 255));
        searchButton.setText("Search");
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });

        returnBookBtn.setBackground(new java.awt.Color(51, 153, 255));
        returnBookBtn.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        returnBookBtn.setForeground(new java.awt.Color(255, 255, 255));
        returnBookBtn.setText("Return Book");
        returnBookBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                returnBookBtnActionPerformed(evt);
            }
        });

        refreshBtn.setBackground(new java.awt.Color(51, 153, 255));
        refreshBtn.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        refreshBtn.setForeground(new java.awt.Color(255, 255, 255));
        refreshBtn.setText("Refresh");
        refreshBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(searchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(searchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(returnBookBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(refreshBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(renewBorrowBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(renewBorrowBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(returnBookBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(refreshBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(42, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
        searchBorrowings();
    }//GEN-LAST:event_searchButtonActionPerformed

    private void returnBookBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_returnBookBtnActionPerformed
        returnBook();
    }//GEN-LAST:event_returnBookBtnActionPerformed

    private void refreshBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshBtnActionPerformed
        loadBorrowingRecords();
    }//GEN-LAST:event_refreshBtnActionPerformed

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
            java.util.logging.Logger.getLogger(LibrarianBorrowingHistory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LibrarianBorrowingHistory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LibrarianBorrowingHistory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LibrarianBorrowingHistory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LibrarianBorrowingHistory().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton renewBorrowBtn;
    private javax.swing.JButton searchButton;
    private javax.swing.JTextField searchTextField;
    private javax.swing.JButton returnBookBtn;
    private javax.swing.JButton refreshBtn;
    // End of variables declaration//GEN-END:variables
}
