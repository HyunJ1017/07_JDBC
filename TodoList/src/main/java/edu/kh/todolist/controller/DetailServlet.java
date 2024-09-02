package edu.kh.todolist.controller;

import java.awt.print.Printable;
import java.io.IOException;

import org.apache.coyote.Request;

import edu.kh.todolist.dto.Todo;
import edu.kh.todolist.service.TodoListService;
import edu.kh.todolist.service.TodoListServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


/**
 * 할 일 상세조회 요청 처리 Servlet
 */
@WebServlet("/todo/detail")
public class DetailServlet extends HttpServlet {

	// a태그 요청은 GET 방식입니다.
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			// 전달받은 파라미터
			String listNo = req.getParameter("listNo");
			
			// 상세조회하는 서비스 호출 후 결과반환
			TodoListService service = new TodoListServiceImpl();
			
			Todo todo = service.selectListNo(listNo);
			// index번째 todo가 없으면 null이 반환될거임
			
			// index번째 todo가 존재하지 않을 경우
			// -> 메인페이지(/) redirect 후
			// -> 해당 인덱스에 할 일이 존재하지 않습니다. alert 출력
			if(todo.getTitle() == null) {
				HttpSession session = req.getSession();
				session.setAttribute("message", "해당 인덱스에 할 일이 존재하지 않습니다.");
				
				resp.sendRedirect("/");	// 기존 req 쓸 수 없음.
				return;
				
			}
			
			// index번째 todo가 존재하는경우
			// forward -> detail.jsp
			req.setAttribute("todo", todo);	// request scope 넘겨줄 값 세팅
			String path = "/WEB-INF/views/detail.jsp";
			
			req.getRequestDispatcher(path).forward(req, resp);
			
			
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
