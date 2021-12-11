/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classname;

import java.sql.Date;
import java.util.ArrayList;
/**
 *
 * @author Admin
 */
public class GiangVien extends NhanVienDHCN{
    private String giangVienNganh;
    private TaiKhoan taiKhoanGV;
    private ArrayList<LopDocLap> listLopDocLap = new ArrayList<LopDocLap>();
    private ArrayList<DangKyPhong> listDangKyPhong = new ArrayList<DangKyPhong>();
    public GiangVien()
    {
      super();
     taiKhoanGV = new TaiKhoan();           
    }
    
    public GiangVien(String maNhanVien)
    {
        super(maNhanVien);
    }

    public GiangVien(String maNhanVien, String hoTen) {
        super(maNhanVien, hoTen);
    }
    
    public GiangVien(String maNhanVien, String hoTen, String gioiTinh, 
                                 String soDienThoai, String giangVienNganh, String taiKhoan, String matKhau)
    {
        super(maNhanVien, hoTen, gioiTinh, soDienThoai);
        taiKhoanGV = new TaiKhoan(taiKhoan, matKhau);
        this.giangVienNganh = giangVienNganh;
    }

    public String getGiangVienNganh() {
        return giangVienNganh;
    }

    public TaiKhoan getTaiKhoanGV() {
        return taiKhoanGV;
    }

    public ArrayList<LopDocLap> getListLopDocLap() {
        return listLopDocLap;
    }

    public ArrayList<DangKyPhong> getListDangKyPhong() {
        return listDangKyPhong;
    }
    public String getThongTin()
    {
         return (listLopDocLap.get(0).getMaLop() + "\n" 
                   + this.getHoTen() + "\n"
                   + listLopDocLap.get(0).getTenMonHoc() + "\n"
                   + listLopDocLap.get(0).getHeDaoTao() + " "
                   + listLopDocLap.get(0).getKhoaDaoTao() + "; "
                   + "LT: " + listLopDocLap.get(0).getLichLyThuyet());
    }
    public String getThongTinKiemTra()
    {
        return (listDangKyPhong.get(0).getMaPhong() + "\n" + listDangKyPhong.get(0).getThoiGian().toString()
                   +"\n" + listDangKyPhong.get(0).getCaThucHanh());
    }
    public void setGiangVienNganh(String giangVienNganh) {
        this.giangVienNganh = giangVienNganh;
    }

    public void setTaiKhoanGV(TaiKhoan taiKhoanGV) {
        this.taiKhoanGV = taiKhoanGV;
    }

    public void setListLopDocLap(ArrayList<LopDocLap> listLopDocLap) {
        this.listLopDocLap = listLopDocLap;
    }

    public void setListDangKyPhong(ArrayList<DangKyPhong> listDangKyPhong) {
        this.listDangKyPhong = listDangKyPhong;
    }
    
}
