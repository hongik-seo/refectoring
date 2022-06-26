package com.nanam.service;


import com.nanam.domain.pointDB.PointDAO;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.sql.SQLException;
import java.util.HashMap;

public class PointService {


    public JSONObject pointCharge(String point_info) {

            JSONParser jsonParser = new JSONParser();
            JSONObject successJson=null;
            try {
                 JSONObject jsonObject = (JSONObject)jsonParser.parse(point_info);

                 String id  =(String) jsonObject.get("ID");

                 String userId  =(String) jsonObject.get("USER_ID");

                 String volumeOfPoint  =(String) jsonObject.get("VOLUME_OF_POINT");

                 String phoneNumberForCharging  =(String) jsonObject.get("PHONE_NUMBER_FOR_CHARGING");

                 String ticketNumberForCharging  =(String) jsonObject.get("TICKET_NUMBER_FOR_CHARGING");

                 String userName  =(String) jsonObject.get("USER_NAME");

                 String totalBookPrice = (String) jsonObject.get("TOTAL_BOOK_PRICE");

                 HashMap<String,String> map = new HashMap<>();


                 map.put("ID",id);
                 map.put("USER_ID",userId);
                 map.put("VOLUME_OF_POINT", volumeOfPoint);
                 map.put("PHONE_NUMBER_FOR_CHARGING", phoneNumberForCharging);
                 map.put("TICKET_NUMBER_FOR_CHARGING", ticketNumberForCharging);
                 map.put("USER_NAME", userName);
                 map.put("TOTAL_BOOK_PRICE", "0");

                 PointDAO pointDAO = new PointDAO();

                 pointDAO.insertAccess(map);


                 String where = " WHERE ID = "+  map.get("ID") ;


                 map.put("sql", where);


                 long result=pointDAO.selectAccess(map);


                 String resultPoint = String.valueOf(result);


                 map.put("TOTAL_POINT", resultPoint);



                 String success = "success";



                 map.put("SUCCESS", success);



                 successJson = new JSONObject(map);


                 return successJson;

            } catch (ParseException | SQLException e) {
                e.printStackTrace();
            }

            return successJson;
    }


    public JSONObject pointSpend(String point_info) {

        JSONParser jsonParser = new JSONParser();
        JSONObject successJson=null;
        try {
            JSONObject jsonObject = (JSONObject)jsonParser.parse(point_info);

            String id  =(String) jsonObject.get("ID");

            String userId  =(String) jsonObject.get("USER_ID");


            String phoneNumberForCharging  =(String) jsonObject.get("PHONE_NUMBER_FOR_CHARGING");

            String ticketNumberForCharging  =(String) jsonObject.get("TICKET_NUMBER_FOR_CHARGING");

            String userName  =(String) jsonObject.get("USER_NAME");

            String totalBookPrice = (String) jsonObject.get("TOTAL_BOOK_PRICE");

            HashMap<String,String> map = new HashMap<>();


            map.put("ID",id);
            map.put("USER_ID",userId);
            map.put("VOLUME_OF_POINT", "0");
            map.put("PHONE_NUMBER_FOR_CHARGING", phoneNumberForCharging);
            map.put("TICKET_NUMBER_FOR_CHARGING", ticketNumberForCharging);
            map.put("USER_NAME", userName);
            map.put("TOTAL_BOOK_PRICE", totalBookPrice);

            //System.out.println(map);

            PointDAO pointDAO = new PointDAO();

            pointDAO.insertAccess(map);


            String where = " WHERE ID = "+  map.get("ID") ;


            map.put("sql", where);


            long result=pointDAO.selectAccess(map);


            String resultPoint = String.valueOf(result);


            map.put("TOTAL_POINT", resultPoint);



            String success = "success";



            map.put("SUCCESS", success);



            successJson = new JSONObject(map);


            return successJson;

        } catch (ParseException | SQLException e) {
            e.printStackTrace();
        }

        return successJson;
    }
}
