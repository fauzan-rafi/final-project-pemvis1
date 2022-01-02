/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package transaction;

import javax.swing.*;
import java.awt.Color;

/**
 *
 * @author fauzan
 */
public class Dasboard extends javax.swing.JFrame {

    /**
     * Creates new form Dasboard
     */
    public Dasboard() {
        initComponents();
        setSize(1440,1024);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("SakuKu");
        navbarPanel.setSize(279, 1024);
        setBtn();
        setViewBtn();
        
        
    }
    
    private void setBtn(){
        arrButton = new JButton[]{dashboardBtn, kategoriBtn, transaksiBtn, laporanBtn, profileBtn};
        arrPanel = new JPanel[]{dashboardPnl,kategoriPnl, transaksiPnl, laporanPnl, profilePnl};
    }
    
    private void setViewBtn(){
        for(int i = 0; i < arrPanel.length; i++){
            // memberi warna semua button
            arrButton[i].setOpaque(false);
            arrButton[i].setContentAreaFilled(false);
            arrButton[i].setBorderPainted(false);
            
            if(i < panelPilihan){
                arrButton[i].setVisible(true);
                arrButton[i].setEnabled(true);
                arrPanel[i].setVisible(false);
            } else if(i == panelPilihan){
                arrButton[i].setEnabled(true);
                arrButton[i].setVisible(true);
                arrPanel[i].setVisible(true);
            } else {
                arrButton[i].setEnabled(true);
                arrButton[i].setVisible(true);
                arrButton[i].setOpaque(false);
                arrButton[i].setContentAreaFilled(false);
                arrButton[i].setBorderPainted(false);
                arrPanel[i].setVisible(true);
            }
        }
    }
    
    protected ImageIcon createImageIcon(String path, String description) {
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL, description);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
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

        navbarPanel = new javax.swing.JPanel();
        judulLabel = new javax.swing.JLabel();
        dashboardBtn = new javax.swing.JButton();
        transaksiBtn = new javax.swing.JButton();
        kategoriBtn = new javax.swing.JButton();
        laporanBtn = new javax.swing.JButton();
        profileBtn = new javax.swing.JButton();
        mainPanel = new javax.swing.JPanel();
        dashboardPnl = new javax.swing.JPanel();
        judulDashboardLbl = new javax.swing.JLabel();
        transaksiPnl = new javax.swing.JPanel();
        kategoriPnl = new javax.swing.JPanel();
        laporanPnl = new javax.swing.JPanel();
        profilePnl = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sakuku");
        setMaximumSize(new java.awt.Dimension(1440, 1024));
        setPreferredSize(new java.awt.Dimension(1440, 1024));
        setResizable(false);
        setSize(new java.awt.Dimension(1440, 1024));

        navbarPanel.setBackground(new java.awt.Color(24, 52, 94));
        navbarPanel.setPreferredSize(new java.awt.Dimension(279, 1024));

        judulLabel.setFont(new java.awt.Font("Roboto", 1, 36));
        judulLabel.setForeground(new java.awt.Color(110, 107, 250));
        judulLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        judulLabel.setText("<html>Sistem<br/>Keuangan</html>");

