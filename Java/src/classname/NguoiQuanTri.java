/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classname;

import java.util.Date;
import java.util.ArrayList;
/**
 *
 * @author Admin
 */
public class NguoiQuanTri extends NhanVienDHCN{
    private String nganhQuanLy;
    private TaiKhoan taiKhoanQT;
    private ArrayList<GiangVien> listGiangVien = new ArrayList<GiangVien>();
    public NguoiQuanTri()
    {
        super();
        taiKhoanQT = new TaiKhoan();
    }
    
    public NguoiQuanTri(String maNhanVien, String hoTen, String gioiTinh, String soDienThoai, 
                                        String nganhQuanLy, String taiKhoan, String matKhau)
    {
        super(maNhanVien, hoTen, gioiTinh, soDienThoai);
        taiKhoanQT = new TaiKhoan(taiKhoan, matKhau);
        this.nganhQuanLy = nganhQuanLy;
    }

    public String getNganhQuanLy() {
        return nganhQuanLy;
    }
    
    public void setNganhQuanLy(String nganhQuanLy) {
        this.nganhQuanLy = nganhQuanLy;
    }
    
}
