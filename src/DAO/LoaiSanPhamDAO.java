/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.LoaiSanPhamDTO;
import DTO.NhaCungCapDTO;
import config.JDBCConnection;
import java.sql.Connection;
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
public class LoaiSanPhamDAO implements DAOinterface<LoaiSanPhamDTO>{

   public static LoaiSanPhamDAO getInstance() {
        return new LoaiSanPhamDAO();
    }

    @Override
    public int insert(LoaiSanPhamDTO lsp) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCConnection.getConnection();
            String sql = "INSERT INTO LoaiSanPham (TenLoai, TrangThai) VALUES(?,?)";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);            
            pst.setString(1, lsp.getTenLoai());            
            pst.setInt(2, lsp.getTrangThai());
            result = pst.executeUpdate();
            JDBCConnection.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int update(LoaiSanPhamDTO lsp) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCConnection.getConnection();
            String sql = "UPDATE LoaiSanPham SET TenLoai = ?, TrangThai = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, lsp.getTenLoai());            
            pst.setInt(2, lsp.getTrangThai());
            result = pst.executeUpdate();
            JDBCConnection.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int delete(int maLoai) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCConnection.getConnection();
            String sql = "UPDATE LoaiSanPham SET TrangThai = 0 WHERE MaLoai = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, maLoai);
            result = pst.executeUpdate();
            JDBCConnection.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public ArrayList<LoaiSanPhamDTO> selectAll() {
        ArrayList<LoaiSanPhamDTO> result = new ArrayList<LoaiSanPhamDTO>();
        try {
            //(TenNCC, DiaChi, Email, SDT, TrangThai)             
            Connection con = (Connection) JDBCConnection.getConnection();
            String sql = "SELECT * FROM LoaiSanPham WHERE TRANGTHAI = 1";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int MaLoai = rs.getInt("MaLoai");
                String TenLoai = rs.getString("TenLoai");                
                int TrangThai = rs.getInt("TrangThai");
                LoaiSanPhamDTO lsp = new LoaiSanPhamDTO(MaLoai, TenLoai, TrangThai);
                result.add(lsp);
            }
            JDBCConnection.closeConnection(con);
        } catch (Exception e) {
        }
        return result;
    }

    @Override
    public LoaiSanPhamDTO selectById(int maLoai) {
        LoaiSanPhamDTO result = null;
        try {
            Connection con = (Connection) JDBCConnection.getConnection();
            String sql = "SELECT*FROM LoaiSanPham WHERE MaLoai = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, maLoai);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int MaLoai = rs.getInt("MaLoai");
                String TenLoai = rs.getString("TenLoai");                
                int TrangThai = rs.getInt("TrangThai");
                result= new LoaiSanPhamDTO(MaLoai, TenLoai, TrangThai);           
            }
            JDBCConnection.closeConnection(con);
        } catch (SQLException e) {
        }
        return result;
    }
    
}
