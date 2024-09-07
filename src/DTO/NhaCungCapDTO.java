/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.util.Objects;

/**
 *
 * @author Admin
 */
public class NhaCungCapDTO {
    private int maNCC;
    private String tenNCC;
    private String diaChi;
    private String email;
    private String sdt;
    private int trangThai;

    public NhaCungCapDTO(int maNCC, String tenNCC, String diaChi, String email, String sdt, int trangThai) {
        this.maNCC = maNCC;
        this.tenNCC = tenNCC;
        this.diaChi = diaChi;
        this.email = email;
        this.sdt = sdt;
        this.trangThai = trangThai;
    }
    public NhaCungCapDTO(String tenNCC, String diaChi, String email, String sdt, int trangThai) {        
        this.tenNCC = tenNCC;
        this.diaChi = diaChi;
        this.email = email;
        this.sdt = sdt;
        this.trangThai = trangThai;
    }

    public NhaCungCapDTO() {
    }
    

    public int getMaNCC() {
        return maNCC;
    }

    public void setMaNCC(int maNCC) {
        this.maNCC = maNCC;
    }

    public String getTenNCC() {
        return tenNCC;
    }

    public void setTenNCC(String tenNCC) {
        this.tenNCC = tenNCC;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + this.maNCC;
        hash = 37 * hash + Objects.hashCode(this.tenNCC);
        hash = 37 * hash + Objects.hashCode(this.diaChi);
        hash = 37 * hash + Objects.hashCode(this.email);
        hash = 37 * hash + Objects.hashCode(this.sdt);
        hash = 37 * hash + this.trangThai;
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
        final NhaCungCapDTO other = (NhaCungCapDTO) obj;
        if (this.maNCC != other.maNCC) {
            return false;
        }
        if (this.trangThai != other.trangThai) {
            return false;
        }
        if (!Objects.equals(this.tenNCC, other.tenNCC)) {
            return false;
        }
        if (!Objects.equals(this.diaChi, other.diaChi)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        return Objects.equals(this.sdt, other.sdt);
    }

    @Override
    public String toString() {
        return "NhaCungCapDTO{" + "maNCC=" + maNCC + ", tenNCC=" + tenNCC + ", diaChi=" + diaChi + ", email=" + email + ", sdt=" + sdt + ", trangThai=" + trangThai + '}';
    }

    
}
