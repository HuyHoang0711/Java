/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classname;

import java.util.Objects;

/**
 *
 * @author Admin
 */
public class LopDocLap {
    private String maLop;
    private String tenMonHoc;
    private String heDaoTao;
    private String khoaDaoTao;
    private String lichLyThuyet;
    private String phongLyThuyet;
    private int soLuongHSSV;

    public LopDocLap() {
    }

    public LopDocLap(String maLop) {
        this.maLop = maLop;
    }

    public LopDocLap(String maLop, String tenMonHoc, String heDaoTao, String khoaDaoTao,
                                  String lichLyThuyet, String phongLyThuyet, int soLuongHSSV) 
    {
        this.maLop = maLop;
        this.tenMonHoc = tenMonHoc;
        this.heDaoTao = heDaoTao;
        this.khoaDaoTao = khoaDaoTao;
        this.lichLyThuyet = lichLyThuyet;
        this.phongLyThuyet = phongLyThuyet;
        this.soLuongHSSV = soLuongHSSV;
    }

    public LopDocLap(String maLop, String tenMonHoc, String heDaoTao, String khoaDaoTao, int soLuongHSSV) {
        this.maLop = maLop;
        this.tenMonHoc = tenMonHoc;
        this.heDaoTao = heDaoTao;
        this.khoaDaoTao = khoaDaoTao;
        this.soLuongHSSV = soLuongHSSV;
    }
     public LopDocLap(String maLop, String tenMonHoc, String heDaoTao, String khoaDaoTao, String lichLyThuyet) {
        this.maLop = maLop;
        this.tenMonHoc = tenMonHoc;
        this.heDaoTao = heDaoTao;
        this.khoaDaoTao = khoaDaoTao;
        this.lichLyThuyet = lichLyThuyet;
    }

    public LopDocLap(String maLop, String tenMonHoc) {
        this.maLop = maLop;
        this.tenMonHoc = tenMonHoc;
    }
      
    public LopDocLap(String maLop, String phongLyThuyet, String lichLyThuyet)
    {
        this.maLop = maLop;
        this.phongLyThuyet = phongLyThuyet;
        this.lichLyThuyet = lichLyThuyet;
    }
    public String getKiemTra()
    {
        return this.phongLyThuyet + "\n" + this.lichLyThuyet;
    }
    
    public String getMaLop() {
        return maLop;
    }

    public String getTenMonHoc() {
        return tenMonHoc;
    }

    public String getHeDaoTao() {
        return heDaoTao;
    }

    public String getKhoaDaoTao() {
        return khoaDaoTao;
    }

    public String getLichLyThuyet() {
        return lichLyThuyet;
    }

    public String getPhongLyThuyet() {
        return phongLyThuyet;
    }

    public int getSoLuongHSSV() {
        return soLuongHSSV;
    }

    public void setMaLop(String maLop) {
        this.maLop = maLop;
    }

    public void setTenMonHoc(String tenMonHoc) {
        this.tenMonHoc = tenMonHoc;
    }

    public void setHeDaoTao(String heDaoTao) {
        this.heDaoTao = heDaoTao;
    }

    public void setKhoaDaoTao(String khoaDaoTao) {
        this.khoaDaoTao = khoaDaoTao;
    }

    public void setLichLyThuyet(String lichLyThuyet) {
        this.lichLyThuyet = lichLyThuyet;
    }

    public void setPhongLyThuyet(String phongLyThuyet) {
        this.phongLyThuyet = phongLyThuyet;
    }

    public void setSoLuongHSSV(int soLuongHSSV) {
        this.soLuongHSSV = soLuongHSSV;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.maLop);
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
        final LopDocLap other = (LopDocLap) obj;
        if (!Objects.equals(this.maLop, other.maLop)) {
            return false;
        }
        return true;
    }
    
}
