package edu.kh.jdbc.member.model.dao;

import static edu.kh.jdbc.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import edu.kh.jdbc.member.vo.Member;

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


	/** 회원 목록 조회 DAO
	 * @param conn
	 * @return memberList
	 * @throws Exception
	 */
	public List<Member> selectAll(Connection conn) throws Exception{
//		결과 저장용 변수 선언
		List<Member> memberList = new ArrayList<>();
		
		try {
//			SQL 얻어오기
			String sql = prop.getProperty("selectAll");
					
//			Statement 객체 생성
			stmt = conn.createStatement();
			
//			SQL(SELECT) 수행 후 결과(RESULT SET) 반환
			rs = stmt.executeQuery(sql);
			
//			반복문(while)을 이용해서 반환 된 결과 List에 저장
			while(rs.next()) {
				String memberId = rs.getString("MEMBER_ID");
				String memberName = rs.getString("MEMBER_NM");
				String memberGender = rs.getString("MEMBER_GENDER");
				
				Member member = new Member();
				member.setMemberId(memberId);
				member.setMemberName(memberName);
				member.setMemberGender(memberGender);
				
				memberList.add(member);
			}
			
		} finally {
//			JDBC 객체 자원 반환
			close(rs);
			close(stmt);
		}
		
//		조회 결과를 옮겨 담은 List 반환
		return memberList;
	}


	/** 내 정보 수정 DAO
	 * @param conn
	 * @param member
	 * @return result
	 * @throws Exception
	 */
	public int updateMember(Connection conn, Member member) throws Exception {
		int result = 0;
		
		try {
			String sql = prop.getProperty("updateMember");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, member.getMemberName());
			pstmt.setString(2, member.getMemberGender());
			pstmt.setInt(3, member.getMemberNo());
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}



	/** 비밀번호 변경 DAO
	 * @param conn
	 * @param memberNo
	 * @param currentPw
	 * @param newPw1
	 * @return result
	 * @throws Exception
	 */
	public int updateMember(Connection conn, int memberNo, String currentPw, String newPw1) throws Exception {
		int result = 0;
		
		try {
			String sql = prop.getProperty("updatePw");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, newPw1);
			pstmt.setInt(2, memberNo);
			pstmt.setString(3, currentPw);
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}


	/** 회원 탈퇴 DAO
	 * @param conn
	 * @param memberNo
	 * @param memberPw
	 * @return result
	 * @throws Exception
	 */
	public int secession(Connection conn, int memberNo, String memberPw) throws Exception {
		int result = 0;
		
		try {
			String sql = prop.getProperty("secession");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, memberNo);
			pstmt.setString(2, memberPw);
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}

		
		return result;
	}
	
	
	
	
	
	
	
	
	

}
