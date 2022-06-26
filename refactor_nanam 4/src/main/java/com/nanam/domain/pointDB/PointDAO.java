package com.nanam.domain.pointDB;

import com.nanam.util.DBManager;

import java.sql.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.Callable;

public class PointDAO {

    public void insertAccess(HashMap<String, String> map) throws SQLException {

        Connection conn = DBManager.getConnection();

        CallableStatement cstmt =null;

        String sql="{call POINT_SUM(?,?,?,?,?,?,?)}";

       // System.out.println(sql);

        cstmt = conn.prepareCall(sql);

        System.out.println("insertMapp:::::::"+map);


        cstmt.setLong(1, Long.valueOf(map.get("ID")));
        cstmt.setString(2, map.get("USER_ID"));
        cstmt.setString(3, map.get("USER_NAME"));
        cstmt.setString(4, map.get("TICKET_NUMBER_FOR_CHARGING"));
        cstmt.setString(5, map.get("PHONE_NUMBER_FOR_CHARGING"));
        cstmt.setLong(6,Long.valueOf(map.get("VOLUME_OF_POINT")));
        cstmt.setLong(7,Long.valueOf(map.get("TOTAL_BOOK_PRICE")));



        cstmt.executeUpdate();


    }

    public long selectAccess(HashMap<String, String> map) throws SQLException {

        Connection conn = DBManager.getConnection();

        Statement stmt = conn.createStatement();

        ResultSet rset = null;

        String sql = " SELECT TOTAL_POINT FROM POINT " + map.get("sql");

        //System.out.println(sql);

        rset=stmt.executeQuery(sql);

        long totalPoint = 0l;

        while (rset.next()){

            totalPoint=rset.getLong("TOTAL_POINT");

        }

        return totalPoint;


    }
}
