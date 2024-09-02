package edu.kh.todolist.dao;

import java.sql.Connection;
import java.util.List;

import edu.kh.todolist.dto.Todo;

public interface TodoListDao {

	/** 모든 TODOLIST를 반환
	 * @return
	 */
	List<Todo> allTodoList(Connection conn) throws Exception ;
	
	/**Todo추가
	 * @param title : 입력할 TODO_TITLE
	 * @param detail : 입력할 TODO_DETAIL
	 * @return 1 || 0
	 */
	int insertTodo(String title, String detail, Connection conn) throws Exception ;

	
	/** PK번호에 해당하는 Todo 반환
	 * @param index : 검색할 PK번호
	 * @return Todo
	 * @throws Exception
	 */
	Todo selectListNo(Connection conn, String listNo) throws Exception ;

	/**할일 여부를 변경
	 * @param listNo
	 * @return boolean
	 */
	int completeTodo(Connection conn, String listNo, boolean com) throws Exception ;

	/** Todo 제목, 상세내용 수정
	 * @param listNo
	 * @param title
	 * @param detail
	 * @return 1 || 0
	 */
	int updateTodo(Connection conn, Todo todo) throws Exception ;

	/** 전달받은 번호의 리스트를 지우고 결과 반환
	 * @param listNo
	 * @return 1 || 0
	 */
	int deleteTodo(Connection conn, String listNo) throws Exception ;

	/** 모든 리스트를 완료여부로 정렬해서 반환
	 * @param order
	 * @return
	 */
	List<Todo> allTodoList(Connection conn, String order) throws Exception ;
	

	
	



}