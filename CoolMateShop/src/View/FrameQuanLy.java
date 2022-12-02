/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import DomainModel.ChatLieu;
import DomainModel.ChiTietQuanAo;
import DomainModel.KichCo;
import DomainModel.MauSac;
import DomainModel.TheLoai;
import Service.IChiTietQuanAoService;
import Service.IKichCoService;
import Service.IMauSacService;
import Service.ITheLoaiService;
import Service.Impl.ChatLieuService;
import Service.Impl.ChiTietQuanAoService;
import Service.Impl.KichCoService;
import Service.Impl.MauSacService;
import Service.Impl.TheLoaiService;
import ViewModel.ChatLieuViewModel;
import ViewModel.ChiTietQuanAoRespone;
import ViewModel.KichCoRepon;

import ViewModel.MauSacRespone;
import ViewModel.TheLoaiViewModel;
import java.awt.Button;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author KIEN TRAN
 */
public class FrameQuanLy extends javax.swing.JFrame {

    private DefaultTableModel dtmKichCo;
    private ButtonGroup btg;
    private ChatLieuService cls;
    private DefaultTableModel dtmChatLieu;
    private DefaultTableModel dtmChiTietQA;
    private IMauSacService mauSacService;
    private DefaultTableModel dtm;
    private DefaultTableModel dtmTheloai;
    private ITheLoaiService tls;
    private IKichCoService kcs;
    private DefaultComboBoxModel dcbmCL;
    private DefaultComboBoxModel dcbmKC;
    private DefaultComboBoxModel dcbmMau;
    private DefaultComboBoxModel dcbmTL;
    private DefaultComboBoxModel dcbmQA;
    private String imageStr = null;
    private IChiTietQuanAoService chiTietQuanAoService;
    private List<MauSac> listMauSac;
    private List<ChatLieu> listChatLieu;
    private List<TheLoai> listTheLoai;
    private List<KichCo> listKichCo;

