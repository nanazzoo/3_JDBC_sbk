package am.breeding.view;

import java.util.Collection;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import am.breeding.model.service.BreedingService;
import am.breeding.model.service.EventService;
import am.breeding.model.vo.Breeding;
import am.breeding.model.vo.Event;
import am.livestock.model.service.LivestockService;
import am.livestock.vo.Livestock;
import am.main.view.AquariumView;

public class BreedingView {
	private Scanner sc = new Scanner(System.in);
	private BreedingService bService = new BreedingService();
	private EventService eService = new EventService();
	private LivestockService lService = new LivestockService();
	private List<Breeding> breedingList;


	int input = -1;
	
	public void breedingMenu() {
		do {
			try {
				System.out.println("--------------------------------------------------------------");
				System.out.println("\n*** Aquarium Management ***\n");
				System.out.println("\n 브리딩 메뉴 \n");

				int memberNo = AquariumView.loginMember.getMemberNo();
				String endFl = null;
				try {
					breedingList = bService.selectAllBreeding(memberNo);
					
					if(breedingList.isEmpty()) {
						System.out.println("\n[조회 결과가 없습니다.]\n");
					} else {
						System.out.println("\n[내 브리딩 목록]\n");
						for(Breeding b : breedingList) {
							if(b.getEndFl().equals("Y")) endFl = "완료";
							if(b.getEndFl().equals("N")) endFl = "진행 중";
							
							System.out.printf("%d. %s      | %s | %s\n"
									, b.getBreedingNo()
									, b.getBreedSpecies1()
									, b.getStartDate()
									, endFl);
						}
					}
					System.out.println("--------------------------------------------------------------");
				} catch (Exception e) {
					System.out.println("\n[브리딩 목록 조회 중 예외가 발생했습니다.]\n");
					e.printStackTrace();
				}
				
				
				System.out.println();
				System.out.println("1. 브리딩 상세 조회");
				System.out.println("2. 새 브리딩 추가");
				System.out.println("0. 뒤로");
				
				System.out.print("메뉴 선택 >> ");
				input = sc.nextInt();
				sc.nextLine();
				System.out.println();
				System.out.println("--------------------------------------------------------------");
				switch(input) {
				case 1: selectBreeding(); break;
				case 2: insertBreeding(); break;
				case 0: System.out.println("\n[로그인 메뉴로 이동합니다.]\n"); break;
				default: System.out.println("\n[메뉴에 있는 번호만 입력해주세요.]\n");
				}
				
			} catch (InputMismatchException e) {
				System.out.println("\n[입력 형식이 올바르지 않습니다.]\n");
				sc.nextLine();
			}
			
		} while (input != 0);
		
	}

	

