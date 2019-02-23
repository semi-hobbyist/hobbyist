package com.hobbyist.notice.model.service;

import static common.JdbcTemplate.close;
import static common.JdbcTemplate.getConnection;

import java.sql.Connection;
import java.util.List;

import com.hobbyist.notice.model.dao.NoticeDao;
import com.hobbyist.notice.model.vo.Notice;
import com.hobbyist.writer.model.vo.WriterEnroll;

public class NoticeService {

	private NoticeDao dao = new NoticeDao();
	
	public int insertNotice(Notice no) {
		Connection conn = getConnection();
		int result = dao.insertNotice(conn,no);
		close(conn);
		return result;
	}
	
	// 검색결과에 따라 리스트 갯수 가져오기
	public int searchCount(String keyword) {
		Connection conn = getConnection();
		int result = dao.searchCount(conn, keyword);
		close(conn);
		return result;
	}

	// 리스트 최근 등록순 정렬
	public List<Notice> selectAll(String keyword, int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<Notice> list = dao.selectAll(conn, keyword, cPage, numPerPage);
		close(conn);
		return list;
	}
	
	// 검색결과에 따라 리스트 갯수 가져오기(분류기준)
	public int searchCountSort(String sort, String keyword) {
		Connection conn = getConnection();
		int result = dao.searchCountSort(conn, sort, keyword);
		close(conn);
		return result;
	}

	// 리스트 분류별 정렬
	public List<Notice> selectSort(String sort, String keyword, int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<Notice> list = dao.selectSort(conn, sort, keyword, cPage, numPerPage);
		close(conn);
		return list;
	}
	
	
}
