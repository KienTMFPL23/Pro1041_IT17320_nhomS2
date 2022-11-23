/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositories.Impl;

import DomainModel.ChiTietQuanAo;
import Repositories.IChiTietQuanAoRepository;
import Utilities.DBcontext;
import ViewModel.ChiTietQuanAoRespone;
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
public class ChiTietQuanAoRepository implements IChiTietQuanAoRepository {

    @Override
    public List<ChiTietQuanAoRespone> getAll() {
        List<ChiTietQuanAoRespone> listCTQA = new ArrayList<>();
        try {
            Connection conn = DBcontext.getConnection();
            String sql = "select ctqa.Id, ctqa.MaQuanAo , ctqa.TenQuanAo,tl.TenTL,kc.Size,ms.TenMau,cl.Ten as 'TenCL',ctqa.GiaBan,ctqa.SoLuong,ctqa.HinhAnh "
                    + "from ChiTietQuanAo ctqa join MauSac ms on ctqa.IdMau = ms.Id\n"
                    + "			join ChatLieu cl on ctqa.IdChatLieu = cl.Id\n"
                    + "			join TheLoai tl on ctqa.IdTheLoai = tl.Id\n"
                    + "			join KichCo kc on ctqa.IdKichCo = kc.Id";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.execute();

            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                String id = rs.getString("Id");
                String ma = rs.getString("MaQuanAo");
                String ten = rs.getString("TenQuanAo");
                String theLoai = rs.getString("TenTL");
                String kichCo = rs.getString("Size");
                String mau = rs.getString("TenMau");
                String chatLieu = rs.getString("TenCL");
                Float giaBan = rs.getFloat("GiaBan");
                Integer soLuong = rs.getInt("SoLuong");
                String hinhAnh = rs.getString("HinhAnh");

                ChiTietQuanAoRespone ctqar = new ChiTietQuanAoRespone();
                ctqar.setMaSP(ma);
                ctqar.setTenSP(ten);
                ctqar.setLoaiSP(theLoai);
                ctqar.setKichCo(kichCo);
                ctqar.setMauSac(mau);
                ctqar.setChatLieu(chatLieu);
                ctqar.setGiaTien(giaBan);
                ctqar.setSoLuong(soLuong);
                ctqar.setHinhAnh(hinhAnh);

                listCTQA.add(ctqar);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listCTQA;
    }

    @Override
    public Integer insert(ChiTietQuanAo ctqa) {
        try {
            Connection conn = DBcontext.getConnection();
            String sql = "INSERT INTO ChiTietQuanAo( IdMau, IdTheLoai,"
                    + "IdKichCo,IdChatLieu,TenQuanAo,MaQuanAo,GiaBan,SoLuong, TrangThai,HinhAnh) "
                    + "VALUES(?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, ctqa.getIdMau());
            ps.setString(2, ctqa.getIdTheLoai());
            ps.setString(3, ctqa.getIdKichCo());
            ps.setString(4, ctqa.getIdChatLieu());
            ps.setString(5, ctqa.getTenQuanAo());
            ps.setString(6, ctqa.getMaQuanAo());
            ps.setFloat(7, ctqa.getGiaBan());
            ps.setInt(8, ctqa.getSoLuong());
            ps.setInt(9, ctqa.getTrangThai());
            ps.setString(10, ctqa.getHinhAnh());
            return ps.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
    }

    @Override
    public Integer update(ChiTietQuanAo ctqa, String ma) {
        try {
            Connection conn = DBcontext.getConnection();
            String sql = "UPDATE ChiTietQuanAo SET  IdMau =?, IdTheLoai = ?,"
                    + "IdKichCo = ?,IdChatLieu = ?,TenQuanAo = ?,MaQuanAo = ?,GiaBan = ?,SoLuong = ?, TrangThai = ?,HinhAnh =? WHERE MaQuanAo = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, ctqa.getIdMau());
            ps.setString(2, ctqa.getIdTheLoai());
            ps.setString(3, ctqa.getIdKichCo());
            ps.setString(4, ctqa.getIdChatLieu());
            ps.setString(5, ctqa.getTenQuanAo());
            ps.setString(6, ctqa.getMaQuanAo());
            ps.setFloat(7, ctqa.getGiaBan());
            ps.setInt(8, ctqa.getSoLuong());
            ps.setInt(9, ctqa.getTrangThai());
            ps.setString(10, ctqa.getHinhAnh());
            ps.setString(11, ma);

            return ps.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
    }

    @Override
    public Integer delete(String ma) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String checkMa(String ma) {
        String maSearch = null;
        try {
            Connection conn = DBcontext.getConnection();
            String sql = "Select MaQuanAo from ChiTietQuanAo where MaQuanAo = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, ma);
            ps.execute();

            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                String maCheck = rs.getString("MaQuanAo");

                maSearch = maCheck;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return maSearch;
    }

    @Override
    public List<ChiTietQuanAo> getQuanAo() {
        List<ChiTietQuanAo> listCTQA = new ArrayList<>();
        try {
            Connection conn = DBcontext.getConnection();
            String sql = "Select * from ChiTietQuanAo";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.execute();

            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                String id = rs.getString(1);
                String idMau = rs.getString(2);
                String idTL = rs.getString(3);
                String idKC = rs.getString(4);
                String idCL = rs.getString(5);
                String tenQA = rs.getString(6);
                String maQA = rs.getString(7);
                Float giaBan = rs.getFloat(8);
                Integer soLuong = rs.getInt(9);
                Integer trangThai = rs.getInt(10);
                String anh = rs.getString(11);

                ChiTietQuanAo ctqa = new ChiTietQuanAo(id, idMau, idTL, idKC, idCL, tenQA, maQA, giaBan, soLuong, trangThai, anh);

                listCTQA.add(ctqa);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listCTQA;
    }

}
