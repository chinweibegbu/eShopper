package com.springboot.eShopper.Shopper;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.springboot.eShopper.Address.Address;
import com.springboot.eShopper.Cart.Cart;
import com.springboot.eShopper.Favourite.Favourite;
import com.springboot.eShopper.Purchase.Purchase;

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
	
	@Column(name="phone_number")
	private String phoneNumber;
	
	@Column(name="email")
	private String email;
	
	@OneToMany(mappedBy="shopper", cascade = CascadeType.ALL)
	@ElementCollection(fetch=FetchType.EAGER)
	@JsonIgnore
	private Collection<Address> addresses = new ArrayList<>();
	
	@OneToOne
	private Cart cart;

	@OneToMany(mappedBy="shopper", cascade = CascadeType.ALL)
	@ElementCollection(fetch=FetchType.EAGER)
	@JsonIgnore
	private Collection<Purchase> purchases = new ArrayList<>();
	
	@OneToMany(mappedBy="shopper", cascade = CascadeType.ALL)
	@ElementCollection(fetch=FetchType.LAZY)
	@JsonIgnore
	private Collection<Favourite> favourites = new ArrayList<>();
	
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
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
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
	public Collection<Favourite> getFavourites() {
		return favourites;
	}
	public void setFavourites(Collection<Favourite> favourites) {
		this.favourites = favourites;
	}
	public Collection<Address> getAddresses() {
		return addresses;
	}
	public void setAddresses(Collection<Address> addresses) {
		this.addresses = addresses;
	}
	public Cart getCart() {
		return cart;
	}
	public void setCart(Cart cart) {
		this.cart = cart;
	}
	public Collection<Purchase> getPurchases() {
		return purchases;
	}
	public void setPurchase(Collection<Purchase> purchases) {
		this.purchases = purchases;
	}
}
