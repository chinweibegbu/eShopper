package com.springboot.eShopper.Cart;
import java.util.*;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import com.springboot.eShopper.CartItem.CartItem;
import com.springboot.eShopper.CartItem.CartItemRepository;
import com.springboot.eShopper.Shopper.Shopper;

@Service
public class CartService {
	
	private final CartRepository cartRepository;
	private final CartItemRepository cartItemRepository;
	
	@Autowired
	public CartService(CartRepository cartRepository, CartItemRepository cartItemRepository) {
		this.cartRepository = cartRepository;
		this.cartItemRepository = cartItemRepository;
	}
	
	public List<Cart> getAllCarts() {
		return cartRepository.findAll();
	}
	
	public Cart getCartById(Integer cartId) {
		return cartRepository.findById(cartId).get();
	}
	
	public Cart getCartByShopper(Shopper shopper) {
		return cartRepository.getCartByShopper(shopper);
	}

	public void addNewCart(Cart cart) {
		cartRepository.save(cart);		
	}

	public void deleteCart(Integer cartId) {
		// Get cart with the given ID
		boolean exists = cartRepository.existsById(cartId);
		
		if(!exists) {
			throw new IllegalStateException("Cart with an id of " + cartId + " does not exist");
		}
		
		// Else, save the cart
		cartRepository.deleteById(cartId);
	}

	@Transactional
	public void updateCart(Cart cart, Integer cartId) {
		boolean exists = cartRepository.existsById(cartId);
		
		if(!exists) {
			throw new IllegalStateException("Cart with an id of " + cartId + " does not exist");
		}
		
		cartRepository.save(cart);
	}
	
	public void addCartItemToCart(Integer cartId, Integer cartItemId) {
		boolean cartItemExists = cartItemRepository.existsById(cartItemId);
		boolean cartExists = cartRepository.existsById(cartId);
		
		if(!cartItemExists) {
			throw new IllegalStateException("CartItem with an id of " + cartItemId + " does not exist");
		}
		
		if(!cartExists) {
			throw new IllegalStateException("Cart with an id of " + cartId + " does not exist");
		}
		
		// Else, get cart item
		CartItem newCartItem = cartItemRepository.getById(cartItemId);
		
		// Get cart
		Cart cartToUpdate = cartRepository.getById(cartId);
		
		// Cart item to cart's list
		cartToUpdate.getCartItems().add(newCartItem);
		
		// Save cart
		cartRepository.save(cartToUpdate);
	}
}
