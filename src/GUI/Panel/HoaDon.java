/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Panel;

import BUS.ChiTietHoaDonBUS;
import BUS.HoaDonBUS;
import BUS.KhachHangBUS;
import BUS.KhachHangBUS;
import BUS.NhanVienBUS;
import DTO.NhanVienDTO;
import DTO.HoaDonDTO;
import GUI.Component.InputDate;
import GUI.Component.InputForm;
import GUI.Component.IntegratedSearch;
import GUI.Component.MainFunction;
import GUI.Component.NumericDocumentFilter;
import GUI.Component.PanelBorderRadius;
import GUI.Component.SelectForm;
import GUI.Component.TableSorter;
import GUI.Dialog.ChiTietHoaDonDialog;
import GUI.Dialog.TaoHoaDonDialog;
import GUI.Main;
import helper.Formater;
import helper.JTableExporter;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.PlainDocument;

/**
 *
 * @author Admin
 */
public class HoaDon extends JPanel implements ActionListener, KeyListener, PropertyChangeListener, ItemListener {

    PanelBorderRadius main, functionBar, box;
    JPanel pnlBorder1, pnlBorder2, pnlBorder3, pnlBorder4, contentCenter;
    JTable tableHoaDon;
    JScrollPane scrollTableHoaDon;
    MainFunction mainFunction;
    IntegratedSearch search;
    DefaultTableModel tblModel;
    SelectForm cbxKhachHang, cbxNhanVien, cbxTrangThai;
    InputDate dateStart, dateEnd;
    InputForm moneyMin, moneyMax;

    TaoHoaDonDialog hoadonDialog;
    JFrame owner = (JFrame) SwingUtilities.getWindowAncestor(this);

    Main m;
    NhanVienDTO nv;

    HoaDonBUS hoadonBUS = new HoaDonBUS();
    KhachHangBUS khBUS = new KhachHangBUS();
    NhanVienBUS nvBUS = new NhanVienBUS();
    ArrayList<HoaDonDTO> listHoaDon;

    Color BackgroundColor = new Color(240, 247, 250);

    public HoaDon(Main m, NhanVienDTO nv) {
        this.m = m;
        this.nv = nv;
        initComponent();
        this.listHoaDon = hoadonBUS.getAll();
        loadDataTalbe(this.listHoaDon);
    }

    private void setComponentSize(JComponent component, Dimension size) {
        component.setPreferredSize(size);
        component.setMaximumSize(size);
    }

    public void initPadding() {
        pnlBorder1 = new JPanel();
        pnlBorder1.setPreferredSize(new Dimension(0, 10));
        pnlBorder1.setBackground(BackgroundColor);
        this.add(pnlBorder1, BorderLayout.NORTH);

        pnlBorder2 = new JPanel();
        pnlBorder2.setPreferredSize(new Dimension(0, 10));
        pnlBorder2.setBackground(BackgroundColor);
        this.add(pnlBorder2, BorderLayout.SOUTH);

        pnlBorder3 = new JPanel();
        pnlBorder3.setPreferredSize(new Dimension(10, 0));
        pnlBorder3.setBackground(BackgroundColor);
        this.add(pnlBorder3, BorderLayout.EAST);

        pnlBorder4 = new JPanel();
        pnlBorder4.setPreferredSize(new Dimension(10, 0));
        pnlBorder4.setBackground(BackgroundColor);
        this.add(pnlBorder4, BorderLayout.WEST);
    }

