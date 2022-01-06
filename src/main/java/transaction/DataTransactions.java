/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package transaction;

/**
 *
 * @author fauzan
 */
public class DataTransactions {
    
    String tanggal, jenis, kategori, deskripsi;
    int id, jumlah;
    
    DataTransactions(int id, String tanggal, String jenis, String kategori, int jumlah, String deskripsi){
        this.tanggal = tanggal;
        this.jenis = jenis;
        this.kategori = kategori;
        this.deskripsi = deskripsi;
        this.id = id;
        this.jumlah = jumlah;
    }
    
}
