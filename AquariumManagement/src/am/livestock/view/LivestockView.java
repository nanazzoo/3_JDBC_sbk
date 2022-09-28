package am.livestock.view;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import am.livestock.model.service.LivestockService;
import am.livestock.vo.Livestock;
import am.main.view.AquariumView;
import am.tank.vo.Tank;

public class LivestockView {
	private Scanner sc = new Scanner(System.in);
	private LivestockService service = new LivestockService();

	
	public void livestockMenu(int tankNo) {
		int input = -1;
		
		do {
			try {
				int memberNo = AquariumView.loginMember.getMemberNo();
				
				List<Livestock> livestockList = service.selectLivestock(memberNo, tankNo);
				
				if(livestockList.isEmpty()) {
					System.out.println("--------------------------------------------------------------");
					System.out.println("\n[등록된 생물이 없습니다]\n");
					System.out.println("--------------------------------------------------------------");
				} else {
					System.out.println("--------------------------------------------------------------");
					System.out.println();
					System.out.println("\n[내 생물 목록]\n");
					for(Livestock ls : livestockList) {
						System.out.printf("%d. %s : %s  | %d | %s | %s |\n",
								ls.getLivestockNo()
								, ls.getLivestockSpecies()
								, ls.getLivestockName()
								, ls.getLivestockPrice()
								, ls.getLivestockGender()
								, ls.getDateOfPurchase());
					}
				}
				
				
			} catch (Exception e) {
				System.out.println("생물을 불러오는 도중 오류가 발생하였습니다.");
				e.printStackTrace();
			}
			
			try {
				System.out.println("--------------------------------------------------------------");
				System.out.println("\n*** Aquarium Management ***\n");
				System.out.println("[생물 관리 메뉴]");
				System.out.println("1. 생물 추가");
				System.out.println("2. 생물 정보 수정");
				System.out.println("3. 생물 삭제");
				System.out.println("0. 뒤로");
				
				System.out.print("메뉴 선택 >> ");
				input = sc.nextInt();
				sc.nextLine();
				System.out.println();
				System.out.println("--------------------------------------------------------------");
				switch(input) {
				case 1: insertLivestock(tankNo); break;
				case 2: updateLivestock(tankNo); break;
				case 3: deleteLivestock(tankNo); break;
				case 0: break;
				default: System.out.println("메뉴에 있는 번호만 입력해주세요.");
				}
				
			} catch (InputMismatchException e) {
				System.out.println("숫자만 입력해주세요.");
				e.printStackTrace();
			}
			
		} while (input != 0);
		
		
	}




	/** 생물 등록
	 * @param tankNo
	 */
	private void insertLivestock(int tankNo) {
		try {
			System.out.println("\n[생물 추가]\n");
			
			int memberNo = AquariumView.loginMember.getMemberNo();
			
			int maxLivestockNo = service.maxLivestockNo(memberNo, tankNo);
			
			
			System.out.print("생물 종류(물고기/산호/무척추): ");
			String livestockSpecies = sc.nextLine();
			
			System.out.print("생물 이름(ex)퍼큘라 크라운): ");
			String livestockName = sc.nextLine();
			
			System.out.print("생물 가격: ");
			int livestockPrice = sc.nextInt();
			
			String livestockGender = null;
			while(true) {
				System.out.print("생물 성별(M/F/모를 시 N): ");
				livestockGender = sc.next().toUpperCase();
				sc.nextLine();
				
				if(livestockGender.equals("M") || livestockGender.equals("F") || livestockGender.equals("N")) {
					break;
				} else {
					System.out.println("M 또는 F 또는 N만 입력 가능합니다.");
				}
			}
			
			
			System.out.print("생물 구입일('YYYY-MM-DD'): ");
			String dateOfPurchase = sc.next();

			Livestock livestock = new Livestock(memberNo, tankNo
					, livestockSpecies, livestockName
					, livestockPrice, livestockGender, dateOfPurchase);
			
			int result = service.insertLivestock(livestock, maxLivestockNo);
			
			if(result > 0) {
				System.out.println("\n[생물 등록 완료]\n");
			} else {
				System.out.println("\n[생물 등록 실패]\n");
				
			}
			
		} catch (Exception e) {
			System.out.println("\n[생물 추가 중 예외 발생]\n");
			e.printStackTrace();
		}
		
		
		
	}
	
	
	
