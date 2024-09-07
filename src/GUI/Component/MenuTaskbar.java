/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Component;

import DAO.NhanVienDAO;
import DTO.NhanVienDTO;
import GUI.Login;
import GUI.Main;
import GUI.Panel.HoaDon;
import GUI.Panel.KhachHang;
import GUI.Panel.NhaCungCap;
import GUI.Panel.NhanVien;
import GUI.Panel.PhieuNhap;
import GUI.Panel.SanPham;
import GUI.Panel.ThongKe.ThongKe;
import GUI.Panel.TrangChu;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Admin
 */
public class MenuTaskbar extends JPanel{
    
    TrangChu trangChu;
    SanPham sanPham;
    PhieuNhap phieuNhap;
    HoaDon hoaDon;
    KhachHang khachHang;
    NhaCungCap nhacungcap;
    NhanVien nhanVien;
    ThongKe thongKe;
    String[][] getSt = {
        {"Trang chủ", "home.svg", "trangchu"},
        {"Sản phẩm", "product.svg", "sanpham"},  
        {"Phiếu nhập", "import.svg", "nhaphang"},
        {"Hóa đơn", "export.svg", "hoadon"},
        {"Khách hàng", "customer.svg", "khachhang"},
        {"Nhà cung cấp", "supplier.svg", "nhacungcap"},
        {"Nhân viên", "staff.svg", "nhanvien"},       
        {"Thống kê", "statistical.svg", "thongke"},
        {"Đăng xuất", "logout.svg", "dangxuat"},
    };

    Main main;
    NhanVienDTO user;
    public ItemTaskBar[] listitem;

    JLabel lblTenNhomQuyen, lblUsername;
    JScrollPane scrollPane;

    //tasbarMenu chia thành 3 phần chính là pnlCenter, pnlTop, pnlBottom
    JPanel pnlCenter, pnlTop, pnlBottom, bar1, bar2, bar3, bar4;

    Color FontColor = new Color(96, 125, 139);
    Color DefaultColor = new Color(255, 255, 255);
    Color HowerFontColor = new Color(1, 87, 155);
    Color HowerBackgroundColor = new Color(187, 222, 251);
    
    public NhanVienDTO nhanVienDTO;
    JFrame owner = (JFrame) SwingUtilities.getWindowAncestor(this);
    
     public MenuTaskbar(Main main) {
        this.main = main;
        initComponent();
    }

   
    public MenuTaskbar(Main main, NhanVienDTO tk) {
        this.main = main;
        this.user = tk;
        this.nhanVienDTO = NhanVienDAO.getInstance().selectById(tk.getMaNV());
        initComponent();
    }

    private void initComponent() {
        listitem = new ItemTaskBar[getSt.length];
        this.setOpaque(true);
        this.setBackground(DefaultColor);
        this.setLayout(new BorderLayout(0, 0));

        // bar1, bar là các đường kẻ mỏng giữa taskbarMenu và MainContent
        pnlTop = new JPanel();
        pnlTop.setPreferredSize(new Dimension(250, 80));
        pnlTop.setBackground(DefaultColor);
        pnlTop.setLayout(new BorderLayout(0, 0));
        this.add(pnlTop, BorderLayout.NORTH);

        JPanel info = new JPanel();
        info.setOpaque(false);
        info.setLayout(new BorderLayout(0, 0));
        pnlTop.add(info, BorderLayout.CENTER);

        userInformation(info);

        bar1 = new JPanel();
        bar1.setBackground(new Color(204, 214, 219));
        bar1.setPreferredSize(new Dimension(1, 0));
        pnlTop.add(bar1, BorderLayout.EAST);

        bar2 = new JPanel();
        bar2.setBackground(new Color(204, 214, 219));
        bar2.setPreferredSize(new Dimension(0, 1));
        pnlTop.add(bar2, BorderLayout.SOUTH);

        pnlCenter = new JPanel();
        pnlCenter.setPreferredSize(new Dimension(230, 600));
        pnlCenter.setBackground(DefaultColor);
        pnlCenter.setLayout(new FlowLayout(0, 0, 5));

        bar3 = new JPanel();
        bar3.setBackground(new Color(204, 214, 219));
        bar3.setPreferredSize(new Dimension(1, 1));
        this.add(bar3, BorderLayout.EAST);

        scrollPane = new JScrollPane(pnlCenter, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(new EmptyBorder(5, 10, 0, 10));
        this.add(scrollPane, BorderLayout.CENTER);

        pnlBottom = new JPanel();
        pnlBottom.setPreferredSize(new Dimension(250, 50));
        pnlBottom.setBackground(DefaultColor);
        pnlBottom.setLayout(new BorderLayout(0, 0));

        bar4 = new JPanel();
        bar4.setBackground(new Color(204, 214, 219));
        bar4.setPreferredSize(new Dimension(1, 1));
        pnlBottom.add(bar4, BorderLayout.EAST);

        this.add(pnlBottom, BorderLayout.SOUTH);

        // THÊM CÁC TASKBAR
        for (int i = 0; i < getSt.length; i++) {
            if (i + 1 == getSt.length) {
                // thêm task bar đăng xuất
                listitem[i] = new ItemTaskBar(getSt[i][1], getSt[i][0]);
                pnlBottom.add(listitem[i]);
            } else {
                // thêm taskbar trang chủ, sản phẩm, nhân viên, phiếu nhập, hóa đơn, thống kê, nhà cung cấp, ...
                listitem[i] = new ItemTaskBar(getSt[i][1], getSt[i][0]);
                pnlCenter.add(listitem[i]);                
            }
        }
        // KIỂM TRA NẾU NGƯỜI ĐĂNG NHẬP LÀ USER (NHÂN VIÊN) THÌ ẨN CÁC TASKBAR KHÔNG CẦN THIẾT
        if (!checkRole(this.user)) {
            listitem[2].setVisible(false);
            listitem[5].setVisible(false);
            listitem[6].setVisible(false);
        }

        listitem[0].setBackground(HowerBackgroundColor);
        listitem[0].setForeground(HowerFontColor);
        listitem[0].isSelected = true;

        for (int i = 0; i < getSt.length; i++) {
            listitem[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent evt) {
                    pnlMenuTaskbarMousePress(evt);
                }
            });
        }

