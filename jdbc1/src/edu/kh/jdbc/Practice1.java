package edu.kh.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Practice1 {
	public static void main(String[] args) {
		
		// EMPLOYEE 테이블에서
		// 사번, 이름, 성별, 급여, 직급명, 부서명을 조회
		// 단, 입력 받은 조건에 맞는 결과만 조회하고 정렬할 것
				
		// - 조건 1 : 성별 (M, F)
		// - 조건 2 : 급여 범위
		// - 조건 3 : 급여 오름차순/내림차순
				
		// [실행화면]
		// 조회할 성별(M/F) : F
		// 급여 범위(최소, 최대 순서로 작성) : 3000000 4000000
		// 급여 정렬(1.ASC, 2.DESC) : 2
				
		// 사번 | 이름   | 성별 | 급여    | 직급명 | 부서명
		//--------------------------------------------------------
		// 218  | 이오리 | F    | 3890000 | 사원   | 없음
		// 203  | 송은희 | F    | 3800000 | 차장   | 해외영업2부
		// 212  | 장쯔위 | F    | 3550000 | 대리   | 기술지원부
		// 222  | 이태림 | F    | 3436240 | 대리   | 기술지원부
		// 207  | 하이유 | F    | 3200000 | 과장   | 해외영업1부
		// 210  | 윤은해 | F    | 3000000 | 사원   | 해외영업1부

		System.out.println("[실행화면]");
		
		Scanner sc = new Scanner(System.in);
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
	
		try {
				
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url      = "jdbc:oracle:thin:@//localhost:1521/XE";
			String userName = "KH_PHJ";
			String password = "KH1234";
			
			conn = DriverManager.getConnection(url, userName, password);
			System.out.println("[" + conn + "] 연결완료");
			
			boolean flag = true;
			String gender = "";
			do {
				System.out.print("(1/3)조회할 성별(M/F) : ");
				gender = sc.next().toUpperCase();
				
				if(gender.equals("F") || gender.equals("M")) flag=false;
				else System.out.println("알맞은값을입력해주세요");
			}while(flag);
			int gen = 0;
			if(gender.equals("F")) gen = 2;
			else gen = 1;
			
			flag = false;
			int min = 0;
			int max = 0;
			
	        while (!flag) {
	            try {
	                System.out.print("(2/3)급여 범위(최소, 최대 순서로 작성) : ");
	                min = sc.nextInt(); 
	                max = sc.nextInt(); 
	                flag = true;
	            } catch (InputMismatchException e) {
	                System.out.println("숫자만입력해주세요");
	                sc.next();
	            }
	        }
			
			if(min > max) {
				int temp = min;
				min = max;
				max = temp;
			}
			
			String asc = "";
			
			flag = false;
	        while (!flag) {
	            try {
	            	
	            	flag = true;
	            	do {
	            		System.out.print("(3/3)급여 정렬(1.ASC, 2.DESC) : ");
	            		int input = sc.nextInt();
	            		if(input == 1) {
	            			asc = "ASC";
	            			flag = false;
	            		}
	            		else if (input == 2) {
	            			asc = "DESC";
	            			flag = false;
	            		}
	            		else {
	            			System.out.println("알맞은값을입력해주세요");
	            			flag = true;
	            		}
	            	}while(flag);
	                
	                flag = true; 
	            } catch (InputMismatchException e) {
	                System.out.println("숫자만입력해주세요");
	                flag = false;
	                sc.next();
	            }
	        }
			
			
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
			
			pstmt.setInt(1, gen);
			pstmt.setInt(2, min);
			pstmt.setInt(3, max);
			
			rs = pstmt.executeQuery();
			
			System.out.println("\n[조회결과]\n사번 | 이름   | 성별 | 급여    | 직급명 | 부서명");
			System.out.println("--------------------------------------------------------");
			while (rs.next()) {
				String empId = rs.getString(1);
				String empName = rs.getString(2);
//				int empNo = rs.getInt(3);	// 성별은 앞서 입력받았으니까 필요없음
				int salary = rs.getInt(4);
				String jobName = rs.getString(5);
				String deptTitle = rs.getString(6);
				
//				if(empNo == 1) gender = "M";// 성별은 앞서 입력받았으니까 필요없음
//				else gender = "F";
				
				if(deptTitle == null)deptTitle="부서없음";
				
				System.out.printf("%4s | %4s | %3s | %7d | %-3s | %s\n", empId, empName, gender, salary, jobName, deptTitle );
				flag = false;
			}
			if(flag) System.out.println("없음");
			System.out.println("\n[출력완료]");
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			
			try {
				if(sc    != null) sc.close();
				if(rs    != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn  != null) conn.close();
				
			} catch (Exception e) {
				e.printStackTrace();
				
			}
		}
		
		
	}

}