    private void initComponent() {
        this.setBackground(BackgroundColor);
        this.setLayout(new BorderLayout(0, 0));
        this.setOpaque(true);

        tableHoaDon = new JTable();
        scrollTableHoaDon = new JScrollPane();
        tblModel = new DefaultTableModel();
        String[] header = new String[]{"STT", "Mã hóa đơn", "Nhà cung cấp", "Nhân viên nhập", "Thời gian", "Tổng tiền", "Trạng thái"};
        tblModel.setColumnIdentifiers(header);
        tableHoaDon.setModel(tblModel);
        tableHoaDon.setDefaultEditor(Object.class, null);
        scrollTableHoaDon.setViewportView(tableHoaDon);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tableHoaDon.setDefaultRenderer(Object.class, centerRenderer);
        tableHoaDon.setFocusable(false);
        tableHoaDon.getColumnModel().getColumn(0).setPreferredWidth(10);
        tableHoaDon.getColumnModel().getColumn(1).setPreferredWidth(50);
        tableHoaDon.getColumnModel().getColumn(2).setPreferredWidth(150);

        tableHoaDon.setAutoCreateRowSorter(true);
        TableSorter.configureTableColumnSorter(tableHoaDon, 0, TableSorter.INTEGER_COMPARATOR);
        TableSorter.configureTableColumnSorter(tableHoaDon, 1, TableSorter.INTEGER_COMPARATOR);
        TableSorter.configureTableColumnSorter(tableHoaDon, 5, TableSorter.VND_CURRENCY_COMPARATOR);

        this.setBackground(BackgroundColor);
        this.setLayout(new BorderLayout(0, 0));
        this.setOpaque(true);

        initPadding();

        contentCenter = new JPanel();
        contentCenter.setPreferredSize(new Dimension(1100, 600));
        contentCenter.setBackground(BackgroundColor);
        contentCenter.setLayout(new BorderLayout(10, 10));
        this.add(contentCenter, BorderLayout.CENTER);

        functionBar = new PanelBorderRadius();
        functionBar.setPreferredSize(new Dimension(0, 100));
        functionBar.setLayout(new GridLayout(1, 2, 50, 0));
        functionBar.setBorder(new EmptyBorder(10, 10, 10, 10));

        String[] action = {"create", "detail", "cancel", "export"};
        mainFunction = new MainFunction("nhaphang", action);

        //Add Event MouseListener
        for (String ac : action) {
            mainFunction.btnFunctionItem.get(ac).addActionListener(this);
        }

        functionBar.add(mainFunction);

        String[] objToSearch = {"Tất cả", "Mã hóa đơn", "Nhà cung cấp", "Nhân viên nhập"};
        search = new IntegratedSearch(objToSearch);
        search.cbxChoose.addItemListener(this);
        search.txtSearchForm.addKeyListener(this);
        search.btnReset.addActionListener(this);
        functionBar.add(search);

        contentCenter.add(functionBar, BorderLayout.NORTH);

        box = new PanelBorderRadius();
        box.setPreferredSize(new Dimension(250, 0));
        box.setLayout(new BoxLayout(box, BoxLayout.Y_AXIS)); // Changed layout to BoxLayout
        box.setBorder(new EmptyBorder(0, 5, 150, 5));
        contentCenter.add(box, BorderLayout.WEST);

        // Handle
        String[] listKH = khBUS.getArrTenKhachHang();
        listKH = Stream.concat(Stream.of("Tất cả"), Arrays.stream(listKH)).toArray(String[]::new);
        String[] listNv = nvBUS.getArrTenNhanVien();
        listNv = Stream.concat(Stream.of("Tất cả"), Arrays.stream(listNv)).toArray(String[]::new);
        String[] listTT = {"Tất cả", "Đã thanh toán", "Đã hủy"};
        Dimension commonSize = new Dimension(200, 50);

        // init
        cbxKhachHang = new SelectForm("Khách hàng", listKH);
        setComponentSize(cbxKhachHang.getCbb(), commonSize);

        cbxNhanVien = new SelectForm("Nhân viên nhập", listNv);
        setComponentSize(cbxNhanVien.getCbb(), commonSize);

        cbxTrangThai = new SelectForm("Trạng thái", listTT);
        setComponentSize(cbxTrangThai.getCbb(), commonSize);

        dateStart = new InputDate("Từ ngày");
        setComponentSize(dateStart.getDateChooser(), commonSize);

        dateEnd = new InputDate("Đến ngày");
        setComponentSize(dateEnd.getDateChooser(), commonSize);

        moneyMin = new InputForm("Từ số tiền (VND)");
        setComponentSize(moneyMin.getTxtForm(), commonSize);

        moneyMax = new InputForm("Đến số tiền (VND)");
        setComponentSize(moneyMax.getTxtForm(), commonSize);
        PlainDocument doc_min = (PlainDocument) moneyMin.getTxtForm().getDocument();
        doc_min.setDocumentFilter(new NumericDocumentFilter());

        PlainDocument doc_max = (PlainDocument) moneyMax.getTxtForm().getDocument();
        doc_max.setDocumentFilter(new NumericDocumentFilter());

        // add listener
        cbxKhachHang.getCbb().addItemListener(this);
        cbxNhanVien.getCbb().addItemListener(this);
        cbxTrangThai.getCbb().addItemListener(this);

        dateStart.getDateChooser().addPropertyChangeListener(this);
        dateEnd.getDateChooser().addPropertyChangeListener(this);
        moneyMin.getTxtForm().addKeyListener(this);
        moneyMax.getTxtForm().addKeyListener(this);

        box.add(cbxKhachHang);
        box.add(cbxNhanVien);
        box.add(dateStart);
        box.add(dateEnd);
        box.add(moneyMin);
        box.add(moneyMax);
        box.add(cbxTrangThai);

        main = new PanelBorderRadius();
        BoxLayout boxly = new BoxLayout(main, BoxLayout.Y_AXIS);
        main.setLayout(boxly);
        main.setBorder(new EmptyBorder(0, 0, 0, 0));
        contentCenter.add(main, BorderLayout.CENTER);
        main.add(scrollTableHoaDon);
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == cbxTrangThai.getCbb()) {
            int index = cbxTrangThai.getSelectedIndex();
            if (index == 0) {
                listHoaDon = hoadonBUS.getAll();
            } else if (index == 1) {
                listHoaDon = hoadonBUS.getPaymentHD();
            } else if (index == 2) {
                listHoaDon = hoadonBUS.getCancelHD();
            }
            loadDataTalbe(listHoaDon);
        } else {
            try {
                Fillter();
            } catch (ParseException ex) {
                Logger.getLogger(HoaDon.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void loadDataTalbe(ArrayList<HoaDonDTO> listhoadon) {
        tblModel.setRowCount(0);
        int size = listhoadon.size();
        String trangthai = "";
        for (int i = 0; i < size; i++) {
            if (listhoadon.get(i).getTrangThai() == 1) {
                trangthai = "Đã thanh toán";
            } else {
                trangthai = "Đã hủy";
            }
            tblModel.addRow(new Object[]{
                i + 1, (int) listhoadon.get(i).getMaHD(),
                khBUS.getTenKhachHang(listhoadon.get(i).getMaKH()),
                nvBUS.getNameById(listhoadon.get(i).getMaNV()),
                Formater.FormatTime(listhoadon.get(i).getNgayLapHD()),
                Formater.FormatVND(listhoadon.get(i).getThanhTienHD()),
                trangthai
            });
        }
    }

    public int getRowSelected() {
        int index = tableHoaDon.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn hóa đơn");
        }
        return index;
    }

    public void Fillter() throws ParseException {
        if (validateSelectDate()) {
            int type = search.cbxChoose.getSelectedIndex();
            int makh = cbxKhachHang.getSelectedIndex() == 0 ? 0 : khBUS.getByIndex(cbxKhachHang.getSelectedIndex() - 1).getMaKH();
            int manv = cbxNhanVien.getSelectedIndex() == 0 ? 0 : nvBUS.getByIndex(cbxNhanVien.getSelectedIndex() - 1).getMaNV();
            String input = search.txtSearchForm.getText() != null ? search.txtSearchForm.getText() : "";
            Date time_start = dateStart.getDate() != null ? dateStart.getDate() : new Date(0);
            Date time_end = dateEnd.getDate() != null ? dateEnd.getDate() : new Date(System.currentTimeMillis());
            String min_price = moneyMin.getText();
            String max_price = moneyMax.getText();
            this.listHoaDon = hoadonBUS.fillerHoaDon(type, input, makh, manv, time_start, time_end, min_price, max_price);
            loadDataTalbe(listHoaDon);
        }
    }

    public void resetForm() {
        cbxKhachHang.setSelectedIndex(0);
        cbxNhanVien.setSelectedIndex(0);
        cbxTrangThai.setSelectedIndex(0);
        search.cbxChoose.setSelectedIndex(0);
        search.txtSearchForm.setText("");
        moneyMin.setText("");
        moneyMax.setText("");
        dateStart.getDateChooser().setCalendar(null);
        dateEnd.getDateChooser().setCalendar(null);
        this.listHoaDon = hoadonBUS.getAll();
        loadDataTalbe(listHoaDon);
    }

    public boolean validateSelectDate() throws ParseException {
        Date time_start = dateStart.getDate();
        Date time_end = dateEnd.getDate();

        Date current_date = new Date();
        if (time_start != null && time_start.after(current_date)) {
            JOptionPane.showMessageDialog(this, "Ngày bắt đầu không được lớn hơn ngày hiện tại", "Lỗi !", JOptionPane.ERROR_MESSAGE);
            dateStart.getDateChooser().setCalendar(null);
            return false;
        }
        if (time_end != null && time_end.after(current_date)) {
            JOptionPane.showMessageDialog(this, "Ngày kết thúc không được lớn hơn ngày hiện tại", "Lỗi !", JOptionPane.ERROR_MESSAGE);
            dateEnd.getDateChooser().setCalendar(null);
            return false;
        }
        if (time_start != null && time_end != null && time_start.after(time_end)) {
            JOptionPane.showMessageDialog(this, "Ngày kết thúc phải lớn hơn ngày bắt đầu", "Lỗi !", JOptionPane.ERROR_MESSAGE);
            dateEnd.getDateChooser().setCalendar(null);
            return false;
        }
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == mainFunction.btnFunctionItem.get("create")) {
            TaoHoaDonDialog hoadonDialog = new TaoHoaDonDialog(this, owner, "Tạo hóa đơn", true, nv);

        } else if (source == mainFunction.btnFunctionItem.get("detail")) {
            int index = getRowSelected();
            if (index != -1) {
                ChiTietHoaDonDialog ctsp = new ChiTietHoaDonDialog(m, "Thông tin hóa đơn", true, listHoaDon.get(index));
            }
        } else if (source == mainFunction.btnFunctionItem.get("cancel")) {
            int index = getRowSelected();
            if (index != -1) {
                int input = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn huỷ hóa đơn ?\nThao tác này không thể hoàn tác nên hãy suy nghĩ kĩ !", "Huỷ hóa đơn", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if (input == 0) {
                    HoaDonDTO pn = listHoaDon.get(index);
                    System.out.println(pn);
                    int c = hoadonBUS.cancelHoaDon(pn.getMaHD());
                    if (c == 0) {
                        JOptionPane.showMessageDialog(null, "Hủy hóa đơn không thành công!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Hủy hóa đơn thành công!");
                        loadDataTalbe(hoadonBUS.getAll());
                    }
                }
            }
        } else if (source == search.btnReset) {
            resetForm();
        } else if (source == mainFunction.btnFunctionItem.get("export")) {
            try {
                JTableExporter.exportJTableToExcel(tableHoaDon);
            } catch (IOException ex) {
                Logger.getLogger(HoaDon.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        try {
            Fillter();
        } catch (ParseException ex) {
            Logger.getLogger(HoaDon.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        try {
            Fillter();
        } catch (ParseException ex) {
            Logger.getLogger(HoaDon.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//    @Override
//    public void itemStateChanged(ItemEvent e) {
//        try {
//            Fillter();
//        } catch (ParseException ex) {
//            Logger.getLogger(HoaDon.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
}
