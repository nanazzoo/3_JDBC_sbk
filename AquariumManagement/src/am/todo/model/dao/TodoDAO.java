package am.todo.model.dao;

import static am.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import am.todo.vo.Todo;

public class TodoDAO {
	
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private Properties prop;
	
	
	public TodoDAO() {
		try {
			prop = new Properties();
			prop.loadFromXML(new FileInputStream("todo-query.xml"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/** 할일 조회 DAO
	 * @param conn
	 * @param todo
	 * @return todoList
	 * @throws Exception
	 */
	public List<Todo> selectTodo(Connection conn, Todo todo) throws Exception{
		List<Todo> todoList = new ArrayList<>();
		
		try {
			String sql = prop.getProperty("selectTodo");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, todo.getMemberNo());
			pstmt.setInt(2, todo.getTankNo());
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Todo todos = new Todo();
				
				todos.setTodoNo(rs.getInt(1));
				todos.setTodoContent(rs.getString(2));
				todos.setRegDate(rs.getString(3));
				todos.setTodoTerm(rs.getString(4));
				
				todoList.add(todos);	
			}
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return todoList;
	}
	
	
	/** 할일 조회 DAO2
	 * @param conn
	 * @param todo
	 * @return todoList
	 * @throws Exception
	 */
	public List<Todo> selectTodo2(Connection conn, Todo todo) throws Exception{
		List<Todo> todoList = new ArrayList<>();
		
		try {
			String sql = prop.getProperty("selectTodo2");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, todo.getMemberNo());
			pstmt.setInt(2, todo.getTankNo());
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Todo todos = new Todo();
				
				todos.setTodoNo(rs.getInt(1));
				todos.setTodoContent(rs.getString(2));

				
				todoList.add(todos);	
			}
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return todoList;
	}

	/** 가장 큰 todoNo 조회
	 * @param conn
	 * @param todo
	 * @return maxTodoNo
	 * @throws Exception
	 */
	public int maxTodoNo(Connection conn, Todo todo) throws Exception{
		int maxTodoNo = 0;
		
		try {
			String sql = prop.getProperty("maxTodoNo");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, todo.getMemberNo());
			pstmt.setInt(2, todo.getTankNo());
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				maxTodoNo = rs.getInt(1);
			}
			
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return maxTodoNo;
	}

	/** 할 일 추가
	 * @param conn
	 * @param todo
	 * @return result
	 * @throws Exception
	 */
	public int insertTodo(Connection conn, Todo todo, int maxTodoNo) throws Exception {
		int result = 0;
		
		try {
			String sql = prop.getProperty("insertTodo");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, todo.getMemberNo());
			pstmt.setInt(2, todo.getTankNo());
			
			if(maxTodoNo == 0) {
				maxTodoNo = 1;
				pstmt.setInt(3, maxTodoNo);
			}
			
			if(maxTodoNo > 0) {
				maxTodoNo = maxTodoNo+1;
				pstmt.setInt(3, maxTodoNo);
			}
			
			pstmt.setString(4, todo.getTodoContent());
			pstmt.setString(5, todo.getTodoTerm());
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	
	/** 할 일 완료 DAO
	 * @param conn
	 * @param todo
	 * @return result
	 * @throws Exception
	 */
	public int completeTodo(Connection conn, Todo todo) throws Exception {
		int result = 0;
		
		try {
		String sql = prop.getProperty("completeTodo");
		
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, todo.getMemberNo());
		pstmt.setInt(2, todo.getTankNo());
		pstmt.setInt(3, todo.getTodoNo());
		
		result = pstmt.executeUpdate();
		
	} finally {
		close(pstmt);
	}
	
	return result;
	}
	
	

	/** 할 일 삭제 DAO
	 * @param conn
	 * @param todo
	 * @return result
	 * @throws Exception
	 */
	public int deleteTodo(Connection conn, Todo todo) throws Exception {
		int result = 0;
		
		try {
		String sql = prop.getProperty("deleteTodo");
		
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, todo.getMemberNo());
		pstmt.setInt(2, todo.getTankNo());
		pstmt.setInt(3, todo.getTodoNo());
		
		result = pstmt.executeUpdate();
		
	} finally {
		close(pstmt);
	}
	
	return result;
	}
	
	
	/** 전체 할 일 삭제 DAO
	 * @param conn
	 * @param todo
	 * @return result
	 * @throws Exception
	 */
	public int deleteAllTodo(Connection conn, Todo todo) throws Exception {
		int result = 0;
		
		try {
			String sql = prop.getProperty("deleteAllTodo");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, todo.getMemberNo());
			pstmt.setInt(2, todo.getTankNo());
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	
	
	/** 완료된 할 일 조회
	 * @param conn
	 * @param todo
	 * @return todoList
	 * @throws Exception
	 */
	public List<Todo> selectComTodo(Connection conn, Todo todo) throws Exception{
		List<Todo> todoList = new ArrayList<>();
		
		try {
			String sql = prop.getProperty("selectComTodo");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, todo.getMemberNo());
			pstmt.setInt(2, todo.getTankNo());
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Todo todos = new Todo();
				
				todos.setTodoNo(rs.getInt(1));
				todos.setTodoContent(rs.getString(2));
				todos.setRegDate(rs.getString(3));
				
				todoList.add(todos);	
			}
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return todoList;
	}


	/** 지연된 할 일 조회 DAO
	 * @param conn
	 * @param memberNo
	 * @return todoList
	 * @throws Exception
	 */
	public List<Todo> selectDelayedTodo(Connection conn, int memberNo) throws Exception {
		List<Todo> todoList = new ArrayList<>();
		
		try {
			String sql = prop.getProperty("selectDelayedTodo");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, memberNo);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Todo todo = new Todo();
				todo.setTankNo(rs.getInt(1));
				todo.setTodoNo(rs.getInt(2));
				todo.setTodoContent(rs.getString(3));
				todo.setTodoTerm(rs.getString(4));
				
				todoList.add(todo);
			}
			
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return todoList;
	}
	
	
	
	
	/** 어항별 지연된 할 일 조회 DAO
	 * @param conn
	 * @param memberNo
	 * @return todoList
	 * @throws Exception
	 */
	public List<Todo> selectDelayedTodo2(Connection conn, Todo todo) throws Exception {
		List<Todo> todoList = new ArrayList<>();
		
		try {
			String sql = prop.getProperty("selectDelayedTodo2");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, todo.getMemberNo());
			pstmt.setInt(2, todo.getTankNo());
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				todo = new Todo();
				todo.setTankNo(rs.getInt(1));
				todo.setTodoNo(rs.getInt(2));
				todo.setTodoContent(rs.getString(3));
				todo.setTodoTerm(rs.getString(4));
				
				todoList.add(todo);
			}
			
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return todoList;
	}

}
