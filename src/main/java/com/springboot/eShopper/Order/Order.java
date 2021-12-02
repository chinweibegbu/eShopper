package com.springboot.eShopper.Order;

import java.util.Date;
import java.util.List;

import com.springboot.eShopper.Address.Address;
import com.springboot.eShopper.OrderItem.OrderItem;
import com.springboot.eShopper.User.User;

public class Order {
	private int OrderId; // natural key
	private User user;
	private List<OrderItem> orderItems;
	private Address userAddress;
	private Date deliveryWindow;
	private double deliveryFee;
	private double shopperTip;
	
	public int getOrderId() {
		return OrderId;
	}
	public void setOrderId(int orderId) {
		OrderId = orderId;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<OrderItem> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}
	public Address getUserAddress() {
		return userAddress;
	}
	public void setUserAddress(Address userAddress) {
		this.userAddress = userAddress;
	}
	public Date getDeliveryWindow() {
		return deliveryWindow;
	}
	public void setDeliveryWindow(Date deliveryWindow) {
		this.deliveryWindow = deliveryWindow;
	}
	public double getDeliveryFee() {
		return deliveryFee;
	}
	public void setDeliveryFee(double deliveryFee) {
		this.deliveryFee = deliveryFee;
	}
	public double getShopperTip() {
		return shopperTip;
	}
	public void setShopperTip(double shopperTip) {
		this.shopperTip = shopperTip;
	}
}
