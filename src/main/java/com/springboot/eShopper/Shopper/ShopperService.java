package com.springboot.eShopper.Shopper;
import java.util.*;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

@Service
public class ShopperService {
	
	private final ShopperRepository shopperRepository;
	
	@Autowired
	public ShopperService(ShopperRepository shopperRepository) {
		this.shopperRepository = shopperRepository;
	}
	
	public List<Shopper> getAllShoppers() {
		return shopperRepository.findAll();
	}

	public void addNewShopper(Shopper shopper) {
		// If the email address exists in the DB, throw an error
		Optional<Shopper> shopperWithEmail = shopperRepository.getShopperByEmail(shopper.getEmail());
		
		if(shopperWithEmail.isPresent()) {
			throw new IllegalStateException("Email already exists");
		}
		
		// Else, save the shopper
		shopperRepository.save(shopper);		
	}

	public void deleteShopper(Integer shopperId) {
		// Get shopper with the given ID
		boolean exists = shopperRepository.existsById(shopperId);
		
		if(!exists) {
			throw new IllegalStateException("Shopper with an id of " + shopperId + " does not exist");
		}
		
		// Else, save the shopper
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
}
