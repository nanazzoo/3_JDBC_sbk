package edu.kh.jdbc.run;

import java.sql.SQLException;
import java.util.Scanner;

import edu.kh.jdbc.model.service.TestService;

public class Run4 {

	public static void main(String[] args) {
//		번호를 입력받아 번호가 일치하는 행을 삭제
//		삭제 성공 시 -> 삭제되었습니다.
//		삭제 실패 시 -> 일치하는 번호가 없습니다.
//		예외 발생 시 -> 삭제 중 예외가 발생했습니다.
		Scanner sc = new Scanner(System.in);
		
		TestService service = new TestService();
		
		System.out.print("삭제할 번호 입력: ");
		int testNo = sc.nextInt();
		
		try {
			int result = service.delete(testNo);
			if(result > 0) {
				System.out.println("삭제되었습니다.");
			} else {
				System.out.println("일치하는 번호가 없습니다.");
			}
		} catch (SQLException e) {
			System.out.println("삭제 중 예외가 발생했습니다.");
			e.printStackTrace();
		}
		

	}

}
