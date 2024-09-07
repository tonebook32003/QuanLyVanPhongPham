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
public class ChiTietPhieuNhapDTO{  //extends ChiTietPhieuDTO
    private int maPN;
    private int maSP;
    private int soLuong;
    private double donGia;

    public ChiTietPhieuNhapDTO(int maPN, int maSP, int soLuong, double donGia) {
        this.maPN = maPN;
        this.maSP = maSP;
        this.soLuong = soLuong;
        this.donGia = donGia;
    }

    public ChiTietPhieuNhapDTO() {
    }

    public int getMaPN() {
        return maPN;
    }

    public void setMaPN(int maPN) {
        this.maPN = maPN;
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
        int hash = 7;
        hash = 61 * hash + Objects.hashCode(this.maPN);
        hash = 61 * hash + Objects.hashCode(this.maSP);
        hash = 61 * hash + this.soLuong;
        hash = 61 * hash + (int) (Double.doubleToLongBits(this.donGia) ^ (Double.doubleToLongBits(this.donGia) >>> 32));
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
        final ChiTietPhieuNhapDTO other = (ChiTietPhieuNhapDTO) obj;
        if (this.soLuong != other.soLuong) {
            return false;
        }
        if (Double.doubleToLongBits(this.donGia) != Double.doubleToLongBits(other.donGia)) {
            return false;
        }
        if (!Objects.equals(this.maPN, other.maPN)) {
            return false;
        }
        return Objects.equals(this.maSP, other.maSP);
    }

    @Override
    public String toString() {
        return "ChiTietPhieuNhapDTO{" + "maPN=" + maPN + ", maSP=" + maSP + ", soLuong=" + soLuong + ", donGia=" + donGia + '}';
    }
    
    
}
