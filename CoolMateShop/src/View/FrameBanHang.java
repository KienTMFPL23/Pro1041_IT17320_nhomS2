/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import DomainModel.ChatLieu;
import DomainModel.ChiTietHoaDon;
import DomainModel.ChiTietQuanAo;
import DomainModel.HoaDon;
import DomainModel.KhachHang;
import DomainModel.KichCo;
import DomainModel.MauSac;
import DomainModel.TheLoai;
import DomainModel.users;
import Repositories.Impl.HoaDonReponsitory;
import Service.IChatLieuService;
import Service.IChiTietHoaDonService;
import Service.IChiTietQuanAoService;
import Service.IHoaDonService;
import Service.IKhachHangService;
import Service.IKichCoService;
import Service.IMauSacService;
import Service.ITheLoaiService;
import Service.IUsersSevice;
import Service.Impl.ChatLieuService;
import Service.Impl.ChiTietHoaDonService;
import Service.Impl.ChiTietQuanAoService;
import Service.Impl.HoaDonService;
import Service.Impl.KhachHangService;
import Service.Impl.KichCoService;
import Service.Impl.MauSacService;
import Service.Impl.TheLoaiService;
import Service.Impl.UsersSerice;
import ViewModel.ChiTietHoaDonRespone;
import ViewModel.ChiTietQuanAoRespone;
import ViewModel.HoaDonViewModel;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.awt.print.PrinterException;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author KIEN TRAN
 */
public class FrameBanHang extends javax.swing.JFrame implements Runnable, ThreadFactory {

    private IChiTietQuanAoService chiTietQuanAoService;
    private IChiTietHoaDonService chiTietHoaDonService;
    private IUsersSevice userService;
    private IKhachHangService khachHangService;
    private DefaultTableModel dtm;
    private DefaultTableModel dtmGioHang;
    private DefaultTableModel dtmHoaDon;
    private ITheLoaiService tlService;
    private IMauSacService msService;
    private IChatLieuService clService;
    private IKichCoService kcService;
    private IHoaDonService hoaDonService;
    private DefaultComboBoxModel dcbm;
    private int indexHoaDonSelect;
    private String idHD = "";
    private String idCTQA = "";
    private String idCTHD = "";

    private List<ChiTietQuanAoRespone> listQuanAo;
    private List<ChiTietQuanAo> listQA;
    private List<ChiTietHoaDonRespone> listChiTietHD;
    private List<HoaDonViewModel> listHoaDon;
    private List<HoaDon> listhd;
    private List<KhachHang> listKhachHang;
    private List<users> listUser;
    private int a = 1;
    private int i = 1;
    private int rowHD;

    private WebcamPanel panel = null;
    private Webcam webcam = null;
    private static final long serialVersionUID = 6441489157408381878L;
    private Executor executor = Executors.newSingleThreadExecutor(this);

    public FrameBanHang() {
        initComponents();

        chiTietQuanAoService = new ChiTietQuanAoService();
        chiTietHoaDonService = new ChiTietHoaDonService();
        hoaDonService = new HoaDonService();
        userService = new UsersSerice();
        khachHangService = new KhachHangService();
        tlService = new TheLoaiService();
        clService = new ChatLieuService();
        kcService = new KichCoService();
        msService = new MauSacService();

        listQuanAo = chiTietQuanAoService.getAllCTQA();
        listQA = chiTietQuanAoService.getAll();
        listChiTietHD = chiTietHoaDonService.getAll();

        listHoaDon = hoaDonService.getAll();
        listhd = hoaDonService.selectList();
        listKhachHang = khachHangService.getList();
        listUser = userService.getlist();

        indexHoaDonSelect = 0;
        rowHD = 0;
        loadTableSP(listQuanAo);
        loadTableHoaDon(listHoaDon);

        addComboTenQA();
        addComboboxChatLieu();
        addComboboxMauSac();
        addComboboxKichCo();
        addComboboxTheLoai();
//        addComboNV();
        addComboKH();
        getValuesTongTien();
        btnThanhToan.setEnabled(false);
        setData();
        Icon icon = new ImageIcon("img/plus.png");
        btnAddKh.setIcon(icon);
        btnAddKh.setText("");
        Icon icon1 = new ImageIcon("img/pay.png");
        btnThanhToan.setIcon(icon1);
        Icon icon2 = new ImageIcon("img/invoice.png");
        btnTaoHoaDon.setIcon(icon2);
        Icon icon3 = new ImageIcon("img/printer.png");
        btnPrint.setIcon(icon3);
        initWebcam();
        setLocationRelativeTo(null);
        setWebcam();
    }

