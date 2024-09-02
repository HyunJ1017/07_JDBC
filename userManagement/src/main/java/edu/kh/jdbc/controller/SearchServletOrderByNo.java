package edu.kh.jdbc.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/search/byNo")
public class SearchServletOrderByNo extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			
			String resultId = req.getParameter("resultId");
			if(resultId == null) {
				System.out.println("searchId == null");
				String path = "/selectAll";
				resp.sendRedirect(path);
				
			} else {
				
				req.setAttribute("searchId", resultId);
				String path = "/search";
				resp.sendRedirect(path);
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		
	}

}
