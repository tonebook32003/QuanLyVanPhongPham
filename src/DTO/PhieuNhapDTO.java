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
public class PhieuNhapDTO {
    private int maPN;
    private Date ngayLap;
    private double thanhTienPN;
    private int maNCC;
    private int maNV;
    private int trangThai;


    public PhieuNhapDTO() {
    }

    public PhieuNhapDTO(int maPN, Date ngayLap, double thanhTienPN, int maNCC, int maNV, int trangThai) {
        this.maPN = maPN;
        this.ngayLap = ngayLap;
        this.thanhTienPN = thanhTienPN;
        this.maNCC = maNCC;
        this.maNV = maNV;
        this.trangThai = trangThai;
    }
    public PhieuNhapDTO(Date ngayLap, double thanhTienPN, int maNCC, int maNV, int trangThai) {
        this.ngayLap = ngayLap;
        this.thanhTienPN = thanhTienPN;
        this.maNCC = maNCC;
        this.maNV = maNV;
        this.trangThai = trangThai;
    }

    public int getMaPN() {
        return maPN;
    }

    public void setMaPN(int maPN) {
        this.maPN = maPN;
    }

    public Date getNgayLap() {
        return ngayLap;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public void setNgayLap(Date ngayLap) {
        this.ngayLap = ngayLap;
    }

    public double getThanhTienPN() {
        return thanhTienPN;
    }

    public void setThanhTienPN(double thanhTienPN) {
        this.thanhTienPN = thanhTienPN;
    }

    public int getMaNCC() {
        return maNCC;
    }

    public void setMaNCC(int maNCC) {
        this.maNCC = maNCC;
    }

    public int getMaNV() {
        return maNV;
    }

    public void setMaNV(int maNV) {
        this.maNV = maNV;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + this.maPN;
        hash = 59 * hash + Objects.hashCode(this.ngayLap);
        hash = 59 * hash + (int) (Double.doubleToLongBits(this.thanhTienPN) ^ (Double.doubleToLongBits(this.thanhTienPN) >>> 32));
        hash = 59 * hash + this.maNCC;
        hash = 59 * hash + this.maNV;
        hash = 59 * hash + this.trangThai;
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
        final PhieuNhapDTO other = (PhieuNhapDTO) obj;
        if (this.maPN != other.maPN) {
            return false;
        }
        if (Double.doubleToLongBits(this.thanhTienPN) != Double.doubleToLongBits(other.thanhTienPN)) {
            return false;
        }
        if (this.maNCC != other.maNCC) {
            return false;
        }
        if (this.maNV != other.maNV) {
            return false;
        }
        if (this.trangThai != other.trangThai) {
            return false;
        }
        return Objects.equals(this.ngayLap, other.ngayLap);
    }

    @Override
    public String toString() {
        return "PhieuNhapDTO{" + "maPN=" + maPN + ", ngayLap=" + ngayLap + ", thanhTienPN=" + thanhTienPN + ", maNCC=" + maNCC + ", maNV=" + maNV + ", trangThai=" + trangThai + '}';
    }

}
