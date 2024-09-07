/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import DTO.ThongKe.ThongKeDoanhThuDTO;
import DTO.ThongKe.ThongKeTheoThangDTO;
//import DTO.ThongKe.ThongKeTonKhoDTO;
import DTO.ThongKe.ThongKeTungNgayTrongThangDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import config.JDBCConnection;
import java.util.Calendar;

/**
 *
 * @author phamthithuphuong
 */
public class ThongKeDAO {
    public static ThongKeDAO getInstance() {
        return new ThongKeDAO();
    }

    // done
    public ArrayList<ThongKeDoanhThuDTO> getDoanhThuTheoTungNam(int year_start, int year_end) {
        ArrayList<ThongKeDoanhThuDTO> result = new ArrayList<>();
        try {
            Connection con = JDBCConnection.getConnection();
            String sql = """
                WITH years AS (
                    SELECT ? AS year
                    UNION ALL
                    SELECT year + 1
                    FROM years
                    WHERE year < ?
                )
                SELECT 
                    years.year AS nam,
                    COALESCE(SUM(CTPN.DonGia * CTPN.SL), 0) AS chiphi, 
                    COALESCE(SUM(CTHD.DonGia * CTHD.SL), 0) AS doanhthu
                FROM years
                LEFT JOIN HoaDon ON YEAR(HoaDon.NgayLapHD) = years.year
                LEFT JOIN CTHD ON HoaDon.MaHD = CTHD.MaHD
                LEFT JOIN PhieuNhap ON YEAR(PhieuNhap.NgayLap) = years.year
                LEFT JOIN CTPN ON PhieuNhap.MaPN = CTPN.MaPN
                GROUP BY years.year
                ORDER BY years.year;
            """;

            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, year_start);
            pst.setInt(2, year_end);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int thoigian = rs.getInt("nam");
                Long chiphi = rs.getLong("chiphi");
                Long doanhthu = rs.getLong("doanhthu");
                ThongKeDoanhThuDTO x = new ThongKeDoanhThuDTO(thoigian, chiphi, doanhthu, doanhthu - chiphi);
                result.add(x);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    // done
    public ArrayList<ThongKeTheoThangDTO> getThongKeTheoThang(int nam) {
        ArrayList<ThongKeTheoThangDTO> result = new ArrayList<>();
        try {
            Connection con = JDBCConnection.getConnection(); 
            String sql = "SELECT MONTH(HoaDon.NgayLapHD) AS thang, \n"
                    + "       COALESCE(SUM(CTHD.DonGia * CTHD.SL), 0) AS doanhthu,\n"
                    + "       COALESCE(SUM(CTPN.DonGia * CTPN.SL), 0) AS chiphi\n"
                    + "FROM HoaDon\n"
                    + "LEFT JOIN CTHD ON HoaDon.MaHD = CTHD.MaHD\n"
                    + "LEFT JOIN CTPN ON CTHD.MaSP = CTPN.MaSP\n"
                    + "LEFT JOIN PhieuNhap ON CTPN.MaPN = PhieuNhap.MaPN\n"
                    + "WHERE YEAR(HoaDon.NgayLapHD) = ?\n"
                    + "GROUP BY MONTH(HoaDon.NgayLapHD)\n"
                    + "ORDER BY MONTH(HoaDon.NgayLapHD);";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, nam);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int thang = rs.getInt("thang");
                int chiphi = rs.getInt("chiphi");
                int doanhthu = rs.getInt("doanhthu");
                int loinhuan = doanhthu - chiphi;
                ThongKeTheoThangDTO thongke = new ThongKeTheoThangDTO(thang, chiphi, doanhthu, loinhuan);
                result.add(thongke);
            }
            con.close(); 
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    
    // done
    public ArrayList<ThongKeTungNgayTrongThangDTO> getThongKeTungNgayTrongThang(int thang, int nam) {
        ArrayList<ThongKeTungNgayTrongThangDTO> result = new ArrayList<>();
        try {
            Connection con = JDBCConnection.getConnection();
            String sql = """
                WITH Dates AS (
                    SELECT DATEADD(DAY, n, CAST(? AS DATE)) AS Date
                    FROM (SELECT TOP (31) ROW_NUMBER() OVER (ORDER BY s1.[object_id]) - 1 AS n
                          FROM sys.all_objects AS s1
                          CROSS JOIN sys.all_objects AS s2) AS Numbers
                    WHERE DATEADD(DAY, n, CAST(? AS DATE)) <= EOMONTH(CAST(? AS DATE))
                )
                SELECT 
                    Dates.Date AS ngay,
                    COALESCE(SUM(CTPN.DonGia * CTPN.SL), 0) AS chiphi,
                    COALESCE(SUM(CTHD.DonGia * CTHD.SL), 0) AS doanhthu
                FROM Dates
                LEFT JOIN HoaDon ON CAST(HoaDon.NgayLapHD AS DATE) = Dates.Date
                LEFT JOIN CTHD ON HoaDon.MaHD = CTHD.MaHD
                LEFT JOIN PhieuNhap ON CAST(PhieuNhap.NgayLap AS DATE) = Dates.Date
                LEFT JOIN CTPN ON PhieuNhap.MaPN = CTPN.MaPN
                GROUP BY Dates.Date
                ORDER BY Dates.Date;
            """;

            PreparedStatement pst = con.prepareStatement(sql);

            // Tạo ngày đầu tiên của tháng dưới dạng java.sql.Date
            Calendar cal = Calendar.getInstance();
            cal.set(nam, thang - 1, 1);
            java.sql.Date firstDayOfMonth = new java.sql.Date(cal.getTimeInMillis());

            // Đặt giá trị cho tham số
            pst.setDate(1, firstDayOfMonth);
            pst.setDate(2, firstDayOfMonth);
            pst.setDate(3, firstDayOfMonth);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                java.sql.Date ngaySQL = rs.getDate("ngay");
                java.util.Date ngay = new java.util.Date(ngaySQL.getTime());
                int chiphi = rs.getInt("chiphi");
                int doanhthu = rs.getInt("doanhthu");
                int loinhuan = doanhthu - chiphi;
                ThongKeTungNgayTrongThangDTO tn = new ThongKeTungNgayTrongThangDTO(ngay, chiphi, doanhthu, loinhuan);
                result.add(tn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    // done
    public ArrayList<ThongKeTungNgayTrongThangDTO> getThongKe7NgayGanNhat() {
    ArrayList<ThongKeTungNgayTrongThangDTO> result = new ArrayList<>();
    try {
        Connection con = JDBCConnection.getConnection();
        String sql = """
            WITH Dates AS (
                SELECT CAST(GETDATE() AS DATE) AS Date
                UNION ALL
                SELECT DATEADD(DAY, -1, Date)
                FROM Dates
                WHERE Date > DATEADD(DAY, -6, GETDATE())
            )
            SELECT 
                Dates.Date AS ngay,
                COALESCE(SUM(CTPN.DonGia * CTPN.SL), 0) AS chiphi,
                COALESCE(SUM(CTHD.DonGia * CTHD.SL), 0) AS doanhthu
            FROM Dates
            LEFT JOIN HoaDon ON CAST(HoaDon.NgayLapHD AS DATE) = Dates.Date
            LEFT JOIN CTHD ON HoaDon.MaHD = CTHD.MaHD
            LEFT JOIN PhieuNhap ON CAST(PhieuNhap.NgayLap AS DATE) = Dates.Date
            LEFT JOIN CTPN ON PhieuNhap.MaPN = CTPN.MaPN
            GROUP BY Dates.Date
            ORDER BY Dates.Date;
        """;

        PreparedStatement pst = con.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            java.sql.Date ngaySQL = rs.getDate("ngay");
            java.util.Date ngay = new java.util.Date(ngaySQL.getTime());
            int chiphi = rs.getInt("chiphi");
            int doanhthu = rs.getInt("doanhthu");
            int loinhuan = doanhthu - chiphi;
            ThongKeTungNgayTrongThangDTO tn = new ThongKeTungNgayTrongThangDTO(ngay, chiphi, doanhthu, loinhuan);
            result.add(tn);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return result;
}

    // done
    public ArrayList<ThongKeTungNgayTrongThangDTO> getThongKeTuNgayDenNgay(String start, String end) {
        ArrayList<ThongKeTungNgayTrongThangDTO> result = new ArrayList<>();
        try {
            Connection con = JDBCConnection.getConnection(); // Thay thế YourConnectionClass bằng lớp xử lý kết nối của bạn
            String sqlSelect = "SELECT\n"
                    + "  dates.date AS ngay,\n"
                    + "  COALESCE(SUM(CTPN.DonGia), 0) AS chiphi,\n"
                    + "  COALESCE(SUM(CTHD.DonGia), 0) AS doanhthu\n"
                    + "FROM (\n"
                    + "  SELECT DATEADD(DAY, number, ?) AS date\n"
                    + "  FROM (\n"
                    + "    SELECT ROW_NUMBER() OVER(ORDER BY object_id) - 1 AS number\n"
                    + "    FROM sys.all_objects\n"
                    + "  ) AS numbers\n"
                    + "  WHERE DATEADD(DAY, number, ?) <= ?\n"
                    + ") AS dates\n"
                    + "LEFT JOIN HoaDon ON CONVERT(DATE, HoaDon.NgayLapHD) = dates.date\n"
                    + "LEFT JOIN CTHD ON HoaDon.MaHD = CTHD.MaHD\n"
                    + "LEFT JOIN PhieuNhap ON CONVERT(DATE, PhieuNhap.NgayLap) = dates.date\n"
                    + "LEFT JOIN CTPN ON PhieuNhap.MaPN = CTPN.MaPN\n"
                    + "GROUP BY dates.date\n"
                    + "ORDER BY dates.date;";

            PreparedStatement pstSelect = con.prepareStatement(sqlSelect);
            pstSelect.setString(1, start);
            pstSelect.setString(2, start);
            pstSelect.setString(3, end);

            ResultSet rs = pstSelect.executeQuery();
            while (rs.next()) {
                Date ngay = rs.getDate("ngay");
                int chiphi = rs.getInt("chiphi");
                int doanhthu = rs.getInt("doanhthu");
                int loinhuan = doanhthu - chiphi;
                ThongKeTungNgayTrongThangDTO tn = new ThongKeTungNgayTrongThangDTO(ngay, chiphi, doanhthu, loinhuan);
                result.add(tn);
            }
            con.close(); // Đóng kết nối sau khi sử dụng xong
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}