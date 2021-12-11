
package form;

import classname.DangKyPhong;
import classname.PhongMay;
import db.Database;
import java.awt.Color;
import java.awt.Frame;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.HashSet;
import javax.swing.JOptionPane;

public class XemPhong extends javax.swing.JFrame {

    PhongMay pm;
    XemPhong dsPhong;
    public XemPhong() {
        initComponents();
        this.setLocationRelativeTo(this);
        this.setTitle("Xem phòng");
        cbTuan.removeAllItems();
        //loadData();
        loadDS();
        
    }
    private HashSet<String> viTriPhong;
    private ArrayList<PhongMay> dsP;
    private PhongMay a = new PhongMay();

    private void loadDS() {
        dsP = findAll();
        viTriPhong = new HashSet<>();

        if (!dsP.isEmpty()) {
            dsP.forEach((phong) -> {
                viTriPhong.add(phong.getViTriPhong());
            });
        }

        if (!viTriPhong.isEmpty()) {
            CBAddressP.removeAllItems();
            CBAddressP.addItem(" --- Chọn vị trí --- ");
            viTriPhong.forEach((vt) -> {
                CBAddressP.addItem(vt);
            });

        }
    }

    private void showTenPhong() {
        dsP = findAll();
        CBNameP.removeAllItems();
        CBNameP.addItem(" --- Chọn phòng --- ");
        dsP.forEach((phong) -> {
            if (CBAddressP.getSelectedItem().toString().compareTo(phong.getViTriPhong()) == 0) {
                CBNameP.addItem(phong.getTenPhong());
            }
        });
    }

    //  CHI TIEETS 
    DangKyPhong dkp = new DangKyPhong();
    ArrayList<DangKyPhong> dsDKP;
    HashSet<Integer> dsTuan = new HashSet<>();
    ArrayList<Integer> dsTuan2 = new ArrayList<>();
    private int min;

    private void loadData() {
        lblIDP.setText(pm.getMaPhong());
        lblNameP.setText(pm.getTenPhong());
        lblNamePH.setText("Thông tin " + pm.getTenPhong());
        lblCSVT.setText(pm.getCoSoVatChat());
        lblSLM.setText(pm.getSoLuongMay() + "");
        lblAdd.setText(pm.getViTriPhong());
    }

    private void showTuan() {
        if (CBNameP.getSelectedIndex() > 0) {
            dsDKP = findByMaPhong(pm.getMaPhong());
            dsDKP.forEach((a) -> {
                Calendar ca = Calendar.getInstance();
                ca.setFirstDayOfWeek(2);
                ca.setTime(a.getThoiGian());
                dsTuan.add(ca.get(Calendar.WEEK_OF_YEAR));
            });
            Comparator<Integer> c = (Integer o1, Integer o2) -> o1 - o2;

            if (!dsTuan.isEmpty()) {
                min = Collections.min(dsTuan, c);
                dsTuan2.clear();
                dsTuan.forEach((tuan) -> {
                    dsTuan2.add(tuan - min + 1);

                });
            }
            cbTuan.removeAllItems();
            cbTuan.addItem(" --- Chọn tuần --- ");
            dsTuan2.forEach((b) -> {
                cbTuan.addItem(b.toString());
            });
        }

    }

