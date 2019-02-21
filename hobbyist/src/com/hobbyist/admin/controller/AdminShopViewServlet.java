package com.hobbyist.admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hobbyist.shop.model.service.ShopService;
import com.hobbyist.shop.model.vo.Cate;
import com.hobbyist.shop.model.vo.Shop;

@WebServlet("/admin/adminShopView")
public class AdminShopViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminShopViewServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 클래스 정보 가져오기
		int no = Integer.parseInt(request.getParameter("no"));
		Shop shop = new ShopService().selectOne(no);

		request.setAttribute("Shop", shop);
		request.getRequestDispatcher("/views/admin/shop/adminShopView.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
