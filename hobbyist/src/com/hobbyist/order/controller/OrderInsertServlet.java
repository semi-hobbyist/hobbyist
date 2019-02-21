package com.hobbyist.order.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hobbyist.order.model.service.OrderService;
import com.hobbyist.order.model.vo.Order;

@WebServlet("/order/orderInsert")
public class OrderInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public OrderInsertServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("---------------- 주문처리 (★) ---------------");
		// 주문정보 매개변수로 받아오기 ------------------------------------->>
		String member = request.getParameter("member");
		String classNo_temp = request.getParameter("classNo");
		String classNo[] = classNo_temp.split(",");
		String classOption_temp = request.getParameter("classOption");
		String classOption[] = classOption_temp.split(",");
		String orderType = request.getParameter("order_type");
		String orderPrice = request.getParameter("order_price");
		String orderAddName = request.getParameter("order_add_name");
		String orderAddPhone = request.getParameter("order_add_phone");
		String orderAddAddress = request.getParameter("order_add_address");
		String orderMsg = request.getParameter("order_msg");
		
		System.out.println("주문하기 전 값이 잘들어왔나? : " + member + " , " + classNo_temp + " , " + " , " + classOption_temp  + " , " + orderType  + " , " + orderPrice + " , " + orderAddName  + " , " + orderAddPhone + " , " + orderAddAddress + " , " + orderMsg);;
		for(String s : classNo) {
			System.out.println("결제하는 클래스 : " + s);
		}
		for(String s : classOption) {
			System.out.println("결제하는 클래스옵션들 : " + s);
		}
		
		System.out.println("---------------- 주문처리 (★★) ---------------");
		int randomNum = 0;
		int result = 0;
		// ----------------------------- 핀코드 등록하기
			for(String s : classNo) {
			randomNum = (int) ((Math.random()*100000000)+1);
			
			// 이미 있는 핀코드인지 구별하기
			int isPincode = new OrderService().selectPincode(randomNum);
			if(isPincode>0) {
				randomNum = (int) ((Math.random()*100000000)+1);
			}
			result += new OrderService().insertPin(randomNum, member, Integer.parseInt(s));
		}
		
		if(result>0) {
			System.out.println("PINCODE [" + randomNum + "]가 생성되었습니다");
		} else {
			System.out.println("PINCODE 생성 실패");
		}
		System.out.println("---------------- 주문처리 (★★★) ---------------");
		int insertResult = 0;
		// ----------------------------- 결제정보 등록
		int randomCode_temp = (int) ((Math.random()*10000000)+1);
		String randomCode = "A" + randomCode_temp;   // 주문번호
		for(int i=0; i<classNo.length; i++) {
				insertResult = new OrderService().insertOrder(randomCode, member, classNo[i], classOption[i], orderType, orderPrice, orderAddName, orderAddPhone, orderAddAddress, orderMsg);
		}
		System.out.println("---------------- 주문처리 (★★★★★) 완료 ---------------");
		String msg = "";
		String loc = "";
		String view = "/views/common/msg.jsp";
		
		if(insertResult>0) {
			msg = "결제완료 [ 주문번호 : " + randomCode + " ]";
			loc = "/";
		} else {
			msg = "결제실패";
			loc = "/myCartList?=" + member;
		}
		
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		request.getRequestDispatcher(view).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
