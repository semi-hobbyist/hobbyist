package com.hobbyist.order.model.service;

import static common.JdbcTemplate.close;
import static common.JdbcTemplate.commit;
import static common.JdbcTemplate.getConnection;
import static common.JdbcTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.hobbyist.order.model.dao.OrderDao;
import com.hobbyist.order.model.vo.OrderData;
import com.hobbyist.order.model.vo.Pin;
import com.hobbyist.shop.model.vo.Shop;

public class OrderService {

	static OrderDao dao = new OrderDao();
	
	public int insertPin(int randomNum, String member, int s) {
		Connection conn = getConnection();
		int result = dao.insertPin(conn, randomNum, member, s);
		if(result>0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public List<OrderData> selectOrderData(String member) {
		Connection conn = getConnection();
		List<OrderData> list = dao.selectOrderData(conn, member);
		close(conn);
		return list;
	}

	public int insertOrder(String randomCode, String member, String s, String c, String orderType, String orderPrice,
			String orderAddName, String orderAddPhone, String orderAddAddress, String orderMsg) {
		Connection conn = getConnection();
		int result = dao.insertOrder(conn, randomCode, member, s, c, orderType, orderPrice, orderAddName, orderAddPhone, orderAddAddress, orderMsg);
		close(conn);
		return result;
	}

	public int selectPincode(int randomNum) {
		Connection conn = getConnection();
		int result = dao.selectPincode(conn, randomNum);
		close(conn);
		return result;
	}

	public Pin activePin(int pinCode, String member) {
		Connection conn = getConnection();
		Pin pin = dao.activePin(conn, pinCode, member);
		close(conn);
		return pin;
	}

	public int updatePin(int pinCode) {
		Connection conn = getConnection();
		int result = dao.updatePin(conn, pinCode);
		close(conn);
		return result;
	}

	public List<Pin> selectPinList(int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<Pin> list = dao.selectPinList(conn, cPage, numPerPage);
		close(conn);
		return list;
	}

	public int selectPinCount() {
		Connection conn = getConnection();
		int result = dao.selectPinCount(conn);
		close(conn);
		return result;
	}
	
}
