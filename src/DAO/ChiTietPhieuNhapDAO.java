/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.ChiTietPhieuNhapDTO;
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
 * @author phamthithuphuong
 */
public class ChiTietPhieuNhapDAO implements ChiTietInterface<ChiTietPhieuNhapDTO>{
    
    public static ChiTietPhieuNhapDAO getInstance() {
        return new ChiTietPhieuNhapDAO();
    }


    @Override
    public ArrayList<ChiTietPhieuNhapDTO> selectAll(int t) {
        ArrayList<ChiTietPhieuNhapDTO> result = new ArrayList<>();
        try {
            Connection con = (Connection) JDBCConnection.getConnection();
            String sql = "SELECT * FROM CTPN WHERE MaPN = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, t);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int maphieu = rs.getInt("MaPN");
                int masp = rs.getInt("MaSP");
                int soluong = rs.getInt("SL");
                double dongia = rs.getInt("DonGia");
                ChiTietPhieuNhapDTO ctphieu = new ChiTietPhieuNhapDTO(maphieu, masp, soluong, dongia);
                result.add(ctphieu);
            }
            JDBCConnection.closeConnection(con);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return result;
    }

    @Override
    public int insert(ArrayList<ChiTietPhieuNhapDTO> t) {
        int result = 0;
        for (int i = 0; i < t.size(); i++) {
            
            try {
                Connection con = (Connection) JDBCConnection.getConnection();
                String sql = "INSERT INTO CTPN (MaPN, MaSP, SL, DonGia) VALUES (?, ?, ?, ?)";
                PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
                pst.setInt(1, t.get(i).getMaPN());
                pst.setInt(2, t.get(i).getMaSP());
                pst.setInt(3, t.get(i).getSoLuong());
                pst.setDouble(4, t.get(i).getDonGia());
                result = pst.executeUpdate();
                JDBCConnection.closeConnection(con);
            } catch (SQLException ex) {
                Logger.getLogger(ChiTietPhieuNhapDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

    @Override
    public int delete(int t) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCConnection.getConnection();
            String sql = "DELETE FROM CTPN WHERE MaPN = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, t);
            result = pst.executeUpdate();
            JDBCConnection.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(ChiTietPhieuNhapDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int update(ArrayList<ChiTietPhieuNhapDTO> t, int pk) {
        int result = this.delete(pk);
        if (result != 0) {
            result = this.insert(t);
        }
        return result;
    }

    public ArrayList<ChiTietPhieuNhapDTO> selectAllByMaPhieuNhap(int maphieunhap) {
        ArrayList<ChiTietPhieuNhapDTO> result = new ArrayList<>();
        try {
            Connection con = (Connection) JDBCConnection.getConnection();
            String sql = "SELECT * FROM CTPN where MaPN = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, maphieunhap);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int mapn = rs.getInt("MaPN");
                int masp = rs.getInt("MaSP");
                int sl = rs.getInt("SoLuong");
                int dongia = rs.getInt("DonGia");
                ChiTietPhieuNhapDTO ct = new ChiTietPhieuNhapDTO(mapn, masp, sl, dongia);
                result.add(ct);
            }
            JDBCConnection.closeConnection(con);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return result;
    }
}
