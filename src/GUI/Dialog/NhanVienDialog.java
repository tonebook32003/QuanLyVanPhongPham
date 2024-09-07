/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Dialog;

import BUS.NhanVienBUS;
import DAO.NhanVienDAO;
import DTO.NhanVienDTO;
import GUI.Component.ButtonCustom;
import GUI.Component.HeaderTitle;
import GUI.Component.InputDate;
import GUI.Component.InputForm;
import GUI.Component.NumericDocumentFilter;
import GUI.Component.SelectForm;
import helper.Validation;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.Date;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractButton;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;
import javax.swing.text.PlainDocument;

/**
 *
 * @author Admin
 */
public class NhanVienDialog extends JDialog {
    private NhanVienBUS nv;
    private HeaderTitle titlePage;
    private JPanel main, bottom;
    private ButtonCustom btnAdd, btnEdit, btnExit;
    //tenNV,  gioiTinh,  ngaySinh,  sdt,  email,  password,  trangThai,  quyenTruyCap
    private InputForm name;
    private InputForm sdt;
    private InputForm email;
    private ButtonGroup gender;
    private JRadioButton male;
    private JRadioButton female;
    private SelectForm cbbQuyenTruyCap;
    private InputForm password;
    private InputDate jcBd;
     String[] arrQuyenTruyCap = {"Admin", "User"};
    
    private NhanVienDTO nhanVien;

