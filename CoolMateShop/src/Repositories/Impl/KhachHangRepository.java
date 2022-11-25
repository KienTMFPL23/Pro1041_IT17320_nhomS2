package Repositories.Impl;

import DomainModel.KhachHang;
import Repositories.IKhachHangRepository;
import Utilities.DBcontext;
import Utilities.DBcontext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class KhachHangRepository implements IKhachHangRepository {

    @Override
    public List<KhachHang> getAll() {
        List<KhachHang> listKhachHang = new ArrayList<>();

        try {
            Connection conn = DBcontext.getConnection();

            String sql = "Select Id,Ma,HoTen,NgaySinh,GioiTinh,Sdt,DiaChi from KhachHang";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.execute();

            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                String id = rs.getString("Id");
                String ma = rs.getString("Ma");
                String ten = rs.getString("HoTen");
                Date ngaySinh = rs.getDate("NgaySinh");
                String gioiTinh = rs.getString("GioiTinh");
                String sdt = rs.getString("Sdt");
                String diaChi = rs.getString("DiaChi");

                KhachHang kh = new KhachHang(id, ma, ten, ngaySinh, gioiTinh, sdt, diaChi);

                listKhachHang.add(kh);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listKhachHang;
    }

    @Override
    public Integer insert(KhachHang kh) {
        try {
            Connection conn = DBcontext.getConnection();

            String sql = "INSERT INTO KhachHang (Ma,HoTen,NgaySinh,GioiTinh,Sdt,DiaChi)"
                    + "VALUES(?,?,?,?,?,?)";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, kh.getMa());
            ps.setString(2, kh.getHoTen());
            ps.setDate(3, new java.sql.Date(kh.getNgaySinh().getTime()));
            ps.setString(4, kh.getGioiTinh());
            ps.setString(5, kh.getSdt());
            ps.setString(6, kh.getDiaChi());

            if (ps.executeUpdate() > 0) {
                return 1;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 1;
    }

    @Override
    public Integer update(KhachHang kh, String id) {
        try {
            Connection conn = DBcontext.getConnection();
            String sql = "Update khachhang "
                    + "Set HoTen =?,NgaySinh =?,GioiTinh = ?,Sdt = ?,DiaChi =? where Id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, kh.getHoTen());
            ps.setDate(2, new java.sql.Date(kh.getNgaySinh().getTime()));
            ps.setString(3, kh.getGioiTinh());
            ps.setString(4, kh.getSdt());
            ps.setString(5, kh.getDiaChi());
            ps.setString(6, id);

            ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 1;
    }

    @Override
    public String findMa(String ma) {
        String maSearch = null;
        try {
            Connection conn = DBcontext.getConnection();

            String sql = "Select Ma from KhachHang where Ma = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, ma);
            ps.execute();

            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                String search = rs.getString("Ma");

                maSearch = search;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return maSearch;
    }

    @Override
    public ArrayList<KhachHang> timTheoTen(String hoTen) {
        ArrayList<KhachHang> list = new ArrayList<>();

        try {
            Connection conn = DBcontext.getConnection();
            String sql = "select * from khachhang where HoTen = '" + hoTen + "'";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,hoTen);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                String id = rs.getString("Id");
                String ma = rs.getString("Ma");
                String hTen = rs.getString("HoTen");
                Date ngaySinh = rs.getDate("NgaySinh");
                String gioiTinh = rs.getString("GioiTinh");
                String sdt = rs.getString("Sdt");
                String diaChi = rs.getString("DiaChi");

                KhachHang khachHang = new KhachHang(id, ma, hTen, ngaySinh, gioiTinh, sdt, diaChi);
                list.add(khachHang);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }

}
