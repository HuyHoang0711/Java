/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tablemodel;
import classname.GiangVien;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author tien huy
 */
public class QLLopTable extends AbstractTableModel{
  
    //Khai báo xâu chứa tiêu đề của bảng.
    private String name[]={"Mã lớp", "Tên môn học", "Hệ đào tạo", "Khóa", "Lịch lý thuyết", "Phòng lý thuyết", "Số lượng SV"};
    //Khai báo lớp Chứa kiểu dữ liệu của từng trường tương ứng.
    private Class classes[]={String.class, String.class, String.class, String.class, String.class,String.class, Integer.class};
    //Tạo một đối tượng arrayList có tên listThiSinh.
    GiangVien giangVien = new GiangVien();

    //phương thức khởi tạo cho class có tham số truyền vào.
    public QLLopTable(GiangVien giangVien)
   {
       this.giangVien = giangVien; 
   }
    //lấy số phần tử của listThiSinh
    @Override
    public int getRowCount()
    {
        return giangVien.getListLopDocLap().size();
    }
    //Lấy số lượng tiêu để của mảng.
    @Override
    public int getColumnCount()
    {
        return name.length;
    }
    //Đưa thông tin của phần tử trong arrayList lên jTable
    public Object getValueAt(int rowIndex,int columnIndex)
    {
        switch(columnIndex)
        {
            case 0: return giangVien.getListLopDocLap().get(rowIndex).getMaLop();
            case 1: return giangVien.getListLopDocLap().get(rowIndex).getTenMonHoc();
            case 2: return giangVien.getListLopDocLap().get(rowIndex).getHeDaoTao();
            case 3: return giangVien.getListLopDocLap().get(rowIndex).getKhoaDaoTao();
            case 4: return giangVien.getListLopDocLap().get(rowIndex).getLichLyThuyet();
            case 5: return giangVien.getListLopDocLap().get(rowIndex).getPhongLyThuyet();
            case 6: return giangVien.getListLopDocLap().get(rowIndex).getSoLuongHSSV();
            default :return null;
        }
    }
    @Override
    public Class getColumnClass(int columnIndex)
    {
        return classes[columnIndex];
    }

    @Override
    public String getColumnName(int column)
    {
        return name[column];
    }

}

