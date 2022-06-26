package com.nanam.domain.bookDB;

import com.nanam.domain.boardDB.BoardVO;
import com.nanam.domain.boardDB.PageVO;
import com.nanam.util.DBManager;

import java.sql.*;
import java.sql.Date;
import java.util.*;

public class BookDAO {

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

        if (map.get("BOOK_LIST").equals("new")) {

            sql = "INSERT INTO BOOK (" + insert_sql + " NEW_BOOK_SEQ , COMMON_BOOK_SEQ )" + " VALUES  (" + values_sql + " NEW_BOOK_SEQ.nextval , COMMON_BOOK_SEQ.nextval  )";

        } else if (map.get("BOOK_LIST").equals("nanam")) {

            sql = "INSERT INTO BOOK (" + insert_sql + " NANAM_BOOK_SEQ , COMMON_BOOK_SEQ )" + " VALUES  (" + values_sql + " NANAM_BOOK_SEQ.nextval ,  COMMON_BOOK_SEQ.nextval  )";


        } else if (map.get("BOOK_LIST").equals("steady")) {

            sql = "INSERT INTO BOOK (" + insert_sql + " STEADY_BOOK_SEQ , COMMON_BOOK_SEQ )" + " VALUES  (" + values_sql + " STEADY_BOOK_SEQ.nextval , COMMON_BOOK_SEQ.nextval )";
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


    public ArrayList<BookVO> selectAccess(BookVO bookVO, Map<String, String> sqlmap, HashMap<String, PageVO> mapForPaging) throws SQLException {

        Connection conn = null;
        Statement stmt = null;
        ResultSet resultSet = null;
        PreparedStatement pstmt = null;
        String listValue = bookVO.getBookList().toString();

        conn = DBManager.getConnection();
        String upperListValue = listValue.toUpperCase(Locale.ROOT);
        String seqGot = upperListValue + "_BOOK_SEQ";
        String whereGot = sqlmap.get("sql");

        int innerInt= mapForPaging.get("pageMaker").getPageNum()*mapForPaging.get("pageMaker").getAmount();
        int outInt = (mapForPaging.get("pageMaker").getPageNum()-1)*mapForPaging.get("pageMaker").getAmount();
        String select =" SELECT B.id resultId , B.userId resultUserId , B.isbn resultIsbn , B.list resultList , B.title resultTitle , B.author resultAuthor, B.authorIntro resultAuthorIntro , B.pubInfo resultPubInfo, B.pubYear resultPubYear, B.price resultPrice, B.bookIndex resultIndex , B.content resultContent , B.insertDate resultInsertDate ,  B.updateDate resultUpdateDate , B.deleteDate resultDeleteDate, B.rn resultRn ,B.goteq resultGotseq , replace( B.img , 'C:\\fakepath\\' , '')  resultImg "+
                " FROM ( SELECT  ROWNUM  rn " +
                " , A.ID  id "
                + " , A.USER_ID  userId "
                + " , A.BOOK_ISBN  isbn "
                + " , A.BOOK_LIST  list  "
                + " , A.BOOK_TITLE_INFO  title "
                + " , A.BOOK_AUTHOR_INFO  author "
                + " , A.BOOK_AUTHOR_INTRODUCE  authorIntro "
                + " , A.BOOK_PUB_INFO  pubInfo "
                + " , A.BOOK_PUB_YEAR_INFO  pubYear "
                + " , A.BOOK_PRICE  price "
                + " , A.BOOK_INDEX  bookIndex "
                + " , A.BOOK_CONTENT  content "
                + " , A.INSERT_DATE  insertDate "
                + " , A.UPDATE_DATE  updateDate "
                + " , A.DELETE_DATE  deleteDate "
                + " , A.BOOK_IMG  img "
                + " , A. "+seqGot+"  goteq "+
                "  FROM ( SELECT " +
                " C.* " + ", ROWNUM "+
                " FROM BOOK C "
                + whereGot
                + " ORDER BY C."+ seqGot  +" DESC ) A "
                + "   WHERE  ROWNUM " +" <= "+innerInt+ ") B "
                + "   WHERE  B.rn " +" > " +outInt+"";

        String sql = select;

        stmt = conn.createStatement();
        resultSet = stmt.executeQuery(sql);

        ArrayList<BookVO> list = new ArrayList<>();
        while (resultSet.next()) {
            BookVO book=new BookVO();
            String insertDate = "";
            String updateDate = "";
            String deleteDate = "";

            long idx = resultSet.getLong("resultId");
            String id = String.valueOf(idx);
            String userId = resultSet.getString("resultUserId");
            String bookIsbn = resultSet.getString("resultIsbn");
            String bookList = resultSet.getString("resultList");
            String bookTitle = resultSet.getString("resultTitle");
            String bookAuthor = resultSet.getString("resultAuthor");
            String bookAuthorIntro = resultSet.getString("resultAuthorIntro");
            String bookPubInfo = resultSet.getString("resultPubInfo");
            String bookPubYear = resultSet.getString("resultPubYear");
            long price = resultSet.getLong("resultPrice");
            String bookPrice=String.valueOf(price);
            String bookIndex = resultSet.getString("resultIndex");
            String bookContent = resultSet.getString("resultContent");
            String bookImg = resultSet.getString("resultImg");
            Date insert = resultSet.getDate("resultInsertDate");
            Date update = resultSet.getDate("resultUpdateDate");
            Date delete = resultSet.getDate("resultDeleteDate");
            String rn = resultSet.getString("resultRn");
            long seqResult = resultSet.getLong("resultGotseq");
            String bookSeq = String.valueOf(seqResult);

            if (insert != null) {
                insertDate = String.valueOf(insert);
            }

            if (update != null) {
                updateDate = String.valueOf(update);
            }

            if (delete != null) {
                deleteDate = String.valueOf(delete);
            }

            book.setAdminId(id);
            book.setUserId(userId);
            book.setBookList(bookList);
            book.setBookContent(bookContent);
            book.setBookIndex(bookIndex);
            book.setBookAuthorInfo(bookAuthor);
            book.setBookAuthorIntro(bookAuthorIntro);
            book.setBookPubInfo(bookPubInfo);
            book.setBookTitleInfo(bookTitle);
            book.setBookPrice(bookPrice);
            book.setBookIsbn(bookIsbn);
            book.setBookPubYearInfo(bookPubYear);
            book.setBookImg(bookImg);
            book.setInsertDate(insertDate);
            book.setUpdateDate(updateDate);
            book.setDeleteDate(deleteDate);
            book.setBookSeq(bookSeq);
            book.setRn(rn);
            list.add(book);

        }

        return list;

    }

    public int getTotal(String boardListForPaging) throws SQLException {

        Connection conn = null;
        Statement stmt = null;
        ResultSet resultSet = null;
        PreparedStatement pstmt = null;

        conn = DBManager.getConnection();
        String where = "  WHERE BOOK_LIST = '"+boardListForPaging+"' ";

        String select = "  SELECT COUNT(*) as total FROM BOOK  ";

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

    public BookVO selectAccess(HashMap<String,String> map, HashMap<String, String> sqlMap) throws SQLException {

        Connection conn = null;
        Statement stmt = null;
        ResultSet resultSet = null;
        conn = DBManager.getConnection();
        String select = "SELECT"+" "+map.get("BOOK_SEQ")+ " ,  ID , USER_ID , BOOK_ISBN , BOOK_LIST , BOOK_TITLE_INFO , BOOK_AUTHOR_INFO , BOOK_AUTHOR_INTRODUCE , BOOK_PUB_INFO ,  BOOK_PUB_YEAR_INFO  , BOOK_PRICE , BOOK_INDEX , BOOK_CONTENT , INSERT_DATE , UPDATE_DATE , DELETE_DATE , replace( BOOK_IMG , 'C:\\fakepath\\' , '') BOOK_IMG FROM BOOK  ";


        String where = sqlMap.get("sql");
        String sql = select+where;

        stmt= conn.createStatement();
        resultSet = stmt.executeQuery(sql);

        BookVO bookVO=new BookVO();
        while (resultSet.next()) {

            String insertDate = "";
            String updateDate = "";
            String deleteDate = "";

            long idx = resultSet.getLong("ID");
            String id = String.valueOf(idx);
            String userId = resultSet.getString("USER_ID");
            String bookIsbn = resultSet.getString("BOOK_ISBN");
            String bookList = resultSet.getString("BOOK_LIST");
            String bookTitle = resultSet.getString("BOOK_TITLE_INFO");
            String bookAuthor = resultSet.getString("BOOK_AUTHOR_INFO");
            String bookAuthorIntro = resultSet.getString("BOOK_AUTHOR_INTRODUCE");
            String bookPubInfo = resultSet.getString("BOOK_PUB_INFO");
            String bookPubYear = resultSet.getString("BOOK_PUB_YEAR_INFO");
            long price = resultSet.getLong("BOOK_PRICE");
            String bookPrice=String.valueOf(price);
            String bookIndex = resultSet.getString("BOOK_INDEX");
            String bookContent = resultSet.getString("BOOK_CONTENT");
            String bookImg = resultSet.getString("BOOK_IMG");

            Date insert = resultSet.getDate("INSERT_DATE");
            Date update = resultSet.getDate("UPDATE_DATE");
            Date delete = resultSet.getDate("DELETE_DATE");
            long seqResult = resultSet.getLong(map.get("BOOK_SEQ"));
            String bookSeq = String.valueOf(seqResult);
            if (insert != null) {
                insertDate = String.valueOf(insert);
            }

            if (update != null) {
                updateDate = String.valueOf(update);
            }

            if (delete != null) {
                deleteDate = String.valueOf(delete);
            }

            bookVO.setAdminId(id);
            bookVO.setUserId(userId);
            bookVO.setBookList(bookList);
            bookVO.setBookContent(bookContent);
            bookVO.setBookIndex(bookIndex);
            bookVO.setBookAuthorInfo(bookAuthor);
            bookVO.setBookAuthorIntro(bookAuthorIntro);
            bookVO.setBookPubInfo(bookPubInfo);
            bookVO.setBookTitleInfo(bookTitle);
            bookVO.setBookPrice(bookPrice);
            bookVO.setBookIsbn(bookIsbn);
            bookVO.setBookPubYearInfo(bookPubYear);
            bookVO.setBookImg(bookImg);


            bookVO.setInsertDate(insertDate);
            bookVO.setUpdateDate(updateDate);
            bookVO.setDeleteDate(deleteDate);
            bookVO.setBookSeq(bookSeq);

        }

        //System.out.println("selectEach:::::::"+bookVO);

        return bookVO;

    }

    public void updateAccess(HashMap<String,String> map, HashMap<String, String> sqlMap) throws SQLException {

        Connection conn = null;
        Statement stmt = null;
        ResultSet resultSet = null;
        conn = DBManager.getConnection();

        long price =Long.valueOf(map.get("BOOK_PRICE"));

        //System.out.println("price:::::::::::::::::::"+price);

        String update = "UPDATE BOOK SET BOOK_PRICE =" +price+ ", BOOK_CONTENT ="+ "'" +map.get("BOOK_CONTENT")+"'"+  " , BOOK_AUTHOR_INTRODUCE =" +"'"+map.get("BOOK_AUTHOR_INTRODUCE")+"'"+ "  , BOOK_INDEX ="+"'"+ map.get("BOOK_INDEX")+"'" + "  , UPDATE_DATE ="+  "'"+ map.get("UPDATE_DATE")+"'";


        String where = sqlMap.get("sql");
        String sql = update+where;


        //System.out.println(sql);

        stmt= conn.createStatement();
        stmt.executeUpdate(sql);


    }


    public void deleteAccess(HashMap<String,String> map, HashMap<String, String> sqlMap) throws SQLException {

        Connection conn = null;
        Statement stmt = null;
        ResultSet resultSet = null;
        conn = DBManager.getConnection();
        String delete = "DELETE FROM BOOK  ";
        String where = sqlMap.get("sql");
        String sql = delete+where;

        stmt= conn.createStatement();
        stmt.executeUpdate(sql);


    }
}
