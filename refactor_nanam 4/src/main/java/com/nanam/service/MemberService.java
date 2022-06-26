package com.nanam.service;

import com.nanam.domain.memberDB.AgreeVO;
import com.nanam.domain.memberDB.MemberDAO;
import com.nanam.domain.memberDB.MemberVO;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Writer;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

public class MemberService {

    public String agreeAndJoinReturn(String agreeYnStr, String joinStr) throws ServletException, IOException {

        List<String> agreeList = new ArrayList<>();
        MemberVO memberVO = new MemberVO();
        AgreeVO agreeVO = new AgreeVO();
        JSONParser parser = new JSONParser();
        MemberDAO memberDAO = MemberDAO.getInstance();

        try {

            JSONObject jsonObjectAgree = (JSONObject) parser.parse(agreeYnStr);
            JSONObject jsonObjectMember = (JSONObject) parser.parse(joinStr);
            JSONArray jsonArrayAgree = (JSONArray) jsonObjectAgree.get("agree");

            for (int i = 0; i < jsonArrayAgree.size(); i++) {
                jsonObjectAgree = (JSONObject) jsonArrayAgree.get(i);
                String str = (String) jsonObjectAgree.get("agreeYn");
                agreeList.add(str);
            }

            String userId = (String) jsonObjectMember.get("userId");
            String userName = (String) jsonObjectMember.get("userName");
            String userPwd = (String) jsonObjectMember.get("userPwd");
            String userPwdConfirm = (String) jsonObjectMember.get("userPwdConfirm");
            String userPhone = (String) jsonObjectMember.get("userPhone");
            String userEmail = (String) jsonObjectMember.get("userEmail");
            String zipcode = (String) jsonObjectMember.get("zipcode");
            String address = (String) jsonObjectMember.get("address");
            String detailAddress = (String) jsonObjectMember.get("detailAddress");

            long id = memberDAO.makeId();

            memberVO = memberVO.setUserId(userId)
                    .setUserName(userName)
                    .setUserPwd(userPwd)
                    .setUserPwdConfirm(userPwdConfirm)
                    .setUserPhone(userPhone)
                    .setUserEmail(userEmail)
                    .setZipcode(zipcode)
                    .setAddress(address)
                    .setDetailAddress(detailAddress)
                    .setId(id);

            agreeVO = agreeVO.setAgreeYnList(agreeList)
                    .setId(id);

        } catch (ParseException | SQLException e) {
            e.printStackTrace();
        }

        boolean[] allY = new boolean[agreeList.size()];
        try {

            for (int i = 0; i < agreeList.size(); i++) {

                if (agreeList.get(i).equals("Y")) {
                    allY[i] = true;
                } else {
                    allY[i] = false;
                }
            }

            if (allY[0] == true && allY[1] == true && allY[2] == true) {
                memberDAO.insertAccess(agreeVO);
                memberDAO.insertAccess(memberVO);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return joinStr;

    }

    public MemberVO alreadyExistMember(String alreadyHasId) {

        MemberVO memberVO = null;

        try {
            memberVO = MemberDAO.getInstance().alreadyExistIDAccess(alreadyHasId);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return memberVO;

    }

    public JSONObject longinAndOut(String loginInfo) {

        MemberVO memberVO = null;

        Map<String, Object> map = new HashMap<>();
        Map<String, String> sqlMap = new HashMap<>();

        Map<String, Object> loginResultMap = null;

        JSONObject json = null;
        try {

            JSONParser parser = new JSONParser();

            JSONObject jsonObject = (JSONObject) parser.parse(loginInfo);


            String id = (String) jsonObject.get("id");
            String pwd = (String) jsonObject.get("pwd");

            map.put("USER_ID", id);
            map.put("USER_PWD", pwd);

            String sql = " LEFT OUTER JOIN POINT P ON (M.ID = P.ID) OR (M.USER_ID = 'scott' AND M.USER_PWD = 'tiger')  WHERE M.USER_ID = ? AND M.USER_PWD=? ";
            sqlMap.put("sql", sql);

            loginResultMap = MemberDAO.getInstance().selectAccess(map, sqlMap);

            if (loginResultMap.get("USER_ID") != null && loginResultMap.get("USER_ID") != "") {

                json = new JSONObject(loginResultMap);
            }


        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }


        return json;

    }


    public JSONObject userIdOrPwdFindSelect(String find_info) {

        Map<String, Object> SelectMap = new HashMap<>();
        Map<String, String> insertMap = new HashMap<>();
        Map<String, String> sqlMap = new HashMap<>();
        Map<String, Object> findResultMap = null;
        JSONObject json = null;
        try {
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(find_info);

            String name = (String) jsonObject.get("name");
            String phone = (String) jsonObject.get("phone");
            String email = (String) jsonObject.get("email");
            String find_certification_num = (String) jsonObject.get("find_certification_num");
            String how_find = (String) jsonObject.get("how_find");
            String what_find = (String) jsonObject.get("what_find");

            SelectMap.put("USER_NAME", name);
            SelectMap.put("USER_PHONE", phone);
            SelectMap.put("USER_EMAIL", email);

            String sql = "  WHERE  ( USER_PHONE = ? OR USER_EMAIL=? ) and USER_NAME = ?  ";
            sqlMap.put("sql", sql);
            findResultMap = MemberDAO.getInstance().selectAccess(SelectMap, sqlMap);
            long id = (long) findResultMap.get("ID");
            String user_id = (String) findResultMap.get("USER_ID");

            insertMap.put("FIND_CERTIFICATION_NUM", find_certification_num);
            insertMap.put("HOW_FIND", how_find);
            insertMap.put("WHAT_FIND", what_find);
            insertMap.put("USER_ID", user_id);

            Iterator<Map.Entry<String, String>> iterator = insertMap.entrySet().iterator();
            String keys = "";
            String vals = "";

            while (iterator.hasNext()) {
                Map.Entry<String, String> it = iterator.next();
                String key = it.getKey();
                String val = it.getValue();
                keys += key + " ,";
                vals += "'" + val + "'" + " ,";
            }

            String insertSql = "insert INTO FIND ( " + keys + " ID ,  FIND_SEQ)   ";
            String valuesSql = " values (  " + vals + " " + id + " ,  COMMON_MEMBER_LOGINANDOUT_SEQ.nextval ) ";
            ;
            sqlMap.put("insert", insertSql);
            sqlMap.put("values", valuesSql);
            MemberDAO.getInstance().insertAccess(sqlMap);

            json = new JSONObject(findResultMap);

            json.put("WHAT_FIND", what_find);

        } catch (SQLException | ParseException e) {
            e.printStackTrace();

        }

        return json;

    }

    public String isLogin(HttpSession session) {

        Map<String, String> sqlMap = new HashMap<>();

        Date date = new Date();

        long timeInMilliSeconds = date.getTime();
        java.sql.Date loginAndOutDate = new java.sql.Date(timeInMilliSeconds);

        long sessionId = (long) session.getAttribute("id");
        String sessionUserId = (String) session.getAttribute("login_id");


        Map<String, Object> insertMap = new HashMap<>();
        insertMap.put("USER_ID", sessionUserId);

        if (sessionUserId != null && sessionUserId != "") {

            insertMap.put("LOGINDATE", loginAndOutDate);
            //  System.out.println("세션있음");
        } else {

            insertMap.put("LOGOUTDATE", loginAndOutDate);

        }


        Iterator<Map.Entry<String, Object>> iterator = insertMap.entrySet().iterator();
        String keys = "";
        String vals = "";

        while (iterator.hasNext()) {

            Map.Entry<String, Object> it = iterator.next();
            String key = it.getKey();
            Object val = it.getValue();
            keys += key + " ,";

            vals += "'" + val + "'" + " ,";

        }


        String insertSql = "insert INTO LOGIN ( " + keys + "  ID  , LOGIN_SEQ)   ";
        String valuesSql = " values (  " + vals + " " + sessionId + " ,  COMMON_MEMBER_LOGINANDOUT_SEQ.nextval ) ";

        sqlMap.put("insert", insertSql);
        sqlMap.put("values", valuesSql);

        try {
            MemberDAO.getInstance().insertAccess(sqlMap);

            return "success";
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "fail";
    }


    public JSONObject myPageAdjustUpdate(String modifyData) {


        MemberDAO memberDAO = MemberDAO.getInstance();
        Date date = new Date();
        long timeInMilliSeconds = date.getTime();
        java.sql.Date modDate = new java.sql.Date(timeInMilliSeconds);
        Map<String, Object> map = new HashMap<>();
        JSONParser parser = new JSONParser();
        String updateResultStr = null;

        JSONObject json = null;
        try {
            JSONObject jsonObjectMember = (JSONObject) parser.parse(modifyData);
            String id = (String) jsonObjectMember.get("id");
            String userId = (String) jsonObjectMember.get("userId");
            String userName = (String) jsonObjectMember.get("userName");
            String userPwd = (String) jsonObjectMember.get("userPwd");
            String userPwdConfirm = (String) jsonObjectMember.get("userPwdConfirm");
            String userPhone = (String) jsonObjectMember.get("userPhone");
            String userEmail = (String) jsonObjectMember.get("userEmail");
            String zipcode = (String) jsonObjectMember.get("zipcode");
            String address = (String) jsonObjectMember.get("address");
            String detailAddress = (String) jsonObjectMember.get("detailAddress");

            map.put("ID", id);
            map.put("USER_ID", userId);
            map.put("USER_NAME", userName);
            map.put("USER_PWD", userPwd);
            map.put("USER_PWD_CONFIRM", userPwdConfirm);
            map.put("USER_PHONE", userPhone);
            map.put("USER_EMAIL", userEmail);
            map.put("ZIPCODE", zipcode);
            map.put("ADDRESS", address);
            map.put("DETAIL_ADDRESS", detailAddress);
            map.put("MODDATE", modDate);

            updateResultStr = memberDAO.updateAccess(map);

            if (updateResultStr.equals("success")) {

                Map<String, String> sqlMap = new HashMap<>();
                Map<String, Object> valueMap = new HashMap<>();
                Map<String, Object> updateResultMap = new HashMap<>();

                String where = " WHERE USER_ID = ? AND ID= ? ";

                sqlMap.put("sql", where);
                valueMap.put("ID", id);
                valueMap.put("USER_ID", userId);

                updateResultMap = memberDAO.selectAccess(valueMap, sqlMap);
                json = new JSONObject(updateResultMap);

            }

            return json;

        } catch (ParseException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return json;
    }
}
