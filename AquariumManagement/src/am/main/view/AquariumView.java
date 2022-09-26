package am.main.view;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import am.main.model.service.AquariumService;
import am.member.view.MemberView;
import am.member.vo.Member;
import am.tank.vo.Tank;

public class AquariumView {
	
	private AquariumService service = new AquariumService();
	private MemberView memberView = new MemberView();
	private Scanner sc = new Scanner(System.in);
	int input = -1;
	
	public static Member loginMember = null;
	private List<Tank> tankInfo;

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
//					어항 추가할 때 어항 번호 자동 생성하는 SQL 구문에서
//					MAX(TANK_NO) 조회할 때 추가하려는 어항이 첫 어항이면
//					MAX(TANK_NO)가 NULL로 나오기 때문에 SQL구문 수정해야함.
					System.out.println("2. 새 어항 추가");
					System.out.println("3. 물량 계산기");
					System.out.println("3. 로그아웃");
					System.out.println("0. 프로그램 종료");
					
					System.out.print("메뉴 선택 >> ");
					input = sc.nextInt();
					sc.nextLine();
					System.out.println();
					
					switch(input) {
					case 1: selectTank(); break;
					case 2: insertTank(); break;
					case 3: waterCalculator(); break;
					case 4: loginMember = null; break;
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
		
		for(Tank t : tankInfo) {
			if(t.getTankNo() == tankNo) {
				memberView.memberMenu(loginMember, tankNo);
				break;
			} 
		}
		System.out.println("\n번호가 일치하는 어항이 없습니다.\n");
		
	}
	
	
	
	/** 
	 * 새 어항 추가
	 */
	private void insertTank() {
		try {
			System.out.println("\n[새 어항 추가]\n");
			
			int memberNo = loginMember.getMemberNo();
			
			int maxTankNo = service.maxTankNo(memberNo);
			
			
			System.out.print("어항 이름: ");
			String tankName = sc.nextLine();
			
			String freshSalt = null;
			while(true) {
				System.out.print("해수/담수 여부(S/F): ");
				freshSalt = sc.next().toUpperCase();
				sc.nextLine();
				
				if(freshSalt.equals("S") || freshSalt.equals("F")) {
					break;
				} else {
					System.out.println("S 또는 F만 입력 가능합니다.");
				}
			}
			
			System.out.print("어항 크기: ");
			String tankSize = sc.nextLine();
			
			System.out.print("여과 방식: ");
			String tankFilter = sc.nextLine();
			
			System.out.print("어항 조명: ");
			String tankLight = sc.nextLine();
			
			System.out.print("어항 첨가제: ");
			String tankAddictive = sc.nextLine();
			
			System.out.print("어항 바닥재: ");
			String tankSubstrate = sc.nextLine();
			
			Tank tank = new Tank(memberNo, tankName, freshSalt, tankSize, tankFilter, tankLight, tankAddictive, tankSubstrate);
			
			
			int result = service.insertTank(tank, maxTankNo);
			
			if(result > 0) {
				System.out.println("\n[어항 추가 완료]\n");
			} else {
				System.out.println("\n[어항 추가 실패]\n");
				
			}
			
		} catch (Exception e) {
			System.out.println("\n[어항 추가 중 예외 발생]\n");
			e.printStackTrace();
		}
		
		
		
	}
	
	private void waterCalculator() {
		System.out.println("\n[물량 계산기]\n");
		
		int input = -1;
		
		System.out.println("1. 하단 섬프");
		System.out.println("2. 내부 배면, 탱크항");
		
		System.out.print("메뉴 선택 >> ");
		input = sc.nextInt();
		sc.nextLine();
		
		System.out.print("가로(cm): ");
		int width = sc.nextInt();
		System.out.print("세로(cm): ");
		int length = sc.nextInt();
		System.out.print("높이(cm): ");
		int height = sc.nextInt();
		
		double result = 0;
		
		switch(input) {
		case 1: 
			System.out.print("섬프 가로(cm): ");
			int sumpWidth = sc.nextInt();
			System.out.print("섬프 세로(cm): ");
			int sumpLength = sc.nextInt();
			System.out.print("섬프 높이(cm): ");
			int sumpHeight = sc.nextInt();
			
			result = (((width*length*height)/1000)*0.9) + (((sumpWidth*sumpLength*sumpHeight)/1000)*0.9);
			break;
		
		case 2: 
			result = (((width*length*height)/1000)*0.9);
			break;
		default:
		}
		
		System.out.println("\n*** 대략적인 추산치일 뿐 정확한 물량이 아닙니다. ***\n");
		System.out.println("물량: " + result + "L");
		
	}
	
	

}
