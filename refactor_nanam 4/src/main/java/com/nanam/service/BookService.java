package com.nanam.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nanam.domain.boardDB.BoardDAO;
import com.nanam.domain.boardDB.BoardVO;
import com.nanam.domain.boardDB.PageVO;
import com.nanam.domain.bookDB.BookDAO;
import com.nanam.domain.bookDB.BookVO;
import com.nanam.domain.bookDB.xmlParsing.Item;

import com.nanam.domain.bookDB.xmlParsing.Root;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.*;

public class BookService {


    public Map<String, Object> writeBook(String writeInfo){

        BookDAO bookDAO = new BookDAO();

        java.sql.Date insertDate = dateSetting();
        Map<String, Object> map = new HashMap<>();
        JSONParser parser = new JSONParser();
        JSONObject jsonObjectBook = null;
        Map<String , Object> resultMap=new HashMap<>();
        try {
            jsonObjectBook = (JSONObject) parser.parse(writeInfo);
            String id = (String) jsonObjectBook.get("id");
            String login_id = (String) jsonObjectBook.get("login_id");
            String list = (String) jsonObjectBook.get("list");
            String tittles = (String) jsonObjectBook.get("tittles");
            String years = (String) jsonObjectBook.get("years");
            String authors = (String) jsonObjectBook.get("authors");
            String isbns = (String) jsonObjectBook.get("isbns");
            String pubInfos = (String) jsonObjectBook.get("pubInfos");
            String bookPrice = (String) jsonObjectBook.get("bookPrice");
            String bookIndex = (String) jsonObjectBook.get("bookIndex");
            String bookContent = (String) jsonObjectBook.get("bookContent");
            String bookAuthorIntroduce = (String) jsonObjectBook.get("bookAuthorIntroduce");
            String file = (String) jsonObjectBook.get("file");



            map.put("ID", Long.valueOf(id));
            map.put("USER_ID", String.valueOf(login_id));
            map.put("BOOK_LIST", String.valueOf(list));
            map.put("BOOK_TITLE_INFO", String.valueOf(tittles));
            map.put("BOOK_AUTHOR_INFO", String.valueOf(authors));
            map.put("BOOK_PUB_YEAR_INFO", String.valueOf(years));
            map.put("BOOK_ISBN", String.valueOf(isbns));
            map.put("BOOK_PUB_INFO", String.valueOf(pubInfos));
            map.put("BOOK_PRICE", String.valueOf(bookPrice));
            map.put("BOOK_INDEX", String.valueOf(bookIndex));
            map.put("BOOK_AUTHOR_INTRODUCE", String.valueOf(bookAuthorIntroduce));
            map.put("BOOK_CONTENT", String.valueOf(bookContent));
            map.put("BOOK_IMG", String.valueOf(file));
            map.put("INSERT_DATE", insertDate.toString());

            //System.out.println("bookInsert:::::::::::::::"+map);

            resultMap=bookDAO.insertAccess(map);


            return resultMap;

        } catch (ParseException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return resultMap;

    }


    public JSONObject searchBook(String searchBook ){

        String key ="";

        try {
            JSONParser jsonParser = new JSONParser();

            JSONObject jObj =(JSONObject) jsonParser.parse(searchBook);

            String keyword=(String) jObj.get("kwd");
            String pageNu =(String) jObj.get("pageNum");
            int pageNum = Integer.parseInt(pageNu);

           // System.out.println("pageNum"+pageNum);
            String category = "도서";

            String url ="https://www.nl.go.kr/NL/search/openApi/search.do?key="+key+"&apiType=xml&srchTarget=total"+"&kwd="+ URLEncoder.encode(keyword,"UTF-8")+"&pageSize=10&pageNum="+pageNum+"&sort=&category="+URLEncoder.encode(category,"UTF-8");

            URL u = new URL(url);

            HttpURLConnection http = (HttpURLConnection) u.openConnection();
            http.setRequestMethod("GET");

            http.setConnectTimeout(10000);       //컨텍션타임아웃 10초
            http.setReadTimeout(5000);           //컨텐츠조회 타임아웃 5총
            int responseCode = http.getResponseCode();


       //     System.out.println("Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(new InputStreamReader(http.getInputStream()));
            String inputLine;
            StringBuffer httpResponse = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                httpResponse.append(inputLine);
            }
            in.close();

            StringReader publishReader= new StringReader(new String(httpResponse.toString()));

            JSONObject jList = forJson(publishReader);

            return jList;
        }catch (Exception e){

            e.printStackTrace();
        }

        return null;

    }


