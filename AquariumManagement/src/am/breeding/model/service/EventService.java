package am.breeding.model.service;

import static am.common.JDBCTemplate.*;
import java.sql.Connection;

import am.breeding.model.dao.BreedingDAO;
import am.breeding.model.dao.EventDAO;
import am.breeding.model.vo.Event;

public class EventService {
	private BreedingDAO bDao = new BreedingDAO();
	private EventDAO eDao = new EventDAO();
	
	
	/** 이벤트 등록 서비스
	 * @param event
	 * @return result
	 * @throws Exception
	 */
	public int insertEvent(Event event) throws Exception{
		Connection conn = getConnection();
		
		int result = eDao.insertEvent(conn, event);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		return result;
	}


	/** 이벤트 수정 서비스
	 * @param eventNo
	 * @param content
	 * @return result
	 * @throws Exception
	 */
	public int updateEvent(int eventNo, String content) throws Exception {
		Connection conn = getConnection();
		
		int result = eDao.updateEvent(conn, eventNo, content);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		return result;
	}
	
	
	/** 이벤트 삭제 서비스
	 * @param eventNo
	 * @param content
	 * @return result
	 * @throws Exception
	 */
	public int deleteEvent(int eventNo) throws Exception {
		Connection conn = getConnection();
		
		int result = eDao.deleteEvent(conn, eventNo);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		return result;
	}

	
	
}
