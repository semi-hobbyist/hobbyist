package com.hobbyist.notice.model.service;

import static common.JdbcTemplate.close;
import static common.JdbcTemplate.getConnection;
import static common.JdbcTemplate.commit;
import static common.JdbcTemplate.rollback;


import java.sql.Connection;
import java.util.List;

import com.hobbyist.notice.model.dao.NoticeDao;
import com.hobbyist.notice.model.vo.Notice;

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
	
	public Notice selectOne(int noticeNo, boolean hasRead) {
		Connection conn = getConnection();
		Notice no = dao.selectOne(conn,noticeNo);
		if(no!=null&&!hasRead) {
			int result=dao.increReadCount(conn,noticeNo);
			if(result>0) {
				commit(conn);
			}
			else {
				rollback(conn);
			}
		}
		close(conn);
		return no;
	}
	
	public String writerImg(String wirter) {
		Connection conn = getConnection();
		String result = dao.writerImg(conn,wirter);
		close(conn);
		return result;
	}
	
}
