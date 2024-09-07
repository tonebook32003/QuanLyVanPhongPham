/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Dialog;

import BUS.ChiTietPhieuNhapBUS;
import BUS.PhieuNhapBUS;
import BUS.SanPhamBUS;
import DAO.KhachHangDAO;
import DAO.NhaCungCapDAO;
import DAO.NhanVienDAO;
import DAO.SanPhamDAO;
import DTO.ChiTietPhieuNhapDTO;
import DTO.PhieuNhapDTO;
import DTO.SanPhamDTO;
import GUI.Component.ButtonCustom;
import GUI.Component.HeaderTitle;
import GUI.Component.InputForm;
import helper.Formater;
import helper.writePDF;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author phamthithuphuong
 */
public class ChiTietPhieuNhapDialog extends JDialog implements ActionListener {

    HeaderTitle titlePage;
    JPanel pnmain, pnmain_top, pnmain_bottom, pnmain_bottom_right, pnmain_bottom_left, pnmain_btn;
    InputForm txtMaPhieu, txtNhanVien, txtNhaCungCap, txtThoiGian;
    DefaultTableModel tblModel;
    JTable table;
    JScrollPane scrollTable;

    PhieuNhapDTO phieunhap;
    PhieuNhapBUS phieunhapBus;
    SanPhamBUS spBus = new SanPhamBUS();
    ButtonCustom btnPdf, btnHuyBo, btnXacNhan;
    ChiTietPhieuNhapBUS ctspBus = new ChiTietPhieuNhapBUS();

    ArrayList<ChiTietPhieuNhapDTO> chitietphieu;

    HashMap<Integer, ArrayList<ChiTietPhieuNhapDTO>> chitietsanpham = new HashMap<>();

    public ChiTietPhieuNhapDialog(JFrame owner, String title, boolean modal, PhieuNhapDTO phieunhapDTO) {
        super(owner, title, modal);
        this.phieunhap = phieunhapDTO;
        phieunhapBus = new PhieuNhapBUS();
        chitietphieu = phieunhapBus.getChiTietPhieu_Type(phieunhapDTO.getMaPN());
        chitietsanpham = ctspBus.getChiTietSanPhamFromMaPN(phieunhapDTO.getMaPN());
        initComponent(title);
        initPhieuNhap();
        loadDataTableChiTietPhieu(chitietphieu);
        this.setVisible(true);
    }


    public void initPhieuNhap() {
        txtMaPhieu.setText(Integer.toString(this.phieunhap.getMaPN()));
        txtNhaCungCap.setText(NhaCungCapDAO.getInstance().selectById(phieunhap.getMaNCC()).getTenNCC());
        txtNhanVien.setText(NhanVienDAO.getInstance().selectById(phieunhap.getMaNV()).getTenNV());
        txtThoiGian.setText(Formater.FormatTime(phieunhap.getNgayLap()));
    }

    

    public void loadDataTableChiTietPhieu(ArrayList<ChiTietPhieuNhapDTO> ctPhieu) {
        tblModel.setRowCount(0);
        int size = ctPhieu.size();
        for (int i = 0; i < size; i++) {
            //SanPhamDTO pb = phienbanBus.getByMaPhienBan(ctPhieu.get(i).getMaphienbansp());
            ChiTietPhieuNhapDTO ctp =  ctPhieu.get(i);
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
        txtMaPhieu = new InputForm("Mã phiếu");
        txtNhanVien = new InputForm("Nhân viên nhập");
        txtNhaCungCap = new InputForm("Nhà cung cấp");
        txtThoiGian = new InputForm("Thời gian tạo");

        txtMaPhieu.setEditable(false);
        txtNhanVien.setEditable(false);
        txtNhaCungCap.setEditable(false);
        txtThoiGian.setEditable(false);

        pnmain_top.add(txtMaPhieu);
        pnmain_top.add(txtNhanVien);
        pnmain_top.add(txtNhaCungCap);
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
        if(phieunhap.getTrangThai() == 0) {
            btnXacNhan = new ButtonCustom("Xác nhận đã nhận hàng", "success", 14);
            btnXacNhan.addActionListener(this);        
            pnmain_btn.add(btnXacNhan);

        }
        
        
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
            writePDF w = new writePDF();
            if (this.phieunhap != null) {
                try {
                    w.writePN(phieunhap.getMaPN());
                    
                } catch (Exception ex) {
                    Logger.getLogger(ChiTietPhieuNhapDialog.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        if(source == btnXacNhan)
        {
            if (this.phieunhap != null) {
                try {
                    int c =phieunhapBus.submitDaNhanHang(phieunhap.getMaPN());
                    if (c == 0) {
                            JOptionPane.showMessageDialog(null, "Xác nhận không thành công!");
                        } else {
                            JOptionPane.showMessageDialog(null, "Xác nhận thành công!");
                            dispose();
                        }
                    
                } catch (Exception ex) {
                    Logger.getLogger(ChiTietPhieuNhapDialog.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        }
    }
    
//    public void loadDataTableChiTietPhieu(ArrayList<ChiTietPhieuNhapDTO> ctPhieu) {
//        tblModel.setRowCount(0);
//        int size = ctPhieu.size();
//        for (int i = 0; i < size; i++) {
//            //SanPhamDTO pb = phienbanBus.getByMaPhienBan(ctPhieu.get(i).getMaphienbansp());
//            ChiTietPhieuNhapDTO ctp =  ctPhieu.get(i);
//            SanPhamDTO sp = spBus.getByMaSP(ctp.getMaSP());
//            String tenSP = sp.getTenSP();
//            tblModel.addRow(new Object[]{ i + 1, ctp.getMaSP(), tenSP, Formater.FormatVND(ctp.getDonGia()), ctp.getSoLuong() });
//      
//        }
//    }
}