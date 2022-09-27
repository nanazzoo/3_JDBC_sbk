package am.breeding.model.service;

import static am.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import am.breeding.model.dao.BreedingDAO;
import am.breeding.model.dao.EventDAO;
import am.breeding.model.vo.Breeding;
import am.breeding.model.vo.Event;

public class BreedingService {
	private BreedingDAO bDao = new BreedingDAO();
	private EventDAO eDao = new EventDAO();


	/** 브리딩 목록 조회 서비스
	 * @param memberNo
	 * @return breedingList
	 * @throws Exception
	 */
	public List<Breeding> selectAllBreeding(int memberNo) throws Exception {
		Connection conn = getConnection();
		
		List<Breeding> breedingList = bDao.selectAllBreeding(conn, memberNo);
		
		close(conn);
		
		return breedingList;
	}


	/** 브리딩 상세 조회 서비스
	 * @param memberNo
	 * @param breedingNo
	 * @return breeding
	 * @throws Exception
	 */
	public Breeding selectBreeding(int memberNo, int breedingNo) throws Exception {
		Connection conn = getConnection();
		
		Breeding breeding = bDao.selectBreeding(conn, memberNo, breedingNo);
		
		if(breeding != null) {
			
			List<Event> commentList = eDao.selectEventList(conn, memberNo, breedingNo);
			
			breeding.setEventList(commentList);
		}
		
		close(conn);
		
		return breeding;
	}


	/** 브리딩 완료 처리 서비스
	 * @param breedingNo
	 * @return result
	 * @throws Exception
	 */
	public int completeBreeding(int memberNo, int breedingNo) throws Exception {
		Connection conn = getConnection();
		
		int result = bDao.completeBreeding(conn, memberNo, breedingNo);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		return result;
	}


	/** 브리딩 삭제 서비스
	 * @param memberNo
	 * @param breedingNo
	 * @return result
	 * @throws Exception
	 */
	public int deleteBreeding(int memberNo, int breedingNo) throws Exception {
		Connection conn = getConnection();
		
		int result = bDao.deleteBreeding(conn, memberNo, breedingNo);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		return result;
	}


	/** 가장 큰 브리딩 번호 서비스
	 * @param memberNo
	 * @return maxBreedingNo
	 * @throws Exception
	 */
	public int maxBreedingNo(int memberNo) throws Exception {
		Connection conn = getConnection();
		
		int maxBreedingNo = bDao.maxBreedingNo(conn, memberNo);
		
		close(conn);
		
		return maxBreedingNo;
	}


	/** 브리딩 추가 서비스
	 * @param breeding
	 * @return result
	 * @throws Exception
	 */
	public int insertBreeding(Breeding breeding) throws Exception{
		Connection conn = getConnection();
		
		int result = bDao.insertBreeding(conn, breeding);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		return result;
	}
	
	/** 브리딩 추가 서비스
	 * @param breeding
	 * @return result
	 * @throws Exception
	 */
	public int insertBreeding2(Breeding breeding) throws Exception{
		Connection conn = getConnection();
		
		int result = bDao.insertBreeding2(conn, breeding);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		return result;
	}

	
	
	
	
	
	
	
	
	
	
	
}
