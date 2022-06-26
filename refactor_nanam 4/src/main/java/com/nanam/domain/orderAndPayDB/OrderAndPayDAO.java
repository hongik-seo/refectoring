package com.nanam.domain.orderAndPayDB;

import com.nanam.util.DBManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class OrderAndPayDAO {

    public void insertAccess(HashMap<String, String> sqlMap) throws SQLException {

        Connection conn = DBManager.getConnection();

        String sql = "INSERT INTO ORDER_INFO "+sqlMap.get("sql");

       // System.out.println(sql);

        Statement stmt = conn.createStatement();
        stmt.executeQuery(sql);


    }

    public ArrayList<OrderAndPayVO> selectAccess(HashMap<String, String> sqlMap) throws SQLException {

        Connection conn = DBManager.getConnection();

        ResultSet resultSet = null;

        String sql = "SELECT ID , USER_ID , CART_BOOK_TITLE , CART_BOOK_ISBN , CART_PRICE , USE_YN_OF_SERVICE, DATE_OF_CART, CART_OF_TOTAL_PRICE , CART_OF_VOLUME , CART_OF_TOTAL_VOLUME , CART_DECIDE , " +
                            "O_ID, O_YN , ORDER_NAME , ORDER_ID , ORDER_PHONE , ORDER_ZIPCODE, ORDER_DETAIL_ADDRESS, DATE_OF_ORDER  , ORDER_ADDRESS , ORDER_CALL "+sqlMap.get("sql");


        System.out.println(sql);

        Statement stmt = conn.createStatement();
        resultSet = stmt.executeQuery(sql);

        ArrayList<OrderAndPayVO> list = new ArrayList<>();

        while (resultSet.next()){

            OrderAndPayVO orderAndPayVO = new OrderAndPayVO();

            orderAndPayVO.setOId(resultSet.getLong("ID"));
            orderAndPayVO.setOrderId(resultSet.getString("USER_ID"));
            orderAndPayVO.setCartBookTitle(resultSet.getString("CART_BOOK_TITLE"));
            orderAndPayVO.setCartBookIsbn(resultSet.getString("CART_BOOK_ISBN"));
            orderAndPayVO.setCartPrice(resultSet.getLong("CART_PRICE"));
            orderAndPayVO.setOYn(resultSet.getString("USE_YN_OF_SERVICE"));
            orderAndPayVO.setDateOfCart(resultSet.getDate("DATE_OF_CART"));
            orderAndPayVO.setCartTotalPrice(resultSet.getLong("CART_OF_TOTAL_PRICE"));
            orderAndPayVO.setCartOfVolume(resultSet.getLong("CART_OF_VOLUME"));
            orderAndPayVO.setCartOfTotalVolume(resultSet.getLong("CART_OF_TOTAL_VOLUME"));
            orderAndPayVO.setCartDecide(resultSet.getString("CART_DECIDE"));
            orderAndPayVO.setOrderName(resultSet.getString("ORDER_NAME"));
            orderAndPayVO.setOrderId(resultSet.getString("ORDER_ID"));
            orderAndPayVO.setOrderPhone(resultSet.getString("ORDER_PHONE"));
            orderAndPayVO.setOrderZipcode(resultSet.getString("ORDER_ZIPCODE"));
            orderAndPayVO.setOrderDetailAddress(resultSet.getString("ORDER_DETAIL_ADDRESS"));
            orderAndPayVO.setDateOfOrder(resultSet.getDate("DATE_OF_ORDER"));
            orderAndPayVO.setOYn(resultSet.getString("O_YN"));
            orderAndPayVO.setOrderAddress(resultSet.getString("ORDER_ADDRESS"));
            orderAndPayVO.setOrderCall(resultSet.getString("ORDER_CALL"));

            list.add(orderAndPayVO);


        }


        return list;


    }

}
