/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ViewModel;

/**
 *
 * @author KIEN TRAN
 */
public class ChiTietQuanAoRespone {

    private String maSP;
    private String tenSP;
    private String loaiSP;
    private String kichCo;
    private String mauSac;
    private String chatLieu;
    private Float giaTien;
    private Integer soLuong;
    private Integer trangThai;
    private String hinhAnh;

    public ChiTietQuanAoRespone() {
    }

    public ChiTietQuanAoRespone(String maSP, String tenSP, String loaiSP, String kichCo, String mauSac, String chatLieu, Float giaTien, Integer soLuong, Integer trangThai, String hinhAnh) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.loaiSP = loaiSP;
        this.kichCo = kichCo;
        this.mauSac = mauSac;
        this.chatLieu = chatLieu;
        this.giaTien = giaTien;
        this.soLuong = soLuong;
        this.trangThai = trangThai;
        this.hinhAnh = hinhAnh;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public String getLoaiSP() {
        return loaiSP;
    }

    public void setLoaiSP(String loaiSP) {
        this.loaiSP = loaiSP;
    }

    public String getKichCo() {
        return kichCo;
    }

    public void setKichCo(String kichCo) {
        this.kichCo = kichCo;
    }

    public String getMauSac() {
        return mauSac;
    }

    public void setMauSac(String mauSac) {
        this.mauSac = mauSac;
    }

    public String getChatLieu() {
        return chatLieu;
    }

    public void setChatLieu(String chatLieu) {
        this.chatLieu = chatLieu;
    }

    public Float getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(Float giaTien) {
        this.giaTien = giaTien;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public Integer getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Integer trangThai) {
        this.trangThai = trangThai;
    }

}
