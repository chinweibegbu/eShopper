package com.springboot.eShopper.Favourite;
import java.util.*;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import com.springboot.eShopper.Product.Product;
import com.springboot.eShopper.Product.ProductRepository;
import com.springboot.eShopper.Shopper.Shopper;
import com.springboot.eShopper.Shopper.ShopperRepository;

@Service
public class FavouriteService {
	
	private final FavouriteRepository favouriteRepository;
	private final ShopperRepository shopperRepository;
	private final ProductRepository productRepository;
	
	@Autowired
	public FavouriteService(FavouriteRepository favouriteRepository, ShopperRepository shopperRepository, ProductRepository productRepository) {
		this.favouriteRepository = favouriteRepository;
		this.shopperRepository = shopperRepository;
		this.productRepository = productRepository;
	}
	
	public List<Favourite> getAllFavourites() {
		return favouriteRepository.findAll();
	}
	
	public Favourite getFavouriteById(Integer favouriteId) {
		// Check if favourite exists
		boolean exists = favouriteRepository.existsById(favouriteId);
		
		if(!exists) {
			throw new IllegalStateException("Favourite with an id of " + favouriteId + " does not exist");
		}
				
		return favouriteRepository.findById(favouriteId).get();
	}
	
	public List<Favourite> getFavouritesByShopperId(Integer shopperId) {
		// Check if shopper exists
		boolean exists = shopperRepository.existsById(shopperId);
		
		if(!exists) {
			throw new IllegalStateException("Shopper with an id of " + shopperId + " does not exist");
		}
		
		// Get shopper with the given ID
		Shopper shopper = shopperRepository.findById(shopperId).get();
		
		return favouriteRepository.getFavouritesByShopper(shopper);
	}
	
	public List<Favourite> getFavouritesByProductId(Integer productId) {
		// Check if product exists
		boolean exists = productRepository.existsById(productId);
		
		if(!exists) {
			throw new IllegalStateException("Product with an id of " + productId + " does not exist");
		}
				
		// Get product with the given ID
		Product product = productRepository.findById(productId).get();
		
		return favouriteRepository.getFavouritesByProduct(product);
	}

	public void addNewFavourite(Favourite favourite) {
		favouriteRepository.save(favourite);		
	}

	public void deleteFavourite(Integer favouriteId) {
		// Check if favourite exists
		boolean exists = favouriteRepository.existsById(favouriteId);
		
		if(!exists) {
			throw new IllegalStateException("Favourite with an id of " + favouriteId + " does not exist");
		}
		
		// Else, delete the favourite
		favouriteRepository.deleteById(favouriteId);
	}

	@Transactional
	public void updateFavourite(Favourite favourite, Integer favouriteId) {
		boolean exists = favouriteRepository.existsById(favouriteId);
		
		if(!exists) {
			throw new IllegalStateException("Favourite with an id of " + favouriteId + " does not exist");
		}
		
		favouriteRepository.save(favourite);
	}
}
