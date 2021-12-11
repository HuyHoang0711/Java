/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form;
import db.Database;
import exception.KiemTraDuLieu;
import manipulatedb.LDLController;
import classname.GiangVien;
import classname.LopDocLap;
import tablemodel.QLLopTable;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;
import javax.swing.DefaultComboBoxModel;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import manipulatedb.DKPController;
/**
 *
 * @author Admin
 */
public class QuanLyLop extends javax.swing.JFrame {
    private ArrayList<GiangVien> listGiangVien = new ArrayList<>();
    private ArrayList<GiangVien> listGiangVien_temp = new ArrayList<>();
    private LopDocLap ldlCu = new LopDocLap();
    private GiangVien gv = new GiangVien();
    private Calendar cld = Calendar.getInstance();
    private int selectedRow = 0;
    private int countClicked = 0;
    /**
     * Creates new form QuanLyLop
     */
    public QuanLyLop() {
        initComponents();
        setTitle("Quản lý lớp độc lập");
        setLocationRelativeTo(null);
        loadDuLieu();
        taoBanSao();
        loadComboGV();
        khoiTaoLichLyThuyet();
        
    }
    public void loadTable(GiangVien gv)
    {
        tableLopDocLap.setModel(new QLLopTable(gv));
    }
    public void loadComboGV()
    {
        
        Iterator<GiangVien> myIterator = listGiangVien.iterator();
         while(myIterator.hasNext())
         {
             comboGV.addItem(myIterator.next().getMaNhanVien());
         }
      
    }
    public void khoiTaoComboBox()
    {
        comboKhoa.removeAllItems();
        int khoangCachNam = cld.get(Calendar.YEAR) - 2018;
        int dx = 10 + khoangCachNam;
        int cx = 17 + khoangCachNam;
        String[] daiHoc = {"K"+String.valueOf(dx-1), "K"+String.valueOf(dx), "K"+String.valueOf(dx+1), "K"+String.valueOf(dx+2)};
        String [] caoDang = {"K"+String.valueOf(cx-1), "K"+String.valueOf(cx), "K"+String.valueOf(cx+1)};
        if(getRadioSelected().equals("Dai hoc"))
        comboKhoa.setModel(new DefaultComboBoxModel(daiHoc));
        else
        comboKhoa.setModel(new DefaultComboBoxModel(caoDang));
    }
    public void khoiTaoLichLyThuyet()
    {
        String[] lich = {"S2", "C2", "T2", "S3", "C3", "T3", "S4", "C4", "T4", "S5", "C5", "T5", "S6", "C6", "T6", "S7", "C7", "T7", "S8", "C8", "T8"};
        comboLich.setModel(new DefaultComboBoxModel(lich));
    }
    public String getRadioSelected()
    {
        if(radioDaiHoc.isSelected())
            return radioDaiHoc.getAccessibleContext().getAccessibleName();
        else
            return radioCaoDang.getAccessibleContext().getAccessibleName();
    }
    public void loadDuLieu()
    {
        String sql = "SELECT gv.MaGiangVien, gv.HoTen, ldl.* FROM GIANGVIEN gv INNER JOIN LOPDOCLAP ldl "
                          + "ON gv.MaGiangVien = ldl.MaGiangVien ORDER BY gv.MaGiangVien ASC";
        String maGiangVien = "";
        String hoTen = "";
        try{
        ResultSet rs = Database.getData(sql);
        GiangVien gvx = new GiangVien();
        while(rs.next())
        {
           // System.out.println(rs.getString("MaGiangVien"));
            if(!rs.getString("MaGiangVien").equals(maGiangVien))
           {
               maGiangVien = rs.getString("MaGiangVien");
               //System.out.println("hello World");
               gvx = new GiangVien();
           }
            gvx.setMaNhanVien(rs.getString("MaGiangVien"));           
            gvx.setHoTen(rs.getString("HoTen"));
            gvx.getListLopDocLap().add(new LopDocLap(rs.getString("MaLop"), rs.getString("TenMonHoc"), rs.getString("HeDaoTao"), 
                                                    rs.getString("KhoaDaoTao"), rs.getString("LichLyThuyet"), rs.getString("PhongLyThuyet"), rs.getInt("SoLuongSV")));
               if(!listGiangVien.contains(gvx))
                   listGiangVien.add(gvx);
           
        }   
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Tải dữ liệu từ CSDL thất bại!", "Thông báo", JOptionPane.ERROR_MESSAGE);
                    
        }
    }
    public void taoBanSao()
    {
        ArrayList<LopDocLap> listLopDocLap;
        for(int i=0; i<listGiangVien.size(); i++)
        {
          listLopDocLap = new ArrayList<>(listGiangVien.get(i).getListLopDocLap());
          GiangVien gv = new GiangVien(listGiangVien.get(i).getMaNhanVien(), listGiangVien.get(i).getHoTen());
          listGiangVien_temp.add(gv);
          listGiangVien_temp.get(i).setListLopDocLap(listLopDocLap);
        }
    }
    public void them(int viTriThem)
    {
        taoBanSao();
        gv.setMaNhanVien(String.valueOf(comboGV.getSelectedItem()));
        String heDaoTao = "";
        if(getRadioSelected().equals("Dai hoc"))
            heDaoTao = radioDaiHoc.getText();
        else
            heDaoTao = radioCaoDang.getText();
        LopDocLap ldl = new LopDocLap(txtMaLop.getText(), txtTenMon.getText(), heDaoTao, String.valueOf(comboKhoa.getSelectedItem()),
                                                            String.valueOf(comboLich.getSelectedItem()), txtPhong.getText(), (int)spinnerSoLuongSV.getValue());
        if(viTriThem != -1)
        listGiangVien.get(listGiangVien.indexOf(gv)).getListLopDocLap().add(viTriThem, ldl);
        else
        listGiangVien.get(listGiangVien.indexOf(gv)).getListLopDocLap().add(ldl);
        LDLController.insert_ldl(txtMaLop.getText(), txtTenMon.getText(), heDaoTao, String.valueOf(comboKhoa.getSelectedItem()),
                                            String.valueOf(comboLich.getSelectedItem()), txtPhong.getText(), 
                                            (int)spinnerSoLuongSV.getValue(), String.valueOf(comboGV.getSelectedItem()));
        ldlCu.setMaLop(txtMaLop.getText());
        loadTable(listGiangVien.get(listGiangVien.indexOf(gv)));
    }
    public void sua()
    {
        taoBanSao();
        gv.setMaNhanVien(String.valueOf(comboGV.getSelectedItem()));
        int viTriCu = 0;
        viTriCu = listGiangVien.get(listGiangVien.indexOf(gv)).getListLopDocLap().indexOf(ldlCu);
        listGiangVien.get(listGiangVien.indexOf(gv)).getListLopDocLap().remove(viTriCu);
       LDLController.delete_ldl(ldlCu.getMaLop());
       DKPController.update_dkp_malop(ldlCu.getMaLop(), txtMaLop.getText());
        them(viTriCu);
    }
    public void xoa()
    {
        taoBanSao();
        gv.setMaNhanVien(String.valueOf(comboGV.getSelectedItem()));
        LopDocLap ldl = new LopDocLap(txtMaLop.getText());
        listGiangVien.get(listGiangVien.indexOf(gv)).getListLopDocLap().remove(listGiangVien.get(listGiangVien.indexOf(gv)).getListLopDocLap().indexOf(ldl));
        LDLController.delete_ldl(txtMaLop.getText());
        DKPController.delete_dkp_malop(txtMaLop.getText());
        loadTable(listGiangVien.get(listGiangVien.indexOf(gv)));
    }
    public boolean kiemTraTrong() throws KiemTraDuLieu
    {
       if(txtMaLop.getText().equals(""))
       {
           txtMaLop.requestFocus();
           throw new KiemTraDuLieu("Mã lớp không được để trống!");
       }
       if(txtTenMon.getText().equals(""))
       {
           txtTenMon.requestFocus();
           throw new KiemTraDuLieu("Tên môn học không được để trống!");
       }
       if(txtPhong.getText().equals(""))
       {
           txtPhong.requestFocus();
           throw new KiemTraDuLieu("Phòng không được để trống!");
       }
       if(!txtMaLop.getText().matches("\\d{15}"))
       {
           txtMaLop.requestFocus();
           throw new KiemTraDuLieu("Định dạng mã lớp không hợp lệ!");
       }
       if(!txtPhong.getText().matches("P\\d{3}-[ABC]\\d{1}"))
       {
           txtPhong.requestFocus();
           throw new KiemTraDuLieu("Định dạng phòng lý thuyết không hợp lệ (302-A9)!");
       }
        if((int)spinnerSoLuongSV.getValue() < 30)
       {
            spinnerSoLuongSV.requestFocus();
           throw new KiemTraDuLieu("Số lượng sinh viên tối thiếu là 30!");
       }
       return true;
    }
    public boolean kiemTraTonTai()
    {
      gv.setMaNhanVien((String)comboGV.getSelectedItem());
      if(listGiangVien.get(listGiangVien.indexOf(gv)).getListLopDocLap().contains(new LopDocLap(txtMaLop.getText())))
          return false;
      return true;
    }
    public boolean kiemTraTrungLich()
    {       
        int i = 0;
       while(i < comboGV.getItemCount())
       {
       gv.setMaNhanVien(comboGV.getItemAt(i));
       LopDocLap ldl = new LopDocLap(txtMaLop.getText(), txtPhong.getText(), (String)comboLich.getSelectedItem());
       Iterator<LopDocLap> myIterator = listGiangVien.get(listGiangVien.indexOf(gv)).getListLopDocLap().iterator();
       while(myIterator.hasNext())
       {
           if(myIterator.next().getKiemTra().equals(ldl.getKiemTra()))
           {
               return false;
           }
       }
       i++;
       }
       return true;
    }
    public void thucHienThaoTac(String thaoTac)
    {
        try
        {
        if(kiemTraTrong())
        {
            if(thaoTac.equals("insert") || thaoTac.equals("update"))
            {
            if(kiemTraTonTai())
            {
                if(kiemTraTrungLich())
                {
                if(thaoTac.equals("insert"))
                {
                    them(-1);
                    return;
                }
                if(thaoTac.equals("update"))
                {
                    sua();
                    return;
                }
                }
                else
                {
                 JOptionPane.showMessageDialog(null, "Lịch lý thuyết và phòng lý thuyết trùng với lớp khác!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                 return;
                }
            }
            else
            {
                 JOptionPane.showMessageDialog(null, "Lớp độc lập đã tồn tại!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                 return;
            }
          }
            if(thaoTac.equals("delete"))
            {
                if(kiemTraTonTai() == false)
                {
                    int luaChon = JOptionPane.showConfirmDialog(null, "Bạn chắc chắn muốn xóa bản ghi này!", "Xác nhận", 
                                          JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
                    if(luaChon == JOptionPane.OK_OPTION)
                    xoa();
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Lớp độc lập không tồn tại!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                }
            }
        }
        }
        catch(KiemTraDuLieu ex)
        {
             JOptionPane.showMessageDialog(null, ex.getMessage(), "Cảnh báo", JOptionPane.WARNING_MESSAGE);
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableLopDocLap = new javax.swing.JTable();
        jLayeredPane2 = new javax.swing.JLayeredPane();
        jPanel9 = new javax.swing.JPanel();
        comboGV = new javax.swing.JComboBox<>();
        jPanel10 = new javax.swing.JPanel();
        txtTenGV = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        txtMaLop = new javax.swing.JTextField();
        radioDaiHoc = new javax.swing.JRadioButton();
        radioCaoDang = new javax.swing.JRadioButton();
        txtTenMon = new javax.swing.JTextField();
        comboKhoa = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        txtPhong = new javax.swing.JTextField();
        comboLich = new javax.swing.JComboBox<>();
        spinnerSoLuongSV = new javax.swing.JSpinner();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        btThem = new javax.swing.JButton();
        btSua = new javax.swing.JButton();
        btXoa = new javax.swing.JButton();
        btClear = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        btQuayLai = new javax.swing.JButton();
        jPanel14 = new javax.swing.JPanel();
        btLamMoi = new javax.swing.JButton();
        btSapXep = new javax.swing.JButton();
        btRedo = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        jLabel1.setText("QUẢN LÝ LỚP ĐỘC LẬP");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(126, 126, 126))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tableLopDocLap.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        tableLopDocLap.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tableLopDocLap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableLopDocLapMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableLopDocLap);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        comboGV.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        comboGV.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboGVItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(comboGV, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(95, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGap(0, 9, Short.MAX_VALUE)
                .addComponent(comboGV, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        txtTenGV.setEditable(false);
        txtTenGV.setBackground(new java.awt.Color(255, 255, 255));
        txtTenGV.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtTenGV.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addGap(0, 27, Short.MAX_VALUE)
                .addComponent(txtTenGV, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtTenGV, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel9.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel9.setText("Mã giảng viên");

        jLayeredPane2.setLayer(jPanel9, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(jPanel10, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(jLabel9, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane2Layout = new javax.swing.GroupLayout(jLayeredPane2);
        jLayeredPane2.setLayout(jLayeredPane2Layout);
        jLayeredPane2Layout.setHorizontalGroup(
            jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(88, Short.MAX_VALUE))
            .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane2Layout.createSequentialGroup()
                    .addContainerGap(92, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(103, Short.MAX_VALUE)))
        );
        jLayeredPane2Layout.setVerticalGroup(
            jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jLayeredPane2Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(jLabel9)))
                .addContainerGap(51, Short.MAX_VALUE))
            .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane2Layout.createSequentialGroup()
                    .addContainerGap(48, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(19, Short.MAX_VALUE)))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtMaLop.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N

        buttonGroup1.add(radioDaiHoc);
        radioDaiHoc.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        radioDaiHoc.setSelected(true);
        radioDaiHoc.setText("Đại học");
        radioDaiHoc.setToolTipText("");
        radioDaiHoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioDaiHocActionPerformed(evt);
            }
        });

        buttonGroup1.add(radioCaoDang);
        radioCaoDang.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        radioCaoDang.setText("Cao đẳng");
        radioCaoDang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioCaoDangActionPerformed(evt);
            }
        });

        txtTenMon.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N

        comboKhoa.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel2.setText("Mã lớp");

        jLabel3.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel3.setText("Tên môn học");

        jLabel4.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel4.setText("Hệ đào tạo");

        jLabel5.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel5.setText("Khóa đào tạo");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(comboKhoa, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMaLop, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                .addComponent(radioDaiHoc)
                                .addGap(18, 18, 18)
                                .addComponent(radioCaoDang)
                                .addGap(26, 26, 26)))
                        .addGap(32, 32, 32))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(txtTenMon, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMaLop, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTenMon, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(15, 15, 15)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radioCaoDang, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(radioDaiHoc)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(comboKhoa, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
        );

        radioDaiHoc.getAccessibleContext().setAccessibleName("Dai hoc");
        radioCaoDang.getAccessibleContext().setAccessibleName("Cao dang");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setPreferredSize(new java.awt.Dimension(221, 200));

        jPanel11.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtPhong.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N

        comboLich.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N

        spinnerSoLuongSV.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        spinnerSoLuongSV.setModel(new javax.swing.SpinnerNumberModel(0, null, null, 5));

        jLabel7.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel7.setText("Phòng lý thuyết");

        jLabel6.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel6.setText("Lịch lý thuyết");
        jLabel6.setToolTipText("");

        jLabel8.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel8.setText("Số lượng sinh viên");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(spinnerSoLuongSV, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboLich, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboLich, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spinnerSoLuongSV, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addContainerGap(53, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel13.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btThem.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        btThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Add.png"))); // NOI18N
        btThem.setText("Thêm");
        btThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btThemActionPerformed(evt);
            }
        });

        btSua.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        btSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Gear.png"))); // NOI18N
        btSua.setText("Sửa");
        btSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSuaActionPerformed(evt);
            }
        });

        btXoa.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        btXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Dustbin.png"))); // NOI18N
        btXoa.setText("Xóa");
        btXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btXoaActionPerformed(evt);
            }
        });

        btClear.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        btClear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Eraser.png"))); // NOI18N
        btClear.setText("Clear");
        btClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btClearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btThem, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btSua, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btClear, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btThem, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btSua, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btClear, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        btQuayLai.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        btQuayLai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Back-arrow.png"))); // NOI18N
        btQuayLai.setText("Quay lại");
        btQuayLai.setToolTipText("");
        btQuayLai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btQuayLaiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btQuayLai, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addComponent(btQuayLai, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        btLamMoi.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        btLamMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Back-arrow.png"))); // NOI18N
        btLamMoi.setText("Undo");
        btLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btLamMoiActionPerformed(evt);
            }
        });

        btSapXep.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        btSapXep.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Sort.png"))); // NOI18N
        btSapXep.setText("Sắp xếp");
        btSapXep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSapXepActionPerformed(evt);
            }
        });

        btRedo.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        btRedo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Continue-arrow.png"))); // NOI18N
        btRedo.setText("Redo");
        btRedo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRedoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btRedo, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addComponent(btSapXep, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(btLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btSapXep, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btRedo, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(189, 189, 189)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLayeredPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(25, 25, 25))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLayeredPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(111, 111, 111)
                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btQuayLaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btQuayLaiActionPerformed
        // TODO add your handling code here:
        new GiaoDienQT().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btQuayLaiActionPerformed

    private void btSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSuaActionPerformed
        // TODO add your handling code here:
        thucHienThaoTac("update");
    }//GEN-LAST:event_btSuaActionPerformed

    private void btXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btXoaActionPerformed
        // TODO add your handling code here:
        thucHienThaoTac("delete");
    }//GEN-LAST:event_btXoaActionPerformed

    private void btClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btClearActionPerformed
        // TODO add your handling code here:
         txtMaLop.setText("");
        txtTenMon.setText("");
        radioDaiHoc.setSelected(true);
        khoiTaoComboBox();
        comboKhoa.setSelectedIndex(0);
        comboLich.setSelectedIndex(0);
        txtPhong.setText("");
        spinnerSoLuongSV.setValue(0);
    }//GEN-LAST:event_btClearActionPerformed

    private void comboGVItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboGVItemStateChanged
        // TODO add your handling code here:
       gv.setMaNhanVien((String)comboGV.getSelectedItem());
       txtTenGV.setText(listGiangVien.get(listGiangVien.indexOf(gv)).getHoTen());
       loadTable(listGiangVien.get(listGiangVien.indexOf(gv)));
    }//GEN-LAST:event_comboGVItemStateChanged

    private void radioDaiHocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioDaiHocActionPerformed
        // TODO add your handling code here:
        khoiTaoComboBox();
    }//GEN-LAST:event_radioDaiHocActionPerformed

    private void radioCaoDangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioCaoDangActionPerformed
        // TODO add your handling code here:
        khoiTaoComboBox();
    }//GEN-LAST:event_radioCaoDangActionPerformed

    private void btThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btThemActionPerformed
        // TODO add your handling code here:
        thucHienThaoTac("insert");
    }//GEN-LAST:event_btThemActionPerformed

    private void tableLopDocLapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableLopDocLapMouseClicked
        // TODO add your handling code here:
        countClicked++;
        selectedRow = tableLopDocLap.getSelectedRow();
        txtMaLop.setText((String)tableLopDocLap.getValueAt(selectedRow, 0));
        txtTenMon.setText((String)tableLopDocLap.getValueAt(selectedRow, 1));
        if(String.valueOf(tableLopDocLap.getValueAt(selectedRow, 2)).charAt(String.valueOf(tableLopDocLap.getValueAt(selectedRow, 2)).length()-1) == 'c')
            radioDaiHoc.setSelected(true);
        else
            radioCaoDang.setSelected(true);
        comboKhoa.removeAllItems();
        comboKhoa.addItem((String)tableLopDocLap.getValueAt(selectedRow, 3));
        comboLich.setSelectedItem(String.valueOf(tableLopDocLap.getValueAt(selectedRow, 4)));
        txtPhong.setText(String.valueOf(tableLopDocLap.getValueAt(selectedRow, 5)));
        spinnerSoLuongSV.setValue(tableLopDocLap.getValueAt(selectedRow, 6));
        ldlCu.setMaLop(txtMaLop.getText());
    }//GEN-LAST:event_tableLopDocLapMouseClicked
