package am.main.model.service;

import static am.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import am.main.dao.AquariumDAO;
import am.member.model.vo.Member;
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



	/** 어항 정보 Service
	 * @param memberNo
	 * @return myTank
	 * @throws Exception
	 */
	public List<Tank> myAquarium(int memberNo) throws Exception {
		Connection conn = getConnection();
		
		List<Tank> myAquarium = dao.myAquarium(conn, memberNo);
		
		close(conn);
		
		return myAquarium;
	}



	public List<Tank> tankInfo(Member loginMember) throws Exception {
		Connection conn = getConnection();
		
		List<Tank> tankInfo = dao.tankInfo(conn, loginMember.getMemberNo());
		
		close(conn);
		
		return tankInfo;
	}

}
