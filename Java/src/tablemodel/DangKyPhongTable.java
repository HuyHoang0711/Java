/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tablemodel;

import java.util.ArrayList;
import classname.DangKyPhong;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author tien huy
 */
public class DangKyPhongTable extends AbstractTableModel{
  
    //Khai báo xâu chứa tiêu đề của bảng.
    private String name[]={"Mã giảng viên","Thời gian","Mã lớp","Ca","Nhóm","Phòng máy"};
    //Khai báo lớp Chứa kiểu dữ liệu của từng trường tương ứng.
    private Class classes[]={String.class,String.class,String.class,String.class,String.class,String.class};
    //Tạo một đối tượng arrayList có tên listThiSinh.
    ArrayList<DangKyPhong> listDKP=new ArrayList<DangKyPhong>();

    //phương thức khởi tạo cho class có tham số truyền vào.
    public DangKyPhongTable(ArrayList<DangKyPhong> listDKP)
   {
       this.listDKP=listDKP;
   }
    //lấy số phần tử của listThiSinh
    @Override
    public int getRowCount()
    {
        return listDKP.size();
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
            case 0: return listDKP.get(rowIndex).getMaNhanVien();
            case 1: return listDKP.get(rowIndex).getThoiGian().toString();
            case 2: return listDKP.get(rowIndex).getMaLop();
            case 3: return listDKP.get(rowIndex).getCaThucHanh();
            case 4: return listDKP.get(rowIndex).getNhom();
            case 5: return listDKP.get(rowIndex).getMaPhong();
            default :return null;
        }
    }
    @Override
    public Class getColumnClass(int columnIndex)
    {
        return classes[columnIndex];
    }

    @Override
    public String getColumnName(int columnIndex)
    {
        return name[columnIndex];
    }

}
