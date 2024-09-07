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
public class ThongKe extends JPanel{
    JTabbedPane tabbedPane;
    JPanel tongquan, nhacungcap, khachhang, doanhthu;
    //ThongKeTonKho nhapxuat;
    Color BackgroundColor = new Color(240, 247, 250);
    ThongKeBUS thongkeBUS = new ThongKeBUS();

    public ThongKe() {
        initComponent();
    }

    public void initComponent() {
        this.setLayout(new GridLayout(1, 1));
        this.setBackground(BackgroundColor);

        tongquan = new ThongKeTongQuan(thongkeBUS);
        doanhthu = new ThongKeDoanhThu(thongkeBUS);

        tabbedPane = new JTabbedPane();
        tabbedPane.setOpaque(false);
        tabbedPane.addTab("Tổng quan", tongquan);
        tabbedPane.addTab("Doanh thu", doanhthu);

        this.add(tabbedPane);
    }
}
