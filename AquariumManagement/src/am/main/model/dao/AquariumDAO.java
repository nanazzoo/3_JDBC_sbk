package am.main.model.dao;

import static am.common.JDBCTemplate.*;

import java.io.Closeable;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import am.member.vo.Member;
import am.tank.vo.Tank;


public class AquariumDAO {
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private Properties prop;
	
	
	 /**
	 * SQL 쿼리문 미리 얻어오기
	 */
	public AquariumDAO() {
		 try {
			 prop = new Properties();
			 
			 prop.loadFromXML(new FileInputStream("main-query.xml"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/** login DAO
	 * @param conn
	 * @param memberId
	 * @param memberPw
	 * @return loginMember
	 * @throws Exception
	 */
	public Member login(Connection conn, String memberId, String memberPw) throws Exception {
		Member loginMember = null;
		
		try {
			String sql = prop.getProperty("login");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			pstmt.setString(2, memberPw);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				loginMember = new Member(rs.getInt("MEMBER_NO"),
						memberId, rs.getString("MEMBER_NM"));
										
			}
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return loginMember;
	}


	/** 아이디 중복 검사 DAO
	 * @param conn
	 * @param memberId
	 * @return result
	 * @throws Exception
	 */
	public int idDupCheck(Connection conn, String memberId) throws Exception {
		int result = 0;
		
		try {
			String sql = prop.getProperty("idDupCheck");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);
			}
			
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return result;
	}


	
	/** signUp
	 * @param conn
	 * @param member
	 * @return result
	 * @throws Exception
	 */
	public int signUp(Connection conn, Member member) throws Exception {
		int result = 0;
		
		try {
			String sql = prop.getProperty("signUp");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getMemberPw());
			pstmt.setString(3, member.getMemberName());
			
			result = pstmt.executeUpdate();
			
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}



	/** 어항 번호, 이름 조회 DAO
	 * @param conn
	 * @param memberNo
	 * @return tankInfo
	 * @throws Exception
	 */
	public List<Tank> tankInfo(Connection conn, int memberNo) throws Exception {
		List<Tank> tankInfo = new ArrayList<>();
		
		try {
			String sql = prop.getProperty("tankInfo");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, memberNo);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Tank tank = new Tank();
				
				tank.setTankNo(rs.getInt("TANK_NO"));
				tank.setTankName(rs.getString("TANK_NAME"));
				
				tankInfo.add(tank);
			}
			
		} finally {
			close(rs);
			close(pstmt);
		}
		
		
		return tankInfo;
	}


	/** 가장 큰 어항번호 DAO
	 * @param conn
	 * @param memberNo
	 * @return maxTankNo
	 * @throws Exception
	 */
	public int maxTankNo(Connection conn, int memberNo) throws Exception {
		int maxTankNo = 0;
		
		try {
			String sql = prop.getProperty("maxTankNo");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, memberNo);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				maxTankNo = rs.getInt(1);
			}
			
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return maxTankNo;
	}


	/** 새 어항 등록 DAO
	 * @param conn
	 * @param tank
	 * @param maxTankNo
	 * @return result
	 * @throws Exception
	 */
	public int insertTank(Connection conn, Tank tank, int maxTankNo) throws Exception {
		int result = 0;
		
		try {
			String sql = prop.getProperty("insertTank");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, tank.getMemberNo());
			
			if(maxTankNo == 0) {
				maxTankNo = 1;
				pstmt.setInt(2, maxTankNo);
			}
			
			if(maxTankNo > 0) {
				maxTankNo = maxTankNo+1;
				pstmt.setInt(2, maxTankNo);
			}
			
			pstmt.setString(3, tank.getTankName());
			pstmt.setString(4, tank.getFreshSalt());
			pstmt.setString(5, tank.getTankSize());
			pstmt.setString(6, tank.getTankFilter());
			pstmt.setString(7, tank.getTankLight());
			pstmt.setString(8, tank.getTankAddictive());
			pstmt.setString(9, tank.getTankSubstrate());
			
			result = pstmt.executeUpdate();
			
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}


	/** 어항 번호 조회용 어항 목록
	 * @param conn
	 * @param memberNo
	 * @return tankList
	 * @throws Exception
	 */
	public List<Tank> tankList(Connection conn, int memberNo) throws Exception {
		List<Tank> tankList = new ArrayList<>();
		
		try {
			
		} finally {
			// TODO: handle finally clause
		}
		
		return tankList;
	}
	
	
	
	
	
	
	
	
	

}
