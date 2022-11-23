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
            String sql = "select hd.MaHD,ctqa.TenQuanAo,cthd.SoLuong,cthd.DonGia from ChiTietHoaDon cthd  join HoaDon hd on cthd.IdHD = hd.Id\n"
                    + " join ChiTietQuanAo ctqa on cthd.IdChiTietQA = ctqa.Id ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.execute();

            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                String ma = rs.getString("MaHD");
                String ten = rs.getString("TenQuanAo");
                Integer soLuong = rs.getInt("SoLuong");
                Float donGia = rs.getFloat("DonGia");

                ChiTietHoaDonRespone cthd = new ChiTietHoaDonRespone(ma, ten, soLuong, donGia);
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

}
