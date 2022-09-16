package edu.kh.jdbc.run;

import java.sql.SQLException;
import java.util.List;

import edu.kh.jdbc.model.service.TestService;
import edu.kh.jdbc.model.vo.TestVO;

public class Run5 {

	public static void main(String[] args) {
//		SELECT ALL 구문 작성
		
		TestService service = new TestService();
		
		List<TestVO> result;
		
		try {
			result = service.select();
			if(result.isEmpty()) {
				System.out.println("조회 결과가 없습니다");
			} else {
				for(TestVO v :result) {
					System.out.println(v);
				}
			}
		} catch (SQLException e) {
			System.out.println("조회 중 예외 발생");
			e.printStackTrace();
		}
		
		
		


	}

}
