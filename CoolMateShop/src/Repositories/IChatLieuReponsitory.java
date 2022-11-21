/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Repositories;

import DomainModel.ChatLieu;
import ViewModel.ChatLieuViewModel;
import java.util.ArrayList;

/**
 *
 * @author BOSS
 */
public interface IChatLieuReponsitory {
    ArrayList<ChatLieuViewModel> selectAll();
    Integer them(ChatLieu cl);
    Integer sua(ChatLieu cl , String ma);
    Integer xoa(String ma);
    String checkMa(String ma);
    
}
