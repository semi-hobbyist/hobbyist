package com.hobbyist.order.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hobbyist.member.model.service.MemberService;
import com.hobbyist.member.model.vo.Member;
import com.hobbyist.order.model.service.OrderDataService;
import com.hobbyist.order.model.vo.OrderData;
import com.hobbyist.shop.model.service.ShopService;
import com.hobbyist.shop.model.vo.Shop;

@WebServlet("/order/order")
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public OrderServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String member = request.getParameter("memberVal");
		System.out.println(member);
		String selectNo = request.getParameter("selectNoVal");
		String selectNoArr[] = selectNo.split(",");
		System.out.println(selectNo);
		String classOption = request.getParameter("classOptionVal");
		String classOptionArr[] = classOption.split(",");
		System.out.println(classOption);
		
		// 회원정보를 가져오기 위해 멤버객체 생성
		Member m = new Member();
		m.setMemberEmail(member);
		Member mb = new MemberService().selectOne(m);
		
		// 해당클래스 선택갯수만큼 반복문 돌려
		List<Shop> orderList = new ArrayList<Shop>();
		for(int i=0; i<selectNoArr.length; i++) {
			Shop s = new ShopService().selectOne(Integer.parseInt(selectNoArr[i]));
			s.setShopOption1(classOptionArr[i]);
			orderList.add(s);
		}
		
		
		// 멤버정보 보내주기
		request.setAttribute("Member", mb);
		// 주문정보리스트 보내주기
		request.setAttribute("OrderList", orderList);
		
		request.getRequestDispatcher("/views/order/order.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
