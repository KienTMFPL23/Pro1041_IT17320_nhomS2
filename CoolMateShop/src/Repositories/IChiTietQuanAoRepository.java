/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Repositories;

import DomainModel.ChiTietQuanAo;
import ViewModel.ChiTietQuanAoRespone;
import java.util.List;

/**
 *
 * @author KIEN TRAN
 */
public interface IChiTietQuanAoRepository {

    List<ChiTietQuanAoRespone> getAll();

    Integer insert(ChiTietQuanAo ctqa);

    Integer update(ChiTietQuanAo ctqa, String ma);

    Integer delete(String ma);
    
    String checkMa(String ma);
}
