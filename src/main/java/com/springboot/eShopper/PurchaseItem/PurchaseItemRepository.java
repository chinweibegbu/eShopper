package com.springboot.eShopper.PurchaseItem;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.eShopper.Purchase.Purchase;

@Repository
public interface PurchaseItemRepository extends JpaRepository<PurchaseItem, Integer> {

	List<PurchaseItem> getPurchaseItemsByPurchase(Purchase purchase);
}
