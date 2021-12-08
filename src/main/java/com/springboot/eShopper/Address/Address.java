package com.springboot.eShopper.Address;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.springboot.eShopper.Shopper.Shopper;

@Entity
@Table(name="address")
public class Address {
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="address_id")
	private int addressId;	// surrogate key
	
	@ManyToOne
	private Shopper shopper;
	
	@Column(name="address_details")
	private String addressDetails;
	
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
	public String getAddressDetails() {
		return addressDetails;
	}
	public void setAddressDetails(String addressDetails) {
		this.addressDetails = addressDetails;
	}
}
