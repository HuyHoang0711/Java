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
public class QLGVTable extends AbstractTableModel{
  
    //Khai báo xâu chứa tiêu đề của bảng.
    private String name[]={"Mã giảng viên", "Họ tên", "Giới tính", "Số điện thoại", "Giảng viên ngành", "Tài khoản", "Mật khẩu"};
    //Khai báo lớp Chứa kiểu dữ liệu của từng trường tương ứng.
    private Class classes[]={String.class,String.class,String.class,String.class,String.class,String.class, String.class};
    //Tạo một đối tượng arrayList có tên listThiSinh.
    ArrayList<GiangVien> listGV=new ArrayList<GiangVien>();

    //phương thức khởi tạo cho class có tham số truyền vào.
    public QLGVTable(ArrayList<GiangVien> listGV)
   {
       this.listGV=listGV;
   }
    //lấy số phần tử của listThiSinh
    @Override
    public int getRowCount()
    {
        return listGV.size();
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
            case 0: return listGV.get(rowIndex).getMaNhanVien();
            case 1: return listGV.get(rowIndex).getHoTen();
            case 2: return listGV.get(rowIndex).getGioiTinh();
            case 3: return listGV.get(rowIndex).getSoDienThoai();
            case 4: return listGV.get(rowIndex).getGiangVienNganh();
            case 5: return listGV.get(rowIndex).getTaiKhoanGV().getTenDangNhap();
            case 6: return listGV.get(rowIndex).getTaiKhoanGV().getMatKhau();
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
