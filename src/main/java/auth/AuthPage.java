
package auth;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import model.Config;
import transaction.Dashboard;

/**
 *
 * @author gummlas
 */
public class AuthPage extends javax.swing.JFrame {

    
//    Connection con;
//    Statement stat;
//    ResultSet rs;
    String sql;
    
    public AuthPage() {
        initComponents();
        setLayoutMain();
    }
    
    


    private void setLayoutMain(){
        setSize(1440,1024);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("SakuKu");
        
        // Set image as background
        
        setLayout(new BorderLayout());

        JLabel background=new JLabel(new ImageIcon(getClass().getResource("/Images/bg-login.png")));

        add(background);

        background.setLayout(new FlowLayout());
        
        // Set false regisrter panel
        registerPanel.setVisible(false);
    }
    
    
    private boolean login(String username, String password){
        boolean result = true;
        
        try {
            sql = "SELECT * FROM users WHERE username='"+username+"' AND password='"+password+"'";
            java.sql.Connection conn = (Connection)Config.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet rs=stm.executeQuery(sql);
            if(rs.next()){
                if(username.equals(rs.getString("username")) && password.equals(rs.getString("password"))){
                    JOptionPane.showMessageDialog(null, "berhasil login");
                    Dashboard dashboard = new Dashboard(Integer.parseInt(rs.getString("id")));
                    dashboard.setVisible(true);
                    this.dispose();
                }
            }else{
                    JOptionPane.showMessageDialog(null, "username atau password salah");
                    result = false;
                }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "failed to login because" + e);
            result = false;
        }
        
