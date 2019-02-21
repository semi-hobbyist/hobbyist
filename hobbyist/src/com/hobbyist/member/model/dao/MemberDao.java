package com.hobbyist.member.model.dao;

import static common.JdbcTemplate.close;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import com.hobbyist.member.model.vo.Member;

public class MemberDao {
	
	private Properties prop = new Properties();

	public MemberDao() {
		String fileName = MemberDao.class.getResource("/sql/member/member.properties").getPath();
		try {
			prop.load(new FileReader(fileName));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public Member selectOne(Connection conn, Member m) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Member result = new Member();
		String sql = prop.getProperty("selectOne");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, m.getMemberEmail());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result.setMemberNo(rs.getInt("member_no"));
				result.setMemberEmail(rs.getString("member_email"));
				result.setMemberPassword(rs.getString("member_password"));
				result.setMemberNickname(rs.getString("member_nickname"));
				result.setMemberName(rs.getString("member_name"));
				result.setMemberBirthday(rs.getString("member_birthday"));
				result.setMemberPhone(rs.getString("member_phone"));
				result.setMemberOriginalImage(rs.getString("member_original_image"));
				result.setMemberRenamedImage(rs.getString("member_renamed_image"));
				result.setMemberEnrolldate(rs.getString("member_enrolldate"));
				result.setMemberGrade(rs.getString("member_grade"));
				result.setMemberWriterYN(rs.getString("member_writer_yn"));
				result.setMemberStatus(rs.getString("member_status"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}
	
	public int enrollMember(Connection conn, Member m) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("enrollMember");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, m.getMemberEmail());
			pstmt.setString(2, m.getMemberPassword());
			pstmt.setString(3, m.getMemberNickname());
			pstmt.setString(4, m.getMemberName());
			pstmt.setString(5, m.getMemberBirthday());
			pstmt.setString(6, m.getMemberPhone());
			pstmt.setString(7, m.getMemberOriginalImage());
			pstmt.setString(8, m.getMemberRenamedImage());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int emailCheck(Connection conn, String finalEmail) {
		PreparedStatement pstmt = null;
		int result = 0;
		ResultSet rs =null;
		String sql = prop.getProperty("emailCheck");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, finalEmail);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				result=rs.getInt(1); //가입할수 없음 //기존의 아이디가 있는거
			}
			System.out.println(result);
			System.out.println(finalEmail);
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}

	public int nicknameCheck(Connection conn, String memberNickname) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		String sql = prop.getProperty("nicknameCheck");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, memberNickname);
			rs=pstmt.executeQuery();
			
			if(rs.next()) { //가입할수 없음
				result=rs.getInt(1);
			} 
			System.out.println("닉네임 값 : "+result);
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}
}
