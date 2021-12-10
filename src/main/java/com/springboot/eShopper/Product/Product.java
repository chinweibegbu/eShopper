package com.springboot.eShopper.Product;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.springboot.eShopper.Category.Category;
import com.springboot.eShopper.Favourite.Favourite;

@Entity
@Table(name="product")
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class Product {
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="product_id")
	private int productId;	// natural key
	
	@Column(name="product_name")
	private String productName;
	
	@ManyToOne
	private Category category;
	
	@Column(name="product_price")
	private double productPrice;
	
	@Column(name="stock_count")
	private int stockCount;
	
	@OneToMany(mappedBy="product", cascade = CascadeType.ALL)
	@ElementCollection(fetch=FetchType.EAGER)
	@JsonIgnore
	private Collection<Favourite> favourites = new ArrayList<>();
	
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
	public Collection<Favourite> getFavourites() {
		return favourites;
	}
	public void setFavourites(Collection<Favourite> favourites) {
		this.favourites = favourites;
	}
}
