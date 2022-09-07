package edu.kh.emp.view;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import edu.kh.emp.model.dao.EmployeeDAO;
import edu.kh.emp.model.vo.Employee;

// 화면용 클래스( 입력(Scanner) / 출력(print()) )
public class EmployeeView {

	private Scanner sc = new Scanner(System.in);
	
	// DAO 객체 생성
	private EmployeeDAO dao = new EmployeeDAO();
	
	
	
	
	// 메인 메뉴
	public void displayMenu() {
		
		int input = 0;
		
		do {
			try {
				System.out.println("---------------------------------------------------------");
				System.out.println("----- 사원 관리 프로그램 -----");
				System.out.println("1. 새로운 사원 정보 추가");
				System.out.println("2. 전체 사원 정보 조회");
				System.out.println("3. 사번이 일치하는 사원 정보 조회");
				System.out.println("4. 사번이 일치하는 사원 정보 수정");
				System.out.println("5. 사번이 일치하는 사원 정보 삭제");
				System.out.println("6. 입력 받은 부서와 일치하는 모든 사원 정보 조회");
				System.out.println("7. 입력 받은 급여 이상을 받는 모든 사원 정보 조회");
				System.out.println("8. 부서별 급여 합 전체 조회");
				System.out.println("9. 주민등록번호가 일치하는 사원 정보 조회");
				System.out.println("0. 프로그램 종료");
				
				System.out.print("메뉴 선택 >> ");
				input = sc.nextInt();
				
				System.out.println();				
				
				
				switch(input) {
				case 1:  insertEmployee();   break;
				case 2:  selectAll();  break;
				case 3:  selectEmpId();  break;
				case 4:     break;
				case 5:     break;
				case 6:     break;
				case 7:     break;
				case 8:     break;
				case 9:  selectEmpNo();   break;
				case 0:  System.out.println("프로그램을 종료합니다...");   break;
				default: System.out.println("메뉴에 존재하는 번호만 입력하세요.");
				}
				
				
			}catch(InputMismatchException e) {
				System.out.println("정수만 입력해주세요.");
				input = -1; // 반복문 첫 번째 바퀴에서 잘못 입력하면 종료되는 상황을 방지
				sc.nextLine(); // 입력 버퍼에 남아있는 잘못 입력된 문자열 제거해서
							   // 무한 반복 방지
			}
			
		}while(input != 0);
		
	}
	
	
	/**
	 * 전체 사원 정보 조회
	 */
	public void selectAll() {
		System.out.println("<전체 사원 정보 조회>");
		
		// DB에서 전체 사원 정보를 조회하여 List<Employee> 형태로 반환하는
		// dao.selectAll() 메서드 호출
		List<Employee> empList = dao.selectAll();
		
		printAll(empList);
	}
	
	
	/** 전달받은 사원 List 모두 출력
	 * @param empList
	 */
	public void printAll(List<Employee> empList) {

		if(empList.isEmpty()) {
			System.out.println("조회된 사원 정보가 없습니다.");
			
		} else {
			System.out.println("사번 |   이름  | 주민 등록 번호 |        이메일        |   전화 번호   | 부서 | 직책 | 급여" );
			System.out.println("------------------------------------------------------------------------------------------------");
			for(Employee emp : empList) { 
				System.out.printf(" %2d  | %4s | %s | %20s | %s | %s | %s | %d\n",
						emp.getEmpId(), emp.getEmpName(), emp.getEmpNo(), emp.getEmail(), 
						emp.getPhone(), emp.getDepartmentTitle(), emp.getJobName(), emp.getSalary());
			}
		}
		
	}
	
	
	/**
	 * 사번이 일치하는 사원 정보 조회
	 */
	public void selectEmpId() {

		System.out.println("<사번이 일치하는 사원 정보 조회>");
		
//		사번 입력 받기
		int empId = inputEmpId();
		
//		입력 받은 사번을 DAO의 selectEmpId() 메서드로 전달하여
//		조회된 사원 정보 반환 받기
		Employee emp = dao.selectEmpId(empId);
		
		printOne(emp);
	}
	
	
	/**
	 * 사번을 입력받아 반환하는 메서드
	 * @return empId
	 */
	public int inputEmpId() {
		System.out.print("사번 입력: ");
		int empId = sc.nextInt();
		sc.nextLine(); //버퍼에 남은 개행문자 제거
		
		return empId;
	}
	
	
	/**
	 * 
	 * @param emp
	 */
	public void printOne(Employee emp) {
		
		if(emp == null) {
			System.out.println("조회된 사원 정보가 없습니다.");
			
		} else {
			System.out.println("사번 |   이름  | 주민 등록 번호 |        이메일        |   전화 번호   | 부서 | 직책 | 급여" );
			System.out.println("------------------------------------------------------------------------------------------------");
			System.out.printf(" %2d  | %4s | %s | %20s | %s | %s | %s | %d\n",
					emp.getEmpId(), emp.getEmpName(), emp.getEmpNo(), emp.getEmail(), 
					emp.getPhone(), emp.getDepartmentTitle(), emp.getJobName(), emp.getSalary());
		}
		
	}
	
	
	/**
	 * 주민등록번호가 일치하는 사원 정보 조회
	 */
	public void selectEmpNo() {
		System.out.println("<주민등록번호가 일치하는 사원 정보 조회>");
		
		System.out.print("주민등록번호 입력: ");
		String empNo = sc.next();
		
		Employee emp = dao.selectEmpNo(empNo);
		
		printOne(emp);
	}
	
	
	/**
	 * 사원 정보 추가
	 */
	public void insertEmployee() {
		System.out.println("<새로운 사원 정보 추가>");
		
//		사번
		int impId = inputEmpId();
		
//		이름
		System.out.print("이름: ");
		String empName = sc.next();
		
//		주민등록번호
		System.out.print("주민등록번호: ");
		String empNo = sc.next();
		
		System.out.print("이메일: ");
		String email = sc.next();
		
		System.out.print("전화번호(-제외): ");
		String phone = sc.next();
		
		System.out.print("부서코드(D1~D9): ");
		String deptCode = sc.next();
		
		System.out.print("직급코드(J1~J7): ");
		String jobCode = sc.next();
		
		System.out.print("급여등급(S1~S6): ");
		String salLevel = sc.next();
		
		System.out.print("급여: ");
		int salary = sc.nextInt();
		
		System.out.print("보너스: ");
		double bonus = sc.nextDouble();
		
		System.out.print("사수번호: ");
		int managerId = sc.nextInt();
		
//		입력 받은 값을 Employee 객체에 담아서 DAO로 전달
		Employee emp = new Employee(impId, empName, empNo, email, phone, 
									salary, deptCode, jobCode, salLevel, bonus, managerId);
		
		int result = dao.insertEmployee(emp);
//		INSERT, UPDATE, DELETE 같은 DML 구문은
//		수행 후 테이블에 반영된 행의 개수를 반환함
//		-> 조건이 잘못된 경우 반영된 행이 없으므로 0 반환
		
		if(result > 0) { // DML 구문 성공 시
			System.out.println("사원 정보 추가 성공");
		} else { // DML 구문 실패 시
			System.out.println("사원 정보 추가 실패...");
		}
		
		
	}
	
	
	
	
}