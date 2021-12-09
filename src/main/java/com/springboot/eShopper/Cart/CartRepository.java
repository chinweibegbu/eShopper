package com.springboot.eShopper.Cart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.eShopper.Shopper.Shopper;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

	// Method to get a Cart by Shopper
	Cart getCartByShopper(Shopper shopper);
}
