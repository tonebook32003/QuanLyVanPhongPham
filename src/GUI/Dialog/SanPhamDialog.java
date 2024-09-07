
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Dialog;

import BUS.LoaiSanPhamBUS;
import BUS.NhaCungCapBUS;
import DAO.SanPhamDAO;
import DTO.SanPhamDTO;
import GUI.Component.ButtonCustom;
import GUI.Component.HeaderTitle;
import GUI.Component.InputDate;
import GUI.Component.InputForm;
import GUI.Component.InputImage;
import GUI.Component.NumericDocumentFilter;
import GUI.Component.SelectForm;
import GUI.Panel.SanPham;
import helper.Validation;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import javax.swing.BoxLayout;
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
import javax.swing.text.PlainDocument;

/**
 *
 * @author Admin
 */
public class SanPhamDialog extends JDialog implements ActionListener {
    
    private HeaderTitle titlePage;
    private JPanel pninfosanpham, pnbottom, pnCenter, pninfosanphamright, pnmain;
    private ButtonCustom btnThemSanPham, btnThoat, btnLuuThayDoi;
    private InputDate hansd;
    InputForm maSP, tenSP, xuatxu, gianhap, giaxuat, soluong, donvitinh;
    
    SelectForm cbbLoaiSanPham, cbbNhaCungCap;
    
    InputImage hinhanh;
    
    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
    GUI.Panel.SanPham jpSP;
 
    
    LoaiSanPhamBUS loaisanphamBUS = new LoaiSanPhamBUS();
    NhaCungCapBUS nhacungcapBUS = new NhaCungCapBUS();
    
    
    
    SanPhamDTO sp;
    String[] arrNhaCungCap;
    String[] arrLoaiSanPham;
    
    
    // KHỞI TẠO GIÁ TRỊ MẶC ĐỊNH
    public void init(SanPham jpSP) {
        this.jpSP = jpSP;
        arrLoaiSanPham = loaisanphamBUS.getArrTenLoaiSanPham();
        arrNhaCungCap = nhacungcapBUS.getArrTenNhaCungCap();
    }

    // KHỞI TẠO FORM CREATE SẢN PHẨM
    public SanPhamDialog(SanPham jpSP, JFrame owner, String title, boolean modal, String type) {
        super(owner, title, modal);
        init(jpSP);
        initComponents(title, type);
    }
    // KHỞI TẠO FORM UPDATE SẢN PHẨM
    public SanPhamDialog(SanPham jpSP, JFrame owner, String title, boolean modal, String type, SanPhamDTO sp) {
        super(owner, title, modal);
        init(jpSP);
        this.sp = sp;
        initComponents(title, type);
    }
    
    public void initCard(String type) {
        pnCenter = new JPanel(new BorderLayout());
        pninfosanpham = new JPanel(new GridLayout(3, 4, 0, 0));
        pninfosanpham.setBackground(Color.WHITE);
        pnCenter.add(pninfosanpham, BorderLayout.CENTER);

        pninfosanphamright = new JPanel();
        pninfosanphamright.setBackground(Color.WHITE);
        pninfosanphamright.setPreferredSize(new Dimension(300, 600));
        pninfosanphamright.setBorder(new EmptyBorder(0, 10, 0, 10));
        pnCenter.add(pninfosanphamright, BorderLayout.WEST);

       
        //maSP = new InputForm("Mã sản phẩm");
        hansd = new InputDate("Hạn sử dụng");
        hansd.setSize(new Dimension(100, 100));
        tenSP = new InputForm("Tên sản phẩm");
        xuatxu = new InputForm("Xuất xứ");
        gianhap = new InputForm("Giá nhập");
        giaxuat = new InputForm("Giá xuất");
        soluong = new InputForm("Số lượng");
        donvitinh = new InputForm("Đơn vị tính");
        cbbLoaiSanPham = new SelectForm("Loại sản phẩm", arrLoaiSanPham);
        cbbNhaCungCap = new SelectForm("Nhà cung cấp", arrNhaCungCap);
        hinhanh = new InputImage("Hình minh họa");

        pninfosanpham.add(tenSP);
        pninfosanpham.add(hansd);
        //pninfosanpham.add(maSP);
        pninfosanpham.add(xuatxu);
        pninfosanpham.add(gianhap);
        pninfosanpham.add(giaxuat);
        pninfosanpham.add(soluong);
        pninfosanpham.add(donvitinh);
        pninfosanpham.add(cbbLoaiSanPham);
        pninfosanpham.add(cbbNhaCungCap);
        pninfosanphamright.add(hinhanh);

        pnbottom = new JPanel(new FlowLayout());
        pnbottom.setBorder(new EmptyBorder(20, 0, 10, 0));
        pnbottom.setBackground(Color.white);
        switch (type) {
            case "update" -> {
                // THÊM NÚT CẬP NHẬT SẢN PHẨM
                btnLuuThayDoi = new ButtonCustom("Lưu thông tin", "success", 14);
                btnLuuThayDoi.addActionListener(this);
                pnbottom.add(btnLuuThayDoi);
            }
            case "create" -> {
                // THÊM NÚT TẠO SẢN PHẨM MỚI
                btnThemSanPham = new ButtonCustom("Thêm sản phẩm", "success", 14);
                btnThemSanPham.addActionListener(this);
                pnbottom.add(btnThemSanPham);
            }
        }

        // THÊM NÚT THOÁT
        btnThoat = new ButtonCustom("Thoát", "danger", 14);
        btnThoat.addActionListener(this);
        pnbottom.add(btnThoat);
        pnCenter.add(pnbottom, BorderLayout.SOUTH);
    }

