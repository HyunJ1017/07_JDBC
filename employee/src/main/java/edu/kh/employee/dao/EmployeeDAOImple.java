package edu.kh.employee.dao;

import static edu.kh.employee.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import edu.kh.employee.common.JDBCTemplate;
import edu.kh.employee.dto.Emp;

public class EmployeeDAOImple implements EmployeeDAO {
	
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private Properties prop = null;

	public EmployeeDAOImple() {
		
		try {
			String filePath = JDBCTemplate.class.getResource("/edu/kh/employee/sql/sql.xml").getPath();
			
			prop = new Properties();
			prop.loadFromXML( new FileInputStream(filePath));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/** 전체조회
	 */
	@Override
	public List<Emp> selectAll(Connection conn) throws Exception {
		List<Emp> empList = new ArrayList<Emp>();
		
		try {
			
			String sql = prop.getProperty("selectAll");
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				
				String empId       = rs.getString("EMP_ID");
				String empName     = rs.getString("EMP_NAME");
				String empNo       = rs.getString("EMP_NO");
				String email       = rs.getString("EMAIL");
				String phone       = rs.getString("PHONE");     
				String deptCode    = rs.getString("DEPT_CODE"); 
				String jobCode     = rs.getString("JOB_CODE");
				String salaryLevel = rs.getString("SAL_LEVEL");
				int    salary      = rs.getInt   ("SALARY");
				float  bonus       = rs.getFloat ("BONUS");
				String managerId   = rs.getString("MANAGER_ID");
				String hireDate    = rs.getString("HIRE_DATE");
				String entDate     = rs.getString("ENT_DATE");
				String entYN       = rs.getString("ENT_YN");
				
				Emp emp = 
						new Emp(empId, empName, empNo, email, phone, deptCode,
								jobCode, salaryLevel, salary, bonus, managerId,
								hireDate, entDate, entYN);
				
				empList.add(emp);
				
			}
			
		} finally {
			close(rs);
			close(stmt);
		}
		
		return empList;
	}

	@Override
	public Emp selectId(Connection conn, String searchId) throws Exception {
		Emp emp = new Emp();
		
		try {
			
			String sql = prop.getProperty("selectId");
			sql = sql + searchId;
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				
				emp.setEmpId(rs.getString("EMP_ID"));
				emp.setEmpName(rs.getString("EMP_NAME"));
				emp.setEmpNo(rs.getString("EMP_NO"));
				emp.setEmail(rs.getString("EMAIL"));
				emp.setPhone(rs.getString("PHONE"));
				emp.setDeptCode(rs.getString("DEPT_CODE")); 
				emp.setJobCode(rs.getString("JOB_CODE"));
				emp.setSalaryLevel(rs.getString("SAL_LEVEL"));
				emp.setSalary(rs.getInt("SALARY"));
				emp.setBonus(rs.getFloat("BONUS"));
				emp.setManagerId(rs.getString("MANAGER_ID"));
				emp.setHireDate(rs.getString("HIRE_DATE"));
				emp.setEntDate(rs.getString("ENT_DATE"));
				emp.setEntYN(rs.getString("ENT_YN"));
				
			}
			
		} finally {
			close(rs);
			close(stmt);
		}
		
		return emp;
	}

	@Override
	public Map<String, String> getDepartment(Connection conn, String deptCode) throws Exception {
		
		Map<String, String> department = new HashMap<String, String>();
		
		try {
			String sql = prop.getProperty("getDepartment");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, deptCode);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				department.put("deptTitle", rs.getString("DEPT_TITLE"));
				department.put("locationId", rs.getString("LOCATION_ID"));
			}
			
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return department;
	}

	@Override
	public String getJobName(Connection conn, String jobCode) throws Exception {
		
		String jobName = "";
		
		try {
			String sql = prop.getProperty("getJobName");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, jobCode);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				jobName = rs.getString("JOB_NAME");
			}
			
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return jobName;
	}

	@Override
	public Map<String, String> getLocation(Connection conn, String locationId) throws SQLException {
		
		Map<String, String> location = new HashMap<String, String>();
		
		try {
			String sql = prop.getProperty("getLocation");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, locationId);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				location.put("nationalCode", rs.getString("NATIONAL_CODE"));
				location.put("localName", rs.getString("LOCAL_NAME"));
				location.put("nationalName", rs.getString("NATIONAL_NAME"));
			}
			
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return location;
	}

}
