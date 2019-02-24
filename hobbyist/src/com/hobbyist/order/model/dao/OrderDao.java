package com.hobbyist.order.model.dao;

import static common.JdbcTemplate.close;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.hobbyist.oneday.model.dao.OnedayDao;
import com.hobbyist.order.model.vo.Order;
import com.hobbyist.order.model.vo.OrderData;
import com.hobbyist.order.model.vo.Pin;
import com.hobbyist.shop.model.vo.Shop;

public class OrderDao {

	static Properties prop = new Properties();

	public OrderDao() {
		String fileName = OnedayDao.class.getResource("/sql/order/order.properties").getPath();
		try {
			prop.load(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int insertOrderData(Connection conn, OrderData od) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			String sql = prop.getProperty("insertOrderData");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, od.getOrderDataMember());
			pstmt.setString(2, od.getOrderDataClass());
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public List<OrderData> selectOrderData(Connection conn, String member) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<OrderData> list = new ArrayList<OrderData>();
		try {
			String sql = prop.getProperty("selectOrderData");
			pstmt = conn.prepareStatement(sql);
			System.out.println("MEMBER :" + member);
			pstmt.setString(1, member);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				OrderData od = new OrderData();
				od.setOrderDataNo(rs.getInt("order_data_no"));
				od.setOrderDataMember(rs.getString("order_data_member"));
				od.setOrderDataClass(rs.getString("order_data_class"));
				od.setOrderDataDate(rs.getTimestamp("order_data_date"));
				list.add(od);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}

	public int insertPin(Connection conn, int randomNum, String member, int s) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			String sql = prop.getProperty("insertPin");
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, randomNum);
			pstmt.setInt(2, s);
			pstmt.setString(3, member);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int insertOrder(Connection conn, String randomCode, String member, String s, String c, String orderType,
			String orderPrice, String orderAddName, String orderAddPhone, String orderAddAddress, String orderMsg, String orderCate) {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			String sql = prop.getProperty("insertOrder");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, randomCode);
			pstmt.setString(2, member);
			pstmt.setString(3, s);
			pstmt.setString(4, c);
			pstmt.setString(5, orderType);
			pstmt.setString(6, orderPrice);
			pstmt.setString(7, orderAddName);
			pstmt.setString(8, orderAddPhone);
			pstmt.setString(9, orderAddAddress);
			pstmt.setString(10, orderMsg);
			pstmt.setString(11, orderCate);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int selectPincode(Connection conn, int randomNum) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		
		try {
			String sql = prop.getProperty("selectPincode");
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, randomNum);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = 1;
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public Pin activePin(Connection conn, int pinCode, String member) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Pin pin = null;
		
		try {
			String sql = prop.getProperty("activePin");
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pinCode);
			pstmt.setString(2, member);
			rs = pstmt.executeQuery();
			
		if(rs.next()) {
				pin = new Pin();
				pin.setPinCode(rs.getInt("pin_code"));
				pin.setPinClass(rs.getInt("pin_class"));
				pin.setPinMember(rs.getString("pin_member"));
				pin.setPinStatus(rs.getString("pin_status"));
				pin.setPinDate(rs.getTimestamp("pin_date"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return pin;
	}

	public int updatePin(Connection conn, int pinCode) {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			String sql = prop.getProperty("updatePin");
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pinCode);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public List<Pin> selectPinList(Connection conn, int cPage, int numPerPage) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Pin> list = new ArrayList<Pin>();
		
		try {
			String sql = prop.getProperty("selectPinList");
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (cPage-1)*numPerPage+1);
			pstmt.setInt(2, cPage*numPerPage);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Pin pin = new Pin();
				pin.setPinCode(rs.getInt("PIN_CODE"));
				pin.setPinClass(rs.getInt("PIN_CLASS"));
				pin.setPinMember(rs.getString("PIN_MEMBER"));
				pin.setPinStatus(rs.getString("PIN_STATUS"));
				pin.setPinDate(rs.getTimestamp("PIN_DATE"));
				list.add(pin);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}

	public int selectPinCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		
		try {
			String sql = prop.getProperty("selectPinCount");
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}

	public int searchCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;

		try {
			String sql = prop.getProperty("searchCount");
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if(rs.next()) {
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	public List<Order> descEnroll(Connection conn, String keyword, int cPage, int numPerPage) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Order> list = new ArrayList<Order>();

		try {
			String sql = prop.getProperty("descEnroll");
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (cPage-1)*numPerPage+1);
			pstmt.setInt(2, cPage*numPerPage);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				Order order = new Order();
				order.setOrderNo(rs.getString("order_no"));
				order.setOrderMember(rs.getString("order_member"));
				order.setOrderClass(rs.getInt("order_class"));
				order.setOrderClassOption(rs.getString("order_class_option"));
				order.setOrderType(rs.getString("order_type"));
				order.setOrderPrice(rs.getInt("order_price"));
				order.setOrderAddName(rs.getString("order_add_name"));
				order.setOrderAddPhone(rs.getString("order_add_phone"));
				order.setOrderAddAddress(rs.getString("order_add_address"));
				order.setOrderMsg(rs.getString("order_msg"));
				order.setOrderDate(rs.getTimestamp("order_date"));
				order.setOrderCate(rs.getString("order_cate"));
				list.add(order);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}
	
}
