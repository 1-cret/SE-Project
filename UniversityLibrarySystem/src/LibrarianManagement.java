import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
/**
 *
 * @author Omayr
 */
public class LibrarianManagement extends javax.swing.JFrame {

    private LibrarianController librarianController;
    private DefaultTableModel tableModel;
    
    /**
     * Creates new form LibrarianManagement
     */
    public LibrarianManagement() {
        initComponents();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.librarianController = new LibrarianController();
        setupTable();
        loadLibrarians();
    }
    
    /**
     * Setup the table model with the appropriate columns
     */
    private void setupTable() {
        tableModel = (DefaultTableModel) jTable1.getModel();
        // Clear existing data
        tableModel.setRowCount(0);
    }
    
    /**
     * Load all librarians from the database
     */
    private void loadLibrarians() {
        // Clear existing data
        tableModel.setRowCount(0);
        
        // Fetch all librarians
        List<Librarian> librarians = librarianController.getAllLibrarians();
        
        // Add rows to the table
        for (Librarian lib : librarians) {
            String status = lib.getStatus() == LibrarianController.Status.ACTIVE ? "Active" : "Disabled";
            tableModel.addRow(new Object[]{
                lib.getUserID(),
                lib.getName(),
                lib.getEmail(),
                status
            });
        }
    }
    
    /**
     * Display an add librarian dialog
     */
    private void showAddLibrarianDialog() throws SQLException {
        // Create input dialog for the new librarian
        String name = JOptionPane.showInputDialog(this, "Enter librarian name:");
        if (name == null || name.trim().isEmpty()) {
            return;
        }
        
        String email = JOptionPane.showInputDialog(this, "Enter librarian email:");
        if (email == null || email.trim().isEmpty()) {
            return;
        }
        
        String password = JOptionPane.showInputDialog(this, "Enter librarian password:");
        if (password == null || password.trim().isEmpty()) {
            return;
        }
        
        int statusChoice = JOptionPane.showConfirmDialog(this, 
                "Is this librarian active?", 
                "Librarian Status", 
                JOptionPane.YES_NO_OPTION);
        LibrarianController.Status status = (statusChoice == JOptionPane.YES_OPTION) ? 
                LibrarianController.Status.ACTIVE : 
                LibrarianController.Status.DISABLED;
        
        // Add the librarian to the database
        boolean success = librarianController.addLibrarian(name, email, password, status);
        
        if (success) {
            JOptionPane.showMessageDialog(this, "Librarian added successfully!");
            loadLibrarians(); // Refresh the table
        } else {
            JOptionPane.showMessageDialog(this, "Failed to add librarian.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Display an update librarian dialog
     */
    private void showUpdateLibrarianDialog() {
        int selectedRow = jTable1.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Please select a librarian to update.", "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Get the selected librarian ID
        int librarianId = (int) jTable1.getValueAt(selectedRow, 0);
        
        // Fetch the librarian from the database
        Librarian selectedLibrarian = librarianController.getLibrarianById(librarianId);
        if (selectedLibrarian == null) {
            JOptionPane.showMessageDialog(this, "Could not find the selected librarian.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Get new values for the librarian
        String name = JOptionPane.showInputDialog(this, "Enter new name:", selectedLibrarian.getName());
        if (name == null) {
            return;
        }
        
        String email = JOptionPane.showInputDialog(this, "Enter new email:", selectedLibrarian.getEmail());
        if (email == null) {
            return;
        }
        
        String password = JOptionPane.showInputDialog(this, "Enter new password (leave empty to keep current):");
        if (password == null) {
            return;
        }
        // If password is empty, keep the old one
        if (password.trim().isEmpty()) {
            password = selectedLibrarian.getPassword();
        }
        
        int statusChoice = JOptionPane.showConfirmDialog(this, 
                "Is this librarian active?", 
                "Librarian Status", 
                JOptionPane.YES_NO_OPTION);
        LibrarianController.Status status = (statusChoice == JOptionPane.YES_OPTION) ? 
                LibrarianController.Status.ACTIVE : 
                LibrarianController.Status.DISABLED;
        
        // Update the librarian in the database
        boolean success = librarianController.updateLibrarian(librarianId, name, email, password, status);
        
        if (success) {
            JOptionPane.showMessageDialog(this, "Librarian updated successfully!");
            loadLibrarians(); // Refresh the table
        } else {
            JOptionPane.showMessageDialog(this, "Failed to update librarian.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Delete the selected librarian
     */
    private void deleteSelectedLibrarian() {
        int selectedRow = jTable1.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Please select a librarian to delete.", "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Get the selected librarian ID
        int librarianId = (int) jTable1.getValueAt(selectedRow, 0);
        
        // Confirm deletion
        int confirm = JOptionPane.showConfirmDialog(this, 
                "Are you sure you want to delete this librarian?", 
                "Confirm Deletion", 
                JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            boolean success = librarianController.deleteLibrarian(librarianId);
            
            if (success) {
                JOptionPane.showMessageDialog(this, "Librarian deleted successfully!");
                loadLibrarians(); // Refresh the table
            } else {
                JOptionPane.showMessageDialog(this, "Failed to delete librarian.", "Error", JOptionPane.ERROR_MESSAGE);
            }
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
        addLibrariantn = new javax.swing.JButton();
        updateLibrarianBtn = new javax.swing.JButton();
        deleteLibrarianBtn = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        addLibrariantn.setBackground(new java.awt.Color(51, 153, 255));
        addLibrariantn.setText("Add Librarian");
        addLibrariantn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addLibrariantnActionPerformed(evt);
            }
        });

        updateLibrarianBtn.setBackground(new java.awt.Color(51, 153, 255));
        updateLibrarianBtn.setText("Update Librarian");
        updateLibrarianBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateLibrarianBtnActionPerformed(evt);
            }
        });

        deleteLibrarianBtn.setBackground(new java.awt.Color(51, 153, 255));
        deleteLibrarianBtn.setText("Delete Librarian");
        deleteLibrarianBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteLibrarianBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(83, Short.MAX_VALUE)
                .addComponent(addLibrariantn, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addComponent(updateLibrarianBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addComponent(deleteLibrarianBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(83, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addLibrariantn, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updateLibrarianBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deleteLibrarianBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Librarians", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 18))); // NOI18N

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Name", "Email", "Status"
            }
        ));
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE)
                .addGap(6, 6, 6))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void updateLibrarianBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateLibrarianBtnActionPerformed
        showUpdateLibrarianDialog();
    }//GEN-LAST:event_updateLibrarianBtnActionPerformed

    private void deleteLibrarianBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteLibrarianBtnActionPerformed
        deleteSelectedLibrarian();
    }//GEN-LAST:event_deleteLibrarianBtnActionPerformed
    
    private void addLibrariantnActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            showAddLibrarianDialog();
        } catch (SQLException ex) {
            Logger.getLogger(LibrarianManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

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
            java.util.logging.Logger.getLogger(LibrarianManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LibrarianManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LibrarianManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LibrarianManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LibrarianManagement().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addLibrariantn;
    private javax.swing.JButton deleteLibrarianBtn;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton updateLibrarianBtn;
    // End of variables declaration//GEN-END:variables
}
