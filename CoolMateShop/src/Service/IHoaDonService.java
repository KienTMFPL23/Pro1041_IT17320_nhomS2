/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Service;

import DomainModel.HoaDon;
import ViewModel.HoaDonViewModel;
import java.util.ArrayList;

/**
 *
 * @author BOSS
 */
public interface IHoaDonService {

    ArrayList<HoaDonViewModel> getlist();

    ArrayList<HoaDonViewModel> getAll();

    ArrayList<HoaDonViewModel> dsHoaDon();

    Integer them(HoaDon hd);

    Integer sua(String ma, HoaDon hd);

    String checkMa(String ma);

    ArrayList<HoaDon> selectList();

    Integer hdCho(HoaDon hd);

    Integer updateTT(String ma, int trangThai);

    Integer updateHoaDon(HoaDon hd, String id);
    
    Float getSumMoney( String ma);
}
