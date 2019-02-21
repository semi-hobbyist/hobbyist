package com.hobbyist.award.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hobbyist.award.model.service.AwardService;
import com.hobbyist.award.model.vo.Award;

/**
 * Servlet implementation class AwardUpdateServlet
 */
@WebServlet("/award/awardUpdate")
public class AwardUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AwardUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	int awardNo=Integer.parseInt(request.getParameter("awardNo"));
	System.out.println("업데이트서블릿");
	boolean hasRead = false;
	Award award=new AwardService().selectOne(awardNo,hasRead);
	String view="";
	String msg="";
	if(award!=null) {		
		request.setAttribute("award", award);
		view="/views/award/awardUpdate.jsp";
	}else {
		msg="조회한 어워드 게시물 없음";
		view="/views/common/msg.jsp";
		request.setAttribute("loc", "/award/awardList");
	}
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
