package am.breeding.model.dao;

import static am.common.JDBCTemplate.*;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import am.breeding.model.vo.Event;

public class EventDAO {
	
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Properties prop;
	
	public EventDAO() {
		try {
			prop = new Properties();
			prop.loadFromXML(new FileInputStream("event-query.xml"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 댓글 목록 조회 DAO
	 * @param conn
	 * @param memberNo
	 * @param breedingNo
	 * @return commentList
	 * @throws Exception
	 */
	public List<Event> selectEventList(Connection conn, int memberNo, int breedingNo) throws Exception {
		List<Event> eventList = new ArrayList<>();
		
		try {
			String sql = prop.getProperty("selectEventList");
			
			pstmt= conn.prepareStatement(sql);
			
			pstmt.setInt(1, memberNo);
			pstmt.setInt(2, breedingNo);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Event event = new Event();
				
				event.setBreedingNo(rs.getInt(1));
				event.setEventNo(rs.getInt(2));
				event.setEventContent(rs.getString(3));
				event.setRegDate(rs.getString(4));
				
				eventList.add(event);
			}
			
			
		} finally {
			close(rs);
			close(pstmt);
		}
			
		return eventList;
	}
	
	

	/** 이벤트 등록 DAO
	 * @param conn
	 * @param event
	 * @return result
	 * @throws Exception
	 */
	public int insertEvent(Connection conn, Event event) throws Exception{
		int result = 0;
		
		try {
			String sql = prop.getProperty("insertEvent");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, event.getMemberNo());
			pstmt.setInt(2, event.getBreedingNo());
			pstmt.setString(3, event.getEventContent());
			pstmt.setString(4, event.getRegDate());
			
			result = pstmt.executeUpdate();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	
	
	public int updateEvent(Connection conn, int eventNo, String content) throws Exception{
		int result = 0;
		
		try {
			String sql = prop.getProperty("updateEvent");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, content);
			pstmt.setInt(2, eventNo);
			
			result = pstmt.executeUpdate();
		} finally {
			close(pstmt);
		}
		
		return result;
		
	}
	
	
	public int deleteEvent(Connection conn, int eventNo) throws Exception{
		int result = 0;
		
		try {
			String sql = prop.getProperty("deleteEvent");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, eventNo);
			
			result = pstmt.executeUpdate();
		} finally {
			close(pstmt);
		}
		
		return result;
		
	}

}
