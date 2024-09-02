package edu.kh.todolist.controller;

import java.io.IOException;
import java.util.List;

import edu.kh.todolist.dto.Todo;
import edu.kh.todolist.service.TodoListService;
import edu.kh.todolist.service.TodoListServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/main/orderby")
public class MainOrderServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			String order = req.getParameter("order");
			String completeCount = req.getParameter("completeCount");
			
			System.out.println(order);
			
			String oderBy="";
			if(order != null) {
				if(order.equals("DESC")) oderBy = "ASC";
				else {
					 oderBy = "DESC";
				}
			} else {
				oderBy = "ASC";
			}
			
			TodoListService service = new TodoListServiceImpl();
			List<Todo> todoList = service.todoListFullViewOrderByComplete(oderBy);
			
			req.setAttribute("todoList", todoList);
			req.setAttribute("completeCount", completeCount);
			req.setAttribute("order", oderBy);
			
			// 메인페이지 응답을 담당하는 jsp에 요청 위임
			String path = "/WEB-INF/views/main.jsp";
			req.getRequestDispatcher(path).forward(req, resp);
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}

}
