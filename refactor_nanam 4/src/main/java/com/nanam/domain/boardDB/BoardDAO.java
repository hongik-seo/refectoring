package com.nanam.domain.boardDB;

import com.nanam.util.DBManager;

import java.sql.*;
import java.sql.Date;
import java.util.*;


public class BoardDAO {

    public Map<String, Object> insertAccess(Map<String, Object> map) throws SQLException {

        Connection conn = null;
        Statement stmt = null;
        ResultSet resultSet = null;
        PreparedStatement pstmt = null;
        Iterator<Map.Entry<String, Object>> iterator = map.entrySet().iterator();
        String insert_sql = "";
        String values_sql = "";

        while (iterator.hasNext()) {

            Map.Entry<String, Object> it = iterator.next();

            String keys = it.getKey();

            Object values = it.getValue();

            if (values.getClass().getName().equals("java.lang.String") || values.getClass().getName().equals("java.sql.Date")) {

                values = "'" + values + "'";

            } else if (values.getClass().getName().equals("java.lang.Long")) {

                values = values;
            }

            insert_sql += keys + " ,  ";

            values_sql += values + " , ";

        }

        String sql = "";

        if (map.get("BOARD_LIST").equals("essay")) {

            sql = "INSERT INTO BOARD (" + insert_sql + " ESSAY_BOARD_SEQ , COMMON_BOARD_SEQ )" + " VALUES  (" + values_sql + "ESSAY_BOARD_SEQ.nextval , COMMON_BOARD_SEQ.nextval  )";

        } else if (map.get("BOARD_LIST").equals("nanam")) {

            sql = "INSERT INTO BOARD (" + insert_sql + " NANAM_BOARD_SEQ , COMMON_BOARD_SEQ )" + " VALUES  (" + values_sql + " NANAM_BOARD_SEQ.nextval ,  COMMON_BOARD_SEQ.nextval  )";


        } else if (map.get("BOARD_LIST").equals("notice")) {

            sql = "INSERT INTO BOARD (" + insert_sql + " NOTICE_BOARD_SEQ , COMMON_BOARD_SEQ )" + " VALUES  (" + values_sql + " NOTICE_BOARD_SEQ.nextval , COMMON_BOARD_SEQ.nextval )";
        }


        if (!"".equals(sql)) {

            conn = DBManager.getConnection();
            stmt = conn.createStatement();
            stmt.executeQuery(sql);

            map.put("result", "success");
            return map;

        }
        map.put("result", "fail");
        return map;

    }


    public ArrayList<BoardVO> selectAccess(BoardVO boardVO, Map<String, String> sqlmap, HashMap<String,PageVO> mapForPaging) throws SQLException {

        Connection conn = null;
        Statement stmt = null;
        ResultSet resultSet = null;
        PreparedStatement pstmt = null;
        String listValue = boardVO.getBoardList().toString();

        conn = DBManager.getConnection();
        String upperListValue = listValue.toUpperCase(Locale.ROOT);
        String seqGot = upperListValue + "_BOARD_SEQ";
        String whereGot = sqlmap.get("sql");

        int innerInt= mapForPaging.get("pageMaker").getPageNum()*mapForPaging.get("pageMaker").getAmount();
        int outInt = (mapForPaging.get("pageMaker").getPageNum()-1)*mapForPaging.get("pageMaker").getAmount();
        String select =" SELECT B.id resultId , B.userId resultUserId , B.subject resultSubject , B.list resultList , B.content resultContnet , B.insertDate resultInsertDate ,  B.updateDate resultUpdateDate , B.deleteDate resultDeleteDate, B.rn resultRn ,B.goteq resultGotseq"+
                        " FROM ( SELECT  ROWNUM  rn " +
                                    " , A.ID  id "
                                  + " , A.USER_ID  userId "
                                  + " , A.BOARD_SUBJECT  subject "
                                  + " , A.BOARD_CONTENT  content  "
                                  + " , A.BOARD_LIST  list "
                                  + " , A.INSERT_DATE  insertDate "
                                  + " , A.UPDATE_DATE  updateDate "
                                  + " , A.DELETE_DATE  deleteDate "
                                  + " , A. "+seqGot+"  goteq "+
                               "  FROM ( SELECT " +
                                             " C.* " + ", ROWNUM "+
                                         " FROM BOARD C "
                                        + whereGot
                                        + " ORDER BY C."+ seqGot  +" DESC ) A "
                              + "   WHERE  ROWNUM " +" <= "+innerInt+ ") B "
                        + "   WHERE  B.rn " +" > " +outInt+"";

        String sql = select;

        stmt = conn.createStatement();
        resultSet = stmt.executeQuery(sql);

        ArrayList<BoardVO> list = new ArrayList<>();
        while (resultSet.next()) {
            BoardVO board=new BoardVO();
            String insertDate = "";
            String updateDate = "";
            String deleteDate = "";

            long idx = resultSet.getLong("resultId");
            String id = String.valueOf(idx);
            String userId = resultSet.getString("resultUserId");
            String boardSubject = resultSet.getString("resultSubject");
            String boardContent = resultSet.getString("resultContnet");
            String boardList = resultSet.getString("resultList");
            Date insert = resultSet.getDate("resultInsertDate");
            Date update = resultSet.getDate("resultUpdateDate");
            Date delete = resultSet.getDate("resultDeleteDate");
            String rn = resultSet.getString("resultRn");
            long seqResult = resultSet.getLong("resultGotseq");
            String boardSeq = String.valueOf(seqResult);

            if (insert != null) {
                insertDate = String.valueOf(insert);
            }

            if (update != null) {
                updateDate = String.valueOf(update);
            }

            if (delete != null) {
                deleteDate = String.valueOf(delete);
            }

            board.setId(id);
            board.setUserId(userId);
            board.setBoardSubject(boardSubject);
            board.setBoardContent(boardContent);
            board.setBoardList(boardList);
            board.setInsertDate(insertDate);
            board.setUpdateDate(updateDate);
            board.setDeleteDate(deleteDate);
            board.setBoardSeq(boardSeq);
            board.setRn(rn);
            list.add(board);

        }

        return list;

    }

