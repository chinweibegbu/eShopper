package com.springboot.eShopper.Product;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.springboot.eShopper.Category.Category;
import com.springboot.eShopper.Favourite.Favourite;

@Entity
@Table(name="product")
public class Product {
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="product_id")
	private int productId;	// natural key
	
	@Column(name="product_id")
	private String productName;
	
	@ManyToOne
	private Category category;
	
	@Column(name="product_price")
	private double productPrice;
	
	@Column(name="stock_count")
	private int stockCount;
	
	@OneToMany
	@JoinTable(
			name="product_favourite",
			joinColumns=@JoinColumn(name="product_id"),
			inverseJoinColumns=@JoinColumn(name="favourite_id")
	)
	private List<Favourite> favourites;
	
	// Getters and Setters
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public double getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}
	public int getStockCount() {
		return stockCount;
	}
	public void setStockCount(int stockCount) {
		this.stockCount = stockCount;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public List<Favourite> getFavourites() {
		return favourites;
	}
	public void setFavourites(List<Favourite> favourites) {
		this.favourites = favourites;
	}
}
