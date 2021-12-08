package com.springboot.eShopper.CartItem;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="api/v1/cartItems")
public class CartItemController {
	
	private final CartItemService cartItemService;
	
	@Autowired
	public CartItemController(CartItemService cartItemService) {
		this.cartItemService = cartItemService;
	}

	@GetMapping
	public List<CartItem> getAllCartItems() {
		return cartItemService.getAllCartItems();
	}
	
	@PostMapping
	public void registerNewCartItem(@RequestBody CartItem cartItem) {
		cartItemService.addNewCartItem(cartItem);
	}
	
	@DeleteMapping(path="{cartItemId}")
	public void deleteCartItem(@PathVariable("cartItemId") Integer cartItemId) {
		cartItemService.deleteCartItem(cartItemId);
	}
	
	@PutMapping(path="{cartItemId}")
	public void updateCartItem(@RequestBody CartItem cartItem, @PathVariable Integer cartItemId) {
		cartItemService.updateCartItem(cartItem, cartItemId);
	}
}
