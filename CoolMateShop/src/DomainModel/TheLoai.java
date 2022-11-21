/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DomainModel;

/**
 *
 * @author BOSS
 */
public class TheLoai {
    private String id;
    private String matl;
    private String tentl;

    public TheLoai(String id, String matl, String tentl) {
        this.id = id;
        this.matl = matl;
        this.tentl = tentl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public TheLoai() {
    }
    
}
