package com.springboot.eShopper.Shopper;
import java.util.*;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import com.springboot.eShopper.Cart.Cart;
import com.springboot.eShopper.Cart.CartRepository;

@Service
public class ShopperService {
	
	private final ShopperRepository shopperRepository;
	private final CartRepository cartRepository;
	
	@Autowired
	public ShopperService(ShopperRepository shopperRepository, CartRepository cartRepository) {
		this.shopperRepository = shopperRepository;
		this.cartRepository = cartRepository;
	}
	
	public List<Shopper> getAllShoppers() {
		return shopperRepository.findAll();
	}
	
	public Shopper getShopperById(Integer shopperId) {
		// Check if shopper exists
		boolean exists = shopperRepository.existsById(shopperId);
		
		if(!exists) {
			throw new IllegalStateException("Shopper with an id of " + shopperId + " does not exist");
		}
		
		return shopperRepository.findById(shopperId).get();
	}

	public void addNewShopper(Shopper shopper) {
		// If the email address exists in the DB, throw an error
		Optional<Shopper> shopperWithEmail = shopperRepository.getShopperByEmail(shopper.getEmail());
		
		if(shopperWithEmail.isPresent()) {
			throw new IllegalStateException("Email already exists");
		}
		
		// Else, save the shopper
		shopperRepository.save(shopper);
		
		// Create new cart
		Cart shopperCart = new Cart();
		
		// Add cart to DB
		cartRepository.save(shopperCart);
		
		// Add shopper to cart
		shopper.setCart(shopperCart);
		
		// Add cart to shopper
		shopperCart.setShopper(shopper);
		
		// Update shopper and cart in DB
		shopperRepository.save(shopper);
		cartRepository.save(shopperCart);
	}

	public void deleteShopper(Integer shopperId) {
		// Get shopper with the given ID
		boolean exists = shopperRepository.existsById(shopperId);
		
		if(!exists) {
			throw new IllegalStateException("Shopper with an id of " + shopperId + " does not exist");
		}
		
		// Else, delete shopper's cart
		Shopper shopperToDelete = shopperRepository.getById(shopperId);
		Cart cartToDelete = cartRepository.getCartByShopper(shopperToDelete);
		
		// Dereference shopper cart
		shopperToDelete.setCart(null);
		
		// Delete shopper's cart
		cartRepository.delete(cartToDelete);
		
		// Delete shopper
		shopperRepository.deleteById(shopperId);
	}

	@Transactional
	public void updateShopper(Shopper shopper, Integer shopperId) {
		boolean exists = shopperRepository.existsById(shopperId);
		
		if(!exists) {
			throw new IllegalStateException("Shopper with an id of " + shopperId + " does not exist");
		}
		
		Shopper shopperToUpdate = shopperRepository.getById(shopperId);
		String newFirstName = shopper.getFirstName();
		String newEmail = shopper.getEmail();
		
		if(!(newFirstName == null)) {
			if (newFirstName .length() > 0 && shopperToUpdate.getFirstName() != newFirstName) {
				shopperToUpdate.setFirstName(shopper.getFirstName());
			} else if(newFirstName .length() <= 0) {
				throw new IllegalStateException("Name cannot be blank");
			}
		}
		
		Optional<Shopper> shopperWithEmail = shopperRepository.getShopperByEmail(shopper.getEmail());
		
		if(shopperWithEmail.isPresent()) {
			throw new IllegalStateException("New email already exists");
		} else if(!(newEmail == null)) {
			if(newEmail.length() > 0 && shopperToUpdate.getEmail() != newEmail) {
				shopperToUpdate.setEmail(shopper.getEmail());
			} else if(newEmail.length() <= 0) {
				throw new IllegalStateException("Email cannot be blank");
			}
		}
		
		shopperRepository.save(shopperToUpdate);
	}
	
	public void blacklistShopper(Integer shopperId, Shopper updatedShopper) {
		// Check if user exists
		boolean exists = shopperRepository.existsById(shopperId);
		
		if(!exists) {
			throw new IllegalStateException("Shopper with an id of " + shopperId + " does not exist");
		}
		
		// Else, update blacklist member variables
		Shopper shopperToBlacklist = shopperRepository.findById(shopperId).get();
		shopperToBlacklist.setBlacklisted(true);
		
		if(updatedShopper != null) {
			String blacklistDetails = updatedShopper.getBlacklistDetails();
			
			shopperToBlacklist.setBlacklistDetails(blacklistDetails);
		}
		
		// Update shopper in DB
		shopperRepository.save(shopperToBlacklist);
	}
}
