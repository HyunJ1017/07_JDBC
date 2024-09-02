package edu.kh.employee.control;

import java.io.IOException;
import java.util.List;

import edu.kh.employee.dto.Emp;
import edu.kh.employee.service.EmployeeService;
import edu.kh.employee.service.EmployeeServiceImple;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/main")
public class MainServlet extends HttpServlet{
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			
			EmployeeService service = new EmployeeServiceImple();
			List<Emp> empList = service.selectAll();
			
			req.setAttribute("empList", empList);
			
			String path = "/WEB-INF/views/main.jsp";
			req.getRequestDispatcher(path).forward(req, resp);
		
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
	}

}
