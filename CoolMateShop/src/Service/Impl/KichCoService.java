/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service.Impl;

import DomainModel.KichCo;
import DomainModel.MauSac;
import Repositories.IKichCoReponsitory;
import Repositories.Impl.KichCoReponsitory;
import Service.IKichCoService;
import ViewModel.KichCoRepon;
import java.util.ArrayList;

/**
 *
 * @author BOSS
 */
public class KichCoService implements IKichCoService {

    private IKichCoReponsitory kcr = new KichCoReponsitory();

    public KichCoService() {
        this.kcr = new KichCoReponsitory();
    }

    @Override
    public ArrayList<KichCoRepon> getList() {
        return this.kcr.selectall();
    }

    @Override
    public Integer insert(KichCo kc) {
        try {
            return kcr.insert(kc);
        } catch (Exception e) {
            return -1;
        }
    }

    @Override
    public Integer delete(String ma) {
        try {
            return kcr.delete(ma);
        } catch (Exception e) {
            return -1;
        }
    }

    @Override
    public Integer update(String ma, KichCo kc) {
        try {
            return kcr.update(ma, kc);
        } catch (Exception e) {
            return -1;
        }
    }

    @Override
    public String checkMa(String ma) {
        return this.kcr.checkMa(ma);
    }

    @Override
    public ArrayList<KichCo> getAll() {
        return this.kcr.getAll();
    }

    @Override
    public KichCo getOne(String id) {
        return null;
    }

}
