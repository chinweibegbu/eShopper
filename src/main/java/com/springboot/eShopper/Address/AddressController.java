package com.springboot.eShopper.Address;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="api/v1/addresses")
public class AddressController {
	
	private final AddressService addressService;
	
	@Autowired
	public AddressController(AddressService addressService) {
		this.addressService = addressService;
	}

	@GetMapping
	public List<Address> getAllAddresses() {
		return addressService.getAllAddresses();
	}
	
	@GetMapping(path="{addressId}")
	public Address getAddressById(@PathVariable("addressId") Integer addressId) {
		return addressService.getAddressById(addressId);
	}
	
	@GetMapping(path="shopper/{shopperId}")
	public List<Address> getAddressedByShopper(@PathVariable("shopperId") Integer shopperId) {
		return addressService.getAddressesByShopperId(shopperId);
	}
	
	@PostMapping
	public void addNewAddress(@RequestBody Address address) {
		addressService.addNewAddress(address);
	}
	
	@DeleteMapping(path="{addressId}")
	public void deleteAddress(@PathVariable("addressId") Integer addressId) {
		addressService.deleteAddress(addressId);
	}
	
	@PutMapping(path="{addressId}")
	public void updateAddress(@RequestBody Address address, @PathVariable Integer addressId) {
		addressService.updateAddress(address, addressId);
	}
}
