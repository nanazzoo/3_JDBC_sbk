package edu.kh.jdbc.board.view;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import edu.kh.jdbc.board.model.service.BoardService;
import edu.kh.jdbc.board.model.service.CommentService;
import edu.kh.jdbc.board.vo.Board;
import edu.kh.jdbc.main.view.MainView;
import edu.kh.jdbc.member.vo.Member;

public class BoardView {
	
	private Scanner sc = new Scanner(System.in);
	
	private BoardService bService = new BoardService();
	
	private CommentService cService = new CommentService();
	
	
	/**
	 * 게시판 기능 메뉴 화면
	 */
	public void boardMenu() {
		int input = -1;
		
		do {
			try {
				System.out.println("\n***** 게시판 기능 *****\n");
				System.out.println("1. 게시글 목록 조회");
				System.out.println("2. 게시글 상세 조회(+ 댓글 기능)");
				System.out.println("3. 게시글 작성");
				System.out.println("4. 게시글 검색");
				System.out.println("0. 메인 메뉴로 이동");
				
				System.out.print("메뉴 선택 >> ");
				input = sc.nextInt();
				sc.nextLine();
				
				System.out.println();
				switch (input) {
				case 1: selectAllBoard(); break;
				case 2: selectBoard(); break;
				case 3: ; break;
				case 4: ; break;
				case 0: System.out.println("[로그인 메뉴로 이동합니다.]"); break;
				default: System.out.println("메뉴에 작성된 번호만 입력해주세요.");
				}
				System.out.println();
			} catch (InputMismatchException e) {
				System.out.println("\n<<입력 형식이 올바르지 않습니다.>>");
				sc.nextLine();
			}
			
		} while (input != 0);
		
		
	}


	/**
	 * 게시글 목록 조회
	 */
	private void selectAllBoard() {
		System.out.println("\n[게시글 목록 조회]\n");
		
		try {
			List<Board> boardList = bService.selectAllBoard();
//			-> DAO에서 new ArrayList<>(); 구문으로 인해 반환되는 조회 결과는
//			   null이 될 수 없다!!
			
			if(boardList.isEmpty()) {
				System.out.println("\n게시글이 존재하지 않습니다.\n");
			} else {
				System.out.println("| 글번호 |    제목(댓글 수)  | 작성자 |   작성일    | 조회수 | ");
				for(Board b : boardList) {
					System.out.printf("| %6d | %10s[%d] | %s | %8s | %6d |\n"
							, b.getBoardNo(), b.getBoardTitle(), b.getCommentCount()
							, b.getMemberName(),b.getCreateDate(), b.getReadCount());
				}
			}
			
		} catch (Exception e) {
			System.out.println("\n<<게시글 목록 조회 중 예외 발생>>\n");
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * 게시글 상세 조회
	 */
	private void selectBoard() {
		System.out.println("\n[게시글 상세 조회]\n");
		
		try {
			System.out.print("게시글 번호: ");
			int boardNo = sc.nextInt();
			sc.nextLine();
			
			Board board = bService.selectBoard(boardNo, MainView.loginMember.getMemberNo());
//											   게시글 번호, 로그인한 회원의 회원 번호
//											   --> 본인이 작성한 글 조회수 증가 X
			
			
			
			
		} catch (Exception e) {
			System.out.println("\\n<<게시글 상세 조회 중 예외 발생>>\\n");
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
