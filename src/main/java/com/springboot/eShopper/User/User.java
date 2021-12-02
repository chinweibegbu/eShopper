package com.springboot.eShopper.User;

import java.util.List;

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
@Table(name="user")
public class User {
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="user_id")
	private int userId;	// natural or surrogate key?
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="phont_number")
	private int phoneNumber;
	
	@Column(name="email")
	private String email;
	
	@OneToMany
	@JoinTable(
			name="user_address",
			joinColumns=@JoinColumn(name="user_id"),
			inverseJoinColumns=@JoinColumn(name="address_id")
	)
	private List<Address> addresses;
	
	@OneToOne
	private Cart cart;

	@OneToMany
	@JoinTable(
			name="user_order",
			joinColumns=@JoinColumn(name="user_id"),
			inverseJoinColumns=@JoinColumn(name="order_id")
	)
	private List<Order> orders;
	
	@OneToMany
	@JoinTable(
			name="user_favourite",
			joinColumns=@JoinColumn(name="user_id"),
			inverseJoinColumns=@JoinColumn(name="favourite_id")
	)
	private List<Favourite> favourites;
	
	@Column(name="wallet")
	private double wallet;
	
	@Column(name="is_blacklisted")
	private boolean isBlacklisted;
	
	@Column(name="blacklist_details")
	private String blacklistDetails;
	
	// Getters and Setters
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
		return isBlacklisted;
	}
	public void setBlackListed(boolean isBlacklisted) {
		this.isBlacklisted = isBlacklisted;
	}
	public String getBlackListDetails() {
		return blacklistDetails;
	}
	public void setBlackListDetails(String blacklistDetails) {
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
	public List<Order> getOrder() {
		return orders;
	}
	public void setOrder(List<Order> order) {
		this.orders = order;
	}
}
