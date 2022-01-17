package transaction;

import com.raven.chart.ModelChart;
import javax.swing.*;
import java.awt.Color;
import java.awt.Component;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import model.Config;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class Dashboard extends javax.swing.JFrame {

    
    DefaultTableModel model;
    DefaultTableModel model2;
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
        tableKategori.setVisible(false);
        loadDataUser();
        readKategori();
        loadChart();
        loadChartReportPengeluaran();
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
        
        tableKategori.setShowHorizontalLines(true);
        tableKategori.setGridColor(new Color(0,0,0,1));
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
            java.sql.Connection conn= (Connection)Config.configDB();
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
    
    
    private void readKategori(){
       tableKategori.setVisible(true);
       model2 = new DefaultTableModel();
       
       model2.addColumn("#");
       model2.addColumn("Judul");
       model2.addColumn("Jenis");
       model2.addColumn("Opsi");
       
       // get data from db and show into table
       try{
            int no=1;
            String sql = "SELECT * FROM categories";
            java.sql.Connection conn = (Connection)Config.configDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            while(res.next()){
                model2.addRow(new Object[]{no++,res.getString("title"), res.getString("type"), "Edit / Delete" });
            }
            
            tableKategori.setModel(model2);
       }catch(Exception e){
           JOptionPane.showMessageDialog(this, "Error : "+e);
       }
       
    }
    
    private void loadDataUser(){
        try {
            int no=1;
            String sql = "select * from users where id='"+this.idUser+"'";
            java.sql.Connection conn=(Connection)Config.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            while(res.next()){
                uProfileFld.setText(res.getString("username"));
                pProfileFld.setText(res.getString("password"));
                rProfileFLd.setText(res.getString("rule"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
    
    public void updateDataUser(String username, String password){
        
    }
    
    
    
    private void loadChart() {
        chart.addLegend("Income", new Color(12, 84, 175), new Color(0, 108, 247));
        chart.addLegend("Outcome", new Color(54, 4, 143), new Color(104, 49, 200));
//        chart.addLegend("Profit", new Color(5, 125, 0), new Color(95, 209, 69));
//        chart.addLegend("Cost", new Color(186, 37, 37), new Color(241, 100, 120));
        chart.addData(new ModelChart("January", new double[]{500, 200, 80, 89}));
        chart.addData(new ModelChart("February", new double[]{1000, 750, 90, 150}));
        chart.addData(new ModelChart("March", new double[]{200, 350, 460, 900}));
        chart.addData(new ModelChart("April", new double[]{480, 150, 750, 700}));
        chart.addData(new ModelChart("May", new double[]{350, 540, 300, 150}));
        chart.addData(new ModelChart("June", new double[]{190, 280, 81, 200}));
        chart.start();
    }
    
    
    
    
    
    // --------------------------------- Report Menu
    public List<ModelChart> getDataPengeluaran() throws SQLException {
        List<ModelChart> list = new ArrayList<>();
        String sql = "SELECT DATE_FORMAT(date,'%M') as M , sum(value) FROM `transactions` WHERE type='outcome' GROUP BY MONTH(date)";
        java.sql.Connection conn=(Connection)Config.configDB();
        java.sql.Statement stm=conn.createStatement();
        ResultSet r = stm.executeQuery(sql);
        while (r.next()) {
            String month = r.getString(1);
            int total = Integer.parseInt(r.getString(2));
//            double cost = r.getDouble(3);
//            double profit = r.getDouble(4);
            list.add(new ModelChart(month, new double[]{total}));
        }
        r.close();
        stm.close();
        return list;
    }
    
    public void loadChartReportPengeluaran(){
        chartReportPengeluaran.addLegend("Outcome", new Color(12, 84, 175), new Color(0, 108, 247));
//        chartReportPengeluaran.addLegend("Cost", new Color(186, 37, 37), new Color(241, 100, 120));
//        chartReportPengeluaran.addLegend("Profit", new Color(5, 125, 0), new Color(95, 209, 69));
        try {
            List<ModelChart> datas = getDataPengeluaran();
            for (int i = datas.size() - 1; i >= 0; i--) {
                chartReportPengeluaran.addData(datas.get(i));
            }
            chartReportPengeluaran.start();
        } catch (SQLException e) {
            System.err.println(e);
        }
    }
    
    // --------------------------------------------------------------------------------------------------------------------------------------------
    

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
        roundPanel1 = new component.RoundPanel();
        chart = new com.raven.chart.CurveChart();
        transaksiPnl = new javax.swing.JPanel();
        judulTransaksiPnl = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        addTransaksiBtn = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableTransaksi = new javax.swing.JTable();
        kategoriPnl = new javax.swing.JPanel();
        judulKategoriPnl = new javax.swing.JLabel();
        labelKategori = new javax.swing.JLabel();
        addKategoriBtn = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableKategori = new javax.swing.JTable();
        laporanPnl = new javax.swing.JPanel();
        judulLaporanPnl = new javax.swing.JLabel();
        labelKategori1 = new javax.swing.JLabel();
        panelPemasukan = new component.RoundPanel();
        chartReportPengeluaran = new com.raven.chart.CurveChart();
        panelPengeluaran = new component.RoundPanel();
        chartReportPemasukan = new com.raven.chart.CurveChart();
        profilePnl = new javax.swing.JPanel();
        judulProfilePnl = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        rProfileFLd = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        pProfileFld = new javax.swing.JPasswordField();
        jLabel6 = new javax.swing.JLabel();
        uProfileFld = new javax.swing.JTextField();
        profileEditBtn = new javax.swing.JButton();
        logoutBtn = new javax.swing.JButton();

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

        roundPanel1.setBackground(new java.awt.Color(60, 60, 60));

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chart, javax.swing.GroupLayout.DEFAULT_SIZE, 691, Short.MAX_VALUE)
                .addContainerGap())
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chart, javax.swing.GroupLayout.DEFAULT_SIZE, 451, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout dashboardPnlLayout = new javax.swing.GroupLayout(dashboardPnl);
        dashboardPnl.setLayout(dashboardPnlLayout);
        dashboardPnlLayout.setHorizontalGroup(
            dashboardPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dashboardPnlLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dashboardPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(roundPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 426, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(judulDashboardLbl))
                .addContainerGap(434, Short.MAX_VALUE))
        );
        dashboardPnlLayout.setVerticalGroup(
            dashboardPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dashboardPnlLayout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addComponent(judulDashboardLbl)
                .addGap(43, 43, 43)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(roundPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(302, Short.MAX_VALUE))
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

        labelKategori.setBackground(new java.awt.Color(255, 255, 255));
        labelKategori.setFont(new java.awt.Font("Inter", 1, 36));
        labelKategori.setForeground(new java.awt.Color(45, 55, 72));
        labelKategori.setText("Kategori");

        addKategoriBtn.setBackground(new java.awt.Color(110, 107, 250));
        addKategoriBtn.setFont(new java.awt.Font("Inter", 0, 18)
        );
        addKategoriBtn.setForeground(new java.awt.Color(255, 255, 255));
        addKategoriBtn.setText("Tambah Kategori");
        addKategoriBtn.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(110, 107, 250), 0, true));
        addKategoriBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addKategoriBtnActionPerformed(evt);
            }
        });

        tableKategori.setAutoCreateRowSorter(true);
        tableKategori.setFont(new java.awt.Font("Inter",0,16)
        );
        tableKategori.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tableKategori.setIntercellSpacing(new java.awt.Dimension(2, 2));
        tableKategori.setRowHeight(40);
        tableKategori.setShowGrid(true);
        tableKategori.setShowVerticalLines(false);
        tableKategori.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableKategoriMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tableKategori);

        javax.swing.GroupLayout kategoriPnlLayout = new javax.swing.GroupLayout(kategoriPnl);
        kategoriPnl.setLayout(kategoriPnlLayout);
        kategoriPnlLayout.setHorizontalGroup(
            kategoriPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kategoriPnlLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(kategoriPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1137, Short.MAX_VALUE)
                    .addGroup(kategoriPnlLayout.createSequentialGroup()
                        .addGroup(kategoriPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(judulKategoriPnl, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelKategori, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(addKategoriBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        kategoriPnlLayout.setVerticalGroup(
            kategoriPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kategoriPnlLayout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addComponent(judulKategoriPnl, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(labelKategori, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 560, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addComponent(addKategoriBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(106, 106, 106))
        );

        mainPanel.add(kategoriPnl, "card4");

        laporanPnl.setBackground(new java.awt.Color(255, 255, 255));
        laporanPnl.setMaximumSize(new java.awt.Dimension(1161, 1024));

        judulLaporanPnl.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        judulLaporanPnl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Laporan.png"))); // NOI18N

        labelKategori1.setBackground(new java.awt.Color(255, 255, 255));
        labelKategori1.setFont(new java.awt.Font("Inter", 1, 36));
        labelKategori1.setForeground(new java.awt.Color(45, 55, 72));
        labelKategori1.setText("Report");

        javax.swing.GroupLayout panelPemasukanLayout = new javax.swing.GroupLayout(panelPemasukan);
        panelPemasukan.setLayout(panelPemasukanLayout);
        panelPemasukanLayout.setHorizontalGroup(
            panelPemasukanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPemasukanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chartReportPengeluaran, javax.swing.GroupLayout.DEFAULT_SIZE, 1090, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelPemasukanLayout.setVerticalGroup(
            panelPemasukanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPemasukanLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(chartReportPengeluaran, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout panelPengeluaranLayout = new javax.swing.GroupLayout(panelPengeluaran);
        panelPengeluaran.setLayout(panelPengeluaranLayout);
        panelPengeluaranLayout.setHorizontalGroup(
            panelPengeluaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPengeluaranLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chartReportPemasukan, javax.swing.GroupLayout.DEFAULT_SIZE, 1103, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelPengeluaranLayout.setVerticalGroup(
            panelPengeluaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPengeluaranLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chartReportPemasukan, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout laporanPnlLayout = new javax.swing.GroupLayout(laporanPnl);
        laporanPnl.setLayout(laporanPnlLayout);
        laporanPnlLayout.setHorizontalGroup(
            laporanPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(laporanPnlLayout.createSequentialGroup()
                .addGroup(laporanPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panelPengeluaran, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(laporanPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(laporanPnlLayout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(laporanPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(judulLaporanPnl, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(labelKategori1, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(laporanPnlLayout.createSequentialGroup()
                            .addGap(25, 25, 25)
                            .addComponent(panelPemasukan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        laporanPnlLayout.setVerticalGroup(
            laporanPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(laporanPnlLayout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(judulLaporanPnl, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(labelKategori1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(panelPemasukan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(panelPengeluaran, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(56, Short.MAX_VALUE))
        );

        mainPanel.add(laporanPnl, "card5");

        profilePnl.setBackground(new java.awt.Color(255, 255, 255));

        judulProfilePnl.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        judulProfilePnl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Profil.png"))); // NOI18N

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Inter", 1, 36));
        jLabel3.setForeground(new java.awt.Color(45, 55, 72));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("Profile");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        Border blackline = BorderFactory.createLineBorder(new Color(216, 216, 216, 1));
        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(216, 216, 216), 1, true));
        jPanel1.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.setMaximumSize(new java.awt.Dimension(544, 570));
        jPanel1.setMinimumSize(new java.awt.Dimension(544, 570));
        jPanel1.setPreferredSize(new java.awt.Dimension(544, 570));

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Inter",1,22));
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Username");

        rProfileFLd.setEditable(false);
        rProfileFLd.setBackground(new java.awt.Color(248, 249, 250));
        rProfileFLd.setFont(new java.awt.Font("Roboto",0,16));
        rProfileFLd.setForeground(new java.awt.Color(0, 0, 0));
        rProfileFLd.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 0, true));

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Inter",1,22));
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Password");

        pProfileFld.setBackground(new java.awt.Color(248, 249, 250));
        pProfileFld.setFont(new java.awt.Font("Roboto",0,16));
        pProfileFld.setForeground(new java.awt.Color(0, 0, 0));
        pProfileFld.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 0, true));

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setFont(new java.awt.Font("Inter",1,22));
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Role");

        uProfileFld.setBackground(new java.awt.Color(248, 249, 250));
        uProfileFld.setFont(new java.awt.Font("Roboto",0,16));
        uProfileFld.setForeground(new java.awt.Color(0, 0, 0));
        uProfileFld.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 0, true));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pProfileFld)
                    .addComponent(rProfileFLd)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE)
                    .addComponent(uProfileFld))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(uProfileFld, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pProfileFld, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(rProfileFLd, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(113, Short.MAX_VALUE))
        );

        profileEditBtn.setBackground(new java.awt.Color(110, 107, 250));
        profileEditBtn.setFont(new java.awt.Font("Roboto",0,18));
        profileEditBtn.setForeground(new java.awt.Color(255, 255, 255));
        profileEditBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Vector-1.png"))); // NOI18N
        profileEditBtn.setText("Ubah");
        profileEditBtn.setIconTextGap(10);

        logoutBtn.setBackground(new java.awt.Color(232, 0, 84));
        logoutBtn.setFont(new java.awt.Font("Roboto",0,18));
        logoutBtn.setForeground(new java.awt.Color(255, 255, 255));
        logoutBtn.setText("Keluar");
        logoutBtn.setIconTextGap(10);

        javax.swing.GroupLayout profilePnlLayout = new javax.swing.GroupLayout(profilePnl);
        profilePnl.setLayout(profilePnlLayout);
        profilePnlLayout.setHorizontalGroup(
            profilePnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(profilePnlLayout.createSequentialGroup()
                .addGroup(profilePnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(profilePnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, profilePnlLayout.createSequentialGroup()
                            .addGap(252, 252, 252)
                            .addComponent(profileEditBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(logoutBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, profilePnlLayout.createSequentialGroup()
                            .addGap(21, 21, 21)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(profilePnlLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(profilePnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(judulProfilePnl, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 426, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(593, Short.MAX_VALUE))
        );
        profilePnlLayout.setVerticalGroup(
            profilePnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(profilePnlLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(judulProfilePnl, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addGroup(profilePnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(profileEditBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(logoutBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(92, Short.MAX_VALUE))
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
        readData();
    }//GEN-LAST:event_kategoriBtnActionPerformed

    private void laporanBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_laporanBtnActionPerformed
        panelPilihan = 3;
        setViewBtn();
    }//GEN-LAST:event_laporanBtnActionPerformed

    private void profileBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_profileBtnActionPerformed
       panelPilihan = 4;
       setViewBtn();
//       profileBtn.setText("");
//       profileBtn.setIcon(null);
//       profileBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/hoverProfile.png")));
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

    private void addKategoriBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addKategoriBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addKategoriBtnActionPerformed

    private void tableKategoriMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableKategoriMouseClicked
        
        int id = Integer.parseInt(tableTransaksi.getValueAt(tableTransaksi.getSelectedRow(), 1).toString());
        String date = tableTransaksi.getValueAt(tableTransaksi.getSelectedRow(), 2).toString();
        String type = tableTransaksi.getValueAt(tableTransaksi.getSelectedRow(), 3).toString();
        String category = tableTransaksi.getValueAt(tableTransaksi.getSelectedRow(), 4).toString();
        int value = Integer.parseInt(tableTransaksi.getValueAt(tableTransaksi.getSelectedRow(), 5).toString());
        String description = tableTransaksi.getValueAt(tableTransaksi.getSelectedRow(), 6).toString();
        
        dataTransactions = new DataTransactions(id, date, type, category, value, description);
        
//        createTransactions = new CreateTransactions(true, dataTransactions, idUser);
//        createTransactions.setVisible(true);
    }//GEN-LAST:event_tableKategoriMouseClicked

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
    private javax.swing.JButton addKategoriBtn;
    private javax.swing.JButton addTransaksiBtn;
    private com.raven.chart.CurveChart chart;
    private com.raven.chart.CurveChart chartReportPemasukan;
    private com.raven.chart.CurveChart chartReportPengeluaran;
    private javax.swing.JButton dashboardBtn;
    private javax.swing.JPanel dashboardPnl;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel judulDashboardLbl;
    private javax.swing.JLabel judulKategoriPnl;
    private javax.swing.JLabel judulLabel;
    private javax.swing.JLabel judulLaporanPnl;
    private javax.swing.JLabel judulProfilePnl;
    private javax.swing.JLabel judulTransaksiPnl;
    private javax.swing.JButton kategoriBtn;
    private javax.swing.JPanel kategoriPnl;
    private javax.swing.JLabel labelKategori;
    private javax.swing.JLabel labelKategori1;
    private javax.swing.JButton laporanBtn;
    private javax.swing.JPanel laporanPnl;
    private javax.swing.JButton logoutBtn;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JPanel navbarPanel;
    private javax.swing.JPasswordField pProfileFld;
    private component.RoundPanel panelPemasukan;
    private component.RoundPanel panelPengeluaran;
    private javax.swing.JButton profileBtn;
    private javax.swing.JButton profileEditBtn;
    private javax.swing.JPanel profilePnl;
    private javax.swing.JTextField rProfileFLd;
    private component.RoundPanel roundPanel1;
    private javax.swing.JTable tableKategori;
    private javax.swing.JTable tableTransaksi;
    private javax.swing.JButton transaksiBtn;
    private javax.swing.JPanel transaksiPnl;
    private javax.swing.JTextField uProfileFld;
    // End of variables declaration//GEN-END:variables
    JButton[] arrButton;
    JPanel[] arrPanel;
    Color[] arrColor;
    
    int panelPilihan = 0;
}