    public void setWebcam() {
        FrameBanHang bh = this;
        bh.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                webcam.close();
            }
        });
    }

    public void setData() {
        DangNhap dn = new DangNhap();
        String maUs = dn.maUser;
        System.out.println(maUs);
        for (users us : listUser) {
            if (maUs.equalsIgnoreCase(us.getMa())) {
                lblNV.setText(us.getHoten());
            }
        }
    }

    private void initWebcam() {
        Dimension size = WebcamResolution.QVGA.getSize();
        webcam = Webcam.getWebcams().get(0);
        webcam.setViewSize(size);

        panel = new WebcamPanel(webcam);
        panel.setPreferredSize(size);
        panel.setFPSDisplayed(true);

        pnQR.add(panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 176, 150));

        executor.execute((Runnable) this);
    }

//    public void addComboNV() {
//        dcbm = (DefaultComboBoxModel) cbNhanVien.getModel();
//        dcbm.removeAllElements();
////        dcbm.addElement("");
//        for (users us : userService.getlist()) {
//            dcbm.addElement(us.getHoten());
//        }
//    }
    public void addComboKH() {
        dcbm = (DefaultComboBoxModel) cbKhachHang.getModel();
        dcbm.removeAllElements();
//        dcbm.addElement("");
        for (KhachHang KH : khachHangService.getList()) {
            dcbm.addElement(KH.getHoTen());
        }
    }

    public void addComboTenQA() {
        dcbm = (DefaultComboBoxModel) cbTenQuanAo.getModel();
        dcbm.removeAllElements();
        dcbm.addElement("");
        for (ChiTietQuanAoRespone qa : listQuanAo) {
            dcbm.addElement(qa.getTenSP());
        }
    }

    public void addComboboxTheLoai() {
        dcbm = (DefaultComboBoxModel) cbTheLoai.getModel();
        dcbm.removeAllElements();
        dcbm.addElement("");
        for (TheLoai tl : tlService.getAll()) {
            dcbm.addElement(tl.getTentl());
        }
    }

    public void addComboboxMauSac() {
        dcbm = (DefaultComboBoxModel) cbMauSac.getModel();
        dcbm.removeAllElements();
        dcbm.addElement("");
        for (MauSac ms : msService.getAll()) {
            dcbm.addElement(ms.getTen());
        }
    }

    public void addComboboxKichCo() {
        dcbm = (DefaultComboBoxModel) cbKichCo.getModel();
        dcbm.removeAllElements();
        dcbm.addElement("");
        for (KichCo kc : kcService.getAll()) {
            dcbm.addElement(kc.getSize());
        }
    }

    public void addComboboxChatLieu() {
        dcbm = (DefaultComboBoxModel) cbChatLieu.getModel();
        dcbm.removeAllElements();
        dcbm.addElement("");
        for (ChatLieu cl : clService.getAll()) {
            dcbm.addElement(cl.getTen());
        }
    }

    public void loadTableSP(List<ChiTietQuanAoRespone> lst) {
        dtm = (DefaultTableModel) tbChiTietSanPham.getModel();
        dtm.setRowCount(0);
        for (ChiTietQuanAoRespone ctqa : lst) {
            Object[] row = {
                ctqa.getMaSP(), ctqa.getTenSP(), ctqa.getLoaiSP(), ctqa.getKichCo(),
                ctqa.getMauSac(), ctqa.getChatLieu(), ctqa.getSoLuong(), ctqa.getGiaTien()
            };
            dtm.addRow(row);
        }
    }

    public void loadTableHoaDon(List<HoaDonViewModel> lst) {
        dtmHoaDon = (DefaultTableModel) tbHoaDon.getModel();
        dtmHoaDon.setRowCount(0);
        for (HoaDonViewModel hd : lst) {
            Object[] row = {
                hd.getMaHD(), hd.getNgayTao(), hd.getKhachHang(), hd.getTinhTrang()
            };
            dtmHoaDon.addRow(row);
        }
    }

    public void filter(String query) {
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(dtm);
        tbChiTietSanPham.setRowSorter(tr);

        if (query != "None") {
            tr.setRowFilter(RowFilter.regexFilter(query));
        } else {
            tbChiTietSanPham.setRowSorter(tr);
        }
    }

    public void getValuesTongTien() {
        float sumMoney = 0;
        for (int i = 0; i < tbGioHang.getRowCount(); i++) {
            sumMoney = sumMoney + Float.parseFloat(tbGioHang.getValueAt(i, 4).toString());
        }
        txtTongTien.setText(Float.toString(sumMoney));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbChiTietSanPham = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        cbTheLoai = new javax.swing.JComboBox<>();
        cbKichCo = new javax.swing.JComboBox<>();
        cbMauSac = new javax.swing.JComboBox<>();
        cbChatLieu = new javax.swing.JComboBox<>();
        cbTenQuanAo = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbGioHang = new javax.swing.JTable();
        btnUpdate = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbHoaDon = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtSDT = new javax.swing.JTextField();
        btnAddKh = new javax.swing.JButton();
        cbKhachHang = new javax.swing.JComboBox<>();
        btnLoad = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtTongTien = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtTienKhachDua = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtTienThua = new javax.swing.JTextField();
        btnThanhToan = new javax.swing.JButton();
        btnPrint = new javax.swing.JButton();
        btnTaoHoaDon = new javax.swing.JButton();
        lblMaHD = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblNV = new javax.swing.JLabel();
        pnQR = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jButton2.setText("jButton2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Th??ng tin s???n ph???m", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(255, 0, 51))); // NOI18N

        tbChiTietSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "M?? qu???n ??o", "T??n qu???n ??o", "Lo???i qu???n ??o", "K??ch c???", "M??u s???c", "Ch???t li???u", "S??? l?????ng", "Gi?? b??n"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbChiTietSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbChiTietSanPhamMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tbChiTietSanPhamMouseEntered(evt);
            }
        });
        jScrollPane2.setViewportView(tbChiTietSanPham);

        jLabel8.setText("T??n qu???n ??o");

        jLabel9.setText("Th??? lo???i");

        jLabel10.setText("K??ch c???");

        jLabel11.setText("M??u s???c");

        jLabel12.setText("Ch???t li???u");

        cbTheLoai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbTheLoai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTheLoaiActionPerformed(evt);
            }
        });

        cbKichCo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbKichCo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbKichCoActionPerformed(evt);
            }
        });

        cbMauSac.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbMauSac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbMauSacActionPerformed(evt);
            }
        });

        cbChatLieu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbChatLieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbChatLieuActionPerformed(evt);
            }
        });

        cbTenQuanAo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbTenQuanAo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbTenQuanAoItemStateChanged(evt);
            }
        });
        cbTenQuanAo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTenQuanAoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(168, 168, 168)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbTenQuanAo, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbTheLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbKichCo, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(64, 64, 64)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(cbMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cbChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 358, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbTenQuanAo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbTheLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbKichCo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Gi??? H??ng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(255, 51, 51))); // NOI18N

        tbGioHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "M?? qu???n ??o", "T??n qu???n ??o", "S??? l?????ng", "????n gi??", "Th??nh ti???n"
            }
        ));
        tbGioHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbGioHangMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbGioHang);

        btnUpdate.setText("C???p nh???t s???n ph???m");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnXoa.setText("X??a s???n ph???m");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnXoa)
                .addGap(15, 15, 15)
                .addComponent(btnUpdate)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUpdate)
                    .addComponent(btnXoa))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "H??a ????n ch???", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(255, 0, 0))); // NOI18N

        tbHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "M?? HD", "Ng??y t???o", "Kh??ch h??ng", "Tr???ng th??i"
            }
        ));
        tbHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbHoaDonMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tbHoaDon);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 557, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(0, 16, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "H??a ????n", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(255, 0, 51))); // NOI18N

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("H??? T??n KH");

        jLabel2.setText("SDT");

        txtSDT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSDTActionPerformed(evt);
            }
        });
        txtSDT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSDTKeyReleased(evt);
            }
        });

        btnAddKh.setText("-");
        btnAddKh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddKhActionPerformed(evt);
            }
        });

        cbKhachHang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnLoad.setText("Reload");
        btnLoad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoadActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(26, 26, 26)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtSDT)
                    .addComponent(cbKhachHang, 0, 281, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnAddKh, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addComponent(btnLoad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cbKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLoad))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAddKh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel3.setText("M?? HD");

        jLabel5.setText("T???ng ti???n");

        jLabel6.setText("Ti???n kh??ch tr???");

        txtTienKhachDua.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTienKhachDuaKeyReleased(evt);
            }
        });

        jLabel7.setText("Ti???n th???a");

        btnThanhToan.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnThanhToan.setForeground(new java.awt.Color(255, 51, 51));
        btnThanhToan.setText("Thanh to??n");
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });

        btnPrint.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnPrint.setForeground(new java.awt.Color(255, 0, 0));
        btnPrint.setText("In h??a ????n");
        btnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintActionPerformed(evt);
            }
        });

        btnTaoHoaDon.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnTaoHoaDon.setForeground(new java.awt.Color(255, 51, 0));
        btnTaoHoaDon.setText("T???o h??a ????n ");
        btnTaoHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoHoaDonActionPerformed(evt);
            }
        });

        lblMaHD.setText("-");

        jLabel4.setText("Nh??n vi??n");

        lblNV.setText("-");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(btnThanhToan)
                        .addGap(32, 32, 32)
                        .addComponent(btnTaoHoaDon)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addGap(83, 83, 83)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblMaHD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTienThua, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtTienKhachDua, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
                                        .addComponent(txtTongTien, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE))
                                    .addComponent(lblNV, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 5, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(lblNV))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMaHD)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtTienKhachDua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtTienThua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnThanhToan, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                    .addComponent(btnTaoHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnPrint, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnQR.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 160, Short.MAX_VALUE)
        );

        pnQR.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -2, 176, 160));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnQR, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(21, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(pnQR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tbChiTietSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbChiTietSanPhamMouseClicked
        if (tbHoaDon.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Vui l??ng ch???n h??a ????n");
            return;
        }
        if (evt.getClickCount() == 2) {
            int row = tbChiTietSanPham.getSelectedRow();
            String sltonStr = tbChiTietSanPham.getValueAt(row, 6).toString();

            ChiTietQuanAoRespone ctqa = listQuanAo.get(row);
            idCTQA = listQA.get(row).getId();

            if (chiTietHoaDonService.getIdQA(this.idHD, this.idCTQA) != null) {
                JOptionPane.showMessageDialog(this, "???? c?? s???n ph???m n??y trong gi??? h??ng");
                return;
            }
            String soLuongMuaStr = JOptionPane.showInputDialog(this, "b???n mu???n s??? l?????ng bao nhi??u");
            try {
                int soLuongMua = Integer.parseInt(soLuongMuaStr);
                int slTon = Integer.parseInt(sltonStr);
                if (soLuongMua > slTon || soLuongMua < 1) {
                    JOptionPane.showMessageDialog(this, "Khong du san pham");
                    return;
                }

                int SluongSauKhiMua = slTon - soLuongMua;
                tbChiTietSanPham.setValueAt(SluongSauKhiMua, row, 6);

                Float donGia = (Float) tbChiTietSanPham.getValueAt(row, 7);

                ///insert cthd
                ChiTietHoaDon cthd = new ChiTietHoaDon();
                cthd.setIdHD(this.idHD);
                cthd.setIdChiTietQA(this.idCTQA);
                cthd.setSoLuong(soLuongMua);
                cthd.setDonGia(donGia);

                chiTietHoaDonService.insert(cthd);

                ChiTietHoaDonRespone chiTietHoaDon = new ChiTietHoaDonRespone();
                chiTietHoaDon.setIdHD(this.idHD);
                chiTietHoaDon.setMaQuanAo(ctqa.getMaSP());
                chiTietHoaDon.setTenQuanAO(ctqa.getTenSP());
                chiTietHoaDon.setSoLuong(soLuongMua);
                chiTietHoaDon.setDonGia(ctqa.getGiaTien());
                chiTietHoaDon.thanhTien(soLuongMua, ctqa.getGiaTien());

                listChiTietHD.add(chiTietHoaDon);

                loadTableChiTietHD(listChiTietHD);

                chiTietQuanAoService.updateSoLuong(this.idCTQA, SluongSauKhiMua);

                getValuesTongTien();
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Vui long nhap so");
                return;
            }
        }
    }//GEN-LAST:event_tbChiTietSanPhamMouseClicked

    public void loadTableChiTietHD(List<ChiTietHoaDonRespone> list) {

        dtmGioHang = (DefaultTableModel) tbGioHang.getModel();
        dtmGioHang.setRowCount(0);
        for (ChiTietHoaDonRespone ct : list) {
            Object[] row = {
                ct.getMaQuanAo(), ct.getTenQuanAO(), ct.getSoLuong(),
                ct.getDonGia(), ct.thanhTien(ct.getSoLuong(), ct.getDonGia())
            };
            dtmGioHang.addRow(row);
        }
    }
    private void txtTienKhachDuaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTienKhachDuaKeyReleased
        try {
            float sum = Float.parseFloat(txtTongTien.getText());
            String tienDuaStr = txtTienKhachDua.getText().trim();
            float tienDua = Float.parseFloat(tienDuaStr);
            float tienThua = tienDua - sum;
            String tienThuaStr = String.valueOf(tienThua);
            txtTienThua.setText(tienThuaStr);
            if (tienThua < 0 || tienThuaStr.isEmpty()) {
                btnThanhToan.setEnabled(false);
            } else {
                btnThanhToan.setEnabled(true);
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Vui l??ng nh???p s???");
            return;
        }
    }//GEN-LAST:event_txtTienKhachDuaKeyReleased

    public void showHDCT(List<ChiTietHoaDonRespone> list) {
        int row = tbHoaDon.getSelectedRow();
        if (row == -1) {
            return;
        }

        list = chiTietHoaDonService.getListHD(this.idHD);
        if (list != null) {
            dtmGioHang = (DefaultTableModel) tbGioHang.getModel();
            dtmGioHang.setRowCount(0);
            for (ChiTietHoaDonRespone cthd : list) {
                Object[] rowData = {
                    cthd.getMaQuanAo(), cthd.getTenQuanAO(), cthd.getSoLuong(), cthd.getDonGia(),
                    cthd.thanhTien(cthd.getSoLuong(), cthd.getDonGia())
                };
                dtmGioHang.addRow(rowData);
            }
            getValuesTongTien();
        }
    }
    private void tbHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbHoaDonMouseClicked
        int row = tbHoaDon.getSelectedRow();
        lblMaHD.setText(tbHoaDon.getValueAt(row, 0).toString());
        idHD = listHoaDon.get(row).getId();
        listChiTietHD = chiTietHoaDonService.getListHD(idHD);
        showHDCT(listChiTietHD);
        System.out.println(idHD);
    }//GEN-LAST:event_tbHoaDonMouseClicked
    JTextArea bill = new JTextArea();

    public void bill_print() {
        long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);
        bill.setText("                                             COOLMATE  \n");
        bill.setText(bill.getText() + "\t5 C???U DI???N B???C T??? LI??M- H?? N???I \n");
        bill.setText(bill.getText() + "\t       Th???i trang v?? h??n th??? n???a, \n");
        bill.setText(bill.getText() + "-----------------------------------------------------------------------\n");
        bill.setText(bill.getText() + "M?? h??a ????n:\t" + lblMaHD.getText() + "\n");
        bill.setText(bill.getText() + "T??n Nh??n Vi??n:" + lblNV.getText() + "\n");
        bill.setText(bill.getText() + "T??n Kh??ch h??ng:\t" + cbKhachHang.getSelectedItem().toString() + "\n");
        bill.setText(bill.getText() + "Ng??y mua:\t" + date + "\n");
        bill.setText(bill.getText() + "-----------------------------------------------------------------------\n");
        bill.setText(bill.getText() + " T??n sp \tS??? l?????ng \t????n gi?? \tT???ng ti???n\n");
        bill.setText(bill.getText() + "------------------------------------------------------------------------\n");

        DefaultTableModel df = (DefaultTableModel) tbGioHang.getModel();
        for (int i = 0; i < tbGioHang.getRowCount(); i++) {

            String name = df.getValueAt(i, 1).toString();
            String qt = df.getValueAt(i, 2).toString();
            String prc = df.getValueAt(i, 3).toString();
            String tong = df.getValueAt(i, 4).toString();

            bill.setText(bill.getText() + name + "\t" + qt + "\t" + prc + "\t " + tong + "\n");
        }
        bill.setText(bill.getText() + "----------------------------------------------------------------\n");
        bill.setText(bill.getText() + "T???ng ti???n :\t" + txtTongTien.getText() + "\n");
        bill.setText(bill.getText() + "Ti???n kh??ch ????a :\t" + txtTienKhachDua.getText() + "\n");
        bill.setText(bill.getText() + "Ti???n th???a :\t" + txtTienThua.getText() + "\n");
        bill.setText(bill.getText() + "====================================\n");
        bill.setText(bill.getText() + "                     Thanks For Your Business...!" + "\n");
        bill.setText(bill.getText() + "----------------------------------------------------------------\n");
        bill.setText(bill.getText() + "                     Software by Techinbox" + "\n");
    }

    public void thanhToan() {
        int choice = JOptionPane.showConfirmDialog(this, "B???n mu???n thanh to??n kh??ng");

        if (choice == JOptionPane.YES_OPTION) {
            String sdt = txtSDT.getText();
            int khachHang = cbKhachHang.getSelectedIndex();
            KhachHang kh = listKhachHang.get(khachHang);

            DangNhap dn = new DangNhap();
            String user = dn.maUser;
            String idUser = "";
            for (users us : listUser) {
                if (user.equalsIgnoreCase(us.getMa())) {
                    idUser = us.getId();
                }
            }
            //update hoa don
            long millis = System.currentTimeMillis();
            java.sql.Date date = new java.sql.Date(millis);
            int trangThai = 1;

            HoaDon hd = new HoaDon(kh.getId(), idUser, date, trangThai);
//        hoaDonService.updateHoaDon(hd, idHD);
            if (hoaDonService.updateHoaDon(hd, idHD) > 0) {
                JOptionPane.showMessageDialog(this, "Th??nh c??ng");
            }
            bill_print();
            lblMaHD.setText("-");

            txtSDT.setText("");
            txtTongTien.setText("");
            txtTienThua.setText("");
            txtTienKhachDua.setText("");
            lblNV.setText("-");
            cbKhachHang.setSelectedIndex(0);
            dtmHoaDon = (DefaultTableModel) tbHoaDon.getModel();
            dtmHoaDon.removeRow(rowHD);

            dtmGioHang = (DefaultTableModel) tbGioHang.getModel();
            int row = dtmGioHang.getRowCount();
            for (int j = row - 1; j >= 0; j--) {
                dtmGioHang.removeRow(j);
            }
        }
    }

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed

        thanhToan();
    }//GEN-LAST:event_btnThanhToanActionPerformed

    private void cbTenQuanAoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbTenQuanAoItemStateChanged
