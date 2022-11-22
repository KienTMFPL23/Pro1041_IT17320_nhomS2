/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositories.Impl;

import DomainModel.TheLoai;
import Repositories.ITheLoaiReponsitory;
import Utilities.DBcontext;
import ViewModel.TheLoaiViewModel;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.html.HTMLDocument;

/**
 *
 * @author BOSS
 */
public class TheLoaiReponsitory implements ITheLoaiReponsitory {

    @Override
    public ArrayList<TheLoaiViewModel> selectAll() {
        ArrayList<TheLoaiViewModel> list = new ArrayList<>();
        try {
            Connection conn = DBcontext.getConnection();
            String sql = "select matl , tentl from theloai ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                String ma = rs.getString("matl");
                String ten = rs.getString("tentl");
                TheLoaiViewModel tlv = new TheLoaiViewModel(ma, ten);
                list.add(tlv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Integer them(TheLoai tl) {
        try {
            Connection conn = DBcontext.getConnection();
            String sql = "insert into theloai(matl,tentl) values(?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, tl.getMatl());
            ps.setString(2, tl.getTentl());
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public String checkMa(String ma) {
        String checkMa = null;
        try {
            Connection conn = DBcontext.getConnection();
            String sql = "select matl from chatlieu where matl = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, ma);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                String Ma = rs.getString("Matl");
                checkMa = ma;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return checkMa;
    }

    @Override
    public Integer xoa(String ma) {
        try {
            Connection conn = DBcontext.getConnection();
            String sql = "delete from TheLoai where MaTL = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, ma);

            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public Integer update(String matl, TheLoai tl) {
        try {
            Connection conn = DBcontext.getConnection();
            String sql = "update theloai set tentl = ? where matl = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, tl.getTentl());
            ps.setString(2, matl);

            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public ArrayList<TheLoai> getAll() {
        ArrayList<TheLoai> listTheLoai = new ArrayList<>();
        try {
            Connection conn = DBcontext.getConnection();
            String sql = "Select Id,MaTL,TenTL from TheLoai";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.execute();

            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                String id = rs.getString(1);
                String ma = rs.getString(2);
                String ten = rs.getString(3);

                TheLoai tl = new TheLoai(id, ma, ten);
                listTheLoai.add(tl);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listTheLoai;
    }

}
