/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View;

import Utilities.DBcontext;
import View.Main;
import com.raven.datechooser.DateBetween;
import com.raven.datechooser.DateChooser;
import com.raven.datechooser.listener.DateChooserAction;
import com.raven.datechooser.listener.DateChooserAdapter;
import java.text.SimpleDateFormat;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author BOSS
 */
public class ThongKePanel extends javax.swing.JPanel {

    private DateChooser chDate = new DateChooser();
    private DefaultTableModel model = new DefaultTableModel();
    private DefaultTableModel model2 = new DefaultTableModel();
    private DefaultTableModel model3 = new DefaultTableModel();

    private DefaultTableModel model4 = new DefaultTableModel();
    private DefaultTableModel model5 = new DefaultTableModel();
    private DefaultTableModel model6 = new DefaultTableModel();

    private DefaultTableModel modelHoaDon = new DefaultTableModel();
    private DefaultTableModel modelloadHoaDon = new DefaultTableModel();
    private DefaultTableModel modelTongDoanhThu = new DefaultTableModel();

    private DefaultTableModel modelMax = new DefaultTableModel();
    private DefaultTableModel modelMin = new DefaultTableModel();
    private DefaultTableModel modelTatCaSanPham = new DefaultTableModel();
    private DefaultTableModel modelSPMuaItNhat = new DefaultTableModel();
    private DefaultTableModel modelSanPhamGanHet = new DefaultTableModel();

    private DefaultTableModel modelKhachHangNam = new DefaultTableModel();
    private DefaultTableModel modelKhachHangNu = new DefaultTableModel();
    private DefaultTableModel modelTatCaKhachHang = new DefaultTableModel();
    private DefaultTableModel modelKhachHangVip = new DefaultTableModel();

    private DefaultTableModel modelUser = new DefaultTableModel();
    private DefaultTableModel tatCaUser = new DefaultTableModel();
    private DefaultTableModel modelNhanVienNam = new DefaultTableModel();
    private DefaultTableModel modelNhanVienNu = new DefaultTableModel();
    private DefaultTableModel modelNhanVienBanNhieuNhat = new DefaultTableModel();
    private DefaultTableModel modelNhanVienKhongBanDuoc = new DefaultTableModel();

    public ThongKePanel() {
        initComponents();
        model = (DefaultTableModel) tblHD.getModel();
        model2 = (DefaultTableModel) tblSPCT.getModel();
        model3 = (DefaultTableModel) tblKhachHang.getModel();

        modelHoaDon = (DefaultTableModel) tblHD.getModel();
        modelloadHoaDon = (DefaultTableModel) tblHD.getModel();
        modelTongDoanhThu = (DefaultTableModel) tblHD.getModel();

        model4 = (DefaultTableModel) tblHD.getModel();
        model5 = (DefaultTableModel) tblSPCT.getModel();
        model6 = (DefaultTableModel) tblKhachHang.getModel();

        modelMax = (DefaultTableModel) tblSPCT.getModel();
        modelMin = (DefaultTableModel) tblSPCT.getModel();
        modelTatCaSanPham = (DefaultTableModel) tblSPCT.getModel();
        modelSPMuaItNhat = (DefaultTableModel) tblSPCT.getModel();
        modelSanPhamGanHet = (DefaultTableModel) tblSPCT.getModel();

        modelKhachHangNam = (DefaultTableModel) tblKhachHang.getModel();
        modelKhachHangNu = (DefaultTableModel) tblKhachHang.getModel();
        modelTatCaKhachHang = (DefaultTableModel) tblKhachHang.getModel();
        modelKhachHangVip = (DefaultTableModel) tblKhachHang.getModel();

        modelUser = (DefaultTableModel) tblUser.getModel();
        tatCaUser = (DefaultTableModel) tblUser.getModel();
        modelNhanVienNam = (DefaultTableModel) tblUser.getModel();
        modelNhanVienNu = (DefaultTableModel) tblUser.getModel();
        modelNhanVienBanNhieuNhat = (DefaultTableModel) tblUser.getModel();
        modelNhanVienKhongBanDuoc = (DefaultTableModel) tblUser.getModel();

        chDate.setTextField(txtDate);
        chDate.setDateSelectionMode(DateChooser.DateSelectionMode.BETWEEN_DATE_SELECTED);
        chDate.setLabelCurrentDayVisible(false);
        chDate.setDateFormat(new SimpleDateFormat("dd-MMMM-yyyy"));

//        chDate.addActionDateChooserListener(new DateChooserAdapter() {
//            @Override
//            public void dateBetweenChanged(DateBetween date, DateChooserAction action) {
//                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//                String dateFrom = sdf.format(date.getFromDate());
//                String toDate = sdf.format(date.getToDate());
//                loadHoaDon("select MaHD,NgayTao,NgayThanhToan,TinhTrang from HoaDon\n"
//                        + "where NgayTao between '" + dateFrom + "' and '" + toDate + "'");
//            }
//
//        });
        chDate.addActionDateChooserListener(new DateChooserAdapter() {
            @Override
            public void dateBetweenChanged(DateBetween date, DateChooserAction action) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String dateFrom = sdf.format(date.getFromDate());
                String toDate = sdf.format(date.getToDate());
                loadCTSP("select TenQuanAo,MaQuanAo,cthd.SoLuong,GiaBan from ChiTietQuanAo ctqa \n"
                        + "join ChiTietHoaDon cthd \n"
                        + "on cthd.IdChiTietQA = ctqa.Id\n"
                        + "join HoaDon hd \n"
                        + "on cthd.IdHD = hd.Id\n"
                        + "where hd.NgayTao between '" + dateFrom + "' and '" + toDate + "'");
            }

        });

