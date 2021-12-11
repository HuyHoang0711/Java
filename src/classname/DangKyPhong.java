/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classname;

import java.sql.Date;
import java.util.Objects;

/**
 *
 * @author Admin
 */
public class DangKyPhong {
    private String maNhanVien;
    private String maPhong;
    private String maLop;
    private String caThucHanh;
    private Date thoiGian;
    private String nhom;
    private int tuan;
    public DangKyPhong()
    {
        
    }
    public DangKyPhong(String maNhanVien, String maPhong, String maLop, String caThucHanh, Date thoiGian, String nhom) 
    {
        this.maNhanVien = maNhanVien;
        this.maPhong = maPhong;
        this.maLop = maLop;
        this.caThucHanh = caThucHanh;
        this.thoiGian = thoiGian;
        this.nhom = nhom;
    }
     public DangKyPhong(String maPhong, String caThucHanh, Date thoiGian, String nhom) {
        this.maPhong = maPhong;
        this.caThucHanh = caThucHanh;
        this.thoiGian = thoiGian;
        this.nhom = nhom;
        this.tuan = tuan;
    }
    public DangKyPhong(String maPhong, String caThucHanh, Date thoiGian, String nhom, int tuan) {
        this.maPhong = maPhong;
        this.caThucHanh = caThucHanh;
        this.thoiGian = thoiGian;
        this.nhom = nhom;
        this.tuan = tuan;
    }

    public DangKyPhong(String maPhong, String caThucHanh, Date thoiGian) {
        this.maPhong = maPhong;
        this.caThucHanh = caThucHanh;
        this.thoiGian = thoiGian;
    }
    
    
    public String getMaNhanVien() {
        return maNhanVien;
    }

    public String getMaPhong() {
        return maPhong;
    }

    public String getMaLop() {
        return maLop;
    }

    public String getCaThucHanh() {
        return caThucHanh;
    }

    public Date getThoiGian() {
        return thoiGian;
    }

    public String getNhom() {
        return nhom;
    }
    public int getTuan() {
        return tuan;
    }
    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public void setMaPhong(String maPhong) {
        this.maPhong = maPhong;
    }

    public void setMaLop(String maLop) {
        this.maLop = maLop;
    }

    public void setCaThucHanh(String caThucHanh) {
        this.caThucHanh = caThucHanh;
    }

    public void setThoiGian(Date thoiGian) {
        this.thoiGian = thoiGian;
    }

    public void setNhom(String nhom) {
        this.nhom = nhom;
    }

    public void setTuan(int tuan) {
        this.tuan = tuan;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + Objects.hashCode(this.maPhong);
        hash = 47 * hash + Objects.hashCode(this.caThucHanh);
        hash = 47 * hash + Objects.hashCode(this.thoiGian);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DangKyPhong other = (DangKyPhong) obj;
        if (!Objects.equals(this.maPhong, other.maPhong)) {
            return false;
        }
        if (!Objects.equals(this.caThucHanh, other.caThucHanh)) {
            return false;
        }
        if (!Objects.equals(this.thoiGian, other.thoiGian)) {
            return false;
        }
        return true;
    }

    
    
    
}
