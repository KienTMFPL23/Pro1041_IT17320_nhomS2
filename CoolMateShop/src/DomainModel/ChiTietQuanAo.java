/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DomainModel;

/**
 *
 * @author KIEN TRAN
 */
public class ChiTietQuanAo {

    private String id;
    private String idMau;
    private String idTheLoai;
    private String idKichCo;
    private String idChatLieu;
    private String tenQuanAo;
    private String maQuanAo;
    private Float giaBan;
    private int soLuong;
    private int trangThai;
    private String hinhAnh;

    public ChiTietQuanAo() {
    }

    public ChiTietQuanAo(String id, String idMau, String idTheLoai, String idKichCo, String idChatLieu, String tenQuanAo, String maQuanAo, Float giaBan, int soLuong, int trangThai, String hinhAnh) {
        this.id = id;
        this.idMau = idMau;
        this.idTheLoai = idTheLoai;
        this.idKichCo = idKichCo;
        this.idChatLieu = idChatLieu;
        this.tenQuanAo = tenQuanAo;
        this.maQuanAo = maQuanAo;
        this.giaBan = giaBan;
        this.soLuong = soLuong;
        this.trangThai = trangThai;
        this.hinhAnh = hinhAnh;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdMau() {
        return idMau;
    }

    public void setIdMau(String idMau) {
        this.idMau = idMau;
    }

    public String getIdTheLoai() {
        return idTheLoai;
    }

    public void setIdTheLoai(String idTheLoai) {
        this.idTheLoai = idTheLoai;
    }

    public String getIdKichCo() {
        return idKichCo;
    }

    public void setIdKichCo(String idKichCo) {
        this.idKichCo = idKichCo;
    }

    public String getIdChatLieu() {
        return idChatLieu;
    }

    public void setIdChatLieu(String idChatLieu) {
        this.idChatLieu = idChatLieu;
    }

    public String getTenQuanAo() {
        return tenQuanAo;
    }

    public void setTenQuanAo(String tenQuanAo) {
        this.tenQuanAo = tenQuanAo;
    }

    public String getMaQuanAo() {
        return maQuanAo;
    }

    public void setMaQuanAo(String maQuanAo) {
        this.maQuanAo = maQuanAo;
    }

    public Float getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(Float giaBan) {
        this.giaBan = giaBan;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

}