        dashboardBtn.setFont(new java.awt.Font("Ubuntu", 1, 24));
        dashboardBtn.setForeground(new java.awt.Color(255, 255, 255));
        dashboardBtn.setText("Dashboard");
        dashboardBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        dashboardBtn.setIconTextGap(50);
        dashboardBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dashboardBtnActionPerformed(evt);
            }
        });

        transaksiBtn.setFont(new java.awt.Font("Ubuntu", 1, 24));
        transaksiBtn.setForeground(new java.awt.Color(255, 255, 255));
        transaksiBtn.setText("Transaksi");
        transaksiBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        transaksiBtn.setIconTextGap(50);
        transaksiBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                transaksiBtnActionPerformed(evt);
            }
        });

        kategoriBtn.setFont(new java.awt.Font("Ubuntu", 1, 24));
        kategoriBtn.setForeground(new java.awt.Color(255, 255, 255));
        kategoriBtn.setText("Kategori");
        kategoriBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        kategoriBtn.setIconTextGap(50);
        kategoriBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kategoriBtnActionPerformed(evt);
            }
        });

        laporanBtn.setFont(new java.awt.Font("Ubuntu", 1, 24));
        laporanBtn.setForeground(new java.awt.Color(255, 255, 255));
        laporanBtn.setText("Laporan");
        laporanBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        laporanBtn.setIconTextGap(50);
        laporanBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                laporanBtnActionPerformed(evt);
            }
        });

        profileBtn.setFont(new java.awt.Font("Ubuntu", 1, 24));
        profileBtn.setForeground(new java.awt.Color(255, 255, 255));
        profileBtn.setText("Laporan");
        profileBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        profileBtn.setIconTextGap(50);
        profileBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                profileBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout navbarPanelLayout = new javax.swing.GroupLayout(navbarPanel);
        navbarPanel.setLayout(navbarPanelLayout);
        navbarPanelLayout.setHorizontalGroup(
            navbarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(navbarPanelLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(navbarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(judulLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dashboardBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(transaksiBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(kategoriBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(laporanBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(profileBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        navbarPanelLayout.setVerticalGroup(
            navbarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(navbarPanelLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(judulLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(dashboardBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(71, 71, 71)
                .addComponent(transaksiBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(78, 78, 78)
                .addComponent(kategoriBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(80, 80, 80)
                .addComponent(laporanBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 277, Short.MAX_VALUE)
                .addComponent(profileBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(136, 136, 136))
        );

        mainPanel.setForeground(new java.awt.Color(255, 255, 255));
        mainPanel.setMaximumSize(new java.awt.Dimension(1161, 1024));
        mainPanel.setPreferredSize(new java.awt.Dimension(1161, 1024));
        mainPanel.setLayout(new java.awt.CardLayout());

        dashboardPnl.setBackground(new java.awt.Color(255, 255, 255));
        dashboardPnl.setForeground(new java.awt.Color(45, 55, 72));

        judulDashboardLbl.setFont(new java.awt.Font("Roboto", 1, 18));
        judulDashboardLbl.setForeground(new java.awt.Color(255, 255, 255));
        judulDashboardLbl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/breadCrumbDashboard.png"))); // NOI18N

        javax.swing.GroupLayout dashboardPnlLayout = new javax.swing.GroupLayout(dashboardPnl);
        dashboardPnl.setLayout(dashboardPnlLayout);
        dashboardPnlLayout.setHorizontalGroup(
            dashboardPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dashboardPnlLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(judulDashboardLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(924, Short.MAX_VALUE))
        );
        dashboardPnlLayout.setVerticalGroup(
            dashboardPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dashboardPnlLayout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(judulDashboardLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(938, Short.MAX_VALUE))
        );

        mainPanel.add(dashboardPnl, "card2");

        javax.swing.GroupLayout transaksiPnlLayout = new javax.swing.GroupLayout(transaksiPnl);
        transaksiPnl.setLayout(transaksiPnlLayout);
        transaksiPnlLayout.setHorizontalGroup(
            transaksiPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1161, Short.MAX_VALUE)
        );
        transaksiPnlLayout.setVerticalGroup(
            transaksiPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1024, Short.MAX_VALUE)
        );

        mainPanel.add(transaksiPnl, "card3");

        javax.swing.GroupLayout kategoriPnlLayout = new javax.swing.GroupLayout(kategoriPnl);
        kategoriPnl.setLayout(kategoriPnlLayout);
        kategoriPnlLayout.setHorizontalGroup(
            kategoriPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1161, Short.MAX_VALUE)
        );
        kategoriPnlLayout.setVerticalGroup(
            kategoriPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1024, Short.MAX_VALUE)
        );

        mainPanel.add(kategoriPnl, "card4");

        javax.swing.GroupLayout laporanPnlLayout = new javax.swing.GroupLayout(laporanPnl);
        laporanPnl.setLayout(laporanPnlLayout);
        laporanPnlLayout.setHorizontalGroup(
            laporanPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1161, Short.MAX_VALUE)
        );
        laporanPnlLayout.setVerticalGroup(
            laporanPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1024, Short.MAX_VALUE)
        );

        mainPanel.add(laporanPnl, "card5");

        javax.swing.GroupLayout profilePnlLayout = new javax.swing.GroupLayout(profilePnl);
        profilePnl.setLayout(profilePnlLayout);
        profilePnlLayout.setHorizontalGroup(
            profilePnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1161, Short.MAX_VALUE)
        );
        profilePnlLayout.setVerticalGroup(
            profilePnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1024, Short.MAX_VALUE)
        );

        mainPanel.add(profilePnl, "card6");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(navbarPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(navbarPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void dashboardBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dashboardBtnActionPerformed
        panelPilihan = 0;
        setViewBtn();
        ImageIcon icon = createImageIcon("/gambar/Dashboard.png","p");
        dashboardBtn.setIcon(icon);
    }//GEN-LAST:event_dashboardBtnActionPerformed

    private void transaksiBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_transaksiBtnActionPerformed
        panelPilihan = 1;
        setViewBtn();
    }//GEN-LAST:event_transaksiBtnActionPerformed

    private void kategoriBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kategoriBtnActionPerformed
        panelPilihan = 2;
        setViewBtn();
    }//GEN-LAST:event_kategoriBtnActionPerformed

    private void laporanBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_laporanBtnActionPerformed
        panelPilihan = 3;
        setViewBtn();
    }//GEN-LAST:event_laporanBtnActionPerformed

    private void profileBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_profileBtnActionPerformed
       panelPilihan = 4;
        setViewBtn();
    }//GEN-LAST:event_profileBtnActionPerformed

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
            java.util.logging.Logger.getLogger(Dasboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Dasboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Dasboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Dasboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Dasboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton dashboardBtn;
    private javax.swing.JPanel dashboardPnl;
    private javax.swing.JLabel judulDashboardLbl;
    private javax.swing.JLabel judulLabel;
    private javax.swing.JButton kategoriBtn;
    private javax.swing.JPanel kategoriPnl;
    private javax.swing.JButton laporanBtn;
    private javax.swing.JPanel laporanPnl;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JPanel navbarPanel;
    private javax.swing.JButton profileBtn;
    private javax.swing.JPanel profilePnl;
    private javax.swing.JButton transaksiBtn;
    private javax.swing.JPanel transaksiPnl;
    // End of variables declaration//GEN-END:variables
    JButton[] arrButton;
    JPanel[] arrPanel;
    Color[] arrColor;
    
    int panelPilihan = 0;
}
