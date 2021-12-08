package com.springboot.eShopper.Shopper;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.springboot.eShopper.Address.Address;
import com.springboot.eShopper.Cart.Cart;
import com.springboot.eShopper.Favourite.Favourite;
import com.springboot.eShopper.Order.Order;

@Entity
@Table(name="shopper")
public class Shopper {
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="shopper_id")
	private int shopperId;	// natural or surrogate key?
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="phont_number")
	private int phoneNumber;
	
	@Column(name="email")
	private String email;
	
	@OneToMany(mappedBy="shopper", cascade = CascadeType.ALL)
	private List<Address> addresses = new ArrayList<>();
	
	@OneToOne
	private Cart cart;

	@OneToMany(mappedBy="shopper", cascade = CascadeType.ALL)
	private List<Order> orders = new ArrayList<>();
	
	@OneToMany(mappedBy="shopper", cascade = CascadeType.ALL)
	private List<Favourite> favourites = new ArrayList<>();
	
	@Column(name="wallet")
	private double wallet;
	
	@Column(name="is_blacklisted")
	private boolean isBlacklisted;
	
	@Column(name="blacklist_details")
	private String blacklistDetails;
	
	// Getters and Setters
	public int getShopperId() {
		return shopperId;
	}
	public void setShopperId(int shopperId) {
		this.shopperId = shopperId;
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
	public boolean isBlacklisted() {
		return isBlacklisted;
	}
	public void setBlacklisted(boolean isBlacklisted) {
		this.isBlacklisted = isBlacklisted;
	}
	public String getBlacklistDetails() {
		return blacklistDetails;
	}
	public void setBlacklistDetails(String blacklistDetails) {
		this.blacklistDetails = blacklistDetails;
	}
	public List<Favourite> getFavourites() {
		return favourites;
	}
	public void setFavourites(List<Favourite> favourites) {
		this.favourites = favourites;
	}
	public List<Address> getAddresses() {
		return addresses;
	}
	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}
	public Cart getCart() {
		return cart;
	}
	public void setCart(Cart cart) {
		this.cart = cart;
	}
	public List<Order> getOrders() {
		return orders;
	}
	public void setOrder(List<Order> orders) {
		this.orders = orders;
	}
}
