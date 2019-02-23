package com.hobbyist.notice.model.dao;

import static common.JdbcTemplate.close;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.hobbyist.notice.model.vo.Notice;
import com.hobbyist.writer.model.dao.WriterDao;
import com.hobbyist.writer.model.vo.WriterEnroll;

public class NoticeDao {

	private Properties prop = new Properties();
	
	public NoticeDao() {
		String fileName = WriterDao.class.getResource("/sql/notice/notice.properties").getPath();
		try {
			prop.load(new FileReader(fileName));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int insertNotice(Connection conn, Notice no) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("insertNotice");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, no.getNoticeSort());
			pstmt.setString(2, no.getNoticeTitle());
			pstmt.setString(3, no.getNoticeWriter());
			pstmt.setString(4, no.getNoticeContent());
			pstmt.setString(5, no.getNoticeFilenameOriginal());
			pstmt.setString(6, no.getNoticeFilenameRenamed());
			pstmt.setString(7, no.getNoticeImgnameOriginal());
			pstmt.setString(8, no.getNoticeImgnameRenamed());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	// 검색결과에 다른 리스트 갯수 가져오기
	public int searchCount(Connection conn, String keyword) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		String sql = prop.getProperty("searchCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + keyword + "%");
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	// 리스트 최근 등록일순으로 가져오기
	public List<Notice> selectAll(Connection conn, String keyword, int cPage, int numPerPage) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Notice> list = new ArrayList();
		String sql = prop.getProperty("selectAll");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + keyword + "%");
			pstmt.setInt(2, (cPage-1)*numPerPage+1);
			pstmt.setInt(3, cPage*numPerPage);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Notice no = new Notice();
				
				no.setNoticeNo(rs.getInt("notice_no"));
				no.setNoticeSort(rs.getString("notice_sort"));
				no.setNoticeTitle(rs.getString("notice_title"));
				no.setNoticeWriter(rs.getString("notice_writer"));
				no.setNoticeContent(rs.getString("notice_content"));
				no.setNoticeDate(rs.getDate("notice_date"));
				no.setNoticeFilenameOriginal(rs.getString("notice_filename_original"));
				no.setNoticeFilenameRenamed(rs.getString("notice_filename_renamed"));
				no.setNoticeImgnameOriginal(rs.getString("notice_imgname_original"));
				no.setNoticeImgnameRenamed(rs.getString("notice_imgname_renamed"));
				no.setNoticeReadcount(rs.getInt("notice_readcount"));
				no.setNoticeStatus(rs.getString("notice_status"));
				list.add(no);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}

