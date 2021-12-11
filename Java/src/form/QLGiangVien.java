/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form;
import db.Database;
import exception.KiemTraDuLieu;
import manipulatedb.GVController;
import tablemodel.QLGVTable;
import classname.GiangVien;
import classname.TaiKhoan;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.sql.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author ASUS
 */
public class QLGiangVien extends javax.swing.JFrame {
    private String maGiangVien = "";
    private String nganhQuanLy = "";
    private String hoTen = "";
    private String gioiTinh = "";
    private String soDienThoai = "";
    private String giangVienNganh = "";
    private String taiKhoan = "";
    private String matKhau = "";
    ArrayList<GiangVien> listGV = new ArrayList<GiangVien>();
    int selectedRow = 0;
    
    /**
     * Creates new form QLGiangVien
     */
    public QLGiangVien() {
        initComponents();
        setLocationRelativeTo(null);
        setTitle("Quản lý giảng viên");
        loadDatabase();
        loadTable();
        loadNganh();
    } 
    public void AC()
    {
        txt_magv.setEnabled(true);
        txt_magv.setText("");
        txt_tengv.setText("");
        txt_sdt.setText("");
        txt_pass.setText("");
        txt_tk.setText("");
        txt_Nganh.setText("");
        buttonGroup1.clearSelection();
    }
    public void loadNganh()
    {
       
        String sql = "SELECT NganhQuanLy FROM NGUOIQUANTRI WHERE MaNhanVien = '"+DangNhap.getMaNhanVien()+"'";
        try{
            ResultSet rs = Database.getData(sql);
            while(rs.next())
            {
                nganhQuanLy = rs.getString("NganhQuanLy");
            }
        }
        catch(SQLException ex)
        {
           ex.printStackTrace();
        }
    }
    public void loadTable(){
        jTable1.setModel(new QLGVTable(listGV));
    }
    public boolean kiemTraTrong() throws KiemTraDuLieu
    {
        if(txt_magv.getText().isEmpty()){
            txt_magv.requestFocus();
            throw new KiemTraDuLieu("Bạn phải nhập mã giảng viên!");
        }
        if(txt_tengv.getText().isEmpty()){
            txt_tengv.requestFocus();
            throw new KiemTraDuLieu("Bạn phải nhập tên giảng viên!");
          }
        if(txt_sdt.getText().isEmpty()){
            txt_sdt.requestFocus();
             throw new KiemTraDuLieu("Bạn phải nhập số điện thoại!");
          }
        if(txt_tk.getText().isEmpty()){
            txt_tk.requestFocus();
            throw new KiemTraDuLieu("Bạn phải nhập tài khoản giảng viên!");
        }
         if(String.valueOf(txt_pass.getPassword()).isEmpty()){
             txt_pass.requestFocus();
             throw new KiemTraDuLieu("Bạn phải nhập mật khẩu giảng viên!");
         }
        return true; 
    }
    public boolean kiemTraTonTai() throws KiemTraDuLieu
    {
        GiangVien gv = new GiangVien(txt_magv.getText());
        if(listGV.contains(gv))
        {
             return false;
        }
        return true;
    }
    public void them(){ 
        txt_Nganh.setText(nganhQuanLy);
        txt_Nganh.setEditable(false);
        maGiangVien = txt_magv.getText();     
        hoTen = txt_tengv.getText();
        soDienThoai = txt_sdt.getText();
        matKhau = String.valueOf(txt_pass.getPassword());
         taiKhoan = txt_tk.getText();
         if(jradio_nam.isSelected()==true)
            gioiTinh = "Nam";
        else if(jRadio_nu.isSelected()==true)
            gioiTinh = "Nữ";
         giangVienNganh = txt_Nganh.getText();
        GiangVien n = new GiangVien(maGiangVien, hoTen, gioiTinh, soDienThoai, giangVienNganh, taiKhoan, matKhau);
        listGV.add(n);
        loadTable();
        GVController.insert_gv(maGiangVien, hoTen, taiKhoan, matKhau, gioiTinh, soDienThoai, giangVienNganh, DangNhap.getMaNhanVien());
      
    }
    public void sua()
    {
        txt_Nganh.setText(nganhQuanLy);
        txt_Nganh.setEditable(false);
        selectedRow = jTable1.getSelectedRow();
        maGiangVien = txt_magv.getText();
         hoTen = txt_tengv.getText();
        matKhau = String.valueOf(txt_pass.getPassword());
        taiKhoan = txt_tk.getText();
        soDienThoai = txt_sdt.getText();
         if(jradio_nam.isSelected()==true)
            gioiTinh = "Nam";
        else if(jRadio_nu.isSelected()==true)
            gioiTinh = "Nữ";
         giangVienNganh = txt_Nganh.getText();
        GiangVien gvm = new GiangVien(maGiangVien, hoTen, gioiTinh, soDienThoai, giangVienNganh, taiKhoan, matKhau);
        listGV.set(selectedRow, gvm);
        GVController.update_gv(maGiangVien, hoTen, taiKhoan, matKhau, gioiTinh, soDienThoai, giangVienNganh, DangNhap.getMaNhanVien());
        loadTable();
    }
   
