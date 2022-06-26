package com.nanam.util;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBManager{

  public static String url ="jdbc:oracle:thin:@a:1521:ORCL";
  public static String uid ="NANAM";
  public static String pwd ="NANAM";

  public static Connection getConnection(){
    Connection conn = null;
   // System.out.println("디비 연결중");
    try {
      Class.forName("oracle.jdbc.driver.OracleDriver");
      conn = DriverManager.getConnection(url, uid, pwd);

      //   System.out.println("디비 연결됨");
    }
    catch (Exception e) {
      e.printStackTrace();

    }
    return conn;
  }

  public static void close(Connection conn, PreparedStatement pstmt, ResultSet rset){
    if (rset != null) {
      try {
        rset.close();
      }
      catch (SQLException e) {
        e.printStackTrace();
      }
    }
    if (pstmt != null) {
      try {
        pstmt.close();
      }
      catch (SQLException e) {
        e.printStackTrace();
      }
    }
    if (conn != null)
      try {
        conn.close();
      }
      catch (SQLException e) {
        e.printStackTrace();
      }
  }

  public static void close(Connection conn, PreparedStatement pstmt)
  {
    if (pstmt != null) {
      try {
        pstmt.close();
      }
      catch (SQLException e) {
        e.printStackTrace();
      }
    }
    if (conn != null)
      try {
        conn.close();
      }
      catch (SQLException e) {
        e.printStackTrace();
      }
  }
}
