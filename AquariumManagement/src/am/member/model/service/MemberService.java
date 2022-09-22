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
	
	

}
