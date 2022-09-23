package am.livestock.model.service;

import static am.common.JDBCTemplate.*;
import java.sql.Connection;
import java.util.List;

import am.livestock.model.dao.LivestockDAO;
import am.livestock.vo.Livestock;

public class LivestockService {
	private LivestockDAO dao = new LivestockDAO();

	/** 생물 목록 조회 서비스
	 * @param memberNo
	 * @param tankNo
	 * @return livestockList
	 * @throws Exception
	 */
	public List<Livestock> selectLivestock(int memberNo, int tankNo) throws Exception {
		Connection conn = getConnection();
		
		List<Livestock> livestockList = dao.selectLivestock(conn, memberNo, tankNo);
		
		close(conn);
		
		return livestockList;
	}

	/** 가장 큰 생물 번호 조회
	 * @param memberNo
	 * @param tankNo
	 * @return maxLivestockNo
	 * @throws Exception
	 */
	public int maxLivestockNo(int memberNo, int tankNo) throws Exception{
		Connection conn = getConnection();
		
		int maxLivestockNo = dao.maxLivestockNo(conn, memberNo, tankNo);
		
		close(conn);
		
		return maxLivestockNo;
	}

	
	
	/** 생물 등록 서비스
	 * @param livestock
	 * @return result
	 * @throws Exception
	 */
	public int insertLivestock(Livestock livestock, int maxLivestockNo) throws Exception {
		Connection conn = getConnection();
		
		int result = dao.insertLivestock(conn, livestock, maxLivestockNo);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		return result;
	}

	
	
	/** 생물 정보 수정 서비스
	 * @param livestock
	 * @return result
	 * @throws Exception
	 */
	public int updateLivestock(Livestock livestock, int input) throws Exception {
		Connection conn = getConnection();
		
		int result = dao.updateLivestock(conn, livestock, input);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		return result;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
