/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form;
import classname.DangKyPhong;
import classname.GiangVien;
import classname.LopDocLap;
import classname.PhongMay;
import exception.KiemTraDuLieu;
import manipulatedb.DKPController;
import db.Database;
import java.awt.Point;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JTextArea;
import java.sql.Date;
import java.util.Iterator;
import javax.swing.JOptionPane;
import javax.swing.JComponent;
import javax.swing.JTextField;

/**
 *
 * @author Admin
 */
public class LapLich extends javax.swing.JFrame {
    private ArrayList<GiangVien> listGiangVien = new ArrayList<>();
    private ArrayList<Date> listThoiGian = new ArrayList<>();
    private ArrayList<GiangVien> listGiangVien_temp = new ArrayList<>();
    private ArrayList<GiangVien> listGiangVien_loc = new ArrayList<>();
    private ArrayList<LopDocLap> listLopDocLap = new ArrayList<>();
    private ArrayList<PhongMay> listPhongMay = new ArrayList<>();
    private GiangVien thongTinCu = new GiangVien();
    private int co = 0;
    private int co1 = 0;
    /**
     * Creates new form LapLich
     */
    public LapLich() {
        initComponents();
        setTitle("Lập lịch");
        setLocationRelativeTo(null);
        khoiTaoTextArea();
        khoiTaoDuLieu();
        loadDuLieu();
        locDuLieu();
        khoiTaoPhongMay();
      
    }
    public void loadDuLieu()
    {
         String sql = "SELECT ldl.HeDaoTao, ldl.KhoaDaoTao, ldl.MaLop, ldl.TenMonHoc, ldl.LichLyThuyet, dkp.ThoiGian, dkp.CaThucHanh,"
                        + " dkp.MaPhong, dkp.Nhom, gv.HoTen, gv.MaGiangVien FROM GIANGVIEN gv INNER JOIN LOPDOCLAP ldl ON gv.MaGiangVien = ldl.MaGiangVien"
                        + " INNER JOIN DANGKYPHONG dkp ON gv.MaGiangVien = dkp.MaGiangVien AND"
                        + " ldl.MaLop = dkp.MaLop";
          try{
           ResultSet rs = Database.getData(sql);
           while(rs.next())
            {
           GiangVien gv = new GiangVien();
           gv.getListLopDocLap().add(new LopDocLap(rs.getString("MaLop"), rs.getString("TenMonHoc"),
                                                                            rs.getString("HeDaoTao"), rs.getString("KhoaDaoTao"), rs.getString("LichLyThuyet")));
           gv.getListDangKyPhong().add(new DangKyPhong(rs.getString("MaPhong"), rs.getString("CaThucHanh"),
                                                                                      rs.getDate("ThoiGian"), rs.getString("Nhom")));
            
           gv.setHoTen(rs.getString("HoTen"));
           gv.setMaNhanVien(rs.getString("MaGiangVien"));
           listGiangVien.add(gv);
            }
            }
            catch(SQLException ex)
            {
                 ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Không thể kết nối CSDL!", "Thông báo", JOptionPane.ERROR_MESSAGE);
             }
            finally{
            Database.closeConnection();
            }
    }
    public void khoiTaoDuLieu()
    {
       String sql = "SELECT DISTINCT ThoiGian FROM DANGKYPHONG ORDER BY ThoiGian ASC";
       String sql1 = "SELECT MaPhong FROM PHONGMAY";
       try{
       ResultSet rs = Database.getData(sql);
       ResultSet rs1 = Database.getData(sql1);
       while(rs.next())
       {
           listThoiGian.add(rs.getDate("ThoiGian"));
           
       }
       
       khoiTaoNgay(0);
       while(rs1.next())
       {
        comboPhongMay.addItem(rs1.getString("MaPhong"));   
       }
       co++;
       }
       catch(SQLException ex)
       {
             ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Không thể kết nối CSDL!", "Thông báo", JOptionPane.ERROR_MESSAGE);
      }
        finally{
            Database.closeConnection();
       }
    }
    public void khoiTaoTextArea()
    { 
       Area_S_1.setText("\n\n\t#N/A\t");
       Area_S_2.setText("\n\n\t#N/A\t");
       Area_S_3.setText("\n\n\t#N/A\t");
       Area_S_4.setText("\n\n\t#N/A\t");
       Area_S_5.setText("\n\n\t#N/A\t");
       Area_S_6.setText("\n\n\t#N/A\t");
       Area_S_7.setText("\n\n\t#N/A\t");
       Area_C_1.setText("\n\n\t#N/A\t");
       Area_C_2.setText("\n\n\t#N/A\t");
       Area_C_3.setText("\n\n\t#N/A\t");
       Area_C_4.setText("\n\n\t#N/A\t");
       Area_C_5.setText("\n\n\t#N/A\t");
       Area_C_6.setText("\n\n\t#N/A\t");
       Area_C_7.setText("\n\n\t#N/A\t");
       Area_T_1.setText("\n\n\t#N/A\t");
       Area_T_2.setText("\n\n\t#N/A\t");
       Area_T_3.setText("\n\n\t#N/A\t");
       Area_T_4.setText("\n\n\t#N/A\t");
       Area_T_5.setText("\n\n\t#N/A\t");
       Area_T_6.setText("\n\n\t#N/A\t");
       Area_T_7.setText("\n\n\t#N/A\t");
    }
    public void khoiTaoCuon()
    {
     Area_S_1.setLineWrap(false);   
     Area_S_2.setLineWrap(false);  
     Area_S_3.setLineWrap(false);  
     Area_S_4.setLineWrap(false);
     Area_S_5.setLineWrap(false);
     Area_S_6.setLineWrap(false);
     Area_S_7.setLineWrap(false);  
     Area_C_1.setLineWrap(false);
     Area_C_2.setLineWrap(false);  
     Area_C_3.setLineWrap(false);  
     Area_C_4.setLineWrap(false);  
     Area_C_5.setLineWrap(false);  
     Area_C_6.setLineWrap(false);  
     Area_C_7.setLineWrap(false);  
     Area_T_1.setLineWrap(false);  
     Area_T_2.setLineWrap(false);  
     Area_T_3.setLineWrap(false);  
     Area_T_4.setLineWrap(false);  
     Area_T_5.setLineWrap(false);  
     Area_T_6.setLineWrap(false);  
     Area_T_7.setLineWrap(false);  
    }
   public void khoiTaoNgay(int st)
   {
       txt_ngay1.setText(listThoiGian.get(st).toString());
       txt_ngay2.setText(listThoiGian.get(st+1).toString());
       txt_ngay3.setText(listThoiGian.get(st+2).toString());
       txt_ngay4.setText(listThoiGian.get(st+3).toString());
       txt_ngay5.setText(listThoiGian.get(st+4).toString());
       txt_ngay6.setText(listThoiGian.get(st+5).toString());
       txt_ngay7.setText(listThoiGian.get(st+6).toString());
   }
     public void khoanhVungNgay()
        {
             Iterator<GiangVien> myIterator = listGiangVien_temp.iterator();
         
           while(myIterator.hasNext())
           {
               GiangVien gv = new GiangVien();
               gv = myIterator.next();
                if(gv.getListDangKyPhong().get(0).getThoiGian().before(Date.valueOf(txt_ngay1.getText()))
                   || gv.getListDangKyPhong().get(0).getThoiGian().after(Date.valueOf(txt_ngay7.getText())))
                   {
                     myIterator.remove();
                    }
           }   
        }
     public void khoanhVungPhong()
     {
         Iterator<GiangVien> myIterator = listGiangVien_temp.iterator();
         while(myIterator.hasNext())
         {
             GiangVien gv = new GiangVien();
             gv = myIterator.next();
             if(gv.getListDangKyPhong().get(0).getMaPhong().compareTo((String)comboPhongMay.getSelectedItem()) != 0)
             {
                 myIterator.remove();
             }
         }
     }
     public int reLoadNgay()
     { 
         int tuan = 0;
         tuan = Integer.parseInt((String)comboTuan.getSelectedItem());
         khoiTaoCuon();
         khoiTaoTextArea();
            if(listThoiGian.size() >= (7*(tuan-1)+7))
              {
              khoiTaoNgay(7*tuan-7);
              return 1;
               }
              else
            {
              setNgayTrong();
              return 0;
            }
                
                    
     }
     public void setNgayTrong()
     {
         txt_ngay1.setText("\t#N/A\t\t");
         txt_ngay2.setText("\t#N/A\t\t");
         txt_ngay3.setText("\t#N/A\t\t");
         txt_ngay4.setText("\t#N/A\t\t");
         txt_ngay5.setText("\t#N/A\t\t");
         txt_ngay6.setText("\t#N/A\t\t");
         txt_ngay7.setText("\t#N/A\t\t");
     }
     public void locDuLieu()
     {
         listGiangVien_temp = new ArrayList<>(listGiangVien);
         khoanhVungNgay();
         khoanhVungPhong();
         locNgayCuThe(txt_ngay1.getText());
         locCaThucHanh(Area_S_1, Area_C_1, Area_T_1);
         locNgayCuThe(txt_ngay2.getText());
         locCaThucHanh(Area_S_2, Area_C_2, Area_T_2);
         locNgayCuThe(txt_ngay3.getText());
         locCaThucHanh(Area_S_3, Area_C_3, Area_T_3);
         locNgayCuThe(txt_ngay4.getText());
         locCaThucHanh(Area_S_4, Area_C_4, Area_T_4);
         locNgayCuThe(txt_ngay5.getText());
         locCaThucHanh(Area_S_5, Area_C_5, Area_T_5);
         locNgayCuThe(txt_ngay6.getText());
         locCaThucHanh(Area_S_6, Area_C_6, Area_T_6);
         locNgayCuThe(txt_ngay7.getText());
         locCaThucHanh(Area_S_7, Area_C_7, Area_T_7);      
     }
     public void locNgayCuThe(String date)
     {
         listGiangVien_loc = new ArrayList<>(listGiangVien_temp);
         Iterator<GiangVien> myIterator = listGiangVien_loc.iterator();
         while(myIterator.hasNext())
         {
             GiangVien gv = new GiangVien();
             gv = myIterator.next();
            
             if(!date.equals(gv.getListDangKyPhong().get(0).getThoiGian().toString()))
             {
                 myIterator.remove();
             }
            
         }
         
     }
     public void locCaThucHanh(JTextArea area1, JTextArea area2, JTextArea area3)
     {
          Iterator<GiangVien> myIterator = listGiangVien_loc.iterator();
          while(myIterator.hasNext())
          {
              GiangVien gv = new GiangVien();
               gv = myIterator.next();
               //System.out.println("Ngày thực hành: " + sdf.format(gv.getListDangKyPhong().get(0).getThoiGian()));
              if(gv.getListDangKyPhong().get(0).getCaThucHanh().equalsIgnoreCase("S"))
              {
                  area1.setLineWrap(true);
                  area1.setText(gv.getThongTin());
              }
               if(gv.getListDangKyPhong().get(0).getCaThucHanh().equalsIgnoreCase("C"))
               {
                   area2.setLineWrap(true);
                  area2.setText(gv.getThongTin());
               }
               if(gv.getListDangKyPhong().get(0).getCaThucHanh().equalsIgnoreCase("T"))
               {
                   area3.setLineWrap(true);
                   area3.setText(gv.getThongTin());
               }
                  
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

        jFrame1 = new javax.swing.JFrame();
        jPanel10 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        txt_MaGiangVien = new javax.swing.JTextField();
        txt_MaPhong = new javax.swing.JTextField();
        checkBox_C = new javax.swing.JCheckBox();
        checkBox_S = new javax.swing.JCheckBox();
        checkBox_T = new javax.swing.JCheckBox();
        txt_ThoiGian = new javax.swing.JTextField();
        combo_MaLop = new javax.swing.JComboBox<>();
        layerPane1 = new javax.swing.JLayeredPane();
        txt_TenMonHoc = new javax.swing.JTextField();
        layerPane2 = new javax.swing.JLayeredPane();
        combo_Nhom = new javax.swing.JComboBox<>();
        txt_TenGiangVien = new javax.swing.JTextField();
        jPanel12 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        bt_Them = new javax.swing.JButton();
        bt_Sua = new javax.swing.JButton();
        bt_Xoa = new javax.swing.JButton();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Area_S_1 = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        Area_S_2 = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        Area_S_3 = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        Area_S_4 = new javax.swing.JTextArea();
        jScrollPane5 = new javax.swing.JScrollPane();
        Area_S_5 = new javax.swing.JTextArea();
        jScrollPane6 = new javax.swing.JScrollPane();
        Area_S_6 = new javax.swing.JTextArea();
        jScrollPane7 = new javax.swing.JScrollPane();
        Area_S_7 = new javax.swing.JTextArea();
        txtCaSang = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        txt_ngay5 = new javax.swing.JTextField();
        txt_ngay6 = new javax.swing.JTextField();
        txt_ngay7 = new javax.swing.JTextField();
        txt_ngay1 = new javax.swing.JTextField();
        txt_ngay2 = new javax.swing.JTextField();
        txt_ngay3 = new javax.swing.JTextField();
        txt_ngay4 = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        Area_C_1 = new javax.swing.JTextArea();
        jScrollPane9 = new javax.swing.JScrollPane();
        Area_C_2 = new javax.swing.JTextArea();
        jScrollPane10 = new javax.swing.JScrollPane();
        Area_C_3 = new javax.swing.JTextArea();
        jScrollPane11 = new javax.swing.JScrollPane();
        Area_C_4 = new javax.swing.JTextArea();
        jScrollPane12 = new javax.swing.JScrollPane();
        Area_C_5 = new javax.swing.JTextArea();
        jScrollPane13 = new javax.swing.JScrollPane();
        Area_C_6 = new javax.swing.JTextArea();
        jScrollPane14 = new javax.swing.JScrollPane();
        Area_C_7 = new javax.swing.JTextArea();
        txtCaChieu = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane15 = new javax.swing.JScrollPane();
        Area_T_1 = new javax.swing.JTextArea();
        jScrollPane16 = new javax.swing.JScrollPane();
        Area_T_2 = new javax.swing.JTextArea();
        jScrollPane17 = new javax.swing.JScrollPane();
        Area_T_3 = new javax.swing.JTextArea();
        jScrollPane18 = new javax.swing.JScrollPane();
        Area_T_4 = new javax.swing.JTextArea();
        jScrollPane19 = new javax.swing.JScrollPane();
        Area_T_5 = new javax.swing.JTextArea();
        jScrollPane20 = new javax.swing.JScrollPane();
        Area_T_6 = new javax.swing.JTextArea();
        jScrollPane21 = new javax.swing.JScrollPane();
        Area_T_7 = new javax.swing.JTextArea();
        txtCaToi = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        comboTuan = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        comboPhongMay = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();

        txt_MaGiangVien.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        txt_MaGiangVien.setDisabledTextColor(new java.awt.Color(255, 255, 255));

        txt_MaPhong.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N

        buttonGroup1.add(checkBox_C);
        checkBox_C.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        checkBox_C.setText("C");

        buttonGroup1.add(checkBox_S);
        checkBox_S.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        checkBox_S.setText("S");

        buttonGroup1.add(checkBox_T);
        checkBox_T.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        checkBox_T.setText("T");

        txt_ThoiGian.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N

        combo_MaLop.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        combo_MaLop.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                combo_MaLopItemStateChanged(evt);
            }
        });

        layerPane1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout layerPane1Layout = new javax.swing.GroupLayout(layerPane1);
        layerPane1.setLayout(layerPane1Layout);
        layerPane1Layout.setHorizontalGroup(
            layerPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 277, Short.MAX_VALUE)
        );
        layerPane1Layout.setVerticalGroup(
            layerPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 71, Short.MAX_VALUE)
        );

        txt_TenMonHoc.setEditable(false);
        txt_TenMonHoc.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txt_TenMonHoc.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_TenMonHoc.setDisabledTextColor(new java.awt.Color(255, 255, 255));

        layerPane2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        javax.swing.GroupLayout layerPane2Layout = new javax.swing.GroupLayout(layerPane2);
        layerPane2.setLayout(layerPane2Layout);
        layerPane2Layout.setHorizontalGroup(
            layerPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 234, Short.MAX_VALUE)
        );
        layerPane2Layout.setVerticalGroup(
            layerPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 70, Short.MAX_VALUE)
        );

        combo_Nhom.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        combo_Nhom.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "N1", "N2", "2N" }));

        txt_TenGiangVien.setEditable(false);
        txt_TenGiangVien.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txt_TenGiangVien.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_TenGiangVien.setDisabledTextColor(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(checkBox_S)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(checkBox_C)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(checkBox_T))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txt_ThoiGian, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(combo_Nhom, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(combo_MaLop, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 74, Short.MAX_VALUE)
                                .addComponent(txt_TenGiangVien, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(txt_TenMonHoc, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txt_MaGiangVien, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(44, 44, 44))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(layerPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_MaPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(layerPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(layerPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txt_MaPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(checkBox_S)
                    .addComponent(checkBox_C)
                    .addComponent(checkBox_T))
                .addGap(29, 29, 29)
                .addComponent(txt_ThoiGian, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(layerPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(combo_Nhom, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(combo_MaLop, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_TenGiangVien, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_TenMonHoc, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_MaGiangVien, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
        );

        txt_TenMonHoc.getAccessibleContext().setAccessibleName("");
        txt_TenMonHoc.getAccessibleContext().setAccessibleDescription("");

        jLabel4.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel4.setText("Mã giảng viên");

        jLabel6.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel6.setText("Mã phòng");

        jLabel7.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel7.setText("Ca thực hành");

        jLabel8.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel8.setText("Thời gian");

        jLabel9.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel9.setText("Nhóm");

        jLabel10.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel10.setText("Mã lớp");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel8)
                            .addComponent(jLabel10)
                            .addComponent(jLabel9))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel7))
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel4)
                .addGap(49, 49, 49)
                .addComponent(jLabel6)
                .addGap(41, 41, 41)
                .addComponent(jLabel7)
                .addGap(32, 32, 32)
                .addComponent(jLabel8)
                .addGap(48, 48, 48)
                .addComponent(jLabel10)
                .addGap(55, 55, 55)
                .addComponent(jLabel9)
                .addContainerGap(86, Short.MAX_VALUE))
        );

        bt_Them.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        bt_Them.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Pencil.png"))); // NOI18N
        bt_Them.setText("Thêm");
        bt_Them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_ThemActionPerformed(evt);
            }
        });

        bt_Sua.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        bt_Sua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/gear.png"))); // NOI18N
        bt_Sua.setText("Sửa");
        bt_Sua.setToolTipText("");
        bt_Sua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_SuaActionPerformed(evt);
            }
        });

        bt_Xoa.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        bt_Xoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Dustbin.png"))); // NOI18N
        bt_Xoa.setText("Xóa");
        bt_Xoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_XoaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(bt_Them, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(bt_Sua, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addComponent(bt_Xoa, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_Sua, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_Them, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_Xoa, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(47, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jFrame1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jFrame1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        Area_S_1.setEditable(false);
        Area_S_1.setColumns(20);
        Area_S_1.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        Area_S_1.setRows(5);
        Area_S_1.setToolTipText("");
        Area_S_1.setWrapStyleWord(true);
        Area_S_1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Area_S_1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(Area_S_1);

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        Area_S_2.setEditable(false);
        Area_S_2.setColumns(20);
        Area_S_2.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        Area_S_2.setRows(5);
        Area_S_2.setToolTipText("");
        Area_S_2.setWrapStyleWord(true);
        Area_S_2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Area_S_2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(Area_S_2);

        jScrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        Area_S_3.setEditable(false);
        Area_S_3.setColumns(20);
        Area_S_3.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        Area_S_3.setRows(5);
        Area_S_3.setToolTipText("");
        Area_S_3.setWrapStyleWord(true);
        Area_S_3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Area_S_3MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(Area_S_3);

        jScrollPane4.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        Area_S_4.setEditable(false);
        Area_S_4.setColumns(20);
        Area_S_4.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        Area_S_4.setRows(5);
        Area_S_4.setToolTipText("");
        Area_S_4.setWrapStyleWord(true);
        Area_S_4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Area_S_4MouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(Area_S_4);

        jScrollPane5.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        Area_S_5.setEditable(false);
        Area_S_5.setColumns(20);
        Area_S_5.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        Area_S_5.setRows(5);
        Area_S_5.setToolTipText("");
        Area_S_5.setWrapStyleWord(true);
        Area_S_5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Area_S_5MouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(Area_S_5);

        jScrollPane6.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        Area_S_6.setEditable(false);
        Area_S_6.setColumns(20);
        Area_S_6.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        Area_S_6.setRows(5);
        Area_S_6.setToolTipText("");
        Area_S_6.setWrapStyleWord(true);
        Area_S_6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Area_S_6MouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(Area_S_6);

        jScrollPane7.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        Area_S_7.setEditable(false);
        Area_S_7.setColumns(20);
        Area_S_7.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        Area_S_7.setRows(5);
        Area_S_7.setToolTipText("");
        Area_S_7.setWrapStyleWord(true);
        Area_S_7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Area_S_7MouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(Area_S_7);

        txtCaSang.setEditable(false);
        txtCaSang.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtCaSang.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCaSang.setText("S");

        txt_ngay5.setEditable(false);
        txt_ngay5.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txt_ngay5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_ngay5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));

        txt_ngay6.setEditable(false);
        txt_ngay6.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txt_ngay6.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_ngay6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));

        txt_ngay7.setEditable(false);
        txt_ngay7.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txt_ngay7.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_ngay7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addComponent(txt_ngay5, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_ngay6, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_ngay7, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(txt_ngay5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txt_ngay6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txt_ngay7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        txt_ngay1.setEditable(false);
        txt_ngay1.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txt_ngay1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_ngay1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));

        txt_ngay2.setEditable(false);
        txt_ngay2.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txt_ngay2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_ngay2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));

        txt_ngay3.setEditable(false);
        txt_ngay3.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txt_ngay3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_ngay3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));

        txt_ngay4.setEditable(false);
        txt_ngay4.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txt_ngay4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_ngay4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtCaSang, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txt_ngay1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_ngay2, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_ngay3, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(txt_ngay4, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(435, 435, 435))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_ngay1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_ngay2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_ngay3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_ngay4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane7)
                    .addComponent(jScrollPane6)
                    .addComponent(jScrollPane5)
                    .addComponent(jScrollPane4)
                    .addComponent(jScrollPane3)
                    .addComponent(jScrollPane2)
                    .addComponent(jScrollPane1)
                    .addComponent(txtCaSang))
                .addGap(4, 4, 4))
        );

        jScrollPane8.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        Area_C_1.setEditable(false);
        Area_C_1.setColumns(20);
        Area_C_1.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        Area_C_1.setRows(5);
        Area_C_1.setToolTipText("");
        Area_C_1.setWrapStyleWord(true);
        Area_C_1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Area_C_1MouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(Area_C_1);

        jScrollPane9.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        Area_C_2.setEditable(false);
        Area_C_2.setColumns(20);
        Area_C_2.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        Area_C_2.setRows(5);
        Area_C_2.setToolTipText("");
        Area_C_2.setWrapStyleWord(true);
        Area_C_2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Area_C_2MouseClicked(evt);
            }
        });
        jScrollPane9.setViewportView(Area_C_2);

        jScrollPane10.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        Area_C_3.setEditable(false);
        Area_C_3.setColumns(20);
        Area_C_3.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        Area_C_3.setRows(5);
        Area_C_3.setToolTipText("");
        Area_C_3.setWrapStyleWord(true);
        Area_C_3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Area_C_3MouseClicked(evt);
            }
        });
        jScrollPane10.setViewportView(Area_C_3);

        jScrollPane11.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        Area_C_4.setEditable(false);
        Area_C_4.setColumns(20);
        Area_C_4.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        Area_C_4.setRows(5);
        Area_C_4.setToolTipText("");
        Area_C_4.setWrapStyleWord(true);
        Area_C_4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Area_C_4MouseClicked(evt);
            }
        });
        jScrollPane11.setViewportView(Area_C_4);

        jScrollPane12.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        Area_C_5.setEditable(false);
        Area_C_5.setColumns(20);
        Area_C_5.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        Area_C_5.setRows(5);
        Area_C_5.setToolTipText("");
        Area_C_5.setWrapStyleWord(true);
        Area_C_5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Area_C_5MouseClicked(evt);
            }
        });
        jScrollPane12.setViewportView(Area_C_5);

        jScrollPane13.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        Area_C_6.setEditable(false);
        Area_C_6.setColumns(20);
        Area_C_6.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        Area_C_6.setRows(5);
        Area_C_6.setToolTipText("");
        Area_C_6.setWrapStyleWord(true);
        Area_C_6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Area_C_6MouseClicked(evt);
            }
        });
        jScrollPane13.setViewportView(Area_C_6);

        jScrollPane14.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        Area_C_7.setEditable(false);
        Area_C_7.setColumns(20);
        Area_C_7.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        Area_C_7.setRows(5);
        Area_C_7.setToolTipText("");
        Area_C_7.setWrapStyleWord(true);
        Area_C_7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Area_C_7MouseClicked(evt);
            }
        });
        jScrollPane14.setViewportView(Area_C_7);

        txtCaChieu.setEditable(false);
        txtCaChieu.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtCaChieu.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCaChieu.setText("C");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtCaChieu, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCaChieu)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jScrollPane15.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        Area_T_1.setEditable(false);
        Area_T_1.setColumns(20);
        Area_T_1.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        Area_T_1.setRows(5);
        Area_T_1.setToolTipText("");
        Area_T_1.setWrapStyleWord(true);
        Area_T_1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Area_T_1MouseClicked(evt);
            }
        });
        jScrollPane15.setViewportView(Area_T_1);

        jScrollPane16.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        Area_T_2.setEditable(false);
        Area_T_2.setColumns(20);
        Area_T_2.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        Area_T_2.setRows(5);
        Area_T_2.setToolTipText("");
        Area_T_2.setWrapStyleWord(true);
        Area_T_2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Area_T_2MouseClicked(evt);
            }
        });
        jScrollPane16.setViewportView(Area_T_2);

        jScrollPane17.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        Area_T_3.setEditable(false);
        Area_T_3.setColumns(20);
        Area_T_3.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        Area_T_3.setRows(5);
        Area_T_3.setToolTipText("");
        Area_T_3.setWrapStyleWord(true);
        Area_T_3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Area_T_3MouseClicked(evt);
            }
        });
        jScrollPane17.setViewportView(Area_T_3);

        jScrollPane18.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        Area_T_4.setEditable(false);
        Area_T_4.setColumns(20);
        Area_T_4.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        Area_T_4.setRows(5);
        Area_T_4.setToolTipText("");
        Area_T_4.setWrapStyleWord(true);
        Area_T_4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Area_T_4MouseClicked(evt);
            }
        });
        jScrollPane18.setViewportView(Area_T_4);

        jScrollPane19.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        Area_T_5.setEditable(false);
        Area_T_5.setColumns(20);
        Area_T_5.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        Area_T_5.setRows(5);
        Area_T_5.setToolTipText("");
        Area_T_5.setWrapStyleWord(true);
        Area_T_5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Area_T_5MouseClicked(evt);
            }
        });
        jScrollPane19.setViewportView(Area_T_5);

        jScrollPane20.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        Area_T_6.setEditable(false);
        Area_T_6.setColumns(20);
        Area_T_6.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        Area_T_6.setRows(5);
        Area_T_6.setToolTipText("");
        Area_T_6.setWrapStyleWord(true);
        Area_T_6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Area_T_6MouseClicked(evt);
            }
        });
        jScrollPane20.setViewportView(Area_T_6);

        jScrollPane21.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        Area_T_7.setEditable(false);
        Area_T_7.setColumns(20);
        Area_T_7.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        Area_T_7.setRows(5);
        Area_T_7.setToolTipText("");
        Area_T_7.setWrapStyleWord(true);
        Area_T_7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Area_T_7MouseClicked(evt);
            }
        });
        jScrollPane21.setViewportView(Area_T_7);

        txtCaToi.setEditable(false);
        txtCaToi.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtCaToi.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCaToi.setText("T");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtCaToi, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane17, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane18, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane19, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane20, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane21, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane15)
                    .addComponent(jScrollPane16)
                    .addComponent(jScrollPane17)
                    .addComponent(jScrollPane18)
                    .addComponent(jScrollPane19)
                    .addComponent(jScrollPane20)
                    .addComponent(jScrollPane21)
                    .addComponent(txtCaToi))
                .addGap(0, 8, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 1122, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 1122, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Calendar.png"))); // NOI18N
        jLabel2.setText("Tuần");

        comboTuan.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        comboTuan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15" }));
        comboTuan.setToolTipText("");
        comboTuan.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboTuanItemStateChanged(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Monitor_1.png"))); // NOI18N
        jLabel3.setText("Phòng");

        comboPhongMay.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        comboPhongMay.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboPhongMayItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboTuan, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboPhongMay, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 26, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboTuan, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboPhongMay, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(119, 119, 119)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Roboto", 0, 30)); // NOI18N
        jLabel1.setText("LỊCH THỰC HÀNH PHÒNG MÁY");
        jLabel1.setToolTipText("");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(19, 19, 19))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jButton1.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Back-arrow.png"))); // NOI18N
        jButton1.setText("Quay lại");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jButton1)
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(426, 426, 426)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(16, 16, 16))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void comboPhongMayItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboPhongMayItemStateChanged
        // TODO add your handling code here:
        co++;
        if(co>1 && reLoadNgay() == 1)
        {
            khoiTaoCuon();
            khoiTaoTextArea();
            locDuLieu();
        }
        //JOptionPane.showMessageDialog(null, "hehee");
    }//GEN-LAST:event_comboPhongMayItemStateChanged
    
    private void comboTuanItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboTuanItemStateChanged
        // TODO add your handling code here:
        if(reLoadNgay() == 1)
        locDuLieu();
    }//GEN-LAST:event_comboTuanItemStateChanged

    public void khoiTaoLayer()
    {
        if(co1 == 0)
        {
        combo_MaLop.setBounds(0, 0, 170, 33);
        txt_TenMonHoc.setBounds(25, 25, 250, 33);
      //  txt_TenMonHoc.setText("Hệ quản trị cơ sở dữ liệu SQL Server");
         layerPane1.add(txt_TenMonHoc, new Integer(1));
         layerPane1.add(combo_MaLop, new Integer(2));
         txt_MaGiangVien.setBounds(0, 0, 150, 30);
         txt_TenGiangVien.setBounds(25, 25, 150, 30);
        //txt_TenGiangVien.setText("Nguyễn Thị Nhung");
        layerPane2.add(txt_TenGiangVien, new Integer(1));
         layerPane2.add(txt_MaGiangVien, new Integer(2));
        }
        co1++;
         
    }
    
    public void timKiem(String duLieu)
    {
        Iterator<GiangVien> myIterator = listGiangVien.iterator();
        while(myIterator.hasNext())
        {
            GiangVien gv = new GiangVien();
            gv = myIterator.next();
            if(gv.getThongTinKiemTra().equalsIgnoreCase(duLieu))
            {
                thongTinCu = gv;
                txt_MaGiangVien.setText(gv.getMaNhanVien());
                txt_TenGiangVien.setText(gv.getHoTen());
                txt_MaPhong.setText(gv.getListDangKyPhong().get(0).getMaPhong());
                xuLyCheckBox(gv.getListDangKyPhong().get(0).getCaThucHanh());
                txt_ThoiGian.setText(gv.getListDangKyPhong().get(0).getThoiGian().toString());
                combo_Nhom.setSelectedItem(gv.getListDangKyPhong().get(0).getNhom());
                khoiTaoLopDocLap(gv.getMaNhanVien(), gv.getListLopDocLap().get(0).getMaLop());
                loadTenMonHoc();
                
            }
        }
    }
    public void khoiTaoLopDocLap(String maGiangVien, String maLop)
    {
        combo_MaLop.removeAllItems();
        listLopDocLap.clear();
        String sql = "SELECT MaLop, TenMonHoc, HeDaoTao, KhoaDaoTao, LichLyThuyet"
                          +" FROM LOPDOCLAP WHERE MaGiangVien = ?";
        try{
        PreparedStatement pre = Database.getPrepareStatement(sql);
        pre.setString(1, maGiangVien);
        ResultSet rs = pre.executeQuery();
        while(rs.next())
        {
            LopDocLap ldl = new LopDocLap();
            ldl.setMaLop(rs.getString("MaLop"));
            ldl.setTenMonHoc(rs.getString("TenMonHoc"));
            ldl.setHeDaoTao(rs.getString("HeDaoTao"));
            ldl.setKhoaDaoTao(rs.getString("KhoaDaoTao"));
            ldl.setLichLyThuyet(rs.getString("LichLyThuyet"));
            listLopDocLap.add(ldl);
            combo_MaLop.addItem(rs.getString("MaLop"));
        }
        combo_MaLop.setSelectedItem(maLop);
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Không thể thực hiện truy vấn!", "Thông báo", JOptionPane.ERROR_MESSAGE);
        }
    }
    public void khoiTaoPhongMay()
    {
        listPhongMay.clear();
        String sql = "SELECT MaPhong FROM PHONGMAY";
        try{
        ResultSet rs = Database.getData(sql);
        while(rs.next())
        {
            PhongMay pm = new PhongMay();
            pm.setMaPhong(rs.getString("MaPhong"));
            listPhongMay.add(pm);
        }
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Không thể thực hiện truy vấn!", "Thông báo", JOptionPane.ERROR_MESSAGE);
        }
    }
    public void xuLyCheckBox(String ca)
    {
        if(ca.equals("S"))
            checkBox_S.setSelected(true);
        if(ca.equals("C"))
            checkBox_C.setSelected(true);
        if(ca.equals("T"))
            checkBox_T.setSelected(true);
    }
    public void khoiTaoFrame()
    {
    
        jFrame1.setTitle("Lịch chi tiết");
        jFrame1.setLocation((int)(this.getLocation().getX()*3F), (int)(this.getLocation().getY()*1.8));
        jFrame1.setSize((int)(this.getWidth()/2.4), (int)(this.getHeight()/1.2));
        jFrame1.setVisible(true);
        txt_MaGiangVien.setEnabled(false);
        khoiTaoLayer();
    }
    public void hienThiChiTiet(JTextArea area, JTextField textNgay, JTextField textCa)
    {
         if(!area.getText().equals("\n\n\t#N/A\t"))
        {
        khoiTaoFrame();
        String duLieu = (String)comboPhongMay.getSelectedItem() + "\n" + textNgay.getText()
                                  + "\n" + textCa.getText();
        timKiem(duLieu);
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Không có dữ liệu!", "Thông báo", JOptionPane.WARNING_MESSAGE);
        }
    }
    private void Area_S_1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Area_S_1MouseClicked
        // TODO add your handling code here:
       hienThiChiTiet(Area_S_1, txt_ngay1, txtCaSang);
    }//GEN-LAST:event_Area_S_1MouseClicked

    private void Area_S_2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Area_S_2MouseClicked
        // TODO add your handling code here:
         hienThiChiTiet(Area_S_2, txt_ngay2, txtCaSang);
    }//GEN-LAST:event_Area_S_2MouseClicked

    private void Area_S_3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Area_S_3MouseClicked
        // TODO add your handling code here:
         hienThiChiTiet(Area_S_3, txt_ngay3, txtCaSang);
    }//GEN-LAST:event_Area_S_3MouseClicked

    private void Area_S_4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Area_S_4MouseClicked
        // TODO add your handling code here:
          hienThiChiTiet(Area_S_4, txt_ngay4, txtCaSang);
    }//GEN-LAST:event_Area_S_4MouseClicked

    private void Area_S_5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Area_S_5MouseClicked
        // TODO add your handling code here:
          hienThiChiTiet(Area_S_5, txt_ngay5, txtCaSang);
    }//GEN-LAST:event_Area_S_5MouseClicked

    private void Area_S_6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Area_S_6MouseClicked
        // TODO add your handling code here:
          hienThiChiTiet(Area_S_6, txt_ngay6, txtCaSang);
    }//GEN-LAST:event_Area_S_6MouseClicked

    private void Area_S_7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Area_S_7MouseClicked
        // TODO add your handling code here:
          hienThiChiTiet(Area_S_7, txt_ngay7, txtCaSang);
    }//GEN-LAST:event_Area_S_7MouseClicked

    private void Area_C_1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Area_C_1MouseClicked
        // TODO add your handling code here:
          hienThiChiTiet(Area_C_1, txt_ngay1, txtCaChieu);
    }//GEN-LAST:event_Area_C_1MouseClicked

    private void Area_C_2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Area_C_2MouseClicked
        // TODO add your handling code here:
          hienThiChiTiet(Area_C_2, txt_ngay2, txtCaChieu);
    }//GEN-LAST:event_Area_C_2MouseClicked

    private void Area_C_3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Area_C_3MouseClicked
        // TODO add your handling code here:
          hienThiChiTiet(Area_C_3, txt_ngay3, txtCaChieu);
    }//GEN-LAST:event_Area_C_3MouseClicked

    private void Area_C_4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Area_C_4MouseClicked
        // TODO add your handling code here:
          hienThiChiTiet(Area_C_4, txt_ngay4, txtCaChieu);
    }//GEN-LAST:event_Area_C_4MouseClicked

    private void Area_C_5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Area_C_5MouseClicked
        // TODO add your handling code here:
         hienThiChiTiet(Area_C_5, txt_ngay5, txtCaChieu);
    }//GEN-LAST:event_Area_C_5MouseClicked

    private void Area_C_6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Area_C_6MouseClicked
        // TODO add your handling code here:
          hienThiChiTiet(Area_C_6, txt_ngay6, txtCaChieu);
    }//GEN-LAST:event_Area_C_6MouseClicked

    private void Area_C_7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Area_C_7MouseClicked
        // TODO add your handling code here:
          hienThiChiTiet(Area_C_7, txt_ngay7, txtCaChieu);
    }//GEN-LAST:event_Area_C_7MouseClicked

    private void Area_T_1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Area_T_1MouseClicked
        // TODO add your handling code here:
          hienThiChiTiet(Area_T_1, txt_ngay1, txtCaToi);
    }//GEN-LAST:event_Area_T_1MouseClicked

    private void Area_T_2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Area_T_2MouseClicked
        // TODO add your handling code here:
          hienThiChiTiet(Area_T_2, txt_ngay2, txtCaToi);
    }//GEN-LAST:event_Area_T_2MouseClicked

    private void Area_T_3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Area_T_3MouseClicked
        // TODO add your handling code here:
         hienThiChiTiet(Area_T_3, txt_ngay3, txtCaToi);
    }//GEN-LAST:event_Area_T_3MouseClicked

    private void Area_T_4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Area_T_4MouseClicked
        // TODO add your handling code here:
         hienThiChiTiet(Area_T_4, txt_ngay4, txtCaToi);
    }//GEN-LAST:event_Area_T_4MouseClicked

    private void Area_T_5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Area_T_5MouseClicked
        // TODO add your handling code here:
         hienThiChiTiet(Area_T_5, txt_ngay5, txtCaToi);
    }//GEN-LAST:event_Area_T_5MouseClicked

    private void Area_T_6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Area_T_6MouseClicked
        // TODO add your handling code here:
         hienThiChiTiet(Area_T_6, txt_ngay6, txtCaToi);
    }//GEN-LAST:event_Area_T_6MouseClicked

    private void Area_T_7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Area_T_7MouseClicked
        // TODO add your handling code here:
        hienThiChiTiet(Area_T_7, txt_ngay7, txtCaToi);
    }//GEN-LAST:event_Area_T_7MouseClicked
    public void loadTenMonHoc()
    {
        String maLop = (String)combo_MaLop.getSelectedItem();
        LopDocLap ldl = new LopDocLap();
        ldl.setMaLop(maLop);
        if(listLopDocLap.contains(ldl))
        {
            txt_TenMonHoc.setText(listLopDocLap.get(listLopDocLap.indexOf(ldl)).getTenMonHoc());
        }
    }
    private void combo_MaLopItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_combo_MaLopItemStateChanged
        // TODO add your handling code here:
       loadTenMonHoc();
    }//GEN-LAST:event_combo_MaLopItemStateChanged
    public int kiemTraHopLe() throws KiemTraDuLieu
    {
        if(txt_MaPhong.getText().equals(""))
        {
            txt_MaPhong.requestFocus();
            throw new KiemTraDuLieu("Mã phòng không được để trống!");
        }
        if(!txt_MaPhong.getText().matches("P\\d{3}"))
        {
            txt_MaPhong.requestFocus();
            throw new KiemTraDuLieu("Mã phòng không đúng định dạng (PXXX)!");
        }
        if(txt_ThoiGian.getText().equals(""))
        {
            txt_ThoiGian.requestFocus();
            throw new KiemTraDuLieu("Thời gian không được để trống!");
        }
        if(!txt_ThoiGian.getText().matches("\\d{4}-\\d{2}-\\d{2}"))
        {
            txt_ThoiGian.requestFocus();
            throw new KiemTraDuLieu("Định dạng thời gian không hợp lệ (yyyy-MM-dd)!");
        }
        kiemTraHopLePhong();
     return 1;       
    }
    
    public String getCheckBoxValue()
    {
        if(checkBox_S.isSelected())
            return checkBox_S.getText();
        if(checkBox_C.isSelected())
            return checkBox_C.getText();
        if(checkBox_T.isSelected())
            return checkBox_T.getText();
      return null; 
    }
    public int kiemTraTonTai()
    {
        String duLieu = txt_MaPhong.getText() + "\n" + txt_ThoiGian.getText() + "\n" 
                                + getCheckBoxValue();
        Iterator<GiangVien> myIterator = listGiangVien.iterator();
        while(myIterator.hasNext())
        {
            GiangVien gv = new GiangVien();
            gv = myIterator.next();
            if(gv.getThongTinKiemTra().equalsIgnoreCase(duLieu))
            {
                return 0;
            }
        }
        return 1;
         
    }
    public int kiemTraHopLePhong() throws KiemTraDuLieu
    {
        PhongMay pm = new PhongMay();
        pm.setMaPhong(txt_MaPhong.getText());
        if(!listPhongMay.contains(pm))
           throw new KiemTraDuLieu("Phòng không tồn tại(P001 - P015)!");
            return 1;
    }
    public void themLich()
    {
        String heDaoTao = "";
        String khoaDaoTao = "";
        String lichLyThuyet = "";
        String caThucHanh = getCheckBoxValue();
        int viTri = 0;
        GiangVien gv = new GiangVien();
        LopDocLap ldl = new LopDocLap();
        DangKyPhong dkp = new DangKyPhong();
        gv.getListLopDocLap().add(ldl);
        gv.getListDangKyPhong().add(dkp);
        gv.setMaNhanVien(txt_MaGiangVien.getText());
        gv.setHoTen(txt_TenGiangVien.getText());
        gv.getListDangKyPhong().get(0).setMaPhong(txt_MaPhong.getText());
        gv.getListDangKyPhong().get(0).setCaThucHanh(caThucHanh);
        gv.getListDangKyPhong().get(0).setThoiGian(Date.valueOf(txt_ThoiGian.getText()));
        gv.getListDangKyPhong().get(0).setNhom((String)combo_Nhom.getSelectedItem());
        gv.getListLopDocLap().get(0).setMaLop((String)combo_MaLop.getSelectedItem());
        gv.getListLopDocLap().get(0).setTenMonHoc(txt_TenMonHoc.getText());
        viTri = listLopDocLap.indexOf(gv.getListLopDocLap().get(0));
       heDaoTao = listLopDocLap.get(viTri).getHeDaoTao();
       // System.out.println("Hệ đào tạo " + listLopDocLap.get(viTri).getHeDaoTao());
       khoaDaoTao = listLopDocLap.get(viTri).getKhoaDaoTao();
      //  System.out.println("Khóa đào tạo " + listLopDocLap.get(viTri).getKhoaDaoTao());
       lichLyThuyet = listLopDocLap.get(viTri).getLichLyThuyet();
       // System.out.println("Lịch lý thuyết: " + listLopDocLap.get(viTri).getLichLyThuyet());
       gv.getListLopDocLap().get(0).setHeDaoTao(heDaoTao);
       gv.getListLopDocLap().get(0).setKhoaDaoTao(khoaDaoTao);
       gv.getListLopDocLap().get(0).setLichLyThuyet(lichLyThuyet);
       listGiangVien.add(gv);
       thongTinCu = gv;
       
       DKPController.insert_dkp(txt_MaGiangVien.getText(), txt_MaPhong.getText(), caThucHanh,
                                             Date.valueOf(txt_ThoiGian.getText()), (String)combo_MaLop.getSelectedItem(), 
                                             (String)combo_Nhom.getSelectedItem());
       
    }
    public void xoaLich()
    {
        Iterator<GiangVien> myIterator = listGiangVien.iterator();
        while(myIterator.hasNext())
        {
            GiangVien gv = new GiangVien();
            gv = myIterator.next();
            if(gv.getThongTinKiemTra().equalsIgnoreCase(txt_MaPhong.getText() + "\n" + txt_ThoiGian.getText() + "\n" + getCheckBoxValue()))
            {
                myIterator.remove();
                break;
            }
        }
        
          DKPController.delete_dkp(txt_MaGiangVien.getText(), txt_MaPhong.getText(), getCheckBoxValue(), Date.valueOf(txt_ThoiGian.getText()));
          JOptionPane.showMessageDialog(null, "Xóa lịch thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
    }
    public void suaLich()
    {
        Iterator<GiangVien> myIterator = listGiangVien.iterator();
        while(myIterator.hasNext())
        {
            GiangVien gv = new GiangVien();
            gv = myIterator.next();
            if(gv.getThongTinKiemTra().equals(thongTinCu.getThongTinKiemTra()))
            {
                myIterator.remove();
                break;
            }
        }
        
        DKPController.delete_dkp(thongTinCu.getMaNhanVien(), thongTinCu.getListDangKyPhong().get(0).getMaPhong(), 
                                              thongTinCu.getListDangKyPhong().get(0).getCaThucHanh(),
                                              thongTinCu.getListDangKyPhong().get(0).getThoiGian());
        
        khoiTaoCuon();
        khoiTaoTextArea();
        locDuLieu();
        themLich();
        JOptionPane.showMessageDialog(null, "Sửa lịch thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        
    }
    public void thucHienThemSuaXoa(String lenhDieuKhien)
    {
        try{
          if(kiemTraHopLe() == 1)
        {
            if(lenhDieuKhien.equals("insert") || lenhDieuKhien.equals("update"))
            {
           if(kiemTraTonTai() == 1)
            {
             if(lenhDieuKhien.equals("insert"))
             {
                themLich();
                JOptionPane.showMessageDialog(null, "Thêm lịch thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
             }
             else
             {
                 suaLich();
                 khoiTaoCuon();
                 khoiTaoTextArea();
             }
             locDuLieu();
             return;
            }
           else
           {
           JOptionPane.showMessageDialog(null, "Lịch hiện tại trùng với lịch khác!", "Thông báo", JOptionPane.ERROR_MESSAGE);
           return;
           }
        }
        if(lenhDieuKhien.equals("delete"))
        {
             if(kiemTraTonTai() == 0)
        {
             int confirm = JOptionPane.showConfirmDialog(null, "Bạn chắc chắn muốn xóa bản ghi này?", "Cảnh báo!", 
                                                                                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
        if(confirm == JOptionPane.OK_OPTION)
        {
             xoaLich();
             khoiTaoCuon();
             khoiTaoTextArea();
            locDuLieu();
        }
        }
        else
         JOptionPane.showMessageDialog(null, "Lịch đăng ký không tồn tại!", "Thông báo", JOptionPane.ERROR_MESSAGE);
        }
        }
        }
        catch(KiemTraDuLieu ex)
        {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Cảnh báo", JOptionPane.WARNING_MESSAGE);
        }
    }
    private void bt_ThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_ThemActionPerformed
        // TODO add your handling code here:       
        thucHienThemSuaXoa("insert");
    }//GEN-LAST:event_bt_ThemActionPerformed

    private void bt_XoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_XoaActionPerformed
        // TODO add your handling code here:
        thucHienThemSuaXoa("delete");
    }//GEN-LAST:event_bt_XoaActionPerformed
    
    private void bt_SuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_SuaActionPerformed
        // TODO add your handling code here:
        thucHienThemSuaXoa("update");
        
    }//GEN-LAST:event_bt_SuaActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        new GiaoDienQT().setVisible(true);
        this.dispose();
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
            java.util.logging.Logger.getLogger(LapLich.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LapLich.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LapLich.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LapLich.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LapLich().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea Area_C_1;
    private javax.swing.JTextArea Area_C_2;
    private javax.swing.JTextArea Area_C_3;
    private javax.swing.JTextArea Area_C_4;
    private javax.swing.JTextArea Area_C_5;
    private javax.swing.JTextArea Area_C_6;
    private javax.swing.JTextArea Area_C_7;
    private javax.swing.JTextArea Area_S_1;
    private javax.swing.JTextArea Area_S_2;
    private javax.swing.JTextArea Area_S_3;
    private javax.swing.JTextArea Area_S_4;
    private javax.swing.JTextArea Area_S_5;
    private javax.swing.JTextArea Area_S_6;
    private javax.swing.JTextArea Area_S_7;
    private javax.swing.JTextArea Area_T_1;
    private javax.swing.JTextArea Area_T_2;
    private javax.swing.JTextArea Area_T_3;
    private javax.swing.JTextArea Area_T_4;
    private javax.swing.JTextArea Area_T_5;
    private javax.swing.JTextArea Area_T_6;
    private javax.swing.JTextArea Area_T_7;
    private javax.swing.JButton bt_Sua;
    private javax.swing.JButton bt_Them;
    private javax.swing.JButton bt_Xoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBox checkBox_C;
    private javax.swing.JCheckBox checkBox_S;
    private javax.swing.JCheckBox checkBox_T;
    private javax.swing.JComboBox<String> comboPhongMay;
    private javax.swing.JComboBox<String> comboTuan;
    private javax.swing.JComboBox<String> combo_MaLop;
    private javax.swing.JComboBox<String> combo_Nhom;
    private javax.swing.JButton jButton1;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane17;
    private javax.swing.JScrollPane jScrollPane18;
    private javax.swing.JScrollPane jScrollPane19;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane20;
    private javax.swing.JScrollPane jScrollPane21;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JLayeredPane layerPane1;
    private javax.swing.JLayeredPane layerPane2;
    private javax.swing.JTextField txtCaChieu;
    private javax.swing.JTextField txtCaSang;
    private javax.swing.JTextField txtCaToi;
    private javax.swing.JTextField txt_MaGiangVien;
    private javax.swing.JTextField txt_MaPhong;
    private javax.swing.JTextField txt_TenGiangVien;
    private javax.swing.JTextField txt_TenMonHoc;
    private javax.swing.JTextField txt_ThoiGian;
    private javax.swing.JTextField txt_ngay1;
    private javax.swing.JTextField txt_ngay2;
    private javax.swing.JTextField txt_ngay3;
    private javax.swing.JTextField txt_ngay4;
    private javax.swing.JTextField txt_ngay5;
    private javax.swing.JTextField txt_ngay6;
    private javax.swing.JTextField txt_ngay7;
    // End of variables declaration//GEN-END:variables
}
