/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;
import classname.DangKyPhong;
import classname.GiangVien;
import classname.LopDocLap;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
/**
 *
 * @author Admin
 */
public class Database {
    private static final String className = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static final String Url = "jdbc:sqlserver://DESKTOP-DT03IGN\\SQLEXPRESS01:1433;databaseName=QLThucHanh;integratedSecurity=true";
    private static String userName = "";
    private static String passWord = "";
    private static Connection connection = null;
    private static ResultSet rs = null;
    private static Statement statement = null;
    private static PreparedStatement preStatement = null;
    public static Connection getConnection()
    {
        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(Url, userName, passWord);
        }
        catch(ClassNotFoundException|SQLException ex)
        {
           // ex.printStackTrace();
           JOptionPane.showMessageDialog(null, "Không thể kết nối CSDL!", "Thông báo", JOptionPane.ERROR_MESSAGE);
        }
            return connection;
    }
    public static ResultSet getData(String sql) throws SQLException
    {
      //  try{
        statement = getConnection().createStatement();
         return statement.executeQuery(sql);
      //  }
      //  catch(SQLException ex)
       /// {
       //     ex.printStackTrace();
            //JOptionPane.showMessageDialog(null, "Lấy dữ liệu không thành công!", "Thông báo", JOptionPane.ERROR_MESSAGE);
      ///  }
      //   return null;
    }
    public static Statement getStatement() throws SQLException
    {
     //   try
      //  {
        statement = getConnection().createStatement();
        return statement;
     //   }
     ///   catch(SQLException ex)
     //   {
      //      ex.printStackTrace();
            //JOptionPane.showMessageDialog(null, "Lấy Statement không thành công!", "Thông báo", JOptionPane.ERROR_MESSAGE);
      //  }
     //   return null;
    }
    public static PreparedStatement getPrepareStatement(String sql) throws SQLException
    {
      //  try{
        preStatement = getConnection().prepareStatement(sql);
        return preStatement;
      //  }
      //  catch(SQLException ex)
       // {
        //    ex.printStackTrace();
            //JOptionPane.showMessageDialog(null, "Lấy PreparedStatement không thành công!", "Thông báo", JOptionPane.ERROR_MESSAGE);

      //  }
       /// return null;
    }
    public static void closeConnection()
    {
        try{
        if(rs != null && !rs.isClosed())
        {
            rs.close();
            rs = null;
        }
        if(statement != null && !statement.isClosed())
        {
            statement.close();
            statement = null;
        }
         if(preStatement != null && !preStatement.isClosed())
        {
            preStatement.close();
            preStatement = null;
        }
         if(connection != null && !connection.isClosed())
         {
             connection.close();
             connection = null;
         }
        }
        catch(SQLException ex)
        {
              JOptionPane.showMessageDialog(null, "Lỗi đóng CSDL!", "Thông báo", JOptionPane.ERROR_MESSAGE);
        }       
    }
}
