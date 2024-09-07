package GUI.Dialog;

import BUS.ChiTietHoaDonBUS;
import BUS.ChiTietHoaDonBUS;
import BUS.HoaDonBUS;
import BUS.HoaDonBUS;
import BUS.SanPhamBUS;
import DAO.KhachHangDAO;
import DAO.NhanVienDAO;
import DTO.ChiTietHoaDonDTO;
import DTO.HoaDonDTO;
import DTO.HoaDonDTO;
import DTO.SanPhamDTO;
import GUI.Component.ButtonCustom;
import GUI.Component.HeaderTitle;
import GUI.Component.InputForm;
import helper.Formater;
import helper.WritePDF_HoaDon;
import helper.writePDF;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author tonsa
 */
public class ChiTietHoaDonDialog extends JDialog implements ActionListener{
    
    HeaderTitle titlePage;
    JPanel pnmain, pnmain_top, pnmain_bottom, pnmain_bottom_right, pnmain_bottom_left, pnmain_btn;
    InputForm txtMaHoaDon, txtNhanVien, txtKhachHang, txtThoiGian;
    DefaultTableModel tblModel;
    JTable table;
    JScrollPane scrollTable;

    HoaDonDTO hoadon;
    HoaDonBUS hoadonBus;
    SanPhamBUS spBus = new SanPhamBUS();
    ButtonCustom btnPdf, btnHuyBo;
    ChiTietHoaDonBUS ctspBus = new ChiTietHoaDonBUS();

    ArrayList<ChiTietHoaDonDTO> chitietphieu;

    HashMap<Integer, ArrayList<ChiTietHoaDonDTO>> chitietsanpham = new HashMap<>();

    public ChiTietHoaDonDialog(JFrame owner, String title, boolean modal, HoaDonDTO hoadonDTO) {
        super(owner, title, modal);
        this.hoadon = hoadonDTO;
        hoadonBus = new HoaDonBUS();
        chitietphieu = hoadonBus.getChiTietHoaDon_Type(hoadonDTO.getMaHD());
        chitietsanpham = ctspBus.getChiTietSanPhamFromMaHD(hoadonDTO.getMaHD());
        initComponent(title);
        initHoaDon();
        loadDataTableChiTietHoaDon(chitietphieu);
        this.setVisible(true);
    }


    public void initHoaDon() {
        txtMaHoaDon.setText(Integer.toString(this.hoadon.getMaHD()));
        txtKhachHang.setText(KhachHangDAO.getInstance().selectById(hoadon.getMaKH()).getTenKH());
        txtNhanVien.setText(NhanVienDAO.getInstance().selectById(hoadon.getMaNV()).getTenNV());
        txtThoiGian.setText(Formater.FormatTime(hoadon.getNgayLapHD()));
    }

    

    public void loadDataTableChiTietHoaDon(ArrayList<ChiTietHoaDonDTO> ctHoaDon) {
        tblModel.setRowCount(0);
        int size = ctHoaDon.size();
        for (int i = 0; i < size; i++) {
            //SanPhamDTO pb = phienbanBus.getByMaPhienBan(ctHoaDon.get(i).getMaphienbansp());
            ChiTietHoaDonDTO ctp =  ctHoaDon.get(i);
            SanPhamDTO sp = spBus.getByMaSP(ctp.getMaSP());
            String tenSP = sp.getTenSP();
            tblModel.addRow(new Object[]{ i + 1, ctp.getMaSP(), tenSP, Formater.FormatVND(ctp.getDonGia()), ctp.getSoLuong() });
        }
    }

    

    public void initComponent(String title) {
        this.setSize(new Dimension(1100, 500));
        this.setLayout(new BorderLayout(0, 0));
        titlePage = new HeaderTitle(title.toUpperCase());

        pnmain = new JPanel(new BorderLayout());

        pnmain_top = new JPanel(new GridLayout(1, 4));
        txtMaHoaDon = new InputForm("Mã phiếu");
        txtNhanVien = new InputForm("Nhân viên nhập");
        txtKhachHang = new InputForm("Khách hàng");
        txtThoiGian = new InputForm("Thời gian tạo");

        txtMaHoaDon.setEditable(false);
        txtNhanVien.setEditable(false);
        txtKhachHang.setEditable(false);
        txtThoiGian.setEditable(false);

        pnmain_top.add(txtMaHoaDon);
        pnmain_top.add(txtNhanVien);
        pnmain_top.add(txtKhachHang);
        pnmain_top.add(txtThoiGian);

        pnmain_bottom = new JPanel(new BorderLayout(5, 5));
        pnmain_bottom.setBorder(new EmptyBorder(5, 5, 5, 5));
        pnmain_bottom.setBackground(Color.WHITE);

        pnmain_bottom_left = new JPanel(new GridLayout(1, 1));
        table = new JTable();
        scrollTable = new JScrollPane();
        tblModel = new DefaultTableModel();
        String[] header = new String[]{"STT", "Mã SP", "Tên SP", "Đơn giá", "Số lượng"};
        tblModel.setColumnIdentifiers(header);
        table.setModel(tblModel);
        table.setFocusable(false);
        scrollTable.setViewportView(table);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, centerRenderer);
        table.getColumnModel().getColumn(2).setPreferredWidth(200);

        pnmain_bottom_left.add(scrollTable);

//        pnmain_bottom_right = new JPanel(new GridLayout(1, 1));
//        pnmain_bottom_right.setPreferredSize(new Dimension(200, 10));
       

        pnmain_bottom.add(pnmain_bottom_left, BorderLayout.CENTER);
//        pnmain_bottom.add(pnmain_bottom_right, BorderLayout.EAST);

        pnmain_btn = new JPanel(new FlowLayout());
        pnmain_btn.setBorder(new EmptyBorder(10, 0, 10, 0));
        pnmain_btn.setBackground(Color.white);
        btnPdf = new ButtonCustom("Xuất file PDF", "success", 14);
        btnHuyBo = new ButtonCustom("Huỷ bỏ", "danger", 14);
        btnPdf.addActionListener(this);
        btnHuyBo.addActionListener(this);
        pnmain_btn.add(btnPdf);
        pnmain_btn.add(btnHuyBo);

        pnmain.add(pnmain_top, BorderLayout.NORTH);
        pnmain.add(pnmain_bottom, BorderLayout.CENTER);
        pnmain.add(pnmain_btn, BorderLayout.SOUTH);

        this.add(titlePage, BorderLayout.NORTH);
        this.add(pnmain, BorderLayout.CENTER);
        this.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == btnHuyBo) {
            dispose();
        }
        if (source == btnPdf) {
            WritePDF_HoaDon w = new WritePDF_HoaDon();
            if (this.hoadon != null) {
                try {
                    w.writeHD(hoadon.getMaHD());
                    
                } catch (Exception ex) {
                    Logger.getLogger(ChiTietHoaDonDialog.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
