/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DomainModel;

import java.util.Date;

/**
 *
 * @author BOSS
 */
public class HoaDon {

    private String id;
    private String idKH;
    private String idUser;
    private String maHd;
    private Date NgayTao;
    private Date NgayThanhToan;
    private int tinhTrang;

    public HoaDon(String id, String idKH, String idUser, String maHd, Date NgayTao, Date NgayThanhToan, int tinhTrang) {
        this.id = id;
        this.idKH = idKH;
        this.idUser = idUser;
        this.maHd = maHd;
        this.NgayTao = NgayTao;
        this.NgayThanhToan = NgayThanhToan;
        this.tinhTrang = tinhTrang;
    }

    public HoaDon(String idKH, String idUser, Date NgayThanhToan, int tinhTrang) {
        this.idKH = idKH;
        this.idUser = idUser;
        this.NgayThanhToan = NgayThanhToan;
        this.tinhTrang = tinhTrang;
    }

    public HoaDon() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdKH() {
        return idKH;
    }

    public void setIdKH(String idKH) {
        this.idKH = idKH;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getMaHd() {
        return maHd;
    }

    public void setMaHd(String maHd) {
        this.maHd = maHd;
    }

    public Date getNgayTao() {
        return NgayTao;
    }

    public void setNgayTao(Date NgayTao) {
        this.NgayTao = NgayTao;
    }

    public Date getNgayThanhToan() {
        return NgayThanhToan;
    }

    public void setNgayThanhToan(Date NgayThanhToan) {
        this.NgayThanhToan = NgayThanhToan;
    }

    public int getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(int tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

}
