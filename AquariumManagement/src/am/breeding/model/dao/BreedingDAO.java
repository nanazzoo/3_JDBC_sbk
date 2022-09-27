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

import am.breeding.model.vo.Breeding;

public class BreedingDAO {

	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Properties prop;
	
	public BreedingDAO() {
		try {
			prop = new Properties();
			prop.loadFromXML(new FileInputStream("breeding-query.xml"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 브리딩 목록 조회 DAO
	 * @param conn
	 * @param memberNo
	 * @return breedingList
	 * @throws Exception
	 */
	public List<Breeding> selectAllBreeding(Connection conn, int memberNo) throws Exception{
		List<Breeding> breedingList = new ArrayList<>();
		
		try {
			String sql = prop.getProperty("selectAllBreeding");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, memberNo);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Breeding breeding = new Breeding();
				
				breeding.setBreedingNo(rs.getInt(1));
				breeding.setBreedSpecies1(rs.getString(2));
				breeding.setStartDate(rs.getString(3));
				breeding.setEndFl(rs.getString(4));
				
				breedingList.add(breeding);
			}
			
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return breedingList;
	}

	
	/** 브리딩 상세 조회 서비스
	 * @param conn
	 * @param memberNo
	 * @param breedingNo
	 * @return breeding
	 * @throws E
	 */
	public Breeding selectBreeding(Connection conn, int memberNo, int breedingNo) throws Exception {
		Breeding breeding = null;
		
		try {
			String sql = prop.getProperty("selectBreeding");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, memberNo);
			pstmt.setInt(2, breedingNo);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				breeding = new Breeding();
				
				breeding.setBreedingNo(rs.getInt(1));
				breeding.setBreedSpecies1(rs.getString(2));
				breeding.setFromTankNo1(rs.getInt(3));
				breeding.setBreedSpecies2(rs.getString(4));
				breeding.setFromTankNo2(rs.getInt(5));
				breeding.setStartDate(rs.getString(6));
			}
			
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return breeding;
	}

	/** 브리딩 완료 처리 DAO
	 * @param conn
	 * @param memberNo
	 * @param breedingNo
	 * @return result
	 * @throws Exception
	 */
	public int completeBreeding(Connection conn, int memberNo, int breedingNo) throws Exception {
		int result = 0;
		
		try {
			String sql = prop.getProperty("completeBreeding");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, memberNo);
			pstmt.setInt(2, breedingNo);
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	/** 브리딩 삭제 DAO
	 * @param conn
	 * @param memberNo
	 * @param breedingNo
	 * @return result
	 * @throws Exception
	 */
	public int deleteBreeding(Connection conn, int memberNo, int breedingNo) throws Exception {
		int result = 0;
		
		try {
			String sql = prop.getProperty("deleteBreeding");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, memberNo);
			pstmt.setInt(2, breedingNo);
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	/** 가장 큰 브리딩 번호
	 * @param conn
	 * @param memberNo
	 * @return maxBreedingNo
	 * @throws Exception
	 */
	public int maxBreedingNo(Connection conn, int memberNo) throws Exception {
		int maxBreedingNo = 0;
		
		try {
			String sql = prop.getProperty("maxBreedingNo");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, memberNo);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				maxBreedingNo = rs.getInt(1);
			}
			
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return maxBreedingNo;
	}

	/** 브리딩 추가 DAO
	 * @param conn
	 * @param breeding
	 * @return result
	 * @throws Exception
	 */
	public int insertBreeding(Connection conn, Breeding breeding) throws Exception {
		int result = 0;
		
		try {
			String sql = prop.getProperty("insertBreeding");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, breeding.getMemberNo());
			
			if(breeding.getBreedingNo()==0) {
				pstmt.setInt(2, 1);	
			} else {
				int breedingNo = breeding.getBreedingNo()+1;
				pstmt.setInt(2, breedingNo);	
			}
			
			pstmt.setString(3, breeding.getBreedSpecies1());
			pstmt.setInt(4, breeding.getFromTankNo1());
			pstmt.setString(5, breeding.getBreedSpecies2());
			pstmt.setInt(6, breeding.getFromTankNo2());
			pstmt.setString(7, breeding.getStartDate());
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	
	/** 브리딩 추가 DAO
	 * @param conn
	 * @param breeding
	 * @return result
	 * @throws Exception
	 */
	public int insertBreeding2(Connection conn, Breeding breeding) throws Exception {
		int result = 0;
		
		try {
			String sql = prop.getProperty("insertBreeding2");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, breeding.getMemberNo());
			
			if(breeding.getBreedingNo()==0) {
				pstmt.setInt(2, 1);	
			} else {
				int breedingNo = breeding.getBreedingNo()+1;
				pstmt.setInt(2, breedingNo);	
			}
			
			pstmt.setString(3, breeding.getBreedSpecies1());
			pstmt.setInt(4, breeding.getFromTankNo1());
			pstmt.setString(5, breeding.getBreedSpecies2());
			pstmt.setInt(6, breeding.getFromTankNo2());
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
