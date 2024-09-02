package edu.kh.jdbc.service;

import java.util.List;

import edu.kh.jdbc.dto.User;

public interface UserService {

	/** 사용자 등록
	 * @param user
	 * @return result : 1 || 0
	 * @throws Exception
	 */
	int insertUser(User user) throws Exception;

	
	/** 아이디 중복 여부 확인
	 * @param userId
	 * @return result(1:중복, 0: 중복X)
	 * @throws Exception
	 */
	int idCheck(String userId) throws Exception;


	/** 로그인
	 * @param userId
	 * @param userPw
	 * @return loginUser
	 * @throws Exception
	 */
	User login(String userId, String userPw) throws Exception;


	/**전체조회
	 * @return 전체목록
	 * @throws Exception
	 */
	List<User> selectAll() throws Exception;


	/** 아이디 검색
	 * @param searchId
	 * @return 조회한 결과 리스트
	 * @throws Exception 
	 */
	List<User> searchId(String searchId) throws Exception;
	
	/** 아이디 검색 + 정렬
	 * @param searchId
	 * @param num	2: ID 오름차순, 3: 이름 오름차순
	 * @return
	 * @throws Exception
	 */
	List<User> searchId(String searchId, int num) throws Exception;


	/** 유저 PK USER_NO 조회
	 * @param userNo
	 * @return User
	 * @throws Exception
	 */
	User selectUser(String userNo) throws Exception;


	/** 번호를 받아서삭제해줌
	 * @param userNo
	 * @return 0 || 1
	 * @throws Exception
	 */
	int deleteUser(int userNo) throws Exception;


	/** User no, pw, name을 입력받아 수정
	 * @param user no: 수정할 유저, pw&name : 수정할 정보
	 * @return int
	 * @throws Exception
	 */
	int updateUser(User user) throws Exception;


	
	
	

}