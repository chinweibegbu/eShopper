package com.springboot.eShopper.Purchase;
import java.util.*;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

@Service
public class PurchaseService {
	
	private final PurchaseRepository purchaseRepository;
	
	@Autowired
	public PurchaseService(PurchaseRepository purchaseRepository) {
		this.purchaseRepository = purchaseRepository;
	}
	
	public List<Purchase> getAllPurchases() {
		return purchaseRepository.findAll();
	}

	public void addNewPurchase(Purchase purchase) {
		purchaseRepository.save(purchase);		
	}

	public void deletePurchase(Integer purchaseId) {
		// Get purchase with the given ID
		boolean exists = purchaseRepository.existsById(purchaseId);
		
		if(!exists) {
			throw new IllegalStateException("Purchase with an id of " + purchaseId + " does not exist");
		}
		
		// Else, save the purchase
		purchaseRepository.deleteById(purchaseId);
	}

	@Transactional
	public void updatePurchase(Purchase purchase, Integer purchaseId) {
		boolean exists = purchaseRepository.existsById(purchaseId);
		
		if(!exists) {
			throw new IllegalStateException("Purchase with an id of " + purchaseId + " does not exist");
		}
		
		purchaseRepository.save(purchase);
	}
}
