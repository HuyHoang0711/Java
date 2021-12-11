/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manipulatedb;

import db.Database;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class LDLController {
        public static int insert_ldl(String maLop, String tenMonHoc, String heDaoTao, 
                                    String khoaDaoTao, String lichLyThuyet, String phongLyThuyet, int soLuongHSSV, String maGiangVien)
        {
            String sql = "INSERT INTO LOPDOCLAP VALUES(?,?,?,?,?,?,?,?)";
            try
            {
            PreparedStatement pre = Database.getPrepareStatement(sql);
            pre.setString(1, maLop);
            pre.setString(2, tenMonHoc);
            pre.setString(3, heDaoTao);
            pre.setString(4, khoaDaoTao);
            pre.setString(5, lichLyThuyet);
            pre.setString(6, phongLyThuyet);
            pre.setInt(7, soLuongHSSV);
            pre.setString(8, maGiangVien);
            return pre.executeUpdate();
            }
            catch(SQLException ex)
            {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Thêm dữ liệu đăng ký phòng thất bại", "Thông báo", JOptionPane.ERROR_MESSAGE);
            }
            return 0;
        }
        public static int delete_ldl(String maLop)
        {
            String sql = "DELETE FROM LOPDOCLAP WHERE MaLop = ?";
            try
            {
            PreparedStatement pre = Database.getPrepareStatement(sql);
            pre.setString(1, maLop);
            return pre.executeUpdate();
            }
            catch(SQLException ex)
            {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Xóa dữ liệu đăng ký phòng thất bại", "Thông báo", JOptionPane.ERROR_MESSAGE);
            }
            return 0;
        }
        public static int update_ldl(String maLop, String tenMonHoc, String heDaoTao, 
                                    String khoaDaoTao, String lichLyThuyet, String phongLyThuyet, int soLuongHSSV)
        {
            String sql = "UPDATE LOPDOCLAP SET TenMonHoc = ?, HeDaoTao = ?, KhoaDaoTao = ?,"
                              + " LichLyThuyet = ?, PhongLyThuyet = ?, SoLuongSV = ? WHERE MaLop = ?";
            try
            {
            PreparedStatement pre = Database.getPrepareStatement(sql);
            pre.setString(1, tenMonHoc);
            pre.setString(2, heDaoTao);
            pre.setString(3, khoaDaoTao);
            pre.setString(4, lichLyThuyet);
            pre.setString(5, phongLyThuyet);
            pre.setInt(6, soLuongHSSV);
            pre.setString(7, maLop);
            return pre.executeUpdate();
            }
            catch(SQLException ex)
            {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Cập nhập dữ liệu đăng ký phòng thất bại", "Thông báo", JOptionPane.ERROR_MESSAGE);
            }
            return 0;
        }
        
}
