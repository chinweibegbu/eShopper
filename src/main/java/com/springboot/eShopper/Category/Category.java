package com.springboot.eShopper.Category;

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

import com.springboot.eShopper.Product.Product;

@Entity
@Table(name="category")
public class Category {
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="category_id")
	private int categoryId;	// natural key
	
	@Column(name="product_id")
	private String categoryName;
	
	@OneToMany
	@JoinTable(
			name="category_sub_category",
			joinColumns=@JoinColumn(name="category_id"),
			inverseJoinColumns=@JoinColumn(name="sub_category_id")
	)
	private List<Category> subCategories;
	
	@ManyToOne
	private Category parentCategory;
	
	@OneToMany
	@JoinTable(
			name="category_product",
			joinColumns=@JoinColumn(name="category_id"),
			inverseJoinColumns=@JoinColumn(name="product_id")
	)
	private List<Product> products;
	
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
	public List<Category> getSubCategories() {
		return subCategories;
	}
	public void setSubCategories(List<Category> subCategories) {
		this.subCategories = subCategories;
	}
}
