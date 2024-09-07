/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Panel.ThongKe;

import BUS.ThongKeBUS;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 *
 * @author phamthithuphuong
 */
public class ThongKeDoanhThu extends JPanel {

    JTabbedPane tabbedPane;
    ThongKeDoanhThuTrongThang thongketrongthang;
    ThongKeDoanhThuTungNam thongketungnam;
    ThongKeDoanhThuTungThang thongkedoanhthutungthang;
    ThongKeDoanhThuTuNgayDenNgay thongkedoanhthutungaydenngay;
    Color BackgroundColor = new Color(240, 247, 250);
    ThongKeBUS thongkeBUS;

    public ThongKeDoanhThu(ThongKeBUS thongkeBUS) {
        this.thongkeBUS = thongkeBUS;
        initComponent();
    }

    public void initComponent() {
        this.setLayout(new GridLayout(1, 1));
        this.setBackground(BackgroundColor);

        thongketrongthang = new ThongKeDoanhThuTrongThang(thongkeBUS);
        thongketungnam = new ThongKeDoanhThuTungNam(thongkeBUS);
        thongkedoanhthutungthang = new ThongKeDoanhThuTungThang(thongkeBUS);
        thongkedoanhthutungaydenngay = new ThongKeDoanhThuTuNgayDenNgay(thongkeBUS);

        tabbedPane = new JTabbedPane();
        tabbedPane.setOpaque(false);
        tabbedPane.addTab("Thống kê theo năm", thongketungnam);
        tabbedPane.addTab("Thống kê từng tháng trong năm", thongkedoanhthutungthang);
        tabbedPane.addTab("Thống kê từng ngày trong tháng", thongketrongthang);
        tabbedPane.addTab("Thống kê từ ngày đến ngày", thongkedoanhthutungaydenngay);

        this.add(tabbedPane);
    }
}