	// 검색결과에 다른 리스트 갯수 가져오기
	public int searchCountSort(Connection conn, String sort, String keyword) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		String sql = prop.getProperty("searchCountSort");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sort);
			pstmt.setString(2, "%" + keyword + "%");
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	// 리스트 최근 등록일순으로 가져오기
	public List<Notice> selectSort(Connection conn, String sort, String keyword, int cPage, int numPerPage) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Notice> list = new ArrayList();
		String sql = prop.getProperty("selectSort");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sort);
			pstmt.setString(2, "%" + keyword + "%");
			pstmt.setInt(3, (cPage-1)*numPerPage+1);
			pstmt.setInt(4, cPage*numPerPage);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Notice no = new Notice();
				
				no.setNoticeNo(rs.getInt("notice_no"));
				no.setNoticeSort(rs.getString("notice_sort"));
				no.setNoticeTitle(rs.getString("notice_title"));
				no.setNoticeWriter(rs.getString("notice_writer"));
				no.setNoticeContent(rs.getString("notice_content"));
				no.setNoticeDate(rs.getDate("notice_date"));
				no.setNoticeFilenameOriginal(rs.getString("notice_filename_original"));
				no.setNoticeFilenameRenamed(rs.getString("notice_filename_renamed"));
				no.setNoticeImgnameOriginal(rs.getString("notice_imgname_original"));
				no.setNoticeImgnameRenamed(rs.getString("notice_imgname_renamed"));
				no.setNoticeReadcount(rs.getInt("notice_readcount"));
				no.setNoticeStatus(rs.getString("notice_status"));
				list.add(no);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}

	// (삭제된)검색결과에 다른 리스트 갯수 가져오기
	public int searchCountDel(Connection conn, String keyword) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		String sql = prop.getProperty("searchCountDel");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + keyword + "%");
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	// (삭제된)리스트 최근 등록일순으로 가져오기
	public List<Notice> selectAllDel(Connection conn, String keyword, int cPage, int numPerPage) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Notice> list = new ArrayList();
		String sql = prop.getProperty("selectAllDel");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + keyword + "%");
			pstmt.setInt(2, (cPage-1)*numPerPage+1);
			pstmt.setInt(3, cPage*numPerPage);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Notice no = new Notice();
				
				no.setNoticeNo(rs.getInt("notice_no"));
				no.setNoticeSort(rs.getString("notice_sort"));
				no.setNoticeTitle(rs.getString("notice_title"));
				no.setNoticeWriter(rs.getString("notice_writer"));
				no.setNoticeContent(rs.getString("notice_content"));
				no.setNoticeDate(rs.getDate("notice_date"));
				no.setNoticeFilenameOriginal(rs.getString("notice_filename_original"));
				no.setNoticeFilenameRenamed(rs.getString("notice_filename_renamed"));
				no.setNoticeImgnameOriginal(rs.getString("notice_imgname_original"));
				no.setNoticeImgnameRenamed(rs.getString("notice_imgname_renamed"));
				no.setNoticeReadcount(rs.getInt("notice_readcount"));
				no.setNoticeStatus(rs.getString("notice_status"));
				list.add(no);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}

	// (삭제된)검색결과에 다른 리스트 갯수 가져오기
	public int searchCountSortDel(Connection conn, String sort, String keyword) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		String sql = prop.getProperty("searchCountSortDel");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sort);
			pstmt.setString(2, "%" + keyword + "%");
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	// (삭제된)리스트 최근 등록일순으로 가져오기
	public List<Notice> selectSortDel(Connection conn, String sort, String keyword, int cPage, int numPerPage) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Notice> list = new ArrayList();
		String sql = prop.getProperty("selectSortDel");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sort);
			pstmt.setString(2, "%" + keyword + "%");
			pstmt.setInt(3, (cPage-1)*numPerPage+1);
			pstmt.setInt(4, cPage*numPerPage);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Notice no = new Notice();
				
				no.setNoticeNo(rs.getInt("notice_no"));
				no.setNoticeSort(rs.getString("notice_sort"));
				no.setNoticeTitle(rs.getString("notice_title"));
				no.setNoticeWriter(rs.getString("notice_writer"));
				no.setNoticeContent(rs.getString("notice_content"));
				no.setNoticeDate(rs.getDate("notice_date"));
				no.setNoticeFilenameOriginal(rs.getString("notice_filename_original"));
				no.setNoticeFilenameRenamed(rs.getString("notice_filename_renamed"));
				no.setNoticeImgnameOriginal(rs.getString("notice_imgname_original"));
				no.setNoticeImgnameRenamed(rs.getString("notice_imgname_renamed"));
				no.setNoticeReadcount(rs.getInt("notice_readcount"));
				no.setNoticeStatus(rs.getString("notice_status"));
				list.add(no);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}
	
	public Notice selectOne(Connection conn, int noticeNo) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("selectOne");
		Notice no = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, noticeNo);
			rs= pstmt.executeQuery();
			if(rs.next()) {
				no = new Notice();

				no.setNoticeNo(rs.getInt("notice_no"));
				no.setNoticeSort(rs.getString("notice_sort"));
				no.setNoticeTitle(rs.getString("notice_title"));
				no.setNoticeWriter(rs.getString("notice_writer"));
				no.setNoticeContent(rs.getString("notice_content"));
				no.setNoticeDate(rs.getDate("notice_date"));
				no.setNoticeFilenameOriginal(rs.getString("notice_filename_original"));
				no.setNoticeFilenameRenamed(rs.getString("notice_filename_renamed"));
				no.setNoticeImgnameOriginal(rs.getString("notice_imgname_original"));
				no.setNoticeImgnameRenamed(rs.getString("notice_imgname_renamed"));
				no.setNoticeReadcount(rs.getInt("notice_readcount"));
				no.setNoticeStatus(rs.getString("notice_status"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return no;
	}
	
	public int increReadCount(Connection conn, int noticeNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("increReadCount");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, noticeNo);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public String writerImg(Connection conn, String writer) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String result = "";
		String sql = prop.getProperty("writerImg");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, writer);
			rs= pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}
	
	public int delNotice(Connection conn, int NoticeNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("delNotice");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, NoticeNo);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int reNotice(Connection conn, int NoticeNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("reNotice");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, NoticeNo);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int del_DB(Connection conn, int NoticeNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("del_DB");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, NoticeNo);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
}
