package edu.kh.employee.control;

import java.io.IOException;
import java.util.Map;

import edu.kh.employee.dto.Emp;
import edu.kh.employee.service.EmployeeService;
import edu.kh.employee.service.EmployeeServiceImple;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/detail")
public class DetailServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		try {
			String empId = req.getParameter("empId");
			
			EmployeeService service = new EmployeeServiceImple();
			Emp emp = service.selectId(empId);
			
			Map<String, String> department = service.getDepartment(emp.getDeptCode());
			String deptTitle = department.get("deptTitle");
			String locationId = department.get("locationId");
			
			String jobName = service.getJobName(emp.getJobCode());
			
			Map<String, String> location = service.getLocation(locationId);
			String localName = location.get("localName");
			String nationalName = location.get("nationalName");
			
			req.setAttribute("emp", emp);
			req.setAttribute("deptTitle", deptTitle);
			req.setAttribute("locationId", locationId);
			req.setAttribute("jobName", jobName);
			req.setAttribute("localName", localName);
			req.setAttribute("nationalName", nationalName);
			
			String path = "/WEB-INF/views/detail.jsp";
			req.getRequestDispatcher(path).forward(req, resp);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		
		
	}

}
