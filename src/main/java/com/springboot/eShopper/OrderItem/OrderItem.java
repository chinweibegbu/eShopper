package com.springboot.eShopper.OrderItem;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.springboot.eShopper.Order.Order;
import com.springboot.eShopper.Product.Product;

@Entity
@Table(name="order_item")
public class OrderItem extends Product {
	@ManyToOne
	private Order order;
	
	@Column(name="item_count")
	private int itemCount;

	// Getter and Setters
	public int getItemCount() {
		return itemCount;
	}
	public void setItemCount(int itemCount) {
		this.itemCount = itemCount;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
}
