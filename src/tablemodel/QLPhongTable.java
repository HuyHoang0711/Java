/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tablemodel;
import classname.PhongMay;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
/**
 *
 * @author Admin
 */
public class QLPhongTable extends AbstractTableModel{
     //Khai báo xâu chứa tiêu đề của bảng.
    private String name[]={"Mã phòng","Tên phòng","Vị trí","Số máy","Cơ sở vật chất"};
    //Khai báo lớp Chứa kiểu dữ liệu của từng trường tương ứng.
    private Class classes[]={String.class,String.class,String.class,Integer.class,String.class};
    //Tạo một đối tượng arrayList có tên listThiSinh.
    ArrayList<PhongMay> listPM=new ArrayList<PhongMay>();

    //phương thức khởi tạo cho class có tham số truyền vào.
    public QLPhongTable(ArrayList<PhongMay> listPM)
   {
       this.listPM=listPM;
   }
    //lấy số phần tử của listThiSinh
    @Override
    public int getRowCount()
    {
        return listPM.size();
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
            case 0: return listPM.get(rowIndex).getMaPhong();
            case 1: return listPM.get(rowIndex).getTenPhong();
            case 2: return listPM.get(rowIndex).getViTriPhong();
            case 3: return listPM.get(rowIndex).getSoLuongMay();
            case 4: return listPM.get(rowIndex).getCoSoVatChat();
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
