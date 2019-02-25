package com.hobbyist.award.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hobbyist.award.model.service.AwardService;

/**
 * Servlet implementation class AwardLikeServlet
 */
@WebServlet("/award/awardLike")
public class AwardLikeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AwardLikeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	int awardNo=Integer.parseInt(request.getParameter("awardNo"));
	String userId=request.getParameter("userId");
	
	boolean flag=false;
	String users=new AwardService().selectLikeUser(awardNo);
	String[] temp;
	List<String> userlist=new ArrayList();
	if(users!=null) {
		temp=users.split(",");
		for(int i=0;i<temp.length;i++) {
			userlist.add(temp[i]);
		}
	}
 	String msg="";
 	String loc="/award/awardView?awardNo="+awardNo;
 	String view="/views/common/msg.jsp";
 	
 	for(int i=0;i<userlist.size();i++) {
 		if(userlist.get(i).equals(userId)) {
 			userlist.remove(i);
 			flag=true;
 			break;
 		}
 	}
 	
 	if(flag) {
 		users="";
 		for(int i=0;i<userlist.size();i++) {
 			if(i==0) users=userlist.get(i);
 			else users+=","+userlist.get(i);
 		}
 		new AwardService().increLikeCountsub(awardNo);
 		new AwardService().increLikeUsers(awardNo, users);
 		
 		request.setAttribute("msg", "게시물 추천 취소");
 		request.setAttribute("loc",loc);
 		request.getRequestDispatcher(view).forward(request, response);
 	}
 	
 	if(!flag) {
 		if(users==null)users=userId;
 		else users+=","+userId;
 		new AwardService().increLikeCountAdd(awardNo);
 		new AwardService().increLikeUsers(awardNo,users);
 		
 		request.setAttribute("msg", "게시물 추천 감사링");
 		request.setAttribute("loc",loc);
 		request.getRequestDispatcher(view).forward(request, response);
 		
 	}
 	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
