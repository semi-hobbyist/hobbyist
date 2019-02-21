package com.hobbyist.myclass.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hobbyist.myclass.model.service.MyClassService;
import com.hobbyist.myclass.model.vo.Lecture;

@WebServlet("/myClassView")
public class MyClassViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public MyClassViewServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int no = Integer.parseInt(request.getParameter("no"));
		
		List<Lecture> lecture = new MyClassService().selectLecture(no);
		
		request.setAttribute("Lecture", lecture);
		request.getRequestDispatcher("/views/myclass/myClassView.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
