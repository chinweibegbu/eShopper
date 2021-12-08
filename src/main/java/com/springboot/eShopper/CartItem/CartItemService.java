package com.springboot.eShopper.CartItem;
import java.util.*;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

@Service
public class CartItemService {
	
	private final CartItemRepository cartItemRepository;
	
	@Autowired
	public CartItemService(CartItemRepository cartItemRepository) {
		this.cartItemRepository = cartItemRepository;
	}
	
	public List<CartItem> getAllCartItems() {
		return cartItemRepository.findAll();
	}

	public void addNewCartItem(CartItem cartItem) {
		cartItemRepository.save(cartItem);		
	}

	public void deleteCartItem(Integer cartItemId) {
		// Get cartItem with the given ID
		boolean exists = cartItemRepository.existsById(cartItemId);
		
		if(!exists) {
			throw new IllegalStateException("CartItem with an id of " + cartItemId + " does not exist");
		}
		
		// Else, save the cartItem
		cartItemRepository.deleteById(cartItemId);
	}

	@Transactional
	public void updateCartItem(CartItem cartItem, Integer cartItemId) {
		boolean exists = cartItemRepository.existsById(cartItemId);
		
		if(!exists) {
			throw new IllegalStateException("CartItem with an id of " + cartItemId + " does not exist");
		}
		
		cartItemRepository.save(cartItem);
	}
}
