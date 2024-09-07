/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.ChiTietHoaDonDAO;
import DAO.HoaDonDAO;
import DTO.ChiTietHoaDonDTO;
import DTO.ChiTietHoaDonDTO;
import DTO.ChiTietHoaDonDTO;
import DTO.HoaDonDTO;
import DTO.HoaDonDTO;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author tonsa
 */
public class HoaDonBUS {

    public final HoaDonDAO hoaDonDAO = new HoaDonDAO();
    public ArrayList<HoaDonDTO> listHoaDon = new ArrayList<>();
    public final ChiTietHoaDonDAO ctHoaDonDAO = new ChiTietHoaDonDAO();
    KhachHangBUS khBUS = new KhachHangBUS();
    NhanVienBUS nvBUS = new NhanVienBUS();

    public HoaDonBUS() {
        listHoaDon = hoaDonDAO.selectAllEventDelete(); // selectAll
    }

    public ArrayList<HoaDonDTO> getAll() {
        this.listHoaDon = hoaDonDAO.selectAll();
        return this.listHoaDon;
    }

    public ArrayList<HoaDonDTO> getCancelHD() {
        ArrayList<HoaDonDTO> list = new ArrayList<>();
        for(HoaDonDTO hd : this.listHoaDon) {
            if(hd.getTrangThai() == 0) {
                list.add(hd);
            }
        }
        return list;
    }
    public ArrayList<HoaDonDTO> getPaymentHD() {
        ArrayList<HoaDonDTO> list = new ArrayList<>();
        for(HoaDonDTO hd : this.listHoaDon) {
            if(hd.getTrangThai() == 1) {
                list.add(hd);
            }
        }
        return list;
    }
    public HoaDonDTO getByIndex(int index) {
        return this.listHoaDon.get(index);
    }

    public int getIndexByMaHD(int maHoaDon) {
        int i = 0;
        int vitri = -1;
        while (i < this.listHoaDon.size() && vitri == -1) {
            if (listHoaDon.get(i).getMaHD() == maHoaDon) {
                vitri = i;
            } else {
                i++;
            }
        }
        return vitri;
    }

    public boolean add(HoaDonDTO hd, ArrayList<ChiTietHoaDonDTO> ctHD) {
        boolean check = hoaDonDAO.insert(hd) != 0;
        if (check) {
            check = ctHoaDonDAO.insert(ctHD) != 0;
        }
        return check;
    }

    public Boolean delete(HoaDonDTO kh) {
        boolean check = hoaDonDAO.delete(kh.getMaKH()) != 0;
        if (check) {
            this.listHoaDon.remove(getIndexByMaHD(kh.getMaHD()));
        }
        return check;
    }

    public Boolean update(HoaDonDTO hd) {
        boolean check = hoaDonDAO.update(hd) != 0;
        if (check) {
            this.listHoaDon.set(getIndexByMaHD(hd.getMaHD()), hd);
        }
        return check;
    }

