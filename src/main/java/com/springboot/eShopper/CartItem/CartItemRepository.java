package com.springboot.eShopper.CartItem;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.eShopper.Cart.Cart;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
	
	// Method to get cartItems in a given cart
	Optional<List<CartItem>> getCartItemsByCart(Cart cart);
}
