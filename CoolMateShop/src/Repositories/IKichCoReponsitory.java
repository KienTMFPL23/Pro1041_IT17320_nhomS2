/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Repositories;

import DomainModel.KichCo;
import ViewModel.KichCoRepon;
import java.util.ArrayList;

/**
 *
 * @author BOSS
 */
public interface IKichCoReponsitory {

    ArrayList<KichCoRepon> selectall();

    Integer insert(KichCo kc);

    Integer delete(String ma);

    Integer update(String ma, KichCo kc);

    String checkMa(String ma);

    ArrayList<KichCo> getAll();

}
