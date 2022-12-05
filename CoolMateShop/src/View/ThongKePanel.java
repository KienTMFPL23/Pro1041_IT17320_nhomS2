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

    private DefaultTableModel modelMax = new DefaultTableModel();
    private DefaultTableModel modelMin = new DefaultTableModel();
    private DefaultTableModel modelTatCaSanPham = new DefaultTableModel();

    private DefaultTableModel modelKhachHangNam = new DefaultTableModel();
    private DefaultTableModel modelKhachHangNu = new DefaultTableModel();
    private DefaultTableModel modelTatCaKhachHang = new DefaultTableModel();

    public ThongKePanel() {
        initComponents();
        model = (DefaultTableModel) tblBang.getModel();
        model2 = (DefaultTableModel) tblSPCT.getModel();
        model3 = (DefaultTableModel) tblKhachHang.getModel();

        model4 = (DefaultTableModel) tblBang.getModel();
        model5 = (DefaultTableModel) tblSPCT.getModel();
        model6 = (DefaultTableModel) tblKhachHang.getModel();

        modelMax = (DefaultTableModel) tblSPCT.getModel();
        modelMin = (DefaultTableModel) tblSPCT.getModel();
        modelTatCaSanPham = (DefaultTableModel) tblSPCT.getModel();
        modelKhachHangNam = (DefaultTableModel) tblKhachHang.getModel();
        modelKhachHangNu = (DefaultTableModel) tblKhachHang.getModel();
        modelTatCaKhachHang = (DefaultTableModel) tblKhachHang.getModel();

        chDate.setTextField(txtDate);
        chDate.setDateSelectionMode(DateChooser.DateSelectionMode.BETWEEN_DATE_SELECTED);
        chDate.setLabelCurrentDayVisible(false);
        chDate.setDateFormat(new SimpleDateFormat("dd-MMMM-yyyy"));

        chDate.addActionDateChooserListener(new DateChooserAdapter() {
            @Override
            public void dateBetweenChanged(DateBetween date, DateChooserAction action) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String dateFrom = sdf.format(date.getFromDate());
                String toDate = sdf.format(date.getToDate());
                loadDoanhThu("select COUNT(hd.Id) as TSHD , SUM(cthd.DonGia) as TT,hd.NgayTao as NT from HoaDon hd \n"
                        + "join ChiTietHoaDon cthd \n"
                        + "on hd.Id = cthd.IdHD\n"
                        + "where hd.NgayTao between '" + dateFrom + "' and '" + toDate + "'\n"
                        + "group by hd.NgayTao");
            }

        });

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

        loadDoanhThuNgay("Select SUM(cthd.DonGia) as DoanhThu,hd.NgayTao from ChiTietHoaDon cthd \n"
                + "join HoaDon hd \n"
                + "on hd.Id = cthd.IdHD\n"
                + "where hd.NgayTao = '2022-11-25'\n"
                + "group by hd.NgayTao");
        loadThang("Select SUM(cthd.DonGia) as DoanhThu,MONTH(hd.NgayTao) as Thang from ChiTietHoaDon cthd \n"
                + "join HoaDon hd \n"
                + "on hd.Id = cthd.IdHD\n"
                + "where MONTH(hd.NgayTao) = 11\n"
                + "group by MONTH(hd.NgayTao) ");
        loadNam("Select SUM(cthd.DonGia) as DoanhThu,YEAR(hd.NgayTao) as Nam from ChiTietHoaDon cthd \n"
                + "join HoaDon hd \n"
                + "on hd.Id = cthd.IdHD\n"
                + "where YEAR(hd.NgayTao) = 2022\n"
                + "group by YEAR(hd.NgayTao) ");
        loadTongHoaDon("select COUNT(HoaDon.MaHD) as TongHoaDon from HoaDon\n"
                + "where NgayTao = '2022-11-25'");

        tblkhach("Select * from KhachHang");
        tbldoanhThu("select COUNT(hd.Id) as TSHD , SUM(cthd.DonGia) as TT,hd.NgayTao as NT from HoaDon hd \n"
                + "join ChiTietHoaDon cthd \n"
                + "on hd.Id = cthd.IdHD\n"
                + "group by hd.NgayTao");
        tblSanPham("select TenQuanAo,MaQuanAo,cthd.SoLuong,GiaBan from ChiTietQuanAo ctqa \n"
                + "join ChiTietHoaDon cthd \n"
                + "on cthd.IdChiTietQA = ctqa.Id\n"
                + "join HoaDon hd \n"
                + "on cthd.IdHD = hd.Id");
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

    public void tbldoanhThu(String sql) {
        try {
            model.setRowCount(0);
            SimpleDateFormat df = new SimpleDateFormat("dd-MMMM-yyyy");
            DecimalFormat f = new DecimalFormat("$ #,##0.##");

            PreparedStatement ps = DBcontext.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String tongHD = rs.getString("TSHD");
                String thanhtien = rs.getString("TT");
                String date = df.format(rs.getDate("NT"));
                model.addRow(new Object[]{tblBang.getRowCount() + 1, tongHD, thanhtien, date});

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

    public void loadDoanhThu(String sql) {
        try {
            model4.setRowCount(0);
            SimpleDateFormat df = new SimpleDateFormat("dd-MMMM-yyyy");
            DecimalFormat f = new DecimalFormat("$ #,##0.##");

            PreparedStatement ps = DBcontext.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String tongHD = rs.getString("TSHD");
                String thanhtien = rs.getString("TT");
                String date = df.format(rs.getDate("NT"));
                model4.addRow(new Object[]{tblBang.getRowCount() + 1, tongHD, thanhtien, date});

            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblBang = new javax.swing.JTable();
        jLabel12 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblSPCT = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblKhachHang = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtDate = new javax.swing.JTextField();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        cb_SanPham = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        cb_khachHang = new javax.swing.JComboBox<>();

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

        tblBang.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        tblBang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Tổng hóa đơn", "Tổng tiền ", "Ngày tạo "
            }
        ));
        jScrollPane1.setViewportView(tblBang);

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
                .addContainerGap(140, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel12)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );

        jTabbedPane1.addTab("Doanh Thu", jPanel5);

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
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 766, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(266, 266, 266)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(56, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(79, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Sản phẩm", jPanel6);

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
                        .addGap(103, 103, 103)
                        .addComponent(jLabel2))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 685, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(177, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Khách hàng", jPanel8);

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel9.setText("Search");

        jLabel15.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 51, 51));
        jLabel15.setText("Theo thời gian ");

        txtDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDateActionPerformed(evt);
            }
        });

        jRadioButton1.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jRadioButton1.setForeground(new java.awt.Color(0, 51, 51));
        jRadioButton1.setText("Khách hàng");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        jRadioButton2.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N
        jRadioButton2.setForeground(new java.awt.Color(0, 51, 51));
        jRadioButton2.setText("Nhân viên");
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });

        jRadioButton3.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N
        jRadioButton3.setForeground(new java.awt.Color(0, 51, 51));
        jRadioButton3.setText("Sản phẩm ");
        jRadioButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton3ActionPerformed(evt);
            }
        });

        cb_SanPham.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Sản phẩm mua nhiều nhất", "Sản phẩm mua ít nhất ", " " }));
        cb_SanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_SanPhamActionPerformed(evt);
            }
        });

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả" }));

        cb_khachHang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Nam", "Nữ ", " " }));
        cb_khachHang.setToolTipText("");
        cb_khachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_khachHangActionPerformed(evt);
            }
        });

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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jRadioButton3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cb_SanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(36, 36, 36)
                                .addComponent(jRadioButton2))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(37, 37, 37)
                                        .addComponent(jRadioButton1)
                                        .addGap(27, 27, 27)
                                        .addComponent(cb_khachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jTabbedPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jRadioButton1, jRadioButton2, jRadioButton3});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton3)
                    .addComponent(jRadioButton2)
                    .addComponent(cb_SanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButton1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cb_khachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(68, Short.MAX_VALUE))
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
    }//GEN-LAST:event_cb_khachHangActionPerformed

    private void txtDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDateActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cb_SanPham;
    private javax.swing.JComboBox<String> cb_khachHang;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblHN;
    private javax.swing.JLabel lblNam;
    private javax.swing.JLabel lblNgay;
    private javax.swing.JLabel lblThang;
    private javax.swing.JTable tblBang;
    private javax.swing.JTable tblKhachHang;
    private javax.swing.JTable tblSPCT;
    private javax.swing.JTextField txtDate;
    // End of variables declaration//GEN-END:variables

}