	/** 브리딩 상세 조회
	 * 
	 */
	private void selectBreeding() {
		int memberNo = AquariumView.loginMember.getMemberNo();
		int breedingNo = 0;
		
		try {
			System.out.println("\n[브리딩 상세 조회]\n");
			
			boolean flag = true;
			
			if(breedingList.isEmpty()) {
				System.out.println("\n[조회할 목록이 없습니다.]\n");
			} else {
				System.out.print("상세 조회할 번호: ");
				breedingNo = sc.nextInt();
				sc.nextLine();
				
				System.out.println();
				for(Breeding b : breedingList) {			
					if(b.getBreedingNo() == breedingNo) {
						flag = false;
						
						Breeding breeding = bService.selectBreeding(memberNo, breedingNo);
						
						System.out.println(" --------------------------------------------------------");
						System.out.println();
						System.out.println("브리딩 번호: " + breeding.getBreedingNo()+"     시작일: " + breeding.getStartDate() + "\n");
						System.out.println("브리딩 생물1: " + breeding.getBreedSpecies1() + "  from tankNo." + breeding.getFromTankNo1());
						System.out.println("브리딩 생물2: " + breeding.getBreedSpecies2() + "  from tankNo." + breeding.getFromTankNo2());
						
						System.out.println();
						System.out.println(" --------------------------------------------------------");
						
						if(!breeding.getEventList().isEmpty()) {
							for(Event c : breeding.getEventList()) {
								System.out.printf("DATE: %s     EVENT NO.%d "
										+ " \n%s\n",
										c.getRegDate(), c.getEventNo(), c.getEventContent());
								System.out.println(" --------------------------------------------------------");
							}
						}
						
						// 댓글 등록, 수정, 삭제
						// 게시글 수정/삭제 메뉴
						subBreedingMenu(breeding);
						
					}
				}
				
				System.out.println();
			}
			
			if(flag) {
				System.out.println("\n[일치하는 브리딩 번호가 없습니다.]\n");
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	
	/** 브리딩 상세 조회 서브 메뉴
	 * @param breeding
	 */
	private void subBreedingMenu(Breeding breeding) {
		try {
			System.out.println("1. 이벤트 등록");
			System.out.println("2. 이벤트 수정");
			System.out.println("3. 이벤트 삭제");
			System.out.println("4. 브리딩 종료");
			System.out.println("5. 브리딩 삭제");
			
			System.out.println("0. 브리딩 메뉴로 돌아가기");
			
			
			System.out.print("\n서브 메뉴 선택 : ");
			int input = sc.nextInt();
			sc.nextLine();
			
			System.out.println("--------------------------------------------------------------");
			
			int memberNo = AquariumView.loginMember.getMemberNo();
			
			switch(input) {
			case 1 : insertEvent(breeding.getBreedingNo(), memberNo); break;
			case 2 : updateEvent(breeding.getEventList(), memberNo);  break;
			case 3 : deleteEvent(breeding.getEventList(), memberNo);  break;
			case 4 : completeBreeding(breeding.getBreedingNo());  break;
			case 5 : deleteBreeding(breeding.getBreedingNo());  break;
			case 0 : System.out.println("\n[브리딩 메뉴로 돌아갑니다...]\n");  break;
			default : System.out.println("\n[메뉴에 작성된 번호만 입력 해주세요.]\n");
			}
			

			if(input > 0 && input < 5) {
				try {
					breeding = bService.selectBreeding(memberNo, breeding.getBreedingNo());
					
					System.out.println(" --------------------------------------------------------");
					System.out.println();
					System.out.println("브리딩 번호: " + breeding.getBreedingNo()+"     시작일: " + breeding.getStartDate() + "\n");
					System.out.println("브리딩 생물1: " + breeding.getBreedSpecies1() + "  from tankNo." + breeding.getFromTankNo1());
					System.out.println("브리딩 생물2: " + breeding.getBreedSpecies2() + "  from tankNo." + breeding.getFromTankNo2());
					
					System.out.println();
					System.out.println(" --------------------------------------------------------");
					
					if(!breeding.getEventList().isEmpty()) {
						for(Event c : breeding.getEventList()) {
							System.out.printf("DATE: %s     EVENT NO.%d "
									+ " \n%s\n",
									c.getRegDate(), c.getEventNo(), c.getEventContent());
							System.out.println(" --------------------------------------------------------");
						}
	               }
	            }catch (Exception e) {
	               e.printStackTrace();
	            }
				
				subBreedingMenu(breeding);
			}
			
		}catch (InputMismatchException e) {
			System.out.println("\n<<입력 형식이 올바르지 않습니다.>>");
			sc.nextLine();
		}
		
		
	}

	
	 
	/** 이벤트 등록
	 * @param breedingNo
	 * @param memberNo
	 */
	private void insertEvent(int breedingNo, int memberNo) {
		try {
			System.out.println("\n[댓글 등록]\n");
			
			String content = inputContent();
			
			System.out.print("날짜 입력('YYYY-MM-DD'): ");
			String regDate = sc.next();

			Event event = new Event();
			event.setEventContent(content);
			event.setBreedingNo(breedingNo);
			event.setMemberNo(memberNo);
			event.setRegDate(regDate);
			
			// 댓글 삽입 서비스 호출 후 결과 반환 받기
			int result = eService.insertEvent(event);
			
			if(result > 0) {
				System.out.println("\n[이벤트 등록 성공]\n");
			}else {
				System.out.println("\n[이벤트 등록 실패...]\n");
			}
			
		}catch (Exception e) {
			System.out.println("\n<<이벤트 등록 중 예외 발생>>\n");
			e.printStackTrace();
		}
		
	}

	private void updateEvent(List<Event> eventList, int memberNo) {
		try {
			System.out.println("\n[이벤트 수정]\n");
			
			System.out.print("수정할 이벤트 번호 입력 : ");
			int eventNo = sc.nextInt();
			sc.nextLine();
			
			boolean flag = true;
			
			for(Event e : eventList) {
				
				if(e.getEventNo() == eventNo) { // 댓글 번호 일치
					flag = false; 
					
					
					// 수정할 이벤트 내용 입력 받기
					String content = inputContent();
					
					// 이벤트 수정 서비스 호출
					int result = eService.updateEvent(eventNo, content);
					
					if(result > 0) {
						System.out.println("\n[이벤트 수정 성공]\n");
					}else {
						System.out.println("\n[이벤트 수정 실패...]\n");
					}
			
					break;
				}
				
			} // for end
			
			if(flag) {
				System.out.println("\n[번호가 일치하는 이벤트가 없습니다.]\n");
			}
			
		}catch (Exception e) {
			System.out.println("\n<<이벤트 수정 중 예외 발생>>\n");
		}
		
	}

	
	
	/** 이벤트 삭제
	 * @param eventList
	 * @param memberNo
	 */
	private void deleteEvent(List<Event> eventList, int memberNo) {
		try {
			System.out.println("\n[이벤트 삭제]\n");
			
			System.out.print("삭제할 이벤트 번호 입력 : ");
			int eventNo = sc.nextInt();
			sc.nextLine();
			
			boolean flag = true;
			
			for(Event e : eventList) {
				
				if(e.getEventNo() == eventNo) { // 댓글 번호 일치
					flag = false; 
						
					System.out.print("정말 삭제 하시겠습니까? (Y/N) : ");
					char ch = sc.next().toUpperCase().charAt(0);
					
					if(ch == 'Y') {
						int result = eService.deleteEvent(eventNo);
						
						if(result > 0) {
							System.out.println("\n[이벤트 삭제 성공]\n");
						}else {
							System.out.println("\n[이벤트 삭제 실패...]\n");
						}
							
					}else {
						System.out.println("\n[취소 되었습니다.]\n");
					}
					
					break; // 더 이상의 검사 불필요
				}
				
			} // for end
			
			if(flag) {
				System.out.println("\n[번호가 일치하는 이벤트가 없습니다.]\n");
			}
			
		}catch (Exception e) {
			System.out.println("\n<<이벤트 삭제 중 예외 발생>>\n");
		}
		
		
		
		
	}
	
	
	
	/** 브리딩 추가
	 * 
	 */
	private void insertBreeding() {
		try {
			int memberNo = AquariumView.loginMember.getMemberNo();
			int maxBreedingNo = bService.maxBreedingNo(memberNo);
			Livestock livestock1 = new Livestock();
			Livestock livestock2 = new Livestock();
			Breeding breeding = new Breeding();
			int result = 0;
			
			List<Livestock> livestockList = lService.selectLivestock(memberNo);
			
			if(livestockList.isEmpty()) {
				System.out.println("\n[등록된 생물이 없습니다.]\n");
				System.out.println("\n[브리딩 메뉴로 돌아값니다.]\n");
			} else {
				for(int i = 1; i <= 2; i++) {
					System.out.println("\n[브리딩 개체 " + i +"]");
				
					System.out.println("\n[내 생물 목록]\n");
				
					for(Livestock ls : livestockList) {
						System.out.printf("%d | %d |  %s |  %s  | %d | %s | %s |\n",
								ls.getTankNo()
								, ls.getLivestockNo()
								, ls.getLivestockSpecies()
								, ls.getLivestockName()
								, ls.getLivestockPrice()
								, ls.getLivestockGender()
								, ls.getDateOfPurchase());
					}
				
					System.out.println("추가할 어항 번호: ");
					int tankNum = sc.nextInt();
					
					System.out.println("추가할 생물 번호: ");
					int livestockNum = sc.nextInt();
					sc.nextLine();
					
					if(i==1)livestock1 = findUsingIterator(tankNum, livestockNum, livestockList);
					if(i==2)livestock2 = findUsingIterator(tankNum, livestockNum, livestockList);
				}
						
				System.out.println("오늘 날짜를 시작일로 지정하려면 'DEFAULT'를 입력해주세요.");
				System.out.print("브리딩 시작일('YYYY-MM-DD'): ");
				String startDate = sc.next();
				
				breeding.setBreedSpecies1(livestock1.getLivestockName());
				breeding.setFromTankNo1(livestock1.getTankNo());
				breeding.setBreedSpecies2(livestock2.getLivestockName());
				breeding.setFromTankNo2(livestock2.getTankNo());
				breeding.setMemberNo(memberNo);
				breeding.setBreedingNo(maxBreedingNo);
				breeding.setStartDate(startDate);
			
				if(breeding.getStartDate().equals("DEFAULT") || breeding.getStartDate().equals("") ||breeding.getStartDate()==null) {
					result = bService.insertBreeding2(breeding);
					System.out.println("디폴트");
				} else {
					result = bService.insertBreeding(breeding);
				}
				
				if(result > 0) {
					System.out.println("\n[브리딩 추가 성공]\n");
				}else {
					System.out.println("\n[브리딩 추가 실패...]\n");
				}
			} 
		
		
		} catch (Exception e) {
			System.out.println("\n[브리딩 추가 도중 예외가 발생했습니다.]\n");
			e.printStackTrace();
		}
		
	}
	
	
	/** 생물 번호가 일치하는 생물 찾기
	 * @param num
	 * @param livestockList
	 * @return
	 */
	public Livestock findUsingIterator(int tankNum, int livestockNum, List<Livestock> livestockList) {
			    Iterator<Livestock> iterator = livestockList.iterator();
			    while (iterator.hasNext()) {
			        Livestock livestock = iterator.next();
			        if(livestock.getTankNo()==tankNum) {
			        	if (livestock.getLivestockNo()==(livestockNum)) {
			        		return livestock;
			        	}
			        }
			    }
			    return null;
			}

	
	
	
	/** 브리딩 완료
	 * @param breedingNo
	 */
	private void completeBreeding(int breedingNo) {
		try {
			int memberNo = AquariumView.loginMember.getMemberNo();
			
			System.out.print("정말 완료 하시겠습니까? (Y/N) : ");
			char ch = sc.next().toUpperCase().charAt(0);
			
			if(ch == 'Y') {
				int result;
					result = bService.completeBreeding(memberNo, breedingNo);
				
				if(result > 0) {
					System.out.println("\n[브리딩을 완료처리했습니다]\n");
				}else {
					System.out.println("\n[브리딩 완료 처리 실패...]\n");
				}
			}
		} catch (Exception e) {
			System.out.println("\n[브리딩 완료 처리 도중 예외가 발생했습니다.]\n");
			e.printStackTrace();
		}
	}

	
	
	/** 브리딩 삭제
	 * @param breedingNo
	 */
	private void deleteBreeding(int breedingNo) {
		try {
			int memberNo = AquariumView.loginMember.getMemberNo();
			
			System.out.print("정말 삭제 하시겠습니까? (Y/N) : ");
			char ch = sc.next().toUpperCase().charAt(0);
			
			if(ch == 'Y') {
				int result;
					result = bService.deleteBreeding(memberNo, breedingNo);
				
				if(result > 0) {
					System.out.println("\n[브리딩 삭제 성공]\n");
				}else {
					System.out.println("\n[브리딩 삭제 실패...]\n");
				}
			}
		} catch (Exception e) {
			System.out.println("\n[브리딩 삭제 도중 예외가 발생했습니다.]\n");
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 *  내용 입력 
	 *  @return content
	 */
	private String inputContent() {
		String content = ""; // 빈 문자열
		String input = null; // 참조하는 객체가 없음
		
		System.out.println("입력 종료 시 ($exit) 입력");
		
		while(true) {
			input = sc.nextLine();
			
			if(input.equals("$exit")) {
				break;
			}
			
			// 입력된 내용을 content에 누적
			content += input + "\n"; 
		}
		
		return content;
	}
	
	
	
}
