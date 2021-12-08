package com.springboot.eShopper.Purchase;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="api/v1/purchases")
public class PurchaseController {
	
	private final PurchaseService purchaseService;
	
	@Autowired
	public PurchaseController(PurchaseService purchaseService) {
		this.purchaseService = purchaseService;
	}

	@GetMapping
	public List<Purchase> getAllPurchases() {
		return purchaseService.getAllPurchases();
	}
	
	@PostMapping
	public void registerNewPurchase(@RequestBody Purchase purchase) {
		purchaseService.addNewPurchase(purchase);
	}
	
	@DeleteMapping(path="{purchaseId}")
	public void deletePurchase(@PathVariable("purchaseId") Integer purchaseId) {
		purchaseService.deletePurchase(purchaseId);
	}
	
	@PutMapping(path="{purchaseId}")
	public void updatePurchase(@RequestBody Purchase purchase, @PathVariable Integer purchaseId) {
		purchaseService.updatePurchase(purchase, purchaseId);
	}
}