    public int getTotal(String boardListForPaging) throws SQLException {

        Connection conn = null;
        Statement stmt = null;
        ResultSet resultSet = null;
        PreparedStatement pstmt = null;

        conn = DBManager.getConnection();
        String where = "  WHERE BOARD_LIST = '"+boardListForPaging+"' ";

        String select = "  SELECT COUNT(*) as total FROM BOARD  ";

        String sql = select+where;

        stmt = conn.createStatement();
        resultSet = stmt.executeQuery(sql);

        //System.out.println(sql);

        int total=0;
        while (resultSet.next()) {

            total = resultSet.getInt("total");
        }
        //System.out.println(total);
        return total;
    }

    public BoardVO selectAccess(HashMap<String,String> map, HashMap<String, String> sqlMap) throws SQLException {

        Connection conn = null;
        Statement stmt = null;
        ResultSet resultSet = null;
        conn = DBManager.getConnection();
        String select = "SELECT"+" "+map.get("BOARD_SEQ")+ " ,  ID , USER_ID , BOARD_SUBJECT , BOARD_CONTENT , BOARD_LIST , INSERT_DATE , UPDATE_DATE , DELETE_DATE FROM BOARD  ";
        String where = sqlMap.get("sql");
        String sql = select+where;

        stmt= conn.createStatement();
        resultSet = stmt.executeQuery(sql);

        BoardVO board=new BoardVO();
        while (resultSet.next()) {

            String insertDate = "";
            String updateDate = "";
            String deleteDate = "";

            long idx = resultSet.getLong("ID");
            String id = String.valueOf(idx);
            String userId = resultSet.getString("USER_ID");
            String boardSubject = resultSet.getString("BOARD_SUBJECT");
            String boardContent = resultSet.getString("BOARD_CONTENT");
            String boardList = resultSet.getString("BOARD_LIST");
            Date insert = resultSet.getDate("INSERT_DATE");
            Date update = resultSet.getDate("UPDATE_DATE");
            Date delete = resultSet.getDate("DELETE_DATE");


            if (insert != null) {
                insertDate = String.valueOf(insert);
            }

            if (update != null) {
                updateDate = String.valueOf(update);
            }

            if (delete != null) {
                deleteDate = String.valueOf(delete);
            }

            board.setId(id);
            board.setUserId(userId);
            board.setBoardSubject(boardSubject);
            board.setBoardContent(boardContent);
            board.setBoardList(boardList);
            board.setInsertDate(insertDate);
            board.setUpdateDate(updateDate);
            board.setDeleteDate(deleteDate);
            board.setBoardSeq(String.valueOf(resultSet.getLong(map.get("BOARD_SEQ"))));

        }


        return board;

    }

    public void updateAccess(HashMap<String,String> map, HashMap<String, String> sqlMap) throws SQLException {

        Connection conn = null;
        Statement stmt = null;
        ResultSet resultSet = null;
        conn = DBManager.getConnection();
        String select = "UPDATE BOARD SET  BOARD_SUBJECT =" +"'"+map.get("BOARD_SUBJECT")+"'"+ "  , BOARD_CONTENT ="+"'"+ map.get("BOARD_CONTENT")+"'" + "  , UPDATE_DATE ="+  "'"+ map.get("UPDATE_DATE")+"'";
        String where = sqlMap.get("sql");
        String sql = select+where;



        stmt= conn.createStatement();
        stmt.executeUpdate(sql);


    }


    public void deleteAccess(HashMap<String,String> map, HashMap<String, String> sqlMap) throws SQLException {

        Connection conn = null;
        Statement stmt = null;
        ResultSet resultSet = null;
        conn = DBManager.getConnection();
        String select = "DELETE FROM BOARD  ";
        String where = sqlMap.get("sql");
        String sql = select+where;

        stmt= conn.createStatement();
        stmt.executeUpdate(sql);


    }

}
