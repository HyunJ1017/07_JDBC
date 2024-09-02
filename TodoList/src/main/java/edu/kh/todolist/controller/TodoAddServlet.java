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

@WebServlet("/todo/add")
public class TodoAddServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		/** 할 일 추가 -> 파일에 입력받은 데이터 저장 */
		try {
			
			// 1.
			TodoListService service = new TodoListServiceImpl();
			
			// 2. 요청시 전달받은 파라미터 얻어오기
			String title = req.getParameter("title");
			String detail = req.getParameter("detail");
			
			// 3. 서비스 호출 후 결과값 받아오기
			int index = service.insertTodo(title, detail);
			// 성공시 추가된 컬럼갯수 실패시 0 반환
			
			// 4. 성공/ 싪
			String message = null;
			if(index>0) message = "추가성공!";
			else         message = "추가실패...";
			
			// 5. 기본 req를 사용할 수 없기 때문에 (direct로 보내면 새로 생성하니까)
			// session 을 이용해서 message를 저장
			HttpSession session = req.getSession();
			session.setAttribute("message", message);
			
			// 6.메인패이지로 redirect
			resp.sendRedirect("/main");
			// -> @WebServlet("/")가 작성된 Servlet을 재요청
			/* redirect는 무조건 GET방식 전달 */
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
