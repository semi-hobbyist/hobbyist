package com.hobbyist.notice.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hobbyist.notice.model.service.NoticeService;
import com.hobbyist.notice.model.vo.Notice;

/**
 * Servlet implementation class NoticeViewServlet
 */
@WebServlet("/notice/noticeView")
public class NoticeViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int noticeNo = Integer.parseInt(request.getParameter("noticeNo"));
		
		
		// 쿠키를 이용한 조회수 중복 막기
		Cookie[] cookies = request.getCookies();
		String noticeCookieVal = "";
		boolean hasRead = false;

		if (cookies != null) {
			output: for (Cookie c : cookies) {
				String name = c.getName();
				String value = c.getValue();
				if ("noticeCookie".equals(name)) {
					noticeCookieVal = value;
					if (value.contains("|" + noticeNo + "|")) {
						hasRead = true;
						break output;
					}
				}
			}
		}
		
		// noticeCookie를 세팅
		if(!hasRead) {
			Cookie c = new Cookie("noticeCookie", noticeCookieVal+"|"+noticeNo+"|");
			c.setMaxAge(-1);
			response.addCookie(c);
		}

		Notice notice = new NoticeService().selectOne(noticeNo,hasRead);
		

		// 작성자 프로필 이미지 가져오기
		String writer = notice.getNoticeWriter();
		String profileImg = new NoticeService().writerImg(writer);
		
		
		request.setAttribute("profileImg", profileImg);
		request.setAttribute("notice", notice);
		request.getRequestDispatcher("/views/notice/noticeView.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
