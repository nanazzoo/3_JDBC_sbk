package am.main.view;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import am.main.model.service.AquariumService;
import am.member.model.vo.Member;
import am.member.view.MemberView;
import am.tank.vo.Tank;

public class AquariumView {
	
	private AquariumService service = new AquariumService();
	private MemberView memberView = new MemberView();
	private Scanner sc = new Scanner(System.in);
	int input = -1;
	
	private Member loginMember = null;

	public void mainMenu() {
		
		do {
			try {
				if(loginMember == null) {
					System.out.println("\n*** Aquarium Management ***\n");
					System.out.println("1. 로그인");
					System.out.println("2. 회원 가입");
					System.out.println("0. 프로그램 종료");
					
					System.out.print("메뉴 선택 >> ");
					input = sc.nextInt();
					sc.nextLine();
					System.out.println();
					
					switch(input) {
					case 1: login(); break;
					case 2: signUp(); break;
					case 0: System.out.println("[프로그램을 종료합니다.]"); break;
					default: System.out.println("메뉴에 있는 번호만 입력해주세요.");
					}
					
				}
					
				if(loginMember != null) {
					System.out.println("\n*** Aquarium Management ***\n");
					System.out.println("\n 어항 관리 메뉴 \n");
					
//					어항 번호 | 어항 이름 목록
					List<Tank> tankInfo;
					try {
						tankInfo = service.tankInfo(loginMember);
						
						if(tankInfo.isEmpty()) {
							System.out.println("\n[등록된 어항이 없습니다.]\n");
						} else {
							System.out.println("| 번호 |   어항 이름   |");
							for(Tank t : tankInfo) {
								System.out.printf("| %4d | %10s |\n"
										, t.getTankNo(), t.getTankName());
							}
						}
					} catch (Exception e) {
						System.out.println("어항 목록을 불러오는 도중 오류가 발생하였습니다.");
						e.printStackTrace();
					}
					
					System.out.println();
					System.out.println("1. 내 어항 선택");
					System.out.println("2. 새 어항 추가");
					System.out.println("3. 로그아웃");
					System.out.println("0. 프로그램 종료");
					
					System.out.print("메뉴 선택 >> ");
					input = sc.nextInt();
					sc.nextLine();
					System.out.println();
					
					switch(input) {
					case 1: selectTank(); break;
					case 2: newAquarium(); break;
					case 3: loginMember = null; break;
					case 0: System.out.println("[프로그램을 종료합니다.]"); break;
					default: System.out.println("메뉴에 있는 번호만 입력해주세요.");
					}
				
				}	
			} catch (InputMismatchException e) {
				System.out.println("입력 형식이 올바르지 않습니다.");
				sc.nextLine();
			}
			
		} while (input != 0);
	}

	
	
	private void signUp() {
		
		System.out.println("[회원 가입]");
		
		String memberId = null;

		String memberPw1 = null;
		String memberPw2 = null;

		String memberName = null;
		String memberGender = null;

		try {
			while(true) {
				System.out.print("아이디: ");
				memberId = sc.next();
				
				int result = service.idDupCheck(memberId);
				
				System.out.println();
				
				if(result == 0) {
					System.out.println("[사용 가능한 아이디입니다.]");
					break;
				} else {
					System.out.println("[이미 사용중인 아이디입니다.]");
				}
				System.out.println();
			}
			
			
			
		} catch (Exception e) {
			System.out.println("회원 가입 도중 오류가 발생하였습니다.");
			e.printStackTrace();
		}
		
		while(true) {
			System.out.print("비밀번호: ");
			memberPw1 = sc.next();
			
			System.out.print("비밀번호 확인: ");
			memberPw2 = sc.next();
			
			System.out.println();
			
			if(memberPw1.equals(memberPw2)) {
				System.out.println("[비밀번호가 일치합니다.]");
				break;
			} else {
				System.out.println("[비밀번호가 일치하지 않습니다. 다시 입력해주세요.]");
			}
			System.out.println();
			
		}
		
		System.out.print("이름: ");
		memberName = sc.next();
		
		Member member = new Member(memberId, memberPw2, memberName);
		
		int result = 0;
		try {
			result = service.signUp(member);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println();
		if(result > 0) {
			System.out.println("[회원 가입이 완료되었습니다.]");
		} else {
			System.out.println("[회원 가입이 실패하였습니다.]");
		}
		System.out.println();
		
				
	}

	private void login() {
		
		System.out.println("[로그인]");
		
		System.out.print("아이디: ");
		String memberId = sc.next();
		
		System.out.print("비밀번호: ");
		String memberPw = sc.next();
		
		try {
			loginMember = service.login(memberId, memberPw);
			
			System.out.println();
			if(loginMember != null) {
				System.out.println(loginMember.getMemberName() + "님 환영합니다.");
			} else {
				System.out.println("아이디 또는 비밀번호가 일치하지 않습니다.");
			}
			System.out.println();
		} catch (Exception e) {
			System.out.println("로그인 도중 오류가 발생하였습니다.");
			e.printStackTrace();
		}
		
		
	}
	
	
	private void selectTank() {
	
		System.out.println("[어항 선택]");
		
		System.out.print("어항 번호: ");
		int tankNo = sc.nextInt();
		
		memberView.memberMenu(loginMember, tankNo);
		
	}
	
	
	
	
	
	private void newAquarium() {
		
		
		
	}
	
	
	

}
