
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
/**
 *
 * @author Omayr
 */
public class ManageMaterial extends javax.swing.JFrame {

    private DefaultTableModel tableModel;
    private Book selectedBook;

    /**
     * Creates new form ManageMaterial
     */
    public ManageMaterial() {
        initComponents();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setupTable();
        loadBooks();
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
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        confirmBorrow = new javax.swing.JButton();
        cancelBorrow = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(51, 153, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Manage Material");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(47, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(37, 37, 37))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Available Material", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 18))); // NOI18N

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ISBN", "Book Name", "Author", "Category", "Location"
            }
        ));
        jTable2.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(jTable2);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 308, Short.MAX_VALUE)
        );

        confirmBorrow.setBackground(new java.awt.Color(51, 153, 255));
        confirmBorrow.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        confirmBorrow.setForeground(new java.awt.Color(255, 255, 255));
        confirmBorrow.setText("Confirm");
        confirmBorrow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmBorrowActionPerformed(evt);
            }
        });

        cancelBorrow.setBackground(new java.awt.Color(51, 153, 255));
        cancelBorrow.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cancelBorrow.setForeground(new java.awt.Color(255, 255, 255));
        cancelBorrow.setText("Cancel");
        cancelBorrow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelBorrowActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(confirmBorrow, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(cancelBorrow, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(confirmBorrow, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cancelBorrow, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        jButton1.setBackground(new java.awt.Color(51, 153, 255));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/add.png"))); // NOI18N
        jButton1.setText("Add Book");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    jButton1ActionPerformed(evt);
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });

        jButton2.setBackground(new java.awt.Color(51, 153, 255));
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/refresh.png"))); // NOI18N
        jButton2.setText("Update Book");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    jButton2ActionPerformed(evt);
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });

        jButton3.setBackground(new java.awt.Color(51, 153, 255));
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/delete.png"))); // NOI18N
        jButton3.setText("Delete Book");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    jButton3ActionPerformed(evt);
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(173, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54)
                .addComponent(jButton2)
                .addGap(54, 54, 54)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(173, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(40, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void confirmBorrowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmBorrowActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_confirmBorrowActionPerformed

    private void cancelBorrowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelBorrowActionPerformed
        this.dispose();
    }//GEN-LAST:event_cancelBorrowActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) throws SQLException {//GEN-FIRST:event_jButton1ActionPerformed
        addBook();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) throws SQLException {//GEN-FIRST:event_jButton2ActionPerformed
        updateBook();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) throws SQLException {//GEN-FIRST:event_jButton3ActionPerformed
        deleteBook();
    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(ManageMaterial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManageMaterial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManageMaterial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManageMaterial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ManageMaterial().setVisible(true);
            }
        });
    }

    private void setupTable() {
        tableModel = (DefaultTableModel) jTable2.getModel();

        tableModel.setRowCount(0);

        String[] columnNames = {"ISBN", "Book Title", "Author 1", "Author 2", "Category", "Status", "Location"};
        tableModel.setColumnIdentifiers(columnNames);
    }

    private void loadBooks() {

        tableModel.setRowCount(0);

        Connection conn = null;
        try {
            conn = DBManager.openCon();
            if (conn != null) {
                String query = "SELECT * FROM BOOK";

                ResultSet rs = DBManager.query(conn, query);

                while (rs != null && rs.next()) {

                    String isbn = rs.getString("ISBN");
                    String title = rs.getString("TITLE");
                    Boolean status = rs.getBoolean("STATUS");
                    String category = rs.getString("CATEGORY");

                    int author1 = rs.getInt("AUTHOR1_ID");
                    String author2 = rs.getString("AUTHOR2_ID");

                    int location = rs.getInt("LOCATION_ID");

                    tableModel.addRow(new Object[]{
                        isbn, title, author1, author2, category, status, location
                    });
                }

                if (tableModel.getRowCount() == 0) {
                    tableModel.addRow(new Object[]{"No books found", "", "", "", "", ""});
                }
            }
        } catch (SQLException e) {
            System.out.println("Error loading books: " + e.getMessage());
            JOptionPane.showMessageDialog(this, "Error loading books: " + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            DBManager.closeCon(conn);
        }
    }

    private void addBook() throws SQLException {

        String isbn = JOptionPane.showInputDialog(this, "Enter ISBN:", "Add New Book", JOptionPane.QUESTION_MESSAGE);
        if (isbn == null || isbn.trim().isEmpty()) {
            return;
        }

        if (isbnExists(isbn)) {
            JOptionPane.showMessageDialog(this, "A book with this ISBN already exists.",
                    "Duplicate ISBN", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String title = JOptionPane.showInputDialog(this, "Enter Book Title:", "Add New Book", JOptionPane.QUESTION_MESSAGE);
        if (title == null || title.trim().isEmpty()) {
            return;
        }

        String category = JOptionPane.showInputDialog(this, "Enter Category:", "Add New Book", JOptionPane.QUESTION_MESSAGE);
        if (category == null) {
            return;
        }

        String author1 = JOptionPane.showInputDialog(this, "Enter Primary Author:", "Add New Book", JOptionPane.QUESTION_MESSAGE);
        if (author1 == null) {
            return;
        }

        String author2 = JOptionPane.showInputDialog(this, "Enter Secondary Author (optional):", "Add New Book", JOptionPane.QUESTION_MESSAGE);

        int author1Id = Integer.parseInt(author1);
        String author2Id = author2;

        int locationId = createLocation();

        Connection conn = null;
        try {
            conn = DBManager.openCon();
            if (conn != null) {

                String insertQuery = "INSERT INTO BOOK (ISBN, TITLE, STATUS, CATEGORY, AUTHOR1_ID, AUTHOR2_ID, LOCATION_ID) "
                        + "VALUES ('" + isbn + "', '" + title + "', 'true', '" + category + "', "
                        + author1Id + ", '" + author2Id + "', " + locationId + ")";
                System.out.println(insertQuery);

                int result = DBManager.updateQuery(conn, insertQuery);

                if (result > 0) {
                    JOptionPane.showMessageDialog(this, "Book added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    loadBooks();
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to add the book.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } finally {
            DBManager.closeCon(conn);
        }
    }

    private boolean isbnExists(String isbn) {
        Connection conn = null;
        try {
            conn = DBManager.openCon();
            if (conn != null) {
                String query = "SELECT COUNT(*) AS COUNT FROM BOOK WHERE ISBN = '" + isbn + "'";
                ResultSet rs = DBManager.query(conn, query);

                if (rs != null && rs.next()) {
                    return rs.getInt("COUNT") > 0;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error checking ISBN: " + e.getMessage());
        } finally {
            DBManager.closeCon(conn);
        }
        return false;
    }

    private int getOrCreateAuthor(String authorName) {
        if (authorName == null || authorName.trim().isEmpty()) {
            return 0;
        }

        Connection conn = null;
        try {
            conn = DBManager.openCon();
            if (conn != null) {

                String query = "SELECT AUTHOR_ID FROM AUTHOR WHERE NAME = '" + authorName + "'";
                ResultSet rs = DBManager.query(conn, query);

                if (rs != null && rs.next()) {
                    return rs.getInt("AUTHOR_ID");
                }

                String bio = "";
                
                String lastIdQuery = "SELECT MAX(AUTHOR_ID) AS LAST_ID FROM AUTHOR";
                ResultSet lastIdResult = DBManager.query(conn, lastIdQuery);
                int lastId = 0;
                if (lastIdResult != null && lastIdResult.next()) {
                    lastId = lastIdResult.getInt("LAST_ID");
                }
                
                int nextId = lastId + 1;
                
                String insertQuery = "INSERT INTO AUTHOR (AUTHOR_ID, NAME, BIO) VALUES (" + nextId + "'" + authorName + "', '" + bio + "')";
                DBManager.updateQuery(conn, insertQuery);

                rs = DBManager.query(conn, "SELECT MAX(AUTHOR_ID) AS AUTHOR_ID FROM AUTHOR");
                if (rs != null && rs.next()) {
                    return rs.getInt("AUTHOR_ID");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error with author: " + e.getMessage());
        } finally {
            DBManager.closeCon(conn);
        }
        return 0;
    }

    private int createLocation() {

        String floorStr = JOptionPane.showInputDialog(this, "Enter Floor Number (1-5):", "Book Location", JOptionPane.QUESTION_MESSAGE);
        if (floorStr == null) {
            return 0;
        }

        int floor;
        try {
            floor = Integer.parseInt(floorStr);
            if (floor < 1 || floor > 5) {
                JOptionPane.showMessageDialog(this, "Floor must be between 1 and 5.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                return 0;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number for floor.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            return 0;
        }

        String section = JOptionPane.showInputDialog(this, "Enter Section (e.g., A, B, Science, Fiction):", "Book Location", JOptionPane.QUESTION_MESSAGE);
        if (section == null || section.trim().isEmpty()) {
            return 0;
        }

        String shelf = JOptionPane.showInputDialog(this, "Enter Shelf Number or ID:", "Book Location", JOptionPane.QUESTION_MESSAGE);
        if (shelf == null || shelf.trim().isEmpty()) {
            return 0;
        }

        String rowStr = JOptionPane.showInputDialog(this, "Enter Row Number:", "Book Location", JOptionPane.QUESTION_MESSAGE);
        if (rowStr == null) {
            return 0;
        }

        float row;
        try {
            row = Float.parseFloat(rowStr);
            if (row <= 0) {
                JOptionPane.showMessageDialog(this, "Row must be a positive number.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                return 0;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number for row.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            return 0;
        }

        Connection conn = null;
        try {
            conn = DBManager.openCon();
            if (conn != null) {
                String insertQuery = "INSERT INTO LOCATION (FLOOR, SECTION, SHELF, ROW) "
                        + "VALUES (" + floor + ", '" + section + "', '" + shelf + "', " + row + ")";

                DBManager.updateQuery(conn, insertQuery);

                ResultSet rs = DBManager.query(conn, "SELECT MAX(LOCATION_ID) AS LOCATION_ID FROM LOCATION");
                if (rs != null && rs.next()) {
                    return rs.getInt("LOCATION_ID");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error creating location: " + e.getMessage());
            JOptionPane.showMessageDialog(this, "Error creating location: " + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            DBManager.closeCon(conn);
        }
        return 0;
    }

    private void updateBook() throws SQLException {
        int selectedRow = jTable2.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Please select a book to update.",
                    "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String isbn = (String) jTable2.getValueAt(selectedRow, 0);
        String currentTitle = (String) jTable2.getValueAt(selectedRow, 1);
        String currentCategory = (String) jTable2.getValueAt(selectedRow, 3);
        String currentStatus = (String) jTable2.getValueAt(selectedRow, 4);

        String newTitle = JOptionPane.showInputDialog(this, "Enter Updated Title:", currentTitle);
        if (newTitle == null) {
            return;
        }

        String newCategory = JOptionPane.showInputDialog(this, "Enter Updated Category:", currentCategory);
        if (newCategory == null) {
            return;
        }

        String[] statusOptions = {"Available", "Unavailable"};
        String newStatus = (String) JOptionPane.showInputDialog(
                this,
                "Select Book Status:",
                "Update Book Status",
                JOptionPane.QUESTION_MESSAGE,
                null,
                statusOptions,
                currentStatus);

        if (newStatus == null) {
            return;
        }
        
        Boolean updatedStatus = newStatus == "Available"? true : false;

        Connection conn = null;
        try {
            conn = DBManager.openCon();
            if (conn != null) {
                String updateQuery = "UPDATE BOOK SET TITLE = '" + newTitle + "', "
                        + "CATEGORY = '" + newCategory + "', "
                        + "STATUS = " + updatedStatus + " "
                        + "WHERE ISBN = '" + isbn + "'";

                int result = DBManager.updateQuery(conn, updateQuery);

                if (result > 0) {
                    JOptionPane.showMessageDialog(this, "Book updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    loadBooks();
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to update the book.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } finally {
            DBManager.closeCon(conn);
        }
    }

    private void deleteBook() throws SQLException {
        int selectedRow = jTable2.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Please select a book to delete.",
                    "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String isbn = (String) jTable2.getValueAt(selectedRow, 0);
        String title = (String) jTable2.getValueAt(selectedRow, 1);

        if (isBookBorrowed(isbn)) {
            JOptionPane.showMessageDialog(this, "This book is currently borrowed and cannot be deleted.",
                    "Cannot Delete", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to delete the book '" + title + "' (ISBN: " + isbn + ")?",
                "Confirm Deletion",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);

        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        Connection conn = null;
        try {
            conn = DBManager.openCon();
            if (conn != null) {
                String deleteQuery = "DELETE FROM BOOK WHERE ISBN = '" + isbn + "'";

                int result = DBManager.updateQuery(conn, deleteQuery);

                if (result > 0) {
                    JOptionPane.showMessageDialog(this, "Book deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    loadBooks();
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to delete the book.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } finally {
            DBManager.closeCon(conn);
        }
    }

    private boolean isBookBorrowed(String isbn) {
        Connection conn = null;
        try {
            conn = DBManager.openCon();
            if (conn != null) {
                String query = "SELECT COUNT(*) AS COUNT FROM BORROW "
                        + "WHERE BOOK_ID = '" + isbn + "' AND RETURN_DATE IS NULL";

                ResultSet rs = DBManager.query(conn, query);

                if (rs != null && rs.next()) {
                    return rs.getInt("COUNT") > 0;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error checking if book is borrowed: " + e.getMessage());
        } finally {
            DBManager.closeCon(conn);
        }
        return false;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelBorrow;
    private javax.swing.JButton confirmBorrow;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable2;
    // End of variables declaration//GEN-END:variables
}