        listitem[0].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                trangChu = new TrangChu();
                main.setPanel(trangChu);
            }
        });

        listitem[1].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                sanPham = new SanPham(main);
                main.setPanel(sanPham);

            }
        });
        listitem[2].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                phieuNhap = new PhieuNhap(main, nhanVienDTO);
                main.setPanel(phieuNhap);
            }
        });
        listitem[3].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                hoaDon = new HoaDon(main, user);
                main.setPanel(hoaDon);
            }
        });
        listitem[4].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                khachHang = new KhachHang(main);
                main.setPanel(khachHang);
            }
        });
        listitem[5].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                nhacungcap = new NhaCungCap(main);
                main.setPanel(nhacungcap);
            }
        });

        listitem[6].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                nhanVien = new NhanVien(main);
                main.setPanel(nhanVien);
            }
        });
        listitem[7].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                thongKe = new ThongKe();
                main.setPanel(thongKe);
            }
        });
        listitem[8].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {

                int input = JOptionPane.showConfirmDialog(null,
                        "Bạn có chắc chắn muốn đăng xuất?", "Đăng xuất",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if (input == 0) {
                    Login login = new Login();
                    main.dispose();
                    login.setVisible(true);
                }
            }
        });
    }

    public boolean checkRole(NhanVienDTO user) {        
        if(user.getQuyenTruyCap().equals("Admin")) {
             return true;
        }
        return false;
    }

    public void pnlMenuTaskbarMousePress(MouseEvent evt) {

        for (int i = 0; i < getSt.length; i++) {
            if (evt.getSource() == listitem[i]) {
                listitem[i].isSelected = true;
                listitem[i].setBackground(HowerBackgroundColor);
                listitem[i].setForeground(HowerFontColor);
            } else {
                listitem[i].isSelected = false;
                listitem[i].setBackground(DefaultColor);
                listitem[i].setForeground(FontColor);
            }
        }
    }
    public void resetChange(){
        this.nhanVienDTO = new NhanVienDAO().selectById(nhanVienDTO.getMaNV());
    }
    public void userInformation(JPanel info) {
        JPanel pnlIcon = new JPanel(new FlowLayout());
        pnlIcon.setPreferredSize(new Dimension(60, 0));
        pnlIcon.setOpaque(false);
        info.add(pnlIcon, BorderLayout.WEST);
        JLabel lblIcon = new JLabel();
        lblIcon.setPreferredSize(new Dimension(50, 70));
        if (nhanVienDTO.getGioiTinh().equals("Nam")) {
            lblIcon.setIcon(new FlatSVGIcon("./icon/boy.svg"));
        } else {
            lblIcon.setIcon(new FlatSVGIcon("./icon/girl.svg"));
        }
        pnlIcon.add(lblIcon);

        JPanel pnlInfo = new JPanel();
        pnlInfo.setOpaque(false);
        pnlInfo.setLayout(new BoxLayout(pnlInfo, BoxLayout.Y_AXIS)); // Sử dụng BoxLayout với hướng dọc
        pnlInfo.setBorder(new EmptyBorder(15, 0, 0, 0));
        info.add(pnlInfo, BorderLayout.CENTER);

        lblUsername = new JLabel(nhanVienDTO.getTenNV());
        lblUsername.putClientProperty("FlatLaf.style", "font: 150% $semibold.font");
        pnlInfo.add(lblUsername);
        // Thêm khoảng cách giữa lblUsername và lblTenNhomQuyen
        pnlInfo.add(Box.createVerticalStrut(12));
        lblTenNhomQuyen = new JLabel(this.user.getQuyenTruyCap());
        lblTenNhomQuyen.putClientProperty("FlatLaf.style", "font: 120% $light.font");
        lblTenNhomQuyen.setForeground(Color.GRAY);
        pnlInfo.add(lblTenNhomQuyen);
       
    }
}
