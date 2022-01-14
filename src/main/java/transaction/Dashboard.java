package transaction;

import javax.swing.*;
import java.awt.Color;
import java.awt.Component;
import java.sql.Connection;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import model.Config;


public class Dashboard extends javax.swing.JFrame {

    Config config = new Config();
    DefaultTableModel model;
    JButton edit,delete;
    DataTransactions dataTransactions;
    CreateTransactions createTransactions;
    int idUser;
    
    public Dashboard(int idUser) {
        initComponents();
        setLayoutMain();
        setBtn();
        setViewBtn();
        this.idUser = idUser;
        tableTransaksi.setVisible(false);
    }
    
    private void setLayoutMain(){
        setSize(1440,1024);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("SakuKu");
        navbarPanel.setSize(279, 1024);
        tableTransaksi.setShowHorizontalLines(true);
        tableTransaksi.setGridColor(new Color(0,0,0,1));
    }
    
    private void setBtn(){
        arrButton = new JButton[]{dashboardBtn, transaksiBtn, kategoriBtn, laporanBtn, profileBtn};
        arrPanel = new JPanel[]{dashboardPnl, transaksiPnl, kategoriPnl, laporanPnl, profilePnl};
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
                arrButton[i].setBorderPainted(true);
                arrButton[i].setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
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
    
//    ------------- Function CRUD -----------------
    
//    Caller readData
    public void getReadData(){
        this.readData();
    }
//    Get data and show to table
    private void readData(){
       tableTransaksi.setVisible(true);
       model = new DefaultTableModel();
       model.addColumn("#");
       model.addColumn("No. Transaksi");
       model.addColumn("Tanggal Transaksi");
       model.addColumn("Jenis Transaksi");
       model.addColumn("Kategori");
       model.addColumn("Jumlah");
       model.addColumn("Deskripsi");
       model.addColumn("Opsi");
       
       // get data from db and show into table
       try{
            int no=1;
            String sql = "SELECT transactions.code AS 'code', transactions.date AS 'date', transactions.type as 'type', categories.title as 'category', transactions.value as 'value', transactions.description as 'description' FROM transactions INNER JOIN categories ON categories.id = transactions.categories_id";
            java.sql.Connection conn= (Connection)config.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            while(res.next()){
                model.addRow(new Object[]{no++,res.getString("code"), res.getString("date"),res.getString("type"),res.getString("category"),res.getString("value"),res.getString("description"), "Edit / Delete" });
            }
            tableTransaksi.setModel(model);
       }catch(Exception e){
           JOptionPane.showMessageDialog(this, "Error : "+e);
       }
    }
    
    

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
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        transaksiPnl = new javax.swing.JPanel();
        judulTransaksiPnl = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        addTransaksiBtn = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableTransaksi = new javax.swing.JTable();
        kategoriPnl = new javax.swing.JPanel();
        judulKategoriPnl = new javax.swing.JLabel();
        laporanPnl = new javax.swing.JPanel();
        judulLaporanPnl = new javax.swing.JLabel();
        profilePnl = new javax.swing.JPanel();
        judulProfilePnl = new javax.swing.JLabel();

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

        dashboardBtn.setFont(new java.awt.Font("Roboto", 1, 18));
        dashboardBtn.setForeground(new java.awt.Color(255, 255, 255));
        dashboardBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/homeIconBtn.png"))); // NOI18N
        dashboardBtn.setText("Dashboard");
        dashboardBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        dashboardBtn.setIconTextGap(50);
        dashboardBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dashboardBtnActionPerformed(evt);
            }
        });

        transaksiBtn.setFont(new java.awt.Font("Roboto", 1, 18));
        transaksiBtn.setForeground(new java.awt.Color(255, 255, 255));
        transaksiBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/transaksiIconBtn.png"))); // NOI18N
        transaksiBtn.setText("Transaksi");
        transaksiBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        transaksiBtn.setIconTextGap(50);
        transaksiBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                transaksiBtnActionPerformed(evt);
            }
        });

        kategoriBtn.setFont(new java.awt.Font("Roboto", 1, 18));
        kategoriBtn.setForeground(new java.awt.Color(255, 255, 255));
        kategoriBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/kategoriIconBtn.png"))); // NOI18N
        kategoriBtn.setText("Kategori");
        kategoriBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        kategoriBtn.setIconTextGap(50);
        kategoriBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kategoriBtnActionPerformed(evt);
            }
        });

        laporanBtn.setFont(new java.awt.Font("Ubuntu", 1, 18));
        laporanBtn.setForeground(new java.awt.Color(255, 255, 255));
        laporanBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/laporanIconBtn.png"))); // NOI18N
        laporanBtn.setText("Laporan");
        laporanBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        laporanBtn.setIconTextGap(50);
        laporanBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                laporanBtnActionPerformed(evt);
            }
        });

        profileBtn.setFont(new java.awt.Font("Ubuntu", 1, 18));
        profileBtn.setForeground(new java.awt.Color(255, 255, 255));
        profileBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/profileIconBtn.png"))); // NOI18N
        profileBtn.setText("Profile");
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
        judulDashboardLbl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Dashboard.png"))); // NOI18N

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Inter", 1, 36));
        jLabel1.setForeground(new java.awt.Color(45, 55, 72));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("Insight Untukmu");

        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 0, true));
        jPanel1.setMaximumSize(new java.awt.Dimension(673, 409));
        jPanel1.setMinimumSize(new java.awt.Dimension(673, 409));
        jPanel1.setPreferredSize(new java.awt.Dimension(673, 409));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 673, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 409, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout dashboardPnlLayout = new javax.swing.GroupLayout(dashboardPnl);
        dashboardPnl.setLayout(dashboardPnlLayout);
        dashboardPnlLayout.setHorizontalGroup(
            dashboardPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dashboardPnlLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dashboardPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 426, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(judulDashboardLbl)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(476, Short.MAX_VALUE))
        );
        dashboardPnlLayout.setVerticalGroup(
            dashboardPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dashboardPnlLayout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addComponent(judulDashboardLbl)
                .addGap(43, 43, 43)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(344, Short.MAX_VALUE))
        );

        mainPanel.add(dashboardPnl, "card2");

        transaksiPnl.setBackground(new java.awt.Color(255, 255, 255));

        judulTransaksiPnl.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        judulTransaksiPnl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Transaksi.png"))); // NOI18N

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Inter", 1, 36));
        jLabel2.setForeground(new java.awt.Color(45, 55, 72));
        jLabel2.setText("Transaksimu");

        addTransaksiBtn.setBackground(new java.awt.Color(110, 107, 250));
        addTransaksiBtn.setFont(new java.awt.Font("Inter", 0, 18)
        );
        addTransaksiBtn.setForeground(new java.awt.Color(255, 255, 255));
        addTransaksiBtn.setText("Tambah Transaksi");
        addTransaksiBtn.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(110, 107, 250), 0, true));
        addTransaksiBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addTransaksiBtnActionPerformed(evt);
            }
        });

        tableTransaksi.setAutoCreateRowSorter(true);
        tableTransaksi.setFont(new java.awt.Font("Inter",0,16)
        );
        tableTransaksi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6"
            }
        ));
        tableTransaksi.setIntercellSpacing(new java.awt.Dimension(2, 2));
        tableTransaksi.setRowHeight(40);
        tableTransaksi.setShowGrid(true);
        tableTransaksi.setShowVerticalLines(false);
        tableTransaksi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableTransaksiMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tableTransaksi);

        javax.swing.GroupLayout transaksiPnlLayout = new javax.swing.GroupLayout(transaksiPnl);
        transaksiPnl.setLayout(transaksiPnlLayout);
        transaksiPnlLayout.setHorizontalGroup(
            transaksiPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(transaksiPnlLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(transaksiPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(transaksiPnlLayout.createSequentialGroup()
                        .addGroup(transaksiPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(judulTransaksiPnl, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(addTransaksiBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 793, Short.MAX_VALUE)))
                .addContainerGap())
        );
        transaksiPnlLayout.setVerticalGroup(
            transaksiPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(transaksiPnlLayout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addComponent(judulTransaksiPnl, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 411, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 105, Short.MAX_VALUE)
                .addComponent(addTransaksiBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(204, 204, 204))
        );

        mainPanel.add(transaksiPnl, "card3");

        kategoriPnl.setBackground(new java.awt.Color(255, 255, 255));
        kategoriPnl.setMaximumSize(new java.awt.Dimension(1161, 1024));

        judulKategoriPnl.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        judulKategoriPnl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Kategori.png"))); // NOI18N

        javax.swing.GroupLayout kategoriPnlLayout = new javax.swing.GroupLayout(kategoriPnl);
        kategoriPnl.setLayout(kategoriPnlLayout);
        kategoriPnlLayout.setHorizontalGroup(
            kategoriPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kategoriPnlLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(judulKategoriPnl, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(954, Short.MAX_VALUE))
        );
        kategoriPnlLayout.setVerticalGroup(
            kategoriPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kategoriPnlLayout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addComponent(judulKategoriPnl, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(924, Short.MAX_VALUE))
        );

        mainPanel.add(kategoriPnl, "card4");

        laporanPnl.setBackground(new java.awt.Color(255, 255, 255));
        laporanPnl.setMaximumSize(new java.awt.Dimension(1161, 1024));

        judulLaporanPnl.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        judulLaporanPnl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Laporan.png"))); // NOI18N

        javax.swing.GroupLayout laporanPnlLayout = new javax.swing.GroupLayout(laporanPnl);
        laporanPnl.setLayout(laporanPnlLayout);
        laporanPnlLayout.setHorizontalGroup(
            laporanPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(laporanPnlLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(judulLaporanPnl, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(962, Short.MAX_VALUE))
        );
        laporanPnlLayout.setVerticalGroup(
            laporanPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(laporanPnlLayout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(judulLaporanPnl, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(926, Short.MAX_VALUE))
        );

        mainPanel.add(laporanPnl, "card5");

        profilePnl.setBackground(new java.awt.Color(255, 255, 255));

        judulProfilePnl.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        judulProfilePnl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Profil.png"))); // NOI18N

        javax.swing.GroupLayout profilePnlLayout = new javax.swing.GroupLayout(profilePnl);
        profilePnl.setLayout(profilePnlLayout);
        profilePnlLayout.setHorizontalGroup(
            profilePnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(profilePnlLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(judulProfilePnl, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(951, Short.MAX_VALUE))
        );
        profilePnlLayout.setVerticalGroup(
            profilePnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(profilePnlLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(judulProfilePnl, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(922, Short.MAX_VALUE))
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
    }//GEN-LAST:event_dashboardBtnActionPerformed

    private void transaksiBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_transaksiBtnActionPerformed
        panelPilihan = 1;
        setViewBtn();
        readData();
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

    private void addTransaksiBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addTransaksiBtnActionPerformed
        createTransactions = new CreateTransactions(false,null, idUser);
        createTransactions.setVisible(true);
    }//GEN-LAST:event_addTransaksiBtnActionPerformed

    private void tableTransaksiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableTransaksiMouseClicked
        // TODO add your handling code here:
        int id = Integer.parseInt(tableTransaksi.getValueAt(tableTransaksi.getSelectedRow(), 1).toString());
        String date = tableTransaksi.getValueAt(tableTransaksi.getSelectedRow(), 2).toString();
        String type = tableTransaksi.getValueAt(tableTransaksi.getSelectedRow(), 3).toString();
        String category = tableTransaksi.getValueAt(tableTransaksi.getSelectedRow(), 4).toString();
        int value = Integer.parseInt(tableTransaksi.getValueAt(tableTransaksi.getSelectedRow(), 5).toString());
        String description = tableTransaksi.getValueAt(tableTransaksi.getSelectedRow(), 6).toString();
        
        dataTransactions = new DataTransactions(id, date, type, category, value, description);
        
        createTransactions = new CreateTransactions(true, dataTransactions, idUser);
        createTransactions.setVisible(true);
    }//GEN-LAST:event_tableTransaksiMouseClicked

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
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Dashboard(0).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addTransaksiBtn;
    private javax.swing.JButton dashboardBtn;
    private javax.swing.JPanel dashboardPnl;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel judulDashboardLbl;
    private javax.swing.JLabel judulKategoriPnl;
    private javax.swing.JLabel judulLabel;
    private javax.swing.JLabel judulLaporanPnl;
    private javax.swing.JLabel judulProfilePnl;
    private javax.swing.JLabel judulTransaksiPnl;
    private javax.swing.JButton kategoriBtn;
    private javax.swing.JPanel kategoriPnl;
    private javax.swing.JButton laporanBtn;
    private javax.swing.JPanel laporanPnl;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JPanel navbarPanel;
    private javax.swing.JButton profileBtn;
    private javax.swing.JPanel profilePnl;
    private javax.swing.JTable tableTransaksi;
    private javax.swing.JButton transaksiBtn;
    private javax.swing.JPanel transaksiPnl;
    // End of variables declaration//GEN-END:variables
    JButton[] arrButton;
    JPanel[] arrPanel;
    Color[] arrColor;
    
    int panelPilihan = 0;
}
