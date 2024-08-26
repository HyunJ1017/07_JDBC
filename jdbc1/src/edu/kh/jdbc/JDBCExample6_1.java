package edu.kh.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class JDBCExample6_1 {
	public static void main(String[] args) {
		
		
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url      = "jdbc:oracle:thin:@//localhost:1521/XE";
			String userName = "KH_PHJ";
			String password = "KH1234";
			
			conn = DriverManager.getConnection(url, userName, password);
			
			String sql = """
				SELECT EMP_ID, EMP_NAME, EMP_NO, DEPT_CODE, SALARY
				FROM EMPLOYEE2
				""";
			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				String empId    = rs.getString("EMP_ID");
				String empName  = rs.getString("EMP_NAME");
				String empNo    = rs.getString("EMP_NO");
				String deptCode = rs.getString("DEPT_CODE");
				int    salary   = rs.getInt("SALARY");
				
				System.out.printf("%s / %s / %s / %s / %d\n",
						empId, empName, empNo, deptCode, salary );
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			try {
				if(rs    != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn  != null) conn.close();
				
			} catch (Exception e) {
				e.printStackTrace();
				
			}
		}
		
	}

}
