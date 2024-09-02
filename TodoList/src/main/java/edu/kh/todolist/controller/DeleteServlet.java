package edu.kh.todolist.controller;

import java.io.IOException;

import edu.kh.todolist.service.TodoListService;
import edu.kh.todolist.service.TodoListServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/todo/delete")
public class DeleteServlet extends HttpServlet {
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String listNo = req.getParameter("listNo");
		String title = req.getParameter("title");
		
		try {
			TodoListService service = new TodoListServiceImpl();
			
			// 서비스 호출 후 결과 반환받기
			int result = service.deleteTodo(listNo);
			
			// 삭제 성공시
			//	main 페이지로 redirect 후
			// 000이 삭제 되었습니다. 출력
			
			// 삭제 실패시 == index가 잘못됨
			// main 페이지로 redirect 후
			// index가 잘못되었습니다.
			
			HttpSession session = req.getSession();
			String message = null;
			if(result>0) {
				message = title + " 할 일이 삭제 되었습니다.";
			}
			else { message = "해당 LIST_NO번째 todo가 존재하지 않습니다.";}
			
			session.setAttribute("message", message);
			
			resp.sendRedirect("/");
			
			
		} catch ( Exception e ) {
			e.printStackTrace();
		}
	}

}
