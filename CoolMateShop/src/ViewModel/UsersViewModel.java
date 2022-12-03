/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ViewModel;

/**
 *
 * @author BOSS
 */
public class UsersViewModel {
    private String Ma;
    private String matKhau;
    private int vaitro;

    public UsersViewModel(String Ma, String matKhau, int vaitro) {
        this.Ma = Ma;
        this.matKhau = matKhau;
        this.vaitro = vaitro;
    }

    public UsersViewModel() {
    }

    public String getMa() {
        return Ma;
    }

    public void setMa(String Ma) {
        this.Ma = Ma;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public int getVaitro() {
        return vaitro;
    }

    public void setVaitro(int vaitro) {
        this.vaitro = vaitro;
    }

    
    
    
}
