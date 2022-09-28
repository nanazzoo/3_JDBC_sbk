package am.parameter.model.service;

import static am.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import am.parameter.model.dao.ParameterDAO;
import am.parameter.vo.Parameter;
import am.todo.vo.Todo;

public class ParameterService {
	private ParameterDAO dao = new ParameterDAO();

	/** 파라미터 개수 조회 서비스
	 * @param parameter
	 * @return int
	 * @throws Exception
	 */
	public int countParemeter(Parameter parameter) throws Exception {
		Connection conn = getConnection();
		
		int result = dao.countParameter(conn, parameter);
		
		close(conn);
		
		return result;
	}

	/** 최근 물성치 조회 서비스
	 * @param parameter
	 * @return parameterList
	 * @throws Exception
	 */
	public Parameter selectCParameter(Parameter parameter) throws Exception{
		Connection conn = getConnection();
		
		Parameter cParameter = dao.selectCParameter(conn, parameter);
		
		close(conn);
		
		return cParameter;
	}
	

	/** 직전 물성치 조회 서비스
	 * @param parameter
	 * @return pParameter
	 * @throws Exception
	 */
	public Parameter selectPParameter(Parameter parameter) throws Exception {
		Connection conn = getConnection();
		
		Parameter pParameter = dao.selectPParameter(conn, parameter);
		
		close(conn);
		
		return pParameter;
	}

	/**
	 * @param parameter
	 * @return
	 * @throws Exception
	 */
	public int insertParameter(Parameter parameter) throws Exception {
		Connection conn = getConnection();
		
		int result = dao.insertParameter(conn, parameter);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}

	/** 물성치 삭제 서비스
	 * @param parameter
	 * @return result
	 * @throws Exception
	 */
	public int deleteParameter(Parameter parameter) throws Exception {
		Connection conn = getConnection();
		
		int result = dao.deleteParameter(conn, parameter);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}
	
	
	/** 전체 물성치 삭제 서비스
	 * @param parameter
	 * @return result
	 * @throws Exception
	 */
	public int deleteAllParameter(Parameter parameter) throws Exception {
		Connection conn = getConnection();
		
		int result = dao.deleteAllParameter(conn, parameter);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}


}
