package com.nanam.controller.action;


import com.fasterxml.jackson.core.JsonParser;
import com.nanam.service.PointService;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = {
        "/pay/pointCharge","/pay/pointSpend"
})

public class PointController extends HttpServlet {

    String result;

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        String path = req.getRequestURI();
        HttpSession session = req.getSession();

        if(path.equals("/pay/pointCharge")){

            String point_info=req.getParameter("point_info");

            PointService pointService = new PointService();

            JSONObject returnJson = pointService.pointCharge(point_info);

            session.setAttribute("login_point",returnJson.get("TOTAL_POINT"));

            PrintWriter out = resp.getWriter();
            out.print(returnJson);
            out.flush();
            out.close();

        }

        if(path.equals("/pay/pointSpend")){

            //System.out.println("/pay/pointSpend()");
            String point_info=req.getParameter("point_result");

            PointService pointService = new PointService();

            JSONObject returnJson = pointService.pointSpend(point_info);

            session.setAttribute("login_point",returnJson.get("TOTAL_POINT"));

            PrintWriter out = resp.getWriter();
            out.print(returnJson);
            out.flush();
            out.close();

        }

    }


}
