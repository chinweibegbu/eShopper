package com.springboot.eShopper.Favourite;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.springboot.eShopper.Product.Product;
import com.springboot.eShopper.User.User;

@Entity
@Table(name="favourite")
public class Favourite {
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="favorite_id")
	private int favouriteId;	// surrogate key
	
	@ManyToOne
	private User user;
	
	@ManyToOne
	private Product product;
	
	// Getters and Setters
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
