package edu.kh.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class JDBCExample6 {
	public static void main(String[] args) {
		
		// ID, PW 를 입력받아
		// 일치하는 사용자(TB_USER)의
		// 이름을 수정(UPDATE)
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		Scanner sc = new Scanner(System.in);
		
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@//localhost:1521/XE";
			String userName = "KH_PHJ";
			String password = "KH1234";
			
			conn = DriverManager.getConnection(url, userName, password);
			
			/** AutoCommit 끄기*/
			conn.setAutoCommit(false);
			
			System.out.print("아이디 입력 : ");
			String id = sc.nextLine();
			
			System.out.print("비밀번호 입력 : ");
			String pw = sc.nextLine();
			
			System.out.print("이름 입력 : ");
			String name = sc.nextLine();
			
			String sql = """
				UPDATE TB_USER
				SET USER_NAME = ?
				WHERE USER_ID = ?
				AND USER_PW = ?
				""";
			
			// PrepareStatement 생성
			pstmt = conn.prepareStatement(sql);
			
			// ?에 알맞은 값 세팅
			pstmt.setString(1, name);
			pstmt.setString(2, id);
			pstmt.setString(3, pw);
			
			// PreparedStatement를 이용하여 SQL 실행 시
			// 매개변수 자리에 아무것도 없어야함
			int result = pstmt.executeUpdate();
			
			// 성공시 성공메세지 + commit
			// 실패시 id 또는 pw 불일치 메세지 + rollback
			if(result > 0) {
				System.out.println("수정성공");
				conn.commit();
			} else {
				System.out.println("id 또는 pw 불일치");
				conn.rollback();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn  != null) conn.close();
				if(sc    != null) sc.close();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

}
