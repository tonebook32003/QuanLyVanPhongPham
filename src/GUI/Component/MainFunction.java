/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Component;

import java.awt.Color;
import java.util.HashMap;
import javax.swing.JSeparator;
import javax.swing.JToolBar;

/**
 *
 * @author Admin
 */
public class MainFunction extends JToolBar {
    //public ButtonToolBar btnAdd, btnDelete, btnEdit, btnDetail, btnNhapExcel, btnXuatExcel, btnHuyPhieu;
    //public JSeparator separator1;
    public HashMap<String, ButtonToolBar> btnFunctionItem = new HashMap<>();
    

    public MainFunction(String chucnang, String[] listBtn) {
        initData();
        initComponent(chucnang, listBtn);
    }

    public void initData() {
        btnFunctionItem.put("create", new ButtonToolBar("THÊM", "add.svg", "create"));
        btnFunctionItem.put("delete", new ButtonToolBar("XÓA", "delete.svg", "delete"));
        btnFunctionItem.put("update", new ButtonToolBar("SỬA", "edit.svg", "update"));
        btnFunctionItem.put("cancel", new ButtonToolBar("HUỶ PHIẾU", "cancel.svg", "delete"));
        btnFunctionItem.put("detail", new ButtonToolBar("CHI TIẾT", "detail.svg", "view"));
        btnFunctionItem.put("import", new ButtonToolBar("NHẬP EXCEL", "import_excel.svg", "create"));
        btnFunctionItem.put("export", new ButtonToolBar("XUẤT EXCEL", "export_excel.svg", "view"));
        btnFunctionItem.put("phone", new ButtonToolBar("XEM DS", "phone.svg", "view"));
    }

    private void initComponent(String chucnang, String[] listBtn) {
        this.setBackground(Color.WHITE);
        this.setRollover(true);
        initData();
        for (String itemButton : listBtn) {
            this.add(btnFunctionItem.get(itemButton));
            btnFunctionItem.get(itemButton).setEnabled(true);            
        }
    }
}
