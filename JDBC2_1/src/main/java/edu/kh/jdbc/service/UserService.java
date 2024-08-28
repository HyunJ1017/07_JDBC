package edu.kh.jdbc.service;

import static edu.kh.jdbc.jdbc.JDBCTemplate.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import edu.kh.jdbc.dao.UserDao;
import edu.kh.jdbc.dto.User;

public class UserService {
	
	UserDao dao = new UserDao();
	
	public UserService() {}
	
	public List<User> selectAll() throws SQLException {
		Connection conn = getConnection();
		List<User> userList = dao.selectAll(conn);
		close(conn);
		return userList;
	}
	
	public boolean searchSameId(String userId) throws SQLException {
		Connection conn = getConnection();
		boolean result = dao.searchSameId(conn, userId);
		close(conn);
		return result;
	}
	
	public int insertUser(User user) throws SQLException {
		Connection conn = getConnection();
		int result = dao.insertUser(conn, user);
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

}
