package am.member.view;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import am.member.model.service.MemberService;
import am.member.model.vo.Member;
import am.tank.vo.Tank;

public class MemberView {
	private Scanner sc = new Scanner(System.in);
	
	private MemberService service = new MemberService();
	
	int input = -1;
	
	
	public void memberMenu(Member loginMember, int tankNo) {
		try {
			System.out.println("\n*** 내 어항 ***\n");
			
			List<Tank> myAquarium = service.selectMyTank(loginMember.getMemberNo(), tankNo);
			
			
			if(myAquarium.isEmpty()) {
				System.out.println("\n[등록된 어항이 없습니다.]\n");
			} else {
				for(Tank t : myAquarium) {	
					System.out.println(t.getTankNo() + ". " + t.getTankName());
					System.out.println();
					
					if(t.getFreshSalt().contains("S")) {
						System.out.println("해수 어항");
					} else {
						System.out.println("담수 어항");
					}
					System.out.println("생물수   : 총 " + + t.getTankMate() + "마리");
					System.out.println("어항 크기: " +  t.getTankSize());
					System.out.println("조명     : " + t.getTankLight());
					System.out.println("여과 방식: " + t.getTankFilter());
					System.out.println("첨가제   : " + t.getTankAddictive());
					System.out.println("바닥재   : " + t.getTankSubstrate());
					System.out.println();
				}
			}
//			
			
		} catch (Exception e) {
			System.out.println("어항을 불러오는 도중 오류가 발생하였습니다.");
			e.printStackTrace();
		}
		
		do {
			
			try {
				
				System.out.println("\n*** Aquarium Management ***\n");
				System.out.println("[어항 관리 메뉴]");
				System.out.println("1. 어항 정보 수정");
				System.out.println("2. 생물");
				System.out.println("3. 물성치");
				System.out.println("4. 할 일");
				System.out.println("0. 뒤로");
				
				System.out.print("메뉴 선택 >> ");
				input = sc.nextInt();
				sc.nextLine();
				System.out.println();
				
				switch(input) {
				case 1: ; break;
				case 2: ; break;
				case 3: ; break;
				case 4: ; break;
				case 0: break;
				default: System.out.println("메뉴에 있는 번호만 입력해주세요.");
				}
			} catch (InputMismatchException e) {
				e.printStackTrace();
			}
			
		} while (input != 0);
		
	}

}