	/** 생물 정보 수정 
	 * @param tankNo
	 */
	private void updateLivestock(int tankNo) {
		try {
			int input = -1;	
			String livestockGender = null;
			String lifeOrDie = null;
			
			System.out.println("\n[생물 정보 수정]\n");
			
			System.out.println("\n수정할 생물 번호 입력>> ");
			int livestockNo = sc.nextInt();
			
			System.out.println("\n[수정할 내용 선택]\n");
			System.out.println("1. 이름");
			System.out.println("2. 가격");
			System.out.println("3. 성별");
			System.out.println("4. 생사 여부");
			System.out.println("0. 취소");
			
			System.out.print("\n번호 입력 >> ");
			input = sc.nextInt();
			sc.nextLine();
			
			Livestock livestock = new Livestock();
			livestock.setTankNo(tankNo);
			livestock.setMemberNo(AquariumView.loginMember.getMemberNo());
			
			System.out.println();
			switch (input) {
			case 1:
				System.out.print("수정할 이름: ");
				String livestockName = sc.nextLine();
				sc.nextLine();
				livestock.setLivestockName(livestockName);
				break;
			case 2:
				System.out.print("수정할 가격: ");
				int livestockPrice = sc.nextInt();
				sc.nextLine();
				livestock.setLivestockPrice(livestockPrice);
				break;
			case 3:
				while(true) {
					System.out.print("수정할 성별(M/F/모를 시 N): ");
					livestockGender = sc.next().toUpperCase();
					sc.nextLine();
					
					if(livestockGender.equals("S") || livestockGender.equals("F") || livestockGender.equals("N")) {
						break;
					} else {
						System.out.println("S 또는 F 또는 N만 입력 가능합니다.");
					}
				}
				livestock.setLivestockGender(livestockGender);
				
				break;
			case 4:
				while(true) {
					System.out.print("생사 여부(L/D): ");
					lifeOrDie = sc.next().toUpperCase();
					sc.nextLine();
					if(lifeOrDie.equals("L") || lifeOrDie.equals("D")) {
						break;
					} else {
						System.out.println("L 또는 D만 입력 가능합니다.");
					}
				}
				livestock.setLifeOrDie(lifeOrDie);
				break;
			case 0: break;

			default: System.out.println("\n[메뉴에 있는 번호만 입력해주세요.]\n"); break;
			}
			System.out.println();
			
			int result = service.updateLivestock(livestock, input);
			
			if(result > 0) {
				System.out.println("\n[생물 정보 수정 완료]\n");
			} else {
				System.out.println("\n[생물 정보 수정 실패]\n");
				
			}
			
			
			
		} catch (Exception e) {
			System.out.println("\n[생물 정보 수정 중 예외 발생]\n");
			e.printStackTrace();
		}
		
	}
	
	
	
	
	

	/** 생물 삭제
	 * @param tankNo
	 */
	private void deleteLivestock(int tankNo) {
		try {
			System.out.println("\n[생물 삭제]\n");
			
			int memberNo = AquariumView.loginMember.getMemberNo();
			
			
			System.out.println("삭제할 생물 번호: ");
			int livestockNo = sc.nextInt();
			
			Livestock livestock = new Livestock();
			livestock.setMemberNo(memberNo);
			livestock.setTankNo(tankNo);
			livestock.setLivestockNo(livestockNo);
			
			System.out.println("정말 삭제하시겠습니까?(Y/N): ");
			String input = sc.next().toLowerCase();
			
			if(input.equals("y")) {
				int result = service.deleteLivestock(livestock);
				
				if(result > 0) {
					System.out.println("\n[생물 삭제 완료]\n");
				} else {
					System.out.println("\n[생물 삭제 실패]\n");
					
				}
			}
			else {
				System.out.println("삭제를 취소합니다.");
			}
			
		} catch (Exception e) {
			System.out.println("\n[생물 삭제 중 예외 발생]\n");
			e.printStackTrace();
		}
		
	}

	
	
	
	
	
	
	
	
	
}
