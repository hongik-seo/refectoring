package com.nanam.service;

import com.fasterxml.jackson.core.JsonParser;
import com.nanam.domain.cartDB.CartDAO;
import com.nanam.domain.cartDB.CartVO;
import com.nanam.domain.orderAndPayDB.OrderAndPayDAO;
import com.nanam.domain.orderAndPayDB.OrderAndPayVO;
import org.apache.axis.handlers.http.HTTPActionHandler;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.sql.SQLException;
import java.util.*;

public class PurchaseService {

    public HashMap<String, String>  cartInsertUserAndCart(HashMap<String, String> map){

        CartDAO cartDAO = new CartDAO();

        String where = "(       ID " +
                            " , USER_ID " +
                            " , CART_BOOK_TITLE " +
                            " , CART_BOOK_ISBN " +
                            " , CART_PRICE " +
                            " , USE_YN_OF_SERVICE " +
                            " , CART_SEQ " +

                            ") " +
                            " VALUES (" +
                            "   (SELECT ID FROM MEMBER WHERE ID = " +Long.valueOf(map.get("ID"))+")" +
                            " , (SELECT USER_ID FROM MEMBER WHERE USER_ID = " +"'"+map.get("USER_ID")+"'"  + ")" +
                            " , (SELECT BOOK_TITLE_INFO FROM BOOK WHERE BOOK_ISBN = " +"'"+map.get("BOOK_ISBN")+ "'" +")" +
                            " , " +"'"+map.get("BOOK_ISBN")+ "'" +
                            " , ( SELECT BOOK_PRICE FROM BOOK WHERE BOOK_ISBN = " + "'" +map.get("BOOK_ISBN")+  "'" +")" +
                            " , 'Y' " +
                            " , CART_SEQ.nextval " +
                            ")";

        HashMap<String,String> sqlMap = new HashMap<>();

        sqlMap.put("sql", where);

        try {
            cartDAO.insertAccess(sqlMap);
            return map;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    public ArrayList<CartVO> showCartList(HashMap<String, String> map){

        ArrayList<CartVO> cartlist = null;

        CartDAO cartDAO = new CartDAO();

        String sql ="  FROM CART c " +
                    "  INNER JOIN BOOK b ON c.CART_BOOK_ISBN = b.BOOK_ISBN " +
                    "  INNER JOIN MEMBER m ON m.ID = c.ID    WHERE c.ID =" + "'"+map.get("ID")+"'" +" AND TO_DATE(sysdate,'YYYY-MM-DD' ) - TO_DATE( c.DATE_OF_CART ,  'YYYY-MM-DD' ) < 3 " ;

        map.put("sql", sql);

        try {

            cartlist= cartDAO.selectAccess(map);


        } catch (SQLException e) {
            e.printStackTrace();
        }


        return cartlist;
    }

    public void  updateCartPrice(HashMap<String,String> map){


        CartDAO cartDAO = new CartDAO();

        HashMap<String,String> sqlMap = new HashMap<>();

        String where =" SET  CART_PRICE  = "+""+map.get("BOOK_PRICE")+"" +" WHERE CART_BOOK_ISBN = (SELECT BOOK_ISBN FROM BOOK WHERE " +map.get("BOOK_SEQ") +"=" + map.get("SEQ") +")" ;

        sqlMap.put("sql",where);

        try {
            cartDAO.updateAccess(sqlMap);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public String deleteButUseYnUpdate(String deleteData) {

        try {
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(deleteData);

            String idStr = (String) jsonObject.get("ID");
            String cartStr = (String) jsonObject.get("CART_SEQ");
            String cartDecideStr = (String) jsonObject.get("CART_DECIDE");

            CartDAO cartDAO = new CartDAO();

            long id = Long.valueOf(idStr);
            long cartSeq = Long.valueOf(cartStr);

            HashMap<String, String> sqlMap = new HashMap<>();

            String where = "  SET USE_YN_OF_SERVICE = 'N' " +
                                 " , CART_PRICE  =  0  " +
                                 " , CART_DECIDE = 'N'  "
                                      + " WHERE ID = " + id + "  " +
                                          " AND CART_SEQ =" + cartSeq + "" +
                                          " AND (TO_DATE(sysdate,'YYYY-MM-DD' ) - TO_DATE( DATE_OF_CART ,  'YYYY-MM-DD' ) <7) " +
                                          " AND (USE_YN_OF_SERVICE = 'Y')"  + "";

            sqlMap.put("sql", where);

            cartDAO.updateAccess(sqlMap);


        } catch (ParseException | SQLException e) {
            e.printStackTrace();
        }
        return deleteData;


    }

    public ArrayList<CartVO> updateCartTotalAndInsertCartFinCartAndSelectCARTFIN(ArrayList<CartVO> list){
        ArrayList<CartVO> resultList =null;


        //System.out.println("updateCartTotalAndInsertCartFinCartAndSelectCARTFIN::"+list);
        HashMap<String, String> sqlMap = new HashMap<>();

        CartDAO cartDAO = new CartDAO();

        for(int i=0; i<list.size(); i++) {


            String updateWhere = "   SET  CART_OF_VOLUME = " + list.get(i).getCartOfVolume() + "" +
                             " , CART_OF_TOTAL_VOLUME = " + list.get(0).getCartOfTotalVolume() + "" +
                             " , CART_OF_TOTAL_PRICE  = " + list.get(0).getCartTotalPrice() + "" +
                                            "  WHERE (ID = " + list.get(0).getId() + " ) " +
                                            "    AND (USE_YN_OF_SERVICE = 'Y')" +
                                            "    AND (CART_DECIDE = 'Y')" +
                                            "    AND (CART_SEQ = " +list.get(i).getCartSeq()+ ")" +
                                            "    AND (CART_PRICE != 0  )";

            sqlMap.put("sql", updateWhere);

            try {
                cartDAO.updateAccess(sqlMap);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


        String cartFinInsertWhere = "_FINAL  SELECT * FROM CART "+   "  WHERE (ID = "+ list.get(0).getId() + " ) " +
                                                                     "    AND (TO_DATE(sysdate,'YYYY-MM-DD' ) - TO_DATE( DATE_OF_CART ,  'YYYY-MM-DD' ) <1) " +
                                                                     "    AND (USE_YN_OF_SERVICE = 'Y')"+
                                                                     "    AND (CART_DECIDE = 'Y')"+
                                                                     "    AND (CART_PRICE != 0  )";

        sqlMap.put("sql", cartFinInsertWhere);

        try {
            cartDAO.insertAccess(sqlMap);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String cartFinSelectWhere  = "  FROM CART_FINAL c " +
                                     "  INNER JOIN BOOK b ON c.CART_BOOK_ISBN = b.BOOK_ISBN " +
                                     "  INNER JOIN MEMBER m ON m.ID = c.ID  " +
                                     "  WHERE (c.ID = "+ list.get(0).getId() + " ) " +
                                     "    AND (TO_DATE(sysdate,'YYYY-MM-DD' ) - TO_DATE( c.DATE_OF_CART ,  'YYYY-MM-DD' ) <=1) " +
                                     "    AND (c.USE_YN_OF_SERVICE = 'Y')"+
                                     "    AND (c.CART_DECIDE = 'Y')"+"" +
                                     "    AND (c.CART_PRICE != 0  )";

        sqlMap.put("sql", cartFinSelectWhere);


        try {
            resultList = cartDAO.selectAccess(sqlMap);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        String cartUpdateDecideAndUseYn = " SET CART_DECIDE = 'N'  WHERE (ID = "+ list.get(0).getId() + " ) " +
                                                                "    AND (TO_DATE(sysdate,'YYYY-MM-DD' ) - TO_DATE( DATE_OF_CART ,  'YYYY-MM-DD' ) <1) " +
                                                                "    AND (USE_YN_OF_SERVICE = 'Y')"+
                                                                "    AND (CART_PRICE != 0  )";

        sqlMap.put("sql", cartUpdateDecideAndUseYn);
        try {
            cartDAO.updateAccess(sqlMap);
        } catch (SQLException e) {
            e.printStackTrace();
        }



        return resultList;
    }

    public ArrayList<CartVO> showOderList(ArrayList<CartVO> list){

        ArrayList<CartVO> resultList =null;

        CartDAO cartDAO = new CartDAO();

        HashMap<String,String> sqlMap = new HashMap<>();

        String cartFinSelectWhere  = "  FROM CART_FINAL c " +
                "  INNER JOIN BOOK b ON c.CART_BOOK_ISBN = b.BOOK_ISBN " +
                "  INNER JOIN MEMBER m ON m.ID = c.ID  " +
                "  WHERE (c.ID = "+ list.get(0).getId() + " ) " +
                "    AND (TO_DATE(sysdate,'YYYY-MM-DD' ) - TO_DATE( c.DATE_OF_CART ,  'YYYY-MM-DD' ) <=1) " +
                "    AND (c.USE_YN_OF_SERVICE = 'Y')"+
                "    AND (c.CART_DECIDE = 'Y')"+"" +
                "    AND (c.CART_PRICE != 0  )";

        sqlMap.put("sql", cartFinSelectWhere);


        try {
            resultList = cartDAO.selectAccess(sqlMap);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultList;
    }

    public JSONObject orderAndPay(String order_info) {

        JSONParser parser = new JSONParser();

        JSONObject jsonObjectResult =null;
        HashMap<String,String> map = new HashMap<>();
        try {
            JSONObject jsonObject = (JSONObject) parser.parse(order_info);

            String id=  (String)jsonObject.get("ID");
            String orderId= (String)jsonObject.get("ORDER_ID");
            String orderName = (String)jsonObject.get("ORDER_NAME");
            String orderPhone = (String)jsonObject.get("ORDER_PHONE");
            String orderZipcode = (String)jsonObject.get("ORDER_ZIPCODE");
            String orderAddress = (String)jsonObject.get("ORDER_ADDRESS");
            String orderDetailAddress = (String)jsonObject.get("ORDER_DETAIL_ADDRESS");
            String orderCall = (String)jsonObject.get("ORDER_CALL");



            map.put("ORDER_NAME",orderName);
            map.put("ORDER_PHONE", orderPhone);
            map.put("ORDER_ZIPCODE", orderZipcode);
            map.put("ORDER_ADDRESS", orderAddress);
            map.put("ORDER_DETAIL_ADDRESS", orderDetailAddress);
            map.put("ORDER_CALL", orderCall);


            Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();

            String key = "";
            String val = "";

            while (iterator.hasNext()){

                Map.Entry<String, String> it = iterator.next();

                String keys = it.getKey();

                String vals = it.getValue();

                key += keys +",";

                val += "'"+vals+"'" +",";



            }


            String sqlInsertOrder = "("+ key  +" ORDER_SEQ , ID , ORDER_ID )  "+ "VALUES (" + val+" ORDER_SEQ.nextval , (SELECT ID FROM MEMBER WHERE ID = "+id+" ) , (SELECT USER_ID FROM MEMBER WHERE ID = "+id+ ")  )";


            OrderAndPayDAO orderAndPayDAO = new OrderAndPayDAO();

            map.put("sql", sqlInsertOrder);

            orderAndPayDAO.insertAccess(map);


            CartDAO cartDAO = new CartDAO();

            String sqlCartFinUseYn = "_FINAL SET USE_YN_OF_SERVICE = 'N' WHERE ID = " +id;

            map.put("sql", sqlCartFinUseYn);

            cartDAO.updateAccess(map);


            jsonObjectResult= new JSONObject(map);

            return jsonObjectResult;

        } catch (ParseException | SQLException e) {
            e.printStackTrace();
        }
        return jsonObjectResult;

    }


    public ArrayList<OrderAndPayVO> showBuyResultList(ArrayList<OrderAndPayVO> list){

        ArrayList<OrderAndPayVO> resultList =null;

        OrderAndPayDAO OrderAndPayResultDAO = new OrderAndPayDAO();

        HashMap<String,String> sqlMap = new HashMap<>();

        String orderResultWhere  = "  FROM ORDER_INFO_VIEW " +
                "  WHERE ( ID = "+ list.get(0).getId() + " ) " +
                "    AND ( DATE_OF_ORDER BETWEEN DATE_OF_CART AND sysdate )  ";

        sqlMap.put("sql", orderResultWhere);


        try {
            resultList = OrderAndPayResultDAO.selectAccess(sqlMap);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultList;
    }
}
