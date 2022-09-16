package edu.kh.jdbc.model.dao;

import static edu.kh.jdbc.common.JDBCTemplate.*;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import edu.kh.jdbc.common.JDBCTemplate;
import edu.kh.jdbc.model.vo.TestVO;

// DAO(Data Access Object): 데이터가 저장된 DB에 접근하는 객체
//						    -> SQL 수행, 결과 반환 받는 기능 수행
public class TestDAO {
	
//	JDBC 객체를 참조하기 위한 참조변수 선언
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	
//	XML로 SQL을 다룰 예정 -> Properties 객체 사용
	private Properties prop;
	
//	기본 생성자
	public TestDAO()  {
		
//		TestDAO 객체 생성 시
//		test-query.xml 파일의 내용을 읽어와
//		Properties 객체에 저장
		try {
			prop = new Properties();
			prop.loadFromXML(new FileInputStream("test-query.xml"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	/** 1행 삽입 DAO
	 * @param conn
	 * @param vo1
	 * @return result
	 */
	public int insert(Connection conn, TestVO vo1) throws SQLException {
//		throws SQLException
//		-> 호출한 곳으로 발생한 SQLException을 던짐
//		-> 예외를 모아서 처리하기 위해서
		
		
//		1. 결과 저장용 변수 선언
		int result = 0;
	
		try {
//			2. SQl 작성(test-query.xml에 작성된 SQL 얻어오기)
//			   -> prop이 저장하고 있음!
			
			String sql = prop.getProperty("insert");
			
//			3. PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
//			-> throws 예외 처리 사용

//			4. ?(위치홀더)에 알맞은 값 세팅
			pstmt.setInt(1, vo1.getTestNo());
			pstmt.setString(2, vo1.getTestTitle());
			pstmt.setString(3, vo1.getTestContent());
			
//			5. SQL(INSERT) 수행 후 결과 반환 받기
//			pstmt.executeQuery() -> SELECT 수행, ResultSet 반환
			result = pstmt.executeUpdate(); // -> DML 수행 , 반영된 행의 개수 반환
			
			
		} finally {
//			6. 사용한 JDBC 객체 자원 반환(close())
			close(pstmt);
		}
		
//		7. SQL 수행 결과 반환
		return result;
	}





	public int update(Connection conn, TestVO vo) throws SQLException {
//		결과 저장할 변수 선언
		int result = 0;
		
//		try-finally 구문 생성
		try {
			
//			sql문 prop에서 가져옴
			String sql = prop.getProperty("update");
			
//			PreparedStatement 생성
			pstmt = conn.prepareStatement(sql);
			
//			?(위치 홀더) 값 지정
			pstmt.setString(1, vo.getTestTitle());
			pstmt.setString(2, vo.getTestContent());
			pstmt.setInt(3, vo.getTestNo());
			
//			SQL 수행 후 결과 값 반환
			result = pstmt.executeUpdate();
			
			
			
		} finally {
//			사용한 pstmt 객체 반환
			close(pstmt);
		}
		
//		결과 값 리턴
		return result;
	}


	public int delete(Connection conn, int testNo) throws SQLException {
		int result = 0;
		
		try {
			String sql = prop.getProperty("delete");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, testNo);
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		
		
		return result;
	}


	public List<TestVO> select(Connection conn) throws SQLException {
//		결과 저장할 리스트 객체 생성
		List<TestVO> result = new ArrayList<>();
		
		try {
//			Properties에서 select SQL문 가져와 sql 변수에 대입
			String sql = prop.getProperty("select");
			
//			Statement 객체 생성
			stmt = conn.createStatement();
			
//			SQL문 실행 후 결과 값을 rs(ResultSet)에 대입
			rs = stmt.executeQuery(sql);
			
//			rs에 대입된 값을 result 리스트에 add
			while(rs.next()) {
				int testNo = rs.getInt("TEST_NO");
				String testTitle = rs.getString("TEST_TITLE");
				String testContent= rs.getString("TEST_CONTENT");
				
				TestVO vo = new TestVO(testNo, testTitle, testContent);
				
				result.add(vo);
			}
			
		} finally {
//			사용한 객체 반환
			close(stmt);
			close(rs);
		}
		
//		결과 리턴
		return result;
	}

	
	
	
	
	
	
	
	
	
	
}