    public FrameQuanLy() {
        initComponents();
        tls = new TheLoaiService();
        kcs = new KichCoService();
        mauSacService = new MauSacService();
        cls = new ChatLieuService();
        chiTietQuanAoService = new ChiTietQuanAoService();
        setLocationRelativeTo(null);
        this.btg = new ButtonGroup();
        radidobutton();

        loadTableCTQA();
        addCbChatLieu();
        addCbMauSac();
        addCbKichCo();
        addCbTheLoai();

        cbChatLieu();
        cbKichCo();
        cbMauSac();
        cbTheLoai();
        cbTenQA();
        listMauSac = mauSacService.getAll();
        listChatLieu = cls.getAll();
        listKichCo = kcs.getAll();
        listTheLoai = tls.getAll();

        Icon icon = new ImageIcon("img/plus.png");
        btnCL.setIcon(icon);
        Icon icon1 = new ImageIcon("img/plus.png");
        btnMau.setIcon(icon1);
        Icon icon2 = new ImageIcon("img/plus.png");
        btnSize.setIcon(icon2);
        Icon icon3 = new ImageIcon("img/plus.png");
        btnTL.setIcon(icon3);
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

    public void loadTableCTQA() {
        dtmChiTietQA = (DefaultTableModel) tbChiTietQuanAo.getModel();
        dtmChiTietQA.setRowCount(0);
        for (ChiTietQuanAoRespone ctqar : chiTietQuanAoService.getAllCTQA()) {
            Object[] row = {
                ctqar.getMaSP(), ctqar.getTenSP(), ctqar.getLoaiSP(), ctqar.getKichCo(),
                ctqar.getMauSac(), ctqar.getChatLieu(), ctqar.getGiaTien(), ctqar.getSoLuong(),
                ctqar.getSoLuong() > 0 ? "Còn hàng" : "Hết hàng", ctqar.getHinhAnh()
            };
            dtmChiTietQA.addRow(row);
        }
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

    public void cbTenQA() {
        dcbmQA = (DefaultComboBoxModel) cbTen.getModel();
        dcbmQA.removeAllElements();
        dcbmQA.addElement("");
        for (ChiTietQuanAo qa : chiTietQuanAoService.getAll()) {
            dcbmQA.addElement(qa.getTenQuanAo());
        }
    }

    public void addCbChatLieu() {
        dcbmCL = (DefaultComboBoxModel) cbChatLieu.getModel();
        dcbmCL.removeAllElements();
        for (ChatLieu chatLieu : cls.getAll()) {
            dcbmCL.addElement(chatLieu);
        }
    }

    public void cbChatLieu() {
        dcbmCL = (DefaultComboBoxModel) cbcl.getModel();
        dcbmCL.removeAllElements();
        dcbmCL.addElement("");
        for (ChatLieu chatLieu : cls.getAll()) {
            dcbmCL.addElement(chatLieu);
        }
    }

    public void addCbMauSac() {
        dcbmMau = (DefaultComboBoxModel) cbMauSac.getModel();
        dcbmMau.removeAllElements();
        for (MauSac mauSac : mauSacService.getAll()) {
            dcbmMau.addElement(mauSac);
        }
    }

    public void cbMauSac() {
        dcbmMau = (DefaultComboBoxModel) cbmau.getModel();
        dcbmMau.removeAllElements();
        dcbmMau.addElement("");
        for (MauSac mauSac : mauSacService.getAll()) {
            dcbmMau.addElement(mauSac);
        }
    }

    public void addCbKichCo() {
        dcbmKC = (DefaultComboBoxModel) cbKichCo.getModel();
        dcbmKC.removeAllElements();
        for (KichCo kichCo : kcs.getAll()) {
            dcbmKC.addElement(kichCo);
        }
    }

    public void cbKichCo() {
        dcbmKC = (DefaultComboBoxModel) cbsize.getModel();
        dcbmKC.removeAllElements();
        dcbmKC.addElement("");
        for (KichCo kichCo : kcs.getAll()) {
            dcbmKC.addElement(kichCo);
        }
    }

    public void addCbTheLoai() {
        dcbmTL = (DefaultComboBoxModel) cbLoaiSP.getModel();
        dcbmTL.removeAllElements();
        for (TheLoai theLoai : tls.getAll()) {
            dcbmTL.addElement(theLoai);
        }
    }

    public void cbTheLoai() {
        dcbmTL = (DefaultComboBoxModel) cbloai.getModel();
        dcbmTL.removeAllElements();
        dcbmTL.addElement("");
        for (TheLoai theLoai : tls.getAll()) {
            dcbmTL.addElement(theLoai);
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
        txtMaSP = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtTenSP = new javax.swing.JTextField();
        cbLoaiSP = new javax.swing.JComboBox<>();
        txtGiaBan = new javax.swing.JTextField();
        cbMauSac = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        cbKichCo = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        cbChatLieu = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        txtSoLuong = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        lblImage = new javax.swing.JLabel();
        btnMau = new javax.swing.JButton();
        btnSize = new javax.swing.JButton();
        btnCL = new javax.swing.JButton();
        btnTL = new javax.swing.JButton();
        btnReload = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbChiTietQuanAo = new javax.swing.JTable();
        cbTen = new javax.swing.JComboBox<>();
        cbloai = new javax.swing.JComboBox<>();
        cbsize = new javax.swing.JComboBox<>();
        cbmau = new javax.swing.JComboBox<>();
        cbcl = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTabbedPane1.setTabPlacement(javax.swing.JTabbedPane.LEFT);

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Quản lý quần áo", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        jLabel2.setText("Mã sản phẩm");

        jLabel3.setText("Màu sắc");

        jLabel4.setText("Tên sản phẩm");

        jLabel5.setText("Loại sản phẩm");

        jLabel6.setText("Giá bán");

        cbLoaiSP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbMauSac.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel8.setText("Kích Cỡ");

        cbKichCo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel9.setText("Chất liệu");

        cbChatLieu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel10.setText("Số lượng");

        jLabel11.setText("Hình ảnh");

        jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblImage.setBackground(new java.awt.Color(255, 0, 0));
        lblImage.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblImage.setForeground(new java.awt.Color(255, 51, 0));
        lblImage.setText("UPLOAD IMAGES");
        lblImage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblImageMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblImage, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblImage, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        btnMau.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnMauMouseClicked(evt);
            }
        });

        btnSize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSizeMouseClicked(evt);
            }
        });

        btnCL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCLMouseClicked(evt);
            }
        });

        btnTL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTLMouseClicked(evt);
            }
        });

        btnReload.setText("Reload");
        btnReload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReloadActionPerformed(evt);
            }
        });

        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnAdd.setText("Thêm");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnUpdate.setText("Sửa");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnClear.setText("Mới");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAdd)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addComponent(btnUpdate)
                .addGap(21, 21, 21))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addComponent(btnClear)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd)
                    .addComponent(btnUpdate))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addComponent(btnClear)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addGap(43, 43, 43))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                                        .addComponent(jLabel10)
                                        .addGap(36, 36, 36)))
                                .addGap(13, 13, 13)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtGiaBan, javax.swing.GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE)
                                    .addComponent(txtSoLuong)))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                                        .addGap(12, 12, 12)
                                        .addComponent(cbLoaiSP, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnTL)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnReload)
                        .addGap(54, 54, 54)))
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbMauSac, 0, 302, Short.MAX_VALUE)
                    .addComponent(cbKichCo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbChatLieu, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnMau, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
                    .addComponent(btnSize, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(cbMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMau, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(cbKichCo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSize, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(cbLoaiSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9)
                        .addComponent(cbChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnTL, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnCL, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnReload)
                            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 12, Short.MAX_VALUE))
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin sản phẩm"));

        tbChiTietQuanAo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Loại sản phẩm", "Kích cỡ", "Màu sắc", "Chất liệu", "Giá Bán", "Số lượng", "Tình trạng"
            }
        ));
        tbChiTietQuanAo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbChiTietQuanAoMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbChiTietQuanAo);

        cbTen.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbTen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTenActionPerformed(evt);
            }
        });

        cbloai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbloai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbloaiActionPerformed(evt);
            }
        });

        cbsize.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbsize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbsizeActionPerformed(evt);
            }
        });

        cbmau.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbmau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbmauActionPerformed(evt);
            }
        });

        cbcl.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbcl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbclActionPerformed(evt);
            }
        });

        jLabel12.setText("Tên quần áo");

        jLabel13.setText("Loại quần áo");

        jLabel14.setText("Kích cỡ");

        jLabel15.setText("Màu sắc");

        jLabel16.setText("Chất liệu");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbTen, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbloai, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(38, 38, 38)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(cbsize, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(35, 35, 35))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbmau, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(44, 44, 44)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbcl, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbloai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbsize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbmau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbcl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                        .addGap(0, 113, Short.MAX_VALUE))))
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
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 931, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
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
        } else {
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
        } else {
            xoaTheloai();
        }
    }//GEN-LAST:event_btnXoaActionPerformed

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
        } else {
            suaTheLoai();
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        if (rdMauSac.isSelected()) {
            MauSac ms = this.getFormMau();
            if (ms == null) {
                return;
            }
            if (mauSacService.checkMa(txtMa.getText().trim()) != null) {
                JOptionPane.showMessageDialog(this, "Trùng mã");
                return;
            }
            if (mauSacService.insert(ms) > 0) {
                loadTableMau();
                clearForm();
                addCbMauSac();
            }
        } else if (rdKichCo.isSelected()) {
            themKichCo();
            addCbKichCo();
        } else if (rdChatLieu.isSelected()) {
            themchatlieu();
            addCbChatLieu();
        } else {
            themTheLoai();
            addCbTheLoai();
        }
    }//GEN-LAST:event_btnThemActionPerformed


    private void rdChatLieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdChatLieuActionPerformed
        // TODO add your handling code here:
        loadTableChatLieu();
    }//GEN-LAST:event_rdChatLieuActionPerformed

    private void rdTheLoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdTheLoaiActionPerformed
        // TODO add your handling code here:
        loadTableTheLoai();
    }//GEN-LAST:event_rdTheLoaiActionPerformed

    private void rdKichCoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdKichCoActionPerformed
        // TODO add your handling code here:
        loadTableKichCo();
    }//GEN-LAST:event_rdKichCoActionPerformed

    private void rdMauSacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdMauSacActionPerformed

        loadTableMau();
    }//GEN-LAST:event_rdMauSacActionPerformed

    private void lblImageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblImageMouseClicked
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            Icon icon = new ImageIcon(file.getAbsolutePath());
            imageStr = file.getName();
            lblImage.setText("");
            this.lblImage.setIcon(icon);
        }
    }//GEN-LAST:event_lblImageMouseClicked

    private void tbChiTietQuanAoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbChiTietQuanAoMouseClicked
        int row = tbChiTietQuanAo.getSelectedRow();
        if (row == -1) {
            return;
        }
        txtMaSP.setText(tbChiTietQuanAo.getValueAt(row, 0).toString());
        txtTenSP.setText(tbChiTietQuanAo.getValueAt(row, 1).toString());
        String loai = tbChiTietQuanAo.getValueAt(row, 2).toString();
        cbLoaiSP.setSelectedItem(loai);
        String kc = tbChiTietQuanAo.getValueAt(row, 3).toString();
        cbKichCo.setSelectedItem(kc);
        String mms = tbChiTietQuanAo.getValueAt(row, 4).toString();
        cbMauSac.setSelectedItem(mms);
        String cl = tbChiTietQuanAo.getValueAt(row, 5).toString();
        cbChatLieu.setSelectedItem(cl);
        txtGiaBan.setText(tbChiTietQuanAo.getValueAt(row, 6).toString());
        txtSoLuong.setText(tbChiTietQuanAo.getValueAt(row, 7).toString());
        String icon = chiTietQuanAoService.getAllCTQA().get(row).getHinhAnh();

        lblImage.setIcon(new ImageIcon(icon));
        lblImage.setText("");

    }//GEN-LAST:event_tbChiTietQuanAoMouseClicked

    public void cleaFormCTQA() {
        txtMaSP.setText("");
        txtTenSP.setText("");
        txtGiaBan.setText("");
        txtSoLuong.setText("");
        lblImage.setText("");
        cbChatLieu.setSelectedIndex(0);
        cbMauSac.setSelectedIndex(0);
        cbLoaiSP.setSelectedIndex(0);
        cbKichCo.setSelectedIndex(0);
    }
    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        ChiTietQuanAo ctqa = this.getFormData();
        if (ctqa == null) {
            return;
        }
        if (chiTietQuanAoService.checkMa(txtMaSP.getText().trim()) != null) {
            JOptionPane.showMessageDialog(this, "Trùng mã");
            return;
        }
        if (chiTietQuanAoService.insert(ctqa) > 0) {
            JOptionPane.showMessageDialog(this, "Thành công");
            loadTableCTQA();
            cleaFormCTQA();
        } else {
            JOptionPane.showMessageDialog(this, "Thất bại");
            return;
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        cleaFormCTQA();
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        int row = tbChiTietQuanAo.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng muốn sửa");
            return;
        }
        String ma = tbChiTietQuanAo.getValueAt(row, 0).toString();
        ChiTietQuanAo ctqa = this.getFormData();
        if (ctqa == null) {
            return;
        }
        if (chiTietQuanAoService.update(ctqa, ma) > 0) {
            JOptionPane.showMessageDialog(this, "Thành công");
            loadTableCTQA();
            cleaFormCTQA();
        } else {
            JOptionPane.showMessageDialog(this, "Thất bại");
            return;
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnTLMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTLMouseClicked
        FrameTheLoai tl = new FrameTheLoai();
        tl.setVisible(true);
        tl.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_btnTLMouseClicked

    private void btnMauMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMauMouseClicked
        FrameMauSac ms = new FrameMauSac();
        ms.setVisible(true);
        ms.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_btnMauMouseClicked

    private void btnSizeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSizeMouseClicked
        FrameKichCo kc = new FrameKichCo();
        kc.setVisible(true);
        kc.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_btnSizeMouseClicked

    private void btnCLMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCLMouseClicked
        FrameChatLieu cl = new FrameChatLieu();
        cl.setVisible(true);
        cl.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_btnCLMouseClicked

    private void btnReloadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReloadActionPerformed
        addCbChatLieu();
        addCbMauSac();
        addCbKichCo();
        addCbTheLoai();
    }//GEN-LAST:event_btnReloadActionPerformed

    private void cbTenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTenActionPerformed
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(((DefaultTableModel) tbChiTietQuanAo.getModel()));
        sorter.setRowFilter(RowFilter.regexFilter(String.valueOf(cbTen.getSelectedItem())));

        tbChiTietQuanAo.setRowSorter(sorter);
    }//GEN-LAST:event_cbTenActionPerformed

    private void cbloaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbloaiActionPerformed
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(((DefaultTableModel) tbChiTietQuanAo.getModel()));
        sorter.setRowFilter(RowFilter.regexFilter(String.valueOf(cbloai.getSelectedItem())));

        tbChiTietQuanAo.setRowSorter(sorter);
    }//GEN-LAST:event_cbloaiActionPerformed

    private void cbsizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbsizeActionPerformed
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(((DefaultTableModel) tbChiTietQuanAo.getModel()));
        sorter.setRowFilter(RowFilter.regexFilter(String.valueOf(cbsize.getSelectedItem())));

        tbChiTietQuanAo.setRowSorter(sorter);
    }//GEN-LAST:event_cbsizeActionPerformed

    private void cbmauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbmauActionPerformed
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(((DefaultTableModel) tbChiTietQuanAo.getModel()));
        sorter.setRowFilter(RowFilter.regexFilter(String.valueOf(cbmau.getSelectedItem())));

        tbChiTietQuanAo.setRowSorter(sorter);
    }//GEN-LAST:event_cbmauActionPerformed

    private void cbclActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbclActionPerformed
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(((DefaultTableModel) tbChiTietQuanAo.getModel()));
        sorter.setRowFilter(RowFilter.regexFilter(String.valueOf(cbcl.getSelectedItem())));

        tbChiTietQuanAo.setRowSorter(sorter);
    }//GEN-LAST:event_cbclActionPerformed

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

    public ChiTietQuanAo getFormData() {
        int mauSac = cbMauSac.getSelectedIndex();
        int theLoai = cbLoaiSP.getSelectedIndex();
        int kichCo = cbKichCo.getSelectedIndex();
        int chatLieu = cbChatLieu.getSelectedIndex();
        MauSac ms = listMauSac.get(mauSac);
        TheLoai tl = listTheLoai.get(theLoai);
        KichCo kc = listKichCo.get(kichCo);
        ChatLieu cl = listChatLieu.get(chatLieu);

        String tenQuanAo = txtTenSP.getText().trim();
        String maQuanAo = txtMaSP.getText().trim();
        String giaBanStr = txtGiaBan.getText().trim();
        String soLuongStr = txtSoLuong.getText().trim();
        String hinhAnh = String.valueOf(lblImage.getIcon());
        int trangThai = 0;

        if (tenQuanAo.length() == 0 || maQuanAo.length() == 0 || giaBanStr.length() == 0
                || soLuongStr.length() == 0 || hinhAnh.length() == 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng không để trống");
            return null;
        }

        float giaBan = -1;
        try {
            giaBan = Float.parseFloat(giaBanStr);
            if (giaBan < 0) {
                JOptionPane.showMessageDialog(this, "Giá bán không được âm");
                return null;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Giá bán phải là số");
            return null;
        }
        int soLuong = -1;
        try {
            soLuong = Integer.parseInt(soLuongStr);
            if (soLuong < 0) {
                JOptionPane.showMessageDialog(this, "Số lượng không được âm");
                return null;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Số lượng phải là số");
            return null;
        }
        if (soLuong > 0) {
            trangThai = 1;
        } else {
            trangThai = 0;
        }
        ChiTietQuanAo ctqa = new ChiTietQuanAo();
        ctqa.setIdMau(ms.getId());
        ctqa.setIdTheLoai(tl.getId());
        ctqa.setIdKichCo(kc.getId());
        ctqa.setIdChatLieu(cl.getId());
        ctqa.setTenQuanAo(tenQuanAo);
        ctqa.setMaQuanAo(maQuanAo);
        ctqa.setGiaBan(giaBan);
        ctqa.setSoLuong(soLuong);
        ctqa.setTrangThai(trangThai);
        ctqa.setHinhAnh(hinhAnh);

        return ctqa;
    }

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
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnCL;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnMau;
    private javax.swing.JButton btnReload;
    private javax.swing.JButton btnSize;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnTL;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<String> cbChatLieu;
    private javax.swing.JComboBox<String> cbKichCo;
    private javax.swing.JComboBox<String> cbLoaiSP;
    private javax.swing.JComboBox<String> cbMauSac;
    private javax.swing.JComboBox<String> cbTen;
    private javax.swing.JComboBox<String> cbcl;
    private javax.swing.JComboBox<String> cbloai;
    private javax.swing.JComboBox<String> cbmau;
    private javax.swing.JComboBox<String> cbsize;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JLabel lblImage;
    private javax.swing.JRadioButton rdChatLieu;
    private javax.swing.JRadioButton rdKichCo;
    private javax.swing.JRadioButton rdMauSac;
    private javax.swing.JRadioButton rdTheLoai;
    private javax.swing.JTable tbChiTietQuanAo;
    private javax.swing.JTable tbThuocTinh;
    private javax.swing.JTextField txtGiaBan;
    private javax.swing.JTextField txtMa;
    private javax.swing.JTextField txtMaSP;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTen;
    private javax.swing.JTextField txtTenSP;
    // End of variables declaration//GEN-END:variables
}
