/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package transaction;

import java.awt.font.TextAttribute;
import java.sql.Connection;
import java.sql.*;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import model.Config;

/**
 *
 * @author fauzan
 */
public class CreateTransactions extends JFrame {

    
    Config config;
    DefaultComboBoxModel model;
    DataTransactions data;
    int idData;
    int idUser;
    Dashboard dashboard;
    ArrayList<Category> categories = new ArrayList<Category>();
    Category category;
    
    
    public CreateTransactions(boolean edit, DataTransactions data,int idUser) {
        initComponents();
        setFrame();
        setJudulLabel();
        fillDataKategori();
        setButton(edit);
        
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
    }
    
    private void setButton(boolean edit){
        if(edit){
            deleteBtn.setVisible(true);
            editBtn.setVisible(true);
            addBtn.setVisible(false);
        }else{
            deleteBtn.setVisible(false);
            editBtn.setVisible(false);
            addBtn.setVisible(true);
        }
    }

    
    private void setFrame(){
        setSize(553, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("Add Transaction");
    }
    
    private Map<TextAttribute, Object> setJudulLabel(){
        Map<TextAttribute, Object> attributes = new HashMap<>();
        attributes.put(TextAttribute.FAMILY, "Inter");
        attributes.put(TextAttribute.WEIGHT, TextAttribute.WEIGHT_BOLD);
        attributes.put(TextAttribute.SIZE, 15);
        return attributes;
    }
    
    private Map<TextAttribute, Object> setKolomLabel(){
        Map<TextAttribute, Object> attributes = new HashMap<>();
        attributes.put(TextAttribute.FAMILY, "Inter");
        attributes.put(TextAttribute.WEIGHT, TextAttribute.WEIGHT_MEDIUM);
        attributes.put(TextAttribute.SIZE, 15);
        return attributes;
    }
    
    
    // function category
    
    private void fillDataKategori(){
        model = new DefaultComboBoxModel();
        setCategory();
        for(int i = 0; i < categories.size(); i++){
            model.addElement(categories.get(i).getName());
        }
        kategoriFld.setModel(model);
    }
    
    
    private void setCategory(){
        try{
            String sql = "SELECT id, title FROM categories ORDER BY id ASC";
            java.sql.Connection conn= (Connection)config.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            while(res.next()){
                category = new Category(Integer.parseInt(res.getString("id")), res.getString("title"));
                categories.add(category);
            }
       }catch(Exception e){
           JOptionPane.showMessageDialog(this, "Error : "+e);
       }
    }
    
    private int getCategory(String name){
        int category = 0;
        
        for(int i = 0; i < categories.size(); i++){
            if(categories.get(i).getName().equals(name)){
                category = categories.get(i).getId();
                break;
            }
        }
        return category;
    }
    
//  --------------------------------------- CRUD table transaction --------------------------------------
    
    private boolean insertData(int userId, String jenis, int kategori, int jumlah, String deskripsi, String tanggal){
        boolean result = true;
        try{
            String sql = "INSERT INTO transactions (user_id, categories_id, type, date, value, description) VALUES ('"+userId+"','"+kategori+"','"+jenis+"','"+tanggal+"','"+jumlah+"','"+deskripsi+"')" ;
            java.sql.Connection conn= (Connection)config.configDB();
            java.sql.PreparedStatement pst= conn.prepareStatement(sql);
            pst.execute();
            result = true;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
            result = false;
        }
        return result;
    }
    
    private boolean deleteData(int id){
        boolean result = true;
        
        try{
            String sql = "DELETE FROM transactions WHERE code = ?";
            java.sql.Connection conn= (Connection)config.configDB();
            java.sql.PreparedStatement pst= conn.prepareStatement(sql);
            pst.setInt(1,id);
            pst.execute();
            result = true;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
            result = false;
        }
        
        return result;
    }
    
    private boolean updateData(int id, String type, int kategori, int jumlah, String deskripsi, String tanggal){
        boolean result = false;
        
        try{
            String sql = "UPDATE transactions SET categories_id ='"+kategori+"', type ='"+type+"', date ='"+tanggal+"', value ='"+jumlah+"', description='"+deskripsi+"' WHERE code = ?";
            java.sql.Connection conn= (Connection)config.configDB();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1,id);
            pst.executeUpdate();
            result = true;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        
        return result;
    }
    
//    ----------------------------------------------------------------------------------------------------------------
    
    private void fillFormData()throws Exception {
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(data.tanggal);  
        dateFld.setDate(date);
        jenisFld.setSelectedItem(data.jenis);
        kategoriFld.setSelectedItem(data.kategori);
        jumlahFld.setText(String.valueOf(data.jumlah));
        deskripsiFld.setText(data.deskripsi);
    }
    
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jumlahFld = new javax.swing.JTextField();
        dateFld = new com.toedter.calendar.JDateChooser();
        kategoriFld = new javax.swing.JComboBox<>();
        jenisFld = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        deskripsiFld = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        addBtn = new javax.swing.JButton();
        editBtn = new javax.swing.JButton();
        deleteBtn = new javax.swing.JButton();

        setTitle("Add transaction");
        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(553, 750));
        setMinimumSize(new java.awt.Dimension(553, 750));
        setSize(new java.awt.Dimension(553, 750));
        getContentPane().setLayout(new java.awt.CardLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(110, 107, 250), 1, true));
        jPanel1.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.setMaximumSize(new java.awt.Dimension(553, 750));
        jPanel1.setMinimumSize(new java.awt.Dimension(553, 750));
        jPanel1.setPreferredSize(new java.awt.Dimension(553, 750));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(java.awt.Font.getFont(setJudulLabel())
        );
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("Tanggal Transaksi :");

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(java.awt.Font.getFont(setJudulLabel())
        );
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("Jenis : ");

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(java.awt.Font.getFont(setJudulLabel())
        );
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("Kategori : ");

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(java.awt.Font.getFont(setJudulLabel())
        );
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setText("Jumlah :");

        jumlahFld.setBackground(new java.awt.Color(255, 255, 255));
        jumlahFld.setFont(java.awt.Font.getFont(setKolomLabel())
        );
        jumlahFld.setForeground(new java.awt.Color(0, 0, 0));
        jumlahFld.setHorizontalAlignment(javax.swing.JTextField.LEFT);

        dateFld.setBackground(new java.awt.Color(248, 249, 250));
        dateFld.setForeground(new java.awt.Color(0, 0, 0));
        dateFld.setAutoscrolls(true);
        dateFld.setDate(new java.util.Date());
        dateFld.setDateFormatString("yyyy-MM-dd");
        dateFld.setFocusCycleRoot(true);
        dateFld.setFont(java.awt.Font.getFont(setKolomLabel()));

        kategoriFld.setBackground(new java.awt.Color(248, 249, 250));
        kategoriFld.setFont(java.awt.Font.getFont(setKolomLabel())
        );
        kategoriFld.setForeground(new java.awt.Color(0, 0, 0));
        kategoriFld.setSelectedItem(null);
        kategoriFld.setAutoscrolls(true);

        jenisFld.setBackground(new java.awt.Color(248, 249, 250));
        jenisFld.setFont(java.awt.Font.getFont(setKolomLabel())
        );
        jenisFld.setForeground(new java.awt.Color(0, 0, 0));
        jenisFld.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "income", "outcome" }));
        jenisFld.setAutoscrolls(true);
        jenisFld.setOpaque(false);

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(java.awt.Font.getFont(setJudulLabel())
        );
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel5.setText("Deskripsi : ");

        deskripsiFld.setBackground(new java.awt.Color(255, 255, 255));
        deskripsiFld.setFont(java.awt.Font.getFont(setKolomLabel())
        );
        deskripsiFld.setForeground(new java.awt.Color(0, 0, 0));
        deskripsiFld.setHorizontalAlignment(javax.swing.JTextField.LEFT);

        jPanel2.setMaximumSize(new java.awt.Dimension(234, 50));
        jPanel2.setMinimumSize(new java.awt.Dimension(234, 50));
        jPanel2.setPreferredSize(new java.awt.Dimension(203, 61));
        jPanel2.setLayout(new java.awt.CardLayout());

        addBtn.setBackground(new java.awt.Color(110, 107, 250));
        addBtn.setFont(new java.awt.Font("Inter", 1, 16)
        );
        addBtn.setForeground(new java.awt.Color(255, 255, 255));
        addBtn.setText("Tambah");
        addBtn.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(110, 107, 250), 0, true));
        addBtn.setMaximumSize(new java.awt.Dimension(203, 61));
        addBtn.setMinimumSize(new java.awt.Dimension(203, 61));
        addBtn.setPreferredSize(new java.awt.Dimension(203, 61));
        addBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBtnActionPerformed(evt);
            }
        });
        jPanel2.add(addBtn, "card2");

        editBtn.setBackground(new java.awt.Color(250, 176, 107));
        editBtn.setFont(new java.awt.Font("Inter", 1, 15)
        );
        editBtn.setForeground(new java.awt.Color(255, 255, 255));
        editBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Vector-1.png"))); // NOI18N
        editBtn.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(110, 107, 250), 0, true));
        editBtn.setPreferredSize(new java.awt.Dimension(203, 61));
        editBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editBtnActionPerformed(evt);
            }
        });
        jPanel2.add(editBtn, "card2");

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
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jenisFld, 0, 465, Short.MAX_VALUE)
                    .addComponent(jumlahFld)
                    .addComponent(kategoriFld, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dateFld, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(deskripsiFld)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(deleteBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(47, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(dateFld, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jenisFld, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(kategoriFld, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jumlahFld, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(deskripsiFld, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deleteBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35))
        );

        getContentPane().add(jPanel1, "card2");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed
        sdf.applyPattern(dateFld.getDateFormatString());
        
        tanggal = sdf.format(dateFld.getDate());
        
        String jenis = String.valueOf(jenisFld.getSelectedItem());
        
        int kategori = getCategory(String.valueOf(kategoriFld.getSelectedItem()));
        
        int jumlah = Integer.parseInt(jumlahFld.getText());
        
        String deskripsi = deskripsiFld.getText();

        if(insertData(idUser, jenis, kategori, jumlah, deskripsi, tanggal) == true){
            JOptionPane.showMessageDialog(null, "Tambah transaksi berhasil" );
            this.dispose();
        }
    }//GEN-LAST:event_addBtnActionPerformed

    private void editBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editBtnActionPerformed
        sdf.applyPattern(dateFld.getDateFormatString());
        
        tanggal = sdf.format(dateFld.getDate());
        
        String jenis = String.valueOf(jenisFld.getSelectedItem());
        
        int kategori = getCategory(String.valueOf(kategoriFld.getSelectedItem()));
        
        int jumlah = Integer.parseInt(jumlahFld.getText());
        
        String deskripsi = deskripsiFld.getText();
        
        if(updateData(idData, jenis, kategori, jumlah, deskripsi, tanggal) == true){
            JOptionPane.showMessageDialog(null, "Update transaksi berhasil" );
            this.dispose();
        }else{
            JOptionPane.showMessageDialog(null, "Update transaksi Gagal" );
        }
    }//GEN-LAST:event_editBtnActionPerformed

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
            java.util.logging.Logger.getLogger(CreateTransactions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CreateTransactions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CreateTransactions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CreateTransactions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CreateTransactions(false,null,0).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBtn;
    private com.toedter.calendar.JDateChooser dateFld;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JTextField deskripsiFld;
    private javax.swing.JButton editBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JComboBox<String> jenisFld;
    private javax.swing.JTextField jumlahFld;
    private javax.swing.JComboBox<String> kategoriFld;
    // End of variables declaration//GEN-END:variables
    SimpleDateFormat sdf = new SimpleDateFormat();
    String tanggal;
}
