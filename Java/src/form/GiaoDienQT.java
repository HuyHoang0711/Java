
package form;

import classname.NguoiQuanTri;
import classname.PhongMay;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


public class GiaoDienQT extends javax.swing.JFrame {
    private String hoTen = "";
    private String maNhanVien = "";
    //private NguoiQuanTri quanTri = new NguoiQuanTri();
    private Color color = new Color(0,0,0);
    public GiaoDienQT() {
        initComponents();
        this.setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        JLabel background=new JLabel(new ImageIcon(getClass().getResource("/icon/Anh2.jpg")));
        add(background);
        background.setLayout(new FlowLayout());
        loadData(); 
        NameQT.setText(this.hoTen);
    }
    private void loadData(){
         this.hoTen = DangNhap.getTenNhanVien();
         this.maNhanVien = DangNhap.getMaNhanVien();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFrame1 = new javax.swing.JFrame();
        colorChooser = new javax.swing.JColorChooser();
        btOK = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        QLLop = new javax.swing.JButton();
        QLPhong = new javax.swing.JButton();
        QLGV = new javax.swing.JButton();
        LapLich = new javax.swing.JButton();
        Home = new javax.swing.JButton();
        NameQT = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        colorChooser.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N

        btOK.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        btOK.setText("OK");
        btOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btOKActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jFrame1Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(colorChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 666, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(61, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jFrame1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btOK, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(326, 326, 326))
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jFrame1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(colorChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btOK, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Trang chủ admin");

        jPanel1.setBackground(new java.awt.Color(0, 153, 153));
        jPanel1.setPreferredSize(new java.awt.Dimension(1920, 47));

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/ThongKe.png"))); // NOI18N
        jButton1.setText("Thống kê");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        QLLop.setBackground(new java.awt.Color(255, 255, 255));
        QLLop.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        QLLop.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/QLLop.png"))); // NOI18N
        QLLop.setText("Quản lý Lớp");
        QLLop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                QLLopActionPerformed(evt);
            }
        });

        QLPhong.setBackground(new java.awt.Color(255, 255, 255));
        QLPhong.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        QLPhong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/QLPhong.png"))); // NOI18N
        QLPhong.setText("Quản lý phòng");
        QLPhong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                QLPhongActionPerformed(evt);
            }
        });

        QLGV.setBackground(new java.awt.Color(255, 255, 255));
        QLGV.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        QLGV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/QLGiangVien.png"))); // NOI18N
        QLGV.setText("Quản lý GV");
        QLGV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                QLGVActionPerformed(evt);
            }
        });

        LapLich.setBackground(new java.awt.Color(255, 255, 255));
        LapLich.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        LapLich.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/LapLich.png"))); // NOI18N
        LapLich.setText("Lập lịch");
        LapLich.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LapLichActionPerformed(evt);
            }
        });

        Home.setBackground(new java.awt.Color(255, 255, 255));
        Home.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Home.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/TrangChu.png"))); // NOI18N
        Home.setText("Trang Chủ");
        Home.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HomeActionPerformed(evt);
            }
        });

        NameQT.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        NameQT.setForeground(new java.awt.Color(255, 255, 255));
        NameQT.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Power-button.png"))); // NOI18N
        jButton2.setText("Đăng xuất");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(Home)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(QLLop, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(QLPhong)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(QLGV, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LapLich, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 81, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(NameQT, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jButton2)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Home, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(QLLop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(QLPhong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(QLGV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(LapLich, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(NameQT, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jMenu5.setText("Edit");

        jMenuItem1.setText("Color");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem1);

        jMenuBar2.add(jMenu5);

        setJMenuBar(jMenuBar2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1273, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(677, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void HomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HomeActionPerformed
        // TODO add your handling code here:
        new GiaoDienQT().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_HomeActionPerformed

    private void QLLopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_QLLopActionPerformed
        // TODO add your handling code here:
        new QuanLyLop().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_QLLopActionPerformed

    private void QLPhongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_QLPhongActionPerformed
        // TODO add your handling code here:
        new QLPhong().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_QLPhongActionPerformed

    private void QLGVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_QLGVActionPerformed
        // TODO add your handling code here:
        new QLGiangVien().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_QLGVActionPerformed

    private void LapLichActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LapLichActionPerformed
        // TODO add your handling code here:
        new LapLich().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_LapLichActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        new ThongKe().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
         int luaChon = JOptionPane.showConfirmDialog(null, "Bạn chắc chắn muốn thoát?", "Thông báo", JOptionPane.OK_CANCEL_OPTION);
        if(luaChon == JOptionPane.OK_OPTION)
        {
        new StartPage().setVisible(true);
        this.dispose();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btOKActionPerformed
        // TODO add your handling code here:
        jFrame1.dispose();
        color = colorChooser.getColor();
        jPanel1.setBackground(color);
    }//GEN-LAST:event_btOKActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        jFrame1.setSize((int)(this.getSize().getWidth()/1.7), (int)(this.getSize().getHeight()/1.7));
        jFrame1.setLocation((int)(this.getLocation().getX()*2.5), (int)(this.getLocation().getY()*2.5));
        jFrame1.setTitle("Color");
        jFrame1.setVisible(true);
        
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    
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
            java.util.logging.Logger.getLogger(GiaoDienQT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GiaoDienQT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GiaoDienQT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GiaoDienQT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GiaoDienQT().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Home;
    private javax.swing.JButton LapLich;
    private javax.swing.JLabel NameQT;
    private javax.swing.JButton QLGV;
    private javax.swing.JButton QLLop;
    private javax.swing.JButton QLPhong;
    private javax.swing.JButton btOK;
    private javax.swing.JColorChooser colorChooser;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
