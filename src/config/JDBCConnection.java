/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class JDBCConnection {    
    Connection conn = null;
    private static String strSever = "ADMIN\\SQL_2019";
    private static String strData = "QL_VanPhongPham";
    private static String strUser = "sa";
    private static String strPass = "12345";
    public JDBCConnection() {
        Connection connection = this.getConnection();
    }
    public static Connection getConnection() {
        Connection connecting = null;
        String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        try { 
            
            Class.forName(driver);            
            String connectionUrl = "jdbc:sqlserver://" + strSever + ":1433;" +
                "databaseName=" + strData + ";" +
                "user=" + strUser + ";" +
                "password=" + strPass + ";" +
                "encrypt=false;" +
                "trustServerCertificate=true;" +
                "sslProtocol=TLSv1.2;";

            connecting = DriverManager.getConnection(connectionUrl);
            System.out.println("Congratulation, the connection was successful");
        } catch (Exception e) {            
            JOptionPane.showMessageDialog(null, "Không thể kết nối đến cơ sở dữ liệu !", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        return connecting;
    }

    public static void closeConnection(Connection c) {
        try {
            if (c != null) {
                c.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }    
    
}
