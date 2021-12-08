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
	
	@PostMapping
	public void registerNewPurchaseItem(@RequestBody PurchaseItem purchaseItem) {
		purchaseItemService.addNewPurchaseItem(purchaseItem);
	}
	
	@DeleteMapping(path="{purchaseItemId}")
	public void deletePurchaseItem(@PathVariable("purchaseItemId") Integer purchaseItemId) {
		purchaseItemService.deletePurchaseItem(purchaseItemId);
	}
	
	@PutMapping(path="{purchaseItemId}")
	public void updatePurchaseItem(@RequestBody PurchaseItem purchaseItem, @PathVariable Integer purchaseItemId) {
		purchaseItemService.updatePurchaseItem(purchaseItem, purchaseItemId);
	}
}
