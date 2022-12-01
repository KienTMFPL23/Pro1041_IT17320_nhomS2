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
public class users {
    private String id ;
    private String ma ;
    private String Hoten;
    private Date ngaysinh;
    private String gioiTinh;
    private String sdt;
    private String DiaChi;
    private String email;
    private int vaiTro;
    private String MatKhau;

    public users(String id, String ma, String Hoten, Date ngaysinh, String gioiTinh, String sdt, String DiaChi, String email, int vaiTro, String MatKhau) {
        this.id = id;
        this.ma = ma;
        this.Hoten = Hoten;
        this.ngaysinh = ngaysinh;
        this.gioiTinh = gioiTinh;
        this.sdt = sdt;
        this.DiaChi = DiaChi;
        this.email = email;
        this.vaiTro = vaiTro;
        this.MatKhau = MatKhau;
    }

    public users() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getHoten() {
        return Hoten;
    }

    public void setHoten(String Hoten) {
        this.Hoten = Hoten;
    }

    public Date getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(Date ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getVaiTro() {
        return vaiTro;
    }

    public void setVaiTro(int vaiTro) {
        this.vaiTro = vaiTro;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public void setMatKhau(String MatKhau) {
        this.MatKhau = MatKhau;
    }

    @Override
    public String toString() {
        return  Hoten ;
    }

   
}