//        
    }//GEN-LAST:event_cbTenQuanAoItemStateChanged


    private void btnTaoHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoHoaDonActionPerformed
//        dtmHoaDon.setRowCount(0);
        int soLuongHD = tbHoaDon.getRowCount();
        if (soLuongHD > 4) {
            JOptionPane.showMessageDialog(this, "Qu?? s??? l?????ng cho ph??p");
            return;
        }
        HoaDon hd = new HoaDon();
        Random random = new Random();

        int ma = random.nextInt(99999999);
        String mahd = String.valueOf("HD" + ma);

        long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);

        hd.setMaHd(mahd);
        hd.setNgayTao(date);
//        hd.setIdUser((String) cbNhanVien.getSelectedItem());
        hd.setTinhTrang(0);

//        listhd.add(hd);
//        HoaDonViewModel hdv = new HoaDonViewModel();
//        hdv.setId(listhd.get(i).getId());
//        hdv.setMaHD(mahd);
//        hdv.setNgayTao(date);
//        hdv.setTinhTrang(0);
//        listHoaDon.add(hdv);
        hoaDonService.hdCho(hd);
        listHoaDon = hoaDonService.getAll();
        loadTableHoaDon(listHoaDon);

    }//GEN-LAST:event_btnTaoHoaDonActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        int row = tbGioHang.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Vui l??ng ch???n d??ng mu???n c???p nh???t");
            return;
        }
        String ma = tbGioHang.getValueAt(row, 0).toString();
        int slton = chiTietQuanAoService.getSLTon(ma);
        int soLuongMua = chiTietHoaDonService.getSlMua(idCTHD);
        String soLuongUpdateStr = JOptionPane.showInputDialog(this, "Nh???p s??? l?????ng b???n mu???n");

        try {
            int slUpdate = Integer.parseInt(soLuongUpdateStr);
            if (slUpdate > slton || slUpdate < 1) {
                JOptionPane.showMessageDialog(this, "Kh??ng h???p l???");
                return;
            }

            listChiTietHD = chiTietHoaDonService.getListHD(idHD);
            idCTHD = listChiTietHD.get(row).getId();
            //update hdct
            chiTietHoaDonService.updateSLuong(idCTHD, slUpdate);
            listChiTietHD = chiTietHoaDonService.getListHD(this.idHD);
            showHDCT(listChiTietHD);

            //update ctsp
            chiTietQuanAoService.updateSoLuong(ma, (slton + soLuongMua) - slUpdate);
            listQuanAo = chiTietQuanAoService.getAllCTQA();
            loadTableSP(listQuanAo);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Kh??ng h???p l???");
            return;
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void cbTenQuanAoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTenQuanAoActionPerformed
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(((DefaultTableModel) tbChiTietSanPham.getModel()));
        sorter.setRowFilter(RowFilter.regexFilter(String.valueOf(cbTenQuanAo.getSelectedItem())));

        tbChiTietSanPham.setRowSorter(sorter);
    }//GEN-LAST:event_cbTenQuanAoActionPerformed

    private void cbTheLoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTheLoaiActionPerformed
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(((DefaultTableModel) tbChiTietSanPham.getModel()));
        sorter.setRowFilter(RowFilter.regexFilter(String.valueOf(cbTheLoai.getSelectedItem())));

        tbChiTietSanPham.setRowSorter(sorter);
    }//GEN-LAST:event_cbTheLoaiActionPerformed

    private void cbKichCoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbKichCoActionPerformed
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(((DefaultTableModel) tbChiTietSanPham.getModel()));
        sorter.setRowFilter(RowFilter.regexFilter(String.valueOf(cbKichCo.getSelectedItem())));

        tbChiTietSanPham.setRowSorter(sorter);
    }//GEN-LAST:event_cbKichCoActionPerformed

    private void cbMauSacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbMauSacActionPerformed
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(((DefaultTableModel) tbChiTietSanPham.getModel()));
        sorter.setRowFilter(RowFilter.regexFilter(String.valueOf(cbMauSac.getSelectedItem())));

        tbChiTietSanPham.setRowSorter(sorter);
    }//GEN-LAST:event_cbMauSacActionPerformed

    private void cbChatLieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbChatLieuActionPerformed
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(((DefaultTableModel) tbChiTietSanPham.getModel()));
        sorter.setRowFilter(RowFilter.regexFilter(String.valueOf(cbChatLieu.getSelectedItem())));

        tbChiTietSanPham.setRowSorter(sorter);
    }//GEN-LAST:event_cbChatLieuActionPerformed

    private void btnAddKhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddKhActionPerformed
        FrameKhachHang kh = new FrameKhachHang(txtSDT.getText());
        kh.setVisible(true);
        kh.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        addComboKH();
    }//GEN-LAST:event_btnAddKhActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        int row = tbGioHang.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "B???n ch???n d??ng x??a");
            return;
        }
        String ma = tbGioHang.getValueAt(row, 0).toString();
        int slton = chiTietQuanAoService.getSLTon(ma);
        int soLuongMua = chiTietHoaDonService.getSlMua(idCTHD);
        idCTQA = listQuanAo.get(row).getId();
        listChiTietHD = chiTietHoaDonService.getListHD(idHD);
        idCTHD = listChiTietHD.get(row).getId();

        int choice = JOptionPane.showConfirmDialog(this, "B???n c?? mu???n x??a kh??ng");
        if (choice != JOptionPane.YES_OPTION) {
            return;
        } else {
            //delete cthd
            chiTietHoaDonService.delete(this.idCTHD);
            listChiTietHD = chiTietHoaDonService.getListHD(this.idHD);
            showHDCT(listChiTietHD);

            //update ctqa
            listQuanAo = chiTietQuanAoService.getAllCTQA();

            chiTietQuanAoService.updateSoLuong(ma, slton + soLuongMua);
            listQuanAo = chiTietQuanAoService.getAllCTQA();
            loadTableSP(listQuanAo);
        }

    }//GEN-LAST:event_btnXoaActionPerformed

    private void txtSDTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSDTActionPerformed
        String hoTen = khachHangService.findName(txtSDT.getText().trim());
        if (hoTen != null) {
            cbKhachHang.setSelectedItem(hoTen);
        } else {
            JOptionPane.showMessageDialog(this, "Kh??ng c?? kh??ch h??ng");
            FrameKhachHang kh = new FrameKhachHang(txtSDT.getText());

            return;
        }
    }//GEN-LAST:event_txtSDTActionPerformed

    private void txtSDTKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSDTKeyReleased

    }//GEN-LAST:event_txtSDTKeyReleased

    private void btnLoadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoadActionPerformed
        Collections.sort(listKhachHang, new Comparator<KhachHang>() {
            @Override
            public int compare(KhachHang kh1, KhachHang kh2) {
                return kh1.getMa().compareTo(kh2.getMa());
            }
        });
        addComboKH();
    }//GEN-LAST:event_btnLoadActionPerformed

    private void tbGioHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbGioHangMouseClicked
