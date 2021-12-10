package com.springboot.eShopper.CartItem;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonObjectSerializer;
import org.springframework.web.bind.annotation.*;

import com.springboot.eShopper.Cart.Cart;

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
	
	@GetMapping(path="/cart/{cartId}")
	public List<CartItem> getAllCartItemsByCartId(@PathVariable("cartId") Integer cartId) {
		return cartItemService.getAllCartItemsByCartId(cartId);
	}
	
	@GetMapping(path="{cartItemId}")
	public CartItem getCartItemById(@PathVariable("cartItemId") Integer cartItemId) {
		return cartItemService.getCartItemById(cartItemId);
	}
	
	@PostMapping
	public void addNewCartItem(@RequestBody Map<String, Integer> input) {		
		cartItemService.addNewCartItem(input.get("productId"), input.get("productCount"), input.get("cartId"));
	}
	
	@DeleteMapping(path="{cartId}/{cartItemId}")
	public void deleteCartItem(@PathVariable("cartId") Integer cartId, @PathVariable("cartItemId") Integer cartItemId) {
		cartItemService.deleteCartItem(cartId, cartItemId);
	}
	
	@PutMapping(path="{cartItemId}")
	public void updateCartItem(@RequestBody CartItem cartItem, @PathVariable Integer cartItemId) {
		cartItemService.updateCartItem(cartItem, cartItemId);
	}
}
