/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package helper;

import DTO.NhanVienDTO;
import config.JDBCConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class HelperFunction {
    
    public static boolean checkLoginUserPass(String us, String pass) {
        boolean isExist = false;
        try {
            Connection con = (Connection) JDBCConnection.getConnection();
            String sql = "SELECT * FROM NhanVien WHERE Email = ? AND Password = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, us);
            pst.setString(2, pass);
            ResultSet rs = (ResultSet) pst.executeQuery();
            
            if(!rs.wasNull()){
                isExist = true;
            }
            JDBCConnection.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isExist;        
    }
}
