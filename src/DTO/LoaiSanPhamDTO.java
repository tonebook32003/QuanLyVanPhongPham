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
public class LoaiSanPhamDTO {
    private int maLoai;
    private String tenLoai;
    private int trangThai;

    public LoaiSanPhamDTO(int maLoai, String tenLoai, int trangThai) {
        this.maLoai = maLoai;
        this.tenLoai = tenLoai;
        this.trangThai = trangThai;
    }
    public LoaiSanPhamDTO(String tenLoai, int trangThai) {
        this.tenLoai = tenLoai;
        this.trangThai = trangThai;
    }
    public LoaiSanPhamDTO() {
    }

    public int getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(int maLoai) {
        this.maLoai = maLoai;
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
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
        hash = 53 * hash + this.maLoai;
        hash = 53 * hash + Objects.hashCode(this.tenLoai);
        hash = 53 * hash + this.trangThai;
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
        final LoaiSanPhamDTO other = (LoaiSanPhamDTO) obj;
        if (this.maLoai != other.maLoai) {
            return false;
        }
        if (this.trangThai != other.trangThai) {
            return false;
        }
        return Objects.equals(this.tenLoai, other.tenLoai);
    }

    @Override
    public String toString() {
        return "LoaiSanPhamDTO{" + "maLoai=" + maLoai + ", tenLoai=" + tenLoai + ", trangThai=" + trangThai + '}';
    }

    
}