   public void loadDatabase(){
        String sql= "SELECT * FROM GIANGVIEN";
        try {
            ResultSet re = Database.getData(sql);
            while(re.next()){
                GiangVien gv = new GiangVien();
                gv.setMaNhanVien(re.getString("MaGiangVien"));
                gv.setHoTen(re.getString("HoTen"));
                gv.setGioiTinh(re.getString("GioiTinh")); 
                gv.setSoDienThoai(re.getString("SoDienThoai"));
                gv.setGiangVienNganh(re.getString("GiangVienNganh"));
                gv.getTaiKhoanGV().setMatKhau(re.getString("MatKhau"));
                gv.getTaiKhoanGV().setTenDangNhap(re.getString("TaiKhoan"));
                listGV.add(gv);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Tải dữ liệu từ CSDL thất bại!", "Thông báo", JOptionPane.ERROR_MESSAGE);
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
        jLabel9 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        txt_magv = new javax.swing.JTextField();
        txt_tengv = new javax.swing.JTextField();
        txt_sdt = new javax.swing.JTextField();
        txt_tk = new javax.swing.JTextField();
        txt_pass = new javax.swing.JPasswordField();
        jradio_nam = new javax.swing.JRadioButton();
        jRadio_nu = new javax.swing.JRadioButton();
        txt_Nganh = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        butSua = new javax.swing.JButton();
        butAC = new javax.swing.JButton();
        butThem = new javax.swing.JButton();
        butXoa = new javax.swing.JButton();
        butTim = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        butThoat = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel9.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel9.setText("Tài Khoản");

        jLabel1.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel1.setText("Mã Giảng Viên");

        jLabel2.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel2.setText("Tên Giảng Viên ");

        jLabel4.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel4.setText("Số Điện Thoại ");

        jLabel8.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel8.setText("Mật Khẩu ");

        jLabel3.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel3.setText("Giới tinh");

        jLabel5.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel5.setText("Giảng Viên Ngành");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel1))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(43, 43, 43)
                .addComponent(jLabel2)
                .addGap(42, 42, 42)
                .addComponent(jLabel3)
                .addGap(28, 28, 28)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(jLabel4)
                .addGap(38, 38, 38)
                .addComponent(jLabel9)
                .addGap(44, 44, 44)
                .addComponent(jLabel8)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        txt_magv.setBackground(new java.awt.Color(255, 255, 255));
        txt_magv.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txt_magv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_magvActionPerformed(evt);
            }
        });

        txt_tengv.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txt_tengv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_tengvActionPerformed(evt);
            }
        });

        txt_sdt.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txt_sdt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_sdtActionPerformed(evt);
            }
        });

        txt_tk.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txt_tk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_tkActionPerformed(evt);
            }
        });

        txt_pass.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N

        buttonGroup1.add(jradio_nam);
        jradio_nam.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jradio_nam.setText("Nam");

        buttonGroup1.add(jRadio_nu);
        jRadio_nu.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jRadio_nu.setText("Nữ");

        txt_Nganh.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N

        butSua.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        butSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Pencil.png"))); // NOI18N
        butSua.setText("Sửa");
        butSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butSuaActionPerformed(evt);
            }
        });

        butAC.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        butAC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Eraser.png"))); // NOI18N
        butAC.setText("AC");
        butAC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butACActionPerformed(evt);
            }
        });

        butThem.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        butThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Add-user.png"))); // NOI18N
        butThem.setText("Thêm");
        butThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butThemActionPerformed(evt);
            }
        });

        butXoa.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        butXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Dustbin.png"))); // NOI18N
        butXoa.setText("Xóa ");
        butXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butXoaActionPerformed(evt);
            }
        });

        butTim.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        butTim.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Loupe.png"))); // NOI18N
        butTim.setText("Tìm Kiếm ");
        butTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butTimActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Refresh.png"))); // NOI18N
        jButton1.setText("ReLoad");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(butTim, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(butThem, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(butSua, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(butXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(118, 118, 118)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(butAC, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(butThem, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(butAC, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(butSua, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addComponent(butXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(butTim, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_magv, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_Nganh, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_sdt, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jradio_nam, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jRadio_nu, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txt_tengv, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_tk, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_pass, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txt_magv, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(txt_tengv, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jradio_nam)
                            .addComponent(jRadio_nu))
                        .addGap(18, 18, 18)
                        .addComponent(txt_Nganh, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(txt_sdt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(txt_tk, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txt_pass, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );

        jTable1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        butThoat.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        butThoat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Back-arrow.png"))); // NOI18N
        butThoat.setText("Quay lại");
        butThoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butThoatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(butThoat, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 794, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addComponent(butThoat, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel6.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        jLabel6.setText("Quản Lí Giảng Viên ");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jLabel6)
                .addContainerGap(33, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel6)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(303, 303, 303)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_magvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_magvActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_magvActionPerformed

    private void txt_tengvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_tengvActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_tengvActionPerformed

    private void txt_sdtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_sdtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_sdtActionPerformed

    private void butACActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butACActionPerformed
        AC();
    }//GEN-LAST:event_butACActionPerformed

    private void butThoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butThoatActionPerformed
       // int ret = JOptionPane.showConfirmDialog(null, "bạn có chắc chắn muốn thoát? ", "THOÁT", JOptionPane.YES_NO_OPTION);
      //  if(ret==JOptionPane.YES_OPTION)
        //{
        new GiaoDienQT().setVisible(true);
        this.dispose();
       // } 
           
    }//GEN-LAST:event_butThoatActionPerformed

    private void butThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butThemActionPerformed
           try{ 
           if(kiemTraTrong())
            {
                if(kiemTraTonTai())
                {
                  if(!taiKhoan.equals(txt_tk.getText()))
                  them();
                  else
                  {
                      JOptionPane.showMessageDialog(null, "Tên tài khoản đã tồn tại!");
                      return;
                  }
                }
                else
                    JOptionPane.showMessageDialog(null, "Giảng viên đã tồn tại!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                
            }
           }
           catch(KiemTraDuLieu ex)
           {
               JOptionPane.showMessageDialog(null, ex.getMessage(), "Cảnh báo", JOptionPane.WARNING_MESSAGE);
           }
    }//GEN-LAST:event_butThemActionPerformed

    private void butSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butSuaActionPerformed
        
        try{
        if(kiemTraTrong())
        { 
            if(!maGiangVien.equals(txt_magv.getText()))
            {
            JOptionPane.showMessageDialog(rootPane, "Không cho phép sửa mã giảng viên");
            txt_magv.setText(maGiangVien);
            return;
            }    
            else
            sua();
        }
        }
        catch(KiemTraDuLieu ex)
        {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Cảnh báo", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_butSuaActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked

        selectedRow = jTable1.getSelectedRow();
        GiangVien n = listGV.get(selectedRow);
        txt_magv.setText(n.getMaNhanVien()+"");
        maGiangVien = txt_magv.getText();
        txt_tengv.setText(n.getHoTen());
        txt_pass.setText(n.getTaiKhoanGV().getMatKhau());
        txt_tk.setText(n.getTaiKhoanGV().getTenDangNhap());
        taiKhoan = txt_tk.getText();
        txt_Nganh.setText(n.getGiangVienNganh());
        txt_sdt.setText(n.getSoDienThoai());
        if(n.getGioiTinh().equals("Nam"))
            jradio_nam.setSelected(true);
        else
            jRadio_nu.setSelected(true);
    }//GEN-LAST:event_jTable1MouseClicked

    private void butTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butTimActionPerformed
         if(txt_magv.getText().isEmpty()){
            JOptionPane.showMessageDialog(rootPane, "Bạn phải nhập mã giảng viên");
            return;
        } 
         else 
         maGiangVien = txt_magv.getText();
        GiangVien gvTim = new GiangVien(maGiangVien);
        ArrayList<GiangVien> dsTim = new ArrayList<GiangVien>();
        ArrayList<GiangVien> kqTim = new ArrayList<GiangVien>();
        if(listGV.contains(gvTim))
        {
            gvTim = listGV.get(listGV.indexOf(gvTim));
            dsTim.add(gvTim);
            jTable1.setModel(new QLGVTable(dsTim));
        }
        else {
             JOptionPane.showMessageDialog(null, "Không thấy giảng viên cần tìm");
        }
       
    }//GEN-LAST:event_butTimActionPerformed

    private void butXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butXoaActionPerformed
       try{
        if(kiemTraTrong())
        {
        if(kiemTraTonTai() == false)
        {
        int luaChon = JOptionPane.showConfirmDialog(null, "Bạn chắc chắn muốn xóa bản ghi này!", "Xác nhận", 
                                                                                JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
        if(luaChon == JOptionPane.OK_OPTION)
        {
        GiangVien gv = new GiangVien(txt_magv.getText());
        listGV.remove(gv);
        GVController.delete_gv(txt_magv.getText());
        loadTable();
        }
        }
        else
            JOptionPane.showMessageDialog(null, "Giảng viên không tồn tại!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
        }
       }
       catch(KiemTraDuLieu ex)
       {
           JOptionPane.showMessageDialog(null, ex.getMessage(), "Cảnh báo", JOptionPane.WARNING_MESSAGE);
       }
     
    }//GEN-LAST:event_butXoaActionPerformed

    private void txt_tkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_tkActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_tkActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        loadTable();
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(QLGiangVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QLGiangVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QLGiangVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QLGiangVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QLGiangVien().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton butAC;
    private javax.swing.JButton butSua;
    private javax.swing.JButton butThem;
    private javax.swing.JButton butThoat;
    private javax.swing.JButton butTim;
    private javax.swing.JButton butXoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JRadioButton jRadio_nu;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JRadioButton jradio_nam;
    private javax.swing.JTextField txt_Nganh;
    private javax.swing.JTextField txt_magv;
    private javax.swing.JPasswordField txt_pass;
    private javax.swing.JTextField txt_sdt;
    private javax.swing.JTextField txt_tengv;
    private javax.swing.JTextField txt_tk;
    // End of variables declaration//GEN-END:variables
}
