package com.springboot.eShopper.Cart;
import java.util.*;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import com.springboot.eShopper.CartItem.CartItem;
import com.springboot.eShopper.CartItem.CartItemRepository;
import com.springboot.eShopper.Shopper.Shopper;
import com.springboot.eShopper.Shopper.ShopperRepository;

@Service
public class CartService {
	
	private final CartRepository cartRepository;
	private final CartItemRepository cartItemRepository;
	private final ShopperRepository shopperRepository;
	
	@Autowired
	public CartService(CartRepository cartRepository, CartItemRepository cartItemRepository, ShopperRepository shopperRepository) {
		this.cartRepository = cartRepository;
		this.cartItemRepository = cartItemRepository;
		this.shopperRepository = shopperRepository;
	}
	
	public List<Cart> getAllCarts() {
		return cartRepository.findAll();
	}
	
	public Cart getCartById(Integer cartId) {
		return cartRepository.findById(cartId).get();
	}
	
	public Cart getCartByShopperId(Integer shopperId) {
		// Get cart with the given ID
		boolean exists = shopperRepository.existsById(shopperId);
		
		if(!exists) {
			throw new IllegalStateException("Shopper with an id of " + shopperId + " does not exist");
		}
		
		// Else, get the shopper
		Shopper shopper = shopperRepository.getById(shopperId);		
		
		// Return cart by shopper
		return cartRepository.getCartByShopper(shopper);
	}

	@Transactional
	public void updateCart(Cart cart, Integer cartId) {
		boolean exists = cartRepository.existsById(cartId);
		
		if(!exists) {
			throw new IllegalStateException("Cart with an id of " + cartId + " does not exist");
		}
		
		Cart cartToUpdate = cartRepository.getById(cartId);
		Double walletTop = cart.getWalletTop();
		Double totalCost = cart.getTotalCost();
		
		if(!(walletTop == 0.00)) {
			if(walletTop <= 0.0) {
				throw new IllegalStateException("Wallet top cannot be less than 0.00");
			} else if(walletTop == cartToUpdate.getWalletTop()) {
				throw new IllegalStateException("Wallet top is the same");
			} else {
				cartToUpdate.setWalletTop(walletTop);
			}
		}
		
		if(!(totalCost == 0.00)) {
			if(totalCost < 0.0) {
				throw new IllegalStateException("Total cost cannot be less than 0.00");
			} else if(totalCost == cartToUpdate.getTotalCost()) {
				throw new IllegalStateException("Total cost is the same");
			} else {
				cartToUpdate.setTotalCost(totalCost);
			}
		}
		
		cartRepository.save(cartToUpdate);
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