    // HÀM KHỞI TẠO CÁC COMPONENTS
    public void initComponents(String title, String type) {
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        this.setSize(new Dimension(1150, 480));
        this.setLayout(new BorderLayout(0, 0));
        titlePage = new HeaderTitle(title.toUpperCase());

        pnmain = new JPanel(new CardLayout());

        initCard(type);

        pnmain.add(pnCenter);

        // khi chức năng là view hoặc update thì hiển thị các trường input cho người dùng
        switch (type) {
            case "view" -> setInfo(sp);
            case "update" -> setInfo(sp);
            default -> {
            }
        }

        this.add(titlePage, BorderLayout.NORTH);
        this.add(pnmain, BorderLayout.CENTER);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    // HÀM THÊM ẢNH SẢN PHẨM VÀO ./src/products
    public String addImage(String urlImg) {
        Random randomGenerator = new Random();
        int ram = randomGenerator.nextInt(1000);
        File sourceFile = new File(urlImg);
        String destPath = "./src/products";
        File destFolder = new File(destPath);
        String newName = ram + sourceFile.getName();
        try {
            Path dest = Paths.get(destFolder.getPath(), newName);
            Files.copy(sourceFile.toPath(), dest);
        } catch (IOException e) {
        }
        return newName;
    }

    // TẠO SỰ KIỆN CLICK VÀO CÁC BUTTON TRONG TOOLBAR (VIEW, CREATE, UPDATE)
    @Override
    public void actionPerformed(ActionEvent e) {
        //btnThemSanPham, btnThoat, btnLuuThayDoi;
        Object source = e.getSource();           
        if (source == btnThemSanPham) {
            if(validateCard()) {
                eventAddSanPham();
            }            
        } else if(source == btnLuuThayDoi){
            if(validateCard()) {
                SanPhamDTO spNew = getInfo();
            if(!spNew.getHinhAnh().equals(this.sp.getHinhAnh())){
                spNew.setHinhAnh(addImage(spNew.getHinhAnh()));
            }
            spNew.setMaSP(this.sp.getMaSP());
            SanPhamDAO.getInstance().update(sp);
            this.jpSP.spBUS.update(spNew);
            this.jpSP.loadDataTalbe(this.jpSP.spBUS.getAll());
            int input = JOptionPane.showConfirmDialog(this, 
                "Bạn có muốn chỉnh sửa chi tiết sản phẩm?", "Chỉnh sửa chi tiết", 
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
            // 0=ok, 2=cancel
            if(input == 0){
                JOptionPane.showMessageDialog(this, "Cập nhật sản phẩm thành công !");
                CardLayout c = (CardLayout) pnmain.getLayout();
                c.next(pnmain);
                dispose();                
            }
            }
            
        }
        if(source == btnThoat){
            dispose();
        }
    }

    // HÀM THÊM SẢN PHẨM
    public void eventAddSanPham() {
        SanPhamDTO sp = getInfo();
        sp.setHinhAnh(addImage(sp.getHinhAnh())); // return về đường dẫn của hình ảnh
        if (jpSP.spBUS.add(sp)) {
            JOptionPane.showMessageDialog(this, "Thêm sản phẩm thành công !");
            jpSP.loadDataTalbe(jpSP.listSP);
            dispose();
        }
    }

    // HÀM LẤY GIÁ TRỊ TỪ CÁC TEXT FIELDS -> PHỤC VỤ HÀM CREATE SẢN PHẨM
    public SanPhamDTO getInfo() {
        String tenSP = this.tenSP.getText().toString();
        String xuatXu = this.xuatxu.getText().toString();      
        Date hsd = null; 
        try {
            // Lấy đối tượng Date trực tiếp từ JDateChooser
            hsd = hansd.getDate();
        } catch (ParseException e) { 
            e.printStackTrace();
        }
        // Tạo một đối tượng java.sql.Date mới từ đối tượng java.util.Date
        java.sql.Date sqlDate = new java.sql.Date(hsd.getTime());
        
        double giaNhap = Double.parseDouble(this.gianhap.getText().toString());
        double giaXuat = Double.parseDouble(this.giaxuat.getText().toString());
        int soLuong = Integer.parseInt(this.soluong.getText());
        String hinhAnh = this.hinhanh.getUrl_img();
        String donViTinh = this.donvitinh.getText().toString();
        String loai = cbbLoaiSanPham.getSelectedItem().toString();
        String nhacungcap = cbbNhaCungCap.getSelectedItem().toString();
        int maLoai = loaisanphamBUS.getIDByTenLoai(loai);
        int maNCC = nhacungcapBUS.getIDByTenNCC(nhacungcap);
        int trangThai = 1;
        SanPhamDTO result = new SanPhamDTO(tenSP, xuatXu, sqlDate, giaNhap, giaXuat, soLuong, hinhAnh, donViTinh, maLoai, maNCC, trangThai);
        return result;
    }

    // HÀM ĐIỀN THÔNG TIN SẢN PHẨM ĐƯỢC CHỌN VÀO CÁC TEXT FIELDS TƯƠNG ỨNG -> PHỤC VỤ HÀM UPDATE
    public void setInfo(SanPhamDTO sp) {    
        hinhanh.setUrl_img(sp.getHinhAnh());
        //maSP.setText(String.valueOf(sp.getMaSP()));
        tenSP.setText(sp.getTenSP());  
        xuatxu.setText(sp.getXuatXu());   
        hansd.setDate(sp.getHsd());
        gianhap.setText(String.valueOf(sp.getGiaNhap()));
        giaxuat.setText(String.valueOf(sp.getGiaXuat()));
        soluong.setText(String.valueOf(sp.getSoLuong()));
        donvitinh.setText(String.valueOf(sp.getDonViTinh()));
        cbbLoaiSanPham.setSelectedIndex(loaisanphamBUS.getIndexByMaLSP(sp.getMaLoai()));
        cbbNhaCungCap.setSelectedIndex(nhacungcapBUS.getIndexByMaNCC(sp.getMaNCC()));
    }


    // HÀM KIỂM TRA INPUT CỦA TEXT FIELDS
    public boolean validateCard() {
        boolean check = true; 
        Date hsd = null; 
        try {
            // Lấy đối tượng Date trực tiếp từ JDateChooser
            hsd = hansd.getDate();
        } catch (ParseException e) { 
            e.printStackTrace();
        }
        // Tạo một đối tượng java.sql.Date mới từ đối tượng java.util.Date
        java.sql.Date sqlDate;
        if(hsd != null) {
            sqlDate = new java.sql.Date(hsd.getTime());
        }
        else {
            sqlDate = null;
        }
        
        String loai = cbbLoaiSanPham.getSelectedItem().toString();
        String nhacungcap = cbbNhaCungCap.getSelectedItem().toString();
        int maLoai = loaisanphamBUS.getIDByTenLoai(loai);
        int maNCC = nhacungcapBUS.getIDByTenNCC(nhacungcap);
        
        if (Validation.isEmpty(this.tenSP.getText()) 
                || Validation.isEmpty(this.xuatxu.getText())
                || Validation.isEmpty(sqlDate.toString()) 
                || Validation.isEmpty(this.gianhap.getText())
                || Validation.isEmpty(this.giaxuat.getText()) 
                || Validation.isEmpty(this.soluong.getText())
                || Validation.isEmpty(this.hinhanh.getUrl_img()) 
                || Validation.isEmpty(this.donvitinh.getText().toString()) 
                || Validation.isEmpty(String.valueOf(maLoai)) 
                || Validation.isEmpty(String.valueOf(maNCC))) {
            check = false;
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin !");
        } else if( !Validation.isDoubleNumber(this.giaxuat.getText()) 
                || !Validation.isDoubleNumber(this.gianhap.getText())
                || !Validation.isDoubleNumber(this.soluong.getText())){
            check = false;
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số nguyên cho các trường dữ liệu tương ứng !");
        }
        return check;
    }

}
