/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.SanPhamDTO;
import config.JDBCConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class SanPhamDAO implements DAOinterface<SanPhamDTO>{
    
    public static SanPhamDAO getInstance() {
        return new SanPhamDAO();
    }

    @Override
    public int insert(SanPhamDTO sp) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCConnection.getConnection();
            String sql = "INSERT INTO SanPham (TenSP, XuatXu, HSD, GiaNhap, GiaXuat, SoLuong, HinhAnh, DonViTinh, MaLoai, MaNCC, TrangThai) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);            
            pst.setString(1, sp.getTenSP());
            pst.setString(2, sp.getXuatXu());
            pst.setDate(3, (Date) sp.getHsd());
            pst.setDouble(4, sp.getGiaNhap());
            pst.setDouble(5, sp.getGiaXuat());
            pst.setInt(6,sp.getSoLuong());
            pst.setString(7,sp.getHinhAnh());
            pst.setString(8,sp.getDonViTinh());
            pst.setInt(9,sp.getMaLoai());
            pst.setInt(10,sp.getMaNCC());
            pst.setInt(11,1);
            result = pst.executeUpdate();
            JDBCConnection.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int update(SanPhamDTO sp) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCConnection.getConnection();
            String sql = "UPDATE SanPham SET TenSP = ?, XuatXu = ?, HSD = ?, GiaNhap = ?, GiaXuat = ?, SoLuong = ?, HinhAnh = ?, DonViTinh = ?, MaLoai = ?, MaNCC = ? WHERE MaSP = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, sp.getTenSP());
            pst.setString(2, sp.getXuatXu());
            pst.setDate(3, (Date) sp.getHsd());
            pst.setDouble(4, sp.getGiaNhap());
            pst.setDouble(5, sp.getGiaXuat());
            pst.setInt(6,sp.getSoLuong());
            pst.setString(7,sp.getHinhAnh());
            pst.setString(8,sp.getDonViTinh());
            pst.setInt(9,sp.getMaLoai());
            pst.setInt(10,sp.getMaNCC());
            //pst.setInt(11,1);
            pst.setInt(11,sp.getMaSP());
            result = pst.executeUpdate();
            JDBCConnection.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int delete(int maSP) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCConnection.getConnection();
            String sql = "UPDATE SanPham SET TrangThai = 0 WHERE MaSP = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, maSP);
            result = pst.executeUpdate();
            JDBCConnection.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public ArrayList<SanPhamDTO> selectAll() {
        ArrayList<SanPhamDTO> result = new ArrayList<SanPhamDTO>();
        try {
            //(TenSP, XuatXu, HSD, GiaNhap, GiaXuat, SoLuong, HinhAnh, DonViTinh, MaLoai, MaNCC, TrangThai)
            Connection con = (Connection) JDBCConnection.getConnection();
            String sql = "SELECT*FROM SANPHAM WHERE TRANGTHAI = 1";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int MaSP = rs.getInt("MaSP");
                String TenSP = rs.getString("TenSP");
                String XuatXu = rs.getString("XuatXu");
                Date HSD = rs.getDate("HSD");
                Double GiaNhap = rs.getDouble("GiaNhap");
                Double GiaXuat = rs.getDouble("GiaXuat");
                int SoLuong = rs.getInt("SoLuong");
                String HinhAnh = rs.getString("HinhAnh");
                String DonViTinh = rs.getString("DonViTinh");                
                int MaLoai = rs.getInt("MaLoai");
                int MaNCC = rs.getInt("MaNCC");
                int TrangThai = rs.getInt("TrangThai");
                SanPhamDTO sp = new SanPhamDTO(MaSP, TenSP, XuatXu, HSD, GiaNhap, GiaXuat, SoLuong, HinhAnh, DonViTinh, MaLoai, MaNCC, TrangThai);
                result.add(sp);
            }
            JDBCConnection.closeConnection(con);
        } catch (Exception e) {
        }
        return result;
    }

    @Override
    public SanPhamDTO selectById(int maSP) {
        SanPhamDTO result = null;
        try {
            Connection con = (Connection) JDBCConnection.getConnection();
            String sql = "SELECT * FROM SANPHAM WHERE MASP=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, maSP);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int MaSP = rs.getInt("MaSP");
                String TenSP = rs.getString("TenSP");
                String XuatXu = rs.getString("XuatXu");
                Date HSD = rs.getDate("HSD");
                Double GiaNhap = rs.getDouble("GiaNhap");
                Double GiaXuat = rs.getDouble("GiaXuat");
                int SoLuong = rs.getInt("SoLuong");
                String HinhAnh = rs.getString("HinhAnh");
                String DonViTinh = rs.getString("DonViTinh");                
                int MaLoai = rs.getInt("MaLoai");
                int MaNCC = rs.getInt("MaNCC");
                int TrangThai = rs.getInt("TrangThai");                
                result = new SanPhamDTO(MaSP, TenSP, XuatXu, HSD, GiaNhap, GiaXuat, SoLuong, HinhAnh, DonViTinh, MaLoai, MaNCC, TrangThai);
            }
            JDBCConnection.closeConnection(con);
        } catch (SQLException e) {
        }
        return result;
    }
    
    // HÀM UPDATE SỐ LƯỢNG TỒN CỦA SẢN PHẨM KHI NHẬP HÀNG
    public int updateSoLuongTonKhiNhapHang(int masp, int soluong) {
        int result = 0;
        int quantity_current = this.selectById(masp).getSoLuong();        
        int quantity_change = quantity_current + soluong;
        try {
            Connection con = (Connection) JDBCConnection.getConnection();
            String sql = "UPDATE SanPham SET SoLuong=? WHERE MaSP = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, quantity_change);
            pst.setInt(2, masp);
            result = pst.executeUpdate();
            JDBCConnection.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    // HÀM UPDATE SỐ LƯỢNG TỒN CỦA SẢN PHẨM KHI ĐƯỢC BÁN ĐI
    public int updateSoLuongTonKhiBan(int masp) {
        int result = 0;
        int quantity_current = this.selectById(masp).getSoLuong();        
        int quantity_change = quantity_current - 1;
        try {
            Connection con = (Connection) JDBCConnection.getConnection();
            String sql = "UPDATE SanPham SET SoLuong=? WHERE MaSP = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, quantity_change);
            pst.setInt(2, masp);
            result = pst.executeUpdate();
            JDBCConnection.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