    public NhanVienDialog(NhanVienBUS nv, JFrame owner, boolean modal, String title, String type) {
        super(owner, title, modal);
        this.nv = nv;
        init(title, type);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public NhanVienDialog(NhanVienBUS nv, JFrame owner, boolean modal, String title, String type, DTO.NhanVienDTO nhanVien) {
        super(owner, title, modal);
        this.nv = nv;
        this.nhanVien = nhanVien;
        init(title, type);
        name.setText(nhanVien.getTenNV());
        sdt.setText(nhanVien.getSdt());
        email.setText(nhanVien.getEmail());
        if(nhanVien.getQuyenTruyCap().equals("Admin")) {
            cbbQuyenTruyCap.setSelectedIndex(0);
        }
        else 
        {
             cbbQuyenTruyCap.setSelectedIndex(1);
        }
        password.setText(nhanVien.getPassword());
        if (nhanVien.getGioiTinh().equals("Nam")) {
            male.setSelected(true);
        } else {
            female.setSelected(true);
        }
        jcBd.setDate(nhanVien.getNgaySinh());
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void init(String title, String type) {
        this.setSize(new Dimension(450, 590));
        this.setLayout(new BorderLayout(0, 0));

        titlePage = new HeaderTitle(title.toUpperCase());

        main = new JPanel();
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
        main.setBackground(Color.white);
        name = new InputForm("Họ và tên");
        sdt = new InputForm("Số điện thoại");
        password = new InputForm("Mật khẩu"); //, "password"
        cbbQuyenTruyCap = new SelectForm("Nhà cung cấp", arrQuyenTruyCap);     
        cbbQuyenTruyCap.setSelectedIndex(1);
        PlainDocument phonex = (PlainDocument) sdt.getTxtForm().getDocument();
        phonex.setDocumentFilter((new NumericDocumentFilter()));
        email = new InputForm("Email");
        male = new JRadioButton("Nam");
        female = new JRadioButton("Nữ");
        gender = new ButtonGroup();
        gender.add(male);
        gender.add(female);
        JPanel jpanelG = new JPanel(new GridLayout(2, 1, 0, 2));
        jpanelG.setBackground(Color.white);
        jpanelG.setBorder(new EmptyBorder(10, 10, 10, 10));
        JPanel jgender = new JPanel(new GridLayout(1, 2));
        jgender.setSize(new Dimension(500, 80));
        jgender.setBackground(Color.white);
        jgender.add(male);
        jgender.add(female);
        JLabel labelGender = new JLabel("Giới tính");
        jpanelG.add(labelGender);
        jpanelG.add(jgender);
        JPanel jpaneljd = new JPanel();
        jpaneljd.setBorder(new EmptyBorder(10, 10, 10, 10));
        JLabel lbBd = new JLabel("Ngày sinh");
        lbBd.setSize(new Dimension(100, 100));
        jpaneljd.setSize(new Dimension(500, 100));
        jpaneljd.setLayout(new FlowLayout(FlowLayout.LEFT));
        jpaneljd.setBackground(Color.white);
        jcBd = new InputDate("Ngày sinh");
        jcBd.setSize(new Dimension(100, 100));
        jpaneljd.add(lbBd);
        jpaneljd.add(jcBd);
        
        main.add(name);
        main.add(email);
        main.add(password);
        main.add(cbbQuyenTruyCap);
        main.add(sdt);
        main.add(jpanelG);
        main.add(jcBd);

        bottom = new JPanel(new FlowLayout());
        bottom.setBorder(new EmptyBorder(10, 0, 10, 0));
        bottom.setBackground(Color.white);
        btnAdd = new ButtonCustom("Thêm người dùng", "success", 14);
        btnEdit = new ButtonCustom("Lưu thông tin", "success", 14);
        btnExit = new ButtonCustom("Hủy bỏ", "danger", 14);
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (ValidationInput()) {
                        if(checkEmail(email.getText())){
                        try {
                            String txtGender = "unknow";
                            if (male.isSelected()) {
                                txtGender = "Nam";
                            } else if (female.isSelected()) {
                                txtGender = "Nữ";
                            }
                            String txtName = name.getText();
                            String txtSdt = sdt.getText();
                            String txtEmail = email.getText();
                            Date birthDay = jcBd.getDate();
                            String quyentruycap = cbbQuyenTruyCap.getSelectedItem().toString();
                            String pass = password.getText();   
                            java.sql.Date sqlDate = new java.sql.Date(birthDay.getTime());
                            
                            NhanVienDTO nV = new NhanVienDTO(txtName, txtGender,sqlDate, txtSdt, txtEmail, pass, 1,  quyentruycap);
                            NhanVienDAO.getInstance().insert(nV);
                            nv.insertNv(nV);
                            nv.loadTable();
                            dispose();
                        } catch (ParseException ex) {
                            Logger.getLogger(NhanVienDialog.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    }
                } catch (ParseException ex) {
                    Logger.getLogger(NhanVienDialog.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (ValidationInput()) {
                        try {
                            String txtGender = "unknow";
                            if (male.isSelected()) {
                                txtGender = "Nam";
                            } else if (female.isSelected()) {
                                txtGender = "Nữ";
                            }
                            String txtName = name.getText();
                            String txtSdt = sdt.getText();
                            String txtEmail = email.getText();
                            Date birthDay = jcBd.getDate();
                            String quyentruycap = cbbQuyenTruyCap.getSelectedItem().toString();
                            String pass = password.getText();   
                            java.sql.Date sqlDate = new java.sql.Date(birthDay.getTime());
                            
                            NhanVienDTO nV = new NhanVienDTO(nhanVien.getMaNV(),txtName, txtGender,sqlDate, txtSdt, txtEmail, pass, 1,  quyentruycap);
                            NhanVienDAO.getInstance().update(nV);
                            nv.listNv.set(nv.getIndex(), nV);
                            nv.loadTable();
                            dispose();
                        } catch (ParseException ex) {
                            Logger.getLogger(NhanVienDialog.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                } catch (ParseException ex) {
                    Logger.getLogger(NhanVienDialog.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        switch (type) {
            case "create" ->
                bottom.add(btnAdd);
            case "update" ->
                bottom.add(btnEdit);
            case "detail" -> {
                name.setDisable();
                sdt.setDisable();
                email.setDisable();
                password.setDisable();
                cbbQuyenTruyCap.setDisable();
                Enumeration<AbstractButton> enumeration = gender.getElements();
                while (enumeration.hasMoreElements()) {
                    enumeration.nextElement().setEnabled(false);
                }
                jcBd.setDisable();
            }
            default ->
                throw new AssertionError();
        }

        bottom.add(btnExit);

        this.add(titlePage, BorderLayout.NORTH);
        this.add(main, BorderLayout.CENTER);
        this.add(bottom, BorderLayout.SOUTH);

    }

    boolean ValidationInput() throws ParseException {
        if (Validation.isEmpty(name.getText())) {
            JOptionPane.showMessageDialog(this, "Tên nhân viên không được rỗng", "Cảnh báo !", JOptionPane.WARNING_MESSAGE);
            return false;
        } else if(name.getText().length()<6){
            JOptionPane.showMessageDialog(this, "Tên nhân viên ít nhất 6 kí tự!");
            return false;
        }else if (Validation.isEmpty(email.getText()) || !Validation.isEmail(email.getText())) {
            JOptionPane.showMessageDialog(this, "Email không được rỗng và phải đúng cú pháp", "Cảnh báo !", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        else if (Validation.isEmpty(sdt.getText()) && !Validation.isNumber(sdt.getText()) && sdt.getText().length() != 10) {
            JOptionPane.showMessageDialog(this, "Số điện thoại không được rỗng và phải là 10 ký tự số", "Cảnh báo !", JOptionPane.WARNING_MESSAGE);
            return false;
        } else if(jcBd.getDate()==null){
            JOptionPane.showMessageDialog(this, "Vui lòng chọn ngày sinh!");
            return false;
        } else if(!male.isSelected() && !female.isSelected()){
            JOptionPane.showMessageDialog(this, "Vui lòng chọn giới tính!");
            return false;
        }
        
        return true;
    }
    
    public boolean checkEmail(String email){
        if(!(NhanVienDAO.getInstance().selectByEmail(email)==null)){
          JOptionPane.showMessageDialog(this, "Tài khoản email này đã được sử dụng trong hệ thống!");
          return false;
        }
        return true;
    }
}
