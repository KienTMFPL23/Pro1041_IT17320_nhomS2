/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service.Impl;

import DomainModel.users;
import Repositories.IDangNhapReponsitory;
import Repositories.Impl.DangNhapReponsitory;
import Service.IDangNhapService;
import ViewModel.UsersViewModel;

/**
 *
 * @author BOSS
 */
public class DangNhapService implements IDangNhapService{
 private IDangNhapReponsitory dnr ;
 public DangNhapService(){
     this.dnr = new DangNhapReponsitory();
 }

    @Override
    public users login(String ma, String matkhau) {
        return this.dnr.login(ma, matkhau);
    }
   
    
}
