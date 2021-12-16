package com.springboot.eShopper.PurchaseItem;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.springboot.eShopper.Product.Product;
import com.springboot.eShopper.Purchase.Purchase;

@Entity
@Table(name="purchase_item")
public class PurchaseItem extends Product {
	@ManyToOne
	@JsonIgnore
	private Purchase purchase;
	
	@Column(name="item_count")
	private int itemCount;

	// Getter and Setters
	public int getItemCount() {
		return itemCount;
	}
	public void setItemCount(int itemCount) {
		this.itemCount = itemCount;
	}
	public Purchase getPurchase() {
		return purchase;
	}
	public void setPurchase(Purchase purchase) {
		this.purchase = purchase;
	}
}
