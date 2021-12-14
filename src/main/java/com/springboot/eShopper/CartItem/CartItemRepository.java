package com.springboot.eShopper.CartItem;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.springboot.eShopper.Cart.Cart;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
	
	// Method to get cartItems in a given cart
	Optional<List<CartItem>> getCartItemsByCart(Cart cart);
	
	// Method to get cartItems that match a given searchTerm
	@Query( value="SELECT * FROM cart_item WHERE product_name ~* :searchTerm",
			nativeQuery=true)
	List<CartItem> findByProductNameContaining(@Param("searchTerm") String searchTerm);
}
