package edu.kh.jdbc.control.insertUser;

import java.io.IOException;

import edu.kh.jdbc.dto.User;
import edu.kh.jdbc.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/user/insertUser/insertServlet")
public class InsertServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// id, pw, name을 입력받고
		// id 중복검사 시행,
		// 중복이면 alert 띄우고
		// 중복 아니면 추가완료 띄워줄까
		// 입력한 값도 넣어서 주자
		// alert도 \n 먹더라
		
		String userId = req.getParameter("inputId");
		String userPw = req.getParameter("inputPw");
		String userName = req.getParameter("inputName");
		
		UserService service = new UserService();
		
		try {
			
			boolean result = service.searchSameId(userId);
			int resultNum = 0;
			String message = null;
			System.out.println(result);
			
			if(!result) {
				System.out.println("true 실행");
				// 중복되는 ID가 없는경우
				User user = new User();
				user.setUserName(userName);
				user.setUserPw(userPw);
				user.setUserId(userId);
				resultNum = service.insertUser(user);
				if(resultNum>0) {
					message = "User 추가 성공\nID: " + userId +", PW : " + userPw + ", Name : " + userName;
				} else {
					message = "추가 실패";
				}
				
				
				
			} else {
				System.out.println("false 실행");
				// ID가 중복될 경우
				message = "중복되는 ID가 이미 존재합니다.";
			}
			
			if(!message.isBlank()) req.getSession().setAttribute("message", message);
			req.setAttribute("userId", userId);
			req.setAttribute("userPw", userPw);
			req.setAttribute("userName", userName);
			
			
			String path = "/WEB-INF/views/user/insertUser.jsp";
			req.getRequestDispatcher(path).forward(req, resp);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		super.doPost(req, resp);
	}
}
