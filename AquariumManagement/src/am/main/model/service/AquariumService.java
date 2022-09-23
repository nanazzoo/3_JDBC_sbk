package am.main.model.service;

import static am.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import am.main.model.dao.AquariumDAO;
import am.member.vo.Member;
import am.tank.vo.Tank;

public class AquariumService {
	private AquariumDAO dao = new AquariumDAO();

	
	
	/** 로그인 Service
	 * @param memberId
	 * @param memberPw
	 * @return loginMember
	 * @throws Exception
	 */
	public Member login(String memberId, String memberPw) throws Exception {
		Connection conn = getConnection();
		
		Member loginMember = dao.login(conn, memberId, memberPw);
		
		close(conn);
		
		return loginMember;
	}



	/** 아이디 중복 검사 Service
	 * @param memberId
	 * @return result
	 * @throws Exception
	 */
	public int idDupCheck(String memberId) throws Exception {
		Connection conn = getConnection();
		
		int result = dao.idDupCheck(conn, memberId);
		
		close(conn);
		
		return result;
	}



	/** signUp Service
	 * @param member
	 * @return result
	 * @throws Exception
	 */
	public int signUp(Member member) throws Exception {
		Connection conn = getConnection();
		
		int result = dao.signUp(conn, member);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}




	public List<Tank> tankInfo(Member loginMember) throws Exception {
		Connection conn = getConnection();
		
		List<Tank> tankInfo = dao.tankInfo(conn, loginMember.getMemberNo());
		
		close(conn);
		
		return tankInfo;
	}



	/** 가장 큰 탱크번호 조회
	 * @param memberNo
	 * @return maxTankNo
	 * @throws Exception
	 */
	public int maxTankNo(int memberNo) throws Exception {
		Connection conn = getConnection();
		
		int maxTankNo = dao.maxTankNo(conn, memberNo);
		
		close(conn);
		
		return maxTankNo;
	}



	/** 새 어항 등록 서비스
	 * @param tank
	 * @param maxTankNo
	 * @return result
	 * @throws Exception
	 */
	public int insertTank(Tank tank, int maxTankNo) throws Exception {
		Connection conn = getConnection();
		
		int result = dao.insertTank(conn, tank, maxTankNo);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		return result;
	}



	/** 어항 번호 검사위한 어항 목록 조회
	 * @param memberNo
	 * @return
	 * @throws Exception
	 */
	public List<Tank> tankList(int memberNo) throws Exception {
		Connection conn = getConnection();
		
		List<Tank> tankList = dao.tankList(conn, memberNo);
		
		close(conn);
		
		return tankList;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
