package com.nanam.domain.cartDB;

import com.nanam.domain.bookDB.BookVO;
import com.nanam.util.DBManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CartDAO {

    public void insertAccess(HashMap<String, String> sqlMap) throws SQLException {

        Connection conn = DBManager.getConnection();

        String sql = "INSERT INTO CART"+sqlMap.get("sql");


        Statement stmt = conn.createStatement();
        stmt.executeQuery(sql);


    }


    public ArrayList<CartVO> selectAccess(HashMap<String, String> map) throws SQLException {

        ArrayList<CartVO> cartList= new ArrayList<>();

        Connection conn = DBManager.getConnection();

        String sql ="SELECT c.ID  resultId" +
                        " , c.USER_ID resultUserID " +
                        " , c.CART_BOOK_TITLE resultCartBookTitle " +
                        " , c.CART_BOOK_ISBN resultCartBookIsbn  " +
                        " , c.CART_PRICE resultCartPrice  " +
                        " , c.DATE_OF_CART resultDate  " +
                        " , c.USE_YN_OF_SERVICE resultUseYn  " +
                        " , c.CART_SEQ resultSeq " +
                        " , c.CART_DECIDE resultCartDecide  "+
                        " , c.CART_OF_TOTAL_VOLUME resultCartOfTotalVolume  "+
                        " , c.CART_OF_TOTAL_PRICE resultCartOfTotalPrice  "+
                        " , c.CART_OF_VOLUME resultCartOfVOLUME  "+
                        " , replace( b.BOOK_IMG , 'C:\\fakepath\\' , '') resultImg " +
                        "   " + map.get("sql");


        //System.out.println("cartSELECT::::::::::::"+sql);

        Statement stmt = conn.createStatement();
        ResultSet resultSet =stmt.executeQuery(sql);

        while (resultSet.next()){
            CartVO cartVO = new CartVO();

            cartVO.setId(resultSet.getLong("resultId"))
                    .setUserId(resultSet.getString("resultUserID"))
                    .setCartBookTitle(resultSet.getString("resultCartBookTitle"))
                    .setCartBookIsbn(resultSet.getString("resultCartBookIsbn"))
                    .setCartPrice(resultSet.getLong("resultCartPrice"))
                    .setDateOfCart(resultSet.getDate("resultDate"))
                    .setUseYnOfService(resultSet.getString("resultUseYn"))
                    .setCartSeq(resultSet.getLong("resultSeq"))
                    .setBookImg(resultSet.getString("resultImg"));

            cartVO.setCartDecide(resultSet.getString("resultCartDecide"));
            cartVO.setCartOfVolume(resultSet.getLong("resultCartOfVOLUME"));
            cartVO.setCartOfTotalVolume(resultSet.getLong("resultCartOfTotalVolume"));
            cartVO.setCartTotalPrice(resultSet.getLong("resultCartOfTotalPrice"));

            cartList.add(cartVO);
        }


        return cartList;

    }


    public void updateAccess(HashMap<String, String> sqlMap) throws SQLException {

        Connection conn = DBManager.getConnection();

        String sql = "UPDATE CART" +sqlMap.get("sql")  ;

        //System.out.println("updatdCart SQL::::::::::::::" +sql);

        Statement stmt = conn.createStatement();
        stmt.executeQuery(sql);

    }



}