//        listChiTietHD = chiTietHoaDonService.getListHD(this.idHD);
//        loadTableChiTietHD(listChiTietHD);
        int row = tbGioHang.getSelectedRow();
        idCTQA = listChiTietHD.get(row).getIdCTQA();

        idCTHD = listChiTietHD.get(row).getId();
    }//GEN-LAST:event_tbGioHangMouseClicked

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
//        bill_print();
        int choice = JOptionPane.showConfirmDialog(this, "B???n c?? mu???n in h??a ????n kh??ng");
        if (choice == JOptionPane.YES_OPTION) {
            try {
                bill.print();
                JOptionPane.showMessageDialog(this, "Th??nh c??ng");
            } catch (PrinterException ex) {
                java.util.logging.Logger.getLogger(FrameBanHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnPrintActionPerformed

    private void tbChiTietSanPhamMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbChiTietSanPhamMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tbChiTietSanPhamMouseEntered

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
            java.util.logging.Logger.getLogger(FrameBanHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameBanHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameBanHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameBanHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrameBanHang().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddKh;
    private javax.swing.JButton btnLoad;
    private javax.swing.JButton btnPrint;
    private javax.swing.JButton btnTaoHoaDon;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<String> cbChatLieu;
    private javax.swing.JComboBox<String> cbKhachHang;
    private javax.swing.JComboBox<String> cbKichCo;
    private javax.swing.JComboBox<String> cbMauSac;
    private javax.swing.JComboBox<String> cbTenQuanAo;
    private javax.swing.JComboBox<String> cbTheLoai;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblMaHD;
    private javax.swing.JLabel lblNV;
    private javax.swing.JPanel pnQR;
    private javax.swing.JTable tbChiTietSanPham;
    private javax.swing.JTable tbGioHang;
    private javax.swing.JTable tbHoaDon;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTienKhachDua;
    private javax.swing.JTextField txtTienThua;
    private javax.swing.JTextField txtTongTien;
    // End of variables declaration//GEN-END:variables

    @Override
    public void run() {
        do {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                ex.printStackTrace();;
            }
            Result result = null;
            BufferedImage image = null;

            if (webcam.isOpen()) {
                if ((image = webcam.getImage()) == null) {
                    continue;
                }
            }

            LuminanceSource source = new BufferedImageLuminanceSource(image);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

            try {
                result = new MultiFormatReader().decode(bitmap);
            } catch (NotFoundException ex) {
                //no result
            }

            if (result != null) {
//                txtQR.setText(result.getText());
                String ma = result.getText().trim();
                for (ChiTietQuanAoRespone ctqa : listQuanAo) {
                    if (ma.equalsIgnoreCase(ctqa.getMaSP())) {
                        int slTon = ctqa.getSoLuong();
                        String id = ctqa.getMaSP();
                        String soLuongMuaStr = JOptionPane.showInputDialog(this, "b???n mu???n s??? l?????ng bao nhi??u");
                        try {
                            int slMua = Integer.parseInt(soLuongMuaStr);
                            if (slMua > slTon || slMua < 1) {
                                JOptionPane.showMessageDialog(this, "Kh??ng h???p l???");
                                return;
                            }
                            int slSauMua = slTon - slMua;
                            System.out.println(slSauMua);
//                            insert cthd
                            ChiTietHoaDon cthd = new ChiTietHoaDon();
                            cthd.setIdHD(idHD);
                            cthd.setIdChiTietQA(ctqa.getId());
                            cthd.setSoLuong(slMua);
                            cthd.setDonGia(ctqa.getGiaTien());
                            chiTietHoaDonService.insert(cthd);
                            //update soluong

                            chiTietQuanAoService.updateSoLuong(id, slSauMua);
                            listQuanAo = chiTietQuanAoService.getAllCTQA();
                            loadTableSP(listQuanAo);
                            //loadcthd
                            ChiTietHoaDonRespone chiTietHoaDon = new ChiTietHoaDonRespone();
                            chiTietHoaDon.setIdHD(this.idHD);
                            chiTietHoaDon.setMaQuanAo(ctqa.getMaSP());
                            chiTietHoaDon.setTenQuanAO(ctqa.getTenSP());
                            chiTietHoaDon.setSoLuong(slMua);
                            chiTietHoaDon.setDonGia(ctqa.getGiaTien());
                            chiTietHoaDon.thanhTien(slMua, ctqa.getGiaTien());

                            listChiTietHD.add(chiTietHoaDon);
                            loadTableChiTietHD(listChiTietHD);

                            getValuesTongTien();
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(this, "Kh??ng h???p l???");
//                            return;
                        }
                    }
                }
            }
        } while (true);
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r, "My thread");
        t.setDaemon(true);
        return t;
    }
}
