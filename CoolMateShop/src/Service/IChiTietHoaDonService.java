/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Service;

import DomainModel.ChiTietHoaDon;
import ViewModel.ChiTietHoaDonRespone;
import java.util.List;

/**
 *
 * @author KIEN TRAN
 */
public interface IChiTietHoaDonService {

    List<ChiTietHoaDonRespone> getAll();

    List<ChiTietHoaDonRespone> getAllByMa(String ma);

    List<ChiTietHoaDonRespone> getListHD(String id);

    Integer insert(ChiTietHoaDon cthd);

    Integer updateSLuong(String id, int soLuong);

    Integer getSlMua(String id);

    Integer delete(String id);

    String getIdQA(String id,String idqa);
}
