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
public class DKPController {
        public static int insert_dkp(String maGiangVien, String maPhong, String caThucHanh, 
                                    Date thoiGian, String maLop, String nhom)
        {
            String sql = "INSERT INTO DANGKYPHONG VALUES(?,?,?,?,?,?)";
            try
            {
            PreparedStatement pre = Database.getPrepareStatement(sql);
            pre.setString(1, maGiangVien);
            pre.setString(2, maPhong);
            pre.setString(3, caThucHanh);
            pre.setDate(4, thoiGian);
            pre.setString(5, maLop);
            pre.setString(6, nhom);
            return pre.executeUpdate();
            }
            catch(SQLException ex)
            {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Thêm dữ liệu đăng ký phòng thất bại", "Thông báo", JOptionPane.ERROR_MESSAGE);
            }
            return 0;
        }
        public static int delete_dkp(String maGiangVien, String maPhong, String caThucHanh, Date thoiGian)
        {
            String sql = "DELETE FROM DANGKYPHONG WHERE MaGiangVien = ? AND MaPhong = ? AND"
                              + " CaThucHanh = ? AND ThoiGian = ?";
            try
            {
            PreparedStatement pre = Database.getPrepareStatement(sql);
            pre.setString(1, maGiangVien);
            pre.setString(2, maPhong);
            pre.setString(3, caThucHanh);
            pre.setDate(4, new java.sql.Date(thoiGian.getTime()));
            return pre.executeUpdate();
            }
            catch(SQLException ex)
            {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Xóa dữ liệu đăng ký phòng thất bại", "Thông báo", JOptionPane.ERROR_MESSAGE);
            }
            return 0;
        }
        public static int update_dkp(String maGiangVien, String maPhong, String caThucHanh, 
                                    Date thoiGian, String maLop, String nhom)
        {
            String sql = "UPDATE DANGKYPHONG SET maPhong = ?, caThucHanh = ?, thoiGian = ?,"
                              + " maLop = ?, nhom = ? WHERE MaGiangVien = ?";
            try
            {
            PreparedStatement pre = Database.getPrepareStatement(sql);
            pre.setString(1, maPhong);
            pre.setString(2, caThucHanh);
            pre.setDate(3, new java.sql.Date(thoiGian.getTime()));
            pre.setString(4, maLop);
            pre.setString(5, nhom);
            pre.setString(6, maGiangVien);
            return pre.executeUpdate();
            }
            catch(SQLException ex)
            {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Cập nhập dữ liệu đăng ký phòng thất bại", "Thông báo", JOptionPane.ERROR_MESSAGE);
            }
            return 0;
        }
        public static int delete_dkp_malop(String maLop)
        {
            String sql = "DELETE FROM DANGKYPHONG WHERE MaLop = ?";
                              
            try
            {
            PreparedStatement pre = Database.getPrepareStatement(sql);
            pre.setString(1, maLop);
            return pre.executeUpdate();
            }
            catch(SQLException ex)
            {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Xóa dữ liệu đăng ký phòng theo mã lớp thất bại", "Thông báo", JOptionPane.ERROR_MESSAGE);
            }
            return 0;
        }
        public static int update_dkp_malop(String maLopCu, String maLopMoi)
        {
            String sql = "UPDATE DANGKYPHONG SET MaLop = ? WHERE MaLop = ?";
                              
            try
            {
            PreparedStatement pre = Database.getPrepareStatement(sql);
            pre.setString(1, maLopMoi);
            pre.setString(2, maLopCu);
            return pre.executeUpdate();
            }
            catch(SQLException ex)
            {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Cập nhập dữ liệu đăng ký phòng theo mã lớp thất bại", "Thông báo", JOptionPane.ERROR_MESSAGE);
            }
            return 0;
        }
}
