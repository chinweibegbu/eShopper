package com.springboot.eShopper.Shopper;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="api/v1/shoppers")
public class ShopperController {
	
	private final ShopperService shopperService;
	
	@Autowired
	public ShopperController(ShopperService shopperService) {
		this.shopperService = shopperService;
	}

	@GetMapping
	public List<Shopper> getAllShoppers() {
		return shopperService.getAllShoppers();
	}
	
	@GetMapping(path="{shopperId}")
	public Shopper getShopperById(@PathVariable("shopperId") Integer shopperId) {
		return shopperService.getShopperById(shopperId);
	}
	
	@PostMapping
	public void registerNewShopper(@RequestBody Shopper shopper) {		
		shopperService.addNewShopper(shopper);
	}
	
	@DeleteMapping(path="{shopperId}")
	public void deleteShopper(@PathVariable("shopperId") Integer shopperId) {
		shopperService.deleteShopper(shopperId);
	}
	
	@PutMapping(path="{shopperId}")
	public void updateShopper(@RequestBody Shopper shopper, @PathVariable Integer shopperId) {
		shopperService.updateShopper(shopper, shopperId);
	}
	
	@PutMapping(path="/blist/{shopperId}")
	public void blacklistShopper(@RequestBody(required=false) Shopper shopper, @PathVariable Integer shopperId) {
		shopperService.blacklistShopper(shopperId, shopper);
	}
}
