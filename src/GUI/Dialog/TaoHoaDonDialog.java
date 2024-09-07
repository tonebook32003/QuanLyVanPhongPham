/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Dialog;

import BUS.HoaDonBUS;
import BUS.KhachHangBUS;
import BUS.NhaCungCapBUS;
import BUS.SanPhamBUS;
import DTO.ChiTietHoaDonDTO;
import DTO.NhanVienDTO;
import DTO.HoaDonDTO;
import DTO.SanPhamDTO;
import GUI.Component.ButtonCustom;
import GUI.Component.InputForm;
import GUI.Component.NumericDocumentFilter;
import GUI.Component.PanelBorderRadius;
import GUI.Component.SelectForm;
import GUI.Main;
import GUI.Panel.HoaDon;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import helper.Formater;
import helper.Validation;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.text.PlainDocument;

/**
 *
 * @author tonsa
 */
public class TaoHoaDonDialog extends JDialog implements ItemListener, ActionListener {

    PanelBorderRadius right, left;
    JPanel pnlBorder1, pnlBorder2, pnlBorder3, pnlBorder4, contentCenter, left_top, main, content_right_bottom, content_btn;
    JTable tableHoaDon, tableSanPham;
    JScrollPane scrollTableHoaDon, scrollTableSanPham;
    DefaultTableModel tblModel, tblModelSP;
    ButtonCustom btnAddSp, btnEditSP, btnDelete, btnImport, btnNhapHang;
    InputForm txtNhanVien, txtMaSp, txtTenSp, txtDongia, txtSoLuong;

    SelectForm cbxKhachHang;
    JTextField txtTimKiem;
    JLabel lbltongtien;
    Main m;
    Color BackgroundColor = new Color(240, 247, 250);

    SanPhamBUS spBUS = new SanPhamBUS();
    KhachHangBUS khBus = new KhachHangBUS();
    HoaDonBUS hoadonBus = new HoaDonBUS();
    NhanVienDTO nvDto;

    ArrayList<DTO.SanPhamDTO> listSP = spBUS.getAll();
    ArrayList<ChiTietHoaDonDTO> listCTHD;
    int maHD;
    int rowPhieuSelect = -1;
    HoaDon jpHD;

    public TaoHoaDonDialog(HoaDon jpHD, JFrame owner, String title, boolean modal, NhanVienDTO nv) { // , Main m
        super(owner, title, modal);
        this.nvDto = nv;
        this.jpHD = jpHD;
        this.maHD = hoadonBus.hoaDonDAO.getAutoIncrement();
        listCTHD = new ArrayList<>();

        initComponent();
        //loadDataTalbeSanPham(listSP);
    }

    public void initPadding() {
        pnlBorder1 = new JPanel();
        pnlBorder1.setPreferredSize(new Dimension(0, 5));
        pnlBorder1.setBackground(BackgroundColor);
        this.add(pnlBorder1, BorderLayout.NORTH);

        pnlBorder2 = new JPanel();
        pnlBorder2.setPreferredSize(new Dimension(0, 5));
        pnlBorder2.setBackground(BackgroundColor);
        this.add(pnlBorder2, BorderLayout.SOUTH);

        pnlBorder3 = new JPanel();
        pnlBorder3.setPreferredSize(new Dimension(5, 0));
        pnlBorder3.setBackground(BackgroundColor);
        this.add(pnlBorder3, BorderLayout.EAST);

        pnlBorder4 = new JPanel();
        pnlBorder4.setPreferredSize(new Dimension(5, 0));
        pnlBorder4.setBackground(BackgroundColor);
        this.add(pnlBorder4, BorderLayout.WEST);
    }

