package com.springboot.eShopper.Address;

import com.springboot.eShopper.User.User;

public class Address {
	private int addressId;	// surrogate key
	private User user;
	private String addressDetails;
	
	public int getAddressId() {
		return addressId;
	}
	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getAddressDetails() {
		return addressDetails;
	}
	public void setAddressDetails(String addressDetails) {
		this.addressDetails = addressDetails;
	}
}
