package com.springboot.eShopper.Product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.eShopper.Category.Category;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
	
	// Method to get a Product by Category
	List<Product> getProductsByCategory(Category category);
}
