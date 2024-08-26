package edu.kh.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class JDBCExample2 {
	public static void main(String[] args) {
		
		// 입력받은 급여보다 초과해서 받는 사원의
		// 사번, 이름, 급여 조회하기
		
		
		/** 1. JDBC 객체 참조용 변수 선언 */
		Connection conn = null;	//DB연결정보 저장(url,id,pass)
		Statement stmt = null;	// SQL 수행, 결과반환용 객체
		ResultSet rs = null; 	// SELECT 수행 결과 저장 객체
		
		Scanner sc = new Scanner(System.in);
		
		try {
			
			/** 2. DriverManager 객체를 이용해서 Connection 객체 생성하기 */
			/** 2-1 Oracle JDBC Driver 객체를 메모리에 로드(적재) 하기*/
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			/** 2-2 DB 연결정보 작성 */
			String type = "jdbc:oracle:thin:@"; //드라이버의 종류
			String host = "localhost"; // DB 서버 컴퓨터의 IP 또는 도메인 주소
			String port = ":1521"; // 프로그램 연결을 위한 구분번호
			String dbName = ":XE"; // DBMS 이름(XE= eXpress Edition)
			String userName = "KH_PHJ"; // 사용자 계정명
			String password = "KH1234"; // 계정 비밀번호
			
			/** 2-3 DB 연결정보와 DriverManager 를 이용해서 Connection 객체 생성 */
			conn = DriverManager.getConnection(type + host + port + dbName, userName, password);
			
			System.out.println(conn);
			
			/** 3. SQL 작성 */
			
			System.out.print("급여 입력 : ");
			int input = sc.nextInt();
			
			String sql = """
					SELECT EMP_ID, EMP_NAME, SALARY
					FROM EMPLOYEE
					WHERE SALARY > 	""" + input;
			
			/** 4. Statement 객체 생성 */
			stmt = conn.createStatement();
			
			/** 5. Statement 객체를 이용해서 SQL 수행 후 결과 반환받기 */
			rs = stmt.executeQuery(sql);
			
			// executeQuery() : CELECT 실행, RESULT 반환. SQL문을 사용할때
			// executeUpdate(): DML문  실행, 결과 행의 갯수 반환
			
			/** 6. 조회 결과가 담겨있는 ResultSet의 컬럼 값 얻어오기 */
			while(rs.next()) {
				// rs.next() : 커서를 다음 행으로 이동
				//	값이 있으면 true, 없으면 false 반환
				
				String empID   = rs.getString("EMP_ID");
				String empName = rs.getString("EMP_NAME");
				int    salary  = rs.getInt("SALARY");
				
				System.out.printf("%s / %s / %d원\n", empID, empName, salary);
				
			}
			
			
			
		} catch (Exception e) {
			// 최상위 예외인 Exception을 이용해서 모든 예외 처리
			// -> 다형성 업캐스팅 적용
			e.printStackTrace();
		} finally {
			/** 7. 사용 완료된 JDBC 객체 자원(메머리) 반환(close) */
				try {
					if(sc   != null) sc.close();
					if(rs   != null) rs.close();
					if(stmt != null) stmt.close();
					if(conn != null) conn.close();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
		
	}

}
