package edu.kh.jdbc.board.view;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import edu.kh.jdbc.board.model.service.BoardService;
import edu.kh.jdbc.board.model.service.CommentService;
import edu.kh.jdbc.board.vo.Board;
import edu.kh.jdbc.board.vo.Comment;
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
			
	         if (board != null) {
	             System.out.println("--------------------------------------------------------");
	             System.out.printf("글번호 : %d \n제목 : %s\n", board.getBoardNo(), board.getBoardTitle());
	             System.out.printf("작성자 : %s | 작성일 : %s  \n조회수 : %d\n", 
	                   board.getMemberName(), board.getCreateDate(), board.getReadCount());
	             System.out.println("--------------------------------------------------------\n");
	             System.out.println(board.getBoardContent());
	             System.out.println("\n--------------------------------------------------------");

	          
	             // 댓글 목록
	             if(!board.getCommentList().isEmpty()) {
	                for(Comment c : board.getCommentList()) {
	                   System.out.printf("댓글번호: %d   작성자: %s  작성일: %s\n%s\n",
	                         c.getCommentNo(), c.getMemberName(), c.getCreateDate(), c.getCommentContent());
	                   System.out.println(" --------------------------------------------------------");
	                }
	             }
	             
	             // 댓글 등록, 수정, 삭제
	             // 게시글 수정/삭제 메뉴
	             subBoardMenu(board);
	             
	             
	          } else {
	             System.out.println("[\n해당 번호의 게시글이 존재하지 않습니다.]\n");
	          }
			
			
		} catch (Exception e) {
			System.out.println("\n<<게시글 상세 조회 중 예외 발생>>\n");
			e.printStackTrace();
		}
		
	}

	
	
	/** 게시글 상세조회 시 출력되는 서브 메뉴
	 * @param board(상세조회된 게시글 + 작성자 번호 + 댓글목록)
	 */
	private void subBoardMenu(Board board) {
		
		try {
			System.out.println("1) 댓글 등록");
			System.out.println("2) 댓글 수정");
			System.out.println("3) 댓글 삭제");
			System.out.println("0) 게시판 메뉴로 돌아가기");
			
			System.out.println("\n서브 메뉴 선택 >>");
			int input = sc.nextInt();
			sc.nextLine();
			
//			로그인한 회원의 회원 번호
			int memberNo = MainView.loginMember.getMemberNo();
			
			System.out.println();
			switch (input) {
			case 1: insertComment(board.getBoardNo(), memberNo); break;
			case 2:  break;
			case 3:  break;
			case 0: System.out.println("\n[게시판 메뉴로 돌아갑니다.]\n"); break;
			default: System.out.println("\n[메뉴에 작성된 번호만 입력해주세요.]\n");
			}
			System.out.println();
			
			
			
		} catch (InputMismatchException e) {
			System.out.println("\n<<입력 형식이 올바르지 않습니다.>>");
			sc.nextLine();
		}
	
		
	}


	/** 댓글 등록 화면
	 * @param boardNo
	 * @param memberNo
	 */
	private void insertComment(int boardNo, int memberNo) {
		
		try {
			System.out.println("\n[댓글 등록]\n");
			
			String content = ""; //빈 문자열
			
			String input = null; // 참조하는 객체가 없음
			
			System.out.println("입력 종료 시 ($exit) 입력");
			
			while(true) {
				input = sc.nextLine();
				
				if(input.equals("$exit")) {
					break;
				}
				
//				입력된 내용을 content에 누적
				content += input + "\n";
				
			}
			
//			DB INSERT 시 필요한 값을 하나의 Comment 객체에 저장
			Comment comment = new Comment();
			comment.setCommentContent(content);
			comment.setBoardNo(boardNo);
			comment.setMemberNo(memberNo);
			
			int result = cService.insertComment(comment);
			
			if(result > 0) {
				System.out.println("\n[댓글 등록 완료]\n");
			} else {
				System.out.println("\n[댓글 등록 실패]\n");
			}
				
		} catch (Exception e) {
			System.out.println("\n<<댓글 등록 중 예외 발생>>\n");
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
