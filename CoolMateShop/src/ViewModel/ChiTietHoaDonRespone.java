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

    private String id;
    private String idHD;
    private String idCTQA;
    private String mahd;
    private String maQuanAo;
    private String tenQuanAO;
    private int soLuong;
    private float donGia;

    public ChiTietHoaDonRespone() {
    }

    public ChiTietHoaDonRespone(String id, String idCTQA, String mahd, String maQuanAo, String tenQuanAO, int soLuong, float donGia) {
        this.id = id;
        this.idCTQA = idCTQA;
        this.mahd = mahd;
        this.maQuanAo = maQuanAo;
        this.tenQuanAO = tenQuanAO;
        this.soLuong = soLuong;
        this.donGia = donGia;
    }

    public ChiTietHoaDonRespone(String mahd, String tenQuanAO, int soLuong, float donGia) {
        this.mahd = mahd;
        this.tenQuanAO = tenQuanAO;
        this.soLuong = soLuong;
        this.donGia = donGia;
    }

    public ChiTietHoaDonRespone(String mahd, String maQuanAo, String tenQuanAO, int soLuong, float donGia) {
        this.mahd = mahd;
        this.maQuanAo = maQuanAo;
        this.tenQuanAO = tenQuanAO;
        this.soLuong = soLuong;
        this.donGia = donGia;

    }

    public ChiTietHoaDonRespone(String idCTQA, String mahd, String maQuanAo, String tenQuanAO, int soLuong, float donGia) {
        this.idCTQA = idCTQA;
        this.mahd = mahd;
        this.maQuanAo = maQuanAo;
        this.tenQuanAO = tenQuanAO;
        this.soLuong = soLuong;
        this.donGia = donGia;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdCTQA() {
        return idCTQA;
    }

    public void setIdCTQA(String idCTQA) {
        this.idCTQA = idCTQA;
    }

    public String getIdHD() {
        return idHD;
    }

    public void setIdHD(String idHD) {
        this.idHD = idHD;
    }

    public String getMaQuanAo() {
        return maQuanAo;
    }

    public void setMaQuanAo(String maQuanAo) {
        this.maQuanAo = maQuanAo;
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

    public float thanhTien(int soLuong, float donGia) {
        return soLuong * donGia;
    }

}
