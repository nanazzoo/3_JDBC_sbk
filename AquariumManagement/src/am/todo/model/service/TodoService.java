package am.todo.model.service;

import static am.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import am.parameter.vo.Parameter;
import am.todo.model.dao.TodoDAO;
import am.todo.vo.Todo;

public class TodoService {
	private TodoDAO dao = new TodoDAO();

	
	
	/** 할 일 조회 서비스
	 * @param todo
	 * @return todoList
	 * @throws Exception
	 */
	public List<Todo> selectTodo(Todo todo) throws Exception {
		Connection conn = getConnection();
		
		List<Todo> todoList = dao.selectTodo(conn, todo);
		
		close(conn);
		
		return todoList;
	}
	
	
	
	/** 할 일 조회 서비스2
	 * @param todo
	 * @return todoList
	 * @throws Exception
	 */
	public List<Todo> selectTodo2(Todo todo) throws Exception {
		Connection conn = getConnection();
		
		List<Todo> todoList = dao.selectTodo2(conn, todo);
		
		close(conn);
		
		return todoList;
	}
	
	

	/** 가장 큰 todo 넘버 조회
	 * @param todo
	 * @return maxTodoNo
	 * @throws Exception
	 */
	public int maxTodoNo(Todo todo) throws Exception{
		Connection conn = getConnection();
		
		int maxTodoNo = dao.maxTodoNo(conn, todo);
		
		close(conn);
		
		return maxTodoNo;
	}
	
	
	

	/** 할 일 등록
	 * @param todo
	 * @param maxTodoNo
	 * @return result
	 * @throws Exception
	 */
	public int insertTodo(Todo todo, int maxTodoNo)  throws Exception{
		Connection conn = getConnection();
		
		int result = dao.insertTodo(conn, todo, maxTodoNo);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}
	
	
	

	/** 할 일 완료 서비스
	 * @param todo
	 * @return result
	 * @throws Exception
	 */
	public int completeTodo(Todo todo) throws Exception{
		Connection conn = getConnection();
		
		int result = dao.completeTodo(conn, todo);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}

	/** 할 일 삭제 서비스
	 * @param todo
	 * @return result
	 * @throws Exception
	 */
	public int deleteTodo(Todo todo) throws Exception {
		Connection conn = getConnection();
		
		int result = dao.deleteTodo(conn, todo);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}
	
	/** 전체 할 일 삭제 서비스
	 * @param todo
	 * @return result
	 * @throws Exception
	 */
	public int deleteAllTodo(Todo todo) throws Exception {
		Connection conn = getConnection();
		
		int result = dao.deleteAllTodo(conn, todo);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}

	
	
	/** 완료된 할 일 조회 서비스
	 * @param todo
	 * @return todoList
	 * @throws Exception
	 */
	public List<Todo> selectComTodo(Todo todo) throws Exception {
		Connection conn = getConnection();
		
		List<Todo> todoList = dao.selectComTodo(conn, todo);
		
		close(conn);
		
		return todoList;
	}



	/** 지연된 할 일 조회 서비스
	 * @param memberNo
	 * @return todoList
	 * @throws Exception
	 */
	public List<Todo> selectDelayedTodo(int memberNo) throws Exception{
		Connection conn = getConnection();
		
		List<Todo> todoList = dao.selectDelayedTodo(conn, memberNo);
		
		close(conn);
		
		return todoList;
	}
	
	/** 지연된 할 일 조회 서비스
	 * @param memberNo
	 * @return todoList
	 * @throws Exception
	 */
	public List<Todo> selectDelayedTodo(Todo todo) throws Exception{
		Connection conn = getConnection();
		
		List<Todo> todoList = dao.selectDelayedTodo2(conn, todo);
		
		close(conn);
		
		return todoList;
	}
	
	
	

}
