package com.springboot.eShopper.Address;

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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.springboot.eShopper.Purchase.Purchase;
import com.springboot.eShopper.Shopper.Shopper;

@Entity
@Table(name="address")
public class Address {
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="address_id")
	private int addressId;	// surrogate key
	
	@ManyToOne
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private Shopper shopper;

	@Column(name="address_details")
	private String addressDetails;
	
	@OneToMany(mappedBy="purchaseAddress", cascade = CascadeType.ALL)
	@ElementCollection(fetch=FetchType.EAGER)
	@JsonIgnore
	private Collection<Purchase> purchases = new ArrayList<>();
	
	// Getters and Setters
	public int getAddressId() {
		return addressId;
	}
	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}
	public Shopper getShopper() {
		return shopper;
	}
	public void setShopper(Shopper shopper) {
		this.shopper = shopper;
	}
//	public String getShopperName() {
//		return shopperName;
//	}
//	public void setShopperName() {
//		this.shopperName = this.shopper.getFirstName() + " " + this.shopper.getLastName();
//	}
	public String getAddressDetails() {
		return addressDetails;
	}
	public void setAddressDetails(String addressDetails) {
		this.addressDetails = addressDetails;
	}
	public Collection<Purchase> getPurchases() {
		return purchases;
	}
	public void setPurchases(Collection<Purchase> purchases) {
		this.purchases = purchases;
	}
}
