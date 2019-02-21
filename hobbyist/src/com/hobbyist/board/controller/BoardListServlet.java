package com.hobbyist.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hobbyist.board.model.service.BoardService;
import com.hobbyist.board.model.vo.Board;

/**
 * Servlet implementation class BoardListServlet
 */
@WebServlet("/board/boardList")
public class BoardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardListServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int cPage = 0;
		try {
			cPage = Integer.parseInt(request.getParameter("cPage"));
		} catch (Exception e) {
			cPage = 1;
		}
		int numPerPage = 0;
		try {
			numPerPage = Integer.parseInt(request.getParameter("numPerPage"));
		} catch (Exception e) {
			numPerPage = 5;
		}
		
		int totalCount = new BoardService().selectCount();
		int totalPage = (int)Math.ceil((double)totalCount/numPerPage);
		List<Board> list = new BoardService().selectList(cPage, numPerPage);
		
		String pageBar = "";
		int pageBarSize = 5;
		int pageNo = (cPage-1)/pageBarSize*pageBarSize+1;
		int pageEnd = pageNo+pageBarSize-1;
		
		if(pageNo == 1) {
			pageBar += "<span>이전</span>";
		} else {
			pageBar += "<span>이전</span>";
		}
		
		while(!(pageNo > totalPage || pageNo > pageEnd)) {
			if(cPage == pageNo) {
				pageBar += "<span class='current'>[" + pageNo + "]</span>";
			} else {
				pageBar += "<span>" + pageNo + "</span>";
			}
			pageNo++;
		}
	
		
		
		request.setAttribute("list", list);
		request.setAttribute("cPage", cPage);
		request.setAttribute("numPerPage", numPerPage);
		request.setAttribute("pageBar", pageBar);
		request.getRequestDispatcher("/views/board/boardList.jsp").forward(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
