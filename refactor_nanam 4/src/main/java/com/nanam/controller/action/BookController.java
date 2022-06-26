package com.nanam.controller.action;

import com.nanam.domain.boardDB.BoardVO;
import com.nanam.domain.boardDB.PageVO;
import com.nanam.domain.bookDB.BookVO;
import com.nanam.service.BookService;
import com.nanam.service.PurchaseService;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


@WebServlet(
        urlPatterns = {
                "/book/uploadBookPage", "/book/uploadBook","/book/bookList/steady","/book/bookList/new","/book/bookList/nanam"
                ,"/book/writeBook", "/book/bookControl" ,"/book/showBookDetail" , "/book/bookControlConceal"
        }
)
public class BookController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    String result;


    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        String path = req.getRequestURI();

        BookService bookService = new BookService();
        HashMap<String,PageVO> pageMap = new HashMap<>();

        if (path.equals("/book/uploadBookPage")) {

            result = "/book/uploadBook.jsp";

        }else if(path.equals("/book/bookList/new")){

            BookVO bookVO = new BookVO();
            String list = "new";
            String pageNumStr = req.getParameter("pageNum");
            int pageNum = 1;

            bookService = new BookService();
            String boardListForPaging = list;

            bookVO.setBookList(list);

            if (pageNumStr == null) {

                pageNum = 1;
            } else {

                pageNum = Integer.parseInt(pageNumStr);
            }

            int amount = 10;
            int total = bookService.getTotalNum(boardListForPaging);
            PageVO pageVO = new PageVO(pageNum, amount, total);
            pageMap.put("pageMaker", pageVO);

            ArrayList<BookVO> bookList = bookService.showBookList(bookVO, pageMap);

            HashMap<String, ArrayList<BookVO>> contentMap = new HashMap<>();
            contentMap.put("content", bookList);

            req.setAttribute("contentList", contentMap);
            req.setAttribute("forList", pageMap);

            result = "/book/newBookList.jsp";

        }else if(path.equals("/book/bookList/steady")) {

            BookVO bookVO = new BookVO();
            String list = "steady";
            String pageNumStr = req.getParameter("pageNum");
            int pageNum = 1;

            bookService = new BookService();
            String boardListForPaging = list;

            bookVO.setBookList(list);

            if (pageNumStr == null) {

                pageNum = 1;
            } else {

                pageNum = Integer.parseInt(pageNumStr);
            }

            int amount = 10;
            int total = bookService.getTotalNum(boardListForPaging);
            PageVO pageVO = new PageVO(pageNum, amount, total);
            pageMap.put("pageMaker", pageVO);

            ArrayList<BookVO> bookList = bookService.showBookList(bookVO, pageMap);

            HashMap<String, ArrayList<BookVO>> contentMap = new HashMap<>();
            contentMap.put("content", bookList);

            req.setAttribute("contentList", contentMap);
            req.setAttribute("forList", pageMap);

            result = "/book/steadyBookList.jsp";

        }else if(path.equals("/book/bookList/nanam")) {

            BookVO bookVO = new BookVO();
            String list = "nanam";
            String pageNumStr = req.getParameter("pageNum");
            int pageNum = 1;

            bookService = new BookService();
            String boardListForPaging = list;

            bookVO.setBookList(list);

            if (pageNumStr == null) {

                pageNum = 1;
            } else {

                pageNum = Integer.parseInt(pageNumStr);
            }

            int amount = 10;
            int total = bookService.getTotalNum(boardListForPaging);
            PageVO pageVO = new PageVO(pageNum, amount, total);
            pageMap.put("pageMaker", pageVO);

            ArrayList<BookVO> bookList = bookService.showBookList(bookVO, pageMap);

            HashMap<String, ArrayList<BookVO>> contentMap = new HashMap<>();
            contentMap.put("content", bookList);

            req.setAttribute("contentList", contentMap);
            req.setAttribute("forList", pageMap);
            result = "/book/nanamBookList.jsp";

        }else if (path.equals("/book/writeBook")) {
            String writeInfo = req.getParameter("writeInfo");

            Map<String, Object> writeBoardResultMap = bookService.writeBook(writeInfo);

            JSONObject jsonObject = new JSONObject(writeBoardResultMap);
            PrintWriter out = resp.getWriter();
            out.print(jsonObject);
            out.flush();
            out.close();

        } else if(path.equals("/book/showBookDetail")){

            result = "/book/showBookDetail.jsp";

        }else if(path.equals("/book/bookControl")) {

            //여기 수정
            HashMap<String, String> map = new HashMap<>();
            String controlFlag = req.getParameter("flag");
            String bookList = req.getParameter("list");
            String seq = req.getParameter("seq");

            //book추가
            String title = req.getParameter("title");
            String author = req.getParameter("author");
            String price = req.getParameter("price");
            String year = req.getParameter("year");
            String isbn = req.getParameter("isbn");
            String index = req.getParameter("index");
            String authorIntro = req.getParameter("authorIntro");


            if (controlFlag.equals("UPDATE")) {

                String content = req.getParameter("content");
                map.put("BOOK_CONTENT", content);
            }

            String forSeqFlag = bookList.toUpperCase(Locale.ROOT);
            String bookSeq = forSeqFlag + "_BOOK_SEQ"; // ESSAY_BOARD_SEQ;

            map.put("BOOK_AUTHOR_INFO", author);
            map.put("BOOK_PRICE", price);
            map.put("BOOK_PUB_YEAR_INFO", year);
            map.put("BOOK_ISBN", isbn);
            map.put("BOOK_INDEX", index);
            map.put("BOOK_LIST", bookList);
            map.put("BOOK_TITLE_INFO", title);
            map.put("BOOK_AUTHOR_INTRODUCE", authorIntro);
            map.put("BOOK_SEQ", bookSeq);
            map.put("SEQ", seq);
            map.put("controlFlag", controlFlag);

            BookVO detailList = bookService.BookCRUD(map);

           // System.out.println("detailList 확인용 ........" + detailList);


            req.setAttribute("detailBook", detailList);


            result = "/book/showBookDetail.jsp";


        }
        req.getRequestDispatcher(result).forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        String path = req.getRequestURI();
        BookService bookService = new BookService();
        if (path.equals("/book/uploadBook")) {
            String searchBook = req.getParameter("searchBook");
            bookService = new BookService();
            JSONObject jList = bookService.searchBook(searchBook);
            PrintWriter out = resp.getWriter();
            out.print(jList);
            out.flush();
            out.close();

        }else if(path.equals("/book/bookControlConceal")) {

            //여기 수정
            HashMap<String, String> map = new HashMap<>();
            String controlFlag = req.getParameter("flag");
            String bookList = req.getParameter("list");
            String seq = req.getParameter("seq");
            String index = req.getParameter("index");
            String authorIntro = req.getParameter("authorIntro");
            String price= req.getParameter("price");


            if (controlFlag.equals("UPDATE")) {

                String content = req.getParameter("content");
                map.put("BOOK_CONTENT", content);
            }

            String forSeqFlag = bookList.toUpperCase(Locale.ROOT);
            String bookSeq = forSeqFlag + "_BOOK_SEQ"; // ESSAY_BOARD_SEQ;


            map.put("BOOK_PRICE", price);
            map.put("BOOK_INDEX", index);
            map.put("BOOK_LIST", bookList);
            map.put("BOOK_AUTHOR_INTRODUCE", authorIntro);
            map.put("BOOK_SEQ", bookSeq);
            map.put("SEQ", seq);
            map.put("controlFlag", controlFlag);



            if (controlFlag.equals("UPDATE") || controlFlag.equals("DELETE")) {
                bookService.BookCRUD(map);
                PurchaseService purchaseService = new PurchaseService();

                purchaseService.updateCartPrice(map);


                result = "/home.jsp";

            }

        }
        req.getRequestDispatcher(result).forward(req, resp);
    }

}
