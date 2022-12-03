/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 *
 * @author BOSS
 */
public class ChuyenManHinhcontroller {

    private JFrame roott;
    private JPanel root;
    private String kindSletecd = "";
    private List<DanhMucBean> list = null;

    public ChuyenManHinhcontroller(JPanel root) {
        this.root = root;
    }

    public void setView(JPanel jpnItem, JLabel jlbItem) {
        kindSletecd = "TrangChu";
//        jpnItem.setBackground(new Color(96, 100, 191));
//        jlbItem.setBackground(new Color(96, 100, 191));
        root.removeAll();
        root.setLayout(new BorderLayout());
        root.add(new TrangChuPanel());
        root.validate();
        root.repaint();

    }

    public void setEvent(List<DanhMucBean> listItem) {
        this.list = listItem;
        for (DanhMucBean dmb : listItem) {
            dmb.getJlb().addMouseListener(new lablelEvent(dmb.getKind(), dmb.getJpn(), dmb.getJlb()));
        }
    }

    class lablelEvent implements MouseListener {

        private JFrame nodde;
        private JPanel node;
        private String kind;
        private JPanel jpnItem;
        private JLabel jlbItem;

        public lablelEvent(String kind, JPanel jpnItem, JLabel jlbItem) {
            this.kind = kind;
            this.jpnItem = jpnItem;
            this.jlbItem = jlbItem;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            switch (kind) {
                case "TrangChu":
                    node = new TrangChuPanel();
                    break;
                case "SanPham":
                    FrameQuanLy ql = new FrameQuanLy();
                    ql.setVisible(true);
                  ql.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                    break;
                case "KhachHang":
                    node = new KhachHangPanel();
                    break;
                case "BanHang":
//                    node = new BanHangPanel();
                    FrameBanHang bh = new FrameBanHang();
                    bh.setVisible(true);
                    bh.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                    break;

                case "DoiTra":
                    node = new DoiTra();
                    break;
                case "NhanVien":
                    node = new NhanVienPanel();
                    break;
                case "ThongKe":
                    node = new ThongKePanel();
                    break;
                case "HoaDon":
//                    node = new HoaDonPanel();
                    node = new QuanLyHoaDonPanel();
                    break;

                default:
                    break;
            }

            root.removeAll();
            root.setLayout(new BorderLayout());
            root.add(node);
            root.validate();
            root.repaint();
            setChangeBackgroud(kind);

        }

        @Override
        public void mousePressed(MouseEvent e) {
            kindSletecd = kind;
//            jpnItem.setBackground(new Color(96, 100, 191));
//            jlbItem.setBackground(new Color(96, 100, 191));

        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
//            jpnItem.setBackground(new Color(96, 100, 191));
//            jlbItem.setBackground(new Color(96, 100, 191));
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if (!kindSletecd.equalsIgnoreCase(kind)) {
//                jpnItem.setBackground(new Color(76, 175, 80));
//                jlbItem.setBackground(new Color(76, 175, 80));
            }

        }

    }

    private void setChangeBackgroud(String kind) {
        for (DanhMucBean dmb : list) {
            if (dmb.getKind().equalsIgnoreCase(kind)) {
//                dmb.getJpn().setBackground(new Color(96, 100, 191));
//                dmb.getJlb().setBackground(new Color(96, 100, 191));
            } else {
//                dmb.getJpn().setBackground(new Color(76, 175, 80));
//                dmb.getJlb().setBackground(new Color(76, 175, 80));
            }
        }
    }
}
