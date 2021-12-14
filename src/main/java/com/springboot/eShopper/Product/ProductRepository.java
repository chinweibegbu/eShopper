package com.springboot.eShopper.Product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.springboot.eShopper.CartItem.CartItem;
import com.springboot.eShopper.Category.Category;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
	
	// Method to get a Product by Category
	List<Product> getProductsByCategory(Category category);
	
	@Query( value="select *, 0 AS clazz_ from product p where p.product_id not in (select product_id from cart_item)",
			nativeQuery=true)
	List<Product> getAllProducts();
	
	// Method to get cartItems that match a given searchTerm
	@Query( value="SELECT *, 0 AS clazz_ FROM product WHERE product_name ~* :searchTerm",
			nativeQuery=true)
	List<Product> findByProductNameContaining(@Param("searchTerm") String searchTerm);
}
