package com.springboot.eShopper.PurchaseItem;
import java.util.*;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

@Service
public class PurchaseItemService {
	
	private final PurchaseItemRepository purchaseItemRepository;
	
	@Autowired
	public PurchaseItemService(PurchaseItemRepository purchaseItemRepository) {
		this.purchaseItemRepository = purchaseItemRepository;
	}
	
	public List<PurchaseItem> getAllPurchaseItems() {
		return purchaseItemRepository.findAll();
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
	public void updatePurchaseItem(PurchaseItem purchaseItem, Integer purchaseItemId) {
		boolean exists = purchaseItemRepository.existsById(purchaseItemId);
		
		if(!exists) {
			throw new IllegalStateException("PurchaseItem with an id of " + purchaseItemId + " does not exist");
		}
		
		purchaseItemRepository.save(purchaseItem);
	}
}
