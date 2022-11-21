/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service.Impl;

import DomainModel.TheLoai;
import Repositories.ITheLoaiReponsitory;
import Repositories.Impl.TheLoaiReponsitory;
import Service.ITheLoaiService;
import ViewModel.TheLoaiViewModel;
import java.util.ArrayList;

/**
 *
 * @author BOSS
 */
public class TheLoaiService  implements ITheLoaiService{
private ITheLoaiReponsitory tlr = new TheLoaiReponsitory();
public TheLoaiService(){
    this.tlr =new TheLoaiReponsitory();
    }
    @Override
    public ArrayList<TheLoaiViewModel> getlist() {
        return this.tlr.selectAll();
    }

    @Override
    public Integer them(TheLoai tl) {
        try {
            return tlr.them(tl);
        } catch (Exception e) {
            return -1;
        }
    }

    @Override
    public String checkMa(String ma) {
        return this.tlr.checkMa(ma);
    }

    @Override
    public Integer xoa(String ma) {
         try {
            return tlr.xoa(ma);
        } catch (Exception e) {
            return -1;
        }
    }

    @Override
    public Integer sua(String ma, TheLoai tl) {
         try {
            return tlr.sua(ma, tl);
        } catch (Exception e) {
            return -1;
        }
    }
    
}
