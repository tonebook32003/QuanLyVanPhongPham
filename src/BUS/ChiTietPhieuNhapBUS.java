/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.ChiTietPhieuNhapDAO;
import DAO.DAOinterface;
import DTO.ChiTietPhieuNhapDTO;
import DTO.SanPhamDTO;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author phamthithuphuong
 */
public class ChiTietPhieuNhapBUS{
    private final ChiTietPhieuNhapDAO ctspDAO = new ChiTietPhieuNhapDAO();
    public SanPhamBUS spbus = new SanPhamBUS();
    public ArrayList<ChiTietPhieuNhapDTO> listctsp = new ArrayList<>();

    public ChiTietPhieuNhapBUS() {
    }

    

    public ArrayList<ChiTietPhieuNhapDTO> getAll() {
        return this.listctsp;
    }

    public ChiTietPhieuNhapDTO getByIndex(int index) {
        return this.listctsp.get(index);
    }

//    public ChiTietPhieuNhapDTO getAllCTSPbyMasp(int masp) {
////        ArrayList<SanPhamDTO> list2 = new ArrayList<>();
//        SanPhamDTO list = spbus.getByMaSP(masp);
//        return list;
//    }

    public HashMap<Integer, ArrayList<ChiTietPhieuNhapDTO>> getChiTietSanPhamFromMaPN(int maphieunhap) {
        ArrayList<ChiTietPhieuNhapDTO> chitietsp = ctspDAO.selectAllByMaPhieuNhap(maphieunhap);
        HashMap<Integer, ArrayList<ChiTietPhieuNhapDTO>> result = new HashMap<>();
        for (ChiTietPhieuNhapDTO i : chitietsp) {
            if (result.get(i.getMaSP()) == null) {
                result.put(i.getMaSP(), new ArrayList<>());
            }
        }
        for (ChiTietPhieuNhapDTO i : chitietsp) {
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
