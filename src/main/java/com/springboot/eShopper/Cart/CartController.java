package com.springboot.eShopper.Cart;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="api/v1/carts")
public class CartController {
	
	private final CartService cartService;
	
	@Autowired
	public CartController(CartService cartService) {
		this.cartService = cartService;
	}

	@GetMapping
	public List<Cart> getAllCarts() {
		return cartService.getAllCarts();
	}
	
	@PostMapping
	public void registerNewCart(@RequestBody Cart cart) {
		cartService.addNewCart(cart);
	}
	
	@DeleteMapping(path="{cartId}")
	public void deleteCart(@PathVariable("cartId") Integer cartId) {
		cartService.deleteCart(cartId);
	}
	
	@PutMapping(path="{cartId}")
	public void updateCart(@RequestBody Cart cart, @PathVariable Integer cartId) {
		cartService.updateCart(cart, cartId);
	}
}