    private void showByTuan() {
        xoaTrang();
        if (cbTuan.getSelectedIndex() > 0) {
            int tuan = Integer.parseInt((String) cbTuan.getSelectedItem());
            dsDKP.forEach((a) -> {
                Calendar ca = Calendar.getInstance();
                ca.setFirstDayOfWeek(2);
                ca.setTime(a.getThoiGian());
                SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                //System.out.println(ca.get(Calendar.DAY_OF_WEEK));
                if (ca.get(Calendar.WEEK_OF_YEAR) == tuan + min - 1) {
                    //System.out.println(ca.get((Calendar.WEEK_OF_YEAR)));
                    switch (ca.get(Calendar.DAY_OF_WEEK)) {
                        case 2:
                            if (a.getCaThucHanh().compareTo("S") == 0) {
                                lblT2_S.setText("\t" + a.getNhom());
                                lblT2_S.setBackground(Color.GREEN);
                            } else if (a.getCaThucHanh().compareTo("C") == 0) {
                                lblT2_C.setText("\t" + a.getNhom());
                                lblT2_C.setBackground(Color.GREEN);
                            } else {
                                lblT2_T.setText("\t" + a.getNhom());
                                lblT2_T.setBackground(Color.GREEN);
                            }
                            T2.setText("       " + df.format(a.getThoiGian()));
                             break;
                        
                        case 3:
                            if (a.getCaThucHanh().compareTo("S") == 0) {
                                lblT3_S.setText("\t" + a.getNhom());
                                lblT3_S.setBackground(Color.GREEN);
                            } else if (a.getCaThucHanh().compareTo("C") == 0) {
                                lblT3_C.setText("\t" + a.getNhom());
                                lblT3_C.setBackground(Color.GREEN);
                            } else {
                                lblT3_T.setText("\t" + a.getNhom());
                                lblT3_T.setBackground(Color.GREEN);
                            }
                            T3.setText("       " + df.format(a.getThoiGian()));
                            break;
                        case 4:
                            if (a.getCaThucHanh().compareTo("S") == 0) {
                                lblT4_S.setText("\t" + a.getNhom());
                                lblT4_S.setBackground(Color.GREEN);
                            } else if (a.getCaThucHanh().compareTo("C") == 0) {
                                lblT4_C.setText("\t" + a.getNhom());
                                lblT4_C.setBackground(Color.GREEN);
                            } else {
                                lblT4_T.setText("\t" + a.getNhom());
                                lblT4_T.setBackground(Color.GREEN);
                            }
                            T4.setText("       " + df.format(a.getThoiGian()));
                        break;
                        case 5:
                            if (a.getCaThucHanh().compareTo("S") == 0) {
                                lblT5_S.setText("\t" + a.getNhom());
                                lblT5_S.setBackground(Color.GREEN);
                            } else if (a.getCaThucHanh().compareTo("C") == 0) {
                                lblT5_C.setText("\t" + a.getNhom());
                                lblT5_C.setBackground(Color.GREEN);
                            } else {
                                lblT5_T.setText("\t" + a.getNhom());
                                lblT5_T.setBackground(Color.GREEN);
                            }
                            T5.setText("       " + df.format(a.getThoiGian()));
                        break;
                        case 6:
                            if (a.getCaThucHanh().compareTo("S") == 0) {
                                lblT6_S.setText("\t" + a.getNhom());
                                lblT6_S.setBackground(Color.GREEN);
                            } else if (a.getCaThucHanh().compareTo("C") == 0) {
                                lblT6_C.setText("\t" + a.getNhom());
                                lblT6_C.setBackground(Color.GREEN);
                            } else {
                                lblT6_T.setText("\t" + a.getNhom());
                                lblT6_T.setBackground(Color.GREEN);
                            }
                            T6.setText("       " + df.format(a.getThoiGian()));
                        break;
                        case 7:
                            if (a.getCaThucHanh().compareTo("S") == 0) {
                                lblT7_S.setText("\t" + a.getNhom());
                                lblT7_S.setBackground(Color.GREEN);
                            } else if (a.getCaThucHanh().compareTo("C") == 0) {
                                lblT7_C.setText("\t" + a.getNhom());
                                lblT7_C.setBackground(Color.GREEN);
                            } else {
                                lblT7_T.setText("\t" + a.getNhom());
                                lblT7_T.setBackground(Color.GREEN);
                            }
                            T7.setText("       " + df.format(a.getThoiGian()));
                        break;
                        case 1:
                            if (a.getCaThucHanh().compareTo("S") == 0) {
                                lblTCN_S.setText("\t" + a.getNhom());
                                lblTCN_S.setBackground(Color.GREEN);
                            } else if (a.getCaThucHanh().compareTo("C") == 0) {
                                lblTCN_C.setText("\t" + a.getNhom());
                                lblTCN_C.setBackground(Color.GREEN);
                            } else {
                                lblCN_T.setText("\t" + a.getNhom());
                                lblCN_T.setBackground(Color.GREEN);
                            }
//                            ca.set(Calendar.DATE, ca.get(Calendar.DAY_OF_MONTH)+7);
//                            Date d = ca.getTime();
                            CN.setText("       " + df.format(a.getThoiGian()));
                        break;

                    }
                }

            });
        }

    }

