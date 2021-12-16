package com.springboot.eShopper.PurchaseItem;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="api/v1/purchaseItems")
public class PurchaseItemController {
	
	private final PurchaseItemService purchaseItemService;
	
	@Autowired
	public PurchaseItemController(PurchaseItemService purchaseItemService) {
		this.purchaseItemService = purchaseItemService;
	}

	@GetMapping
	public List<PurchaseItem> getAllPurchaseItems() {
		return purchaseItemService.getAllPurchaseItems();
	}
	
	@GetMapping(path="{purchaseItemId}")
	public PurchaseItem getPurchaseItemById(@PathVariable("purchaseItemId") Integer purchaseItemId) {
		return purchaseItemService.getPurchaseItemById(purchaseItemId);
	}
	
	@GetMapping(path="purchase/{purchaseId}")
	public List<PurchaseItem> getPurchaseItemsByPurchase(@PathVariable("purchaseId") Integer purchaseId) {
		return purchaseItemService.getPurchaseItemsByPurchaseId(purchaseId);
	}
	
	@DeleteMapping(path="{purchaseItemId}")
	public void deletePurchaseItem(@PathVariable("purchaseItemId") Integer purchaseItemId) {
		purchaseItemService.deletePurchaseItem(purchaseItemId);
	}
	
	@PutMapping(path="{purchaseItemId}/increment")
	public void incrementItemCount(@PathVariable Integer purchaseItemId) {
		purchaseItemService.incrementItemCount(purchaseItemId);
	}
	
	@PutMapping(path="{purchaseItemId}/decrement")
	public void decrementItemCount(@PathVariable Integer purchaseItemId) {
		purchaseItemService.decrementItemCount(purchaseItemId);
	}
}
