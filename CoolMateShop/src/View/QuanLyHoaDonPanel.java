/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View;

import DomainModel.KhachHang;
import DomainModel.users;
import Service.IChiTietHoaDonService;
import Service.IHoaDonService;
import Service.IKhachHangService;
import Service.IUsersSevice;
import Service.Impl.ChiTietHoaDonService;
import Service.Impl.HoaDonService;
import Service.Impl.KhachHangService;
import Service.Impl.UsersSerice;
import ViewModel.ChiTietHoaDonRespone;
import ViewModel.HoaDonViewModel;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author KIEN TRAN
 */
public class QuanLyHoaDonPanel extends javax.swing.JPanel {

    private IChiTietHoaDonService chiTietHoaDonService;
    private IHoaDonService hoaDonService;
    private IUsersSevice usersSevice;
    private IKhachHangService khachHangService;
    private List<HoaDonViewModel> listHD;
    private List<ChiTietHoaDonRespone> listCTHD;
    private DefaultTableModel dtmhd;
    private DefaultTableModel dtmcthd;
    private DefaultComboBoxModel dcbmnv;
    private DefaultComboBoxModel dcbmKH;
    private String idHD = "";

    public QuanLyHoaDonPanel() {
        initComponents();

        chiTietHoaDonService = new ChiTietHoaDonService();
        hoaDonService = new HoaDonService();
        usersSevice = new UsersSerice();
        khachHangService = new KhachHangService();
        listHD = hoaDonService.dsHoaDon();
        loadTableHD(listHD);

        addComboKH();
        addComboNV();
    }

    public void addComboNV() {
        dcbmnv = (DefaultComboBoxModel) cbNV.getModel();
        dcbmnv.removeAllElements();
        dcbmnv.addElement("");
        for (users us : usersSevice.getlist()) {
            dcbmnv.addElement(us.getHoten());
        }
    }

    public void addComboKH() {
        dcbmKH = (DefaultComboBoxModel) cbKH.getModel();
        dcbmKH.removeAllElements();
        dcbmKH.addElement("");
        for (KhachHang KH : khachHangService.getList()) {
            dcbmKH.addElement(KH.getHoTen());
        }
    }

    public void loadTableHD(List<HoaDonViewModel> lst) {
        dtmhd = (DefaultTableModel) tbHD.getModel();
        dtmhd.setRowCount(0);
        for (HoaDonViewModel hd : lst) {
            Object[] row = {
                hd.getMaHD(), hd.getUsers(), hd.getKhachHang(), hd.getNgayTao(),
                hd.getNgayThanhToan(), hd.getTinhTrang() == 1 ? "Đã thanh toán" : "Chờ thanh toán", hd.getTongtien()
            };
            dtmhd.addRow(row);
        }
    }

    public void showHDCT(List<ChiTietHoaDonRespone> list) {
        int row = tbHD.getSelectedRow();
        if (row == -1) {
            return;
        }
        int i = 1;
        list = chiTietHoaDonService.getListHD(this.idHD);
        if (list != null) {
            dtmcthd = (DefaultTableModel) tbHDCT.getModel();
            dtmcthd.setRowCount(0);
            for (ChiTietHoaDonRespone cthd : list) {
                Object[] rowData = {
                    i++, cthd.getMaQuanAo(), cthd.getTenQuanAO(), cthd.getSoLuong(), cthd.getDonGia(),
                    cthd.thanhTien(cthd.getSoLuong(), cthd.getDonGia())
                };
                dtmcthd.addRow(rowData);
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbHD = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cbNV = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        cbKH = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jdTao = new com.toedter.calendar.JDateChooser();
        jButton1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbHDCT = new javax.swing.JTable();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hóa đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18), new java.awt.Color(255, 0, 51))); // NOI18N

        tbHD.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã hóa đơn", "Nhân viên", "Khách hàng", "Ngày tạo", "Ngày thanh toán", "Tình trạng", "Tổng tiền"
            }
        ));
        tbHD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbHDMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbHD);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Nhân viên");

        cbNV.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbNVActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Khách hàng");

        cbKH.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbKHActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Ngày :");

        jButton1.setText("Tìm kiếm");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbNV, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbKH, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jdTao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(86, 86, 86)
                .addComponent(jButton1)
                .addContainerGap(101, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jdTao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hóa đơn chi tiết", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18), new java.awt.Color(255, 51, 51))); // NOI18N

        tbHDCT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã SP", "Tên sản phẩm", "Số lượng", "Đơn giá", "Tổng tiền"
            }
        ));
        jScrollPane2.setViewportView(tbHDCT);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 645, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tbHDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbHDMouseClicked
        int row = tbHD.getSelectedRow();
        idHD = listHD.get(row).getId();
        listCTHD = chiTietHoaDonService.getListHD(idHD);
        showHDCT(listCTHD);
    }//GEN-LAST:event_tbHDMouseClicked

    private void cbNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbNVActionPerformed
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(((DefaultTableModel) tbHD.getModel()));
        sorter.setRowFilter(RowFilter.regexFilter(String.valueOf(cbNV.getSelectedItem())));

        tbHD.setRowSorter(sorter);
    }//GEN-LAST:event_cbNVActionPerformed

    private void cbKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbKHActionPerformed
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(((DefaultTableModel) tbHD.getModel()));
        sorter.setRowFilter(RowFilter.regexFilter(String.valueOf(cbKH.getSelectedItem())));

        tbHD.setRowSorter(sorter);
    }//GEN-LAST:event_cbKHActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(((DefaultTableModel) tbHD.getModel()));
        sorter.setRowFilter(RowFilter.regexFilter(sdf.format(jdTao.getDate())));

        tbHD.setRowSorter(sorter);
    }//GEN-LAST:event_jButton1ActionPerformed
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbKH;
    private javax.swing.JComboBox<String> cbNV;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private com.toedter.calendar.JDateChooser jdTao;
    private javax.swing.JTable tbHD;
    private javax.swing.JTable tbHDCT;
    // End of variables declaration//GEN-END:variables
}
