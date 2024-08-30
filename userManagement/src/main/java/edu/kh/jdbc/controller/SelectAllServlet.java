package edu.kh.jdbc.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import edu.kh.jdbc.dto.User;
import edu.kh.jdbc.service.UserService;
import edu.kh.jdbc.service.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/selectAll")
public class SelectAllServlet extends HttpServlet {
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		UserService service = new UserServiceImpl();
		
		try {
			List<User> userList = service.selectAll();
			req.setAttribute("userList", userList);
			req.setAttribute("check", "User Management 전체 목록");
			
			String path = "/WEB-INF/views/selectAll.jsp";
			
			req.getRequestDispatcher(path).forward(req, resp);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
