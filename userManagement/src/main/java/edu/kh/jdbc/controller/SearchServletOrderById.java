package edu.kh.jdbc.controller;

import java.io.IOException;
import java.util.List;

import edu.kh.jdbc.dto.User;
import edu.kh.jdbc.service.UserService;
import edu.kh.jdbc.service.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/search/byId")
public class SearchServletOrderById extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
		String resultId = req.getParameter("resultId");
		System.out.println("resultId : " + resultId);
		if(resultId == null) {
			System.out.println("resultId == null");
			resultId = "";
		}
		
		UserService service = new UserServiceImpl();
		List<User> searchList = service.searchId(resultId, 2);
		int listSize = searchList.size();
		
		if(searchList.isEmpty()) {
			req.getSession().setAttribute("message", "입력한" + resultId + "를 포함한 ID가 없습니다.");
		} else {
			req.setAttribute("userList", searchList);
		}
		req.setAttribute("listSize", listSize);
		String path = "/WEB-INF/views/selectAll.jsp";
		
		req.getRequestDispatcher(path).forward(req, resp);
		
		
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
	}

}
