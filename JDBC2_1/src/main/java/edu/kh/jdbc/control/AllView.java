package edu.kh.jdbc.control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import edu.kh.jdbc.dto.User;
import edu.kh.jdbc.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/user/allView")
public class AllView extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("checkpoint1");
		UserService service = new UserService();
		
		
		try {
			List<User> userList = service.selectAll();
			System.out.println(userList.get(0).toString());
			req.setAttribute("userList", userList);
			req.setAttribute("check", "데이터넘어옴");
			
			String path = "/WEB-INF/views/user/userList.jsp";
			
			req.getRequestDispatcher(path).forward(req, resp);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
}
