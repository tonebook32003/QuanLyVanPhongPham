/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Component;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Admin
 */
public class IntegratedSearch extends JPanel {
    public JComboBox<String> cbxChoose;
    public JButton btnReset;
    public JTextField txtSearchForm;

    private void initComponent(String str[]) {

        this.setBackground(Color.WHITE);
        BoxLayout bx = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.setLayout(bx);

        JPanel jpSearch = new JPanel(new BorderLayout(5,10));
        jpSearch.setBorder(new EmptyBorder(18,15,18,15));
        jpSearch.setBackground(Color.white);
        cbxChoose = new JComboBox();
        cbxChoose.setModel(new DefaultComboBoxModel<>(str));
        cbxChoose.setPreferredSize(new Dimension(140, 0));
        cbxChoose.setFont(new java.awt.Font(FlatRobotoFont.FAMILY, 0, 13));
        cbxChoose.setFocusable(false);
        jpSearch.add(cbxChoose,BorderLayout.WEST);

        txtSearchForm = new JTextField();
        txtSearchForm.setFont(new Font(FlatRobotoFont.FAMILY, 0, 13));
        txtSearchForm.putClientProperty("JTextField.placeholderText", "Nhập nội dung tìm kiếm...");
        txtSearchForm.putClientProperty("JTextField.showClearButton", true);
        jpSearch.add(txtSearchForm);

        btnReset = new JButton("Làm mới");
        btnReset.setFont(new java.awt.Font(FlatRobotoFont.FAMILY, 0, 14));
        btnReset.setIcon(new FlatSVGIcon("./icon/refresh.svg"));
        btnReset.setPreferredSize(new Dimension(125, 0));
        btnReset.addActionListener(this::btnResetActionPerformed);
        jpSearch.add(btnReset,BorderLayout.EAST);
        this.add(jpSearch);
    }

    public IntegratedSearch(String str[]) {
        initComponent(str);
    }

    private void btnResetActionPerformed(java.awt.event.ActionEvent e) {
        txtSearchForm.setText("");
        cbxChoose.setSelectedIndex(0);
    }
}
