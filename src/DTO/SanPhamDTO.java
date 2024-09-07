/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Admin
 */
public class SanPhamDTO {
    private int maSP;
    private String tenSP;
    private String xuatXu;
    private Date hsd;
    private double giaNhap;
    private double giaXuat;
    private int soLuong;
    private String hinhAnh;
    private String donViTinh;
    private int maLoai;
    private int maNCC;
    private int trangThai;

    public SanPhamDTO() {
    }

    public SanPhamDTO(int maSP, String tenSP, String xuatXu, Date hsd, double giaNhap, double giaXuat, int soLuong, String hinhAnh, String donViTinh, int maLoai, int maNCC, int trangThai) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.xuatXu = xuatXu;
        this.hsd = hsd;
        this.giaNhap = giaNhap;
        this.giaXuat = giaXuat;
        this.soLuong = soLuong;
        this.hinhAnh = hinhAnh;
        this.donViTinh = donViTinh;
        this.maLoai = maLoai;
        this.maNCC = maNCC;
        this.trangThai = trangThai;
    }
    public SanPhamDTO(String tenSP, String xuatXu, Date hsd, double giaNhap, double giaXuat, int soLuong, String hinhAnh, String donViTinh, int maLoai, int maNCC, int trangThai) {
        this.tenSP = tenSP;
        this.xuatXu = xuatXu;
        this.hsd = hsd;
        this.giaNhap = giaNhap;
        this.giaXuat = giaXuat;
        this.soLuong = soLuong;
        this.hinhAnh = hinhAnh;
        this.donViTinh = donViTinh;
        this.maLoai = maLoai;
        this.maNCC = maNCC;
        this.trangThai = trangThai;
    }
    public int getMaSP() {
        return maSP;
    }

    public void setMaSP(int maSP) {
        this.maSP = maSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public String getXuatXu() {
        return xuatXu;
    }

    public void setXuatXu(String xuatXu) {
        this.xuatXu = xuatXu;
    }

    public Date getHsd() {
        return hsd;
    }

    public void setHsd(Date hsd) {
        this.hsd = hsd;
    }

    public double getGiaNhap() {
        return giaNhap;
    }

    public void setGiaNhap(double giaNhap) {
        this.giaNhap = giaNhap;
    }

    public double getGiaXuat() {
        return giaXuat;
    }

    public void setGiaXuat(double giaXuat) {
        this.giaXuat = giaXuat;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public String getDonViTinh() {
        return donViTinh;
    }

    public void setDonViTinh(String donViTinh) {
        this.donViTinh = donViTinh;
    }

    public int getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(int maLoai) {
        this.maLoai = maLoai;
    }

    public int getMaNCC() {
        return maNCC;
    }

    public void setMaNCC(int maNCC) {
        this.maNCC = maNCC;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.maSP;
        hash = 97 * hash + Objects.hashCode(this.tenSP);
        hash = 97 * hash + Objects.hashCode(this.xuatXu);
        hash = 97 * hash + Objects.hashCode(this.hsd);
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.giaNhap) ^ (Double.doubleToLongBits(this.giaNhap) >>> 32));
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.giaXuat) ^ (Double.doubleToLongBits(this.giaXuat) >>> 32));
        hash = 97 * hash + this.soLuong;
        hash = 97 * hash + Objects.hashCode(this.hinhAnh);
        hash = 97 * hash + Objects.hashCode(this.donViTinh);
        hash = 97 * hash + this.maLoai;
        hash = 97 * hash + this.maNCC;
        hash = 97 * hash + this.trangThai;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SanPhamDTO other = (SanPhamDTO) obj;
        if (this.maSP != other.maSP) {
            return false;
        }
        if (Double.doubleToLongBits(this.giaNhap) != Double.doubleToLongBits(other.giaNhap)) {
            return false;
        }
        if (Double.doubleToLongBits(this.giaXuat) != Double.doubleToLongBits(other.giaXuat)) {
            return false;
        }
        if (this.soLuong != other.soLuong) {
            return false;
        }
        if (this.maLoai != other.maLoai) {
            return false;
        }
        if (this.maNCC != other.maNCC) {
            return false;
        }
        if (this.trangThai != other.trangThai) {
            return false;
        }
        if (!Objects.equals(this.tenSP, other.tenSP)) {
            return false;
        }
        if (!Objects.equals(this.xuatXu, other.xuatXu)) {
            return false;
        }
        if (!Objects.equals(this.hinhAnh, other.hinhAnh)) {
            return false;
        }
        if (!Objects.equals(this.donViTinh, other.donViTinh)) {
            return false;
        }
        return Objects.equals(this.hsd, other.hsd);
    }

    @Override
    public String toString() {
        return "SanPhamDTO{" + "maSP=" + maSP + ", tenSP=" + tenSP + ", xuatXu=" + xuatXu + ", hsd=" + hsd + ", giaNhap=" + giaNhap + ", giaXuat=" + giaXuat + ", soLuong=" + soLuong + ", hinhAnh=" + hinhAnh + ", donViTinh=" + donViTinh + ", maLoai=" + maLoai + ", maNCC=" + maNCC + ", trangThai=" + trangThai + '}';
    }   

}

