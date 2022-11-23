/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ViewModel;

/**
 *
 * @author KIEN TRAN
 */
public class ChiTietHoaDonRespone {

    private String mahd;
    private String tenQuanAO;
    private int soLuong;
    private float donGia;

    public ChiTietHoaDonRespone() {
    }

    public ChiTietHoaDonRespone(String mahd, String tenQuanAO, int soLuong, float donGia) {
        this.mahd = mahd;
        this.tenQuanAO = tenQuanAO;
        this.soLuong = soLuong;
        this.donGia = donGia;
    }

    public String getMahd() {
        return mahd;
    }

    public void setMahd(String mahd) {
        this.mahd = mahd;
    }

    public String getTenQuanAO() {
        return tenQuanAO;
    }

    public void setTenQuanAO(String tenQuanAO) {
        this.tenQuanAO = tenQuanAO;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public float getDonGia() {
        return donGia;
    }

    public void setDonGia(float donGia) {
        this.donGia = donGia;
    }

}
