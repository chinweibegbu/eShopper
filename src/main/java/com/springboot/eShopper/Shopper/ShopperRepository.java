package com.springboot.eShopper.Shopper;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopperRepository extends JpaRepository<Shopper, Integer> {

	// Method to get a Shopper by Email
	Optional<Shopper> getShopperByEmail(String email);
}
