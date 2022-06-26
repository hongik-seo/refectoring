package com.nanam.service;

import com.nanam.domain.boardDB.BoardDAO;
import com.nanam.domain.boardDB.BoardVO;
import com.nanam.domain.boardDB.PageVO;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.sql.SQLException;
import java.util.*;

public class BoardService {

    public Map<String, Object> writeBoard(String writeInfo){

        BoardDAO boardDAO = new BoardDAO();
        Date date = new Date();
        long timeInMilliSeconds = date.getTime();
        java.sql.Date insertDate = new java.sql.Date(timeInMilliSeconds);
        Map<String, Object> map = new HashMap<>();
        JSONParser parser = new JSONParser();
        JSONObject jsonObjectBoard = null;
        Map<String , Object> resultMap=new HashMap<>();
        try {
            jsonObjectBoard = (JSONObject) parser.parse(writeInfo);
            String id = (String) jsonObjectBoard.get("id");
            String login_id = (String) jsonObjectBoard.get("login_id");
            String subject = (String) jsonObjectBoard.get("subject");
            String content = (String) jsonObjectBoard.get("content");
            String list = (String) jsonObjectBoard.get("list");

            map.put("ID", Long.valueOf(id));
            map.put("USER_ID", String.valueOf(login_id));
            map.put("BOARD_SUBJECT", String.valueOf(subject));
            map.put("BOARD_CONTENT", String.valueOf(content));
            map.put("BOARD_LIST", String.valueOf(list));
            map.put("INSERT_DATE", insertDate.toString());

            resultMap=boardDAO.insertAccess(map);


            return resultMap;

        } catch (ParseException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return resultMap;

    }

    public ArrayList<BoardVO> showBoardList(BoardVO boardVO, HashMap<String, PageVO> pageMap){


        BoardDAO boardDAO = new BoardDAO();
        java.sql.Date insertDate = dateSetting();
        Map<String, String> sqlMap = new HashMap<>();
        ArrayList<BoardVO> returnList = null;

        try {


            if(boardVO.getBoardList().equals("essay")){

              //  System.out.println("서비스단 map :::::::"+pageMap);

                String where = " WHERE C.BOARD_LIST = 'essay' ";
                sqlMap.put("sql", where);
                returnList=boardDAO.selectAccess(boardVO, sqlMap, pageMap);
                return returnList;
            }else if( boardVO.getBoardList().equals("nanam")) {

                String where = " WHERE C.BOARD_LIST = 'nanam' ";
                sqlMap.put("sql", where);
                returnList= boardDAO.selectAccess(boardVO, sqlMap, pageMap);
                return returnList;

            }else if(boardVO.getBoardList().equals("notice")) {

                String where = "  WHERE C.BOARD_LIST = 'notice' ";
                sqlMap.put("sql", where);
                returnList = boardDAO.selectAccess(boardVO, sqlMap, pageMap);

                return  returnList;
            }


        }catch (Exception e){

            e.printStackTrace();
        }

        return null;
    }


    public int getTotalNum(String boardListForPaging){

        int total =0;

        BoardDAO boardDAO = new BoardDAO();

        try {
            total=boardDAO.getTotal(boardListForPaging);

            return total;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return total;
    }

    public BoardVO BoardCRUD(HashMap<String,String> map){

        BoardDAO boardDAO = new BoardDAO();
        HashMap<String,String> sqlMap = new HashMap<>();

        String toUpperSeq = map.get("BOARD_LIST").toString()+"_BOARD_SEQ";
        String boardSeq=toUpperSeq.toUpperCase(Locale.ROOT);


        String where = " WHERE  BOARD_LIST =" + "'"+map.get("BOARD_LIST").toString() +"'"+
                       " AND  "+boardSeq+"="+Long.valueOf(map.get("SEQ").toString())+" ";

        sqlMap.put("sql", where);


         BoardVO detailList = null;
        try {

            if(map.get("controlFlag").equals("SELECT")) {
                detailList = boardDAO.selectAccess(map, sqlMap);

            }else if(map.get("controlFlag").equals("UPDATE")){

                java.sql.Date updateDate = dateSetting();
                map.put("UPDATE_DATE", String.valueOf(updateDate));
                boardDAO.updateAccess(map, sqlMap);

            }else if(map.get("controlFlag").equals("DELETE")){

                java.sql.Date deleteDate = dateSetting();
                map.put("DELETE_DATE", String.valueOf(deleteDate));
                boardDAO.deleteAccess(map, sqlMap);

            }
            return detailList;
        } catch (SQLException e) {

            e.printStackTrace();

        }
        return detailList;
    }

    public java.sql.Date dateSetting(){

        Date date = new Date();
        long timeInMilliSeconds = date.getTime();
        java.sql.Date resultDate = new java.sql.Date(timeInMilliSeconds);

        return resultDate;
    }


}
