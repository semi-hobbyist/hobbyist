package com.hobbyist.board.model.service;

import static common.JdbcTemplate.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import com.hobbyist.board.model.dao.BoardDao;
import com.hobbyist.board.model.vo.Board;
import com.hobbyist.board.model.vo.BoardComment;
import com.hobbyist.board.model.vo.BoardDQ;
import com.hobbyist.board.model.vo.BoardFAQ;
import com.hobbyist.board.model.vo.BoardReport;

public class BoardService {
	
	private BoardDao dao = new BoardDao();
	
	public Board selectOne(int boardNo, boolean hasRead) {
		Connection conn = getConnection();
		Board b = dao.selectOne(conn, boardNo);
		if(b != null && !hasRead) {
			int result = dao.updateReadCount(conn, boardNo);
			if(result > 0) commit(conn);
			else rollback(conn);
		}
		close(conn);
		
		return b;
	}

	public BoardDQ selectDQOne(int boardDQNo) {
		Connection conn = getConnection();
		BoardDQ b = dao.selectDQOne(conn, boardDQNo);
		close(conn);
		
		return b;
	}
	
	public List<Board> selectAll() {
		
		Connection conn = getConnection();
		List<Board> list = dao.selectAll(conn);
		close(conn);
		
		return list;
	}
	
	public List<Board> selectList(int cPage, int numPerPage) {
		
		Connection conn = getConnection();
		List<Board> list = dao.selectList(conn, cPage, numPerPage);
		close(conn);
		
		return list;
	}
	
	public List<BoardFAQ> selectFAQList(int cPage, int numPerPage) {
		
		Connection conn = getConnection();
		List<BoardFAQ> list = dao.selectFAQList(conn, cPage, numPerPage);
		close(conn);
		
		return list;
	}
	
	public List<BoardFAQ> selectFAQSearchList(int cPage, int numPerPage, String searchType, String searchKeyword) {
		
		Connection conn = getConnection();
		List<BoardFAQ> list = dao.selectFAQSearchList(conn, cPage, numPerPage, searchType, searchKeyword);
		close(conn);
		
		return list;
	}
	
	public List<BoardDQ> selectDQList(int cPage, int numPerPage) {
		
		Connection conn = getConnection();
		List<BoardDQ> list = dao.selectDQList(conn, cPage, numPerPage);
		close(conn);
		
		return list;
	}
	
	public List<Board> selectSearchList(int cPage, int numPerPage, String searchType, String searchKeyword) {
		
		Connection conn = getConnection();
		List<Board> list = dao.selectSearchList(conn, cPage, numPerPage, searchType, searchKeyword);
		close(conn);
		
		return list;
	}
	
	public int selectCount() {
		
		Connection conn = getConnection();
		int totalCount = dao.selectCount(conn);
		close(conn);
		
		return totalCount;
	}
	
	public int selectFAQCount() {
		
		Connection conn = getConnection();
		int totalCount = dao.selectFAQCount(conn);
		close(conn);
		
		return totalCount;
	}
	
	public int selectFAQSearchCount(String searchType, String searchKeyword) {
		
		Connection conn = getConnection();
		int totalCount = dao.selectFAQSearchCount(conn, searchType, searchKeyword);
		close(conn);
		
		return totalCount;
	}
	
	public int selectDQCount() {
		
		Connection conn = getConnection();
		int totalCount = dao.selectDQCount(conn);
		close(conn);
		
		return totalCount;
	}
	
	public int selectSearchCount(String searchType, String searchKeyword) {
		
		Connection conn = getConnection();
		int totalCount = dao.selectSearchCount(conn, searchType, searchKeyword);
		close(conn);
		
		return totalCount;
	}
	
	public List<BoardComment> selectCommentAll(int boardNo) {
		Connection conn = getConnection();
		List<BoardComment> bcList = dao.selectCommentAll(conn, boardNo);
		close(conn);
		
		return bcList;
	}
	
	public int insertBoard(Board b) {
		Connection conn = getConnection();
		int result = dao.insertBoard(conn, b);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		
		return result;
	}
	
	public int insertDQBoard(BoardDQ b) {
		Connection conn = getConnection();
		int result = dao.insertDQBoard(conn, b);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		close(conn);
		
		return result;
	}
	
	public int insertComment(BoardComment bc) {
		Connection conn = getConnection();
		int result = dao.insertComment(conn, bc);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		close(conn);
		
		return result;
	}
	
	public int insertBoardReport(BoardReport b) {
		Connection conn = getConnection();
		int result = dao.insertBoardReport(conn, b);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		close(conn);
		
		return result;
	}
	
	public int selectNextOne(int boardNo) {
		
		Connection conn = getConnection();
		int moveNo = dao.selectNextOne(conn, boardNo);
		close(conn);
		
		return moveNo;
	}
	
	public int selectPrevOne(int boardNo) {
		
		Connection conn = getConnection();
		int moveNo = dao.selectPrevOne(conn, boardNo);
		close(conn);
		
		return moveNo;
	}
	
	public int updateBoard(Board b) {
		
		Connection conn = getConnection();
		int result = dao.updateBoard(conn, b);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		
		return result;
	}
	
	public int updateDQBoard(BoardDQ b) {
		
		Connection conn = getConnection();
		int result = dao.updateDQBoard(conn, b);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		
		return result;
	}
	
	public int updateDQAnswerBoard(int BoardDQNo, String boardDQContent) {
		
		Connection conn = getConnection();
		int result = dao.updateDQAnswerBoard(conn, BoardDQNo, boardDQContent);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		
		return result;
	}
	
	public void updateLikeCountAdd(int boardNo) {
		
		Connection conn = getConnection();
		int result = dao.updateLikeCountAdd(conn, boardNo);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		close(conn);
		
	}
	
	public void updateLikeCountSub(int boardNo) {
		
		Connection conn = getConnection();
		int result = dao.updateLikeCountSub(conn, boardNo);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		close(conn);
		
	}
	
	public void updateLikeUsers(int boardNo, String users) {
		
		Connection conn = getConnection();
		int result = dao.updateLikeUsers(conn, boardNo, users);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		close(conn);
		
	}
	
	public int deleteBoard(int boardNo) {
		
		Connection conn = getConnection();
		int result = dao.deleteBoard(conn, boardNo);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		
		return result;
	}
	
	public int deleteDQBoard(int boardDQNo) {
		
		Connection conn = getConnection();
		int result = dao.deleteDQBoard(conn, boardDQNo);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		
		return result;
	}
	
	public int deleteComment(int delNo) {
		Connection conn = getConnection();
		int result = dao.deleteComment(conn, delNo);

		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		
		return result;
	}
	
	public String selectLikeUsers(int boardNo) {
		
		Connection conn = getConnection();
		String users = dao.selectLikeUsers(conn, boardNo);
		close(conn);
		
		return users;
	}
	
	
	
	
	
	
	
	
	

}
