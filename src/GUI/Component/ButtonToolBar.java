/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Component;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import java.awt.Color;
import java.awt.Cursor;
import javax.swing.JButton;

/**
 *
 * @author Nguyễn Văn Lợi
 * ButtonToolBar thiết lập hình dạng của các Button trên cùng của mỗi Panel
 * Ví dụ: Mỗi Panel NhanVien, SanPham, ... đều có chức năng thêm xóa sửa ... -> ButtonTooolbar
 */
public class ButtonToolBar extends JButton {
    String permission;
    public ButtonToolBar(String title, String icon, String permisstion) {
        this.permission = permission;
        this.setFont(new java.awt.Font(FlatRobotoFont.FAMILY, 1, 14));
        this.setForeground(new Color(1, 88, 155));
        this.setIcon(new FlatSVGIcon("./icon/"+icon));
        this.setText(title);
        this.setFocusable(false);
        this.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        this.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.putClientProperty("JButton.buttonType", "toolBarButton");
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
    
}
