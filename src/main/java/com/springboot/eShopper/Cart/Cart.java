package com.springboot.eShopper.Cart;

import java.util.List;

import com.springboot.eShopper.CartItem.CartItem;
import com.springboot.eShopper.User.User;

public class Cart {
	private int cartId;	// surrogate key
	private User user;
	
	public int getCartId() {
		return cartId;
	}
	public void setCartId(int cartId) {
		this.cartId = cartId;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<CartItem> getCartItems() {
		return cartItems;
	}
	public void setCartItems(List<CartItem> cartItems) {
		this.cartItems = cartItems;
	}
	public double getWalletTop() {
		return walletTop;
	}
	public void setWalletTop(double walletTop) {
		this.walletTop = walletTop;
	}
	public double getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}
	private List<CartItem> cartItems;
	private double walletTop;
	private double totalCost;
}
