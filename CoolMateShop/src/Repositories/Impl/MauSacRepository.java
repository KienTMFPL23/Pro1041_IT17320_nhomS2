/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositories.Impl;

import DomainModel.MauSac;
import Repositories.IMauSacRepository;
import Utilities.DBcontext;
import ViewModel.MauSacRespone;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author KIEN TRAN
 */
public class MauSacRepository implements IMauSacRepository {

    @Override
    public Integer insert(MauSac ms) {
        try {
            Connection conn = DBcontext.getConnection();
            String sql = "INSERT INTO MauSac (MaMau,TenMau) values (?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, ms.getMa());
            ps.setString(2, ms.getTen());
            return ps.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
    }

    @Override
    public Integer update(MauSac ms, String ma) {
        try {
            Connection conn = DBcontext.getConnection();
            String sql = "Update MauSac set TenMau = ? where MaMau = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, ms.getTen());
            ps.setString(2, ma);

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
            String sql = "Delete from MauSac where MaMau = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, ma);

            return ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
    }

    @Override
    public String checkMa(String ma) {
        String maCheck = null;
        try {
            Connection conn = DBcontext.getConnection();
            String sql = "Select MaMau from MauSac Where MaMau = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, ma);

            ps.execute();
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                String maSearch = rs.getString("MaMau");
                maCheck = maSearch;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return maCheck;
    }

    @Override
    public List<MauSacRespone> getAllMau() {
        List<MauSacRespone> listMauSac = new ArrayList<>();
        try {
            Connection conn = DBcontext.getConnection();
            String sql = "Select Id,MaMau,TenMau from MauSac ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.execute();

            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                String id = rs.getString(1);
                String ma = rs.getString(2);
                String ten = rs.getString(3);

                MauSacRespone ms = new MauSacRespone(ma, ten);
                listMauSac.add(ms);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listMauSac;
    }

    @Override
    public MauSac getOne(String id) {
        try {
            Connection conn = DBcontext.getConnection();
            String sql = "Select Id,MaMau,TenMau from MauSac where Id = ? ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            ps.execute();

            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                String idSearch = rs.getString(1);
                String ma = rs.getString(2);
                String ten = rs.getString(3);

                MauSac ms = new MauSac(idSearch, ma, ten);
                return ms;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public List<MauSac> getList() {
        List<MauSac> listMau = new ArrayList<>();
        try {
            Connection conn = DBcontext.getConnection();
            String sql = "Select Id,MaMau,TenMau from MauSac";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.execute();

            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                String id = rs.getString(1);
                String ma = rs.getString(2);
                String ten = rs.getString(3);

                MauSac ms = new MauSac(id, ma, ten);
                listMau.add(ms);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listMau;
    }

}
