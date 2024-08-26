package edu.kh.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCExample1 {
	
	public static void main(String[] args) {
		
		
		/** JDBC(Java DataBase Connectivity)
		 * 
		 * - Java에서 DB에 연결(접근) 할 수 있게 해주는 Java API
		 *   (Java에서 제공하는 코드)
		 *   -> java.sql 패키지에 존재함
		 */
		
		// Java 코드를 이용해서 EMPLOYEE 테이블에서
		// 사번, 이름, 부서코드, 직급코드, 급여, 입사일 조회 후
		// 이클립스 콘솔에 출력
		
		
		/** 1. JDBC 객체 참조용 변수 선언 */
		/* - java.sql.Connection :
		 * 		특정 DBMS와 연결하기 위한 정보를 저장한 객체
		 * 		== DBeaver에서 사용하는 DB 연결과 같은 역할의 객체
		 * 		(DB서버주소, 포트번호, DB이름, 계정명, 비밀번호)
		 *  */
		Connection conn = null;
		
		// java.sql.Statement
		// - 1) SQL을 DB에 전달하는 역할
		// - 2) DB에서 SQL 수행 결과를 받환받아옴
		// (Statement : 성명, 진술, "보고서")
		Statement stmt = null; // 4. Statement 객체 생성
		
		// java.sql.ResultSet
		// - SELECT 조회를 저장할 객체
		ResultSet rs = null;
		
		try {
			
			/** 2. DriverManager 객체를 이용해서 Connection 객체 생성하기 */
			
			// java.sql.DriverManager
			// - DB 연결 정보화 JDBC 드라이버를 이용해서
			// 원하는 DB와 연결할 수 있는 Connection 객체를 생성하는 객체
			
			
			/** 2-1 Oracle JDBC Driver 객체를 메모리에 로드(적재) 하기*/
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// Class.forName("패키지명+클래스명")
			// - 해당 클래스를 읽어 메모리에 적재(할당)
			//  -> JVM이 프로그램 동작에 사용할 객체를 생성하는 구문
			// --> 객체생성 구문인데 참조할 변수명은 사용 안함
			
			// oracle.jdbc.driver.OracleDriver
			// - Oracle DBMS 연결 시 필요한 코드가 담겨있는 클래스
			//  (Oracle에서 만든 클래스)
			
			
			/** 2-2 DB 연결정보 작성 */
			String type = "jdbc:oracle:thin:@"; //드라이버의 종류
			
			String host = "localhost"; // DB 서버 컴퓨터의 IP 또는 도메인 주소
			
			String port = ":1521"; // 프로그램 연결을 위한 구분번호
			
			String dbName = ":XE"; // DBMS 이름(XE= eXpress Edition)
			
			String userName = "KH_PHJ"; // 사용자 계정명
			
			String password = "KH1234"; // 계정 비밀번호
			
			
			/** 2-3 DB 연결정보와 DriverManager 를 이용해서 Connection 객체 생성 */
			conn = DriverManager.getConnection(
					type + host + port + dbName,	// url
					userName,						// user
					password);						// password
			
			// Connection 객체가 잘 생성되었는지 확인
			// == DB 연결 정보에 오타가 없는지 확인
			System.out.println(conn);
			
			
			/** 3. SQL 작성 */
			// !! 주의사항 !!
			// -> JDBC 코드에서 SQL 작성 시
			//	세미콜론 (;) 을 작성하면 안된다!!!
			// -> "SQL 명령어가 올바르게 종료되지 않았습니다"
			String sql = "SELECT "	// 끝에 띄어쓰기 안하면 연결된 문자열로 인식
					+ "EMP_ID, EMP_NAME, DEPT_CODE, JOB_CODE, SALARY, HIRE_DATE "
					+ "FROM EMPLOYEE";
			
			
			/** 4. Statement 객체 생성 */
			stmt = conn.createStatement();
			// 연결된 DB에 SQL을 전달하고 결과를 반환받을 Statement 객체를 생성함
			
			
			/** 5. Statement 객체를 이용해서 SQL 수행 후 결과 반환받기 */
			// 1) ResultSet Statement.executeQuery(sql);
			// -> SELECT문 실행 메서드. 결과로 RESULT SET 반환
			//    javq.sql.ResultSet 반환
			
			// 2) int Statement.executeUpdate(sql);
			// -> DML (INSERT, UPDATE, DELETE) 실행 메서드
			//    결과로 INT 반환(삽입, 수정, 삭제된 행의 개수)
			
			rs = stmt.executeQuery(sql);
			
			
			/** 6. 조회 결과가 담겨있는 ResultSet을
			 * 커서(Cursor)를 이용해 1행씩 접근해
			 * 각행에 작성된 컬럼 값 얻어오기
			 */
			while(rs.next()) {	// while : 언제끝날지 모르는 반복에 사용
				
				// rs.next() : 커서를 다음 행으로 이동 시킨 후 
				//		이동된 행에 값이 있으면 true 없으면 false
				// 처음 호출시 1행부터 시작
				
				//rs.get자료형(컬럼명||순서);
				// -> 현재 행에서 지정된 컬럼의 값을 얻어와 반환
				// -> 지정된 자료형 형태로 값이 반환
				//    (자료형을 잘못 지정하면 예외발생)
				
				// [java]         [db]
				// String         CHAR, VARCHAR2
				// int, long      NUMBER (정수만 저장된 컬럼)
				// float, double  NUMBER (정수 + 실수)
				// java.sql.Date  DATE
				
				String empID    = rs.getString("EMP_ID");
				String empName  = rs.getString("EMP_NAME");
				String deptCode = rs.getString("DEPT_CODE");
				String jobCode  = rs.getString("JOB_CODE");
				int salary      = rs.getInt("SALARY");
				Date hireDate   = rs.getDate("HIRE_DATE");
				
				System.out.printf(
						"사번 : %s / 이름 : %s / 부서코드 : %s / 직급코드 : %s 급여 : %d, 입사일 : %s \n",
						empID, empName, deptCode, jobCode, salary, hireDate.toString());
				
			}
			
		} catch (SQLException e) {
			// SQLExeption : DB 연결과 관련된 예외의 최상위 부보
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			
		} finally {
			
			/** 7. 사용 완료된 JDBC 객체 자원(메머리) 반환(close) */
			// -> 수행하지 않을 시 DB와 연결된 Connection이 남아있어서
			// 다은 클라이언트가 추가적으로 연결되지 못하는
			// 문제가 발생할 수 있다.
			try {
				// 만들어진 역순으로 close를 수행하는것을 추천
				if(rs != null) rs.close();
				if(stmt != null)stmt.close();
				if(conn != null)conn.close();
				
				// if문은 NullPointException 방지용 구문
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
