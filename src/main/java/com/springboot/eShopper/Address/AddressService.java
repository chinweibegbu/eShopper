package com.springboot.eShopper.Address;
import java.util.*;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

@Service
public class AddressService {
	
	private final AddressRepository addressRepository;
	
	@Autowired
	public AddressService(AddressRepository addressRepository) {
		this.addressRepository = addressRepository;
	}
	
	public List<Address> getAllAddresss() {
		return addressRepository.findAll();
	}

	public void addNewAddress(Address address) {
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
