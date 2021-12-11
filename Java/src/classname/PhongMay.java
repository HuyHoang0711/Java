/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classname;
import java.util.ArrayList;
import java.util.Objects;
/**
 *
 * @author Admin
 */
public class PhongMay {
    private String maPhong;
    private String tenPhong;
    private String viTriPhong;
    private int soLuongMay;
    private String coSoVatChat;
    private ArrayList<DangKyPhong> listDangKyPhong = new ArrayList<DangKyPhong>();
    public PhongMay()
    {
        
    }

    public PhongMay(String maPhong) {
        this.maPhong = maPhong;
    }

    public PhongMay(String maPhong, String tenPhong, String viTriPhong, int soLuongMay, String coSoVatChat) {
        this.maPhong = maPhong;
        this.tenPhong = tenPhong;
        this.viTriPhong = viTriPhong;
        this.soLuongMay = soLuongMay;
        this.coSoVatChat = coSoVatChat;
    }

    public String getMaPhong() {
        return maPhong;
    }

    public String getTenPhong() {
        return tenPhong;
    }

    public String getViTriPhong() {
        return viTriPhong;
    }

    public int getSoLuongMay() {
        return soLuongMay;
    }

    public String getCoSoVatChat() {
        return coSoVatChat;
    }

    public void setMaPhong(String maPhong) {
        this.maPhong = maPhong;
    }

    public void setTenPhong(String tenPhong) {
        this.tenPhong = tenPhong;
    }

    public void setViTriPhong(String viTriPhong) {
        this.viTriPhong = viTriPhong;
    }

    public void setSoLuongMay(int soLuongMay) {
        this.soLuongMay = soLuongMay;
    }

    public void setCoSoVatChat(String coSoVatChat) {
        this.coSoVatChat = coSoVatChat;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.maPhong);
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
        final PhongMay other = (PhongMay) obj;
        if (!Objects.equals(this.maPhong, other.maPhong)) {
            return false;
        }
        return true;
    }
    
}