    private void initComponent() {
        this.setSize(new Dimension(1200, 700));
        this.setBackground(BackgroundColor);
        this.setLayout(new BorderLayout(0, 0));
        this.setLocationRelativeTo(null);
        // Phiếu nhập
        tableHoaDon = new JTable();
        scrollTableHoaDon = new JScrollPane();
        tblModel = new DefaultTableModel();
        String[] header = new String[]{"STT", "Mã SP", "Tên sản phẩm", "Đơn giá", "Số lượng"};
        tblModel.setColumnIdentifiers(header);
        tableHoaDon.setModel(tblModel);
        scrollTableHoaDon.setViewportView(tableHoaDon);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        TableColumnModel columnModel = tableHoaDon.getColumnModel();
        for (int i = 0; i < 5; i++) {
            if (i != 2) {
                columnModel.getColumn(i).setCellRenderer(centerRenderer);
            }
        }
        tableHoaDon.getColumnModel().getColumn(2).setPreferredWidth(300);
        tableHoaDon.setDefaultEditor(Object.class, null);
        tableHoaDon.setFocusable(false);
        scrollTableHoaDon.setViewportView(tableHoaDon);

        tableHoaDon.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int index = tableHoaDon.getSelectedRow();
                if (index != -1) {
                    setFormChiTietPhieu(listCTHD.get(index));
                    rowPhieuSelect = index;
                    actionbtn("update");
                }
            }
        });

        //load comboBox KH len 
        JPanel right_top;
        right_top = new JPanel(new GridLayout(4, 1, 0, 0));
        right_top.setPreferredSize(new Dimension(300, 360));
        right_top.setOpaque(false);
        cbxKhachHang = new SelectForm("Khách hàng", khBus.getArrTenKhachHang());
        cbxKhachHang.setSelectedIndex(-1);
        cbxKhachHang.getCbb().addItemListener(this);

        // Table sản phẩm
        tableSanPham = new JTable();
        scrollTableSanPham = new JScrollPane();
        tblModelSP = new DefaultTableModel();
        String[] headerSP = new String[]{"Mã SP", "Tên sản phẩm", "Đơn giá", "Số lượng tồn"};
        tblModelSP.setColumnIdentifiers(headerSP);
        tableSanPham.setModel(tblModelSP);
        loadDataTalbeSanPham(listSP);
        scrollTableSanPham.setViewportView(tableSanPham);
        tableSanPham.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tableSanPham.getColumnModel().getColumn(1).setPreferredWidth(300);
        tableSanPham.setDefaultEditor(Object.class, null);
        tableSanPham.setFocusable(false);
        scrollTableSanPham.setViewportView(tableSanPham);
        tableSanPham.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int rowIndex = tableSanPham.getSelectedRow();
                int columnIndex = 0;
                int maSP = (int) tableSanPham.getModel().getValueAt(rowIndex, columnIndex);
                if (maSP != -1) {
                    resetForm();
                    setInfoSanPham(listSP, maSP);
                    ChiTietHoaDonDTO ctp = checkTonTai();
                    if (ctp == null) {
                        actionbtn("add");
                    } else {
                        actionbtn("update");
                        setFormChiTietPhieu(ctp);
                    }
                }
            }
        });

        contentCenter = new JPanel();
        contentCenter.setPreferredSize(new Dimension(1100, 600));
        contentCenter.setBackground(BackgroundColor);
        contentCenter.setLayout(new BorderLayout(5, 5));
        this.add(contentCenter, BorderLayout.CENTER);

        left = new PanelBorderRadius();
        left.setLayout(new BorderLayout(0, 5));
        left.setBackground(Color.white);

        left_top = new JPanel(); // Chứa tất cả phần ở phía trái trên cùng
        left_top.setLayout(new BorderLayout());
        left_top.setBorder(new EmptyBorder(5, 5, 10, 10));
        left_top.setOpaque(false);

        JPanel content_top, content_left, content_right, content_right_top;
        content_top = new JPanel(new GridLayout(1, 2, 5, 5));
        content_top.setOpaque(false);
        content_left = new JPanel(new BorderLayout(5, 5));
        content_left.setOpaque(false);
        content_left.setPreferredSize(new Dimension(0, 300));

        txtTimKiem = new JTextField();
        txtTimKiem.putClientProperty("JTextField.placeholderText", "Tên sản phẩm, mã sản phẩm...");
        txtTimKiem.putClientProperty("JTextField.showClearButton", true);
        txtTimKiem.putClientProperty("JTextField.leadingIcon", new FlatSVGIcon("./icon/search.svg"));

        txtTimKiem.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ArrayList<SanPhamDTO> rs = spBUS.search(txtTimKiem.getText());
                loadDataTalbeSanPham(rs);
            }
        });

        txtTimKiem.setPreferredSize(new Dimension(100, 40));
        content_left.add(txtTimKiem, BorderLayout.NORTH);
        content_left.add(scrollTableSanPham, BorderLayout.CENTER);

        content_right = new JPanel(new BorderLayout(5, 5));
        content_right.setOpaque(false);

        content_right_top = new JPanel(new BorderLayout());
        txtMaSp = new InputForm("Mã sản phẩm");
        txtMaSp.setEditable(false);
        txtTenSp = new InputForm("Tên sản phẩm");
        txtTenSp.setEditable(false);
        JPanel content_right_top_cbx = new JPanel(new BorderLayout());
        txtDongia = new InputForm("Giá nhập");
        txtDongia.setEditable(false);
        txtSoLuong = new InputForm("Số lượng");
        PlainDocument soluong = (PlainDocument) txtSoLuong.getTxtForm().getDocument();
        soluong.setDocumentFilter((new NumericDocumentFilter()));

        content_right_top_cbx.add(txtDongia, BorderLayout.CENTER);
        content_right_top_cbx.add(txtSoLuong, BorderLayout.WEST);
        content_right_top.add(txtMaSp, BorderLayout.WEST);
        content_right_top.add(txtTenSp, BorderLayout.CENTER);
        content_right_top.add(content_right_top_cbx, BorderLayout.SOUTH);

        content_right_bottom = new JPanel(new CardLayout());

        JPanel card_content_one = new JPanel(new BorderLayout());
        card_content_one.setBackground(Color.white);
        card_content_one.setPreferredSize(new Dimension(100, 90));
        JPanel card_content_one_model = new JPanel(new BorderLayout());
        card_content_one_model.setPreferredSize(new Dimension(100, 90));

        card_content_one.add(card_content_one_model, BorderLayout.NORTH);

        JPanel card_content_two_model = new JPanel(new BorderLayout());
        card_content_two_model.setBorder(new EmptyBorder(10, 10, 10, 10));
        card_content_two_model.setSize(new Dimension(0, 100));
        card_content_two_model.setBackground(Color.white);

        content_right_bottom.add(card_content_one);
        content_right_bottom.add(card_content_two_model);

        content_right.add(content_right_top, BorderLayout.NORTH);

        content_top.add(content_left);
        content_top.add(content_right);

        content_btn = new JPanel();
        content_btn.setPreferredSize(new Dimension(0, 47));
        content_btn.setLayout(new GridLayout(1, 4, 5, 5));
        content_btn.setBorder(new EmptyBorder(8, 5, 0, 10));
        content_btn.setOpaque(false);
        btnAddSp = new ButtonCustom("Thêm sản phẩm", "success", 14);
        btnEditSP = new ButtonCustom("Sửa sản phẩm", "warning", 14);
        btnDelete = new ButtonCustom("Xoá sản phẩm", "danger", 14);
        btnImport = new ButtonCustom("Nhập Excel", "excel", 14);
        btnAddSp.addActionListener(this);
        btnEditSP.addActionListener(this);
        btnDelete.addActionListener(this);
        btnImport.addActionListener(this);
        btnEditSP.setEnabled(false);
        btnDelete.setEnabled(false);
        content_btn.add(btnAddSp);
        content_btn.add(btnImport);
        content_btn.add(btnEditSP);
        content_btn.add(btnDelete);

        left_top.add(content_top, BorderLayout.CENTER);

        main = new JPanel();
        main.setOpaque(false);
        main.setPreferredSize(new Dimension(0, 250));
        main.setBorder(new EmptyBorder(0, 5, 10, 10));
        BoxLayout boxly = new BoxLayout(main, BoxLayout.Y_AXIS);
        main.setLayout(boxly);
        main.add(scrollTableHoaDon);
        left.add(left_top, BorderLayout.CENTER);
        left.add(main, BorderLayout.SOUTH);

        right = new PanelBorderRadius();
        right.setPreferredSize(new Dimension(320, 0));
        right.setBorder(new EmptyBorder(5, 5, 5, 5));
        right.setLayout(new BorderLayout());

        JPanel right_center, right_bottom, pn_tongtien;

        txtNhanVien = new InputForm("Nhân viên nhập");
        txtNhanVien.setText(nvDto.getTenNV());
        txtNhanVien.setEditable(false);
        right_top.add(txtNhanVien);
        right_top.add(cbxKhachHang);

        right_center = new JPanel();
        right_center.setPreferredSize(new Dimension(100, 100));
        right_center.setOpaque(false);

        right_bottom = new JPanel(new GridLayout(2, 1));
        right_bottom.setPreferredSize(new Dimension(300, 100));
        right_bottom.setBorder(new EmptyBorder(10, 10, 10, 10));
        right_bottom.setOpaque(false);

        pn_tongtien = new JPanel(new FlowLayout(1, 20, 0));
        pn_tongtien.setOpaque(false);
        JLabel lbltien = new JLabel("TỔNG TIỀN: ");
        lbltien.setFont(new Font(FlatRobotoFont.FAMILY, 1, 18));
        lbltongtien = new JLabel("0đ");
        lbltongtien.setFont(new Font(FlatRobotoFont.FAMILY, 1, 18));
        lbltien.setForeground(new Color(255, 51, 51));
        pn_tongtien.add(lbltien);
        pn_tongtien.add(lbltongtien);
        right_bottom.add(pn_tongtien);

        btnNhapHang = new ButtonCustom("Thanh toán", "excel", 14);
        btnNhapHang.addActionListener(this);
        right_bottom.add(btnNhapHang);
        left_top.add(content_btn, BorderLayout.SOUTH);

        right.add(right_top, BorderLayout.NORTH);
        right.add(right_center, BorderLayout.CENTER);
        right.add(right_bottom, BorderLayout.SOUTH);

        contentCenter.add(left, BorderLayout.CENTER);
        contentCenter.add(right, BorderLayout.EAST);

        initPadding();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    public void loadDataTalbeSanPham(ArrayList<DTO.SanPhamDTO> result) {
        tblModelSP.setRowCount(0);
        for (DTO.SanPhamDTO sp : result) {
            tblModelSP.addRow(new Object[]{sp.getMaSP(), sp.getTenSP(), sp.getGiaNhap(), sp.getSoLuong()});
        }
    }

    public void loadDataTableChiTietPhieu(ArrayList<ChiTietHoaDonDTO> ctPhieu) {
        tblModel.setRowCount(0);
        SanPhamBUS spBus = new SanPhamBUS();
        int size = ctPhieu.size();
        for (int i = 0; i < size; i++) {
            ChiTietHoaDonDTO ctp = ctPhieu.get(i);
            SanPhamDTO sp = spBus.getByMaSP(ctp.getMaSP());
            String tenSP = sp.getTenSP();
            tblModel.addRow(new Object[]{i + 1, ctp.getMaSP(), tenSP, Formater.FormatVND(ctp.getDonGia()), ctp.getSoLuong()});
        }
        lbltongtien.setText(Formater.FormatVND(hoadonBus.getTongTien(ctPhieu)));
    }

    public void setInfoSanPham(ArrayList<SanPhamDTO> lst, int maSP) {
        for (SanPhamDTO sp : lst) {
            if (sp.getMaSP() == maSP) {
                this.txtMaSp.setText(Integer.toString(sp.getMaSP()));
                this.txtTenSp.setText(sp.getTenSP());
                this.txtDongia.setText(String.valueOf(sp.getGiaNhap()));
            }
        }

    }

    // Tra ve 1 doi tuong la CTPN
    int mancc = 0;
    int manccsau = 0;

    public ChiTietHoaDonDTO getInfoChiTietPhieu() {
        int masp = Integer.parseInt(txtMaSp.getText());
        double gianhap = Double.parseDouble(txtDongia.getText());
        int soluong = Integer.parseInt(txtSoLuong.getText());
        // combobox nhacungcap !!!!!
        if (mancc == 0) {
            mancc = khBus.getByIndex(cbxKhachHang.getSelectedIndex()).getMaKH();
        } else {
            manccsau = khBus.getByIndex(cbxKhachHang.getSelectedIndex()).getMaKH();
        }

        ChiTietHoaDonDTO ctphieu = new ChiTietHoaDonDTO(maHD, masp, soluong, gianhap);
        return ctphieu;
    }

    public boolean validateNhap() {
        //int phuongthuc = cbxPtNhap.getSelectedIndex();
        if (Validation.isEmpty(txtMaSp.getText())) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm", "Chọn sản phẩm", JOptionPane.WARNING_MESSAGE);
            return false;
        } else if (!Validation.isDoubleNumber(txtDongia.getText())) {
            JOptionPane.showMessageDialog(this, "Giá nhập không hợp lệ !", "Cảnh báo !", JOptionPane.WARNING_MESSAGE);
            return false;
        } else if (!Validation.isNumber(txtSoLuong.getText())) {
            JOptionPane.showMessageDialog(this, "Số lượng không hợp lệ !", "Cảnh báo !", JOptionPane.WARNING_MESSAGE);
            return false;
        } else if (Validation.isEmpty(cbxKhachHang.getValue())) {
            JOptionPane.showMessageDialog(this, "Bạn phải chọn 1 khách hàng để thêm !", "Cảnh báo !", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        return true;
    }

    public void addCTHoaDon() {
        ChiTietHoaDonDTO ctphieu = getInfoChiTietPhieu();
        ChiTietHoaDonDTO p = hoadonBus.findCT(listCTHD, ctphieu.getMaSP());
        if (p == null) {
            //TH: thang moi add == th truoc do
            if (mancc == manccsau) {
                listCTHD.add(ctphieu);
                loadDataTableChiTietPhieu(listCTHD);
                resetForm();
            } else {
                //TH: no la sp dau tien duoc add vao
                if (manccsau == 0) {
                    mancc = khBus.getByIndex(cbxKhachHang.getSelectedIndex()).getMaKH();
                    listCTHD.clear();
                    listCTHD.add(ctphieu);
                    loadDataTableChiTietPhieu(listCTHD);
                    resetForm();
                } else {
                    //TH: thang add sau khac MaKH thang add truoc
                    int input = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn thay đổi khách hàng  !", "Xác nhận đổi khách hàng", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
                    if (input == JOptionPane.OK_OPTION) {
                        mancc = khBus.getByIndex(cbxKhachHang.getSelectedIndex()).getMaKH();
                        listCTHD.clear();
                        listCTHD.add(ctphieu);
                        loadDataTableChiTietPhieu(listCTHD);
                        resetForm();
                        JOptionPane.showMessageDialog(this, "Thay đổi khách hàng thành công !");
                    } else {
                        JOptionPane.showMessageDialog(this, "Thay đổi khách hàng không thành công !", "Cảnh báo !", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        } else {
            int input = JOptionPane.showConfirmDialog(this, "Sản phẩm đã tồn tại trong hóa đơn !\nBạn có muốn chỉnh sửa không ?", "Sản phẩm đã tồn tại !", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
            if (input == 0) {
//                setFormChiTietPhieu(ctphieu.getMaphienbansp());
            }
        }
    }

    // kiem tra san pham da duoc them vao phieu nhap chua
    public ChiTietHoaDonDTO checkTonTai() {
        int masp = Integer.parseInt(txtMaSp.getText());
        ChiTietHoaDonDTO p = hoadonBus.findCT(listCTHD, masp);
        return p;
    }

    public void actionbtn(String type) {
        boolean val_1 = type.equals("add");
        boolean val_2 = type.equals("update");
        btnAddSp.setEnabled(val_1);
        btnImport.setEnabled(val_1);
        btnEditSP.setEnabled(val_2);
        btnDelete.setEnabled(val_2);
        content_btn.revalidate();
        content_btn.repaint();
    }

    public void setFormChiTietPhieu(ChiTietHoaDonDTO phieu) {
        this.txtMaSp.setText(Integer.toString(phieu.getMaSP()));
        this.txtTenSp.setText(spBUS.getByMaSP(phieu.getMaSP()).getTenSP());
        this.txtDongia.setText(Double.toString(phieu.getDonGia()));
        this.txtSoLuong.setText(Integer.toString(phieu.getSoLuong()));
    }

    public void resetForm() {
        this.txtMaSp.setText("");
        this.txtTenSp.setText("");
        this.txtDongia.setText("");
        this.txtSoLuong.setText("");
        // reset comboxbox nhacungcap !!!!!!
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == btnAddSp && validateNhap()) {
            addCTHoaDon();

        } else if (source == btnDelete) {
            int index = tableHoaDon.getSelectedRow();
            listCTHD.remove(index);
            actionbtn("add");
            loadDataTableChiTietPhieu(listCTHD);
            resetForm();
        } else if (source == btnEditSP) {
            int index = tableHoaDon.getSelectedRow();
            listCTHD.remove(index);

            addCTHoaDon();
            loadDataTableChiTietPhieu(listCTHD);
        } else if (source == btnNhapHang) {
            eventBtnThanhToan();
        }

    }

    public void eventBtnThanhToan() {
        if (listCTHD.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Chưa có sản phẩm nào trong hóa đơn !", "Cảnh báo !", JOptionPane.ERROR_MESSAGE);
        } else {
            int input = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn tạo hóa đơn nhập !", "Xác nhận tạo hóa đơn", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
            if (input == 0) {
                int makh = khBus.getByIndex(cbxKhachHang.getSelectedIndex()).getMaKH();
                LocalDate currentDate = LocalDate.now();
                Date date = java.sql.Date.valueOf(currentDate);
                // tao hoa don moi
                HoaDonDTO hd = new HoaDonDTO(hoadonBus.getTongTien(listCTHD), date, nvDto.getMaNV(), makh, 1);

                //
                boolean result = hoadonBus.add(hd, listCTHD);
                if (result) {
                    JOptionPane.showMessageDialog(this, "Nhập hàng thành công !");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Nhập hàng không thành công !", "Cảnh báo !", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
//        if (e.getStateChange() == ItemEvent.SELECTED) {
//            try {
//                loadDataTalbeSanPham(listSP);
//            } catch (Exception ex) {
//                JOptionPane.showMessageDialog(null, "Build du lieu that bai");
//            }
//        }

    }

}
