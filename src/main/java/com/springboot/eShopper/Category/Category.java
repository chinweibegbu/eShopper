package com.springboot.eShopper.Category;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.springboot.eShopper.Product.Product;

@Entity
@Table(name="category")
public class Category {
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="category_id")
	private int categoryId;	// natural key
	
	@Column(name="category_name")
	private String categoryName;
	
	@OneToMany(mappedBy="category", cascade = CascadeType.ALL)
	@ElementCollection(fetch=FetchType.EAGER)
	@JsonIgnore
	private Collection<Product> products = new ArrayList<>();
	
	// Getters and Setters
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public Collection<Product> getProducts() {
		return products;
	}
	public void setProducts(Collection<Product> products) {
		this.products = products;
	}
}
