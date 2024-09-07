/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.NhanVienDAO;
import DTO.NhanVienDTO;
import GUI.Dialog.NhanVienDialog;
import GUI.Panel.NhanVien;
import helper.Validation;
import java.awt.CardLayout;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.BuiltinFormats;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


/**
 *
 * @author Admin
 */
public class NhanVienBUS implements ActionListener, DocumentListener{
    public GUI.Panel.NhanVien nv;
    private JTextField textField;
    public ArrayList<DTO.NhanVienDTO> listNv = NhanVienDAO.getInstance().selectAll();
    public NhanVienDAO nhanVienDAO = NhanVienDAO.getInstance();

    public NhanVienBUS() {
    }

    public NhanVienBUS(NhanVien nv) {
        this.nv = nv;
    }

    public NhanVienBUS(JTextField textField, NhanVien nv) {
        this.textField = textField;
        this.nv = nv;
    }

    public ArrayList<DTO.NhanVienDTO> getAll() {
        return this.listNv;
    }

    public NhanVienDTO getByIndex(int index) {
        return this.listNv.get(index);
    }

    public int getIndexById(int manv) {
        int i = 0;
        int vitri = -1;
        int size = this.listNv.size();
        while (i < size && vitri == -1) {
            if (this.listNv.get(i).getMaNV() == manv) {
                vitri = i;
            } else {
                i++;
            }
        }
        return vitri;
    }   
    
    
    public String getNameById(int manv) {
        return nhanVienDAO.selectById(manv).getTenNV();
    }

