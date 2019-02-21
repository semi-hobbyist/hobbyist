package com.hobbyist.order.model.vo;

import java.sql.Timestamp;

public class Order {
	
	private int orderNo;
	private String orderMember;
	private int orderClass;
	private String orderClassOption;
	private String orderType;
	private int orderPrice;
	private String orderAddName;
	private String orderAddPhone;
	private String orderAddAddress;
	private String orderMsg;
	private Timestamp orderDate;
	
	public Order(int orderNo, String orderMember, int orderClass, String orderClassOption, String orderType,
			int orderPrice, String orderAddName, String orderAddPhone, String orderAddAddress, String orderMsg,
			Timestamp orderDate) {
		super();
		this.orderNo = orderNo;
		this.orderMember = orderMember;
		this.orderClass = orderClass;
		this.orderClassOption = orderClassOption;
		this.orderType = orderType;
		this.orderPrice = orderPrice;
		this.orderAddName = orderAddName;
		this.orderAddPhone = orderAddPhone;
		this.orderAddAddress = orderAddAddress;
		this.orderMsg = orderMsg;
		this.orderDate = orderDate;
	}

	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

	public String getOrderMember() {
		return orderMember;
	}

	public void setOrderMember(String orderMember) {
		this.orderMember = orderMember;
	}

	public int getOrderClass() {
		return orderClass;
	}

	public void setOrderClass(int orderClass) {
		this.orderClass = orderClass;
	}

	public String getOrderClassOption() {
		return orderClassOption;
	}

	public void setOrderClassOption(String orderClassOption) {
		this.orderClassOption = orderClassOption;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public int getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(int orderPrice) {
		this.orderPrice = orderPrice;
	}

	public String getOrderAddName() {
		return orderAddName;
	}

	public void setOrderAddName(String orderAddName) {
		this.orderAddName = orderAddName;
	}

	public String getOrderAddPhone() {
		return orderAddPhone;
	}

	public void setOrderAddPhone(String orderAddPhone) {
		this.orderAddPhone = orderAddPhone;
	}

	public String getOrderAddAddress() {
		return orderAddAddress;
	}

	public void setOrderAddAddress(String orderAddAddress) {
		this.orderAddAddress = orderAddAddress;
	}

	public String getOrderMsg() {
		return orderMsg;
	}

	public void setOrderMsg(String orderMsg) {
		this.orderMsg = orderMsg;
	}

	public Timestamp getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Timestamp orderDate) {
		this.orderDate = orderDate;
	}
	
	
}
