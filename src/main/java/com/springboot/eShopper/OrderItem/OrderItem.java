package com.springboot.eShopper.OrderItem;

import com.springboot.eShopper.Product.Product;

public class OrderItem extends Product {
	private int itemCount;

	public int getItemCount() {
		return itemCount;
	}
	public void setItemCount(int itemCount) {
		this.itemCount = itemCount;
	}
}
