package com.springboot.eShopper.Shopper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.springboot.eShopper.Cart.Cart;
import com.springboot.eShopper.Cart.CartService;

@RestController
@RequestMapping(path="api/v1/shoppers")
public class ShopperController {
	
	private final ShopperService shopperService;
	private final CartService cartService;
	
	@Autowired
	public ShopperController(ShopperService shopperService, CartService cartService) {
		this.shopperService = shopperService;
		this.cartService = cartService;
	}

	@GetMapping
	public List<Shopper> getAllShoppers() {
		return shopperService.getAllShoppers();
	}
	
	@PostMapping
	public void registerNewShopper(@RequestBody Shopper shopper) {
		shopperService.addNewShopper(shopper);
		
		// Create cart for shopper
		Cart shopperCart = new Cart();
		cartService.addNewCart(shopperCart);
		
		// Add cart to shopper
		shopper.setCart(shopperCart);
		
		// Add shopper to cart
		shopperCart.setShopper(shopper);
	}
	
	@DeleteMapping(path="{shopperId}")
	public void deleteShopper(@PathVariable("shopperId") Integer shopperId) {
		shopperService.deleteShopper(shopperId);
	}
	
	@PutMapping(path="{shopperId}")
	public void updateShopper(@RequestBody Shopper shopper, @PathVariable Integer shopperId) {
		shopperService.updateShopper(shopper, shopperId);
	}
}
