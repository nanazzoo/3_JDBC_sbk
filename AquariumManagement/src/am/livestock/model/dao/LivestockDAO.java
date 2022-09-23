package am.livestock.model.dao;


import static am.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import am.livestock.vo.Livestock;

public class LivestockDAO {
	
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private Properties prop;
	
	
	public LivestockDAO() {
		
		try {
			prop = new Properties();
			
			prop.loadFromXML(new FileInputStream("livestock-query.xml"));
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/** 생물 목록 조회 DAO
	 * @param conn
	 * @param memberNo
	 * @param tankNo
	 * @return livestockList
	 * @throws Exception
	 */
	public List<Livestock> selectLivestock(Connection conn, int memberNo, int tankNo) throws Exception {
		List<Livestock> livestockList = new ArrayList<>();
		
		try {
			String sql = prop.getProperty("selectLivestock");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, memberNo);
			pstmt.setInt(2, tankNo);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Livestock livestock = new Livestock();
				
				livestock.setLivestockNo(rs.getInt("LIVESTOCK_NO"));
				livestock.setLivestockSpecies(rs.getString("LIVESTOCK_SPECIES"));
				livestock.setLivestockName(rs.getString("LIVESTOCK_NM"));
				livestock.setLivestockPrice(rs.getInt("LIVESTOCK_PRICE"));
				livestock.setLivestockGender(rs.getString("LIVESTOCK_GENDER"));
				livestock.setDateOfPurchase(rs.getString(6));
				
				livestockList.add(livestock);
			}
			
			
		} finally {
			close(rs);
			close(pstmt);
		}
		
	
		return livestockList;
	}


	/** 가장 큰 생물 번호 조회 DAO
	 * @param conn
	 * @param memberNo
	 * @param tankNo
	 * @return maxLivestockNo
	 * @throws Exception
	 */
	public int maxLivestockNo(Connection conn, int memberNo, int tankNo) throws Exception {
		int maxLivestockNo = 0;
		
		try {
			String sql = prop.getProperty("maxLivestockNo");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, memberNo);
			pstmt.setInt(2, tankNo);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				maxLivestockNo = rs.getInt(1);
			}
			
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return maxLivestockNo;
	}


	/** 생물 등록 DAO
	 * @param conn
	 * @param livestock
	 * @return result
	 * @throws Exception
	 */
	public int insertLivestock(Connection conn, Livestock livestock, int maxLivestockNo) throws Exception {
		int result = 0;
		
		try {
			String sql = prop.getProperty("insertLivestock");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, livestock.getMemberNo());
			pstmt.setInt(2, livestock.getTankNo());
			
			
			if(maxLivestockNo == 0) {
				maxLivestockNo = 1;
				pstmt.setInt(3, maxLivestockNo);
			}
			
			if(maxLivestockNo > 0) {
				maxLivestockNo = maxLivestockNo+1;
				pstmt.setInt(3, maxLivestockNo);
			}
			
			pstmt.setString(4, livestock.getLivestockSpecies());
			pstmt.setString(5, livestock.getLivestockName());
			pstmt.setInt(6, livestock.getLivestockPrice());
			pstmt.setString(7, livestock.getLivestockGender());
			pstmt.setString(8, livestock.getDateOfPurchase());
			
			result = pstmt.executeUpdate();
			
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}

 
	/** 생물 정보 수정 DAO
	 * @param conn
	 * @param livestock
	 * @return result
	 * @throws Exception
	 */
	public int updateLivestock(Connection conn, Livestock livestock, int input) throws Exception {
		int result = 0;
		
		try {
			String sql = null;
			if(input == 1) sql = prop.getProperty("updateName");
			if(input == 2) sql = prop.getProperty("updatePrice");
			if(input == 3) sql = prop.getProperty("updateGender");
			if(input == 4) sql = prop.getProperty("updateLifeOrDie");
			
			pstmt= conn.prepareStatement(sql);
			
			if(input == 1) pstmt.setString(1, livestock.getLivestockName());
			if(input == 2) pstmt.setInt(1, livestock.getLivestockPrice());
			if(input == 3) pstmt.setString(1, livestock.getLivestockGender());
			if(input == 4) pstmt.setString(1, livestock.getLifeOrDie());
			
			pstmt.setInt(2, livestock.getMemberNo());
			pstmt.setInt(3, livestock.getTankNo());
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}
}
