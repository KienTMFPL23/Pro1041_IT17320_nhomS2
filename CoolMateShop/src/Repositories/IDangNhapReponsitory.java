/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Repositories;

import DomainModel.users;
import ViewModel.UsersViewModel;

/**
 *
 * @author BOSS
 */
public interface IDangNhapReponsitory {
    users login (String ma, String matkhau);
}
