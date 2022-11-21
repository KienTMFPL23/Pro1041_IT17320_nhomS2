/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Service;

import DomainModel.KichCo;
import DomainModel.MauSac;
import ViewModel.KichCoRepon;
import java.util.ArrayList;

/**
 *
 * @author BOSS
 */
public interface IKichCoService {

    ArrayList<KichCoRepon> getList();

    Integer insert(KichCo kc);

    Integer delete(String ma);

    Integer update(String ma, KichCo kc);
    String checkMa(String ma);
}
