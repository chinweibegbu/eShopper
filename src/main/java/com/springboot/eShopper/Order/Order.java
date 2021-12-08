package com.springboot.eShopper.Order;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.springboot.eShopper.Address.Address;
import com.springboot.eShopper.OrderItem.OrderItem;
import com.springboot.eShopper.Shopper.Shopper;

@Entity
@Table(name="order")
public class Order {
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="order_id")
	private int orderId; // natural key
	
	@ManyToOne
	private Shopper shopper;
	
	@OneToMany(mappedBy="order", cascade = CascadeType.ALL)
	private List<OrderItem> orderItems;
	
	@ManyToOne
	private Address shopperAddress;
	
	@Column(name="delivery_window")
	private Date deliveryWindow;
	
	@Column(name="delivery_fee")
	private double deliveryFee;
	
	@Column(name="shopper_tip")
	private double shopperTip;
	
	// Getters and Setters
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		orderId = orderId;
	}
	public Shopper getShopper() {
		return shopper;
	}
	public void setShopper(Shopper shopper) {
		this.shopper = shopper;
	}
	public List<OrderItem> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}
	public Address getShopperAddress() {
		return shopperAddress;
	}
	public void setShopperAddress(Address shopperAddress) {
		this.shopperAddress = shopperAddress;
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
