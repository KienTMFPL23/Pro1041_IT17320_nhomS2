/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositories.Impl;

import DomainModel.KichCo;
import Repositories.IKichCoReponsitory;
import Utilities.DBcontext;
import ViewModel.KichCoRepon;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author BOSS
 */
public class KichCoReponsitory implements IKichCoReponsitory {

    @Override
    public ArrayList<KichCoRepon> selectall() {
        ArrayList<KichCoRepon> list = new ArrayList<>();
        try {
            Connection conn = DBcontext.getConnection();
            String sql = "select id ,  ma , size from kichco";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {

                String ma = rs.getString("ma");
                String size = rs.getString("size");
                KichCoRepon kcr = new KichCoRepon(ma, size);
                list.add(kcr);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Integer insert(KichCo kc) {
        try {
            Connection conn = DBcontext.getConnection();
            String sql = "INSERT INTO KichCo (Ma,size) values (?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, kc.getMa());
            ps.setString(2, kc.getSize());
            return ps.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
    }

    @Override
    public Integer delete(String ma) {
        try {
            Connection conn = DBcontext.getConnection();
            String sql = "Delete from KichCo where Ma = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, ma);

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public Integer update(String ma, KichCo kc) {
        try {
            Connection conn = DBcontext.getConnection();
            String sql = "Update KichCo set Size = ? where ma=? ";
            PreparedStatement ps = conn.prepareStatement(sql);
           
            ps.setString(1, kc.getSize());
             ps.setString(2, ma);
            return ps.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
    }

    @Override
    public String checkMa(String ma) {
        String checkMa = null;
        try {
            Connection conn = DBcontext.getConnection();
            String sql = "select Ma from KichCo where Ma = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, ma);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while(rs.next()){
                String maStr = rs.getString("Ma");
                checkMa = ma;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return checkMa;
    }

    

    

}
