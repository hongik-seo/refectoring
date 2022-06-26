package com.nanam.controller.action;


import com.nanam.domain.boardDB.BoardVO;
import com.nanam.domain.boardDB.PageVO;
import com.nanam.service.BoardService;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@WebServlet(
        urlPatterns ={"/board/officialBoardList/essay" , "/board/officialBoardList/nanam" , "/board/officialBoardList/notice", "/board/officialBoardDetail"  , "/board/freeFront" ,"/board/uploadBoardFront" , "/board/writeOfficialBoard",
                     "/board/boardControl",
        })
public class BoardController extends HttpServlet {

    String result;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        String path = req.getRequestURI();

        HashMap<String,PageVO> pageMap = new HashMap<>();
        BoardService boardService = new BoardService();
        if (path.equals("/board/officialBoardList/essay")) {

            BoardVO boardVO = new BoardVO();
            String list = "essay";
            String pageNumStr = req.getParameter("pageNum");
            int pageNum = 1;

            boardService = new BoardService();
            String boardListForPaging = list;

            boardVO.setBoardList(list);

            if (pageNumStr == null) {

                pageNum = 1;
            } else {

                pageNum = Integer.parseInt(pageNumStr);
            }

            int amount = 10;
            int total = boardService.getTotalNum(boardListForPaging);
            PageVO pageVO = new PageVO(pageNum, amount, total);
            pageMap.put("pageMaker", pageVO);

            ArrayList<BoardVO> boardList = boardService.showBoardList(boardVO, pageMap);

            HashMap<String, ArrayList<BoardVO>> contentMap = new HashMap<>();
            contentMap.put("content", boardList);

            req.setAttribute("contentList", contentMap);
            req.setAttribute("forList", pageMap);
            result = "/board/essay.jsp";

        }else if(path.equals("/board/officialBoardList/nanam")) {

            BoardVO boardVO =new BoardVO();
            String list = "nanam";
            String pageNumStr = req.getParameter("pageNum");
            int pageNum = 1;
            boardService = new BoardService();
            String boardListForPaging=list;
            boardVO.setBoardList(list);

            if(pageNumStr==null){

                pageNum=1;
            }else{

                pageNum = Integer.parseInt(pageNumStr);
            }

            int amount = 10;
            int total  = boardService.getTotalNum(boardListForPaging);

            PageVO pageVO=new PageVO( pageNum, amount, total);
            pageMap.put("pageMaker", pageVO);

            ArrayList<BoardVO> boardList= boardService.showBoardList(boardVO, pageMap);

            HashMap<String,ArrayList<BoardVO>> contentMap = new HashMap<>();
            contentMap.put("content", boardList);

            req.setAttribute("contentList", contentMap);
            req.setAttribute("forList",pageMap);


            result = "/board/publish.jsp";

        } else if (path.equals("/board/officialBoardList/notice")) {

            BoardVO boardVO =new BoardVO();
            String list = "notice";
            String pageNumStr = req.getParameter("pageNum");
            int pageNum = 1;
            boardService = new BoardService();
            String boardListForPaging=list;
            boardVO.setBoardList(list);

            if(pageNumStr==null){

                pageNum=1;
            }else{

                pageNum = Integer.parseInt(pageNumStr);
            }

            int amount = 10;
            int total  = boardService.getTotalNum(boardListForPaging);

            PageVO pageVO=new PageVO( pageNum, amount, total);
            pageMap.put("pageMaker", pageVO);

            ArrayList<BoardVO> boardList= boardService.showBoardList(boardVO, pageMap);

            HashMap<String,ArrayList<BoardVO>> contentMap = new HashMap<>();
            contentMap.put("content", boardList);

            req.setAttribute("contentList", contentMap);
            req.setAttribute("forList",pageMap);
            result = "/board/notice.jsp";

        } else if (path.equals("/board/freeFront")) {

            result = "/board/freeBoard.jsp";

        } else if (path.equals("/board/uploadBoardFront")) {

            result = "/board/uploadBoard.jsp";

        } else if (path.equals("/board/officialBoardDetail")) {

            result = "/board/officialBoardDetail.jsp";


        } else if (path.equals("/board/writeOfficialBoard")) {

            Map<String, Object> writeBoardResultMap = null;

            String writeInfo = req.getParameter("writeInfo");

            writeBoardResultMap = boardService.writeBoard(writeInfo);

            JSONObject jsonObject = new JSONObject(writeBoardResultMap);
            PrintWriter out = resp.getWriter();
            out.print(jsonObject);
            out.flush();
            out.close();

        } else if(path.equals("/board/boardControl")){

            HashMap<String,String > map = new HashMap<>();
            String controlFlag = req.getParameter("flag");
            String boardList=req.getParameter("list");
            String subject =req.getParameter("subject");
            String seq=req.getParameter("seq");
            String rn = req.getParameter("rn");

            String content = null;

            if(controlFlag.equals("UPDATE")){

                content= req.getParameter("content");
                map.put("BOARD_CONTENT", content);
            }

            String forSeqFlag=boardList.toUpperCase(Locale.ROOT);
            String boardSeq = forSeqFlag+"_BOARD_SEQ"; // ESSAY_BOARD_SEQ;

            map.put("BOARD_LIST", boardList);
            map.put("BOARD_SUBJECT", subject);
            map.put("BOARD_SEQ", boardSeq);
            map.put("SEQ", seq);
            map.put("BOOK_SEQ", seq);
            map.put("controlFlag",controlFlag);


            if(controlFlag.equals("UPDATE") ||controlFlag.equals("DELETE") ) {
                boardService.BoardCRUD(map);
                result = "/board/officialBoardList/"+boardList;

            }else {

                BoardVO detailList = boardService.BoardCRUD(map);

               // System.out.println("detailList 확인용 ........" + detailList);

                detailList.setRn(rn);

                req.setAttribute("detailBoard", detailList);

                result = "/board/officialBoardDetail.jsp";
            }

        }

        req.getRequestDispatcher(result).forward(req,resp);

    }




}