public void sapXep(String cachSapXep)
    {
       Comparator<LopDocLap> c = new Comparator<LopDocLap>()
       {
         @Override
         public int compare(LopDocLap ldl1, LopDocLap ldl2)
         {
             if(ldl1.getMaLop().compareToIgnoreCase(ldl2.getMaLop()) == 0)
             {
                 return ldl1.getTenMonHoc().compareToIgnoreCase(ldl2.getTenMonHoc());
             }
             else
             return ldl1.getMaLop().compareToIgnoreCase(ldl2.getMaLop());
         }

       };
       gv.setMaNhanVien(String.valueOf(comboGV.getSelectedItem()));
       if(cachSapXep.equals("ASC"))
       {
         Collections.sort(listGiangVien.get(listGiangVien.indexOf(gv)).getListLopDocLap(), c);
       }
       else
       {
              Collections.sort(listGiangVien.get(listGiangVien.indexOf(gv)).getListLopDocLap(), c);
              Collections.reverse(listGiangVien.get(listGiangVien.indexOf(gv)).getListLopDocLap());
           
       }
          loadTable(listGiangVien.get(listGiangVien.indexOf(gv)));
       }
    private void btSapXepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSapXepActionPerformed
        // TODO add your handling code here:
        String luaChon = (String)JOptionPane.showInputDialog(null, "Đưa ra lựa chọn sắp xếp", "Thông báo", JOptionPane.YES_NO_OPTION, 
                                                                                new ImageIcon(""), new String[]{"ASC", "DESC"}, "ASC");
        if(luaChon == null)
            return;
            sapXep(luaChon);
    }//GEN-LAST:event_btSapXepActionPerformed

    private void btLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLamMoiActionPerformed
        // TODO add your handling code here:
        GiangVien gvx = new GiangVien(String.valueOf(comboGV.getSelectedItem()));
        loadTable(listGiangVien_temp.get(listGiangVien_temp.indexOf(gvx)));
    }//GEN-LAST:event_btLamMoiActionPerformed

    private void btRedoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRedoActionPerformed
        // TODO add your handling code here:
        GiangVien gvx = new GiangVien(String.valueOf(comboGV.getSelectedItem()));
        loadTable(listGiangVien.get(listGiangVien.indexOf(gvx)));
        
    }//GEN-LAST:event_btRedoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(QuanLyLop.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QuanLyLop.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QuanLyLop.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuanLyLop.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QuanLyLop().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btClear;
    private javax.swing.JButton btLamMoi;
    private javax.swing.JButton btQuayLai;
    private javax.swing.JButton btRedo;
    private javax.swing.JButton btSapXep;
    private javax.swing.JButton btSua;
    private javax.swing.JButton btThem;
    private javax.swing.JButton btXoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> comboGV;
    private javax.swing.JComboBox<String> comboKhoa;
    private javax.swing.JComboBox<String> comboLich;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLayeredPane jLayeredPane2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton radioCaoDang;
    private javax.swing.JRadioButton radioDaiHoc;
    private javax.swing.JSpinner spinnerSoLuongSV;
    private javax.swing.JTable tableLopDocLap;
    private javax.swing.JTextField txtMaLop;
    private javax.swing.JTextField txtPhong;
    private javax.swing.JTextField txtTenGV;
    private javax.swing.JTextField txtTenMon;
    // End of variables declaration//GEN-END:variables
}
