package edu.kh.todolist.service;

import java.util.List;
import java.util.Map;

import edu.kh.todolist.dto.Todo;

//Service :  데이터 가공, 로직 처리 등 기능(비즈니스 로직)을 제공하는 역할
public interface TodoListService {

	/**완료된 갯수, TodoList 를 담은 Map
	 * @return todoList
	 *  "completeCount" : 완료된 갯수
	 *  "todoList" : allTodoList
	 */
	Map<String, Object> todoListFullView() throws Exception ;

	/**Todo추가
	 * @param title : 입력할 TODO_TITLE
	 * @param detail : 입력할 TODO_DETAIL
	 * @return 1 || 0
	 */
	int insertTodo(String title, String detail) throws Exception ;

	/** PK번호에 해당하는 Todo 반환
	 * @param index : 검색할 PK번호
	 * @return Todo
	 * @throws Exception
	 */
	Todo selectListNo(String listNo) throws Exception ;

	/** 할일 여부를 변경
	 * @param listNo
	 * @return boolean
	 */
	boolean completeTodo(String listNo, String complete) throws Exception ;

	/** Todo 제목, 상세내용 수정
	 * @param listNo
	 * @param title
	 * @param detail
	 * @return 1 || 0
	 */
	int updateTodo(String listNo, String title, String detail, String color) throws Exception ;

	/** 전달받은 번호의 리스트를 지우고 결과 반환
	 * @param listNo
	 * @return 1 || 0
	 */
	int deleteTodo(String listNo) throws Exception ;

	
	/** 모든 리스트를 완료여부로 정렬해서 반환
	 * @param order
	 * @return
	 */
	List<Todo> todoListFullViewOrderByComplete(String order) throws Exception ;





	
	
	

}