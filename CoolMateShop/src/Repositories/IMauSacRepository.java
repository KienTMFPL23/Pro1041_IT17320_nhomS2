/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Repositories;

import DomainModel.MauSac;
import ViewModel.MauSacRespone;
import java.util.List;

/**
 *
 * @author KIEN TRAN
 */
public interface IMauSacRepository {

    List<MauSac> getAll();
    
    List<MauSacRespone> getAllMau();

    Integer insert(MauSac ms);

    Integer update(MauSac ms, String ma);

    Integer delete(String ma);

    String checkMa(String ma);
}
