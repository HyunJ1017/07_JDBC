package edu.kh.jdbc.dao;

import java.sql.Connection;
import java.util.List;

import edu.kh.jdbc.dto.User;

public interface UserDao {

	/** 사용자 등록
	 * @param conn
	 * @param user
	 * @return result
	 * @throws Exception
	 */
	int insertUser(Connection conn, User user) throws Exception;

	/** 아이디 중복 여부 확인
	 * @param conn
	 * @param userId
	 * @return result
	 * @throws Exception
	 */
	int idCheck(Connection conn, String userId) throws Exception;

	/** 로그인
	 * @param conn
	 * @param userId
	 * @param userPw
	 * @return loginUser
	 * @throws Exception
	 */
	User login(Connection conn, String userId, String userPw) throws Exception;

	/**전체선택
	 * @param conn
	 * @return 전체목록
	 */
	List<User> selectAll(Connection conn) throws Exception;

	/**검색어가 아이디에 포함된 사용자 조회
	 * @param conn
	 * @param searchId
	 * @return UserList
	 */
	List<User> searchId(Connection conn, String searchId) throws Exception;

	/**USER_NO 검색
	 * @param conn
	 * @param searchNo
	 * @return User
	 * @throws Exception
	 */
	User selectUser(Connection conn, int searchNo) throws Exception;

	
	/** 번호를 받아서 삭제해 줍니다
	 * @param conn
	 * @param userNo PK
	 * @return 1 || 0
	 * @throws Exception
	 */
	int deleteUser(Connection conn, int userNo) throws Exception;

	
	/**
	 * @param conn
	 * @param user
	 * @return
	 * @throws Exception 
	 */
	int updateUser(Connection conn, User user) throws Exception;
	
	
	
	
	
	
	
	

}