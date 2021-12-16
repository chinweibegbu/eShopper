package com.springboot.eShopper.PurchaseItem;
import java.util.*;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import com.springboot.eShopper.Purchase.Purchase;
import com.springboot.eShopper.Purchase.PurchaseRepository;

@Service
public class PurchaseItemService {
	
	private final PurchaseItemRepository purchaseItemRepository;
	private final PurchaseRepository purchaseRepository;
	
	@Autowired
	public PurchaseItemService(PurchaseItemRepository purchaseItemRepository, PurchaseRepository purchaseRepository) {
		this.purchaseItemRepository = purchaseItemRepository;
		this.purchaseRepository = purchaseRepository;
	}
	
	public List<PurchaseItem> getAllPurchaseItems() {
		return purchaseItemRepository.findAll();
	}
	
	public PurchaseItem getPurchaseItemById(Integer purchaseItemId) {
		// Get purchaseItem with the given ID
		boolean exists = purchaseItemRepository.existsById(purchaseItemId);
		
		if(!exists) {
			throw new IllegalStateException("PurchaseItem with an id of " + purchaseItemId + " does not exist");
		}
				
		return purchaseItemRepository.findById(purchaseItemId).get();
	}
	
	public List<PurchaseItem> getPurchaseItemsByPurchaseId(Integer purchaseId) {
		// Get purchase with the given ID
		boolean exists = purchaseRepository.existsById(purchaseId);
		
		if(!exists) {
			throw new IllegalStateException("Purchase with an id of " + purchaseId + " does not exist");
		}
		
		// Get purchase
		Purchase purchase = purchaseRepository.getById(purchaseId);
				
		return purchaseItemRepository.getPurchaseItemsByPurchase(purchase);
	}

	public void addNewPurchaseItem(PurchaseItem purchaseItem) {
		purchaseItemRepository.save(purchaseItem);		
	}

	public void deletePurchaseItem(Integer purchaseItemId) {
		// Get purchaseItem with the given ID
		boolean exists = purchaseItemRepository.existsById(purchaseItemId);
		
		if(!exists) {
			throw new IllegalStateException("PurchaseItem with an id of " + purchaseItemId + " does not exist");
		}
		
		// Else, save the purchaseItem
		purchaseItemRepository.deleteById(purchaseItemId);
	}

	@Transactional
	public void incrementItemCount(Integer purchaseItemId) {
		// Check if purchaseItem exists
		boolean exists = purchaseItemRepository.existsById(purchaseItemId);
		
		if(!exists) {
			throw new IllegalStateException("PurchaseItem with an id of " + purchaseItemId + " does not exist");
		}
		
		// Get purchaseItem
		PurchaseItem purchaseItemToUpdate = purchaseItemRepository.getById(purchaseItemId);
		
		// Increment item count
		Integer oldItemCount = purchaseItemToUpdate.getItemCount();
		purchaseItemToUpdate.setItemCount(oldItemCount+1);
		
		// Save purchaseItem		
		purchaseItemRepository.save(purchaseItemToUpdate);
	}
	
	@Transactional
	public void decrementItemCount(Integer purchaseItemId) {
		// Check if purchaseItem exists
		boolean exists = purchaseItemRepository.existsById(purchaseItemId);
		
		if(!exists) {
			throw new IllegalStateException("PurchaseItem with an id of " + purchaseItemId + " does not exist");
		}
		
		// Get purchaseItem
		PurchaseItem purchaseItemToUpdate = purchaseItemRepository.getById(purchaseItemId);
		
		// Increment item count
		Integer oldItemCount = purchaseItemToUpdate.getItemCount();
		purchaseItemToUpdate.setItemCount(oldItemCount-1);
		
		// Save purchaseItem		
		purchaseItemRepository.save(purchaseItemToUpdate);
	}
}
