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
public class ChiTietHoaDonDTO {
    private int maHD;
    private int maSP;
    private int soLuong;
    private double donGia;

    public ChiTietHoaDonDTO(int maHD, int maSP, int soLuong, double donGia) {
        this.maHD = maHD;
        this.maSP = maSP;
        this.soLuong = soLuong;
        this.donGia = donGia;
    }

    public int getMaHD() {
        return maHD;
    }

    public void setMaHD(int maHD) {
        this.maHD = maHD;
    }

    public int getMaSP() {
        return maSP;
    }

    public void setMaSP(int maSP) {
        this.maSP = maSP;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 19 * hash + Objects.hashCode(this.maHD);
        hash = 19 * hash + Objects.hashCode(this.maSP);
        hash = 19 * hash + this.soLuong;
        hash = 19 * hash + (int) (Double.doubleToLongBits(this.donGia) ^ (Double.doubleToLongBits(this.donGia) >>> 32));
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
        final ChiTietHoaDonDTO other = (ChiTietHoaDonDTO) obj;
        if (this.soLuong != other.soLuong) {
            return false;
        }
        if (Double.doubleToLongBits(this.donGia) != Double.doubleToLongBits(other.donGia)) {
            return false;
        }
        if (!Objects.equals(this.maHD, other.maHD)) {
            return false;
        }
        return Objects.equals(this.maSP, other.maSP);
    }

    @Override
    public String toString() {
        return "ChiTietHoaDonDTO{" + "maHD=" + maHD + ", maSP=" + maSP + ", soLuong=" + soLuong + ", donGia=" + donGia + '}';
    }
    
    
}
