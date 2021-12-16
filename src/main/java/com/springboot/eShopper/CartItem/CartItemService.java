package com.springboot.eShopper.CartItem;
import java.util.*;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import com.springboot.eShopper.Cart.Cart;
import com.springboot.eShopper.Cart.CartRepository;
import com.springboot.eShopper.Product.Product;
import com.springboot.eShopper.Product.ProductRepository;

@Service
public class CartItemService {
	
	private final CartItemRepository cartItemRepository;
	private final CartRepository cartRepository;
	private final ProductRepository productRepository;
	
	@Autowired
	public CartItemService(CartItemRepository cartItemRepository, CartRepository cartRepository, ProductRepository productRepository) {
		this.cartItemRepository = cartItemRepository;
		this.cartRepository = cartRepository;
		this.productRepository = productRepository;
	}
	
	public List<CartItem> getAllCartItems() {
		return cartItemRepository.findAll();
	}
	
	public List<CartItem> getAllCartItemsByCartId(Integer cartId) {
		// Get if cart exists
		boolean exists = cartRepository.existsById(cartId);
		
		if(!exists) {
			throw new IllegalStateException("Cart with an id of " + cartId + " does not exist");
		}
		
		// Else, return cart items in that cart
		return cartItemRepository.getCartItemsByCart(cartRepository.getById(cartId)).get();
	}

	public CartItem getCartItemById(Integer cartItemId) {
		// Get if cartItem exists
		boolean exists = cartItemRepository.existsById(cartItemId);
		
		if(!exists) {
			throw new IllegalStateException("CartItem with an id of " + cartItemId + " does not exist");
		}
		
		// Else, return cartItem
		return cartItemRepository.findById(cartItemId).get();
	}
	
	public List<CartItem> searchCartItemsByProductName(String searchTerm) {
		return cartItemRepository.findByProductNameContaining(searchTerm);
	}
	
	public void addNewCartItem(Integer productId, Integer itemCount, Integer cartId) {		
		// Check if product exists
		boolean productExists = productRepository.existsById(productId);
		
		if(!productExists) {
			throw new IllegalStateException("Product with an id of " + productId + " does not exist");
		}
		
		// Get product
		Product product = productRepository.getById(productId);
		
		// Create new cartItem
		CartItem cartItem = new CartItem();
		
		// Set cartItem details
		cartItem.setProductName(product.getProductName());
		cartItem.setProductPrice(product.getProductPrice());
		cartItem.setStockCount(product.getStockCount());
		cartItem.setItemCount(itemCount);
		
		// Check if cart exists
		boolean cartExists = cartRepository.existsById(cartId);
		
		if(!cartExists) {
			throw new IllegalStateException("Cart with an id of " + cartId + " does not exist");
		}
		
		// Get cart
		Cart cart = cartRepository.getById(cartId);
		
		// Add cart item to cart's list
		cart.getCartItems().add(cartItem);
		
		// Set cart items's cart
		cartItem.setCart(cart);
		
		// Save cart to DB
		cartRepository.save(cart);
	}

	public void deleteCartItem(Integer cartId, Integer cartItemId) {
		// Check if cart and cartItemId work
		boolean cartExists = cartRepository.existsById(cartId);
		boolean cartItemExists = cartItemRepository.existsById(cartItemId);
		
		if(!cartExists) {
			throw new IllegalStateException("Cart with an id of " + cartId + " does not exist");
		}
		
		if(!cartItemExists) {
			throw new IllegalStateException("CartItem with an id of " + cartItemId + " does not exist");
		}
		
		// Else
		// Get cart and cart item
		Cart cartToUpdate = cartRepository.getById(cartId);
		CartItem cartItemToDelete = cartItemRepository.getById(cartItemId);
		
		// Delete cart item from cart's list
		cartToUpdate.getCartItems().remove(cartItemToDelete);
		
		// Detach cart from cart item
		cartItemToDelete.setCart(null);
				
		// Delete cartItem
		cartItemRepository.deleteById(cartItemId);
	}

	@Transactional
	public void updateCartItem(CartItem cartItem, Integer cartItemId) {
		boolean exists = cartItemRepository.existsById(cartItemId);
		
		if(!exists) {
			throw new IllegalStateException("CartItem with an id of " + cartItemId + " does not exist");
		}
		
		// Get cartItem to update
		CartItem cartItemToUpdate = cartItemRepository.getById(cartItemId);
		
		// Get new itemCount
		Integer newItemCount = cartItem.getItemCount();
		
		// Validate newItemCount
		if(newItemCount <= 0) {
			throw new IllegalStateException("CartItem must have an itemCount of at least 1");
		} else if(newItemCount == cartItemToUpdate.getItemCount()) {
			throw new IllegalStateException("CartItem itemCount is the same");
		} else {
			cartItemToUpdate.setItemCount(newItemCount);
		}
		
		cartItemRepository.save(cartItemToUpdate);
	}
}
