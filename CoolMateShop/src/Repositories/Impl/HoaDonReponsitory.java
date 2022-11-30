/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositories.Impl;

import DomainModel.HoaDon;
import Repositories.IHoaDonReponsitory;
import Utilities.DBcontext;
import java.awt.datatransfer.DataFlavor;
import ViewModel.HoaDonViewModel;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author BOSS
 */
public class HoaDonReponsitory implements IHoaDonReponsitory {

    @Override
    public ArrayList<HoaDonViewModel> selectAll() {
        ArrayList<HoaDonViewModel> list = new ArrayList<>();
        try {
            Connection conn = DBcontext.getConnection();
            String sql = "select khachhang.HoTen as 'khachhang',users.HoTen as 'us' ,HoaDon.mahd,NgayTao,NgayThanhToan,TinhTrang\n"
                    + "from HoaDon join KhachHang   on HoaDon.IdKH = KhachHang.Id\n"
                    + "join Users on Users.id = HoaDon.IdUser";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                String khachhang = rs.getString("khachhang");
                String nguoidung = rs.getString("us");
                String mahd = rs.getString("mahd");
                java.sql.Date ngayTao = rs.getDate("ngaytao");
                java.sql.Date ngaythanhtoan = rs.getDate("ngaythanhtoan");
                int trangthai = rs.getInt("TinhTrang");
                HoaDonViewModel hd = new HoaDonViewModel(khachhang, nguoidung, mahd, ngayTao, ngaythanhtoan, trangthai);
                list.add(hd);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Integer them(HoaDon hd) {
        try {
            Connection conn = DBcontext.getConnection();
            String sql = "insert into hoadon(idkh,iduser,mahd,ngaytao,ngaythanhtoan,tinhtrang) values (?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, hd.getIdKH());
            ps.setString(2, hd.getIdUser());
            ps.setString(3, hd.getMaHd());
            ps.setDate(4, new java.sql.Date(hd.getNgayTao().getTime()));
            ps.setDate(5, new java.sql.Date(hd.getNgayThanhToan().getTime()));
            ps.setInt(6, hd.getTinhTrang());
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public Integer sua(String ma, HoaDon hd) {
        try {
            Connection conn = DBcontext.getConnection();
            String sql = "update hoadon set idkh = ? , iduser=?,ngaytao = ?,ngaythanhtoan=? ,tinhtrang=? where mahd = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, hd.getIdKH());
            ps.setString(2, hd.getIdUser());

            ps.setDate(3, new java.sql.Date(hd.getNgayTao().getTime()));
            ps.setDate(4, new java.sql.Date(hd.getNgayThanhToan().getTime()));
            ps.setInt(5, hd.getTinhTrang());
            ps.setString(6, ma);
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public String checkMa(String ma) {
        String maCheck = null;
        try {
            Connection conn = DBcontext.getConnection();
            String sql = "select mahd from hoadon where mahd =?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, ma);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                String Ma = rs.getString("mahd");
                maCheck = Ma;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return maCheck;
    }

    @Override
    public ArrayList<HoaDon> selectList() {
        ArrayList<HoaDon> list = new ArrayList<>();
        try {
            Connection conn = DBcontext.getConnection();
            String sql = "select * from hoadon";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                String id = rs.getString("id");
                String khachhang = rs.getString("idkh");
                String nguoidung = rs.getString("iduser");
                String mahd = rs.getString("mahd");
                java.sql.Date ngayTao = rs.getDate("ngaytao");
                java.sql.Date ngaythanhtoan = rs.getDate("ngaythanhtoan");
                int trangthai = rs.getInt("TinhTrang");
                HoaDon hd = new HoaDon(id, khachhang, nguoidung, mahd, ngayTao, ngaythanhtoan, trangthai);
                list.add(hd);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Integer hdCho(HoaDon hd) {
        try {
            Connection conn = DBcontext.getConnection();
            String sql = "insert into hoadon(mahd,ngaytao,tinhtrang) values (?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, hd.getMaHd());
            ps.setDate(2, new java.sql.Date(hd.getNgayTao().getTime()));
            ps.setInt(3, hd.getTinhTrang());
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public ArrayList<HoaDonViewModel> getList() {
        ArrayList<HoaDonViewModel> list = new ArrayList<>();
        try {
            Connection conn = DBcontext.getConnection();
            String sql = "select * from HoaDon where TinhTrang = 0";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                String id = rs.getString("Id");
                String khachhang = rs.getString("IdKH");
                String nguoidung = rs.getString("IdUser");
                String mahd = rs.getString("mahd");
                java.sql.Date ngayTao = rs.getDate("ngaytao");
                java.sql.Date ngaythanhtoan = rs.getDate("ngaythanhtoan");
                int trangthai = rs.getInt("TinhTrang");
                HoaDonViewModel hd = new HoaDonViewModel(id, khachhang, nguoidung, mahd, ngayTao, ngaythanhtoan, trangthai);
                list.add(hd);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Integer updateTrangThai(String ma, int trangThai) {
        try {
            Connection conn = DBcontext.getConnection();
            String sql = "update hoadon set tinhtrang=? where mahd = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, trangThai);
            ps.setString(2, ma);
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public Integer updateHoaDon(HoaDon hd, String id) {
        try {
            Connection conn = DBcontext.getConnection();
            String sql = "update hoadon set idkh = ? , iduser=?,ngaythanhtoan=? ,tinhtrang=? where Id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, hd.getIdKH());
            ps.setString(2, hd.getIdUser());

            ps.setDate(3, new java.sql.Date(hd.getNgayThanhToan().getTime()));
            ps.setInt(4, hd.getTinhTrang());
            ps.setString(5, id);
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

}
