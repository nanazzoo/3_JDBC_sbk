package am.parameter.view;

import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import am.livestock.vo.Livestock;
import am.main.view.AquariumView;
import am.member.vo.Member;
import am.parameter.model.service.ParameterService;
import am.parameter.vo.Parameter;
import am.tank.vo.Tank;

public class ParameterView {
	private ParameterService service = new ParameterService();
	private Scanner sc = new Scanner(System.in);
	
	public void parameterMenu(int tankNo) {
		int input = -1;
		
		do {
			
			try {
				int memberNo = AquariumView.loginMember.getMemberNo();
				Parameter parameter = new Parameter();
				parameter.setMemberNo(memberNo);
				parameter.setTankNo(tankNo);
				
				int count = service.countParemeter(parameter);
				
				if(count == 0) {
					System.out.println("\n[등록된 물성치가 없습니다]\n");
				} else if(count == 1) {
					Parameter cParameter = service.selectCParameter(parameter);
					
					System.out.println("\n[최근 물성치]\n");
						
						System.out.println();
						System.out.println("등록일  : " + cParameter.getRegDate());
						System.out.println("온도    : " + cParameter.getParamTemp());
						System.out.println("질산염  : " + cParameter.getParamNo3());
						System.out.println("인산염  : " + cParameter.getParamPo4());
						System.out.println("경도    : " + cParameter.getParamKh());
						System.out.println("칼슘    : " + cParameter.getParamCa());
						System.out.println("마그네슘: " + cParameter.getParamMg());
						System.out.println();
					
					
					
				} else {
					Parameter cParameter = service.selectCParameter(parameter);
					Parameter pParameter = service.selectPParameter(parameter);

					 
					System.out.println("\n[최근 물성치 비교]\n");
					
					System.out.println();
					System.out.println("등록일  : " + pParameter.getRegDate()  +"      " + cParameter.getRegDate());
					System.out.println("온도    : " + pParameter.getParamTemp()  +"  ->  "+ cParameter.getParamTemp());
					System.out.println("질산염  : " + pParameter.getParamNo3()  + "  ->  "+cParameter.getParamNo3());
					System.out.println("인산염  : " + pParameter.getParamPo4()  +"  ->  "+cParameter.getParamPo4());
					System.out.println("경도    : " + pParameter.getParamKh()  +"  ->  "+cParameter.getParamKh());
					System.out.println("칼슘    : " + pParameter.getParamCa()  +"  ->  " + cParameter.getParamCa());
					System.out.println("마그네슘: " + pParameter.getParamMg()  +"  ->  "+ cParameter.getParamMg());
					System.out.println();
					
				}
				
				
				
			} catch (Exception e) {
				System.out.println("\n[물성치를 불러오는 도중 오류가 발생하였습니다.]\n");
				e.printStackTrace();
			}
			
			try {
				
				System.out.println("\n*** Aquarium Management ***\n");
				System.out.println("[물성치 메뉴]");
				System.out.println("1. 물성치 추가");
				System.out.println("2. 물성치 삭제");
				System.out.println("0. 뒤로");
				
				System.out.print("메뉴 선택 >> ");
				input = sc.nextInt();
				sc.nextLine();
				System.out.println();
				
				switch(input) {
				case 1: insertParameter(tankNo); break;
				case 2: deleteParameter(tankNo); break;
				case 0: System.out.println("\n[로그인 메뉴로 돌아갑니다.]\n"); break;
				default: System.out.println("\n[메뉴에 있는 번호만 입력해주세요.]\n");
				}
				
			} catch (InputMismatchException e) {
				System.out.println("\n[숫자만 입력해주세요.]\n");
				input = -1;
				sc.nextLine();
			}
			
		} while (input != 0);
		
		
	}

	
	/** 물성치 기록
	 * @param tankNo
	 */
	private void insertParameter(int tankNo) {
		try {
			System.out.println("\n[물성치 기록]\n");
			
			int memberNo = AquariumView.loginMember.getMemberNo();
				
			
			System.out.print("온도: ");
			double paramTemp = sc.nextDouble();
			
			System.out.print("질산염: ");
			double paramNo3 = sc.nextDouble();
			
			System.out.print("인산염: ");
			double paramPo4 = sc.nextDouble();
			
			System.out.print("경도: ");
			double paramKh = sc.nextDouble();
			
			System.out.print("칼슘: ");
			double paramCa = sc.nextDouble();
			
			System.out.print("마그네슘: ");
			double paramMg = sc.nextDouble();
			
			Parameter parameter = new Parameter();
			parameter.setMemberNo(memberNo);
			parameter.setTankNo(tankNo);
			parameter.setParamTemp(paramTemp);
			parameter.setParamNo3(paramNo3);
			parameter.setParamPo4(paramPo4);
			parameter.setParamKh(paramKh);
			parameter.setParamCa(paramCa);
			parameter.setParamMg(paramMg);
			
			
			int result = service.insertParameter(parameter);
			
			if(result > 0) {
				System.out.println("\n[물성치 등록 완료]\n");
			} else {
				System.out.println("\n[물성치 등록 실패]\n");
				
			}
			
		} catch (Exception e) {
			System.out.println("\n[물성치 등록 중 예외 발생]\n");
			e.printStackTrace();
		}
		
		
	}
	
	/** 물성치 삭제
	 * @param tankNo
	 */
	private void deleteParameter(int tankNo) {
		try {
			System.out.println("\n[물성치 기록 삭제]\n");
			
			int memberNo = AquariumView.loginMember.getMemberNo();
			
			
			System.out.println("삭제할 물성치 번호: ");
			int parameterNo = sc.nextInt();
			
			Parameter parameter = new Parameter();
			parameter.setMemberNo(memberNo);
			parameter.setTankNo(tankNo);
			parameter.setParameterNo(parameterNo);
			
			System.out.println("정말 삭제하시겠습니까?(Y/N): ");
			String input = sc.next().toLowerCase();
			
			if(input.equals("y")) {
				int result = service.deleteParameter(parameter);
				
				if(result > 0) {
					System.out.println("\n[물성치 삭제 완료]\n");
				} else {
					System.out.println("\n[물성치 삭제 실패]\n");
					
				}
			}
			else {
				System.out.println("삭제를 취소합니다.");
			}
			
		} catch (Exception e) {
			System.out.println("\n[물성치 삭제 중 예외 발생]\n");
			e.printStackTrace();
		}
		
	}

	
	

}
