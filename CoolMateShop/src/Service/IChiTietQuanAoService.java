/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Service;

import DomainModel.ChiTietQuanAo;
import ViewModel.ChiTietQuanAoRespone;
import java.util.List;

/**
 *
 * @author KIEN TRAN
 */
public interface IChiTietQuanAoService {

    List<ChiTietQuanAoRespone> getAllCTQA();

    List<ChiTietQuanAo> getAll();

    Integer insert(ChiTietQuanAo ctqa);

    Integer update(ChiTietQuanAo ctqa, String ma);

    Integer updateSoLuong(String id, int soLuong);

    Integer getSLTon(String id);

    Integer delete(String ma);

    List<ChiTietQuanAoRespone> searchByMa(String ma);

    String checkMa(String ma);
}
