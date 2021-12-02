package com.springboot.eShopper.UserFavourites;

import com.springboot.eShopper.Product.Product;
import com.springboot.eShopper.User.User;

public class UserFavourites {
	private int favouriteId;	// surrogate key
	private User user;
	private Product product;
	
	public int getFavouriteId() {
		return favouriteId;
	}
	public void setFavouriteId(int favouriteId) {
		this.favouriteId = favouriteId;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
}
