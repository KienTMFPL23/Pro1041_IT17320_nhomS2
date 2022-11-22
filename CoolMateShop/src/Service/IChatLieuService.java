/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Service;

import DomainModel.ChatLieu;
import ViewModel.ChatLieuViewModel;
import java.util.ArrayList;

/**
 *
 * @author BOSS
 */
public interface IChatLieuService {

    ArrayList<ChatLieuViewModel> getList();

    Integer them(ChatLieu cl);

    Integer sua(ChatLieu cl, String ma);

    Integer xoa(String ma);

    String checkMa(String ma);

    ArrayList<ChatLieu> getAll();
}
