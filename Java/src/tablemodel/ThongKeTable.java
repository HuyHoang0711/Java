/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tablemodel;
import classname.GiangVien;
import classname.LopDocLap;
import classname.DangKyPhong;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
/**
 *
 * @author Admin
 */
public class ThongKeTable extends AbstractTableModel{
    private String[] name = {"Hệ", "Khóa", "Lớp", "Nhóm", "SL HSSV", "Môn học", "Ngày học", "Ca học", "Phòng", "Giáo viên"};
    private Class[] classes = {String.class, String.class, String.class, String.class, Integer.class, String.class, String.class, String.class, String.class, String.class};
    private ArrayList<GiangVien> listGiangVien = new ArrayList<>();
    public ThongKeTable(ArrayList<GiangVien> listGiangVien)
    {
        this.listGiangVien = listGiangVien;
    }
    @Override
    public int getRowCount() {
        return listGiangVien.size();
    }

    @Override
    public int getColumnCount() {
        return name.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex)
        {
            case 0: return listGiangVien.get(rowIndex).getListLopDocLap().get(0).getHeDaoTao();
            case 1: return listGiangVien.get(rowIndex).getListLopDocLap().get(0).getKhoaDaoTao();
            case 2: return listGiangVien.get(rowIndex).getListLopDocLap().get(0).getMaLop();
            case 3: return listGiangVien.get(rowIndex).getListDangKyPhong().get(0).getNhom();
            case 4: return listGiangVien.get(rowIndex).getListLopDocLap().get(0).getSoLuongHSSV();
            case 5: return listGiangVien.get(rowIndex).getListLopDocLap().get(0).getTenMonHoc();
            case 6: return listGiangVien.get(rowIndex).getListDangKyPhong().get(0).getThoiGian().toString();
            case 7: return listGiangVien.get(rowIndex).getListDangKyPhong().get(0).getCaThucHanh();
            case 8: return listGiangVien.get(rowIndex).getListDangKyPhong().get(0).getMaPhong();
            case 9: return listGiangVien.get(rowIndex).getHoTen();
            default: return null;      
        }
    }
    @Override
    public String getColumnName(int columnIndex)
    {
        return name[columnIndex];
    }
    @Override 
    public Class getColumnClass(int columnIndex)
    {
        return classes[columnIndex];
    }
            
    
}
