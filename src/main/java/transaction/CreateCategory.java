
package transaction;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import model.Config;


public class CreateCategory extends javax.swing.JFrame {

    Config config;
    DefaultComboBoxModel model;
    DataCategories data;
    int idData;
    int idUser;
    Dashboard dashboard;
    ArrayList<Category> categories = new ArrayList<Category>();
    Category category;
    
    
    public CreateCategory(boolean edit, DataCategories data,int idUser) {
        initComponents();
        setFrame();
        
//      set data id
        if(data != null){
            this.data = data;
            this.idData =  data.id;

            try {
                fillFormData();
            } catch (Exception ex) {
                Logger.getLogger(CreateTransactions.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
//      set id user
        this.idUser = idUser;
        
        setButton(edit);
    }

    
    private void setFrame(){
        setSize(553, 494);
        setLocationRelativeTo(null);
        setTitle("Add Transaction");
    }
    
    
    private void setButton(boolean edit){
        if(edit){
            if(isAdmin()){
                deleteBtn.setVisible(true);
            }else{
                deleteBtn.setVisible(false);
            }
            addBtn.setVisible(false);
            editBtn.setVisible(true);
        }else{
            deleteBtn.setVisible(false);
            addBtn.setVisible(true);
            editBtn.setVisible(false);
        }
    }
    
    private boolean isAdmin(){
        boolean result = false;
        try {
            String sql = "select rule from users where id='"+this.idUser+"'";
            java.sql.Connection conn=(Connection)Config.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            while(res.next()){
                if(res.getString("rule").equals("admin")){
                    result = true;
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        
        return result;
    }
    
//    -------------------------------------------------------------------------------------------------------------------
    
    
    //  --------------------------------------- CRUD table transaction --------------------------------------
    
    private boolean insertData(String title, String tipe){
        boolean result = false;
        try{
            String sql = "INSERT INTO categories (title, type) VALUES ('"+title+"','"+tipe+"')" ;
            java.sql.Connection conn= (Connection)config.configDB();
            java.sql.PreparedStatement pst= conn.prepareStatement(sql);
            pst.execute();
            result = true;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        return result;
    }
    
    private boolean deleteData(int id){
        boolean result = false;
        
        try{
            String sql ="delete from categories where id='"+id+"'";
            java.sql.Connection conn=(Connection)Config.configDB();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.execute();
            result = true;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        
        return result;
    }
    
    private boolean updateData(int id, String title, String tipe){
        boolean result = false;
        
        try{
            String sql ="update categories set title="+"'"+title+"', type='"+tipe+"' where id='"+id+"'";
            java.sql.Connection conn=(Connection)Config.configDB();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.execute();
            result = true;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        
        return result;
    }
    
    
    private void fillFormData()throws Exception {
        kategoriNameFld.setText(data.nama);
        tipeComboBox.setSelectedItem(data.jenis);
    }
    
    
    
    
    
    
    
//    -------------------------------------------------------------------------------------------------------------------
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        kategoriNameFld = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        tipeComboBox = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        addBtn = new javax.swing.JButton();
        editBtn = new javax.swing.JButton();
        deleteBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(553, 494));
        setMinimumSize(new java.awt.Dimension(553, 494));
        setPreferredSize(new java.awt.Dimension(553, 494));
        setResizable(false);
        setSize(new java.awt.Dimension(553, 494));
        getContentPane().setLayout(new java.awt.CardLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setForeground(new java.awt.Color(0, 0, 0));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Inter",1,14));
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Nama Kategori");

        kategoriNameFld.setBackground(new java.awt.Color(248, 249, 250));
        kategoriNameFld.setFont(new java.awt.Font("Roboto",0,14));
        kategoriNameFld.setForeground(new java.awt.Color(0, 0, 0));
        kategoriNameFld.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 0, true));

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Inter",1,14));
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Jenis");

        tipeComboBox.setBackground(new java.awt.Color(248, 249, 250));
        tipeComboBox.setFont(new java.awt.Font("Roboto",0,14) {
        }
    );
    tipeComboBox.setForeground(new java.awt.Color(0, 0, 0));
    tipeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "income", "outcome" }));

    jPanel2.setLayout(new java.awt.CardLayout());

    addBtn.setBackground(new java.awt.Color(110, 107, 250));
    addBtn.setFont(new java.awt.Font("Inter",1,15));
    addBtn.setForeground(new java.awt.Color(255, 255, 255));
    addBtn.setText("Tambah");
    addBtn.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            addBtnActionPerformed(evt);
        }
    });
    jPanel2.add(addBtn, "card2");

    editBtn.setBackground(new java.awt.Color(250, 176, 107));
    editBtn.setFont(new java.awt.Font("Inter",1,14));
    editBtn.setForeground(new java.awt.Color(255, 255, 255));
    editBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Vector-1.png"))); // NOI18N
    editBtn.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            editBtnActionPerformed(evt);
        }
    });
    jPanel2.add(editBtn, "card3");

    deleteBtn.setBackground(new java.awt.Color(250, 107, 107));
    deleteBtn.setFont(new java.awt.Font("Inter", 1, 15)
    );
    deleteBtn.setForeground(new java.awt.Color(255, 255, 255));
    deleteBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Vector.png"))); // NOI18N
    deleteBtn.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(110, 107, 250), 0, true));
    deleteBtn.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            deleteBtnActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel1Layout.createSequentialGroup()
            .addGap(61, 61, 61)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(tipeComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(kategoriNameFld)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 418, Short.MAX_VALUE)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(deleteBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addContainerGap(74, Short.MAX_VALUE))
    );
    jPanel1Layout.setVerticalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel1Layout.createSequentialGroup()
            .addGap(39, 39, 39)
            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(36, 36, 36)
            .addComponent(kategoriNameFld, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(39, 39, 39)
            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(30, 30, 30)
            .addComponent(tipeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 71, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(deleteBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGap(40, 40, 40))
    );

    getContentPane().add(jPanel1, "card2");

    pack();
    }// </editor-fold>//GEN-END:initComponents

    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed

        int opsi = JOptionPane.showConfirmDialog(null, "Benarkah anda ingin menghapus data ini ?", "Penghapusan Data", JOptionPane.YES_NO_OPTION);
        if (opsi == JOptionPane.YES_OPTION){
            deleteData(this.idData);
            JOptionPane.showMessageDialog(null, "Data Berhasil di Hapus!!");
            this.dispose();
        }else{
            JOptionPane.showMessageDialog(null, "Data tidak di Hapus!!");
            this.dispose();
        }
    }//GEN-LAST:event_deleteBtnActionPerformed

    private void editBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editBtnActionPerformed
        String title = kategoriNameFld.getText();
        String tipe = String.valueOf(tipeComboBox.getSelectedItem());
        
        if(updateData(idData,title,tipe)){
            JOptionPane.showMessageDialog(null,"Edit kategori berhasil");
        }
        
    }//GEN-LAST:event_editBtnActionPerformed

    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed
        String title = kategoriNameFld.getText();
        String tipe = String.valueOf(tipeComboBox.getSelectedItem());
        
        if(insertData(title,tipe)){
            JOptionPane.showMessageDialog(null,"Tambah kategori berhasil");
        }
    }//GEN-LAST:event_addBtnActionPerformed

    
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
            java.util.logging.Logger.getLogger(CreateCategory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CreateCategory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CreateCategory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CreateCategory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CreateCategory(false,null,0).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBtn;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JButton editBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField kategoriNameFld;
    private javax.swing.JComboBox<String> tipeComboBox;
    // End of variables declaration//GEN-END:variables
}
