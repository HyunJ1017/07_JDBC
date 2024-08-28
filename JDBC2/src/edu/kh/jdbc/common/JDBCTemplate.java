package edu.kh.jdbc.common;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/* [TEMPLATE] : 양식, 주형, 본을뜨기위한틀
 * -> 미리 만들어둔 양식
 * 
 * [JDBCTemplate] :
 * 	JDBC 관련 작업을 위한 코드를 미리 작성해서 제공할 클래스
 * 
 * - getConndection() + AutoCommit false
 * - commit() / rollback()
 * - 각종 close()
 * 
 * ***** 중요 *****
 * 어디서든 JDBCTemplate 클래스를 객체로 만들지 않고도
 * 메서드를 사용할 수 있도록 하기 위해
 * 모든 메서드를 public static 으로 선언 */
public class JDBCTemplate {
	
	/* try catch 줄일 수 있음
	 * connection conn 선언 줄일 수 있음
	 */
	
	// 필드
	private static Connection conn = null;
	// -> static 메서드에서 사용 가능한 필드는 static 필드만 가능
	
	// 메서드
	
	/**
	 * 호출시 Connection 객체를 생성해서 반환하는 메서드
	 * @return
	 */
	public static Connection getConnection() {
		
		try {
			
			// 이전에 참조하는 Connection 객체가 존재하고
			// 아직 closed() 한 상태가 아니라면
			if(conn != null && !conn.isClosed() ) {
				return conn; // 새로 만들지 않고 기존 Connection 반환
			}
			
			
			/* DB 연결을 위한 정보들을 별도 파일에 작성하여
			 * 읽어오는 형식으로 코드를 변경!!!
			 * 
			 * 이유 1 : Github에 코드 올리면 정보노출되서
			 * 			보안적인 측면에서 직접 보지 못하게 함
			 * 
			 * 이유 2 : 혹시라도 연결하는 DB 정보가 변경될 경우
			 * 			Java 코드가 아닌
			 * 			읽어오는 파일의 내용을 수정하면 되기 때문에
			 * 			Java 코드 수정 x -> 추가 컴파일 필요 x
			 * 			--> 개발 시간 단축 !!
			 * - 프로그램 실행시 입력받으면 안되나
			 * - 필요한데 없으면 입력창이 띄워지게 하거나
			 */
			
			// driver.xml 파일 내용 읽어오기
			
			// 1. Properties 객체 생성
			// - Map의 자식 클래스
			// - K, V 가 모두 String 타입
			// - xml파일 입출력을 쉽게 할 수 있는 메서드를 제공해줌
			Properties prop = new Properties();
			
			// 2. Properties 메서드를 이용해서
			//    driver.xml 파일 내용을 읽어와 prop에 저장
			String filePath = "driver.xml";
			// 프로젝트 폴더 바로 아래 driver.xml을 의미
			
			prop.loadFromXML(new FileInputStream(filePath));
			
			// prop에 저장된 값(driver.xml에서 읽어온 값)을 이용해
			// Connection 객체 생성하기
			
			Class.forName( prop.getProperty( "driver" ) );
			String url = prop.getProperty("url");
			String userName = prop.getProperty("userName");
			String password = prop.getProperty("password");
			
			conn = DriverManager.getConnection(url, userName, password);
			
			// 만들어진 Connection에 AutoCommit 끄기
			conn.setAutoCommit(false);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	
	// =================================================================
	
	/* 트랜잭션 제어 처리 메서드(commit, rollback) */
	
	/**
	 * 전달 받은 커넥션에서 수행한 SQL을 COMMIT하는 메서드
	 * @param conn
	 */
	public static void commit(Connection conn) {
		try {
			if(conn != null && !conn.isClosed()) conn.commit();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 전달 받은 커넥션에서 수행한 SQL을 Rollback하는 메서드
	 * @param conn
	 */
	public static void rollback(Connection conn) {
		try {
			if(conn != null && !conn.isClosed()) conn.rollback();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// =================================================================

	
	/**
	 * 전달 받은 커넥션을 close(자원 반환)하는 메서드
	 * @param conn
	 */
	public static void close(Connection conn) {
		try {
			if(conn != null && !conn.isClosed()) conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 전달 받은 Statement을 close(자원 반환)하는 메서드
	 * + PreparedStatement도 close 처리 가능
	 * 		--> 다형성 업캐스팅 적용
	 * @param stmt
	 */
	public static void close(Statement stmt) {
		try {
			if(stmt != null && !stmt.isClosed()) stmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
	}
	
	/**
	 * 전달 받은 ResultSet을 close(자원 반환)하는 메서드
	 * @param rs
	 */
	public static void close(ResultSet rs) {
		try {
			if(rs != null && !rs.isClosed()) rs.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
	}
	
}
