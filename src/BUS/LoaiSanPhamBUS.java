/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.LoaiSanPhamDAO;
import DAO.NhaCungCapDAO;
import DTO.LoaiSanPhamDTO;
import DTO.NhaCungCapDTO;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class LoaiSanPhamBUS {
    private final LoaiSanPhamDAO lspDAO = new LoaiSanPhamDAO();
    private ArrayList<LoaiSanPhamDTO> listLSP = new ArrayList<>();

    public LoaiSanPhamBUS() {
        this.listLSP = lspDAO.selectAll();
    }

    public ArrayList<LoaiSanPhamDTO> getAll() {
        return this.listLSP;
    }

    public LoaiSanPhamDTO getByIndex(int index) {
        return this.listLSP.get(index);
    }

    public boolean add(LoaiSanPhamDTO lsp) {
        boolean check = lspDAO.insert(lsp) != 0;
        if (check) {
            this.listLSP.add(lsp);
        }
        return check;
    }

    public boolean delete(LoaiSanPhamDTO lsp, int index) {
        boolean check = lspDAO.delete(lsp.getMaLoai()) != 0;
        if (check) {
            this.listLSP.remove(index);
        }
        return check;
    }

    public boolean update(LoaiSanPhamDTO lsp) {
        boolean check = lspDAO.update(lsp) != 0;
        if (check) {
            this.listLSP.set(getIndexByMaLoai(lsp.getMaLoai()), lsp);
        }
        return check;
    }

    public int getIndexByMaLoai(int maLoai) {
        int i = 0;
        int vitri = -1;
        while (i < this.listLSP.size() && vitri == -1) {
            if (listLSP.get(i).getMaLoai() == maLoai) {
                vitri = i;
            } else {
                i++;
            }
        }
        return vitri;
    }

//    public ArrayList<NhaCungCapDTO> search(String txt, String type) {
//        ArrayList<NhaCungCapDTO> result = new ArrayList<>();
//        txt = txt.toLowerCase();
//        switch (type) {
//            case "Tất cả" -> {
//                for (NhaCungCapDTO i : listNcc) {
//                    if (Integer.toString(i.getMaNCC()).contains(txt) || i.getTenNCC().contains(txt) || i.getDiaChi().contains(txt) || i.getEmail().contains(txt) || i.getSdt().contains(txt)) {
//                        result.add(i);
//                    }
//                }
//            }
//            case "Mã nhà cung cấp" -> {
//                for (NhaCungCapDTO i : listNcc) {
//                    if (Integer.toString(i.getMaNCC()).contains(txt)) {
//                        result.add(i);
//                    }
//                }
//            }
//            case "Tên nhà cung cấp" -> {
//                for (NhaCungCapDTO i : listNcc) {
//                    if (i.getTenNCC().toLowerCase().contains(txt)) {
//                        result.add(i);
//                    }
//                }
//            }
//            case "Địa chỉ" -> {
//                for (NhaCungCapDTO i : listNcc) {
//                    if (i.getDiaChi().toLowerCase().contains(txt)) {
//                        result.add(i);
//                    }
//                }
//            }
//            case "Số điện thoại" -> {
//                for (NhaCungCapDTO i : listNcc) {
//                    if (i.getSdt().toLowerCase().contains(txt)) {
//                        result.add(i);
//                    }
//                }
//            }
//            case "Email" -> {
//                for (NhaCungCapDTO i : listNcc) {
//                    if (i.getEmail().toLowerCase().contains(txt)) {
//                        result.add(i);
//                    }
//                }
//            }
//        }
//        return result;
//    }

    public String[] getArrTenLoaiSanPham() {
        int size = listLSP.size();
        String[] result = new String[size];
        for (int i = 0; i < size; i++) {
            result[i] = listLSP.get(i).getTenLoai();
        }
        return result;
    }
    public int getIDByTenLoai(String tenLoai) {
        int id = 0;
        int length = this.listLSP.size();
        for(int i = 0; i < length; i ++) {
            if(listLSP.get(i).getTenLoai().equals(tenLoai)) {
                id = listLSP.get(i).getMaLoai();
            }
        }
        return id;
    }
    public int getIndexByMaLSP(int malsp) {
        int i = 0;
        int vitri = -1;
        while (i < this.listLSP.size() && vitri == -1) {
            if (listLSP.get(i).getMaLoai()== malsp) {
                vitri = i;
            } else {
                i++;
            }
        }
        return vitri;
    }

    public String getTenLoaiSanPham(int maloai) {
        return this.listLSP.get(getIndexByMaLoai(maloai)).getTenLoai();
    }

//    public NhaCungCapDTO findCT(ArrayList<NhaCungCapDTO> ncc, String tenncc) {
//        NhaCungCapDTO p = null;
//        int i = 0;
//        while (i < ncc.size() && p == null) {
//            if (ncc.get(i).getTenNCC().equals(tenncc)) {
//                p = ncc.get(i);
//            } else {
//                i++;
//            }
//        }
//        return p;
//    }
}
