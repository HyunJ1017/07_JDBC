package edu.kh.jdbc.dao;

import static edu.kh.jdbc.jdbc.JDBCTemplate.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.kh.jdbc.jdbc.JDBCTemplate;
import edu.kh.jdbc.dto.User;

// DAO (Data Access Object) : 데이터가 저장된 곳에 접근하는 용도의 객체
// -> DB에 접근하여 Java에서 원하는 결과를 얻기 위해
//   SQL을 수행하고 결과 반환받는 역할

public class UserDao {
	
	// 필드
	// - DB 접근 관련한 JDBC 객체 참조형 변수를 미리 선언
	//	private Connection conn = null; -> Service로 넘김
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	// 1. 결과 저장용 변수 선언
	// 2. SQL 작성
	// 3. (Prepared)Statement 생성
	// 4. placeholder에 값 대입
	// 5. SQL(INSERT) 수행(excuteUpdate()) 후 결과(행의갯수|조회값) 반환
	// 6. 사용한 JDBC 객체 자원 반환
	// 7. 결과 저장용 변수에 저장된 값을 반환
	
	
	/**전달받은 Connection을 이용해 DB에 접근하여
	 * 전달받은 아이디와 일치하는 User 정보 조회하기
	 * @param conn  : Service에서 생성한 Connection 객체
	 * @param input : view에서 입력받은 ID
	 * @return
	 */
	public User selectId(Connection conn, String input) {
		
		User user = null; //결과 저장용 변수
		
		try {
			String sql = "SELECT * FROM TB_USER WHERE USER_ID = ?";
			
			// PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			
			// placeholder에 알맞은 값 대입
			pstmt.setString(1, input);
			
			// SQL 수행 후 결과반환받기
			rs = pstmt.executeQuery();
			
			// 조회 결과가 있을경우
			// 중복되는 아이디(Unique)가 없을 경우 1행만 조회되기 때문에
			// while보대 if를 사용하는게 효과적
			if(rs.next()) {
				
				// 각 컬럼의 값 얻어오기
				int userNo      = rs.getInt("USER_NO");
				String userId   = rs.getString("USER_ID");
				String userPw   = rs.getString("USER_PW");
				String userName = rs.getString("USER_NAME");
				
				// java.sql.Date 활용
				Date enrollDate = rs.getDate("ENROLL_DATE");
				
				// 조회된 컬럼값을 이용해 User 객체 생성
				user = new User(userNo, userId, userPw, userName, enrollDate.toString());
				
			}
			
		} catch(Exception e) {
			e.printStackTrace();
			
		}finally {
			// 사용한 JDBC 객체 자원 반환 close
			JDBCTemplate.close(rs);
			JDBCTemplate.close(pstmt);
			
			// Connection 객체는 Service에서 close
		}
		
		return user; // 결과반환 (생성된 User 또는 null)
	}
	
	
	/**
	 * User 등록하는 DAO 메서드
	 * @param conn : DB 연결 정보가 담겨있는 객체
	 * @param user : 입력받은 id,pw,name
	 * @return result : INSERT 결과 행의 갯수 
	 */
	public int insertUser(Connection conn, User user) throws SQLException{
		
		// SQL 수행 중 발생하는 예외를
		// catch로 처리하지 않고, throws를 이용해서 호출부로 던져 처리
		// -> catch문이 필요 없다!!
		
		// 1. 결과 저장용 변수 선언
		int result = 0;
		
		try {
			// 2. SQL 작성
			String sql = """
			INSERT INTO TB_USER
			VALUES(SEQ_USER_NO.NEXTVAL, ?, ?, ?, DEFAULT)
			""";
			
			// 3. PreparedStatement 생성
			pstmt = conn.prepareStatement(sql);
			
			// 4. placeholder에 값 대입
			pstmt.setString(1, user.getUserId());
			pstmt.setString(2, user.getUserPw());
			pstmt.setString(3, user.getUserName());
			
			// 5. SQL(INSERT) 수행(excuteUpdate()) 후 삽입된 행의갯수 반환
			result = pstmt.executeUpdate();
			
		} finally {
			// 6. 사용한 JDBC 객체 자원 반환
			close(pstmt);
			
		}
		
		// 7. 결과 저장용 변수에 저장된 값을 반환
		return result;
	}
	
	
	
