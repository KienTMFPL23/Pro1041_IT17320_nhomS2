/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service.Impl;

import DomainModel.HoaDon;
import Repositories.IHoaDonReponsitory;
import Repositories.Impl.HoaDonReponsitory;
import Service.IHoaDonService;
import ViewModel.HoaDonViewModel;
import java.util.ArrayList;

/**
 *
 * @author BOSS
 */
public class HoaDonService implements IHoaDonService {

    private IHoaDonReponsitory hdr;

    public HoaDonService() {
        this.hdr = new HoaDonReponsitory();
    }

    @Override
    public ArrayList<HoaDonViewModel> getlist() {
        return this.hdr.selectAll();
    }

    @Override
    public Integer them(HoaDon hd) {
        try {
            return this.hdr.them(hd);
        } catch (Exception e) {
            return -1;
        }
    }

    @Override
    public Integer sua(String ma, HoaDon hd) {
        try {
            return this.hdr.sua(ma, hd);
        } catch (Exception e) {
            return -1;
        }
    }

    @Override
    public String checkMa(String ma) {
        return this.hdr.checkMa(ma);
    }

    @Override
    public ArrayList<HoaDon> selectList() {
        return this.hdr.selectList();
    }

    @Override
    public Integer hdCho(HoaDon hd) {
        try {
            return hdr.hdCho(hd);
        } catch (Exception e) {
            return -1;
        }
    }

    @Override
    public ArrayList<HoaDonViewModel> getAll() {
        return hdr.getList();
    }

    @Override
    public Integer updateTT(String ma, int tt) {
        try {
            return hdr.updateTrangThai(ma, tt);
        } catch (Exception e) {
            return -1;
        }
    }

    @Override
    public Integer updateHoaDon(HoaDon hd, String id) {
        try {
            return hdr.updateHoaDon(hd, id);
        } catch (Exception e) {
            return -1;
        }
    }

    @Override
    public ArrayList<HoaDonViewModel> dsHoaDon() {
        return hdr.dsHoaDon();
    }

    @Override
    public Float getSumMoney(String ma) {
        return hdr.getSumMoney(ma);
    }

}
