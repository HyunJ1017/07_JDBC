package edu.kh.jdbc.controller;

import java.io.IOException;

import edu.kh.jdbc.dto.User;
import edu.kh.jdbc.service.UserService;
import edu.kh.jdbc.service.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/updateUser")
public class UpdateUserServlet extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			
			int userNo = Integer.parseInt( req.getParameter("userNo") );
			String userPw = req.getParameter("userPw");
			String userName = req.getParameter("userName");
			
			User user = new User();
			user.setUserNo(userNo);
			user.setUserPw(userPw);
			user.setUserName(userName);
			
			UserService service = new UserServiceImpl();
			int result = service.updateUser(user);
			
			String message;
			if(result > 0) {
				message = "수정하는게 성공";
			} else {
				message = "실패";
			}
			
			req.getSession().setAttribute("message", message);
			
			String path = "/selectUser?userNo=" + userNo;
			resp.sendRedirect(path);
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
