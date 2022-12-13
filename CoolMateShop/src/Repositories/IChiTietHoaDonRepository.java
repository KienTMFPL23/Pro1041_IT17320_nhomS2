/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Repositories;

import DomainModel.ChiTietHoaDon;
import ViewModel.ChiTietHoaDonRespone;
import java.util.List;

/**
 *
 * @author KIEN TRAN
 */
public interface IChiTietHoaDonRepository {

    List<ChiTietHoaDonRespone> getAll();

    List<ChiTietHoaDonRespone> getAllByMa(String ma);

    List<ChiTietHoaDonRespone> getAllHD(String id);
    
    Integer insert(ChiTietHoaDon cthd);

    Integer update(String id, int soLuong);

    Integer delete(String id);
    
    Integer getSLMua(String id);
    
    String getIdQA(String idHD, String idqa);
}
