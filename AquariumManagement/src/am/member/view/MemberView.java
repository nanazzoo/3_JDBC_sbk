package am.member.view;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import am.livestock.view.LivestockView;
import am.livestock.vo.Livestock;
import am.main.view.AquariumView;
import am.member.model.service.MemberService;
import am.member.vo.Member;
import am.parameter.view.ParameterView;
import am.tank.vo.Tank;
import am.todo.view.TodoView;

public class MemberView {
	private Scanner sc = new Scanner(System.in);
	
	private MemberService service = new MemberService();
	private LivestockView view = new LivestockView();
	private ParameterView pView = new ParameterView();
	private TodoView tView = new TodoView();
	
	private int input = -1;
	public static int tankNo = 0;
	
	public void memberMenu(Member loginMember, int tankNo) {
		
		do {
			try {
				System.out.println("\n*** 내 어항 ***\n");
				this.tankNo = tankNo;
				
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
			
			try {
				
				System.out.println("\n*** Aquarium Management ***\n");
				System.out.println("[어항 관리 메뉴]");
				System.out.println("1. 어항 정보 수정");
				System.out.println("2. 생물");
				System.out.println("3. 물성치");
				System.out.println("4. 할 일");
				System.out.println("5. 어항 삭제");
				System.out.println("0. 뒤로");
				
				System.out.print("메뉴 선택 >> ");
				input = sc.nextInt();
				sc.nextLine();
				System.out.println();
				
				switch(input) {
				case 1: updateTank(tankNo); break;
				case 2: view.livestockMenu(tankNo); break;
				case 3: pView.parameterMenu(tankNo); break;
				case 4: tView.todoMenu(tankNo); break;
				case 5: deleteTank(tankNo); break;
				case 0: break;
				default: System.out.println("메뉴에 있는 번호만 입력해주세요.");
				}
			} catch (InputMismatchException e) {
				e.printStackTrace();
			}
			
		} while (input != 0);
		
	}

	

	/** 어항 정보 수정
	 * @param tankNo
	 */
	private void updateTank(int tankNo) {
		
		try {
			System.out.println("\n[어항 정보 수정]\n");
			
			System.out.println("1. 어항 이름");
			System.out.println("2. 어항 조명");
			System.out.println("3. 어항 첨가제");
			System.out.println("4. 어항 바닥재");
			
			System.out.print("\n메뉴 선택 >> ");
			int input = sc.nextInt();
			sc.nextLine();
			
			System.out.print("변경할 내용 입력: ");
			String content = sc.nextLine();
			
			
			Tank tank = new Tank();
			tank.setTankNo(tankNo);
			tank.setMemberNo(AquariumView.loginMember.getMemberNo());
			
			int result = service.updateTank(input, content, tank);
			
			
			if(result > 0) {
				System.out.println("\n[수정 완료]\n");
			} else {
				System.out.println("\n[수정 실패]\n");
				
			}
			
			
		} catch (Exception e) {
			System.out.println("\n[어항 정보 수정 중 오류가 발생했습니다.]\n");
			e.printStackTrace();
		}
		
		
	}
	
	
	
	/**
	 * 내 어항 삭제
	 */
	private void deleteTank(int tankNo) {
		try {
			
			System.out.println("\n[어항 삭제]\n");
			
			System.out.print("정말로 삭제하시겠습니까?(Y/N): ");
			char ch = sc.next().toUpperCase().charAt(0);
			
			if(ch == 'Y') {
				System.out.println("\n어항을 삭제합니다.\n");
				
				int result = service.deleteTank(tankNo);
				
				if(result > 0) {
					System.out.println("\n[어항 삭제 성공]\n");
					this.input = 0;
				} else {
					System.out.println("\n[어항 삭제 실패]\n");
				}
				
				
			} else {
				System.out.println("\n삭제를 취소합니다.\n");
			}
			
		} catch (Exception e) {
			System.out.println("\n[어항 삭제 중 오류가 발생했습니다.]\n");
			e.printStackTrace();
		}
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
