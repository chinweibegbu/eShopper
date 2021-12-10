package com.springboot.eShopper.CartItem;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.springboot.eShopper.Cart.Cart;
import com.springboot.eShopper.Product.Product;

@Entity
@Table(name="cart_item")
public class CartItem extends Product {
	@ManyToOne
	@JsonIgnore
	private Cart cart;
	
	@Column(name="item_count")
	private int itemCount;

	// Getters and Setters
	public int getItemCount() {
		return itemCount;
	}
	public void setItemCount(int itemCount) {
		this.itemCount = itemCount;
	}
	public Cart getCart() {
		return cart;
	}
	public void setCart(Cart cart) {
		this.cart = cart;
	}
}
