package edu.kh.employee.service;

import static edu.kh.employee.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import edu.kh.employee.dao.EmployeeDAO;
import edu.kh.employee.dao.EmployeeDAOImple;
import edu.kh.employee.dto.Emp;

public class EmployeeServiceImple implements EmployeeService {
	
	EmployeeDAO dao = new EmployeeDAOImple();

	@Override
	public List<Emp> selectAll() throws Exception {
		
		Connection conn = getConnection();
		List<Emp> empLsit = dao.selectAll(conn);
		close(conn);
		return empLsit;
	}

	@Override
	public Emp selectId(String empId) throws Exception {
		
		Connection conn = getConnection();
		Emp emp = dao.selectId(conn, empId);
		close(conn);
		return emp;
	}

	@Override
	public Map<String, String> getDepartment(String deptCode) throws Exception {
		Connection conn = getConnection();
		Map<String, String> department = dao.getDepartment(conn, deptCode);
		close(conn);
		return department;
	}

	@Override
	public String getJobName(String jobCode) throws Exception {
		Connection conn = getConnection();
		String jobName = dao.getJobName(conn, jobCode);
		close(conn);
		return jobName;
	}

	@Override
	public Map<String, String> getLocation(String locationId) throws Exception {
		Connection conn = getConnection();
		Map<String, String> location = dao.getLocation(conn, locationId);
		close(conn);
		return location;
	}

}
