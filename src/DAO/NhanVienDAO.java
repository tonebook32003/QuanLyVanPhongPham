/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.NhanVienDTO;
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
public class NhanVienDAO implements DAOinterface<NhanVienDTO> {
    
    public static NhanVienDAO getInstance(){
        return new NhanVienDAO();
    }

    @Override
    public int insert(NhanVienDTO nv) {
        int result = 0 ;
        try {
            Connection con = (Connection) JDBCConnection.getConnection();
            String sql = "INSERT INTO NhanVien (TenNV, GioiTinh,NgaySinh, SDT, Email, Password, TrangThai, QuyenTruyCap ) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, nv.getTenNV());
            pst.setString(2, nv.getGioiTinh());
            pst.setDate(3, (Date) (nv.getNgaySinh()));
            pst.setString(4, nv.getSdt());
            pst.setString(5, nv.getEmail());
            pst.setString(6, nv.getPassword());
            pst.setInt(7, nv.getTrangThai());    
            pst.setString(8, nv.getQuyenTruyCap()); 
            result = pst.executeUpdate();
            JDBCConnection.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int update(NhanVienDTO nv) {
        int result = 0 ;
        try {
            Connection con = (Connection) JDBCConnection.getConnection();
            String sql = "UPDATE NhanVien SET TenNV = ?, GioiTinh = ?, NgaySinh = ?, SDT=?, Email = ?, Password = ?, TrangThai = ?, QuyenTruyCap = ? WHERE MaNV=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql); 
            pst.setString(1, nv.getTenNV());
            pst.setString(2, nv.getGioiTinh());            
            pst.setDate(3, (Date) nv.getNgaySinh());
            pst.setString(4, nv.getSdt());
            pst.setString(5, nv.getEmail());
            pst.setString(6, nv.getPassword());
            pst.setInt(7, nv.getTrangThai());      
            pst.setString(8, nv.getQuyenTruyCap()); 
            pst.setInt(9, nv.getMaNV());
            result = pst.executeUpdate();
            JDBCConnection.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int delete(int maNV) {
        int result = 0 ;
        try {
            Connection con = (Connection) JDBCConnection.getConnection();
            String sql = "UPDATE NhanVien SET TrangThai = 0 WHERE MaNV = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, maNV);
            result = pst.executeUpdate();
            JDBCConnection.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public ArrayList<NhanVienDTO> selectAll() {
        ArrayList<NhanVienDTO> result = new ArrayList<NhanVienDTO>();
        try {
            Connection con = (Connection) JDBCConnection.getConnection();
            String sql = "SELECT * FROM NhanVien WHERE TrangThai = 1";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                int MaNV = rs.getInt("MaNV");
                String TenNV = rs.getString("TenNV");
                String GioiTinh = rs.getString("GioiTinh");   
                Date NgaySinh = rs.getDate("NgaySinh");
                String SDT = rs.getString("SDT");     
                String Email = rs.getString("Email");                    
                String Password = rs.getString("Password");
                int TrangThai = rs.getInt("TrangThai");
                String QuyenTruyCap = rs.getString("QuyenTruyCap");
                NhanVienDTO nv = new NhanVienDTO(MaNV, TenNV, GioiTinh, NgaySinh, SDT, Email, Password, TrangThai, QuyenTruyCap);
                result.add(nv);
            }
            JDBCConnection.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
    
    public ArrayList<NhanVienDTO> selectAllEventDelete() {
        ArrayList<NhanVienDTO> result = new ArrayList<NhanVienDTO>();
        try {
            Connection con = (Connection) JDBCConnection.getConnection();
            String sql = "SELECT * FROM NhanVien";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                int MaNV = rs.getInt("MaNV");
                String TenNV = rs.getString("TenNV");
                String GioiTinh = rs.getString("GioiTinh");   
                Date NgaySinh = rs.getDate("NgaySinh");
                String SDT = rs.getString("SDT");     
                String Email = rs.getString("Email");                    
                String Password = rs.getString("Password");
                int TrangThai = rs.getInt("TrangThai");
                String QuyenTruyCap = rs.getString("QuyenTruyCap");
                NhanVienDTO nv = new NhanVienDTO(MaNV, TenNV, GioiTinh, NgaySinh, SDT, Email, Password, TrangThai, QuyenTruyCap);
                result.add(nv);
            }
            JDBCConnection.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
    @Override
    public NhanVienDTO selectById(int t) {
        NhanVienDTO result = null;
        try {
            Connection con = (Connection) JDBCConnection.getConnection();
            String sql = "SELECT * FROM nhanvien WHERE manv=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, t);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){                
                int MaNV = rs.getInt("MaNV");
                String TenNV = rs.getString("TenNV");
                String GioiTinh = rs.getString("GioiTinh");   
                Date NgaySinh = rs.getDate("NgaySinh");
                String SDT = rs.getString("SDT");     
                String Email = rs.getString("Email");                    
                String Password = rs.getString("Password");
                int TrangThai = rs.getInt("TrangThai");
                String QuyenTruyCap = rs.getString("QuyenTruyCap");
                result = new NhanVienDTO(MaNV, TenNV, GioiTinh, NgaySinh, SDT, Email, Password, TrangThai, QuyenTruyCap);                
            }
            JDBCConnection.closeConnection(con);
        } catch (Exception e) {
        }
        return result;
    }
    
    public NhanVienDTO selectByEmail(String t) {
        NhanVienDTO result = null;
        try {
            Connection con = (Connection) JDBCConnection.getConnection();
            String sql = "SELECT * FROM NhanVien WHERE Email=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                int MaNV = rs.getInt("MaNV");
                String TenNV = rs.getString("TenNV");
                String GioiTinh = rs.getString("GioiTinh");   
                Date NgaySinh = rs.getDate("NgaySinh");
                String SDT = rs.getString("SDT");     
                String Email = rs.getString("Email");                    
                String Password = rs.getString("Password");
                int TrangThai = rs.getInt("TrangThai");
                String QuyenTruyCap = rs.getString("QuyenTruyCap");
                result = new NhanVienDTO(MaNV, TenNV, GioiTinh, NgaySinh, SDT, Email, Password, TrangThai, QuyenTruyCap);  
            }
            JDBCConnection.closeConnection(con);
        } catch (Exception e) {
        }
        return result;
    }
}
