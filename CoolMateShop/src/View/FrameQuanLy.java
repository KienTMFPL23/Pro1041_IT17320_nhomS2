/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import DomainModel.ChatLieu;
import DomainModel.KichCo;
import DomainModel.MauSac;
import DomainModel.TheLoai;
import Service.IKichCoService;
import Service.IMauSacService;
import Service.ITheLoaiService;
import Service.Impl.ChatLieuService;
import Service.Impl.KichCoService;
import Service.Impl.MauSacService;
import Service.Impl.TheLoaiService;
import ViewModel.ChatLieuViewModel;
import ViewModel.KichCoRepon;

import ViewModel.MauSacRespone;
import ViewModel.TheLoaiViewModel;
import java.awt.Button;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author KIEN TRAN
 */
public class FrameQuanLy extends javax.swing.JFrame {

    private DefaultTableModel dtmKichCo;
    private ButtonGroup btg;
    private ChatLieuService cls;
    private DefaultTableModel dtmChatLieu;
    private IMauSacService mauSacService;
    private DefaultTableModel dtm;
    private DefaultTableModel dtmTheloai;
    private ITheLoaiService tls;
    private IKichCoService kcs;

    public FrameQuanLy() {
        initComponents();
        tls = new TheLoaiService();
        kcs = new KichCoService();
        mauSacService = new MauSacService();
        cls = new ChatLieuService();
        setLocationRelativeTo(null);
        this.btg = new ButtonGroup();
        radidobutton();
    }

    public void radidobutton() {
        btg.add(rdChatLieu);
        btg.add(rdKichCo);
        btg.add(rdMauSac);
        btg.add(rdTheLoai);
    }

    public void clearForm() {
        txtMa.setText("");
        txtTen.setText("");
    }

    public MauSac getFormMau() {
        String ma = txtMa.getText().trim();
        String ten = txtTen.getText().trim();

        if (ma.length() == 0 || ten.length() == 0) {
            JOptionPane.showMessageDialog(this, "Không được để trống");
            return null;
        }

        MauSac ms = new MauSac();
        ms.setMa(ma);
        ms.setTen(ten);
        return ms;
    }

    public KichCo getFormKichco() {
        String ma = txtMa.getText().trim();
        String ten = txtTen.getText().trim();
        String id = "";
        if (ma.length() == 0 || ten.length() == 0) {
            JOptionPane.showMessageDialog(this, "Không được để trống");
            return null;
        }

        KichCo kc = new KichCo();
        kc.setMa(ma);
        kc.setSize(ten);
        kc.setId(id);
        return kc;
    }

    public ChatLieu getFormChatLieu() {
        String ma = txtMa.getText().trim();
        String ten = txtTen.getText().trim();
        String id = "";
        if (ma.length() == 0 || ten.length() == 0) {
            JOptionPane.showMessageDialog(this, "Không được để trống");
            return null;
        }

        ChatLieu cl = new ChatLieu();
        cl.setMa(ma);
        cl.setTen(ten);
        cl.setId(id);
        return cl;
    }

    public void loadTableChatLieu() {
        int i = 1;
        dtmChatLieu = (DefaultTableModel) tbThuocTinh.getModel();
        dtmChatLieu.setRowCount(0);
        for (ChatLieuViewModel cl : this.cls.getList()) {
            dtmChatLieu.addRow(new Object[]{i++, cl.getMa(), "Chất liệu", cl.getTen()});
        }
    }

