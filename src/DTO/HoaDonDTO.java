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
public class HoaDonDTO {

    private int maHD;
    private double thanhTienHD;
    private Date ngayLapHD;
    private int maNV;
    private int maKH;
    private int trangThai;

    public HoaDonDTO() {
    }

    public HoaDonDTO(int maHD, double thanhTienHD, Date ngayLapHD, int maNV, int maKH, int trangThai) {
        this.maHD = maHD;
        this.thanhTienHD = thanhTienHD;
        this.ngayLapHD = ngayLapHD;
        this.maNV = maNV;
        this.maKH = maKH;
        this.trangThai = trangThai;
    }

    public HoaDonDTO(double thanhTienHD, Date ngayLapHD, int maNV, int maKH, int trangThai) {
        this.thanhTienHD = thanhTienHD;
        this.ngayLapHD = ngayLapHD;
        this.maNV = maNV;
        this.maKH = maKH;
        this.trangThai = trangThai;
    }

    public int getMaHD() {
        return maHD;
    }

    public void setMaHD(int maHD) {
        this.maHD = maHD;
    }

    public double getThanhTienHD() {
        return thanhTienHD;
    }

    public void setThanhTienHD(double thanhTienHD) {
        this.thanhTienHD = thanhTienHD;
    }

    public Date getNgayLapHD() {
        return ngayLapHD;
    }

    public void setNgayLapHD(Date ngayLapHD) {
        this.ngayLapHD = ngayLapHD;
    }

    public int getMaNV() {
        return maNV;
    }

    public void setMaNV(int maNV) {
        this.maNV = maNV;
    }

    public int getMaKH() {
        return maKH;
    }

    public void setMaKH(int maKH) {
        this.maKH = maKH;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 73 * hash + Objects.hashCode(this.maHD);
        hash = 73 * hash + (int) (Double.doubleToLongBits(this.thanhTienHD) ^ (Double.doubleToLongBits(this.thanhTienHD) >>> 32));
        hash = 73 * hash + Objects.hashCode(this.ngayLapHD);
        hash = 73 * hash + Objects.hashCode(this.maNV);
        hash = 73 * hash + Objects.hashCode(this.maKH);
        hash = 73 * hash + this.trangThai;
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
        final HoaDonDTO other = (HoaDonDTO) obj;
        if (Double.doubleToLongBits(this.thanhTienHD) != Double.doubleToLongBits(other.thanhTienHD)) {
            return false;
        }
        if (!Objects.equals(this.maHD, other.maHD)) {
            return false;
        }
        if (!Objects.equals(this.maNV, other.maNV)) {
            return false;
        }
        if (!Objects.equals(this.maKH, other.maKH)) {
            return false;
        }
        if (this.trangThai != other.trangThai) {
            return false;
        }
        return Objects.equals(this.ngayLapHD, other.ngayLapHD);
    }

    @Override
    public String toString() {
        return "HoaDonDTO{" + "maHD=" + maHD + ", thanhTienHD=" + thanhTienHD + ", ngayLapHD=" + ngayLapHD + ", maNV=" + maNV + ", maKH=" + maKH + ", trangThai" + trangThai + '}';
    }

}
