package edu.kh.jdbc.run;

import java.util.Scanner;

import edu.kh.jdbc.model.service.TestService;
import edu.kh.jdbc.model.vo.TestVO;

public class Run6 {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		TestService service = new TestService();
		
		System.out.print("번호: ");
		int testNo = sc.nextInt();
		
		System.out.print("제목: ");
		String testTitle = sc.next();
		sc.nextLine();
		
		System.out.print("내용: ");
		String testContent = sc.nextLine();
		
		TestVO vo = new TestVO(testNo, testTitle, testContent);
		
		try {
			int result = service.insert(vo);
			if(result>0) {
				System.out.println("삽입 완료");
			} else {
				System.out.println("삽입 실패");
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
}
