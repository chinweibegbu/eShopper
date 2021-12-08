package com.springboot.eShopper.Cart;
import java.util.*;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

@Service
public class CartService {
	
	private final CartRepository cartRepository;
	
	@Autowired
	public CartService(CartRepository cartRepository) {
		this.cartRepository = cartRepository;
	}
	
	public List<Cart> getAllCarts() {
		return cartRepository.findAll();
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
}
