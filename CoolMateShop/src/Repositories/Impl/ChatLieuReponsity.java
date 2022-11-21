/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositories.Impl;

import DomainModel.ChatLieu;
import Repositories.IChatLieuReponsitory;
import Service.IChatLieuService;
import Utilities.DBcontext;
import ViewModel.ChatLieuViewModel;
import java.util.ArrayList;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author BOSS
 */
public class ChatLieuReponsity implements IChatLieuReponsitory {

    @Override
    public ArrayList<ChatLieuViewModel> selectAll() {
        ArrayList<ChatLieuViewModel> list = new ArrayList<>();
        try {
            Connection conn = DBcontext.getConnection();
            String sql = "select ma , ten from chatlieu ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                String ma = rs.getString("Ma");
                String ten = rs.getString("Ten");
                ChatLieuViewModel clv = new ChatLieuViewModel(ma, ten);
                list.add(clv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Integer them(ChatLieu cl) {
        try {
            Connection conn = DBcontext.getConnection();
            String sql = "insert into chatlieu (ma,ten) values (?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, cl.getMa());
            ps.setString(2, cl.getTen());
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public Integer sua(ChatLieu cl, String ma) {
        try {
            Connection conn = DBcontext.getConnection();
            String sql = "update chatlieu set ten = ?  where ma = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, cl.getTen());
            ps.setString(2, ma);

            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public Integer xoa(String ma) {
try {
            Connection conn = DBcontext.getConnection();
            String sql = "delete from chatlieu where ma = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
        
            ps.setString(1, ma);

            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;    }

    @Override
    public String checkMa(String ma) {
        String checkMa = null;
        try {
            Connection conn = DBcontext.getConnection();
            String sql = "select ma from chatlieu where ma = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, ma);
                 ps.execute();
                 ResultSet rs = ps.getResultSet();
                 while(rs.next()){
                     String Ma = rs.getString("Ma");
                     checkMa = ma;
                     
                 }
        } catch (Exception e) {
        }
        return checkMa;
    }

}
