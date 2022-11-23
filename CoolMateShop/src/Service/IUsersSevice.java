/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Service;

import DomainModel.users;
import java.util.ArrayList;

/**
 *
 * @author BOSS
 */
public interface IUsersSevice {

    ArrayList<users> getlist();

    Integer them(users Users);

    Integer xoa(String id);

    Integer sua(String id, users Users);
     String checkMa(String ma);

}
