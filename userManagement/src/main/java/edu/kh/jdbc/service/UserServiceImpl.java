package edu.kh.jdbc.service;

import static edu.kh.jdbc.common.JDBCTemplate.*;

import java.sql.Connection;

import edu.kh.jdbc.dao.UserDao;
import edu.kh.jdbc.dao.UserDaoImpl;
import edu.kh.jdbc.dto.User;

public class UserServiceImpl implements UserService {
	
	// 필드
	private UserDao dao = new UserDaoImpl();

	// method
	@Override
	public int insertUser(User user) throws Exception {
		
		// 1. Connection 생성
		Connection conn = getConnection();
		
		// 2. 데이터 조정(생략)
		// 3. DAO 메서드 호출 후 결과반환
		int result = dao.insertUser(conn, user);
		
		// 4. DML 수행 -> 트랜잭션 처리
		if(result>0) commit(conn);
		else		 rollback(conn);
		
		// 5. 사용 완료된 JDBC 반환
		close(conn);
		
		return result;
	}

}
