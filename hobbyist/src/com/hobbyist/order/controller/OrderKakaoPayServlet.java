package com.hobbyist.order.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.google.gson.JsonObject;

@WebServlet("/order/kakaopay")
public class OrderKakaoPayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public OrderKakaoPayServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 카카오페이 들어옴
		System.out.println("카카오페이");

		// 박성화 네이버 개발자 클라이언트 아이디와 시크릿값
		String key = "7258692bdee44d72bdfed6dbd307756b";
		String header = "KakaoAK " + key;
		
		JSONObject obj = new JSONObject();
		obj.put("cid", "TC0ONETIME");
		obj.put("partner_order_id", "partner_order_id");
		obj.put("item_name", "초코파이");
		obj.put("quantity", "1");
		obj.put("total_amount", "2200");
		obj.put("vat_amount", "200");
		obj.put("tax_free_amount", "0");
		obj.put("approval_url", "http://localhost:8080/hobbyist/");
		obj.put("fail_url", "http://localhost:8080/hobbyist/");
		obj.put("cancel_url", "http://localhost:8080/hobbyist/");

		try {
			String apiURL = "https://kapi.kakao.com/v1/payment/ready";
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("Authorization", header);
			int responseCode = con.getResponseCode();
			System.out.println(responseCode);
			BufferedReader br;
			if(responseCode==200) { // 정상 호출
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else {  // 에러 발생
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			String inputLine;
			StringBuffer sb = new StringBuffer();
			while ((inputLine = br.readLine()) != null) {
				sb.append(inputLine);
			}
			br.close();

			response.setContentType("application/json; charset=UTF-8");
			response.getWriter().print(sb);

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
