/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.ChiTietPhieuNhapDTO;
import DTO.PhieuNhapDTO;
import config.JDBCConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author phamthithuphuong
 */
public class PhieuNhapDAO implements DAOinterface<PhieuNhapDTO> {

    public static PhieuNhapDAO getInstance() {
        return new PhieuNhapDAO();
    }
    
    
    @Override
    public int insert(PhieuNhapDTO t) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCConnection.getConnection();
            String sql = "INSERT INTO PhieuNhap (NgayLap, ThanhTienPN, MaNCC, MaNV, TrangThai) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setDate(1, (java.sql.Date)t.getNgayLap());
            pst.setDouble(2, t.getThanhTienPN());
            pst.setInt(3, t.getMaNCC());
            pst.setInt(4, t.getMaNV());
            pst.setInt(5, t.getTrangThai());
            result = pst.executeUpdate();
            JDBCConnection.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(PhieuNhapDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int update(PhieuNhapDTO t) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCConnection.getConnection();
            String sql = "UPDATE PhieuNhap SET NgayLap = ?, ThanhTienPN = ?, MaNCC = ?, MaNV = ?, TrangThai = ? WHERE MaPN = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setDate(1, (java.sql.Date)t.getNgayLap());
            pst.setDouble(2, t.getThanhTienPN());
            pst.setInt(3, t.getMaNCC());
            pst.setInt(4, t.getMaNV());
            pst.setInt(5, t.getTrangThai());
            pst.setInt(6, t.getMaPN());
            result = pst.executeUpdate();
            JDBCConnection.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(PhieuNhapDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    public int submitDaNhanHang(int maPN) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCConnection.getConnection();
            String sql = "UPDATE PhieuNhap SET TrangThai = ? WHERE MaPN = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            
            pst.setInt(1, 1);
            pst.setInt(2, maPN);
            result = pst.executeUpdate();
            JDBCConnection.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(PhieuNhapDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int delete(int t) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCConnection.getConnection();
            String sql = "UPDATE PhieuNhap SET TrangThai = 0 WHERE MaPN = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, t);
            result = pst.executeUpdate();
            JDBCConnection.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(PhieuNhapDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public ArrayList<PhieuNhapDTO> selectAll() {
        ArrayList<PhieuNhapDTO> result = new ArrayList<>();
        try {
            Connection con = (Connection) JDBCConnection.getConnection();
            String sql = "SELECT * FROM PhieuNhap WHERE TrangThai = 1";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int maPN = rs.getInt("MaPN");
                Date ngayLap = rs.getDate("NgayLap");
                Double thanhTienPN = rs.getDouble("ThanhTienPN");
                int maNCC = rs.getInt("MaNCC");
                int maNV = rs.getInt("MaNV");
                int trangThai = rs.getInt("TrangThai");
                PhieuNhapDTO phieunhap = new PhieuNhapDTO(maPN, ngayLap, thanhTienPN, maNCC, maNV, trangThai);
                result.add(phieunhap);
            }
            JDBCConnection.closeConnection(con);
        } catch (SQLException e) {
        }
        return result;
    }
    public ArrayList<PhieuNhapDTO> selectAllEvenDelete() {
        ArrayList<PhieuNhapDTO> result = new ArrayList<>();
        try {
            Connection con = (Connection) JDBCConnection.getConnection();
            String sql = "SELECT * FROM PhieuNhap";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int maPN = rs.getInt("MaPN");
                Date ngayLap = rs.getDate("NgayLap");
                Double thanhTienPN = rs.getDouble("ThanhTienPN");
                int maNCC = rs.getInt("MaNCC");
                int maNV = rs.getInt("MaNV");
                int trangThai = rs.getInt("TrangThai");
                PhieuNhapDTO phieunhap = new PhieuNhapDTO(maPN, ngayLap, thanhTienPN, maNCC, maNV, trangThai);
                result.add(phieunhap);
            }
            JDBCConnection.closeConnection(con);
        } catch (SQLException e) {
        }
        return result;
    }
    @Override
    public PhieuNhapDTO selectById(int t) {
        PhieuNhapDTO result = null;
        try {
            Connection con = (Connection) JDBCConnection.getConnection();
            String sql = "SELECT * FROM PhieuNhap WHERE MaPN=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, t);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int maPN = rs.getInt("MaPN");
                Date ngayLap = rs.getDate("NgayLap");
                Double thanhTienPN = rs.getDouble("ThanhTienPN");
                int maNCC = rs.getInt("MaNCC");
                int maNV = rs.getInt("MaNV");
                int trangThai = rs.getInt("TrangThai");
                result = new PhieuNhapDTO(maPN, ngayLap, thanhTienPN, maNCC, maNV, trangThai);
            }
            JDBCConnection.closeConnection(con);
        } catch (Exception e) {
        }
        return result;
    }

    public ArrayList<PhieuNhapDTO> statistical(long min, long max) {
        ArrayList<PhieuNhapDTO> result = new ArrayList<>();
        try {
            Connection con = (Connection) JDBCConnection.getConnection();
            String sql = "SELECT * FROM PhieuNhap WHERE ThanhTienPN BETWEEN ? AND ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setLong(1, min);
            pst.setLong(2,max);

            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int maPN = rs.getInt("MaPN");
                Date ngayLap = rs.getDate("NgayLap");
                Double thanhTienPN = rs.getDouble("ThanhTienPN");
                int maNCC = rs.getInt("MaNCC");
                int maNV = rs.getInt("MaNV");
                int trangThai = rs.getInt("TrangThai");
                PhieuNhapDTO phieunhap = new PhieuNhapDTO(maPN, ngayLap, thanhTienPN, maNCC, maNV, trangThai);
                result.add(phieunhap);
            }
            JDBCConnection.closeConnection(con);
        } catch (SQLException e) {
        }
        return result;
    }
    
   
    
    public int cancelPhieuNhap(int maphieu){
        int result = 0;
        try {
            Connection con = (Connection) JDBCConnection.getConnection();
            String sql = "DELETE FROM PhieuNhap WHERE MaPN = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, maphieu);
            result = pst.executeUpdate();
            JDBCConnection.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(PhieuNhapDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public int getAutoIncrement() {
        int result = -1;
        try {
            Connection con = (Connection) JDBCConnection.getConnection();
            String sql = "SELECT IDENT_CURRENT('QL_VanPhongPham.dbo.PhieuNhap') AS AUTO_INCREMENT;";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs2 = pst.executeQuery();
            if (!rs2.isBeforeFirst()) {
                System.out.println("No data");
            } else {
                while (rs2.next()) {
                    result = rs2.getInt("AUTO_INCREMENT");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PhieuNhapDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result + 1;
    }
    
}
