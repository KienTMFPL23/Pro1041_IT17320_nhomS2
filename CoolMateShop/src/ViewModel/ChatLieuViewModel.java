/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ViewModel;

/**
 *
 * @author BOSS
 */
public class ChatLieuViewModel {
    private String ma;
    private String ten;

    public ChatLieuViewModel(String ma, String ten) {
        this.ma = ma;
        this.ten = ten;
    }

    public ChatLieuViewModel() {
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }
    
}
