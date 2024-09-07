/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.ChiTietHoaDonDAO;
import DTO.ChiTietHoaDonDTO;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author tonsa
 */
public class ChiTietHoaDonBUS {

    private final ChiTietHoaDonDAO ctspDAO = new ChiTietHoaDonDAO();
    public SanPhamBUS spbus = new SanPhamBUS();
    public ArrayList<ChiTietHoaDonDTO> listctsp = new ArrayList<>();

    public ChiTietHoaDonBUS() {
    }

    public ArrayList<ChiTietHoaDonDTO> getAll() {
        return this.listctsp;
    }

    public ChiTietHoaDonDTO getByIndex(int index) {
        return this.listctsp.get(index);
    }

//    public ChiTietHoaDonDTO getAllCTSPbyMasp(int masp) {
////        ArrayList<SanPhamDTO> list2 = new ArrayList<>();
//        SanPhamDTO list = spbus.getByMaSP(masp);
//        return list;
//    }
    public HashMap<Integer, ArrayList<ChiTietHoaDonDTO>> getChiTietSanPhamFromMaHD(int mahoadon) {
        ArrayList<ChiTietHoaDonDTO> chitiethd = ctspDAO.selectAllByMaHoaDon(mahoadon);
        HashMap<Integer, ArrayList<ChiTietHoaDonDTO>> result = new HashMap<>();
        for (ChiTietHoaDonDTO i : chitiethd) {
            if (result.get(i.getMaSP()) == null) {
                result.put(i.getMaSP(), new ArrayList<>());
            }
        }
        for (ChiTietHoaDonDTO i : chitiethd) {
            result.get(i.getMaSP()).add(i);
        }
        return result;
    }

//    public void Show(ArrayList<ChiTietSanPhamDTO> x) {
//        for (ChiTietSanPhamDTO a : x) {
//            System.out.println(a.getImei());
//        }
//    }
//    public void updateXuat(ArrayList<ChiTietSanPhamDTO> ct) {
//        for (ChiTietSanPhamDTO chiTietSanPhamDTO : ct) {
//            ctspDAO.updateXuat(chiTietSanPhamDTO);
//        }
//    }
//    public ArrayList<ChiTietSanPhamDTO> FilterPBvaTT(String text,int masp, int phienban, int tinhtrang) {
//        ArrayList<ChiTietSanPhamDTO> list = this.getAllCTSPbyMasp(masp);
//        ArrayList<ChiTietSanPhamDTO> result = new ArrayList<>();
//        for (ChiTietSanPhamDTO i : list) {
//            if(i.getMaphienbansp() == phienban && i.getTinhtrang() == tinhtrang && i.getImei().contains(text))
//                result.add(i);
//        }
//        return result;
//    }
//    public ArrayList<ChiTietSanPhamDTO> FilterPBvaAll(String text,int masp, int phienban) {
//        ArrayList<ChiTietSanPhamDTO> list = this.getAllCTSPbyMasp(masp);
//        ArrayList<ChiTietSanPhamDTO> result = new ArrayList<>();
//        for (ChiTietSanPhamDTO i : list) {
//            if(i.getMaphienbansp() == phienban && i.getImei().contains(text))
//                result.add(i);
//        }
//        return result;
//    }
}
