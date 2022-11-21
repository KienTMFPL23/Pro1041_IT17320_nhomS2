/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ViewModel;

/**
 *
 * @author BOSS
 */
public class TheLoaiViewModel {
    private String matl;
    private String tentl;

    public TheLoaiViewModel(String matl, String tentl) {
        this.matl = matl;
        this.tentl = tentl;
    }

    public TheLoaiViewModel() {
    }

    public String getMatl() {
        return matl;
    }

    public void setMatl(String matl) {
        this.matl = matl;
    }

    public String getTentl() {
        return tentl;
    }

    public void setTentl(String tentl) {
        this.tentl = tentl;
    }
    
}