        return result;
    }
    
    
    
    private boolean register(String fullName, String username, String password){
        boolean result = true;
        
        try {
            String sql = "INSERT INTO users(full_name , username, password, rule) VALUES ('"+fullName+"', '"+username+"', '"+password+"', 'user')";
            java.sql.Connection conn=(Connection)Config.configDB();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Daftar akun berhasil");
            result = true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            result = false;
        }
        
        return result;
        
    }
    
    private void kosongkan(){
        fldFullnameRegist.setText("");
        fldUsernameRegist.setText("");
        fldPasswordRegist.setText("");
    }
    
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        loginPanel = new javax.swing.JPanel();
        judulPage = new javax.swing.JLabel();
        lblUsername = new javax.swing.JLabel();
        lblPassword = new javax.swing.JLabel();
        fldUsername = new javax.swing.JTextField();
        fldPassword = new javax.swing.JPasswordField();
        btnLogin = new javax.swing.JButton();
        btnOpenRegister = new javax.swing.JButton();
        registerPanel = new javax.swing.JPanel();
        judulPage1 = new javax.swing.JLabel();
        lblUsername1 = new javax.swing.JLabel();
        lblPassword1 = new javax.swing.JLabel();
        fldFullnameRegist = new javax.swing.JTextField();
        fldPasswordRegist = new javax.swing.JPasswordField();
        btnRegister = new javax.swing.JButton();
        btnOpenLogin = new javax.swing.JButton();
        lblUsername2 = new javax.swing.JLabel();
        fldUsernameRegist = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(24, 52, 94));
        setMaximumSize(new java.awt.Dimension(1440, 1024));
        setMinimumSize(new java.awt.Dimension(1440, 1024));
        setPreferredSize(new java.awt.Dimension(1440, 1024));
        setResizable(false);
        setSize(new java.awt.Dimension(1440, 1024));

        mainPanel.setBackground(new java.awt.Color(255, 255, 255));
        mainPanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 0, true));
        mainPanel.setForeground(new java.awt.Color(45, 55, 72));
        mainPanel.setMaximumSize(new java.awt.Dimension(553, 728));
        mainPanel.setMinimumSize(new java.awt.Dimension(553, 728));
        mainPanel.setPreferredSize(new java.awt.Dimension(553, 728));
        mainPanel.setLayout(new java.awt.CardLayout());

        loginPanel.setBackground(new java.awt.Color(255, 255, 255));
        loginPanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 0, true));
        loginPanel.setForeground(new java.awt.Color(45, 55, 72));
        loginPanel.setMaximumSize(new java.awt.Dimension(553, 728));
        loginPanel.setMinimumSize(new java.awt.Dimension(553, 728));

        judulPage.setBackground(new java.awt.Color(255, 255, 255));
        judulPage.setFont(new java.awt.Font("Spectral",1,48));
        judulPage.setForeground(new java.awt.Color(45, 55, 72));
        judulPage.setText("Masuk");

        lblUsername.setBackground(new java.awt.Color(255, 255, 255));
        lblUsername.setFont(new java.awt.Font("Inter",1,18));
        lblUsername.setForeground(new java.awt.Color(45, 55, 72));
        lblUsername.setText("Username");

        lblPassword.setBackground(new java.awt.Color(255, 255, 255));
        lblPassword.setFont(new java.awt.Font("Inter",1,18));
        lblPassword.setForeground(new java.awt.Color(45, 55, 72));
        lblPassword.setText("Password");

        fldUsername.setBackground(new java.awt.Color(248, 249, 250));
        fldUsername.setFont(new java.awt.Font("Roboto",0,15));
        fldUsername.setForeground(new java.awt.Color(0, 0, 0));
        fldUsername.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(154, 154, 154), 0, true));

        fldPassword.setBackground(new java.awt.Color(248, 249, 250));
        fldPassword.setFont(new java.awt.Font("Roboto",1,15));
        fldPassword.setForeground(new java.awt.Color(0, 0, 0));
        fldPassword.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(154, 154, 154), 0, true));
        fldPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fldPasswordActionPerformed(evt);
            }
        });

        btnLogin.setBackground(new java.awt.Color(110, 107, 250));
        btnLogin.setFont(new java.awt.Font("Roboto",1,18));
        btnLogin.setForeground(java.awt.Color.white);
        btnLogin.setText("Masuk");
        btnLogin.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 0, true));
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        btnOpenRegister.setBackground(new java.awt.Color(255, 255, 255));
        btnOpenRegister.setFont(new java.awt.Font("Roboto",0,13));
        btnOpenRegister.setForeground(java.awt.Color.black);
        btnOpenRegister.setText("Belum punya akun ? Daftar");
        btnOpenRegister.setBorderPainted(false);
        btnOpenRegister.setContentAreaFilled(false);
        btnOpenRegister.setFocusPainted(false);
        btnOpenRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpenRegisterActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout loginPanelLayout = new javax.swing.GroupLayout(loginPanel);
        loginPanel.setLayout(loginPanelLayout);
        loginPanelLayout.setHorizontalGroup(
            loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loginPanelLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(judulPage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblUsername, javax.swing.GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE)
                    .addComponent(lblPassword, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(fldUsername)
                    .addComponent(fldPassword)
                    .addComponent(btnLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loginPanelLayout.createSequentialGroup()
                .addContainerGap(161, Short.MAX_VALUE)
                .addComponent(btnOpenRegister, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(159, 159, 159))
        );
        loginPanelLayout.setVerticalGroup(
            loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loginPanelLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(judulPage, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57)
                .addComponent(lblUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(fldUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(lblPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(fldPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54)
                .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addComponent(btnOpenRegister, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
        );

        mainPanel.add(loginPanel, "card2");

        registerPanel.setBackground(new java.awt.Color(255, 255, 255));
        registerPanel.setForeground(new java.awt.Color(45, 55, 72));
        registerPanel.setMaximumSize(new java.awt.Dimension(553, 728));
        registerPanel.setMinimumSize(new java.awt.Dimension(553, 728));

        judulPage1.setBackground(new java.awt.Color(255, 255, 255));
        judulPage1.setFont(new java.awt.Font("Spectral",1,48));
        judulPage1.setForeground(new java.awt.Color(45, 55, 72));
        judulPage1.setText("Daftar Akun");

        lblUsername1.setBackground(new java.awt.Color(255, 255, 255));
        lblUsername1.setFont(new java.awt.Font("Inter",1,18));
        lblUsername1.setForeground(new java.awt.Color(45, 55, 72));
        lblUsername1.setText("Full Name");

        lblPassword1.setBackground(new java.awt.Color(255, 255, 255));
        lblPassword1.setFont(new java.awt.Font("Inter",1,18));
        lblPassword1.setForeground(new java.awt.Color(45, 55, 72));
        lblPassword1.setText("Password");

        fldFullnameRegist.setBackground(new java.awt.Color(248, 249, 250));
        fldFullnameRegist.setFont(new java.awt.Font("Roboto",0,15));
        fldFullnameRegist.setForeground(new java.awt.Color(0, 0, 0));
        fldFullnameRegist.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(154, 154, 154), 0, true));

        fldPasswordRegist.setBackground(new java.awt.Color(248, 249, 250));
        fldPasswordRegist.setFont(new java.awt.Font("Roboto",1,15));
        fldPasswordRegist.setForeground(new java.awt.Color(0, 0, 0));
        fldPasswordRegist.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(154, 154, 154), 0, true));
        fldPasswordRegist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fldPasswordRegistActionPerformed(evt);
            }
        });

        btnRegister.setBackground(new java.awt.Color(110, 107, 250));
        btnRegister.setFont(new java.awt.Font("Roboto",1,18));
        btnRegister.setForeground(java.awt.Color.white);
        btnRegister.setText("Daftar");
        btnRegister.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 0, true));
        btnRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegisterActionPerformed(evt);
            }
        });

        btnOpenLogin.setBackground(new java.awt.Color(255, 255, 255));
        btnOpenLogin.setFont(new java.awt.Font("Roboto",0,13));
        btnOpenLogin.setForeground(java.awt.Color.black);
        btnOpenLogin.setText("Sudah punya akun ?  Masuk");
        btnOpenLogin.setBorderPainted(false);
        btnOpenLogin.setContentAreaFilled(false);
        btnOpenLogin.setFocusPainted(false);
        btnOpenLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpenLoginActionPerformed(evt);
            }
        });

        lblUsername2.setBackground(new java.awt.Color(255, 255, 255));
        lblUsername2.setFont(new java.awt.Font("Inter",1,18));
        lblUsername2.setForeground(new java.awt.Color(45, 55, 72));
        lblUsername2.setText("Username");

        fldUsernameRegist.setBackground(new java.awt.Color(248, 249, 250));
        fldUsernameRegist.setFont(new java.awt.Font("Roboto",0,15));
        fldUsernameRegist.setForeground(new java.awt.Color(0, 0, 0));
        fldUsernameRegist.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(154, 154, 154), 0, true));

        javax.swing.GroupLayout registerPanelLayout = new javax.swing.GroupLayout(registerPanel);
        registerPanel.setLayout(registerPanelLayout);
        registerPanelLayout.setHorizontalGroup(
            registerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(registerPanelLayout.createSequentialGroup()
                .addGroup(registerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(registerPanelLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(registerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(judulPage1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblUsername1, javax.swing.GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE)
                            .addComponent(lblPassword1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(fldFullnameRegist)
                            .addComponent(fldPasswordRegist)
                            .addComponent(btnRegister, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblUsername2, javax.swing.GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE)
                            .addComponent(fldUsernameRegist)))
                    .addGroup(registerPanelLayout.createSequentialGroup()
                        .addGap(159, 159, 159)
                        .addComponent(btnOpenLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        registerPanelLayout.setVerticalGroup(
            registerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(registerPanelLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(judulPage1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblUsername1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(fldFullnameRegist, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblUsername2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(fldUsernameRegist, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(lblPassword1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(fldPasswordRegist, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addComponent(btnRegister, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnOpenLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        mainPanel.add(registerPanel, "card3");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1310, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 868, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void fldPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fldPasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fldPasswordActionPerformed

    private void btnOpenRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpenRegisterActionPerformed
        loginPanel.setVisible(false);
        registerPanel.setVisible(true);
    }//GEN-LAST:event_btnOpenRegisterActionPerformed

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        String username = fldUsername.getText();
        String password = String.valueOf(fldPassword.getPassword());
        
        login(username, password);
    }//GEN-LAST:event_btnLoginActionPerformed

    private void fldPasswordRegistActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fldPasswordRegistActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fldPasswordRegistActionPerformed

    private void btnRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegisterActionPerformed
        String username = fldUsername.getText();
        String password = String.valueOf(fldPassword.getPassword());
        String fullname = fldFullnameRegist.getText();
        
        if(register(fullname,username,password)){
            kosongkan();
            login(username, password);
        }else{
            kosongkan();
            JOptionPane.showMessageDialog(null, "Daftar akun gagal, silahkan ulangi");
        }
        
    }//GEN-LAST:event_btnRegisterActionPerformed

    private void btnOpenLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpenLoginActionPerformed
        loginPanel.setVisible(true);
        registerPanel.setVisible(false);
    }//GEN-LAST:event_btnOpenLoginActionPerformed

    
    
    
    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AuthPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AuthPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AuthPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AuthPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AuthPage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogin;
    private javax.swing.JButton btnOpenLogin;
    private javax.swing.JButton btnOpenRegister;
    private javax.swing.JButton btnRegister;
    private javax.swing.JTextField fldFullnameRegist;
    private javax.swing.JPasswordField fldPassword;
    private javax.swing.JPasswordField fldPasswordRegist;
    private javax.swing.JTextField fldUsername;
    private javax.swing.JTextField fldUsernameRegist;
    private javax.swing.JLabel judulPage;
    private javax.swing.JLabel judulPage1;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblPassword1;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JLabel lblUsername1;
    private javax.swing.JLabel lblUsername2;
    private javax.swing.JPanel loginPanel;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JPanel registerPanel;
    // End of variables declaration//GEN-END:variables
}
