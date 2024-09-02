package edu.kh.todolist.dao;

import static edu.kh.todolist.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import edu.kh.todolist.common.JDBCTemplate;
import edu.kh.todolist.dto.Todo;

public class TodoListDaoImpl implements TodoListDao{
	
	// 필드
		// JDBC 객체 참조 변수 + Properties 참조 변수 선언
		private Statement stmt;
		private PreparedStatement pstmt;
		private ResultSet rs;
		
		private Properties prop; 
		// ->  K,V가 모두 String인 Map, 파일 입출력이 쉬움
		
		
		// 기본 생성자
		public TodoListDaoImpl() {
			
			// 객체 생성 시 
			// 외부에 존재하는 sql.xml 파일을 읽어와
			// prop에 저장
			
			try {
				String filePath = 
						JDBCTemplate.class.getResource("/edu/kh/todolist/sql/sql.xml").getPath();
				
				// 지정된 경로의 XML 파일 내용을 읽어와
				// Properties 객체에 K:V 세팅
				prop = new Properties();
				prop.loadFromXML(new FileInputStream(filePath));
				
			}catch (Exception e) {
				e.printStackTrace();
			}
		}


	@Override
	public List<Todo> allTodoList(Connection conn) throws Exception {
		List<Todo> todoLsit = new ArrayList<Todo>();
		
		try {
			String sql = prop.getProperty("allList");
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while( rs.next() ) {
				String listNo = rs.getString("LIST_NO");
				String title = rs.getString("TODO_TITLE");
				String detail = rs.getString("TODO_DETAIL");
				String flag = rs.getString("COMPLETE");
				boolean complete = false;
				if(flag.equals("O")) complete = true;
				String regDate = rs.getString("REG_DATE");
				String color = rs.getString("COLOR");
				
				Todo todo = new Todo(listNo, title, detail, complete, regDate, color);
				todoLsit.add(todo);
				
			}
			
		} finally {
			close(rs);
			close(stmt);
		}
		
		return todoLsit;
	}


	/**Todo추가
	 * @param title : 입력할 TODO_TITLE
	 * @param detail : 입력할 TODO_DETAIL
	 * @return 1 || 0
	 */
	@Override
	public int insertTodo(String title, String detail, Connection conn) throws Exception {
		
		int result = 0;
		
		try {
			String sql = prop.getProperty("insertTodo");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, detail);
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}


	/** PK번호에 해당하는 Todo 반환
	 * @param index : 검색할 PK번호
	 * @return Todo
	 * @throws Exception
	 */
	@Override
	public Todo selectListNo(Connection conn, String listNo) throws Exception {
		Todo todo = new Todo();
		
		try {
			String sql = prop.getProperty("selectListNo");
			sql = sql + listNo;
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				String title = rs.getString("TODO_TITLE");
				String detail = rs.getString("TODO_DETAIL");
				String flag = rs.getString("COMPLETE");
				boolean complete = false;
				if(flag.equals("O")) complete = true;
				String regDate = rs.getString("REG_DATE");
				String color = rs.getString("COLOR");
				
				todo.setListNo(listNo+"");
				todo.setTitle(title);
				todo.setDetail(detail);
				todo.setComplete(complete);
				todo.setRegDate(regDate);
				todo.setColor(color);
				
			}
			
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return todo;
	}

	/**할일 여부를 변경
	 * @param listNo
	 * @return boolean
	 */
	@Override
	public int completeTodo(Connection conn, String listNo, boolean com) throws Exception {
		int result = 0;
		
		try {
			String complete = "";
			if(com) {
				complete = "X";
			} else {
				complete = "O";
			}
			String sql = prop.getProperty("updateComplete");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, complete);
			pstmt.setString(2, listNo);
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
			
		}
		
		return result;
	}

	
	/** Todo 제목, 상세내용 수정
	 * @param listNo
	 * @param title
	 * @param detail
	 * @return 1 || 0
	 */
	@Override
	public int updateTodo(Connection conn, Todo todo) throws Exception {
		int result = 0;
		
		try {
			String sql = prop.getProperty("updateTodo");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, todo.getTitle());
			pstmt.setString(2, todo.getDetail());
			pstmt.setString(3, todo.getColor());
			pstmt.setString(4, todo.getListNo());
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}


	/** 전달받은 번호의 리스트를 지우고 결과 반환
	 * @param listNo
	 * @return 1 || 0
	 */
	@Override
	public int deleteTodo(Connection conn, String listNo) throws Exception {
		int result = 0;
		
		try {
			String sql = prop.getProperty("deleteTodo");
			sql = sql + listNo;
			stmt = conn.createStatement();
			result = stmt.executeUpdate(sql);
			
		} finally {
			close(stmt);
			
		}
		
		return result;
	}

	/** 모든 리스트를 완료여부로 정렬해서 반환
	 * @param order
	 * @return
	 */
	@Override
	public List<Todo> allTodoList(Connection conn, String order) throws Exception {
		List<Todo> todoList = new ArrayList<Todo>();
		try {
			String sql = prop.getProperty("allListOderBy");
			sql = sql + order;
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while( rs.next() ) {
				String listNo = rs.getString("LIST_NO");
				String title = rs.getString("TODO_TITLE");
				String detail = rs.getString("TODO_DETAIL");
				String flag = rs.getString("COMPLETE");
				boolean complete = false;
				if(flag.equals("O")) complete = true;
				String regDate = rs.getString("REG_DATE");
				String color = rs.getString("COLOR");
				
				Todo todo = new Todo(listNo, title, detail, complete, regDate, color);
				todoList.add(todo);
				
			}
			
		} finally {
			close(rs);
			close(stmt);
		}
		
		return todoList;
	}
	
	
	
	
	

}