/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.ChiTietPhieuNhapDAO;
import DAO.PhieuNhapDAO;
import DTO.ChiTietPhieuNhapDTO;
import DTO.PhieuNhapDTO;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

/**
 *
 * @author phamthithuphuong
 */
public class PhieuNhapBUS {
    public final PhieuNhapDAO phieunhapDAO = new PhieuNhapDAO();
    public final ChiTietPhieuNhapDAO ctPhieuNhapDAO = new ChiTietPhieuNhapDAO();

    NhaCungCapBUS nccBUS = new NhaCungCapBUS();
    NhanVienBUS nvBUS = new NhanVienBUS();

    ArrayList<PhieuNhapDTO> listPhieuNhap = phieunhapDAO.selectAll();

    public PhieuNhapBUS() {
    }

    public ArrayList<PhieuNhapDTO> getAll() {
        this.listPhieuNhap = phieunhapDAO.selectAll();
        return this.listPhieuNhap;
    }
    public ArrayList<PhieuNhapDTO> getAllEvenDelete() {
        this.listPhieuNhap = phieunhapDAO.selectAllEvenDelete();
        return this.listPhieuNhap;
    }
    

    public ArrayList<PhieuNhapDTO> getAllList() {
        return this.listPhieuNhap;
    }

    

    public ArrayList<ChiTietPhieuNhapDTO> getChiTietPhieu(int maphieunhap) {
        return ctPhieuNhapDAO.selectAll(maphieunhap);
    }

//    public ArrayList<ChiTietPhieuDTO> getChiTietPhieu_Type(int maphieunhap) {
//        ArrayList<ChiTietPhieuNhapDTO> arr = ctPhieuNhapDAO.selectAll(Integer.toString(maphieunhap));
//        ArrayList<ChiTietPhieuDTO> result = new ArrayList<>();
//        for (ChiTietPhieuDTO i : arr) {
//            result.add(i);
//        }
//        return result;
//    }

    public boolean add(PhieuNhapDTO phieu, ArrayList<ChiTietPhieuNhapDTO> ctPhieu) {
        boolean check = phieunhapDAO.insert(phieu) != 0;
        if (check) {
            check = ctPhieuNhapDAO.insert(ctPhieu) != 0;
        }
        return check;
    }

    public ChiTietPhieuNhapDTO findCT(ArrayList<ChiTietPhieuNhapDTO> ctphieu, int mapb) {
        ChiTietPhieuNhapDTO p = null;
        int i = 0;
        while (i < ctphieu.size() && p == null) {
            if (ctphieu.get(i).getMaSP()== mapb) {
                p = ctphieu.get(i);
            } else {
                i++;
            }
        }
        return p;
    }

    public long getTongTien(ArrayList<ChiTietPhieuNhapDTO> ctphieu) {
        long result = 0;
        for (ChiTietPhieuNhapDTO item : ctphieu) {
            result += item.getDonGia()* item.getSoLuong();
        }
        return result;
    }

    public ArrayList<PhieuNhapDTO> fillerPhieuNhap(int type, String input, int mancc, int manv, Date time_s, Date time_e, String price_minnn, String price_maxxx) {
        Long price_min = !price_minnn.equals("") ? Long.valueOf(price_minnn) : 0L;
        Long price_max = !price_maxxx.equals("") ? Long.valueOf(price_maxxx) : Long.MAX_VALUE;
        Timestamp time_start = new Timestamp(time_s.getTime());
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time_e.getTime());
        // Đặt giá trị cho giờ, phút, giây và mili giây của Calendar
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        
        Timestamp time_end = new Timestamp(calendar.getTimeInMillis());
        ArrayList<PhieuNhapDTO> result = new ArrayList<>();
        for (PhieuNhapDTO phieuNhap : getAllList()) {
            boolean match = false;
            switch (type) {
                case 0 -> {
                    if (Integer.toString(phieuNhap.getMaPN()).contains(input)
                            || nccBUS.getTenNhaCungCap(phieuNhap.getMaNCC()).toLowerCase().contains(input)
                            || nvBUS.getNameById(phieuNhap.getMaNV()).toLowerCase().contains(input)) {
                        match = true;
                    }
                }
                case 1 -> {
                    if (Integer.toString(phieuNhap.getMaPN()).contains(input)) {
                        match = true;
                    }
                }
                case 2 -> {
                    if (nccBUS.getTenNhaCungCap(phieuNhap.getMaNCC()).toLowerCase().contains(input)) {
                        match = true;
                    }
                }
                case 3 -> {
                    if (nvBUS.getNameById(phieuNhap.getMaNV()).toLowerCase().contains(input)) {
                        match = true;
                    }
                }
            }

            if (match
                    && (manv == 0 || phieuNhap.getMaNV()== manv) && (mancc == 0 || phieuNhap.getMaNCC()== mancc)
                    && (phieuNhap.getNgayLap().compareTo(time_start) >= 0)
                    && (phieuNhap.getNgayLap().compareTo(time_end) <= 0)
                    && phieuNhap.getThanhTienPN()>= price_min
                    && phieuNhap.getThanhTienPN()<= price_max) {
                result.add(phieuNhap);
            }
        }
        return result;
    }
    //thay doi trang thai PN = 0
    
    public int cancelPhieuNhap(int maphieu) {
        return phieunhapDAO.delete(maphieu);
    }
    
    public int submitDaNhanHang(int maphieu) {
        return phieunhapDAO.submitDaNhanHang(maphieu);
    }
    
    public ArrayList<ChiTietPhieuNhapDTO> getChiTietPhieu_Type(int maphieunhap) {
        ArrayList<ChiTietPhieuNhapDTO> arr = ctPhieuNhapDAO.selectAll(maphieunhap);
        ArrayList<ChiTietPhieuNhapDTO> result = new ArrayList<>();
        for (ChiTietPhieuNhapDTO i : arr) {
            result.add(i);
        }
        return result;
    }

}
