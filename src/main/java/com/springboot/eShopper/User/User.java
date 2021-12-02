package com.springboot.eShopper.User;

import java.util.List;

import com.springboot.eShopper.Address.Address;
import com.springboot.eShopper.UserFavourites.UserFavourites;

public class User {
	private int userId;
	private String firstName;
	private String lastName;
	private int phoneNumber;
	private String email;
	private List<Address> addresses;
	private List<UserFavourites> userFavourites;
	private double wallet;
	private boolean isBlackListed;
	private String blackListDetails;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public double getWallet() {
		return wallet;
	}
	public void setWallet(double wallet) {
		this.wallet = wallet;
	}
	public boolean isBlackListed() {
		return isBlackListed;
	}
	public void setBlackListed(boolean isBlackListed) {
		this.isBlackListed = isBlackListed;
	}
	public String getBlackListDetails() {
		return blackListDetails;
	}
	public void setBlackListDetails(String blackListDetails) {
		this.blackListDetails = blackListDetails;
	}
	public List<UserFavourites> getUserFavourites() {
		return userFavourites;
	}
	public void setUserFavourites(List<UserFavourites> userFavourites) {
		this.userFavourites = userFavourites;
	}
	public List<Address> getAddresses() {
		return addresses;
	}
	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}
}
