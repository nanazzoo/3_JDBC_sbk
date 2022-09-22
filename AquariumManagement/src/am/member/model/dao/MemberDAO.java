package am.member.model.dao;

import static am.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import am.tank.vo.Tank;

public class MemberDAO {
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private Properties prop;
	
	
	public MemberDAO() {
		 try {
			 prop = new Properties();
			 
			 prop.loadFromXML(new FileInputStream("member-query.xml"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/** 내 어항 정보 조회 DAO
	 * @param conn
	 * @param memberNo
	 * @return myTank
	 * @throws Exception
	 */
	public List<Tank> selectMyTank(Connection conn, int memberNo, int tankNo) throws Exception {
		List<Tank> myTank = new ArrayList<>();
		
		try {
			String sql = prop.getProperty("selectMyTank");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, memberNo);
			pstmt.setInt(2, tankNo);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Tank tank = new Tank();
				
				tank.setTankNo(rs.getInt(1));
				tank.setTankName(rs.getString(2));
				tank.setFreshSalt(rs.getString(3));
				tank.setTankSize(rs.getString(4));
				tank.setTankLight(rs.getString(5));
				tank.setTankFilter(rs.getString(6));
				tank.setTankAddictive(rs.getString(7));
				tank.setTankSubstrate(rs.getString(8));
				tank.setTankMate(rs.getInt(9));
				
				myTank.add(tank);
			}
					
			
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return myTank;
	}

}
