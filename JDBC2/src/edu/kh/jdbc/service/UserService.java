package edu.kh.jdbc.service;

// import static : 지정된 경로에 존재하는 "static" 구문을 모두 얻어와
// 클래스명.메서드명()이 아닌 메서드명()만 작성해도 호출 가능함
import static edu.kh.jdbc.common.JDBCTemplate.*;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.kh.jdbc.common.JDBCTemplate;
import edu.kh.jdbc.dao.UserDao;
import edu.kh.jdbc.dto.User;

// Service : 비즈니스 로직 처리
// - DB에 CRUD 후 결과 반환받기
//  + DML 성공 여부에 따른 트랜잭션 제어처리(commit, rollback)
// --> commit/rollback에는 Connection 객체가 필요하기 때문에
//		Connection 객체를 Service에서 생성 후
//		Dao에 전달하는 형식의 코드를 작성하게 됨

public class UserService {
	
	// 필드
	private UserDao dao = new UserDao();
	
	// 1. Connection 생성
	// 2. 데이터 가공(할게없으면 넘어감)
	// 3. DB접근을 위한 DAO 메서드 호출 및 결과반환(할게없으면 넘어감)
	// 4. INSERT 수행 결과에 따라 트랜잭션 제어처리(할게없으면 넘어감)
	// 5. 커넥션 반환하기
	
	
	
	// 메서드
	
	/**
	 * 전달받은 ID와 일치하는 유저정보 반환
	 * @param input 입력된 아이디
	 * @return 아이디가 일치하는 회원 정보, 없으면 null
	 */
	public User selectId(String input) {
		
		// 커넥션 생성(JDBCTEMPLATE에 있는 필드를 이용하여 객체 생성)
		Connection conn = JDBCTemplate.getConnection();
		
		// Dao 메서드 호출 후 결과 반환받기
		User user = dao.selectId(conn, input);
		
		// 다쓴 커넥션 닫기
		// SELECT는 COMMIT / ROLLBACK 안해도 됨
		JDBCTemplate.close(conn);
		
		return user;
	}





	/**
	 * User 등록 서비스
	 * @param user : 입력받은 id,pw,name
	 * @return 삽입 성공한 결과 행의 개수 0 or 1
	 * @throws SQLException 
	 */
	public int insertUser(User user) throws SQLException {
		
		// 1. Connection 생성
//		Connection conn = JDBCTemplate.getConnection();
		// --> import static 함
		Connection conn = getConnection();
		
		// 2. 데이터 가공(할게없으면 넘어감)
		
		// 3. DB접근을 위한 DAO 메서드 호출 및 결과반환(할게없으면 넘어감)
		// 결과(삽입 성공한 행의 개수, int) 반환받기
		int result = dao.insertUser(conn, user);
		
		// 4. INSERT 수행 결과에 따라 트랜잭션 제어처리
		//(커넥션을 서비스에서 만든 이유 : 트랜잭션 제어처리(commit, rollback))
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		// 5. 커넥션 반환하기
		close(conn);
		
		return result;
	}
	
	/**
	 * TB_USER 전체 검색
	 * @return List<User> User객체들이 담겨있는 ArrayList
	 * @throws SQLException
	 */
	public List<User> selectAll() throws SQLException {
		// 1. Connection 생성
		Connection conn = getConnection();
		
		// 2. 데이터 가공(할게없으면 넘어감)
		// 3. DB접근을 위한 DAO 메서드 호출 및 결과반환(할게없으면 넘어감)
		List<User> userList = dao.selectAll(conn); 
		// 4. INSERT 수행 결과에 따라 트랜잭션 제어처리(할게없으면 넘어감)
		// 5. 커넥션 반환하기
		close(conn);
		return userList;
	}




	/**
	 * id를 넘겨와 중복검사
	 * @param inputId : 입력받은 ID
	 * @return true : 중복, false : 없음
	 */
	public boolean checkId(String inputId) throws SQLException {
		
		// 1. Connection 생성
		Connection conn = getConnection();
		// 2. 데이터 가공(할게없으면 넘어감)
		boolean result = false;
		
		// ID 중복검사
		
		result = dao.searchSameId(conn, inputId);
		
		// 5. 커넥션 반환하기
		close(conn);
		
		return result;
	}




	/**
	 * 검색할 이름을 전달받아 전달받은 단어가 포함된 이름을 검색해서 결과 반환
	 * @param keyword : 전달받은 단어
	 * @return List<User> : 검색결과가 담긴 객체
	 */
	public List<User> selectName(String keyword) throws SQLException {
		// 1. Connection 생성
		Connection conn = getConnection();
		
		// 2. 데이터 가공(할게없으면 넘어감)
		List<User> searchList = new ArrayList<User>();
		
		// 3. DB접근을 위한 DAO 메서드 호출 및 결과반환(할게없으면 넘어감)
		searchList = dao.selectName(conn, keyword);
		
		// 4. INSERT 수행 결과에 따라 트랜잭션 제어처리(할게없으면 넘어감)
		// 5. 커넥션 반환하기
		close(conn);
		
		return searchList;
	}




	/**
	 * 검색할 유저 PK번호를 입력받아 일치하는 유저객체 반환
	 * @param userNo : 검색할 번호
	 * @return User : 검색결과를 담은 User객체
	 */
	public User selectNo(int userNo) throws SQLException {
		Connection conn = getConnection();
		User user = dao.selectNo(conn, userNo);
		close(conn);
		return user;
	}




	/**
	 * 전달받은 유저객체의 정보와 일치하는 USER를 DB에서 삭제
	 * @param user
	 * @return
	 * @throws SQLException 
	 */
	public int deleteUser(User user) throws SQLException {
		Connection conn = getConnection();
		int result = dao.deleteUser(conn, user);
		if(result>0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}




	/**
	 * USER_NO, 변경할 USER_NAME을 입력받아 정보 수정
	 * @param userNo : 변경할 USER의 USER_NO
	 * @param inputName : 새로 입력할 USER_NAME
	 * @return int 변경된 행의 갯수
	 */
	public int updateUserName(int userNo, String inputName) throws SQLException {
		Connection conn = getConnection();
		int result = dao.updateUserName(conn, userNo, inputName);
		if(result>0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}



	//==============================================================================
	
	/** userList에 있는 모든 user INSERT하기
	 * @param userList
	 * @return result : 삽입된 행의 개수
	 * @throws SQLException 
	 * @throws Exception
	 */
	public int multiInsertUser(List<User> userList) throws SQLException {
		
		Connection conn = getConnection();
		
		// 다중 INSERT 방법
		// 1) SQL을 이용한 다중 INSERT
		// 2) Java 반복문을 이용한 다중 INSERT  (이거 사용!!)
		
		int count = 0;
		
		
		
		for(User user : userList) {
			int result = insertUser(user);
			count += result;
		}
		
		/**
		 * 강제실패코드
		 */
//		count--;
		
		if(count == userList.size()) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		
		return count;
	}





	public int idCheck(String userId) throws SQLException {
		
		if(checkId(userId)) return 1;
		
		return 0;
	}





}
