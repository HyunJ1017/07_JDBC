package edu.kh.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCExample6_2 {
	
	/** DAO */
	
	List<EMPLOYEE> employees = null;
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	StringBuilder sb = null;
	
	
	public JDBCExample6_2() throws ClassNotFoundException, SQLException{
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url      = "jdbc:oracle:thin:@//localhost:1521/XE";
		String userName = "KH_PHJ";
		String password = "KH1234";
		
		conn = DriverManager.getConnection(url, userName, password);
		
	}
	
	/** 전체조회결과를 String 에 담아서 반환
	 */
	public String allSearch() throws SQLException {
		
		sb = new StringBuilder();
		
		try {
			
			String sql = """
				SELECT EMP_ID, EMP_NAME, EMP_NO, DEPT_CODE, SALARY
				FROM EMPLOYEE2
				""";
			pstmt = conn.prepareStatement(sql);
			
			System.out.println(conn);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				String empId    = rs.getString("EMP_ID");
				String empName  = rs.getString("EMP_NAME");
				String empNo    = rs.getString("EMP_NO");
				String deptCode = rs.getString("DEPT_CODE");
				int    salary   = rs.getInt("SALARY");
				
				sb.append(empId);
				sb.append(" / ");
				sb.append(empName);
				sb.append(" / ");
				sb.append(empNo);
				sb.append(" / ");
				sb.append(deptCode);
				sb.append(" / ");
				sb.append(salary);
				sb.append("\n");
				
			}
			
		} finally {
			if(rs    != null) rs.close();
			if(pstmt != null) pstmt.close();
		}
		
		return sb.toString();
	}
	
	/**
	 * 이름검색
	 * @throws SQLException 
	 */
	public List<EMPLOYEE> searchName(String EMP_NAME) throws SQLException {
		
		try {
			List<EMPLOYEE> empList = new ArrayList<EMPLOYEE>();
			
			EMP_NAME = "'%" + EMP_NAME + "%'";
			
			String sql = """
				SELECT EMP_ID, EMP_NAME, EMP_NO, DEPT_CODE, SALARY
				FROM EMPLOYEE2
				WHERE EMP_NAME LIKE """ + EMP_NAME;
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			boolean flag = true;
			// 검색값이 있으면 false 없으면 true
			String empId    ="";
			String empName  ="";
			String empNo    ="";
			String deptCode ="";
			int    salary   =0;
			
			while(rs.next()) {
				flag = false;
				empId    = rs.getString("EMP_ID");
				empName  = rs.getString("EMP_NAME");
				empNo    = rs.getString("EMP_NO");
				deptCode = rs.getString("DEPT_CODE");
				salary   = rs.getInt("SALARY");
				EMPLOYEE emp = new EMPLOYEE(empId, empName, empNo, deptCode, salary);
				empList.add(emp);
			}
			
			if(flag) {
				return null;
			} else {
				return empList;
			}
			
		} finally {
			if(rs    != null) rs.close();
			if(pstmt != null) pstmt.close();
		}
		
	}

	/**input
	 * 1. 사번, 2. 이름, 3. 주민번호, 4. 부서코드
	 * 이름, 부서코드는 입력값을 포함한 검색
	 * 나머지는 완전일치
	 * @param input 검색종류
	 * @param search 검색할 내용
	 * @return
	 * @throws SQLException 
	 */
	public List<EMPLOYEE> searchBox(int input, String search) throws SQLException {
		
		try {
			List<EMPLOYEE> empList = new ArrayList<EMPLOYEE>();
			
			String searchCase = "";
			
			switch(input) {
				case 1 :
					searchCase = "EMP_ID";
					search = "'" + search + "'";
					break;
				case 2 :
					searchCase = "EMP_NAME";
					search = "'%" + search + "%'";
					break;
				case 3 :
					searchCase = "EMP_NO";
					search = "'" + search + "'";
					break;
				case 4 :
					searchCase = "DEPT_CODE";
					search = "'%" + search + "%'";
					break;
			}
			
			String sql = 
				"SELECT EMP_ID, EMP_NAME, EMP_NO, DEPT_CODE, SALARY " +
				"FROM EMPLOYEE2 " +
				"WHERE " + searchCase + " LIKE " + search;
			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			/* 리스트 요소 초기화 */
			boolean flag = true;
			// 검색값이 있으면 false 없으면 true
			String empId    ="";
			String empName  ="";
			String empNo    ="";
			String deptCode ="";
			int    salary   =0;
			
			while(rs.next()) {
				flag = false;
				empId    = rs.getString("EMP_ID");
				empName  = rs.getString("EMP_NAME");
				empNo    = rs.getString("EMP_NO");
				deptCode = rs.getString("DEPT_CODE");
				salary   = rs.getInt("SALARY");
				EMPLOYEE emp = new EMPLOYEE(empId, empName, empNo, deptCode, salary);
				empList.add(emp);
			}
			
			
			if(flag) {
				return null;
			} else {
				return empList;
			}
		} finally {
			if(rs    != null) rs.close();
			if(pstmt != null) pstmt.close();
		}
	}

}
