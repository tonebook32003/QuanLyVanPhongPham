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
public class NhanVienDTO {
    private int maNV;
    private String tenNV;
    private String gioiTinh;
    private Date ngaySinh;
    private String sdt;
    private String email;
    private String password;
    private int trangThai;
    private String quyenTruyCap;

    public NhanVienDTO() {
    }

    public NhanVienDTO(int maNV, String tenNV, String gioiTinh, Date ngaySinh, String sdt, String email, String password, int trangThai, String quyenTruyCap) {
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
        this.sdt = sdt;
        this.email = email;
        this.password = password;
        this.trangThai = trangThai;
        this.quyenTruyCap = quyenTruyCap;
    }
    public NhanVienDTO(String tenNV, String gioiTinh, Date ngaySinh, String sdt, String email, String password, int trangThai, String quyenTruyCap) {       
        this.tenNV = tenNV;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
        this.sdt = sdt;
        this.email = email;
        this.password = password;
        this.trangThai = trangThai;
        this.quyenTruyCap = quyenTruyCap;
    }

    public int getMaNV() {
        return maNV;
    }

    public void setMaNV(int maNV) {
        this.maNV = maNV;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public String getQuyenTruyCap() {
        return quyenTruyCap;
    }

    public void setQuyenTruyCap(String quyenTruyCap) {
        this.quyenTruyCap = quyenTruyCap;
    }    

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + this.maNV;
        hash = 53 * hash + Objects.hashCode(this.tenNV);
        hash = 53 * hash + Objects.hashCode(this.gioiTinh);
        hash = 53 * hash + Objects.hashCode(this.ngaySinh);
        hash = 53 * hash + Objects.hashCode(this.sdt);
        hash = 53 * hash + Objects.hashCode(this.email);
        hash = 53 * hash + Objects.hashCode(this.password);
        hash = 53 * hash + this.trangThai;
        hash = 53 * hash + Objects.hashCode(this.quyenTruyCap);
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
        final NhanVienDTO other = (NhanVienDTO) obj;
        if (this.maNV != other.maNV) {
            return false;
        }
        if (this.trangThai != other.trangThai) {
            return false;
        }
        if (!Objects.equals(this.tenNV, other.tenNV)) {
            return false;
        }
        if (!Objects.equals(this.gioiTinh, other.gioiTinh)) {
            return false;
        }
        if (!Objects.equals(this.sdt, other.sdt)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        if (!Objects.equals(this.quyenTruyCap, other.quyenTruyCap)) {
            return false;
        }
        return Objects.equals(this.ngaySinh, other.ngaySinh);
    }

    @Override
    public String toString() {
        return "NhanVienDTO{" + "maNV=" + maNV + ", tenNV=" + tenNV + ", gioiTinh=" + gioiTinh + ", ngaySinh=" + ngaySinh + ", sdt=" + sdt + ", email=" + email + ", password=" + password + ", trangThai=" + trangThai + ", quyenTruyCap=" + quyenTruyCap + '}';
    }

}
