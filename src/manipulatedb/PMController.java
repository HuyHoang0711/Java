/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manipulatedb;

import db.Database;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class PMController {
     public static int insert_pm(String maPhong, String tenPhong, String viTriPhong, 
                                            int soLuongMay, String coSoVatChat)
        {
            String sql = "INSERT INTO PHONGMAY VALUES(?,?,?,?,?)";
            try
            {
            PreparedStatement pre = Database.getPrepareStatement(sql);
            pre.setString(1, maPhong);
            pre.setString(2, tenPhong);
            pre.setString(3, viTriPhong);
            pre.setInt(4, soLuongMay);
            pre.setString(5, coSoVatChat);
            return pre.executeUpdate();
            }
            catch(SQLException ex)
            {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Thêm dữ liệu phòng máy thất bại!", "Thông báo", JOptionPane.ERROR_MESSAGE);
            }
            return 0;
        }
        public static int delete_pm(String maPhong)
        {
            String sql = "DELETE FROM PHONGMAY WHERE MaPhong = ?";
            try
            {
            PreparedStatement pre = Database.getPrepareStatement(sql);
            pre.setString(1, maPhong);
            return pre.executeUpdate();
            }
            catch(SQLException ex)
            {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Xóa dữ liệu phòng máy thất bại!", "Thông báo", JOptionPane.ERROR_MESSAGE);
            }
            return 0;
        }
        public static int update_pm(String maPhong, String tenPhong, String viTriPhong, 
                                            int soLuongMay, String coSoVatChat)
        {
            String sql = "UPDATE PHONGMAY SET TenPhong = ?, ViTriPhong = ?, SoLuongMay = ?,"
                              + " CoSoVatChat = ? WHERE MaPhong = ?";
            try
            {
            PreparedStatement pre = Database.getPrepareStatement(sql);
            pre.setString(1, tenPhong);
            pre.setString(2, viTriPhong);
            pre.setInt(3, soLuongMay);
            pre.setString(4, coSoVatChat);
            pre.setString(5, maPhong);
            return pre.executeUpdate();
            }
            catch(SQLException ex)
            {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Cập nhập dữ liệu phòng máy thất bại!", "Thông báo", JOptionPane.ERROR_MESSAGE);
            }
            return 0;
        }
}
