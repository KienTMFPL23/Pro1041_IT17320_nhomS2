/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DomainModel;

/**
 *
 * @author KIEN TRAN
 */
public class ChiTietHoaDon {

    private String id;
    private String idHD;
    private String idChiTietQA;
    private String idKhuyenMai;
    private int soLuong;
    private float donGia;

    public ChiTietHoaDon() {
    }

    public ChiTietHoaDon(String id, String idHD, String idChiTietQA, String idKhuyenMai, int soLuong, float donGia) {
        this.id = id;
        this.idHD = idHD;
        this.idChiTietQA = idChiTietQA;
        this.idKhuyenMai = idKhuyenMai;
        this.soLuong = soLuong;
        this.donGia = donGia;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdHD() {
        return idHD;
    }

    public void setIdHD(String idHD) {
        this.idHD = idHD;
    }

    public String getIdChiTietQA() {
        return idChiTietQA;
    }

    public void setIdChiTietQA(String idChiTietQA) {
        this.idChiTietQA = idChiTietQA;
    }

    public String getIdKhuyenMai() {
        return idKhuyenMai;
    }

    public void setIdKhuyenMai(String idKhuyenMai) {
        this.idKhuyenMai = idKhuyenMai;
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
