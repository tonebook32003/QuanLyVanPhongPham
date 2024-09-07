/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.ChiTietHoaDonDTO;
import DTO.ChiTietHoaDonDTO;
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
 * @author tonsa
 */
public class ChiTietHoaDonDAO implements ChiTietInterface<ChiTietHoaDonDTO>{
    
    public static ChiTietHoaDonDAO getInstance() {
        return new ChiTietHoaDonDAO();
    }


    @Override
    public ArrayList<ChiTietHoaDonDTO> selectAll(int t) {
        ArrayList<ChiTietHoaDonDTO> result = new ArrayList<>();
        try {
            Connection con = (Connection) JDBCConnection.getConnection();
            String sql = "SELECT * FROM CTHD WHERE MaHD = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, t);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int maphieu = rs.getInt("MaHD");
                int masp = rs.getInt("MaSP");
                int soluong = rs.getInt("SL");
                double dongia = rs.getInt("DonGia");
                ChiTietHoaDonDTO ctphieu = new ChiTietHoaDonDTO(maphieu, masp, soluong, dongia);
                result.add(ctphieu);
            }
            JDBCConnection.closeConnection(con);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return result;
    }

    @Override
    public int insert(ArrayList<ChiTietHoaDonDTO> t) {
        int result = 0;
        for (int i = 0; i < t.size(); i++) {
            
            try {
                Connection con = (Connection) JDBCConnection.getConnection();
                String sql = "INSERT INTO CTHD (MaHD, MaSP, SL, DonGia) VALUES (?, ?, ?, ?)";
                PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
                pst.setInt(1, t.get(i).getMaHD());
                pst.setInt(2, t.get(i).getMaSP());
                pst.setInt(3, t.get(i).getSoLuong());
                pst.setDouble(4, t.get(i).getDonGia());
                result = pst.executeUpdate();
                JDBCConnection.closeConnection(con);
            } catch (SQLException ex) {
                Logger.getLogger(ChiTietHoaDonDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

    @Override
    public int delete(int t) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCConnection.getConnection();
            String sql = "DELETE FROM CTHD WHERE MaHD = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, t);
            result = pst.executeUpdate();
            JDBCConnection.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(ChiTietHoaDonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int update(ArrayList<ChiTietHoaDonDTO> t, int pk) {
        int result = this.delete(pk);
        if (result != 0) {
            result = this.insert(t);
        }
        return result;
    }

    public ArrayList<ChiTietHoaDonDTO> selectAllByMaHoaDon(int mahoadon) {
        ArrayList<ChiTietHoaDonDTO> result = new ArrayList<>();
        try {
            Connection con = (Connection) JDBCConnection.getConnection();
            String sql = "SELECT * FROM CTHD where MaHD = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, mahoadon);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int mapn = rs.getInt("MaHD");
                int masp = rs.getInt("MaSP");
                int sl = rs.getInt("SoLuong");
                int dongia = rs.getInt("DonGia");
                ChiTietHoaDonDTO ct = new ChiTietHoaDonDTO(mapn, masp, sl, dongia);
                result.add(ct);
            }
            JDBCConnection.closeConnection(con);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return result;
    }
}