    public String[] getArrTenNhanVien() {
        int size = listNv.size();
        String[] result = new String[size];
        for (int i = 0; i < size; i++) {
            result[i] = listNv.get(i).getTenNV();
        }
        return result;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String btn = e.getActionCommand(); // văn bản hiển thị trên button
        switch (btn) {
            case "THÊM" -> {
                NhanVienDialog nvthem = new NhanVienDialog(this, nv.owner, true, "Thêm nhân viên", "create");
                JOptionPane.showMessageDialog(nv, "Thêm khách hàng thành công !");
            }
            case "SỬA" -> {
                int index = nv.getRow();
                if (index < 0) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn nhân viên cần sửa");
                } else {
                    int input = JOptionPane.showConfirmDialog(nv,
                    "Bạn có muốn chỉnh sửa chi tiết nhân viên?", "Chỉnh sửa chi tiết", 
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
                    // 0=ok, 2=cancel
                    if(input == 0){
                        NhanVienDialog nvsua = new NhanVienDialog(this, nv.owner, true, "Sửa nhân viên", "update", nv.getNhanVien());
                        JOptionPane.showMessageDialog(nv, "Cập nhật nhân viên thành công !");  
                    }                    
                }
            }
            case "XÓA" -> {                
                if (nv.getRow() < 0) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn nhân viên cần xóa");
                } else {                    
                    int input = JOptionPane.showConfirmDialog(nv,
                    "Bạn có chắc chắc muốn xóa nhân viên?", "Xóa nhân viên", 
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
                    // 0=ok, 2=cancel
                    if(input == 0){
                        deleteNv(nv.getNhanVien());
                        JOptionPane.showMessageDialog(nv, "Xóa nhân viên thành công !");  
                    }    
                }
            }
            case "CHI TIẾT" -> {
                if (nv.getRow() < 0) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn nhân viên");
                } else {
                    NhanVienDialog nvsua = new NhanVienDialog(this, nv.owner, true, "Xem nhân viên", "detail", nv.getNhanVien());
                }
            }
            case "NHẬP EXCEL" -> {
                importExcel();
            }
            case "XUẤT EXCEL" -> {
                String[] header = new String[]{"MãNV", "Tên nhân viên", "Giới tính", "Ngày sinh", "Số điên thoại", "Email", "Password", "Trạng thái", "Quyền truy cập"};
                exportExcel(listNv, header);
            }
        }
        nv.loadDataTalbe(listNv);
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        String text = textField.getText();
        if (text.length() == 0) {
            nv.loadDataTalbe(listNv);
        } else {
            ArrayList<NhanVienDTO> listSearch = search(text);
            searchLoadTable(listSearch);
        }
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        String text = textField.getText();
        if (text.length() == 0) {
            nv.loadDataTalbe(listNv);
        } else {
            ArrayList<NhanVienDTO> listSearch = search(text);
            searchLoadTable(listSearch);
        }
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        // System.out.println("Text field changed: " + textField.getText());
    }

    public void insertNv(NhanVienDTO nv) {
        listNv.add(nv);
    }

    public void updateNv(int index, NhanVienDTO nv) {
        listNv.set(index, nv);
    }

    public int getIndex() {
        return nv.getRow();
    }

    public void deleteNv(NhanVienDTO nv) {
        NhanVienDAO.getInstance().delete(nv.getMaNV());
        listNv.removeIf(n -> (n.getMaNV() == nv.getMaNV()));
        loadTable();
    }

    public void loadTable() {
        nv.loadDataTalbe(listNv);
    }

    public void searchLoadTable(ArrayList<NhanVienDTO> list) {
        nv.loadDataTalbe(list);
    }

    public void openFile(String file) {
        try {
            File path = new File(file);
            Desktop.getDesktop().open(path);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void exportExcel(ArrayList<NhanVienDTO> list, String[] header) {
        try {
            if (!list.isEmpty()) {
                JFileChooser jFileChooser = new JFileChooser();
                jFileChooser.showSaveDialog(nv.owner);
                File saveFile = jFileChooser.getSelectedFile();
                if (saveFile != null) {
                    saveFile = new File(saveFile.toString() + ".xlsx");
                    Workbook wb = new XSSFWorkbook();
                    Sheet sheet = wb.createSheet("Nhân viên");

                    writeHeader(header, sheet, 0);
                    int rowIndex = 1;
                    for (NhanVienDTO nv : list) {
                        Row row = sheet.createRow(rowIndex++);
                        writeNhanVien(nv, row);
                    }
                    FileOutputStream out = new FileOutputStream(new File(saveFile.toString()));
                    wb.write(out);
                    wb.close();
                    out.close();
                    openFile(saveFile.toString());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<NhanVienDTO> search(String text) {
        String luachon = (String) nv.search.cbxChoose.getSelectedItem();
        text = text.toLowerCase();
        ArrayList<NhanVienDTO> result = new ArrayList<>();
        switch (luachon) {
            case "Tất cả" -> {
                for (NhanVienDTO i : this.listNv) {
                    if (i.getTenNV().toLowerCase().contains(text) || i.getEmail().toLowerCase().contains(text)
                            || i.getSdt().toLowerCase().contains(text)) {
                        result.add(i);
                    }
                }
            }
            case "Họ tên" -> {
                for (NhanVienDTO i : this.listNv) {
                    if (i.getTenNV().toLowerCase().contains(text)) {
                        result.add(i);
                    }
                }
            }
            case "Email" -> {
                for (NhanVienDTO i : this.listNv) {
                    if (i.getEmail().toLowerCase().contains(text)
                           ) {
                        result.add(i);
                    }
                }
            }
            default ->
                throw new AssertionError();
        }

        return result;
    }

    private static void writeHeader(String[] list, Sheet sheet, int rowIndex) {
        CellStyle cellStyle = createStyleForHeader(sheet);
        Row row = sheet.createRow(rowIndex);
        Cell cell;
        for (int i = 0; i < list.length; i++) {
            cell = row.createCell(i);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(list[i]);
            sheet.autoSizeColumn(i);
        }
    }

    private static CellStyle createStyleForHeader(Sheet sheet) {
        // Create font
        Font font = sheet.getWorkbook().createFont();
        font.setFontName("Times New Roman");
        font.setBold(true);
        font.setFontHeightInPoints((short) 14); // font size
        font.setColor(IndexedColors.WHITE.getIndex()); // text color

        // Create CellStyle
        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        cellStyle.setFont(font);
        cellStyle.setFillForegroundColor(IndexedColors.BLUE.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        return cellStyle;
    }

    private static void writeNhanVien(NhanVienDTO nv, Row row) {
        CellStyle cellStyleFormatNumber = null;
        if (cellStyleFormatNumber == null) {
            // Format number
            short format = (short) BuiltinFormats.getBuiltinFormat("#,##0");
            // DataFormat df = workbook.createDataFormat();
            // short format = df.getFormat("#,##0");

            //Create CellStyle
            Workbook workbook = row.getSheet().getWorkbook();
            cellStyleFormatNumber = workbook.createCellStyle();
            cellStyleFormatNumber.setDataFormat(format);
        }
        
        //tenNV,  gioiTinh,  ngaySinh,  sdt,  email,  password,  trangThai,  quyenTruyCap
        
        Cell cell = row.createCell(0);
        cell.setCellValue(nv.getMaNV());

        cell = row.createCell(1);
        cell.setCellValue(nv.getTenNV());
        
        cell = row.createCell(2);
        cell.setCellValue(nv.getGioiTinh());

        cell = row.createCell(3);
        cell.setCellValue("" + nv.getNgaySinh());
        
        cell = row.createCell(4);
        cell.setCellValue(nv.getSdt());

        cell = row.createCell(5);
        cell.setCellValue(nv.getEmail());

        cell = row.createCell(6);
        cell.setCellValue(nv.getPassword());

        cell = row.createCell(7);
        cell.setCellValue("" + nv.getTrangThai());
        
        cell = row.createCell(8);
        cell.setCellValue("" + nv.getQuyenTruyCap());
        
    }

    public void importExcel() {
        File excelFile;
        FileInputStream excelFIS = null;
        BufferedInputStream excelBIS = null;
        XSSFWorkbook excelJTableImport = null;
        JFileChooser jf = new JFileChooser();
        int result = jf.showOpenDialog(null);
        jf.setDialogTitle("Open file");
        Workbook workbook = null;
        int k = 0;
        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                excelFile = jf.getSelectedFile();
                excelFIS = new FileInputStream(excelFile);
                excelBIS = new BufferedInputStream(excelFIS);
                excelJTableImport = new XSSFWorkbook(excelBIS);
                XSSFSheet excelSheet = excelJTableImport.getSheetAt(0);
                
                for (int row = 1; row <= excelSheet.getLastRowNum(); row++) {
                    int check = 1;
                    int gt;
                    XSSFRow excelRow = excelSheet.getRow(row);
                    //int id = NhanVienDAO.getInstance().getAutoIncrement();
                    String tennv = excelRow.getCell(0).getStringCellValue();
                    String gioitinh = excelRow.getCell(1).getStringCellValue();    
                    Date ngaysinh = (Date) excelRow.getCell(2).getDateCellValue();
                    String sdt = excelRow.getCell(3).getStringCellValue();                    
                    java.sql.Date birth = new java.sql.Date(ngaysinh.getTime());
                    String email = excelRow.getCell(4).getStringCellValue();
                    String password = excelRow.getCell(5).getStringCellValue();
                    String trangThai = excelRow.getCell(6).getStringCellValue();
                    int trangthai = 1;
                    if(trangThai.equals("Nghỉ")) {
                        trangthai = 0;
                    }                    
                    String quyentruycap = excelRow.getCell(7).getStringCellValue();
                    
                    if (Validation.isEmpty(tennv) || Validation.isEmpty(email)
                            || !Validation.isEmail(email) || Validation.isEmpty(sdt)
                            || Validation.isEmpty(sdt) || !isPhoneNumber(sdt)
                            || sdt.length() != 10 || Validation.isEmpty(gioitinh)) {
                        check = 0;
                    }
                    if (check == 0) {
                        k += 1;
                    } else {
                        //tenNV,  gioiTinh,  ngaySinh,  sdt,  email,  password,  trangThai,  quyenTruyCap
                        NhanVienDTO nvdto = new NhanVienDTO(tennv, gioitinh, birth, sdt, email, password , trangthai,  quyentruycap);
                        NhanVienDAO.getInstance().insert(nvdto);
                    }
                    JOptionPane.showMessageDialog(null, "Nhập thành công");
                }

            } catch (FileNotFoundException ex) {
                System.out.println("Lỗi đọc file");
            } catch (IOException ex) {
                System.out.println("Lỗi đọc file");
            }
        }
        if (k != 0) {
            JOptionPane.showMessageDialog(null, "Những dữ liệu không chuẩn không được thêm vào");
        }
    }

    public static boolean isPhoneNumber(String str) {
        // Loại bỏ khoảng trắng và dấu ngoặc đơn nếu có
        str = str.replaceAll("\\s+", "").replaceAll("\\(", "").replaceAll("\\)", "").replaceAll("\\-", "");

        // Kiểm tra xem chuỗi có phải là một số điện thoại hợp lệ hay không
        if (str.matches("\\d{10}")) { // Kiểm tra số điện thoại 10 chữ số
            return true;
        } else if (str.matches("\\d{3}-\\d{3}-\\d{4}")) { // Kiểm tra số điện thoại có dấu gạch ngang
            return true;
        } else {
            return str.matches("\\(\\d{3}\\)\\d{3}-\\d{4}"); // Kiểm tra số điện thoại có dấu ngoặc đơn
        }        // Trả về false nếu chuỗi không phải là số điện thoại hợp lệ

    }
}
