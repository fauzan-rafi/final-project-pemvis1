package model;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Config {
    private static Connection mysqlconfig;
    
    public static Connection configDB()throws SQLException{
        try {
            String url="jdbc:mysql://localhost:3306/saku"; //url database
            String user="fauzan"; //user database
            String pass="Test@2021"; //password database
//            memanggil driver
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
//            koneksi database
            mysqlconfig=DriverManager.getConnection(url, user, pass); 
        } catch (Exception e) {
            System.err.println("Koneksi gagal "+e.getMessage()); //perintah menampilkan error pada koneksi
        }
        return mysqlconfig;
    }    
    
    public static void main(String[] args) throws SQLException {
        configDB();
    }
}
