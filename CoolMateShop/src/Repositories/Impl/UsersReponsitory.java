/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositories.Impl;

import DomainModel.users;
import Repositories.IUsersReponsitory;
import Utilities.DBcontext;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author BOSS
 */
public class UsersReponsitory implements IUsersReponsitory {

    @Override
    public ArrayList<users> selectAll() {
        ArrayList<users> list = new ArrayList<>();
        try {
            Connection conn = DBcontext.getConnection();
            String sql = "select * from Users ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                String idStr = rs.getString("id");
                String maStr = rs.getString("ma");
                String hoTenStr = rs.getString("hoten");
                java.sql.Date ngaysinh = rs.getDate("NgaySinh");
                String gioiTinh = rs.getString("gioiTinh");
                String sdt = rs.getString("sdt");
                String diachi = rs.getString("diaChi");
                String email = rs.getString("email");
                int vaitro = rs.getInt("VaiTro");
                String matKhau = rs.getString("matkhau");
                users use = new users(idStr, maStr, hoTenStr, ngaysinh, gioiTinh, sdt, diachi, email, vaitro, matKhau);
                list.add(use);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Integer them(users Users) {
        try {
            Connection conn = DBcontext.getConnection();
            String sql = "insert into users(ma,hoten,ngaysinh,gioitinh,sdt,diachi,email,vaitro,matkhau) values(?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, Users.getMa());
            ps.setString(2, Users.getHoten());
            ps.setDate(3, new java.sql.Date(Users.getNgaysinh().getTime()));
            ps.setString(4, Users.getGioiTinh());
            ps.setString(5, Users.getSdt());
            ps.setString(6, Users.getDiaChi());
            ps.setString(7, Users.getEmail());
            ps.setInt(8, Users.getVaiTro());
            ps.setString(9, Users.getMatKhau());
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public Integer xoa(String id) {
        try {
            Connection conn = DBcontext.getConnection();
            String sql = "delete from users where id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public Integer sua(String id, users Users) {
        try {
            Connection conn = DBcontext.getConnection();
            String sql = "update users set ma = ? , hoten = ? , ngaysinh = ? , gioitinh = ? , sdt = ?, diachi = ?, email=  ? , vaitro = ? where id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, Users.getMa());
            ps.setString(2, Users.getHoten());
            ps.setDate(3, new java.sql.Date(Users.getNgaysinh().getTime()));
            ps.setString(4, Users.getGioiTinh());
            ps.setString(5, Users.getSdt());
            ps.setString(6, Users.getDiaChi());
            ps.setString(7, Users.getEmail());
            ps.setInt(8, Users.getVaiTro());
            ps.setString(9, id);
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
            String sql = "select ma from users where ma ='" + ma + "'";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.execute();
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {

                String maStr = rs.getString("ma");
                checkMa = ma;

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return checkMa;
    }
}
