package com.nanam.controller.action;


import com.nanam.domain.bookDB.BookVO;
import com.nanam.domain.cartDB.CartVO;
import com.nanam.domain.orderAndPayDB.OrderAndPayVO;
import com.nanam.service.BookService;
import com.nanam.service.PointService;
import com.nanam.service.PurchaseService;
import org.json.simple.JSONArray;
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

                "/cart/cartFront","/cart/cartFromHead","/cart/deleteCart","/cart/goToOrder","/order/orderFromHead"
                ,"/orderAndPay/orderAndPay", "/orderAndPay/result" , "/order/payResultFromHead"
        }
)
public class PurchaseController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    String result;


    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        String path = req.getRequestURI();


        if(path.equals("/cart/cartFront")){
            HashMap<String,String> map = new HashMap<>();
            PurchaseService purchaseService = new PurchaseService();

            String controlFlag  = req.getParameter("flag");
            String id = req.getParameter("id");
            String userId = req.getParameter("userId");
            map.put("ID", id);
            map.put("USER_ID", userId);
            map.put("controlFlag", controlFlag);
            String  bookList = req.getParameter("bookList");
            String  seq = req.getParameter("bookSeq");
            String  bookIsbn = req.getParameter("bookIsbn");
            map.put("ID", id);
            map.put("USER_ID", userId);
            map.put("BOOK_LIST", bookList);
            map.put("SEQ", seq);
            map.put("BOOK_ISBN", bookIsbn);
            String forSeqFlag = bookList.toUpperCase(Locale.ROOT);
            String bookSeq = forSeqFlag + "_BOOK_SEQ"; // ESSAY_BOARD_SEQ;
            map.put("BOOK_SEQ", bookSeq);

            purchaseService.cartInsertUserAndCart(map);

            ArrayList<CartVO> showMap = purchaseService.showCartList(map);
            Map<String,ArrayList<CartVO> > cartListResultMap = new HashMap<>();
            cartListResultMap.put("cartList", showMap);
            req.setAttribute("detailBookList", cartListResultMap);
            ;

            result ="/pay/cart.jsp";

        }else if(path.equals("/cart/cartFromHead")){

            String id=req.getParameter("id");

            HashMap<String,String> map = new HashMap<>();

            map.put("ID",id);
            PurchaseService purchaseService = new PurchaseService();
            ArrayList<CartVO> showMap = purchaseService.showCartList(map);
            Map<String,ArrayList<CartVO> > cartListResultMap = new HashMap<>();

            cartListResultMap.put("cartList", showMap);
            req.setAttribute("detailBookList", cartListResultMap);
            result ="/pay/cart.jsp";

        }else if(path.equals("/order/orderFromHead")){
            PurchaseService purchaseService = new PurchaseService();

            String id=req.getParameter("id");

            ArrayList<CartVO> list =new ArrayList<>();

            CartVO cartVO = new CartVO();
            cartVO.setId(Long.valueOf(id));

            list.add(cartVO);
            Map<String,ArrayList<CartVO> > cartListResultMap = new HashMap<>();
            ArrayList<CartVO> showMap = purchaseService.showOderList(list);

            //System.out.println(showMap);

            cartListResultMap.put("cartList", showMap);
            req.setAttribute("detailBookList", cartListResultMap);

            result = "/pay/orderAndPay.jsp";

        }else if(path.equals("/orderAndPay/result")|| path.equals("/order/payResultFromHead")){

          String id= req.getParameter("id");

          PurchaseService purchaseService = new PurchaseService();

          ArrayList<OrderAndPayVO > list = new ArrayList<>();

          OrderAndPayVO orderAndPayVO = new OrderAndPayVO();

          orderAndPayVO.setId(Long.valueOf(id));
          list.add(orderAndPayVO);

          Map<String,ArrayList<OrderAndPayVO> > cartListResultMap = new HashMap<>();
          ArrayList<OrderAndPayVO> showMap = purchaseService.showBuyResultList(list);


          cartListResultMap.put("orderList", showMap);
          req.setAttribute("detailBookList", cartListResultMap);

          result = "/pay/buyResult.jsp";


        }

        req.getRequestDispatcher(result).forward(req,resp);
    }


    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        String path = req.getRequestURI();

        if(path.equals("/cart/deleteCart")){

            String deleteData=req.getParameter("deleteData");
            PurchaseService purchaseService = new PurchaseService();

            String echoStr=purchaseService.deleteButUseYnUpdate(deleteData);

            PrintWriter out = resp.getWriter();
            out.print(echoStr);
            out.flush();
            out.close();

        }else if(path.equals("/cart/goToOrder")){

            String[] cartOfVolume = req.getParameterValues("bookNum");
            String[] cartSeqs = req.getParameterValues("CART_SEQ");
            ArrayList<CartVO> list = new ArrayList<>();

            for(int i=0; i<cartOfVolume.length; i++){
                CartVO cartVO = new CartVO();
                String cartOfTotalOfVolume = req.getParameter("totalNum");
                String cartOfTotalOfPrice = req.getParameter("totalPrice");
                String id = req.getParameter("ID");

                cartVO.setId(Long.valueOf(id));
                cartVO.setCartOfTotalVolume(Long.valueOf(cartOfTotalOfVolume));
                cartVO.setCartTotalPrice(Long.valueOf(cartOfTotalOfPrice));
                cartVO.setCartOfVolume(Long.valueOf(cartOfVolume[i]));
                cartVO.setCartSeq(Long.valueOf(cartSeqs[i]));
                list.add(cartVO);
            }

            PurchaseService purchaseService = new PurchaseService();
            Map<String,ArrayList<CartVO> > cartListResultMap = new HashMap<>();

            ArrayList<CartVO> showMap = purchaseService.updateCartTotalAndInsertCartFinCartAndSelectCARTFIN(list);


            cartListResultMap.put("cartList", showMap);
            req.setAttribute("detailBookList", cartListResultMap);

            result = "/pay/orderAndPay.jsp";

        }else if(path.equals("/orderAndPay/orderAndPay")){


            String order_info= req.getParameter("order_info");


            PurchaseService purchaseService = new PurchaseService();
            JSONObject returnOrder = purchaseService.orderAndPay(order_info);


            PrintWriter out = resp.getWriter();
            out.print(returnOrder);
            out.flush();
            out.close();

        }

        req.getRequestDispatcher(result).forward(req,resp);

    }




}
