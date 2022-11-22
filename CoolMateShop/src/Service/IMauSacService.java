/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Service;

import DomainModel.MauSac;
import ViewModel.MauSacRespone;
import java.util.List;

/**
 *
 * @author KIEN TRAN
 */
public interface IMauSacService {


    List<MauSacRespone> getAllMau();

    Integer insert(MauSac ms);

    Integer update(MauSac ms, String ma);

    Integer delete(String ma);

    String checkMa(String ma);
    
    MauSac getOne(String id);
    
    List<MauSac> getAll();
}
