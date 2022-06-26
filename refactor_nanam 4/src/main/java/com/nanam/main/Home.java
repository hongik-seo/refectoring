package com.nanam.main;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(urlPatterns = {"/nanam/home"})
public class Home extends HttpServlet {
	String result;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub

		result="/home.jsp";
		System.out.println(result);

		req.getRequestDispatcher(result).forward(req, resp);
		
		
	}

	
	
	
	
	
	
}
