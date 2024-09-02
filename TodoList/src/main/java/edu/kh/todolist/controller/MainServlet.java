package edu.kh.todolist.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import edu.kh.todolist.dto.Todo;
import edu.kh.todolist.service.TodoListService;
import edu.kh.todolist.service.TodoListServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// "/" 요청을 매핑하여 처리하는 서블릿
// "/" ==localhost/main == 메인페이지
@WebServlet("/main")
public class MainServlet extends HttpServlet {
	
	/* dho "/" 메인페이지 요청을 처리하는 서블릿을 만들었는가?
	 * 
	 * - Servlert(Back-End) 단계에서 추가한 데이터를 
	 * 	 메인페이지에서 사용할 수 있게 하려고
	 * 
	 */

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		// 테스트용 셈플 데이터
		// req.setAttribute("str", "메인페이지 요청시 값 전달됨");
		
		
		try {
			// Servicr 객체 생성
			TodoListService service = new TodoListServiceImpl();
			
			// 전체 할 일 목록 + 완료된 Todo 개수가 담긴 Mapㅇ,ㄹ
			// Service 호출해서 얻어오기
			Map<String, Object> map = service.todoListFullView();
			
			// Map에 저장된 값 풀어내기
			@SuppressWarnings("unchecked")
			List<Todo> todoList = (List<Todo>) map.get("todoList");
			int completeCount = (int)map.get("completeCount");
			
			// request scope에 객체값을 추가
			req.setAttribute("todoList", todoList);
			req.setAttribute("completeCount", completeCount);
			
			// 메인페이지 응답을 담당하는 jsp에 요청 위임
			String path = "/WEB-INF/views/main.jsp";
			req.getRequestDispatcher(path).forward(req, resp);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
}