    public void themchatlieu() {
        String ma = txtMa.getText().trim();
        if (cls.checkMa(ma) != null) {
            JOptionPane.showMessageDialog(this, "Trung mã");
            return;
        } else {
            ChatLieu cl = this.getFormChatLieu();
            if (cl == null) {
                return;
            }
            this.cls.them(cl);
            loadTableChatLieu();
            clearForm();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jTextField4 = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtTen = new javax.swing.JTextField();
        rdMauSac = new javax.swing.JRadioButton();
        rdKichCo = new javax.swing.JRadioButton();
        rdTheLoai = new javax.swing.JRadioButton();
        rdChatLieu = new javax.swing.JRadioButton();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txtMa = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbThuocTinh = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTabbedPane1.setTabPlacement(javax.swing.JTabbedPane.LEFT);

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("Quản lý quần áo"));

        jLabel2.setText("Mã sản phẩm");

        jTextField2.setText("jTextField2");

        jLabel3.setText("Màu sắc");

        jLabel4.setText("Tên sản phẩm");

        jLabel5.setText("Loại sản phẩm");

        jLabel6.setText("Giá bán");

        jTextField3.setText("jTextField3");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jTextField4.setText("jTextField4");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField3))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(43, 43, 43)))
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField4))))
                .addGap(36, 36, 36)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(379, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(27, 27, 27)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(60, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(244, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Thông tin chi tiết", jPanel4);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Thuộc tính sản phẩm"));

        jLabel1.setText("Tên thuộc tính");

        rdMauSac.setText("Màu sắc");
        rdMauSac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdMauSacActionPerformed(evt);
            }
        });

        rdKichCo.setText("Kích cỡ");
        rdKichCo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdKichCoActionPerformed(evt);
            }
        });

        rdTheLoai.setText("Thể loại");
        rdTheLoai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdTheLoaiActionPerformed(evt);
            }
        });

        rdChatLieu.setText("Chất liệu");
        rdChatLieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdChatLieuActionPerformed(evt);
            }
        });

        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        jLabel7.setText("Mã thuộc tính");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnThem)
                        .addGap(34, 34, 34)
                        .addComponent(btnSua)
                        .addGap(44, 44, 44)
                        .addComponent(btnXoa)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtTen)
                            .addComponent(txtMa, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE))
                        .addGap(32, 32, 32)
                        .addComponent(rdMauSac)
                        .addGap(40, 40, 40)
                        .addComponent(rdKichCo)
                        .addGap(31, 31, 31)
                        .addComponent(rdTheLoai)
                        .addGap(39, 39, 39)
                        .addComponent(rdChatLieu)
                        .addGap(0, 118, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(rdMauSac)
                        .addComponent(rdKichCo)
                        .addComponent(rdTheLoai)
                        .addComponent(rdChatLieu)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem)
                    .addComponent(btnSua)
                    .addComponent(btnXoa))
                .addContainerGap())
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin thuộc tính"));

        tbThuocTinh.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã thuộc tính", "Loại thuộc tính", "Tên thuộc tính"
            }
        ));
        tbThuocTinh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbThuocTinhMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbThuocTinh);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Thuộc tính", jPanel5);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Sản phẩm", jPanel2);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 948, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 554, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Khách hàng", jPanel6);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void rdChatLieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdChatLieuActionPerformed
        // TODO add your handling code here:
        loadTableChatLieu();
    }//GEN-LAST:event_rdChatLieuActionPerformed

    public void loadTableMau() {
        int i = 1;
        dtm = (DefaultTableModel) tbThuocTinh.getModel();
        dtm.setRowCount(0);
        for (MauSac ms : mauSacService.getAll()) {
            Object[] row = {
                i++, ms.getMa(), "Màu Sắc", ms.getTen()
            };
            dtm.addRow(row);
        }
    }

    public void loadTableKichCo() {
        int i = 1;
        dtmKichCo = (DefaultTableModel) tbThuocTinh.getModel();
        dtmKichCo.setRowCount(0);
        for (KichCoRepon kc : this.kcs.getList()) {
            dtmKichCo.addRow(new Object[]{i++, kc.getMaKC(), "Kích Cỡ", kc.getTenKC()});
        }
    }

    public void themKichCo() {
        String maStr = txtMa.getText().trim();
        if (this.kcs.checkMa(maStr) != null) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập  mã ko trùng");
            return;
        } else {
            KichCo kc = this.getFormKichco();
            if (kc == null) {
                return;
            }
            kcs.insert(kc);
            loadTableKichCo();
            clearForm();
        }
    }

    public void suaKichCo() {
        int row = tbThuocTinh.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng cần sửa ");
            return;
        }
        String maStr = tbThuocTinh.getValueAt(row, 1).toString();
        KichCo kc = this.getFormKichco();
        if (kc == null) {
            return;
        }
        kcs.update(maStr, kc);
        loadTableKichCo();
        clearForm();
    }

    public void XoaKichCo() {
        int row = tbThuocTinh.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng cần xoá ");

            return;
        }
        String maStr = tbThuocTinh.getValueAt(row, 1).toString();
        int check = JOptionPane.showConfirmDialog(this, "Bạn chắc chắn muốn xoá dòng này chứ");
        if (check != JOptionPane.YES_OPTION) {
            return;
        }
        this.kcs.delete(maStr);
        loadTableKichCo();
        clearForm();
    }

    public void xoaChatLieu() {
        int row = tbThuocTinh.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng cần xoá ");

            return;
        }
        String maStr = tbThuocTinh.getValueAt(row, 1).toString();
        int check = JOptionPane.showConfirmDialog(this, "Bạn chắc chắn muốn xoá dòng này chứ");
        if (check != JOptionPane.YES_OPTION) {
            return;
        }
        this.cls.xoa(maStr);
        loadTableChatLieu();
        clearForm();
    }

    public void suaChatLieu() {
        int row = tbThuocTinh.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng cần sửa ");
            return;
        }
        String maStr = tbThuocTinh.getValueAt(row, 1).toString();
        ChatLieu cl = this.getFormChatLieu();
        if (cl == null) {
            return;
        }
        this.cls.sua(cl, maStr);
        loadTableChatLieu();
        clearForm();
    }

    public void loadTableTheLoai() {
        int i = 1;
        dtmTheloai = (DefaultTableModel) tbThuocTinh.getModel();
        dtmTheloai.setRowCount(0);
        for (TheLoaiViewModel tl : this.tls.getlist()) {
            dtmTheloai.addRow(new Object[]{i++, tl.getMatl(), "Thể Loại", tl.getTentl()});
        }
    }

    public TheLoai getFormTheLoai() {
        String ma = txtMa.getText().trim();
        String ten = txtTen.getText().trim();
        String id = "";
        if (ma.length() == 0 || ten.length() == 0) {
            JOptionPane.showMessageDialog(this, "Không được để trống");
            return null;
        }

        TheLoai tl = new TheLoai();
        tl.setMatl(ma);
        tl.setTentl(ten);
        tl.setId(id);
        return tl;
    }

    public void themTheLoai() {
        String maStr = txtMa.getText().trim();
        if (this.tls.checkMa(maStr) != null) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập  mã ko trùng");
            return;
        } else {
            TheLoai tl = this.getFormTheLoai();
            if (tl == null) {
                return;
            }
            tls.them(tl);
            loadTableTheLoai();
            clearForm();
        }
    }

    public void xoaTheloai() {
        int row = tbThuocTinh.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng cần xoá ");

            return;
        }
        String maStr = tbThuocTinh.getValueAt(row, 1).toString();
        int check = JOptionPane.showConfirmDialog(this, "Bạn chắc chắn muốn xoá dòng này chứ");
        if (check != JOptionPane.YES_OPTION) {
            return;
        }
        this.tls.xoa(maStr);
        loadTableTheLoai();
        clearForm();
    }

    public void suaTheLoai() {
        int row = tbThuocTinh.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng cần sửa ");
            return;
        }
        String maStr = tbThuocTinh.getValueAt(row, 1).toString();
        TheLoai tl = this.getFormTheLoai();
        if (tl == null) {
            return;
        }
        tls.sua(maStr, tl);
        loadTableTheLoai();
        clearForm();
    }
    private void rdMauSacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdMauSacActionPerformed

        loadTableMau();
    }//GEN-LAST:event_rdMauSacActionPerformed


    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        if (rdMauSac.isSelected()) {
            MauSac ms = this.getFormMau();
            if (ms == null) {
                return;
            }
            if (mauSacService.insert(ms) > 0) {
                loadTableMau();
                clearForm();
            }
        } else if (rdKichCo.isSelected()) {
            themKichCo();

        } else if (rdChatLieu.isSelected()) {
            themchatlieu();
        }else{
            themTheLoai();
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        int row = tbThuocTinh.getSelectedRow();
        String ma = tbThuocTinh.getValueAt(row, 1).toString();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng sửa");
            return;
        }
        if (rdMauSac.isSelected()) {
            MauSac ms = this.getFormMau();
            if (ms == null) {
                return;
            }
            if (mauSacService.update(ms, ms.getMa()) > 0) {
                loadTableMau();
                clearForm();
            }
        } else if (rdKichCo.isSelected()) {
            suaKichCo();
        } else if (rdChatLieu.isSelected()) {
            suaChatLieu();
        }else {
            suaTheLoai();
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void tbThuocTinhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbThuocTinhMouseClicked
        int row = tbThuocTinh.getSelectedRow();
        if (row == -1) {
            return;
        }
        if (rdMauSac.isSelected()) {
            txtMa.setText(tbThuocTinh.getValueAt(row, 1).toString());
            txtTen.setText(tbThuocTinh.getValueAt(row, 3).toString());
        } else if (rdKichCo.isSelected()) {
            txtMa.setText(tbThuocTinh.getValueAt(row, 1).toString());
            txtTen.setText(tbThuocTinh.getValueAt(row, 3).toString());
        } else if (rdChatLieu.isSelected()) {
            txtMa.setText(tbThuocTinh.getValueAt(row, 1).toString());
            txtTen.setText(tbThuocTinh.getValueAt(row, 3).toString());
        }else {
             txtMa.setText(tbThuocTinh.getValueAt(row, 1).toString());
            txtTen.setText(tbThuocTinh.getValueAt(row, 3).toString());
            }
    }//GEN-LAST:event_tbThuocTinhMouseClicked

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed

        if (rdMauSac.isSelected()) {
            int row = tbThuocTinh.getSelectedRow();
            String ma = tbThuocTinh.getValueAt(row, 1).toString();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng xóa");
                return;
            }
            if (mauSacService.delete(ma) > 0) {
                loadTableMau();
                clearForm();
            }
        } else if (rdKichCo.isSelected()) {
            XoaKichCo();
        } else if (rdChatLieu.isSelected()) {
            xoaChatLieu();
        }else {
            xoaTheloai();
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void rdKichCoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdKichCoActionPerformed
        // TODO add your handling code here:
        loadTableKichCo();
    }//GEN-LAST:event_rdKichCoActionPerformed

    private void rdTheLoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdTheLoaiActionPerformed
        // TODO add your handling code here:
        loadTableTheLoai();
    }//GEN-LAST:event_rdTheLoaiActionPerformed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(FrameQuanLy.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameQuanLy.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameQuanLy.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameQuanLy.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrameQuanLy().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JRadioButton rdChatLieu;
    private javax.swing.JRadioButton rdKichCo;
    private javax.swing.JRadioButton rdMauSac;
    private javax.swing.JRadioButton rdTheLoai;
    private javax.swing.JTable tbThuocTinh;
    private javax.swing.JTextField txtMa;
    private javax.swing.JTextField txtTen;
    // End of variables declaration//GEN-END:variables
}