        chDate.addActionDateChooserListener(new DateChooserAdapter() {
            @Override
            public void dateBetweenChanged(DateBetween date, DateChooserAction action) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String dateFrom = sdf.format(date.getFromDate());
                String toDate = sdf.format(date.getToDate());
                loadKhachHang("Select Ma,HoTen,NgaySinh,GioiTinh,Sdt,DiaChi from KhachHang kh\n"
                        + "join HoaDon hd \n"
                        + "on kh.Id = hd.IdKH\n"
                        + "where hd.NgayTao between '" + dateFrom + "' and '" + toDate + "' ");
            }

        });

        chDate.addActionDateChooserListener(new DateChooserAdapter() {
            @Override
            public void dateBetweenChanged(DateBetween date, DateChooserAction action) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String dateFrom = sdf.format(date.getFromDate());
                String toDate = sdf.format(date.getToDate());
                loadThang("select SUM(cthd.DonGia * cthd.SoLuong) as DoanhThu,hd.NgayTao as Thang from HoaDon hd \n"
                        + "join ChiTietHoaDon cthd\n"
                        + "on hd.Id = cthd.IdHD\n"
                        + "where NgayTao between '" + dateFrom + "' and '" + toDate + "' \n"
                        + "group by NgayTao");
            }

        });

        chDate.addActionDateChooserListener(new DateChooserAdapter() {
            @Override
            public void dateBetweenChanged(DateBetween date, DateChooserAction action) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String dateFrom = sdf.format(date.getFromDate());
                String toDate = sdf.format(date.getToDate());
                loadNam("select SUM(cthd.DonGia * cthd.SoLuong) as DoanhThu,hd.NgayTao as Thang from HoaDon hd \n"
                        + "join ChiTietHoaDon cthd\n"
                        + "on hd.Id = cthd.IdHD\n"
                        + "where NgayTao between '" + dateFrom + "' and '" + toDate + "' \n"
                        + "group by NgayTao");
            }

        });

        chDate.addActionDateChooserListener(new DateChooserAdapter() {
            @Override
            public void dateBetweenChanged(DateBetween date, DateChooserAction action) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String dateFrom = sdf.format(date.getFromDate());
                String toDate = sdf.format(date.getToDate());
                loadDoanhThuNgay("select SUM(cthd.DonGia * cthd.SoLuong) as DoanhThu,hd.NgayTao as Thang from HoaDon hd \n"
                        + "join ChiTietHoaDon cthd\n"
                        + "on hd.Id = cthd.IdHD\n"
                        + "where NgayTao between '" + dateFrom + "' and '" + toDate + "' \n"
                        + "group by NgayTao");
            }

        });
        chDate.addActionDateChooserListener(new DateChooserAdapter() {
            @Override
            public void dateBetweenChanged(DateBetween date, DateChooserAction action) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String dateFrom = sdf.format(date.getFromDate());
                String toDate = sdf.format(date.getToDate());
                loadTongHoaDon("select COUNT(HoaDon.MaHD) as TongHoaDon from HoaDon\n"
                        + "where NgayTao between '" + dateFrom + "' and '" + toDate + "'");
            }

        });
        chDate.addActionDateChooserListener(new DateChooserAdapter() {
            @Override
            public void dateBetweenChanged(DateBetween date, DateChooserAction action) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String dateFrom = sdf.format(date.getFromDate());
                String toDate = sdf.format(date.getToDate());
                loadTongDoanhThu("select COUNT(hd.MaHD) as TongHoaDon , SUM(cthd.SoLuong * cthd.DonGia) as TongDoanhThu,NgayTao as NT from HoaDon hd\n"
                        + "join ChiTietHoaDon cthd \n"
                        + "on hd.Id = cthd.IdHD\n"
                        + "where NgayTao between '" + dateFrom + "' and '" + toDate + "' \n"
                        + "group by NgayTao");
            }

        });

        tblkhach("Select * from KhachHang");

        tblSanPham("select TenQuanAo,MaQuanAo,cthd.SoLuong,GiaBan from ChiTietQuanAo ctqa \n"
                + "join ChiTietHoaDon cthd \n"
                + "on cthd.IdChiTietQA = ctqa.Id\n"
                + "join HoaDon hd \n"
                + "on cthd.IdHD = hd.Id");
        tblUser("select Ma,HoTen,NgaySinh,GioiTinh,Sdt,DiaChi,Email,VaiTro,MatKhau from Users");

        tblHoaDon("select COUNT(hd.MaHD) as TongHoaDon , SUM(cthd.SoLuong * cthd.DonGia) as TongDoanhThu,NgayTao as NT from HoaDon hd\n"
                + "join ChiTietHoaDon cthd \n"
                + "on hd.Id = cthd.IdHD\n"
                + "group by NgayTao");

    }

    public void loadTongDoanhThu(String sql) {
        try {
            modelTongDoanhThu.setRowCount(0);
            SimpleDateFormat df = new SimpleDateFormat("dd-MMMM-yyyy");
            DecimalFormat f = new DecimalFormat("$ #,##0.##");

            PreparedStatement ps = DBcontext.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String soHoaDon = rs.getString("TongHoaDon");
                String tongTien = f.format(rs.getInt("TongDoanhThu"));
                String ngayTao = df.format(rs.getDate("NT"));
                modelTongDoanhThu.addRow(new Object[]{tblHD.getRowCount() + 1, soHoaDon, tongTien, ngayTao});

            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void tblHoaDon(String sql) {
        try {
            modelHoaDon.setRowCount(0);
            SimpleDateFormat df = new SimpleDateFormat("dd-MMMM-yyyy");
            DecimalFormat f = new DecimalFormat("$ #,##0.##");

            PreparedStatement ps = DBcontext.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String soHoaDon = rs.getString("TongHoaDon");
                String tongTien = f.format(rs.getInt("TongDoanhThu"));
                String ngayTao = df.format(rs.getDate("NT"));
                modelHoaDon.addRow(new Object[]{tblHD.getRowCount() + 1, soHoaDon, tongTien, ngayTao});
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void tblUser(String sql) {
        try {
            modelUser.setRowCount(0);
            SimpleDateFormat df = new SimpleDateFormat("dd-MMMM-yyyy");
            DecimalFormat f = new DecimalFormat("$ #,##0.##");

            PreparedStatement ps = DBcontext.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String ma = rs.getString("Ma");
                String hoTen = rs.getString("HoTen");
                String ngaySinh = df.format(rs.getDate("NgaySinh"));
                String gioiTinh = rs.getString("GioiTinh");
                String sdt = rs.getString("Sdt");
                String diaChi = rs.getString("DiaChi");
                String email = rs.getString("Email");
                String vaiTro = rs.getString("VaiTro");
                String matKhau = rs.getString("MatKhau");
                modelUser.addRow(new Object[]{tblUser.getRowCount() + 1, ma, hoTen, ngaySinh, gioiTinh, sdt, diaChi, email, vaiTro, matKhau});
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void tblSanPham(String sql) {
        try {
            model2.setRowCount(0);
            SimpleDateFormat df = new SimpleDateFormat("dd-MMMM-yyyy");
            DecimalFormat f = new DecimalFormat(" #,##0.##");

            PreparedStatement ps = DBcontext.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String tenQA = rs.getString("TenQuanAo");
                String maQA = rs.getString("MaQuanAo");
                String soLuong = f.format(rs.getInt("SoLuong"));
                String giaBan = f.format(rs.getInt("GiaBan"));
                model2.addRow(new Object[]{tblSPCT.getRowCount() + 1, tenQA, maQA, soLuong, giaBan});

            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void tblkhach(String sql) {
        try {
            model3.setRowCount(0);
            SimpleDateFormat df = new SimpleDateFormat("dd-MMMM-yyyy");
            DecimalFormat f = new DecimalFormat("$ #,##0.##");

            PreparedStatement ps = DBcontext.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String ma = rs.getString("Ma");
                String hoTen = rs.getString("HoTen");
                String ngaySinh = df.format(rs.getDate("NgaySinh"));
                String gioiTinh = rs.getString("GioiTinh");
                String sdt = rs.getString("Sdt");
                String diaChi = rs.getString("DiaChi");
                model3.addRow(new Object[]{tblKhachHang.getRowCount() + 1, ma, hoTen, ngaySinh, gioiTinh, sdt, diaChi});
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public void loadHoaDon(String sql) {
//        try {
//            model4.setRowCount(0);
//            SimpleDateFormat df = new SimpleDateFormat("dd-MMMM-yyyy");
//            DecimalFormat f = new DecimalFormat("$ #,##0.##");
//
//            PreparedStatement ps = DBcontext.getConnection().prepareStatement(sql);
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                String ma = rs.getString("MaHD");
//                String ngayTao = df.format(rs.getDate("NgayTao"));
//                String ngayThanhToan = df.format(rs.getDate("NgayThanhToan"));
//                String tinhTrang = rs.getString("TinhTrang");
//                model4.addRow(new Object[]{tblHD.getRowCount() + 1, ma, ngayTao, ngayThanhToan, tinhTrang});
//
//            }
//            rs.close();
//            ps.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
    public void loadCTSP(String sql) {
        try {
            model5.setRowCount(0);
            SimpleDateFormat df = new SimpleDateFormat("dd-MMMM-yyyy");
            DecimalFormat f = new DecimalFormat(" #,##0.##");

            PreparedStatement ps = DBcontext.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String tenQA = rs.getString("TenQuanAo");
                String maQA = rs.getString("MaQuanAo");
                String soLuong = f.format(rs.getInt("SoLuong"));
                String giaBan = f.format(rs.getInt("GiaBan"));
                model5.addRow(new Object[]{tblSPCT.getRowCount() + 1, tenQA, maQA, soLuong, giaBan});

            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadKhachHang(String sql) {
        try {
            model6.setRowCount(0);
            SimpleDateFormat df = new SimpleDateFormat("dd-MMMM-yyyy");
            DecimalFormat f = new DecimalFormat("$ #,##0.##");

            PreparedStatement ps = DBcontext.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String ma = rs.getString("Ma");
                String hoTen = rs.getString("HoTen");
                String ngaySinh = df.format(rs.getDate("NgaySinh"));
                String gioiTinh = rs.getString("GioiTinh");
                String sdt = rs.getString("Sdt");
                String diaChi = rs.getString("DiaChi");
                model6.addRow(new Object[]{tblKhachHang.getRowCount() + 1, ma, hoTen, ngaySinh, gioiTinh, sdt, diaChi});
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadTongHoaDon(String sql) {
        try {
            model.setRowCount(0);
            SimpleDateFormat df = new SimpleDateFormat("dd-MMMM-yyyy");
            DecimalFormat f = new DecimalFormat("$ #,##0.##");

            PreparedStatement ps = DBcontext.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                String thanhtien = rs.getString("TongHoaDon");

                lblHN.setText(thanhtien + " " + "đơn hàng");

            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadDoanhThuNgay(String sql) {
        try {
            model.setRowCount(0);
            SimpleDateFormat df = new SimpleDateFormat("dd-MMMM-yyyy");
            DecimalFormat f = new DecimalFormat("$ #,##0.##");

            PreparedStatement ps = DBcontext.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                String thanhtien = rs.getString("Doanhthu");

                lblNgay.setText(thanhtien + " " + "VND");

            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadThang(String sql) {
        try {
            model.setRowCount(0);
            SimpleDateFormat df = new SimpleDateFormat("dd-MMMM-yyyy");
            DecimalFormat f = new DecimalFormat("$ #,##0.##");

            PreparedStatement ps = DBcontext.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                String thanhtien = rs.getString("Doanhthu");

                lblThang.setText(thanhtien + " " + "VND");

            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadNam(String sql) {
        try {
            model.setRowCount(0);
            SimpleDateFormat df = new SimpleDateFormat("dd-MMMM-yyyy");
            DecimalFormat f = new DecimalFormat("$ #,##0.##");

            PreparedStatement ps = DBcontext.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                String thanhtien = rs.getString("Doanhthu");

                lblNam.setText(thanhtien + " " + "VND");

            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadMax(String sql) {
        try {
            modelMax.setRowCount(0);
            SimpleDateFormat df = new SimpleDateFormat("dd-MMMM-yyyy");
            DecimalFormat f = new DecimalFormat(" #,##0.##");

            PreparedStatement ps = DBcontext.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String tenQA = rs.getString("TenQuanAo");
                String maQA = rs.getString("MaQuanAo");
                String soLuong = f.format(rs.getInt("SoLuong"));
                String giaBan = f.format(rs.getInt("DonGia"));
                modelMax.addRow(new Object[]{tblSPCT.getRowCount() + 1, tenQA, maQA, soLuong, giaBan});

            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadMin(String sql) {
        try {
            modelMin.setRowCount(0);
            SimpleDateFormat df = new SimpleDateFormat("dd-MMMM-yyyy");
            DecimalFormat f = new DecimalFormat(" #,##0.##");

            PreparedStatement ps = DBcontext.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String tenQA = rs.getString("TenQuanAo");
                String maQA = rs.getString("MaQuanAo");
                String soLuong = f.format(rs.getInt("SoLuong"));
                String giaBan = f.format(rs.getInt("DonGia"));
                modelMin.addRow(new Object[]{tblSPCT.getRowCount() + 1, tenQA, maQA, soLuong, giaBan});

            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadTatCaSanPham(String sql) {
        try {
            modelTatCaSanPham.setRowCount(0);
            SimpleDateFormat df = new SimpleDateFormat("dd-MMMM-yyyy");
            DecimalFormat f = new DecimalFormat(" #,##0.##");

            PreparedStatement ps = DBcontext.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String tenQA = rs.getString("TenQuanAo");
                String maQA = rs.getString("MaQuanAo");
                String soLuong = f.format(rs.getInt("SoLuong"));
                String giaBan = f.format(rs.getInt("DonGia"));
                modelTatCaSanPham.addRow(new Object[]{tblSPCT.getRowCount() + 1, tenQA, maQA, soLuong, giaBan});

            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadKhachHangNam(String sql) {
        try {
            modelKhachHangNam.setRowCount(0);
            SimpleDateFormat df = new SimpleDateFormat("dd-MMMM-yyyy");
            DecimalFormat f = new DecimalFormat("$ #,##0.##");

            PreparedStatement ps = DBcontext.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String ma = rs.getString("Ma");
                String hoTen = rs.getString("HoTen");
                String ngaySinh = df.format(rs.getDate("NgaySinh"));
                String gioiTinh = rs.getString("GioiTinh");
                String sdt = rs.getString("Sdt");
                String diaChi = rs.getString("DiaChi");
                modelKhachHangNam.addRow(new Object[]{tblKhachHang.getRowCount() + 1, ma, hoTen, ngaySinh, gioiTinh, sdt, diaChi});
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadKhachHangNu(String sql) {
        try {
            modelKhachHangNu.setRowCount(0);
            SimpleDateFormat df = new SimpleDateFormat("dd-MMMM-yyyy");
            DecimalFormat f = new DecimalFormat("$ #,##0.##");

            PreparedStatement ps = DBcontext.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String ma = rs.getString("Ma");
                String hoTen = rs.getString("HoTen");
                String ngaySinh = df.format(rs.getDate("NgaySinh"));
                String gioiTinh = rs.getString("GioiTinh");
                String sdt = rs.getString("Sdt");
                String diaChi = rs.getString("DiaChi");
                modelKhachHangNu.addRow(new Object[]{tblKhachHang.getRowCount() + 1, ma, hoTen, ngaySinh, gioiTinh, sdt, diaChi});
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadTatCaKhachHang(String sql) {
        try {
            modelTatCaKhachHang.setRowCount(0);
            SimpleDateFormat df = new SimpleDateFormat("dd-MMMM-yyyy");
            DecimalFormat f = new DecimalFormat("$ #,##0.##");

            PreparedStatement ps = DBcontext.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String ma = rs.getString("Ma");
                String hoTen = rs.getString("HoTen");
                String ngaySinh = df.format(rs.getDate("NgaySinh"));
                String gioiTinh = rs.getString("GioiTinh");
                String sdt = rs.getString("Sdt");
                String diaChi = rs.getString("DiaChi");
                modelTatCaKhachHang.addRow(new Object[]{tblKhachHang.getRowCount() + 1, ma, hoTen, ngaySinh, gioiTinh, sdt, diaChi});
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadKhachHangVip(String sql) {
        try {
            modelKhachHangVip.setRowCount(0);
            SimpleDateFormat df = new SimpleDateFormat("dd-MMMM-yyyy");
            DecimalFormat f = new DecimalFormat("$ #,##0.##");

            PreparedStatement ps = DBcontext.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String ma = rs.getString("Ma");
                String hoTen = rs.getString("HoTen");
                String ngaySinh = df.format(rs.getDate("NgaySinh"));
                String gioiTinh = rs.getString("GioiTinh");
                String sdt = rs.getString("Sdt");
                String diaChi = rs.getString("DiaChi");
                modelKhachHangVip.addRow(new Object[]{tblKhachHang.getRowCount() + 1, ma, hoTen, ngaySinh, gioiTinh, sdt, diaChi});
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadSanPhamMuaItNhat(String sql) {
        try {
            modelSPMuaItNhat.setRowCount(0);
            SimpleDateFormat df = new SimpleDateFormat("dd-MMMM-yyyy");
            DecimalFormat f = new DecimalFormat(" #,##0.##");

            PreparedStatement ps = DBcontext.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String tenQA = rs.getString("TenQuanAo");
                String maQA = rs.getString("MaQuanAo");
                String soLuong = f.format(rs.getInt("SoLuong"));
                String giaBan = f.format(rs.getInt("GiaBan"));
                modelSPMuaItNhat.addRow(new Object[]{tblSPCT.getRowCount() + 1, tenQA, maQA, soLuong, giaBan});

            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadTatCaUser(String sql) {
        try {
            tatCaUser.setRowCount(0);
            SimpleDateFormat df = new SimpleDateFormat("dd-MMMM-yyyy");
            DecimalFormat f = new DecimalFormat("$ #,##0.##");

            PreparedStatement ps = DBcontext.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String ma = rs.getString("Ma");
                String hoTen = rs.getString("HoTen");
                String ngaySinh = df.format(rs.getDate("NgaySinh"));
                String gioiTinh = rs.getString("GioiTinh");
                String sdt = rs.getString("Sdt");
                String diaChi = rs.getString("DiaChi");
                String email = rs.getString("Email");
                String vaiTro = rs.getString("VaiTro");
                String matKhau = rs.getString("MatKhau");
                tatCaUser.addRow(new Object[]{tblUser.getRowCount() + 1, ma, hoTen, ngaySinh, gioiTinh, sdt, diaChi, email, vaiTro, matKhau});
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadUserNam(String sql) {
        try {
            modelNhanVienNam.setRowCount(0);
            SimpleDateFormat df = new SimpleDateFormat("dd-MMMM-yyyy");
            DecimalFormat f = new DecimalFormat("$ #,##0.##");

            PreparedStatement ps = DBcontext.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String ma = rs.getString("Ma");
                String hoTen = rs.getString("HoTen");
                String ngaySinh = df.format(rs.getDate("NgaySinh"));
                String gioiTinh = rs.getString("GioiTinh");
                String sdt = rs.getString("Sdt");
                String diaChi = rs.getString("DiaChi");
                String email = rs.getString("Email");
                String vaiTro = rs.getString("VaiTro");
                String matKhau = rs.getString("MatKhau");
                modelNhanVienNam.addRow(new Object[]{tblUser.getRowCount() + 1, ma, hoTen, ngaySinh, gioiTinh, sdt, diaChi, email, vaiTro, matKhau});
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void loadUserNu(String sql) {
        try {
            modelNhanVienNu.setRowCount(0);
            SimpleDateFormat df = new SimpleDateFormat("dd-MMMM-yyyy");
            DecimalFormat f = new DecimalFormat("$ #,##0.##");

            PreparedStatement ps = DBcontext.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String ma = rs.getString("Ma");
                String hoTen = rs.getString("HoTen");
                String ngaySinh = df.format(rs.getDate("NgaySinh"));
                String gioiTinh = rs.getString("GioiTinh");
                String sdt = rs.getString("Sdt");
                String diaChi = rs.getString("DiaChi");
                String email = rs.getString("Email");
                String vaiTro = rs.getString("VaiTro");
                String matKhau = rs.getString("MatKhau");
                modelNhanVienNu.addRow(new Object[]{tblUser.getRowCount() + 1, ma, hoTen, ngaySinh, gioiTinh, sdt, diaChi, email, vaiTro, matKhau});
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadUserBanNhieuNhat(String sql) {
        try {
            modelNhanVienBanNhieuNhat.setRowCount(0);
            SimpleDateFormat df = new SimpleDateFormat("dd-MMMM-yyyy");
            DecimalFormat f = new DecimalFormat("$ #,##0.##");

            PreparedStatement ps = DBcontext.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String ma = rs.getString("Ma");
                String hoTen = rs.getString("HoTen");
                String ngaySinh = df.format(rs.getDate("NgaySinh"));
                String gioiTinh = rs.getString("GioiTinh");
                String sdt = rs.getString("Sdt");
                String diaChi = rs.getString("DiaChi");
                String email = rs.getString("Email");
                String vaiTro = rs.getString("VaiTro");
                String matKhau = rs.getString("MatKhau");
                modelNhanVienBanNhieuNhat.addRow(new Object[]{tblUser.getRowCount() + 1, ma, hoTen, ngaySinh, gioiTinh, sdt, diaChi, email, vaiTro, matKhau});
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void loadUserKhongBanDuoc(String sql) {
        try {
            modelNhanVienKhongBanDuoc.setRowCount(0);
            SimpleDateFormat df = new SimpleDateFormat("dd-MMMM-yyyy");
            DecimalFormat f = new DecimalFormat("$ #,##0.##");

            PreparedStatement ps = DBcontext.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String ma = rs.getString("Ma");
                String hoTen = rs.getString("HoTen");
                String ngaySinh = df.format(rs.getDate("NgaySinh"));
                String gioiTinh = rs.getString("GioiTinh");
                String sdt = rs.getString("Sdt");
                String diaChi = rs.getString("DiaChi");
                String email = rs.getString("Email");
                String vaiTro = rs.getString("VaiTro");
                String matKhau = rs.getString("MatKhau");
                modelNhanVienKhongBanDuoc.addRow(new Object[]{tblUser.getRowCount() + 1, ma, hoTen, ngaySinh, gioiTinh, sdt, diaChi, email, vaiTro, matKhau});
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblHN = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        lblNgay = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        lblThang = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        lblNam = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblSPCT = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblKhachHang = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblUser = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHD = new javax.swing.JTable();
        jLabel12 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        cb_SanPham = new javax.swing.JComboBox<>();
        cb_nhanVien = new javax.swing.JComboBox<>();
        cb_khachHang = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        txtDate = new javax.swing.JTextField();

        jPanel1.setBackground(new java.awt.Color(204, 255, 204));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel1.setText("Tổng hóa đơn ");

        lblHN.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        lblHN.setText("-");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(lblHN, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(lblHN, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(50, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(153, 255, 255));

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel3.setText("Tổng doanh thu ngày");

        lblNgay.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        lblNgay.setText("-");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lblNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(lblNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(56, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 204, 204));

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel4.setText("Tổng doanh thu tháng");

        lblThang.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        lblThang.setText("-");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel4)
                .addContainerGap(24, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblThang, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(lblThang, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(51, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(204, 204, 0));

        jLabel5.setBackground(new java.awt.Color(204, 204, 0));
        jLabel5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel5.setText("Tổng doanh thu năm");

        lblNam.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        lblNam.setText("-");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNam, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(lblNam, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(57, Short.MAX_VALUE))
        );

        tblSPCT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Tên quần áo", "Mã quần áo", "Số lượng ", "Đơn giá"
            }
        ));
        jScrollPane2.setViewportView(tblSPCT);

        jLabel11.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel11.setText("CHI TIẾT SẢN PHẨM COOLMATE SHOP");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(266, 266, 266)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(31, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 766, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Sản phẩm", jPanel6);

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setText("THÔNG TIN KHÁCH HÀNG");

        tblKhachHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã", "Họ Tên", "Ngày sinh", "Giới tính", "Sdt", "Địa chỉ"
            }
        ));
        jScrollPane3.setViewportView(tblKhachHang);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(282, 282, 282)
                        .addComponent(jLabel2))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 685, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(83, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Khách hàng", jPanel8);

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel6.setText("NHÂN VIÊN COOLMATE SHOP");

        tblUser.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã", "Họ tên", "Ngày sinh", "Giới tính", "Sdt", "Địa chỉ", "Email", "Vai trò", "Mật khẩu"
            }
        ));
        jScrollPane4.setViewportView(tblUser);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 663, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(236, 236, 236)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(120, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("User", jPanel7);

        tblHD.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        tblHD.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Tổng hóa đơn", "Tổng tiền ", "Ngày tạo "
            }
        ));
        jScrollPane1.setViewportView(tblHD);

        jLabel12.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel12.setText("THỐNG KÊ DOANH THU CỬA HÀNG COOLMATE SHOP");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 680, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(204, 204, 204)
                        .addComponent(jLabel12)))
                .addContainerGap(76, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel12)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(91, 91, 91))
        );

        jTabbedPane1.addTab("Doanh Thu", jPanel5);

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel9.setText("Search");

        jRadioButton1.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jRadioButton1.setForeground(new java.awt.Color(0, 51, 51));
        jRadioButton1.setText("Khách hàng");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        jRadioButton2.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jRadioButton2.setForeground(new java.awt.Color(0, 51, 51));
        jRadioButton2.setText("Nhân viên");
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });

        jRadioButton3.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jRadioButton3.setForeground(new java.awt.Color(0, 51, 51));
        jRadioButton3.setText("Sản phẩm ");
        jRadioButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton3ActionPerformed(evt);
            }
        });

        cb_SanPham.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Sản phẩm mua nhiều nhất", "Sản phẩm mua ít nhất ", "Sản phẩm gần hết" }));
        cb_SanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_SanPhamActionPerformed(evt);
            }
        });

        cb_nhanVien.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Nam ", "Nữ", "Nhân viên bán nhiều nhất", "Nhân viên không bán được" }));
        cb_nhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_nhanVienActionPerformed(evt);
            }
        });

        cb_khachHang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Nam", "Nữ ", "Khách hàng VIP", " " }));
        cb_khachHang.setToolTipText("");
        cb_khachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_khachHangActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        jLabel7.setText("BẢNG THỐNG KÊ CHI TIẾT CỬA HÀNG COOLMATE SHOP THEO NGÀY ");

        jPanel9.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel9.setForeground(new java.awt.Color(51, 51, 0));

        jLabel15.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 51, 51));
        jLabel15.setText("Thống kê theo Thời gian");

        txtDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel15)
                .addGap(18, 18, 18)
                .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(65, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 829, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(2, 2, 2)
                                            .addComponent(jRadioButton2)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(cb_nhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                            .addComponent(jRadioButton3)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(cb_SanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                            .addComponent(jRadioButton1)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(cb_khachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(36, 36, 36)
                                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addGap(140, 140, 140)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 534, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jRadioButton1, jRadioButton2, jRadioButton3});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cb_SanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jRadioButton3))))
                        .addGap(45, 45, 45)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jRadioButton1)
                            .addComponent(cb_khachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jRadioButton2)
                            .addComponent(cb_nhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jRadioButton1, jRadioButton2, jRadioButton3});

    }// </editor-fold>//GEN-END:initComponents

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void jRadioButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton3ActionPerformed

    private void cb_SanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_SanPhamActionPerformed
        // TODO add your handling code here:
        if (cb_SanPham.getSelectedIndex() == 0) {
            loadTatCaSanPham("Select ctqa.TenQuanAo,ctqa.MaQuanAo,cthd.SoLuong,cthd.DonGia from ChiTietQuanAo ctqa \n"
                    + "join ChiTietHoaDon cthd \n"
                    + "on ctqa.Id = cthd.IdChiTietQA\n"
                    + "join HoaDon hd \n"
                    + "on hd.Id = cthd.IdHD");
        }
        if (cb_SanPham.getSelectedIndex() == 1) {
            loadMax("Select ctqa.TenQuanAo,ctqa.MaQuanAo,cthd.SoLuong,cthd.DonGia from ChiTietQuanAo ctqa \n"
                    + "join ChiTietHoaDon cthd \n"
                    + "on ctqa.Id = cthd.IdChiTietQA\n"
                    + "join HoaDon hd \n"
                    + "on hd.Id = cthd.IdHD\n"
                    + "where cthd.SoLuong in (select MAX(SoLuong) from ChiTietHoaDon)");
        }
        if (cb_SanPham.getSelectedIndex() == 2) {
            loadMin("Select ctqa.TenQuanAo,ctqa.MaQuanAo,cthd.SoLuong,cthd.DonGia from ChiTietQuanAo ctqa \n"
                    + "join ChiTietHoaDon cthd \n"
                    + "on ctqa.Id = cthd.IdChiTietQA\n"
                    + "join HoaDon hd \n"
                    + "on hd.Id = cthd.IdHD\n"
                    + "where cthd.SoLuong in (select MIn(SoLuong) from ChiTietHoaDon) ");
        }

        if (cb_SanPham.getSelectedIndex() == 3) {
            loadSanPhamMuaItNhat("select TenQuanAo,MaQuanAo,ctqa.SoLuong,ctqa.GiaBan from ChiTietQuanAo ctqa \n"
                    + "where ctqa.SoLuong in (select min(SoLuong) from ChiTietQuanAo)");
        }

    }//GEN-LAST:event_cb_SanPhamActionPerformed

    private void cb_khachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_khachHangActionPerformed
        // TODO add your handling code here:
        if (cb_khachHang.getSelectedIndex() == 0) {
            loadTatCaKhachHang("select Ma,HoTen,NgaySinh,GioiTinh,Sdt,DiaChi from KhachHang");
        }
        if (cb_khachHang.getSelectedIndex() == 1) {
            loadKhachHangNam("select Ma,HoTen,NgaySinh,GioiTinh,Sdt,DiaChi from KhachHang where GioiTinh = 'Nam'");
        }
        if (cb_khachHang.getSelectedIndex() == 2) {
            loadKhachHangNu("select Ma,HoTen,NgaySinh,GioiTinh,Sdt,DiaChi from KhachHang where GioiTinh = 'Nữ'");
        }
        if (cb_khachHang.getSelectedIndex() == 3) {
            loadKhachHangVip("select kh.Ma,kh.HoTen,kh.NgaySinh,kh.GioiTinh,kh.Sdt,kh.DiaChi from KhachHang kh \n"
                    + "join HoaDon hd \n"
                    + "on kh.Id = hd.IdKH\n"
                    + "join ChiTietHoaDon cthd\n"
                    + "on hd.id = cthd.IdHD\n"
                    + "where cthd.SoLuong > 10");
        }
    }//GEN-LAST:event_cb_khachHangActionPerformed

    private void txtDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDateActionPerformed

    private void cb_nhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_nhanVienActionPerformed
        // TODO add your handling code here:
        if (cb_nhanVien.getSelectedIndex() == 0) {
            loadTatCaUser("select Ma,HoTen,NgaySinh,GioiTinh,Sdt,DiaChi,Email,VaiTro,MatKhau from Users us ");
        }
        if (cb_nhanVien.getSelectedIndex() == 1) {
            loadUserNam("select Ma,HoTen,NgaySinh,GioiTinh,Sdt,DiaChi,Email,VaiTro,MatKhau from Users us \n"
                    + "where us.GioiTinh = 'Nam'");
        }
        if (cb_nhanVien.getSelectedIndex() == 2) {
            loadUserNu("select Ma,HoTen,NgaySinh,GioiTinh,Sdt,DiaChi,Email,VaiTro,MatKhau from Users us \n"
                    + "where us.GioiTinh = 'Nữ'");
        }
        if (cb_nhanVien.getSelectedIndex() == 3) {
            loadUserBanNhieuNhat("select top 1 us.Ma,us.HoTen,us.NgaySinh,us.GioiTinh,us.Sdt,us.DiaChi,us.Email,us.VaiTro,us.MatKhau from Users us\n"
                    + "join HoaDon hd \n"
                    + "on us.Id = hd.IdUser\n"
                    + "where hd.IdUser in (select max(IdUser) from HoaDon)");
        }
        if (cb_nhanVien.getSelectedIndex() == 4) {
            loadUserKhongBanDuoc("select us.Ma,us.HoTen,us.NgaySinh,us.GioiTinh,us.Sdt,us.DiaChi,us.Email,us.VaiTro,us.MatKhau from Users us\n"
                    + "where us.id not in (select IdUser from HoaDon)");
        }
    }//GEN-LAST:event_cb_nhanVienActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cb_SanPham;
    private javax.swing.JComboBox<String> cb_khachHang;
    private javax.swing.JComboBox<String> cb_nhanVien;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblHN;
    private javax.swing.JLabel lblNam;
    private javax.swing.JLabel lblNgay;
    private javax.swing.JLabel lblThang;
    private javax.swing.JTable tblHD;
    private javax.swing.JTable tblKhachHang;
    private javax.swing.JTable tblSPCT;
    private javax.swing.JTable tblUser;
    private javax.swing.JTextField txtDate;
    // End of variables declaration//GEN-END:variables

}
