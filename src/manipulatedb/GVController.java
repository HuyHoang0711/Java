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
public class GVController {
      public static int insert_gv(String maGiangVien, String hoTen, String taiKhoan,String matKhau, 
                                             String gioiTinh, String soDienThoai,String giangVienNganh, String maNhanVien)
    {
        String sql ="INSERT INTO GIANGVIEN VALUES(?,?,?,?,?,?,?,?)";
        try{
            PreparedStatement ps = Database.getPrepareStatement(sql);
            ps.setString(1, maGiangVien);
             ps.setString(2, hoTen);
            ps.setString(3, taiKhoan);
            ps.setString(4, matKhau);
            ps.setString(5, gioiTinh);
            ps.setString(6, soDienThoai);
            ps.setString(7, giangVienNganh);
            ps.setString(8, maNhanVien);
            return ps.executeUpdate();
           // JOptionPane.showMessageDialog(null, "Thêm giảng viên thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }catch (SQLException ex) {
            ex.printStackTrace();
          JOptionPane.showMessageDialog(null, "Thêm giảng viên thất bại", "Thông báo", JOptionPane.ERROR_MESSAGE);
        }
        return 0;
    }
    public static int update_gv(String maGiangVien, String hoTen, String taiKhoan,String matKhau, 
                                              String gioiTinh, String soDienThoai, String giangVienNganh, String maNhanVien )
    {
        String sql ="UPDATE GIANGVIEN SET  HoTen = ?,  TaiKhoan = ?,  MatKhau = ?, GioiTinh = ?, "
                         + "SoDienThoai = ?, GiangVienNganh = ?, MaNhanVien = ? WHERE MaGiangVien = ?";
        try {
            PreparedStatement ps = Database.getPrepareStatement(sql);
            ps.setString(1, hoTen);
            ps.setString(2, taiKhoan);
            ps.setString(3, matKhau);
            ps.setString(4, gioiTinh);
            ps.setString(5, soDienThoai);
            ps.setString(6, giangVienNganh);
            ps.setString(7, maNhanVien);
            ps.setString(8, maGiangVien);
            return ps.executeUpdate();
            //JOptionPane.showMessageDialog(null, "Sửa thành công");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi. Sửa thất bại");
        }
        return 0;
    }
    public static int delete_gv(String maGiangVien){
        String sql ="DELETE FROM GIANGVIEN WHERE MaGiangVien = ?";
        try {
            PreparedStatement ps = Database.getPrepareStatement(sql);
            ps.setString(1, maGiangVien);
            return ps.executeUpdate();
            //JOptionPane.showMessageDialog(null, "Xoá giảng viên thành công");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Xoá giảng viên thất bại");    
    }
        return 0;
}
    public static int update_TaiKhoan(String maGiangVien, String matKhauMoi)
    {
        String sql = "UPDATE GIANGVIEN SET MatKhau = ? WHERE maGiangVien = ?";
        try
        {
            PreparedStatement ps = Database.getPrepareStatement(sql);
             ps.setString(1, matKhauMoi);
            ps.setString(2, maGiangVien);
            return ps.executeUpdate();
        }
        catch(SQLException ex)
        {
             ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Cập nhập mật khẩu thất bại!", "Thông báo", JOptionPane.ERROR_MESSAGE);    
        }
        return 0;
    }
}
