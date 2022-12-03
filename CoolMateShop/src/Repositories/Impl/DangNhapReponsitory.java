/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositories.Impl;

import DomainModel.users;
import Repositories.IDangNhapReponsitory;
import Utilities.DBcontext;
import ViewModel.UsersViewModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author BOSS
 */
public class DangNhapReponsitory implements IDangNhapReponsitory {

    @Override
    public users login(String ma, String matkhau) {
       try {
            Connection conn = DBcontext.getConnection();
            String sql = "Select ma, matkhau, Vaitro from users where ma = ? and matkhau = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, ma);
            ps.setString(2, matkhau);
            ps.execute();

            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                String user = rs.getString("ma");
                String pass = rs.getString("matkhau");
                int roles = rs.getInt("vaitro");

                users u = new users();
                u.setMa(user);
                u.setMatKhau(pass);
                u.setVaiTro(roles);
                
                return u;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;

}
}
