package com.springboot.eShopper.Favourite;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.eShopper.Product.Product;
import com.springboot.eShopper.Shopper.Shopper;

@Repository
public interface FavouriteRepository extends JpaRepository<Favourite, Integer> {

	// Method to get cartItems in a given cart
	List<Favourite> getFavouritesByShopper(Shopper shoper);
	
	// Method to get cartItems in a given cart
	List<Favourite> getFavouritesByProduct(Product product);
}
