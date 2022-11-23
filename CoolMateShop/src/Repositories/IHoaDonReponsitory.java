/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Repositories;

import DomainModel.HoaDon;
import ViewModel.HoaDonViewModel;
import java.util.ArrayList;

/**
 *
 * @author BOSS
 */
public interface IHoaDonReponsitory {
    ArrayList<HoaDonViewModel> selectAll();
    Integer them(HoaDon hd);
    Integer sua(String ma,HoaDon hd);
    String checkMa(String ma);
    ArrayList<HoaDon> selectList();
}
