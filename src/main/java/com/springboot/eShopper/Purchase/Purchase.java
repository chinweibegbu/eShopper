package com.springboot.eShopper.Purchase;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

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
import com.springboot.eShopper.Address.Address;
import com.springboot.eShopper.PurchaseItem.PurchaseItem;
import com.springboot.eShopper.Shopper.Shopper;

@Entity
@Table(name="purchase")
public class Purchase {
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="purchase_id")
	private int purchaseId; // natural key
	
	@ManyToOne
	@JsonIgnore
	private Shopper shopper;
	
	@OneToMany(mappedBy="purchase", cascade = CascadeType.ALL)
	@ElementCollection(fetch=FetchType.EAGER)
	private Collection<PurchaseItem> purchaseItems = new ArrayList<>();
	
	@ManyToOne
	private Address purchaseAddress;
	
	@Column(name="delivery_window")
	private Date deliveryWindow;
	
	@Column(name="delivery_fee")
	private double deliveryFee;
	
	@Column(name="shopper_tip")
	private double shopperTip;
	
	@Column(name="total_cost")
	private double totalCost;
	
	// Getters and Setters
	public int getPurchaseId() {
		return purchaseId;
	}
	public void setPurchaseId(int purchaseId) {
		this.purchaseId = purchaseId;
	}
	public Shopper getShopper() {
		return shopper;
	}
	public void setShopper(Shopper shopper) {
		this.shopper = shopper;
	}
	public Collection<PurchaseItem> getPurchaseItems() {
		return purchaseItems;
	}
	public void setPurchaseItems(Collection<PurchaseItem> purchaseItems) {
		this.purchaseItems = purchaseItems;
	}
	public Address getShopperAddress() {
		return purchaseAddress;
	}
	public void setShopperAddress(Address shopperAddress) {
		this.purchaseAddress = shopperAddress;
	}
	public Date getDeliveryWindow() {
		return deliveryWindow;
	}
	public void setDeliveryWindow(Date deliveryWindow) {
		this.deliveryWindow = deliveryWindow;
	}
	public double getDeliveryFee() {
		return deliveryFee;
	}
	public void setDeliveryFee(double deliveryFee) {
		this.deliveryFee = deliveryFee;
	}
	public double getShopperTip() {
		return shopperTip;
	}
	public void setShopperTip(double shopperTip) {
		this.shopperTip = shopperTip;
	}
	public Address getPurchaseAddress() {
		return purchaseAddress;
	}
	public void setPurchaseAddress(Address purchaseAddress) {
		this.purchaseAddress = purchaseAddress;
	}
	public double getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}
}
