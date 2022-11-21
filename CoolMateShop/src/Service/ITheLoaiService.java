/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Service;

import DomainModel.TheLoai;
import ViewModel.TheLoaiViewModel;
import java.util.ArrayList;

/**
 *
 * @author BOSS
 */
public interface ITheLoaiService {
    ArrayList<TheLoaiViewModel> getlist();
    Integer them(TheLoai tl);
    String checkMa(String ma);
     Integer xoa(String ma);
    Integer sua(String ma,TheLoai tl);
}