    private void xoaTrang() {
        String empty = "\t#N/A";
        T2.setText(empty);
        T3.setText(empty);
        T4.setText(empty);
        T5.setText(empty);
        T6.setText(empty);
        T7.setText(empty);
        CN.setText(empty);
        lblT2_S.setText(empty);
        lblT3_S.setText(empty);
        lblT4_S.setText(empty);
        lblT5_S.setText(empty);
        lblT6_S.setText(empty);
        lblT7_S.setText(empty);
        lblTCN_S.setText(empty);
        lblT2_C.setText(empty);
        lblT3_C.setText(empty);
        lblT4_C.setText(empty);
        lblT5_C.setText(empty);
        lblT6_C.setText(empty);
        lblT7_C.setText(empty);
        lblTCN_C.setText(empty);
        lblT2_T.setText(empty);
        lblT3_T.setText(empty);
        lblT4_T.setText(empty);
        lblT5_T.setText(empty);
        lblT6_T.setText(empty);
        lblT7_T.setText(empty);
        lblCN_T.setText(empty);

        lblT2_S.setBackground(Color.WHITE);
        lblT3_S.setBackground(Color.WHITE);
        lblT4_S.setBackground(Color.WHITE);
        lblT5_S.setBackground(Color.WHITE);
        lblT6_S.setBackground(Color.WHITE);
        lblT7_S.setBackground(Color.WHITE);
        lblTCN_S.setBackground(Color.WHITE);
        lblT2_C.setBackground(Color.WHITE);
        lblT3_C.setBackground(Color.WHITE);
        lblT4_C.setBackground(Color.WHITE);
        lblT5_C.setBackground(Color.WHITE);
        lblT6_C.setBackground(Color.WHITE);
        lblT7_C.setBackground(Color.WHITE);
        lblTCN_C.setBackground(Color.WHITE);
        lblT2_T.setBackground(Color.WHITE);
        lblT3_T.setBackground(Color.WHITE);
        lblT4_T.setBackground(Color.WHITE);
        lblT5_T.setBackground(Color.WHITE);
        lblT6_T.setBackground(Color.WHITE);
        lblT7_T.setBackground(Color.WHITE);
        lblCN_T.setBackground(Color.WHITE);

        CaS.setText("  S");
        CaC.setText("  C");
        CaT.setText("  T");
    }
      public ArrayList findAll() {
        ArrayList<PhongMay> pTs = new ArrayList<>();
        String findSQL = "SELECT * FROM PHONGMAY";
        try {
            //PreparedStatement preStmt = Database.getPrepareStatement(findSQL);
            ResultSet rs = Database.getData(findSQL);
            while (rs.next()) {
                PhongMay a = new PhongMay();
                a.setMaPhong(rs.getString("MaPhong"));
                a.setTenPhong(rs.getString("TenPhong"));
                a.setSoLuongMay(rs.getInt("SoLuongMay"));
                a.setViTriPhong(rs.getString("ViTriPhong"));
                a.setCoSoVatChat(rs.getString("CoSoVatChat"));
                pTs.add(a);
            }
            Database.closeConnection();
        } catch (SQLException ex) {
          ex.printStackTrace();
          JOptionPane.showMessageDialog(null, "Tải dữ liệu từ CSDL thất bại!", "Thông báo", JOptionPane.ERROR_MESSAGE);
        }
        return pTs;
    }
 public ArrayList findByMaPhong(String maP) {
        ArrayList<DangKyPhong> pTs = new ArrayList<>();
        String findSQL = "SELECT * FROM DANGKYPHONG WHERE MaPhong = '" + maP+"' ORDER BY ThoiGian ASC";
        try {
            
                ResultSet rs = Database.getData(findSQL);
            while (rs.next()) {
                DangKyPhong a = new DangKyPhong();
                a.setMaNhanVien(rs.getString("MaGiangVien"));
                a.setCaThucHanh(rs.getString("CaThucHanh"));
                a.setMaLop(rs.getString("MaLop"));
                a.setMaPhong(rs.getString("MaPhong"));
                a.setNhom(rs.getString("Nhom"));
                a.setThoiGian(rs.getDate("ThoiGian"));

                pTs.add(a);
            }
            Database.closeConnection();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Tải dữ liệu từ CSDL thất bại!", "Thông báo", JOptionPane.ERROR_MESSAGE);
        }
        return pTs;
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblT3_C = new javax.swing.JTextField();
        lblT4_C = new javax.swing.JTextField();
        lblT5_C = new javax.swing.JTextField();
        lblT6_C = new javax.swing.JTextField();
        CN = new javax.swing.JTextField();
        lblT7_C = new javax.swing.JTextField();
        T3 = new javax.swing.JTextField();
        lblTCN_C = new javax.swing.JTextField();
        lblT6_T = new javax.swing.JTextField();
        lblT7_T = new javax.swing.JTextField();
        lblT2_C = new javax.swing.JTextField();
        lblT3_T = new javax.swing.JTextField();
        lblCN_T = new javax.swing.JTextField();
        lblT4_T = new javax.swing.JTextField();
        lblT2_T = new javax.swing.JTextField();
        cbTuan = new javax.swing.JComboBox<>();
        CBAddressP = new javax.swing.JComboBox<>();
        CaT = new javax.swing.JTextField();
        lblT5_T = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        btnBack = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        T4 = new javax.swing.JTextField();
        T2 = new javax.swing.JTextField();
        CBNameP = new javax.swing.JComboBox<>();
        T5 = new javax.swing.JTextField();
        CaC = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        T7 = new javax.swing.JTextField();
        CaS = new javax.swing.JTextField();
        T6 = new javax.swing.JTextField();
        lblT2_S = new javax.swing.JTextField();
        lblT3_S = new javax.swing.JTextField();
        lblT4_S = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        lblT5_S = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        lblT6_S = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        lblAdd = new javax.swing.JLabel();
        lblCSVT = new javax.swing.JLabel();
        lblIDP = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblNamePH = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblNameP = new javax.swing.JLabel();
        lblSLM = new javax.swing.JLabel();
        lblT7_S = new javax.swing.JTextField();
        lblTCN_S = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblT3_C.setEditable(false);
        lblT3_C.setBackground(new java.awt.Color(255, 255, 255));
        lblT3_C.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        lblT3_C.setText("#N/A");
        lblT3_C.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        lblT4_C.setEditable(false);
        lblT4_C.setBackground(new java.awt.Color(255, 255, 255));
        lblT4_C.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        lblT4_C.setText("#N/A");
        lblT4_C.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        lblT5_C.setEditable(false);
        lblT5_C.setBackground(new java.awt.Color(255, 255, 255));
        lblT5_C.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        lblT5_C.setText("#N/A");
        lblT5_C.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        lblT6_C.setEditable(false);
        lblT6_C.setBackground(new java.awt.Color(255, 255, 255));
        lblT6_C.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        lblT6_C.setText("#N/A");
        lblT6_C.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        CN.setEditable(false);
        CN.setBackground(new java.awt.Color(255, 255, 255));
        CN.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        CN.setText("#N/A");
        CN.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        lblT7_C.setEditable(false);
        lblT7_C.setBackground(new java.awt.Color(255, 255, 255));
        lblT7_C.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        lblT7_C.setText("#N/A");
        lblT7_C.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        T3.setEditable(false);
        T3.setBackground(new java.awt.Color(255, 255, 255));
        T3.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        T3.setText("#N/A");
        T3.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        lblTCN_C.setEditable(false);
        lblTCN_C.setBackground(new java.awt.Color(255, 255, 255));
        lblTCN_C.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        lblTCN_C.setText("#N/A");
        lblTCN_C.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        lblT6_T.setEditable(false);
        lblT6_T.setBackground(new java.awt.Color(255, 255, 255));
        lblT6_T.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        lblT6_T.setText("#N/A");
        lblT6_T.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        lblT7_T.setEditable(false);
        lblT7_T.setBackground(new java.awt.Color(255, 255, 255));
        lblT7_T.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        lblT7_T.setText("#N/A");
        lblT7_T.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        lblT2_C.setEditable(false);
        lblT2_C.setBackground(new java.awt.Color(255, 255, 255));
        lblT2_C.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        lblT2_C.setText("#N/A");
        lblT2_C.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        lblT3_T.setEditable(false);
        lblT3_T.setBackground(new java.awt.Color(255, 255, 255));
        lblT3_T.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        lblT3_T.setText("#N/A");
        lblT3_T.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        lblCN_T.setEditable(false);
        lblCN_T.setBackground(new java.awt.Color(255, 255, 255));
        lblCN_T.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        lblCN_T.setText("#N/A");
        lblCN_T.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        lblT4_T.setEditable(false);
        lblT4_T.setBackground(new java.awt.Color(255, 255, 255));
        lblT4_T.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        lblT4_T.setText("#N/A");
        lblT4_T.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        lblT2_T.setEditable(false);
        lblT2_T.setBackground(new java.awt.Color(255, 255, 255));
        lblT2_T.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        lblT2_T.setText("#N/A");
        lblT2_T.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        cbTuan.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        cbTuan.setAutoscrolls(true);
        cbTuan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbTuanMouseClicked(evt);
            }
        });
        cbTuan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTuanActionPerformed(evt);
            }
        });

        CBAddressP.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        CBAddressP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CBAddressPActionPerformed(evt);
            }
        });

        CaT.setEditable(false);
        CaT.setBackground(new java.awt.Color(255, 255, 255));
        CaT.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        CaT.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        CaT.setText("T");
        CaT.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        lblT5_T.setEditable(false);
        lblT5_T.setBackground(new java.awt.Color(255, 255, 255));
        lblT5_T.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        lblT5_T.setText("#N/A");
        lblT5_T.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        jLabel10.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel10.setText("Vị trí phòng:");

        btnBack.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        btnBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Back-arrow.png"))); // NOI18N
        btnBack.setText("Quay Lại");
        btnBack.setAutoscrolls(true);
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel8.setText("Tuần:");
        jLabel8.setAutoscrolls(true);

        jLabel11.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel11.setText("Tên Phòng:");

        T4.setEditable(false);
        T4.setBackground(new java.awt.Color(255, 255, 255));
        T4.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        T4.setText("#N/A");
        T4.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        T2.setEditable(false);
        T2.setBackground(new java.awt.Color(255, 255, 255));
        T2.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        T2.setText("#N/A");
        T2.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        CBNameP.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        CBNameP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CBNamePActionPerformed(evt);
            }
        });

        T5.setEditable(false);
        T5.setBackground(new java.awt.Color(255, 255, 255));
        T5.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        T5.setText("#N/A");
        T5.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        CaC.setEditable(false);
        CaC.setBackground(new java.awt.Color(255, 255, 255));
        CaC.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        CaC.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        CaC.setText("C");
        CaC.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        jLabel7.setFont(new java.awt.Font("Roboto", 1, 20)); // NOI18N
        jLabel7.setText("DANH SÁCH PHÒNG");

        T7.setEditable(false);
        T7.setBackground(new java.awt.Color(255, 255, 255));
        T7.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        T7.setText("#N/A");
        T7.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        CaS.setEditable(false);
        CaS.setBackground(new java.awt.Color(255, 255, 255));
        CaS.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        CaS.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        CaS.setText("S");
        CaS.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        T6.setEditable(false);
        T6.setBackground(new java.awt.Color(255, 255, 255));
        T6.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        T6.setText("#N/A");
        T6.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        lblT2_S.setEditable(false);
        lblT2_S.setBackground(new java.awt.Color(255, 255, 255));
        lblT2_S.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        lblT2_S.setText("#N/A");
        lblT2_S.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        lblT3_S.setEditable(false);
        lblT3_S.setBackground(new java.awt.Color(255, 255, 255));
        lblT3_S.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        lblT3_S.setText("#N/A");
        lblT3_S.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        lblT4_S.setEditable(false);
        lblT4_S.setBackground(new java.awt.Color(255, 255, 255));
        lblT4_S.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        lblT4_S.setText("#N/A");
        lblT4_S.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        jLabel1.setFont(new java.awt.Font("Roboto", 1, 15)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setText("Note:");

        jTextField1.setBackground(new java.awt.Color(0, 255, 0));

        lblT5_S.setEditable(false);
        lblT5_S.setBackground(new java.awt.Color(255, 255, 255));
        lblT5_S.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        lblT5_S.setText("#N/A");
        lblT5_S.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        jLabel9.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel9.setText(":    Đã có lịch");

        lblT6_S.setEditable(false);
        lblT6_S.setBackground(new java.awt.Color(255, 255, 255));
        lblT6_S.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        lblT6_S.setText("#N/A");
        lblT6_S.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        lblAdd.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        lblAdd.setAutoscrolls(true);

        lblCSVT.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        lblCSVT.setAutoscrolls(true);

        lblIDP.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        lblIDP.setAutoscrolls(true);

        jLabel3.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel3.setText("Tên Phòng:");
        jLabel3.setAutoscrolls(true);

        jLabel4.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel4.setText("Vị trí phòng:");
        jLabel4.setAutoscrolls(true);

        jLabel2.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel2.setText("Mã phòng:");
        jLabel2.setAutoscrolls(true);

        jLabel5.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel5.setText("Số máy:");
        jLabel5.setAutoscrolls(true);

        lblNamePH.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        lblNamePH.setText("THÔNG TIN PHÒNG MÁY");
        lblNamePH.setAutoscrolls(true);
        lblNamePH.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lblNamePH.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel6.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel6.setText("Cơ sở vật chất:");
        jLabel6.setAutoscrolls(true);

        lblNameP.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        lblNameP.setAutoscrolls(true);

        lblSLM.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        lblSLM.setAutoscrolls(true);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblIDP, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                            .addComponent(lblNameP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(71, 71, 71)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblSLM, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblCSVT, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)))
                    .addComponent(lblNamePH, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(176, 176, 176))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblNamePH)
                        .addGap(27, 27, 27)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(lblIDP, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(lblNameP, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(lblSLM, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(lblCSVT, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(lblAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        lblT7_S.setEditable(false);
        lblT7_S.setBackground(new java.awt.Color(255, 255, 255));
        lblT7_S.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        lblT7_S.setText("#N/A");
        lblT7_S.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        lblTCN_S.setEditable(false);
        lblTCN_S.setBackground(new java.awt.Color(255, 255, 255));
        lblTCN_S.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        lblTCN_S.setText("#N/A");
        lblTCN_S.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(CaC, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(CaS, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(9, 9, 9)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(lblT2_S, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(lblT3_S, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(lblT4_S, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(lblT5_S, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(lblT6_S, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(lblT7_S, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(lblTCN_S, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(T2, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(T3, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(T4, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(14, 14, 14)
                                            .addComponent(T5, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(T6, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(10, 10, 10)
                                            .addComponent(T7, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(CN)))
                                    .addGap(1, 1, 1))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(lblT2_C, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblT3_C, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(lblT4_C, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(lblT5_C, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(lblT6_C, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(lblT7_C, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(lblTCN_C, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel7)
                                        .addComponent(jLabel10)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel11)
                                                .addComponent(jLabel8))
                                            .addGap(53, 53, 53)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(cbTuan, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(CBAddressP, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(CBNameP, 0, 177, Short.MAX_VALUE)))))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 503, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(11, 11, 11)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(CaT, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblT2_T, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblT3_T, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblT4_T, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblT5_T, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblT6_T, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblT7_T, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblCN_T, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel9)))))
                .addContainerGap(47, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel7)
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(CBAddressP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(CBNameP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbTuan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(T2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(T7, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(T5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(T4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(T3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(T6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CN, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblT6_S)
                    .addComponent(lblT5_S)
                    .addComponent(lblT4_S)
                    .addComponent(lblT7_S)
                    .addComponent(lblTCN_S)
                    .addComponent(CaS, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblT2_S)
                    .addComponent(lblT3_S))
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lblTCN_C)
                    .addComponent(lblT6_C, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblT5_C, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblT4_C, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblT3_C, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(CaC, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblT7_C)
                    .addComponent(lblT2_C, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(CaT)
                    .addComponent(lblT3_T, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblT4_T)
                    .addComponent(lblT5_T)
                    .addComponent(lblT6_T)
                    .addComponent(lblT7_T)
                    .addComponent(lblCN_T)
                    .addComponent(lblT2_T, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(39, 39, 39))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbTuanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbTuanMouseClicked
        showByTuan();
    }//GEN-LAST:event_cbTuanMouseClicked

    private void cbTuanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTuanActionPerformed
        showByTuan();
    }//GEN-LAST:event_cbTuanActionPerformed

    private void CBAddressPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CBAddressPActionPerformed
        showTenPhong();
        cbTuan.removeAllItems();
    }//GEN-LAST:event_CBAddressPActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed

        if(DangNhap.getQuyenDangNhap().equals("giangvien"))
        new GiaoDienGV().setVisible(true);
        else
         new DKPhong().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnBackActionPerformed

    private void CBNamePActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CBNamePActionPerformed
        dsTuan.clear();
        cbTuan.removeAllItems();
        int i = CBNameP.getSelectedIndex();
        if (i > 0) {
            dsP = findAll();
            dsP.forEach((phong) -> {
                if (CBNameP.getSelectedItem().toString().compareTo(phong.getTenPhong()) == 0) {
                    pm = phong;
                }
            });

            loadData();
            showTuan();

        }

    }//GEN-LAST:event_CBNamePActionPerformed

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
            java.util.logging.Logger.getLogger(XemPhong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(XemPhong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(XemPhong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(XemPhong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new XemPhong().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> CBAddressP;
    private javax.swing.JComboBox<String> CBNameP;
    private javax.swing.JTextField CN;
    private javax.swing.JTextField CaC;
    private javax.swing.JTextField CaS;
    private javax.swing.JTextField CaT;
    private javax.swing.JTextField T2;
    private javax.swing.JTextField T3;
    private javax.swing.JTextField T4;
    private javax.swing.JTextField T5;
    private javax.swing.JTextField T6;
    private javax.swing.JTextField T7;
    private javax.swing.JButton btnBack;
    private javax.swing.JComboBox<String> cbTuan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel lblAdd;
    private javax.swing.JTextField lblCN_T;
    private javax.swing.JLabel lblCSVT;
    private javax.swing.JLabel lblIDP;
    private javax.swing.JLabel lblNameP;
    private javax.swing.JLabel lblNamePH;
    private javax.swing.JLabel lblSLM;
    private javax.swing.JTextField lblT2_C;
    private javax.swing.JTextField lblT2_S;
    private javax.swing.JTextField lblT2_T;
    private javax.swing.JTextField lblT3_C;
    private javax.swing.JTextField lblT3_S;
    private javax.swing.JTextField lblT3_T;
    private javax.swing.JTextField lblT4_C;
    private javax.swing.JTextField lblT4_S;
    private javax.swing.JTextField lblT4_T;
    private javax.swing.JTextField lblT5_C;
    private javax.swing.JTextField lblT5_S;
    private javax.swing.JTextField lblT5_T;
    private javax.swing.JTextField lblT6_C;
    private javax.swing.JTextField lblT6_S;
    private javax.swing.JTextField lblT6_T;
    private javax.swing.JTextField lblT7_C;
    private javax.swing.JTextField lblT7_S;
    private javax.swing.JTextField lblT7_T;
    private javax.swing.JTextField lblTCN_C;
    private javax.swing.JTextField lblTCN_S;
    // End of variables declaration//GEN-END:variables
}
