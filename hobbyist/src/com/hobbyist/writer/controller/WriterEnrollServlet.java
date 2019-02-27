package com.hobbyist.writer.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hobbyist.notice.model.service.NoticeService;
import com.hobbyist.notice.model.vo.WeNotice;

/**
 * Servlet implementation class WriterEnrollServlet
 */
@WebServlet("/writer/writerEnroll")
public class WriterEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WriterEnrollServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//로그인한 회원만 작가신청 페이지로 들어갈 수 있는 분기문
		HttpSession session = request.getSession();
		String msg = "";
		String loc = "";
		String view = "";
		String  weQuarter = "";
		WeNotice wnList = null;
		if(session.getAttribute("logginMember")==null) {
			msg="작가신청을 하기 위해서는 로그인이 필요합니다.";
			loc="/notice/noticeList";
			view="/views/common/msg.jsp";
		}
		else {
			
			//작가신청 관련 작가신청 기간이 아닐때 막는 로직
			weQuarter = request.getParameter("weQuarter");
			wnList = new NoticeService().cuWeSelectOne();
			
			if(!wnList.getWeQuarter().equals(weQuarter)) {
				msg="작가신청기간이 아닙니다.";
				loc="/notice/noticeList";
				view="/views/common/msg.jsp";
			}
			else {
				view="/views/writer/writerEnroll.jsp";
			}
		}
		

		
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		request.setAttribute("weQuarter", weQuarter);
		request.getRequestDispatcher(view).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
