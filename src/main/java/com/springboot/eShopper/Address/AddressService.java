package com.springboot.eShopper.Address;
import java.util.*;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import com.springboot.eShopper.Shopper.Shopper;
import com.springboot.eShopper.Shopper.ShopperRepository;

@Service
public class AddressService {
	
	private final AddressRepository addressRepository;
	private final ShopperRepository shopperRepository;
	
	@Autowired
	public AddressService(AddressRepository addressRepository, ShopperRepository shopperRepository) {
		this.addressRepository = addressRepository;
		this.shopperRepository = shopperRepository;
	}
	
	public List<Address> getAllAddresses() {
		return addressRepository.findAll();
	}
	
	public Address getAddressById(Integer addressId) {
		// Get address with the given ID
		boolean exists = addressRepository.existsById(addressId);
		
		if(!exists) {
			throw new IllegalStateException("Address with an id of " + addressId + " does not exist");
		}
				
		return addressRepository.findById(addressId).get();
	}
	
	public List<Address> getAddressesByShopperId(Integer shopperId) {
		// Get address with the given ID
		boolean exists = shopperRepository.existsById(shopperId);
		
		if(!exists) {
			throw new IllegalStateException("Shopper with an id of " + shopperId + " does not exist");
		}
		
		// Get shopper with given ID
		Shopper shopper = shopperRepository.findById(shopperId).get();
		
		return addressRepository.getAddressesByShopper(shopper);
	}

	public void addNewAddress(Address address) {
		// Check if shopper exists
		Integer shopperId = address.getShopper().getShopperId();
		boolean exists = shopperRepository.existsById(shopperId);
		
		if(!exists) {
			throw new IllegalStateException("Shopper with an id of " + shopperId + " does not exist");
		}
		
		// Else, save address
		addressRepository.save(address);		
	}

	public void deleteAddress(Integer addressId) {
		// Get address with the given ID
		boolean exists = addressRepository.existsById(addressId);
		
		if(!exists) {
			throw new IllegalStateException("Address with an id of " + addressId + " does not exist");
		}
		
		// Else, save the address
		addressRepository.deleteById(addressId);
	}

	@Transactional
	public void updateAddress(Address address, Integer addressId) {
		boolean exists = addressRepository.existsById(addressId);
		
		if(!exists) {
			throw new IllegalStateException("Address with an id of " + addressId + " does not exist");
		}
		
		addressRepository.save(address);
	}
}
