package com.hobbyist.mypage.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hobbyist.writer.model.vo.WriterEnroll;

/**
 * Servlet implementation class Mypage_writerEnrollListServlet
 */
@WebServlet("/mypage/mypage_writerEnrollList")
public class Mypage_writerEnrollListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Mypage_writerEnrollListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<WriterEnroll> list = null;
		String sort = "";
		int cPage = 0;
		int numPerPage = 0;
		String pageBar = "";
		String keyword = "";
		boolean weFlag = true;
		request.setAttribute("list", list);
		request.setAttribute("sort", sort);
		request.setAttribute("cPage", cPage);
		request.setAttribute("numPerPage", numPerPage);
		request.setAttribute("pageBar", pageBar);
		request.setAttribute("keyword", keyword);
		request.setAttribute("weFlag", weFlag);
		request.getRequestDispatcher("/views/mypage/writer/myPage_writerEnroll.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
