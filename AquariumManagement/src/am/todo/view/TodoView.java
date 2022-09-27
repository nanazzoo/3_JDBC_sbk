package am.todo.view;

import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import am.main.view.AquariumView;
import am.member.vo.Member;
import am.parameter.model.service.ParameterService;
import am.parameter.vo.Parameter;
import am.tank.vo.Tank;
import am.todo.model.service.TodoService;
import am.todo.vo.Todo;

public class TodoView {
	
	private TodoService service = new TodoService();
	private Scanner sc = new Scanner(System.in);
		
	public void todoMenu(int tankNo) {
		int input = -1;
		
		do {
			
			try {
				int memberNo = AquariumView.loginMember.getMemberNo();
				Todo todo = new Todo();
				todo.setMemberNo(memberNo);
				todo.setTankNo(tankNo);
				
				List<Todo> todoList = service.selectTodo(todo);
				
				if(todoList.isEmpty()) {
					System.out.println("\n[등록된 물성치가 없습니다]\n");
				} else {
					System.out.println("\n[예정된 할 일]\n");
					
					System.out.println("             할 일           |   기한   \n");
					for(Todo t : todoList) {
						System.out.printf("   %18s  |  %s  \n"
								,t.getTodoContent(), t.getTodoTerm());
					}
					
				}
				
			} catch (Exception e) {
				System.out.println("\n[할 일을 불러오는 도중 오류가 발생하였습니다.]\n");
				e.printStackTrace();
			}
			
			try {
				System.out.println("\n[할 일 메뉴]\n");
				System.out.println("1. 할 일 추가");
				System.out.println("2. 할 일 완료");
				System.out.println("3. 할 일 삭제");
				System.out.println("4. 완료된 할 일 보기");
				System.out.println("0. 뒤로");
				
				System.out.print("메뉴 선택 >> ");
				input = sc.nextInt();
				sc.nextLine();
				System.out.println();
				
				switch(input) {
				case 1: insertTodo(tankNo); break;
				case 2: completeTodo(tankNo); break;
				case 3: deleteTodo(tankNo); break;
				case 4: selectComTodo(tankNo); break;
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



	/** 할 일 추가
	 * @param tankNo
	 */
	private void insertTodo(int tankNo) {
		try {
			System.out.println("\n[할 일 추가]\n");
			
			int memberNo = AquariumView.loginMember.getMemberNo();
				
			
			System.out.print("할 일: ");
			String todoContent = sc.nextLine();
			
			System.out.print("완료 기한(YYYY-MM-DD): ");
			String todoTerm = sc.next();

			
			Todo todo = new Todo();
			todo.setMemberNo(memberNo);
			todo.setTankNo(tankNo);
			todo.setTodoContent(todoContent);
			todo.setTodoTerm(todoTerm);
			
			int maxTodoNo = service.maxTodoNo(todo);

			
			int result = service.insertTodo(todo, maxTodoNo);
			
			if(result > 0) {
				System.out.println("\n[할 일 등록 완료]\n");
			} else {
				System.out.println("\n[할 일 등록 실패]\n");
				
			}
//			
		} catch (Exception e) {
			System.out.println("\n[할 일 등록 중 예외 발생]\n");
			e.printStackTrace();
		}
		
		
	}
	
	private void completeTodo(int tankNo) {
		
		try {	
			System.out.println("\n[할 일 완료]\n");
		
			int memberNo = AquariumView.loginMember.getMemberNo();
			Todo todo = new Todo();
			todo.setMemberNo(memberNo);
			todo.setTankNo(tankNo);
			
			List<Todo> todoList = service.selectTodo2(todo);
			for(Todo t: todoList) {
				System.out.printf("%d. %s\n", t.getTodoNo(), t.getTodoContent());
			}
			
			
			System.out.print("완료할 할 일 번호 선택: ");
			int todoNo = sc.nextInt();
			
			todo.setTodoNo(todoNo);
			
			System.out.print("선택한 할일을 완료하시겠습니까?(Y/N): ");
			char ch = sc.next().toLowerCase().charAt(0);
			
			if(ch == 'y') {
				int result = service.completeTodo(todo);
				
				if(result > 0) {
					System.out.println("\n[할 일 완료 성공]\n");
				} else {
					System.out.println("\n[할 일 완료 실패]\n");
					
				}
				
			} else {
				System.out.println("\n[완료를 취소합니다.]\n");
			}
			
			
			
			
		} catch (Exception e) {
			System.out.println("\n[할 일 완료 중 예외 발생]\n");
			e.printStackTrace();
		}
		
	}
	
	
	
	/** 할 일 삭제
	 * @param tankNo
	 */
	private void deleteTodo(int tankNo) {
		try {
			System.out.println("\n[할 일 삭제]\n");
			
			int memberNo = AquariumView.loginMember.getMemberNo();
			
			Todo todo = new Todo();
			todo.setMemberNo(memberNo);
			todo.setTankNo(tankNo);
			
			List<Todo> todoList = service.selectTodo2(todo);
			for(Todo t: todoList) {
				System.out.printf("%d. %s\n", t.getTodoNo(), t.getTodoContent());
			}
			
			System.out.println("삭제할 할 일 번호: ");
			int todoNo = sc.nextInt();
			
			todo.setTodoNo(todoNo);
			
			System.out.println("정말 삭제하시겠습니까?(Y/N): ");
			String input = sc.next().toLowerCase();
			
			if(input.equals("y")) {
				int result = service.deleteTodo(todo);
				
				if(result > 0) {
					System.out.println("\n[할 일 삭제 완료]\n");
				} else {
					System.out.println("\n[할 일 삭제 실패]\n");
					
				}
			}
			else {
				System.out.println("삭제를 취소합니다.");
			}
			
		} catch (Exception e) {
			System.out.println("\n[할 일 삭제 중 예외 발생]\n");
			e.printStackTrace();
		}
		
	}

	
	/** 완료된 할 일 조회
	 * @param tankNo
	 */
	private void selectComTodo(int tankNo) {
		try {
			System.out.println("\n[완료된 할 일 조회]\n");
			
			int memberNo = AquariumView.loginMember.getMemberNo();
			

			Todo todo = new Todo();
			todo.setMemberNo(memberNo);
			todo.setTankNo(tankNo);
			
			List<Todo> todoList = service.selectComTodo(todo);
			
			if(todoList.isEmpty()) {
				System.out.println("\n[조회 결과가 없습니다.]\n");
			} else {
				System.out.println("\n[완료된 할 일 목록]\n");
				for(Todo t:todoList) {
					System.out.printf("%d/.   %18s  |  %s  \n"
							, t.getTodoNo(), t.getTodoContent(), t.getRegDate());
				}
			}


		
		} catch (Exception e) {
			System.out.println("\n[할 일 조회 중 예외 발생]\n");
			e.printStackTrace();
		}
		
		
	}
	

	


}
