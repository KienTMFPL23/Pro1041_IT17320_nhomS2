/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Repositories;

import DomainModel.TheLoai;
import ViewModel.TheLoaiViewModel;
import java.util.ArrayList;

/**
 *
 * @author BOSS
 */
public interface ITheLoaiReponsitory {

    ArrayList<TheLoaiViewModel> selectAll();

    Integer them(TheLoai tl);

    String checkMa(String ma);

    Integer xoa(String ma);

    Integer update(String matl, TheLoai tl);

    ArrayList<TheLoai> getAll();
}
