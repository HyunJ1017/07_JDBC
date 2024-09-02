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

@WebServlet("/todo/complete")
public class CompleteServlet extends HttpServlet {

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		try {
			// 전달받은 인덱스 얻어오기
			String listNo = req.getParameter("listNo");
			String complete = req.getParameter("complete");
			
			//할일 여부를 변경하는 서비스 호출 후 반환받기
			TodoListService service = new TodoListServiceImpl(); 
			boolean result = service.completeTodo(listNo, complete);
			
			//session scope 객체 얻어오기
			HttpSession session = req.getSession();
			
			// 변경 성공시 원래 보고있던 상세페이지로 redirect (=~새로고침)
			if(result) {
				
				session.setAttribute("message", "완료여부가 변경되었습니다");
				resp.sendRedirect("/todo/detail?listNo=" + listNo );
				return;
			}
			
			// 변경 실패시
			session.setAttribute("message", "해당 index번째 Todo가 존재하지 않습니다.");
			resp.sendRedirect("/");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
}
