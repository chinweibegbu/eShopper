package com.springboot.eShopper.Purchase;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.springboot.eShopper.Address.Address;

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
	
	@GetMapping(path="{purchaseId}")
	public Purchase getPurchaseById(@PathVariable("purchaseId") Integer purchaseId) {
		return purchaseService.getPurchaseById(purchaseId);
	}
	
	@GetMapping(path="shopper/{shopperId}")
	public List<Purchase> getAllPurchasesByShopper(@PathVariable("shopperId") Integer shopperId) {
		return purchaseService.getPurchasesByShopperId(shopperId);
	}
	
	@PostMapping(path="cart/{cartId}")
	public void addNewPurchase(@PathVariable("cartId") Integer cartId) {
		purchaseService.addNewPurchase(cartId);
	}
	
	@PostMapping(path="{purchaseId}/delivery")
	public ResponseEntity<List<Date>> addDeliveryAddress(@PathVariable("purchaseId") Integer purchaseId, @RequestBody Map<String, Integer> deliveryAddress) {
		try {
			List<Date> deliveryWindows = purchaseService.addDeliveryAddress(purchaseId, deliveryAddress.get("addressId"));
			
			return ResponseEntity.ok(deliveryWindows);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return ResponseEntity.ok(new ArrayList<Date>());
	}
	
	@PostMapping(path="{purchaseId}/deliveryWindow")
	public void addDeliveryWindow(@PathVariable("purchaseId") Integer purchaseId, @RequestBody Map<String, String> deliveryWindow) {
		try {
			purchaseService.addDeliveryWindow(purchaseId, deliveryWindow.get("window"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	@PostMapping(path="payment")
	public void addPaymentDetails(@RequestBody Map<String, String> payment) {
		purchaseService.addPaymentDetails();
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
