/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositories.Impl;

import DomainModel.ChiTietHoaDon;
import Repositories.IChiTietHoaDonRepository;
import Utilities.DBcontext;
import ViewModel.ChiTietHoaDonRespone;
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
public class ChiTietHoaDonRepository implements IChiTietHoaDonRepository {

    @Override
    public List<ChiTietHoaDonRespone> getAll() {
        List<ChiTietHoaDonRespone> listCTHD = new ArrayList<>();
        try {
            Connection conn = DBcontext.getConnection();
            String sql = "select cthd.Id, cthd.IdChiTietQA,hd.MaHD,ctqa.MaQuanAo,ctqa.TenQuanAo,cthd.SoLuong,cthd.DonGia from ChiTietHoaDon cthd  join HoaDon hd on cthd.IdHD = hd.Id\n"
                    + " join ChiTietQuanAo ctqa on cthd.IdChiTietQA = ctqa.Id ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.execute();

            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                String id = rs.getString("Id");
                String idqa = rs.getString("IdChiTietQA");
                String ma = rs.getString("MaHD");
                String maqa = rs.getString("MaQuanAo");
                String ten = rs.getString("TenQuanAo");
                Integer soLuong = rs.getInt("SoLuong");
                Float donGia = rs.getFloat("DonGia");

                ChiTietHoaDonRespone cthd = new ChiTietHoaDonRespone(id, idqa, ma, maqa, ten, soLuong, donGia);
                listCTHD.add(cthd);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listCTHD;
    }

    @Override
    public Integer insert(ChiTietHoaDon cthd) {
        try {
            Connection conn = DBcontext.getConnection();
            String sql = "insert into ChiTietHoaDon(IdHD,IdChiTietQA,SoLuong,DonGia) values(?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, cthd.getIdHD());
            ps.setString(2, cthd.getIdChiTietQA());
            ps.setInt(3, cthd.getSoLuong());
            ps.setFloat(4, cthd.getDonGia());

            return ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
    }

    @Override
    public Integer update(String id, int soLuong) {
        try {
            Connection conn = DBcontext.getConnection();
            String sql = "Update ChiTietHoaDon set SoLuong = ? where Id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, soLuong);
            ps.setString(2, id);

            return ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
    }

    @Override
    public List<ChiTietHoaDonRespone> getAllByMa(String ma) {
        List<ChiTietHoaDonRespone> listCTHD = new ArrayList<>();
        try {
            Connection conn = DBcontext.getConnection();
            String sql = "select hd.MaHD,ctqa.TenQuanAo,ctqa.MaQuanAo,ctqa.SoLuong,cthd.DonGia from ChiTietHoaDon cthd  join HoaDon hd on cthd.IdHD = hd.Id\n"
                    + "                   join ChiTietQuanAo ctqa on cthd.IdChiTietQA = ctqa.Id where ctqa.MaQuanAo =  ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, ma);
            ps.execute();

            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                String mahd = rs.getString("MaHD");
                String maqa = rs.getString("MaQuanAo");
                String ten = rs.getString("TenQuanAo");
                Integer soLuong = rs.getInt("SoLuong");
                Float donGia = rs.getFloat("DonGia");

                ChiTietHoaDonRespone cthd = new ChiTietHoaDonRespone(mahd, maqa, ten, soLuong, donGia);
                listCTHD.add(cthd);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listCTHD;
    }

    @Override
    public List<ChiTietHoaDonRespone> getAllHD(String id) {
        List<ChiTietHoaDonRespone> listCTHD = new ArrayList<>();
        try {
            Connection conn = DBcontext.getConnection();
            String sql = "	select cthd.Id,hd.MaHD,cthd.IdChiTietQA,ctqa.MaQuanAo,ctqa.TenQuanAo,cthd.SoLuong,cthd.DonGia from HoaDon hd join ChiTietHoaDon cthd on hd.Id = cthd.IdHD\n"
                    + "							join ChiTietQuanAo ctqa on cthd.IdChiTietQA = ctqa.Id where cthd.IdHD = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            ps.execute();

            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                String idCTHD = rs.getString("Id");
                String idCTQA = rs.getString("IdChiTietQA");
                String mahd = rs.getString("MaHD");
                String maqa = rs.getString("MaQuanAo");
                String ten = rs.getString("TenQuanAo");
                Integer soLuong = rs.getInt("SoLuong");
                Float donGia = rs.getFloat("DonGia");

                ChiTietHoaDonRespone cthd = new ChiTietHoaDonRespone(idCTHD, idCTQA, mahd, maqa, ten, soLuong, donGia);
                listCTHD.add(cthd);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listCTHD;
    }

    @Override
    public Integer delete(String id) {
        try {
            Connection conn = DBcontext.getConnection();
            String sql = "Delete from ChiTietHoaDon where Id = ? ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, id);

            return ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
    }

    @Override
    public Integer getSLMua(String id) {
        int soLuongMua = 0;
        try {
            Connection conn = DBcontext.getConnection();
            String sql = "Select SoLuong from ChiTietHoaDon where Id = ? ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            ps.execute();

            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                int soLuong = rs.getInt("SoLuong");
                soLuongMua = soLuong;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return soLuongMua;
    }

}
