package com.qianfeng.xiaomi.vo;

import com.qianfeng.xiaomi.order.pojo.Order;

import java.util.Date;

/**
 * 页面中展示Order
 * @author wgy
 *
 */
public class OrderVo extends Order {
	private String username;
	public OrderVo() {
		// TODO Auto-generated constructor stub
	}
	
	

	public OrderVo(String id, int uid, double money, String status, Date time, int aid, String address,String username) {
		super(id, uid, money, status, time,address);
		this.username=username;
	}
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
}
