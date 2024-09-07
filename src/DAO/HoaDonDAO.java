/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DAO.DAOinterface;
import DTO.HoaDonDTO;
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
 * @author tonsa
 */
public class HoaDonDAO implements DAOinterface<HoaDonDTO> {

    public static HoaDonDAO getInstance() {
        return new HoaDonDAO();
    }

    @Override
    public int insert(HoaDonDTO hd) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCConnection.getConnection();
            String sql = "INSERT INTO HOADON (ThanhTienHD, NgayLapHD, MaNV, MaKH, TrangThai) VALUES (?,?,?,?,?)";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            //pst.setInt(1, hd.getMaHD());
            pst.setDouble(1, hd.getThanhTienHD());
            pst.setDate(2, new java.sql.Date(hd.getNgayLapHD().getTime()));
            pst.setInt(3, hd.getMaNV());
            pst.setInt(4, hd.getMaKH());
            pst.setInt(5, 1);

            result = pst.executeUpdate();
            JDBCConnection.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int update(HoaDonDTO hd) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCConnection.getConnection();
            String sql = "UPDATE HOADON SET ThanhTienHD = ?, NgayLapHD = ?, MaNV = ?, MaKH = ?, TrangThai = ? WHERE MaHD = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);       
            pst.setDouble(1, hd.getThanhTienHD());
            pst.setDate(2, new java.sql.Date(hd.getNgayLapHD().getTime()));
            pst.setInt(3, hd.getMaNV());
            pst.setInt(4, hd.getMaKH());
            pst.setInt(5, 1);
            pst.setInt(6, hd.getMaHD());
            result = pst.executeUpdate();
            JDBCConnection.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int delete(int maHD) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCConnection.getConnection();
            String sql = "UPDATE HOADON SET TrangThai = 0 WHERE MaHD = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, maHD);
            result = pst.executeUpdate();
            JDBCConnection.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public ArrayList<HoaDonDTO> selectAll() {
        ArrayList<HoaDonDTO> result = new ArrayList<HoaDonDTO>();
        try {
            Connection con = (Connection) JDBCConnection.getConnection();
            String sql = "SELECT * FROM HOADON WHERE TrangThai = 1";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int MaHD = rs.getInt("MaHD");
                double ThanhTienHD = rs.getDouble("ThanhTienHD");
                Date NgayLapHD = rs.getDate("NgayLapHD");
                int MaNV = rs.getInt("MaNV");
                int MaKH = rs.getInt("MaKH");
                int TrangThai = rs.getInt("TrangThai");
                HoaDonDTO hd = new HoaDonDTO(MaHD, ThanhTienHD, NgayLapHD, MaNV, MaKH, TrangThai);
                result.add(hd);
            }
        } catch (Exception e) {
        }
        return result;
    }

    public ArrayList<HoaDonDTO> selectAllEventDelete() {
        ArrayList<HoaDonDTO> result = new ArrayList<HoaDonDTO>();
        try {
            //TenKH, DiaChi, SDT, Email, TrangThai
            Connection con = (Connection) JDBCConnection.getConnection();
            String sql = "SELECT * FROM HoaDon";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int MaHD = rs.getInt("MaHD");
                double ThanhTienHD = rs.getDouble("ThanhTienHD");
                Date NgayLapHD = rs.getDate("NgayLapHD");
                int MaNV = rs.getInt("MaNV");
                int MaKH = rs.getInt("MaKH");
                int TrangThai = rs.getInt("TrangThai");

                HoaDonDTO kh = new HoaDonDTO(MaHD, ThanhTienHD, NgayLapHD, MaNV, MaKH, TrangThai);
                result.add(kh);
            }
            JDBCConnection.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public HoaDonDTO selectById(int maHD) {
        HoaDonDTO result = null;
        try {
            Connection con = (Connection) JDBCConnection.getConnection();
            String sql = "SELECT * FROM HoaDon WHERE MaHD = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, maHD);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int MaHD = rs.getInt("MaHD");
                double ThanhTienHD = rs.getDouble("ThanhTienHD");
                Date NgayLapHD = rs.getDate("NgayLapHD");
                int MaNV = rs.getInt("MaNV");
                int MaKH = rs.getInt("MaKH");
                int TrangThai = rs.getInt("TrangThai");
                result = new HoaDonDTO(MaHD,ThanhTienHD,NgayLapHD,MaNV,MaKH,TrangThai);
            }
            JDBCConnection.closeConnection(con);
        } catch (Exception e) {
        }
        return result;
    }

    public int getAutoIncrement() {
        int result = -1;
        try {
            Connection con = (Connection) JDBCConnection.getConnection();
            String sql = "SELECT IDENT_CURRENT('QL_VanPhongPham.dbo.HoaDon') AS AUTO_INCREMENT;";
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
            Logger.getLogger(HoaDonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result + 1;
    }

}
