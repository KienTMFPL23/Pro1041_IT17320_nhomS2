/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service.Impl;

import DomainModel.users;
import Repositories.IUsersReponsitory;
import Repositories.Impl.UsersReponsitory;
import Service.IUsersSevice;
import java.util.ArrayList;

/**
 *
 * @author BOSS
 */
public class UsersSerice implements IUsersSevice {

    private IUsersReponsitory usr = new UsersReponsitory();
    
    public UsersSerice() {
        this.usr = new UsersReponsitory();
    }

    @Override
    public ArrayList<users> getlist() {
        return this.usr.selectAll();
    }
    
    @Override
    public Integer them(users Users) {
        try {
            this.usr.them(Users);
        } catch (Exception e) {
        }
        return -1;
    }
    
    @Override
    public Integer xoa(String id) {
        try {
            this.usr.xoa(id);
        } catch (Exception e) {
        }
        return -1;
    }
    
    @Override
    public Integer sua(String id, users Users) {
        try {
            this.usr.sua(id, Users);
        } catch (Exception e) {
        }
        return -1;
    }

    @Override
    public String checkMa(String ma) {
        return this.usr.checkMa(ma);
    }
    
}
