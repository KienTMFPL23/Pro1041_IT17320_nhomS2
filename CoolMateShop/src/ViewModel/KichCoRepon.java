/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ViewModel;

/**
 *
 * @author BOSS
 */
public class KichCoRepon {
    private String maKC;
    private String tenKC;

    public KichCoRepon(String maKC, String tenKC) {
        this.maKC = maKC;
        this.tenKC = tenKC;
    }

    public KichCoRepon() {
    }

    public String getMaKC() {
        return maKC;
    }

    public void setMaKC(String maKC) {
        this.maKC = maKC;
    }

    public String getTenKC() {
        return tenKC;
    }

    public void setTenKC(String tenKC) {
        this.tenKC = tenKC;
    }
    
}
