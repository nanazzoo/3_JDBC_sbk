package edu.kh.jdbc.board.model.service;

import static edu.kh.jdbc.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import edu.kh.jdbc.board.model.dao.BoardDAO;
import edu.kh.jdbc.board.model.dao.CommentDAO;
import edu.kh.jdbc.board.vo.Board;

public class BoardService {
	
//	BoardDAO 객체 생성
	private BoardDAO dao = new BoardDAO();

//	CommentDAO 객체 생성 -> 상세 조회 시 댓글 목록 조회 용도
	private CommentDAO cDao = new CommentDAO();

	
	/** 게시글 목록 조회 서비스
	 * @return boardList
	 * @throws Exception
	 */
	public List<Board> selectAllBoard() throws Exception{
		Connection conn = getConnection();
		
		List<Board> boardList = dao.selectAllBoard(conn);
		
		close(conn);
		
		return boardList;
	}


	/** 게시글 상세 조회 서비스
	 * @param boardNo
	 * @param memberNo
	 * @return board
	 * @throws Exception
	 */
	public Board selectBoard(int boardNo, int memberNo) throws Exception {
		Connection conn = getConnection();
		
//		1. 게시글 상세 조회 DAO 호출
		Board board = dao.selectBoard(conn, boardNo);
		
		if(board != null) {
			
		} else {
			
		}
		
		close(conn);
		
		return board;
	}
	
	
	
	
	
}
