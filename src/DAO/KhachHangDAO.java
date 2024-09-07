/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DAO.DAOinterface;
import DTO.KhachHangDTO;
import DTO.LoaiSanPhamDTO;
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
public class KhachHangDAO implements DAOinterface<KhachHangDTO>{
    
    public static KhachHangDAO getInstance(){
        return new KhachHangDAO();
    }

    @Override
    public int insert(KhachHangDTO kh) {
        int result = 0 ;
        try {
            Connection con = (Connection) JDBCConnection.getConnection();
            String sql = "INSERT INTO KhachHang (TenKH, DiaChi, SDT, Email, TrangThai) VALUES (?,?,?,?,?)";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, kh.getTenKH());
            pst.setString(2, kh.getDiaChi());
            pst.setString(3, (kh.getSdt()));
            pst.setString(4, kh.getEmail());
            pst.setInt(5, 1);   
           
            result = pst.executeUpdate();
            JDBCConnection.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int update(KhachHangDTO kh) {
        int result = 0 ;
        try {
            Connection con = (Connection) JDBCConnection.getConnection();
            String sql = "UPDATE KhachHang SET TenKH = ?, DiaChi = ?, SDT = ?, Email = ?, TrangThai = ? WHERE MaKH = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, kh.getTenKH());
            pst.setString(2, kh.getDiaChi());
            pst.setString(3, (kh.getSdt()));
            pst.setString(4, kh.getEmail());
            pst.setInt(5, 1); 
            pst.setInt(6, kh.getMaKH());
            result = pst.executeUpdate();
            JDBCConnection.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int delete(int maKH) {
        int result = 0 ;
        try {
            Connection con = (Connection) JDBCConnection.getConnection();
            String sql = "UPDATE KhachHang SET TrangThai = 0 WHERE MaKH = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, maKH);
            result = pst.executeUpdate();
            JDBCConnection.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public ArrayList<KhachHangDTO> selectAll() {
        ArrayList<KhachHangDTO> result = new ArrayList<KhachHangDTO>();
        try {
            //TenKH, DiaChi, SDT, Email, TrangThai
            Connection con = (Connection) JDBCConnection.getConnection();
            String sql = "SELECT * FROM KhachHang WHERE TrangThai = 1";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                int MaKH = rs.getInt("MaKH");
                String TenKH = rs.getString("TenKH"); 
                String DiaChi = rs.getString("DiaChi");     
                String SDT = rs.getString("SDT");                    
                String Email = rs.getString("Email");
                int TrangThai = rs.getInt("TrangThai");
                KhachHangDTO kh = new KhachHangDTO(MaKH, TenKH, DiaChi, SDT, Email, TrangThai);
                result.add(kh);
            }
            JDBCConnection.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
    
    public ArrayList<KhachHangDTO> selectAllEventDelete() {
        ArrayList<KhachHangDTO> result = new ArrayList<KhachHangDTO>();
        try {
            //TenKH, DiaChi, SDT, Email, TrangThai
            Connection con = (Connection) JDBCConnection.getConnection();
            String sql = "SELECT * FROM KhachHang";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                int MaKH = rs.getInt("MaKH");
                String TenKH = rs.getString("TenKH"); 
                String DiaChi = rs.getString("DiaChi");     
                String SDT = rs.getString("SDT");                    
                String Email = rs.getString("Email");
                int TrangThai = rs.getInt("TrangThai");
                KhachHangDTO kh = new KhachHangDTO(MaKH, TenKH, DiaChi, SDT, Email, TrangThai);
                result.add(kh);
            }
            JDBCConnection.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
    @Override
    public KhachHangDTO selectById(int maKH) {
        KhachHangDTO result = null;
        try {
            Connection con = (Connection) JDBCConnection.getConnection();
            String sql = "SELECT * FROM KhachHang WHERE MaKH = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, maKH);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){                
                int MaKH = rs.getInt("MaKH");
                String TenKH = rs.getString("TenKH"); 
                String DiaChi = rs.getString("DiaChi");     
                String SDT = rs.getString("SDT");                    
                String Email = rs.getString("Email");
                int TrangThai = rs.getInt("TrangThai");
                result = new KhachHangDTO(MaKH, TenKH, DiaChi, SDT, Email,TrangThai);                
            }
            JDBCConnection.closeConnection(con);
        } catch (Exception e) {
        }
        return result;
    }
    
    public KhachHangDTO selectByEmail(String emailKH) {
        KhachHangDTO result = null;
        try {
            Connection con = (Connection) JDBCConnection.getConnection();
            String sql = "SELECT*FROM KhachHang WHERE Email = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, emailKH);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                int MaKH = rs.getInt("MaKH");
                String TenKH = rs.getString("TenKH"); 
                String DiaChi = rs.getString("DiaChi");     
                String SDT = rs.getString("SDT");                    
                String Email = rs.getString("Email");
                int TrangThai = rs.getInt("TrangThai");
                result = new KhachHangDTO(MaKH, TenKH, DiaChi, SDT, Email,TrangThai);        
            }
            JDBCConnection.closeConnection(con);
        } catch (Exception e) {
        }
        return result;
    }
}