    public ArrayList<HoaDonDTO> search(String text, String type) {
        ArrayList<HoaDonDTO> result = new ArrayList<>();
        text = text.toLowerCase();
        switch (type) {
            case "Tất cả" -> {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                for (HoaDonDTO i : this.listHoaDon) {
                    if (Integer.toString(i.getMaKH()).toLowerCase().contains(text)
                            || Double.toString(i.getThanhTienHD()).toLowerCase().contains(text)
                            || Integer.toString(i.getMaHD()).toLowerCase().contains(text)
                            || Integer.toString(i.getMaNV()).toLowerCase().contains(text)
                            || dateFormat.format(i.getNgayLapHD()).toLowerCase().contains(text)
                            || Integer.toString(i.getMaKH()).toLowerCase().contains(text)) {
                        result.add(i);
                    }
                }
            }
            case "Mã Hóa Đơn" -> {
                for (HoaDonDTO i : this.listHoaDon) {
                    if (Integer.toString(i.getMaHD()).toLowerCase().contains(text)) {
                        result.add(i);
                    }
                }
            }
            case "Thành tiền hóa đơn" -> {;
                for (HoaDonDTO i : this.listHoaDon) {
                    if (Double.toString(i.getThanhTienHD()).toLowerCase().contains(text)) {
                        result.add(i);
                    }
                }
            }
            case "Ngày lập hóa đơn" -> {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                for (HoaDonDTO i : this.listHoaDon) {
                    String ngayLapHDString = dateFormat.format(i.getNgayLapHD());
                    if (ngayLapHDString.toLowerCase().contains(text.toLowerCase())) {
                        result.add(i);
                    }
                }
            }

            case "Mã nhân viên" -> {
                for (HoaDonDTO i : this.listHoaDon) {
                    if (Integer.toString(i.getMaNV()).toLowerCase().contains(text)) {
                        result.add(i);
                    }
                }
            }
            case "Mã khách hàng" -> {
                for (HoaDonDTO i : this.listHoaDon) {
                    if (Integer.toString(i.getMaKH()).toLowerCase().contains(text)) {
                        result.add(i);
                    }
                }
            }
        }

        return result;
    }

    public long getTongTien(ArrayList<ChiTietHoaDonDTO> ctphieu) {
        long result = 0;
        for (ChiTietHoaDonDTO item : ctphieu) {
            result += item.getDonGia() * item.getSoLuong();
        }
        return result;
    }

    public ChiTietHoaDonDTO findCT(ArrayList<ChiTietHoaDonDTO> ctphieu, int mapb) {
        ChiTietHoaDonDTO p = null;
        int i = 0;
        while (i < ctphieu.size() && p == null) {
            if (ctphieu.get(i).getMaSP() == mapb) {
                p = ctphieu.get(i);
            } else {
                i++;
            }
        }
        return p;
    }

    public ArrayList<HoaDonDTO> fillerHoaDon(int type, String input, int mancc, int manv, Date time_s, Date time_e, String price_minnn, String price_maxxx) {
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
        ArrayList<HoaDonDTO> result = new ArrayList<>();
        for (HoaDonDTO hoadon : getAll()) {
            boolean match = false;
            switch (type) {
                case 0 -> {
                    if (Integer.toString(hoadon.getMaHD()).contains(input)
                            || khBUS.getTenKhachHang(hoadon.getMaKH()).toLowerCase().contains(input)
                            || nvBUS.getNameById(hoadon.getMaNV()).toLowerCase().contains(input)) {
                        match = true;
                    }
                }
                case 1 -> {
                    if (Integer.toString(hoadon.getMaHD()).contains(input)) {
                        match = true;
                    }
                }
                case 2 -> {
                    if (khBUS.getTenKhachHang(hoadon.getMaKH()).toLowerCase().contains(input)) {
                        match = true;
                    }
                }
                case 3 -> {
                    if (nvBUS.getNameById(hoadon.getMaNV()).toLowerCase().contains(input)) {
                        match = true;
                    }
                }
            }

            if (match
                    && (manv == 0 || hoadon.getMaNV() == manv) && (mancc == 0 || hoadon.getMaKH() == mancc)
                    && (hoadon.getNgayLapHD().compareTo(time_start) >= 0)
                    && (hoadon.getNgayLapHD().compareTo(time_end) <= 0)
                    && hoadon.getThanhTienHD() >= price_min
                    && hoadon.getThanhTienHD() <= price_max) {
                result.add(hoadon);
            }
        }
        return result;
    }

    public ArrayList<ChiTietHoaDonDTO> getChiTietHoaDon_Type(int maphieunhap) {
        ArrayList<ChiTietHoaDonDTO> arr = ctHoaDonDAO.selectAll(maphieunhap);
        ArrayList<ChiTietHoaDonDTO> result = new ArrayList<>();
        for (ChiTietHoaDonDTO i : arr) {
            result.add(i);
        }
        return result;
    }

    public int cancelHoaDon(int hoadon) {
        return hoaDonDAO.delete(hoadon);
    }

}
