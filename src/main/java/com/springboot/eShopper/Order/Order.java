package com.springboot.eShopper.Order;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.springboot.eShopper.Address.Address;
import com.springboot.eShopper.OrderItem.OrderItem;
import com.springboot.eShopper.User.User;

@Entity
@Table(name="order")
public class Order {
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="order_id")
	private int OrderId; // natural key
	
	@ManyToOne
	private User user;
	
	@OneToMany
	@JoinTable(
			name="order_order_item",
			joinColumns=@JoinColumn(name="order_id"),
			inverseJoinColumns=@JoinColumn(name="product_id")
	)
	private List<OrderItem> orderItems;
	
	@ManyToOne
	private Address userAddress;
	
	@Column(name="delivery_window")
	private Date deliveryWindow;
	
	@Column(name="delivery_fee")
	private double deliveryFee;
	
	@Column(name="shopper_tip")
	private double shopperTip;
	
	// Getters and Setters
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
