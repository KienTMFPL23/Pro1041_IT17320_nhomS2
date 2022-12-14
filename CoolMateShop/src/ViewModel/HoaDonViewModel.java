/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ViewModel;

import java.util.Date;

/**
 *
 * @author BOSS
 */
public class HoaDonViewModel {

    private String id;
    private String khachHang;
    private String users;
    private String maHD;
    private Date ngayTao;
    private Date ngayThanhToan;
    private int tinhTrang;
    private float tongtien;

    public HoaDonViewModel(String khachHang, String users, String maHD, Date ngayTao, Date ngayThanhToan, int tinhTrang) {

        this.khachHang = khachHang;
        this.users = users;
        this.maHD = maHD;
        this.ngayTao = ngayTao;
        this.ngayThanhToan = ngayThanhToan;
        this.tinhTrang = tinhTrang;
    }

    public HoaDonViewModel(String id, String khachHang, String users, String maHD, Date ngayTao, Date ngayThanhToan, int tinhTrang) {
        this.id = id;
        this.khachHang = khachHang;
        this.users = users;
        this.maHD = maHD;
        this.ngayTao = ngayTao;
        this.ngayThanhToan = ngayThanhToan;
        this.tinhTrang = tinhTrang;
    }

    public HoaDonViewModel(String id, String khachHang, String users, String maHD, Date ngayTao, Date ngayThanhToan, int tinhTrang, float tongtien) {
        this.id = id;
        this.khachHang = khachHang;
        this.users = users;
        this.maHD = maHD;
        this.ngayTao = ngayTao;
        this.ngayThanhToan = ngayThanhToan;
        this.tinhTrang = tinhTrang;
        this.tongtien = tongtien;
    }

    public float getTongtien() {
        return tongtien;
    }

    public void setTongtien(float tongtien) {
        this.tongtien = tongtien;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public HoaDonViewModel() {
    }

    public String getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(String khachHang) {
        this.khachHang = khachHang;
    }

    public String getUsers() {
        return users;
    }

    public void setUsers(String users) {
        this.users = users;
    }

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public Date getNgayThanhToan() {
        return ngayThanhToan;
    }

    public void setNgayThanhToan(Date ngayThanhToan) {
        this.ngayThanhToan = ngayThanhToan;
    }

    public int getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(int tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

}
