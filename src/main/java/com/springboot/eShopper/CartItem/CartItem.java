package com.springboot.eShopper.CartItem;

import com.springboot.eShopper.Product.Product;

public class CartItem extends Product {
	private int itemCount;

	public int getItemCount() {
		return itemCount;
	}
	public void setItemCount(int itemCount) {
		this.itemCount = itemCount;
	}
}