    public int getTotalNum(String boardListForPaging){

        int total =0;

        BookDAO bookDAO = new BookDAO();

        try {
            total=bookDAO.getTotal(boardListForPaging);

            return total;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return total;
    }


    public JSONObject forJson(StringReader publishReader) throws JAXBException {

        JAXBContext jaxbContext = JAXBContext.newInstance(Root.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        Root publishRoot = (Root) unmarshaller.unmarshal(publishReader);

        List<Item> bookList =  publishRoot.getResult().getItemList();

        JSONObject obj = new JSONObject();
        JSONArray jArray = new JSONArray();
        for(int i=0; i<bookList.size(); i++){
            JSONObject sObject = new JSONObject();

            sObject.put("BOOK_AUTHOR_INFO", bookList.get(i).getAuthor_info());
            sObject.put("BOOK_ISBN", bookList.get(i).getIsbn());
            sObject.put("BOOK_PUB_INFO", bookList.get(i).getPub_info());
            sObject.put("BOOK_PUB_YEAR_INFO", bookList.get(i).getPub_year_info());
            sObject.put("BOOK_PUB_TITLE_INFO", bookList.get(i).getTitle_info());

            jArray.add(sObject);

        }

        obj.put("parsing", jArray);

        return obj;
    }

    public ArrayList<BookVO> showBookList(BookVO bookVO, HashMap<String, PageVO> pageMap){


        BookDAO bookDAO = new BookDAO();
        java.sql.Date insertDate = dateSetting();
        Map<String, String> sqlMap = new HashMap<>();
        ArrayList<BookVO> returnList = null;

        try {

            if(bookVO.getBookList().equals("new")){

                System.out.println("서비스단 map :::::::"+pageMap);

                String where = " WHERE C.BOOK_LIST = 'new' ";
                sqlMap.put("sql", where);
                returnList=bookDAO.selectAccess(bookVO, sqlMap, pageMap);
                return returnList;
            }else if( bookVO.getBookList().equals("steady")) {

                String where = " WHERE C.BOOK_LIST = 'steady' ";
                sqlMap.put("sql", where);
                returnList= bookDAO.selectAccess(bookVO, sqlMap, pageMap);
                return returnList;

            }else if(bookVO.getBookList().equals("nanam")) {

                String where = "  WHERE C.BOOK_LIST = 'nanam' ";
                sqlMap.put("sql", where);
                returnList = bookDAO.selectAccess(bookVO, sqlMap, pageMap);

                return  returnList;
            }


        }catch (Exception e){

            e.printStackTrace();
        }

        return null;
    }



    public BookVO BookCRUD(HashMap<String,String> map){

        BookDAO bookDAO = new BookDAO();
        HashMap<String,String> sqlMap = new HashMap<>();
        String toUpperSeq = map.get("BOOK_LIST").toString()+"_BOOK_SEQ";
        String bookSeq=toUpperSeq.toUpperCase(Locale.ROOT);

        String where = " WHERE  BOOK_LIST =" + "'"+map.get("BOOK_LIST").toString() +"'"+
                " AND  "+bookSeq+"="+Long.valueOf(map.get("SEQ").toString())+" ";

        sqlMap.put("sql", where);


        BookVO detailList = null;
        try {

            if(map.get("controlFlag").equals("SELECT")) {
                detailList = bookDAO.selectAccess(map, sqlMap);

            }else if(map.get("controlFlag").equals("UPDATE")){

                java.sql.Date updateDate = dateSetting();
                map.put("UPDATE_DATE", String.valueOf(updateDate));
                bookDAO.updateAccess(map, sqlMap);

            }else if(map.get("controlFlag").equals("DELETE")){

                java.sql.Date deleteDate = dateSetting();
                map.put("DELETE_DATE", String.valueOf(deleteDate));
                bookDAO.deleteAccess(map, sqlMap);

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
