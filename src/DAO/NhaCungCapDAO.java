/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.NhaCungCapDTO;
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
public class NhaCungCapDAO implements DAOinterface<NhaCungCapDTO>{

    public static NhaCungCapDAO getInstance() {
        return new NhaCungCapDAO();
    }

    @Override
    public int insert(NhaCungCapDTO ncc) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCConnection.getConnection();
            String sql = "INSERT INTO NhaCungCap(TenNCC, DiaChi, Email, SDT, TrangThai) VALUES(?,?,?,?,?)";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);            
            pst.setString(1, ncc.getTenNCC());
            pst.setString(2, ncc.getDiaChi());
            pst.setString(3, ncc.getEmail());
            pst.setString(4, ncc.getSdt());
            pst.setInt(5, ncc.getTrangThai());
            result = pst.executeUpdate();
            JDBCConnection.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int update(NhaCungCapDTO ncc) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCConnection.getConnection();
            String sql = "UPDATE NhaCungCap SET TenNCC = ?, DiaChi = ?, Email = ?, SDT = ?, TrangThai = ? WHERE MaNCC = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, ncc.getTenNCC());
            pst.setString(2, ncc.getDiaChi());
            pst.setString(3, ncc.getEmail());
            pst.setString(4, ncc.getSdt());
            pst.setInt(5, 1);
            pst.setInt(6, ncc.getMaNCC());
            result = pst.executeUpdate();
            JDBCConnection.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int delete(int maNCC) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCConnection.getConnection();
            String sql = "UPDATE NhaCungCap SET TrangThai = 0 WHERE MaNCC = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, maNCC);
            result = pst.executeUpdate();
            JDBCConnection.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public ArrayList<NhaCungCapDTO> selectAll() {
        ArrayList<NhaCungCapDTO> result = new ArrayList<NhaCungCapDTO>();
        try {
            //(TenNCC, DiaChi, Email, SDT, TrangThai)             
            Connection con = (Connection) JDBCConnection.getConnection();
            String sql = "SELECT*FROM NhaCungCap WHERE TRANGTHAI = 1";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int MaNCC = rs.getInt("MaNCC");
                String TenNCC = rs.getString("TenNCC");
                String DiaChi = rs.getString("DiaChi");              
                String Email = rs.getString("Email");
                String SDT = rs.getString("SDT");   
                int TrangThai = rs.getInt("TrangThai");
                NhaCungCapDTO sp = new NhaCungCapDTO(MaNCC, TenNCC, DiaChi, Email, SDT, TrangThai);
                result.add(sp);
            }
            JDBCConnection.closeConnection(con);
        } catch (Exception e) {
        }
        return result;
    }

    @Override
    public NhaCungCapDTO selectById(int maSP) {
        NhaCungCapDTO result = null;
        try {
            Connection con = (Connection) JDBCConnection.getConnection();
            String sql = "SELECT * FROM NhaCungCap WHERE MaNCC=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, maSP);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int MaNCC = rs.getInt("MaNCC");
                String TenNCC = rs.getString("TenNCC");
                String DiaChi = rs.getString("DiaChi");              
                String Email = rs.getString("Email");
                String SDT = rs.getString("SDT");   
                int TrangThai = rs.getInt("TrangThai");
                result = new NhaCungCapDTO(MaNCC, TenNCC, DiaChi, Email, SDT, TrangThai);                
            }
            JDBCConnection.closeConnection(con);
        } catch (SQLException e) {
        }
        return result;
    }
    
}
