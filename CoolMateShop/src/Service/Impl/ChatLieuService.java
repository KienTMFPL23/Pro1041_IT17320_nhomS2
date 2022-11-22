/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service.Impl;

import DomainModel.ChatLieu;
import Repositories.IChatLieuReponsitory;
import Repositories.Impl.ChatLieuReponsity;
import Service.IChatLieuService;
import Service.IChatLieuService;
import ViewModel.ChatLieuViewModel;
import ViewModel.ChatLieuViewModel;
import java.util.ArrayList;

/**
 *
 * @author BOSS
 */
public class ChatLieuService implements IChatLieuService {

    private IChatLieuReponsitory clr = new ChatLieuReponsity();

    public ChatLieuService() {
        this.clr = new ChatLieuReponsity();
    }

    @Override
    public ArrayList<ChatLieuViewModel> getList() {
        return this.clr.selectAll();
    }

    @Override
    public Integer them(ChatLieu cl) {
        try {
            return clr.them(cl);
        } catch (Exception e) {
            return -1;
        }
    }

    @Override
    public Integer sua(ChatLieu cl, String ma) {
        try {
            return clr.sua(cl, ma);
        } catch (Exception e) {
            return -1;
        }
    }

    @Override
    public Integer xoa(String ma) {
        try {
            return clr.xoa(ma);
        } catch (Exception e) {
            return -1;
        }
    }

    @Override
    public String checkMa(String ma) {
        return this.clr.checkMa(ma);
    }

    @Override
    public ArrayList<ChatLieu> getAll() {
        return this.clr.getAll();
    }

}
