package com.springboot.eShopper.Cart;

import java.util.ArrayList;
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

import com.springboot.eShopper.CartItem.CartItem;
import com.springboot.eShopper.Shopper.Shopper;

@Entity
@Table(name="cart")
public class Cart {
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="cart_id")
	private int cartId;	// surrogate key
	
	@ManyToOne
	private Shopper shopper;
	
	@OneToMany(mappedBy="cart", cascade = CascadeType.ALL)
	private List<CartItem> cartItems = new ArrayList<>();
	
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
	public Shopper getShopper() {
		return shopper;
	}
	public void setShopper(Shopper shopper) {
		this.shopper = shopper;
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
