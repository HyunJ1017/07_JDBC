package edu.kh.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;

public class Practice1_1 {
	public static void main(String[] args) {
		
		
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Random random = new Random();
		
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url      = "jdbc:oracle:thin:@//localhost:1521/XE";
			String userName = "KH_PHJ";
			String password = "KH1234";
			
			conn = DriverManager.getConnection(url, userName, password);
			
			String gender = "";
			int gen = 0;
			int min = 0;
			int max = 0;
			String asc = "";
			
			int i = random.nextInt(10000000);
			
			if(i < 5000000) {
				gender = "F";
				gen = 2;
			} else {
				gender = "M";
				gen = 1;
			}
			
			if(i>5000000) {
				max = i;
				min = 10000000-i;}
			else {
				min = i;
				max = 10000000-i;
			}
			
			if(i%2 == 0) asc ="ASC";
			else asc = "DESC";
			
			String sql = """
					SELECT EMP_ID, EMP_NAME, SUBSTR(EMP_NO, 8, 1), SALARY, JOB_NAME, DEPT_TITLE
					FROM EMPLOYEE
					JOIN JOB USING (JOB_CODE)
					LEFT JOIN DEPARTMENT ON (DEPT_CODE = DEPT_ID)
					WHERE SUBSTR(EMP_NO, 8, 1) = ?
					AND SALARY BETWEEN ? AND ?
					ORDER BY SALARY
					""" + asc;
				
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setInt(1,gen);
				pstmt.setInt(2,min);
				pstmt.setInt(3,max);
				
				rs = pstmt.executeQuery();
				boolean flag = true;
				
				System.out.println("[실행화면]");
				System.out.println("조회할 성별(M/F) : " + gender);
				System.out.println("급여 범위(최소, 최대 순서로 작성) : " + min + " " + max);
				System.out.println("급여 정렬(1.ASC, 2.DESC) :" + asc);
				
				
				System.out.println("\n[조회결과]\n사번 | 이름   | 성별 | 급여    | 직급명 | 부서명");
				System.out.println("--------------------------------------------------------");
				while (rs.next()) {
					String empId = rs.getString(1);
					String empName = rs.getString(2);
//					int empNo = rs.getInt(3);	// 성별은 앞서 입력받았으니까 필요없음
					int salary = rs.getInt(4);
					String jobName = rs.getString(5);
					String deptTitle = rs.getString(6);
					
//					if(empNo == 1) gender = "M";// 성별은 앞서 입력받았으니까 필요없음
//					else gender = "F";
					
					if(deptTitle == null)deptTitle="부서없음";
					
					System.out.printf("%4s / %4s / %3s / %7d / %-6s / %6s\n", empId, empName, gender, salary, jobName, deptTitle );
					flag = false;
				}
				if(flag) System.out.println("Result is not exist");
				System.out.println("\n[출력완료]");
			
		} catch(Exception e) {
			e.printStackTrace();
			
		} finally {
			try {
				if(rs    != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn  != null) conn.close();
				
			} catch(Exception e) {
				e.printStackTrace();
				
			}
		}
		
	}
}
