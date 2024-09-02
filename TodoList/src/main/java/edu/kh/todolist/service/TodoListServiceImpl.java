package edu.kh.todolist.service;

import static edu.kh.todolist.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.kh.todolist.dao.TodoListDao;
import edu.kh.todolist.dao.TodoListDaoImpl;
import edu.kh.todolist.dto.Todo;

public class TodoListServiceImpl implements TodoListService{

	private TodoListDao dao = new TodoListDaoImpl();

	/**완료된 갯수, TodoList 를 담은 Map
	 * @return todoList
	 *  "completeCount" : 완료된 갯수
	 *  "todoList" : allTodoList
	 */
	@Override
	public Map<String, Object> todoListFullView() throws Exception {
		
		Connection conn = getConnection();
		
		List<Todo> todoList = dao.allTodoList(conn);
		
		int count = 0;
		
		for(Todo todo : todoList) {
			if(todo.isComplete()) count++;
		}
		
		Map<String, Object> mainList = new HashMap<String, Object>();
		
		mainList.put("completeCount", count);
		mainList.put("todoList", todoList);
		
		close(conn);
		
		return mainList;
	}
	
	/**Todo추가
	 * @param title : 입력할 TODO_TITLE
	 * @param detail : 입력할 TODO_DETAIL
	 * @return 1 || 0
	 */
	@Override
	public int insertTodo(String title, String detail) throws Exception {
		Connection conn = getConnection();
		int result = dao.insertTodo(title, detail, conn);
		if(result>0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	/** PK번호에 해당하는 Todo 반환
	 * @param index : 검색할 PK번호
	 * @return Todo
	 * @throws Exception
	 */
	@Override
	public Todo selectListNo(String listNo) throws Exception {
		Connection conn = getConnection();
		Todo todo = dao.selectListNo(conn, listNo);
		close(conn);
		return todo;
	}

	/**할일 여부를 변경
	 * @param listNo
	 * @return boolean
	 */
	@Override
	public boolean completeTodo(String listNo, String complete) throws Exception {
		Connection conn = getConnection();
		boolean com = true;
		if(complete.equals("true")) {
			com = true;
		} else if(complete.equals("false")) {
			com = false;
		}
		int col = dao.completeTodo(conn, listNo, com);
		boolean result = false;
		if(col>0) {
			commit(conn);
			result = true;
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	/** Todo 제목, 상세내용 수정
	 * @param listNo
	 * @param title
	 * @param detail
	 * @return 1 || 0
	 */
	@Override
	public int updateTodo(String listNo, String title, String detail, String color) throws Exception {
		Connection conn = getConnection();
		
		Todo todo = new Todo();
		todo.setListNo(listNo);
		todo.setTitle(title);
		todo.setDetail(detail);
		todo.setColor(color);
		
		int result = dao.updateTodo(conn, todo);
		if(result>0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	/** 전달받은 번호의 리스트를 지우고 결과 반환
	 * @param listNo
	 * @return 1 || 0
	 */
	@Override
	public int deleteTodo(String listNo) throws Exception {
		Connection conn = getConnection();
		int result = dao.deleteTodo(conn, listNo);
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	/** 모든 리스트를 완료여부로 정렬해서 반환
	 * @param order
	 * @return
	 */
	@Override
	public List<Todo> todoListFullViewOrderByComplete(String order) throws Exception {
		Connection conn = getConnection();
		List<Todo> todoList = dao.allTodoList(conn, order);
		close(conn);
		return todoList;
	}
	
	
	
	
}