package edu.kh.jdbc.member.model.sevice;

import static edu.kh.jdbc.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import edu.kh.jdbc.member.model.dao.MemberDAO;
import edu.kh.jdbc.member.vo.Member;

public class MemberService {
	
	private MemberDAO dao = new MemberDAO();

	/** 회원 목록 조회 서비스
	 * @return memberList
	 * @throws Exception
	 */
	public List<Member> selectAll() throws Exception {
		
		Connection conn = getConnection();
		
		List<Member> memberList = dao.selectAll(conn);
		
		close(conn);
		
		return memberList;
	}

	
	
	
	/** 내 정보 수정
	 * @param member
	 * @return result
	 * @throws Exception
	 */
	public int updateMember(Member member) throws Exception {
		Connection conn = getConnection();
		
		int result = dao.updateMember(conn, member);
		
		if(result > 0) commit(conn);
		else 		   rollback(conn);
		
		close(conn);
		
		return result;
	}




	/** 비밀번호 변경 서비스
	 * @param memberNo
	 * @param currentPw
	 * @param newPw1
	 * @return result
	 * @throws Exception
	 */
	public int updatePw(int memberNo, String currentPw, String newPw1) throws Exception {
		Connection conn = getConnection();
		
		int result = dao.updateMember(conn, memberNo, currentPw, newPw1);
		
		if(result > 0) commit(conn);
		else 		   rollback(conn);
		
		close(conn);
		
		return result;
	}




	/** 회원 탈퇴 서비스
	 * @param memberNo
	 * @param memberPw
	 * @return result
	 * @throws Exception
	 */
	public int secession(int memberNo, String memberPw) throws Exception {
		Connection conn = getConnection();
		
		int result = dao.secession(conn, memberNo, memberPw);
		
		if(result > 0) commit(conn);
		else 		   rollback(conn);
		
		close(conn);
		
		return result;
	}

	
	
	
	
	
	
	
}
