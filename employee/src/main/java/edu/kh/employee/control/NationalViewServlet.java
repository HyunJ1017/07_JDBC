package edu.kh.employee.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import edu.kh.employee.dto.Emp;
import edu.kh.employee.service.EmployeeService;
import edu.kh.employee.service.EmployeeServiceImple;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/nationalView")
public class NationalViewServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			
			EmployeeService service = new EmployeeServiceImple();
			
			List<Emp> empList = service.selectAll();
			
			/* empList + LOCAL_CODE */
			List<Emp> empListL0 = new ArrayList<Emp>();
			List<Emp> empListL1 = new ArrayList<Emp>();	// 한
			List<Emp> empListL2 = new ArrayList<Emp>();	// 일
			List<Emp> empListL3 = new ArrayList<Emp>();	// 중
			List<Emp> empListL4 = new ArrayList<Emp>();	// 미
			List<Emp> empListL5 = new ArrayList<Emp>();	// 러
			
			for(Emp emp : empList) {
				
				String deptCode = emp.getDeptCode();
				Map<String, String> department = service.getDepartment( deptCode );
				String locationId = department.get("locationId");
				if(locationId == null) locationId = "L0";
				
				if(locationId.equals("L1")) {
					empListL1.add(emp);
				} else if(locationId.equals("L2")) {
					empListL2.add(emp);
				} else if(locationId.equals("L3")) {
					empListL3.add(emp);
				} else if(locationId.equals("L4")) {
					empListL4.add(emp);
				} else if(locationId.equals("L5")) {
					empListL5.add(emp);
				} else {
					empListL0.add(emp);
				}
				
			}
			
			req.setAttribute("emplistL1", empListL1);
			req.setAttribute("empListL2", empListL2);
			req.setAttribute("empListL3", empListL3);
			req.setAttribute("empListL4", empListL4);
			req.setAttribute("empListL5", empListL5);
			req.setAttribute("empListL0", empListL0);
			
			
			String path = "/WEB-INF/views/nationalView.jsp";
			req.getRequestDispatcher(path).forward(req, resp);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
	}

}
