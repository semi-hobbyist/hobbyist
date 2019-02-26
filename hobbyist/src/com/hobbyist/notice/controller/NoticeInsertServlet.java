package com.hobbyist.notice.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hobbyist.member.model.vo.Member;
import com.hobbyist.notice.model.service.NoticeService;
import com.hobbyist.notice.model.vo.WeNotice;

/**
 * Servlet implementation class NoticeInsertServlet
 */
@WebServlet("/notice/noticeInsert")
public class NoticeInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Member logginMember = (Member)request.getSession(false).getAttribute("logginMember");
		
		if(logginMember==null||!logginMember.getMemberEmail().equals("admin")) {
			request.setAttribute("msg", "잘못된 경로로 이동하셨습니다.");
			request.setAttribute("loc", "/");
			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
		}
		
		//작가신청 공지글 관련 시기 변수 가져오기(중복공지 방지 로직)
		List<WeNotice> wn = new NoticeService().weSelectAll();
		String[] weQuarter = wn.get(0).getWeQuarter().split(",");
		String weYear = weQuarter[0];
		String weQu = weQuarter[1];
		
		Calendar c = Calendar.getInstance();
		String cuYear = String.valueOf(c.get(Calendar.YEAR));
		String cuQu = weQu;
		if(weYear.equals(cuYear)) {
			cuQu = String.valueOf(Integer.parseInt(weQu) + 1);
		}
		
		//작가신청 공지글 등록할때 날짜 최소값 구하기
		Date minTime = new NoticeService().minTime(wn.get(0).getWeNoticeNo());
		
		request.setAttribute("wn", wn);
		request.setAttribute("cuYear", cuYear);
		request.setAttribute("cuQu", cuQu);
		request.setAttribute("minTime", minTime);
		request.getRequestDispatcher("/views/notice/noticeWrite.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
