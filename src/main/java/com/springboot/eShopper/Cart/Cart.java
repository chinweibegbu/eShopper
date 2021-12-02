package com.springboot.eShopper.Cart;

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

import com.springboot.eShopper.CartItem.CartItem;
import com.springboot.eShopper.User.User;

@Entity
@Table(name="cart")
public class Cart {
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="cart_id")
	private int cartId;	// surrogate key
	
	@ManyToOne
	private User user;
	
	@OneToMany
	@JoinTable(
			name="cart_cart_item",
			joinColumns=@JoinColumn(name="cart_id"),
			inverseJoinColumns=@JoinColumn(name="product_id")
	)
	private List<CartItem> cartItems;
	
	@Column(name="wallet_top")
	private double walletTop;
	
	@Column(name="total_cost")
	private double totalCost;
	
	// Getters and Setters
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
}
