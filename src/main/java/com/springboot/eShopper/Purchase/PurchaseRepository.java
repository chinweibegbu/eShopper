package com.springboot.eShopper.Purchase;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.eShopper.Shopper.Shopper;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Integer> {
	
	List<Purchase> getPurchasesByShopper(Shopper shopper);
}
