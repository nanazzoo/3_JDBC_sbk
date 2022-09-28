package am.member.model.service;

import static am.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import am.member.model.dao.MemberDAO;
import am.tank.vo.Tank;

public class MemberService {
	private MemberDAO dao = new MemberDAO();

	
	
	/** 내 어항 정보 서비스
	 * @param memberNo
	 * @return myTank
	 * @throws Exception
	 */
	public List<Tank> selectMyTank(int memberNo, int tankNo) throws Exception{
		Connection conn = getConnection();
		
		List<Tank> myTank = dao.selectMyTank(conn, memberNo, tankNo);
		
		close(conn);
		
		return myTank;
	}



	/** 어항 정보 수정 서비스
	 * @param columnNm
	 * @param content
	 * @return result
	 * @throws Exception
	 */
	public int updateTank(int input, String content, Tank tank) throws Exception {
		Connection conn = getConnection();
		
		int result = dao.updateTank(conn, input, content, tank);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}



	
	/** 어항 삭제 서비스
	 * @param tankNo
	 * @return result
	 * @throws Exception
	 */
	public int deleteTank(int memberNo, int tankNo) throws Exception {
		Connection conn = getConnection();
		
		int result = dao.deleteTank(conn, memberNo, tankNo);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}
	
	

}