	/**
	 * 유저 전체검색
	 * @param conn
	 * @return
	 */
	public List<User> selectAll(Connection conn) throws SQLException {
		// 1. 결과 저장용 변수 선언
		List<User> userList = new ArrayList<User>();
		
		// 2. SQL 작성
		String sql = """
		SELECT USER_NO, USER_ID, USER_PW, USER_NAME, TO_CHAR(ENROLL_DATE, 'YY"년" MM"월" DD"일"') "DATE"
		FROM TB_USER
		ORDER BY USER_NO ASC""";
		
		try {
			// 3. Statement 생성
			stmt = conn.createStatement();
			// 4. placeholder에 값 대입
			// 5. SQL(SELECT) 수행(executeQuery()) 후 결과(행의갯수|조회값) 반환
			rs = stmt.executeQuery(sql);
			
			// 5-1. 결과값을 결과 저장용 변수에 담기
			// result set을 List에 옮겨담는이유
			// - 1. List가 사용이 편해서
			//	 -> 호환되는 곳도 많음(jsp, thymeleaf 등)
			// - 2. 사용된 ResultSet은 DAO에서 close 되기 때문
			while(rs.next()) {
				
				int userNo = rs.getInt("USER_NO");
				String userId = rs.getString("USER_ID");
				String userPw = rs.getString("USER_PW");
				String userName = rs.getString("USER_NAME");
				
				String enrollDate = rs.getString("DATE");
				// - java.sql.Date 타입으로 값을 저장하지 않는 이유!
				//  -> TO_CHAR()를 이용해서 문자열로 변환했기 때문!
				
				User user = new User(userNo, userId, userPw, userName, enrollDate);
				userList.add(user);
				
			}
		
		} finally {
			// 6. 사용한 JDBC 객체 자원 반환
			close(rs);
			close(pstmt);
		}
		// 7. 결과 저장용 변수에 저장된 값을 반환
		return userList;
	}

	/**
	 * 이름(전달받은 단어를 포함한)검색
	 * @param conn
	 * @param keyword 검색할 이름
	 * @return
	 */
	public List<User> selectName(Connection conn, String keyword) throws SQLException {
		// 1. 결과 저장용 변수 선언
		// 2. SQL 작성
		// 3. (Prepared)Statement 생성
		// 4. placeholder에 값 대입
		// 5. SQL(INSERT) 수행(excuteUpdate()) 후 결과(행의갯수|조회값) 반환
		// 6. 사용한 JDBC 객체 자원 반환
		// 7. 결과 저장용 변수에 저장된 값을 반환
		
		List<User> serchList = new ArrayList<User>();
		
		String sql = """
		SELECT USER_NO, USER_ID, USER_PW, USER_NAME, TO_CHAR(ENROLL_DATE, 'YY\"년\" MM\"월\" DD\"일\"') ENROLL_DATE
		FROM TB_USER
		WHERE USER_NAME LIKE '%' || ? || '%'
		ORDER BY USER_NO ASC
		""";
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, keyword);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				int userNo = rs.getInt("USER_NO");
				String userId = rs.getString("USER_ID");
				String userPw = rs.getString("USER_PW");
				String userName = rs.getString("USER_NAME");
				
				String enrollDate = rs.getString("ENROLL_DATE");
				// - java.sql.Date 타입으로 값을 저장하지 않는 이유!
				//  -> TO_CHAR()를 이용해서 문자열로 변환했기 때문!
				
				User user = new User(userNo, userId, userPw, userName, enrollDate);
				serchList.add(user);
				
			}
			
		} finally {
			close(rs);
			close(pstmt);
		}
		return serchList;
	}

	/**
	 * 중복검사
	 * @param newId 검색할 아이디
	 * @return 일치하는 아이디가 있으면 true 없으면 false
	 */
	public boolean searchSameId(Connection conn, String newId) throws SQLException {
		boolean result = false;
		String sql = """
		SELECT USER_NO
		FROM TB_USER
		WHERE USER_ID = ?
		ORDER BY USER_NO ASC
		""";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, newId);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				result = true;
				System.out.println("중복아이디존재");
			}
			
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return result;
	}

	
	/**
	 * USER_NO 검색 후 일치하는 정보를 User객체에 담아 반환
	 * @param conn
	 * @param userNo
	 * @return
	 * @throws SQLException
	 */
	public User selectNo(Connection conn, int userNo) throws SQLException {
		User user = null;
		String sql = """
		SELECT USER_NO, USER_ID, USER_PW, USER_NAME, TO_CHAR(ENROLL_DATE, 'YY\"년\" MM\"월\" DD\"일\"') ENROLL_DATE
		FROM TB_USER
		WHERE USER_NO = ?
		ORDER BY USER_NO ASC
		""";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userNo);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int userNo1 = rs.getInt("USER_NO");
				String userId = rs.getString("USER_ID");
				String userPw = rs.getString("USER_PW");
				String userName = rs.getString("USER_NAME");
				String enrollDate = rs.getString("ENROLL_DATE");
				user = new User(userNo1, userId, userPw, userName, enrollDate);
			}
			
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return user;
	}

	
	
	/**
	 * 전달받은 유저객체의 정보와 일치하는 USER를 DB에서 삭제
	 * @param user
	 * @return
	 */
	public int deleteUser(Connection conn ,User user) throws SQLException {
		int result = 0;
		String sql = """
		DELETE
		FROM TB_USER
		WHERE USER_NO = ?
		""";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, user.getUserNo());
			result = pstmt.executeUpdate();
			
		}finally {
			close(pstmt);
			
		}
		
		return result;
	}

	/**
	 * USER_NO, 변경할 USER_NAME을 입력받아 정보 수정
	 * @param userNo : 변경할 USER의 USER_NO
	 * @param inputName : 새로 입력할 USER_NAME
	 * @return int 변경된 행의 갯수
	 */
	public int updateUserName(Connection conn, int userNo, String inputName) throws SQLException {
		int result = 0;
		String sql = """
		UPDATE TB_USER
		SET USER_NAME = ?
		WHERE USER_NO = ?
		""";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, inputName);
			pstmt.setInt(2, userNo);
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}


	
	
	
}
